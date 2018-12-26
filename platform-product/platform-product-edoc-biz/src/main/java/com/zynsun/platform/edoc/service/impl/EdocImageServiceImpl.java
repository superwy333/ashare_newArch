package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.InvoiceFacade;
import com.zynsun.platform.edoc.mapper.EdocImageMapper;
import com.zynsun.platform.edoc.model.EdocImageModel;
import com.zynsun.platform.edoc.service.EdocImageService;
import com.zynsun.platform.edoc.service.InvoiceItemService;
import com.zynsun.platform.edoc.service.InvoiceService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 14:33 2017/12/27
 * @Modified By:
 */
@Service
public class EdocImageServiceImpl extends BaseServiceImpl<EdocImage> implements EdocImageService {

    @Autowired
    private EdocImageMapper edocImageMapper;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceItemService invoiceItemService;
    @Autowired
    private InvoiceFacade invoiceFacade;
    @Autowired
    private EdocHeaderFacade edocHeaderFacade;

    @Override
    protected IMapper<EdocImage> getBaseMapper() {
        return edocImageMapper;
    }

    @Override
    public void updateEdocImage(long edocImageId) throws Exception {
        EdocImage edocImage = this.queryById(edocImageId);
        edocImage.setId(edocImageId);
        edocImage.setDeleted(Constant.DeleteFlag.DEL_YES);
        this.updateById(edocImage);
        Invoice invoice = invoiceService.queryInvoiceByImgId(edocImageId);
        if (!BeanUtil.isEmpty(invoice)) {
            invoice.setDeleted(Constant.DeleteFlag.DEL_YES);
            invoiceService.updateById(invoice);
        }
    }

    @Override
    public void delImagesByEdocHeaderId(long edocHeaderId) {
        edocImageMapper.delImagesByEdocHeaderId(edocHeaderId, Constant.DeleteFlag.DEL_YES);
    }

    @Override
    public void delImagesByEdocHeaderIdAndPageNo(long edocHeaderId, long pageNo) {
        EdocImage queryImages = new EdocImage();
        queryImages.setDeleted(Constant.DeleteFlag.DEL_NO);
        queryImages.setEdocHeaderId(edocHeaderId);
        queryImages.setPageNo(pageNo);
        List<EdocImage> imagesList = edocImageMapper.select(queryImages);
        if (!BeanUtil.isEmpty(imagesList)) {
            for (EdocImage edocImage : imagesList) {
                invoiceService.remove(invoiceService.selectInvoiceByImgId(edocImage.getId()));
                remove(edocImage);
            }
        }
    }

    @Override
    public int queryMaxPagNum(Long edocHeaderId) {
        return edocImageMapper.queryMaxPageNoByEdocHeaderId(edocHeaderId);
    }

    @Override
    public void updateImages(EdocImage edocImage, EdocImage copyEdocImage) {
        //逻辑删除原有图片
        this.remove(edocImage);
        //替换新图片
        this.add(copyEdocImage);
    }

    @Override
    public void operateUpdateImages(EdocImage edocImage, EdocImage copyEdocImage) {
        // 替换图片
        this.updateImages(edocImage, copyEdocImage);

        Invoice isInv = invoiceService.selectInvoiceByImgId(edocImage.getId());
        // 如果是发票图片 1.更新发票表图片id
        if (isInv != null) {
            // 更新发票对应的图片id
            isInv.setEdocImageId(copyEdocImage.getId());
            invoiceService.updateByIdSelective(isInv);
        }

    }

    @Override
    public List<EdocImage> queryVouncherById(Long id) {
        return edocImageMapper.selectVouncherById(String.valueOf(id));
    }

    @Override
    public List<EdocImageModel> selectImages(EdocImageModel edocImageModel) {
        List<EdocImage> list = edocImageMapper.select(DozerUtil.map(edocImageModel, EdocImage.class));
        if (list != null && !list.isEmpty()) {
            return DozerUtil.mapList(list, EdocImageModel.class);
        } else {
            return null;
        }
    }

    @Override
    public List<EdocImageModel> selectImagesByEdocHeaderId(Long edocHeaderId, int docType) {
        return edocImageMapper.selectImagesByEdocHeaderId(edocHeaderId, docType);
    }

    @Override
    public List<EdocImage> selectAllImagesByEdocHeaderId(Long edocHeaderId) {
        return edocImageMapper.selectAllImagesByEdocHeaderId(edocHeaderId);
    }

    @Override
    public ExecuteResult<EdocImage> saveEdocImageAndInvoice(EdocImage edocImage, Invoice invoice, List<InvoiceItem> invoiceItems) {
        ExecuteResult<EdocImage> result = new ExecuteResult<>();
        if (null != edocImage) {
            // 保存img 至数据库
            edocImage.setImageSource("1");
            edocImage.setImageFormat("jpg");
            if (edocImage.getId() != null) {
                this.updateByIdSelective(edocImage);

            } else {
                this.add(edocImage);
            }

            if (null != invoice
                    &&!"FY_COVER".equals(edocImage.getEdocImageType())
                    &&!"HT_COVER".equals(edocImage.getEdocImageType())
                    &&!"CG_COVER".equals(edocImage.getEdocImageType())
                    &&!"ZL_COVER".equals(edocImage.getEdocImageType())
                    &&!"1001".equals(edocImage.getEdocImageType())) {
                // 电子发票客户端传给服务端5, 需要转为4
                if ("5".equals(invoice.getInvType())) {
                    invoice.setInvType(Constant.InvoiceType.INV_NORMAL);
                }

                // todo 是否是发票, 判断逻辑待商榷
                boolean isInv = Arrays.asList(
                        Constant.InvoiceType.VATS_INV,
                        Constant.InvoiceType.VATN_INV,
                        Constant.InvoiceType.P_INV,
                        Constant.InvoiceType.INV_NORMAL).contains(invoice.getInvType());

                if (isInv&&!BeanUtil.isEmpty(invoice.getInvCode())&&!BeanUtil.isEmpty(invoice.getInvNo())) {
                    // 校验码截取后6位
                    String checkCode = invoice.getCheckCode();
                    if (null != checkCode && checkCode.length() >= 6) {
                        invoice.setCheckCode(checkCode.substring(checkCode.length() - 6, checkCode.length()));
                    }
                    invoice.setEdocImageId(edocImage.getId());
                    // invoice.setCheckStatus(Constant.CheckStatus.WAITTING);
                    // 客户端上传发票时做一次规则校验
                    try {
                        invoice = invoiceService.invoiceCheckRuleNoUpdate(invoice);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 规则校验通过, 发票验真状态设为待验真 ==> 所有发票创建状态就是待验真
                    invoice.setCheckReal("1");
                    if (invoice.getId() != null) {
                        invoiceService.updateByIdSelective(invoice);
                    } else {
                        invoiceService.add(invoice);
                    }

                    // 人人乐需求：同一个单据下多余的相同发票进行逻辑删除，并且把影像单证类型置为附件
                    List<Invoice> invoiceList = invoiceService.queryInvByEdocHeader(edocImage.getEdocHeaderId());
                    int repeatInvNum = 0;
                    for (Invoice i:invoiceList) {
                        if (i.getInvCode().equals(invoice.getInvCode())
                                &&i.getInvNo().equals(invoice.getInvNo())) {
                            repeatInvNum++;
                            if (repeatInvNum>1) {
                                invoice.setDeleted(1);
                                invoiceService.updateByIdSelective(invoice);
                                edocImage.setEdocImageType(Constant.BillTypeCode.NORMAL_BILL);
                                this.updateByIdSelective(edocImage);
                                break;
                            }
                        }
                    }

                    //如果规则校验通过，进行验真
                    // 如果发票验真国税接口调不通，则会影响客户端上传，所以这里不做验真

                    // 新建一个线程验真
                    //Invoice finalInvoice = invoice;
                    /*new Thread(() -> {
                        //这里是线程需要做的事情
                        try {
                            if (finalInvoice.getCheckStatus().equals(Constant.InvoiceCheckStatus.PASS)) {
                                Invoice queryCondition = new Invoice();
                                queryCondition.setInvCode(finalInvoice.getInvCode());
                                queryCondition.setInvNo(finalInvoice.getInvNo());
                                Invoice invoiceInDB = invoiceService.query(queryCondition).get(0);
                                invoiceFacade.checkReal(invoiceInDB.getId());
                                // 这个时候验真人填系统
                                InvoiceDTO invoiceInDB2 = invoiceFacade.queryInvoiceById(finalInvoice.getId()).getResult();
                                if (!BeanUtil.isEmpty(invoiceInDB2)&&!BeanUtil.isEmpty(invoiceInDB2.getCheckDetails())) {
                                    invoiceInDB2.setCheckRealOperator("客户端上传，系统自动验真");
                                    invoiceService.updateByIdSelective(DozerUtil.map(invoiceInDB2,Invoice.class));
                                }
                            }
                            // 每次验真完之后做异常检查
                            Long edocHeaderId = invoiceService.getEdocHeaderIdByInvId(finalInvoice.getId());
                            edocHeaderFacade.edocAbnormalCheck(edocHeaderId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();*/

                    /*if (invoice.getCheckStatus().equals(Constant.InvoiceCheckStatus.PASS)) {
                        Invoice queryCondition = new Invoice();
                        queryCondition.setInvCode(invoice.getInvCode());
                        queryCondition.setInvNo(invoice.getInvNo());
                        Invoice invoiceInDB = invoiceService.query(queryCondition).get(0);
                        invoiceFacade.checkReal(invoiceInDB.getId());
                        // 这个时候验真人填系统
                        InvoiceDTO invoiceInDB2 = invoiceFacade.queryInvoiceById(invoice.getId()).getResult();
                        if (!BeanUtil.isEmpty(invoiceInDB2)&&!BeanUtil.isEmpty(invoiceInDB2.getCheckDetails())) {
                            invoiceInDB2.setCheckRealOperator("客户端上传，系统自动验真");
                            invoiceService.updateByIdSelective(DozerUtil.map(invoiceInDB2,Invoice.class));
                        }
                    }*/
                }

                if (null != invoiceItems && invoiceItems.size() > 0 && !"1".equals(invoice.getDeleted())) {
                    for (InvoiceItem invoiceItem : invoiceItems) {
                        invoiceItem.setInvId(invoice.getId());
                        invoiceItem.setEdocImageId(edocImage.getId());
                        invoiceItem.setEdocHeaderId(edocImage.getEdocHeaderId());
                        if (invoiceItem.getId() != null) {
                            invoiceItemService.updateByIdSelective(invoiceItem);
                        } else {
                            invoiceItemService.add(invoiceItem);
                        }
                    }
                }
            }
        } else {
            result.addErrorMessage("影像信息为空");
        }
        result.setResult(edocImage);
        return result;
    }

    @Override
    public List<EdocImageModel> queryEdocImageByIds(List<Long> ids) {
        Condition condition = new Condition(EdocImage.class);
        if (BeanUtil.isEmpty(ids)) {
            condition.createCriteria().andEqualTo("deleted", 0);
        } else {
            condition.createCriteria().andIn("id", ids).andEqualTo("deleted", 0);
        }
        List<EdocImage> edocImage = edocImageMapper.selectByCondition(condition);
        if (BeanUtil.isEmpty(edocImage)) {
            return null;
        }
        return DozerUtil.mapList(edocImage, EdocImageModel.class);
    }

    @Override
    public List<EdocImage> queryEdocImage(EdocImage edocImage) {
        return edocImageMapper.selectEdocImage(edocImage);
    }

    @Override
    public Page<EdocImageModel> queryEdocImageByPage(EdocImageModel edocImageModel) {
        return edocImageMapper.selectEdocImageByPage(edocImageModel);
    }


    @Override
    public List<EdocImage> selectAllImages(Long parentId) {
        List<EdocImage> edocImageList = edocImageMapper.selectAllImages(parentId);
        return edocImageList;
    }


    @Override
    public List<EdocImage> queryEdocImageByParentId(Long id) {
        return edocImageMapper.selectEdocImageByParentId(id);
    }

    @Override
    public void addEdocImage(EdocImage edocImage, List<EdocImage> edocImageList, Long pageNo) {
        this.add(edocImage);
        int k = 0;
        //第一次进行循环，主要是为了找出当前追加图片在集合中的位置
        for (int i = 0; i < edocImageList.size(); i++) {
            if (edocImageList.get(i).getPageNo().equals(pageNo)) {
                k = i + 1;
                break;
            }
        }
        //第二次遍历，是将当前位置后面的pageNo加一，排序靠后
        for (int j = edocImageList.size() - 1; j >= k; j--) {
            Long page = edocImageList.get(j).getPageNo();
            edocImageList.get(j).setPageNo(page + 1);
            edocImageMapper.updateEdocImage(edocImageList.get(j));
        }
    }


}
