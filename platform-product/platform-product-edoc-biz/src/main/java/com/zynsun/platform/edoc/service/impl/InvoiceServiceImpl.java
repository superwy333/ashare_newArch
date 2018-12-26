package com.zynsun.platform.edoc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.domain.Invoice;
import com.zynsun.platform.edoc.domain.InvoiceItem;
import com.zynsun.platform.edoc.mapper.InvoiceMapper;
import com.zynsun.platform.edoc.model.InvModifyModel;
import com.zynsun.platform.edoc.model.InvoiceCheckParam;
import com.zynsun.platform.edoc.model.InvoiceModel;
import com.zynsun.platform.edoc.service.*;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.InvoiceCheckUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zynsun.openplatform.util.BeanUtil.isNumber;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/08 11:14
 */
@Service
public class InvoiceServiceImpl extends BaseServiceImpl<Invoice> implements InvoiceService {

    private static final Logger INVOICE_LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceItemService invoiceItemService;
    @Autowired
    private EdocInvDiffService edocInvDiffService;
    @Autowired
    private EdocImageService edocImageService;
    @Autowired
    private EdocHeaderService edocHeaderService;

    @Override
    protected IMapper<Invoice> getBaseMapper() {
        return invoiceMapper;
    }

    @Override
    public Invoice selectInvoiceByImgId(long edocImageId) {
        Invoice invoice = new Invoice();
        invoice.setEdocImageId(edocImageId);
        return invoiceMapper.selectOne(invoice);
    }

    @Override
    public void deleteInvoiceByEdocHeaderId(Long edocHeaderId) {
        invoiceMapper.deleteInvoiceByEdocHeaderId(edocHeaderId);
    }

    @Override
    public Page<InvoiceModel> queryInvoices(InvoiceModel invoiceModel) {
        return invoiceMapper.queryInvoices(invoiceModel);
    }

    @Override
    public List<InvoiceModel> queryInvoicesNoPage(InvoiceModel invoiceModel) {
        return invoiceMapper.queryInvoicesNoPage(invoiceModel);
    }

    @Override
    public Page<InvoiceModel> queryInvoicesDataGrid(InvoiceModel invoiceModel) {
        return invoiceMapper.queryInvoicesDataGrid(invoiceModel);
    }

    @Override
    public Page<InvoiceModel> queryInvoicesByEdocNo(String edocNo) {
        Page<InvoiceModel> page = invoiceMapper.queryInvoicesByEdocNo(edocNo);
        return page;
    }

    @Override
    public Page<InvoiceModel> queryInvoicesByEdocNoForCheck(InvoiceModel invoiceModel) {
        Page<InvoiceModel> page = invoiceMapper.queryInvoicesByEdocNoForCheck(invoiceModel);
        return page;
    }

    @Override
    public String CheckInvoice(Long id) throws IOException{
        //根据id获取发票实例
        Invoice invoice = this.queryByInvId(id);

        if(BeanUtil.isEmpty(invoice)){
            return "该条发票不存在";
        }

        String resultJson = InvoiceCheckUtils.sendPost(tranToParam(invoice));

        if(!BeanUtil.isEmpty(JSON.parseObject(resultJson).get("errMsg"))){
            return "发票号码："+invoice.getInvNo()+" "+JSON.parseObject(resultJson).get("errMsg");
        }

        JSONObject invoiceJson = JSON.parseObject(resultJson).getJSONObject("invAllDTO");

        invoice = new Invoice();
        invoice.setInvCode(invoiceJson.getString("invoiceCode"));
        invoice.setInvNo(invoiceJson.getString("invoiceNum"));
        invoice.setInvDate(invoiceJson.getString("invoiceDate"));
        invoice.setInvAmount(invoiceJson.getString("amountTotal"));
        invoice.setCheckCode(invoiceJson.getString("checkCode"));
        invoice.setInvType(invoiceJson.getString("invoiceType"));
        invoice.setCheckReal(invoiceJson.getString("checkStatus"));
        invoice.setBuyerName(invoiceJson.getString("custName"));
        invoice.setBuyerTaxCode(invoiceJson.getString("custTaxCode"));
        invoice.setSalerName(invoiceJson.getString("compName"));
        invoice.setSalerTaxCode(invoiceJson.getString("comTaxCode"));
        invoice.setInvTax(invoiceJson.getString("taxTotal"));
        invoice.setInvTotal(invoiceJson.getString("amountTax"));
        invoice.setInvMachineNo(invoiceJson.getString("machineCode"));

        this.add(invoice);

        JSONArray itemJson = invoiceJson.getJSONArray("list");
        List<HashMap> items = JSON.parseArray(itemJson.toJSONString(),HashMap.class);

        List<InvoiceItem> itemList = new ArrayList<>();

        items.parallelStream().forEach(item->{
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setItemName((String)item.get("goodsName"));
            invoiceItem.setItemAmount((String)item.get("goodsAmount"));
            invoiceItem.setItemPrice((String)item.get("goodsPrice"));
            invoiceItem.setItemTaxRate((String)item.get("taxRate"));
            invoiceItem.setItemTax((String)item.get("taxAmount"));
            invoiceItem.setItemSpec((String)item.get("goodsSpec"));
            invoiceItem.setItemQuantity((String)item.get("goodsQuantity"));
            invoiceItem.setUnit((String)item.get("goodsUnit"));
            itemList.add(invoiceItem);
        });

        invoiceItemService.addAll(itemList);

        return null;
    }

    @Override
    public Invoice queryByInvId(Long id) {
        Invoice invoice = new Invoice();
        invoice.setId(id);
        return this.queryOne(invoice);
    }

    public String tranToParam(Invoice invoice){
        InvoiceCheckParam invoiceCheckParam = new InvoiceCheckParam();
        invoiceCheckParam.addInvoiceParam(invoice.getInvCode(),invoice.getInvNo(),invoice.getInvDate(),invoice.getInvAmount(),invoice.getCheckCode());
        String json = JSON.toJSONString(invoiceCheckParam);
        return json;
    }

    @Override
    public Invoice queryInvoiceByImgId(Long imgId) {
        return invoiceMapper.selectByImgId(imgId);
    }

    @Override
    public List<Invoice> queryInvoiceListByImgId(Long imgId) {
        return invoiceMapper.selectInvoiceListByImgId(imgId);
    }

    @Override
    public List<Invoice> queryRepeatInvList(Invoice invoice) {
        return invoiceMapper.queryRepeatInvList(invoice);
    }

    @Override
    public List<Invoice> queryRepeatInvListNew(Invoice invoice) {
        return invoiceMapper.queryRepeatInvListForMaualCheckStatus(invoice);
    }

    @Override
    public List<Invoice> queryCheckRealNullList() {
        return invoiceMapper.queryInvCheckRealList();
    }

    @Override
    public Invoice updateInvoice(InvoiceModel waitEditInvModel) {
        Invoice result = new Invoice();

        if (BeanUtil.isEmpty(waitEditInvModel.getId())){
            //id不存在，新增数据
            Invoice invoice = DozerUtil.map(waitEditInvModel, Invoice.class);
            this.add(invoice);
            result = invoice;
//            INVOICE_LOGGER.info("发票id获取失败，发票代码：{}，发票号码：{}", waitEditInvModel.getInvCode(), waitEditInvModel.getInvNo());
            return result;
        }

        Invoice invoice = DozerUtil.map(waitEditInvModel, Invoice.class);
        //修改发票数据
        // invoice.setCheckStatus(Constant.CheckStatus.PASS);
        Invoice beforeUpdateInv = this.queryById(invoice.getId());
        invoice.setVersion(beforeUpdateInv.getVersion()); // 避免乐观锁

        // 发票修改记录
        edocInvDiffService.createInvChangeHistory(DozerUtil.map(invoice, InvModifyModel.class));

        this.updateByIdSelective(invoice);
        result = invoice;

        return result;
    }

    @Override
    public int deleteInvoiceByImgId(long imgId) {
        int delResult = 0;

        // 删除图片
        EdocImage waitDelImg = edocImageService.queryById(imgId);
        if (waitDelImg == null) {
            return delResult;
        }
        edocImageService.remove(waitDelImg);

        // 如果图片对应有发票,对应的发票也需删除
        List<Invoice> waitDelInvList = this.queryInvoiceListByImgId(imgId);
        if (!BeanUtil.isEmpty(waitDelInvList)) {
            for (Invoice invoice : waitDelInvList) {
                this.remove(invoice);
            }
        }
        delResult = 1;

        return delResult;
    }

    @Override
    public void invoiceCheckRule(Invoice invoice) {

        //发票冲突校验
        if (this.isRepeat(invoice.getInvNo(),invoice.getInvCode())) {
            Invoice queryCondition = new Invoice();
            queryCondition.setInvNo(invoice.getInvNo());
            queryCondition.setInvCode(invoice.getInvCode());
            List<Invoice> conflicInvoiceList = this.query(queryCondition);
            List<String> edocNos = new ArrayList<>();
            for (Invoice i:conflicInvoiceList) {
                EdocImage edocImage = edocImageService.queryById(i.getEdocImageId());
                if (null != edocImage) {
                    EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
                    if (null != edocHeader&&!edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.INVALID)) {
                        edocNos.add(edocHeader.getEdocNo());
                    }
                }
            }
            invoice.setExtField1(edocNos.toString());
            if (BeanUtil.isEmpty(invoice.getCheckStatus())||!Constant.InvoiceCheckStatus.PASS.equals(invoice.getCheckStatus())) {
                invoice.setCheckStatus(Constant.InvoiceCheckStatus.CONFLICT);
            }
            //发票规则校验
        }else if (this.isNoAndCodeInVaildRenRenLe(invoice)){
            invoice.setCheckStatus(Constant.InvoiceCheckStatus.NOT_PASS);
        }else {
            invoice.setCheckStatus(Constant.InvoiceCheckStatus.PASS);
        }
        this.updateByIdSelective(invoice);
    }

    @Override
    public Invoice invoiceCheckRuleNoUpdate(Invoice invoice) {
        try {
            //发票冲突校验
            if (this.isRepeatNoUpdate(invoice.getInvNo(),invoice.getInvCode())) {
                Invoice queryCondition = new Invoice();
                queryCondition.setInvNo(invoice.getInvNo());
                queryCondition.setInvCode(invoice.getInvCode());
                List<Invoice> conflicInvoiceList = this.query(queryCondition);
                List<String> edocNos = new ArrayList<>();
                for (Invoice i:conflicInvoiceList) {
                    EdocImage edocImage = edocImageService.queryById(i.getEdocImageId());
                    if (null != edocImage) {
                        EdocHeader edocHeader = edocHeaderService.queryById(edocImage.getEdocHeaderId());
                        if (null != edocHeader&&!edocHeader.getEdocTaskStatus().equals(Constant.BizStatus.INVALID)) {
                            edocNos.add(edocHeader.getEdocNo());
                        }
                    }
                }
                // 发票冲突字段长度保护
                if (edocNos.size() > 5) {
                    invoice.setExtField1("冲突过多，超出数据库最大限制，请联系管理员！");

                }else {
                    invoice.setExtField1(edocNos.toString());
                }
                invoice.setCheckStatus(Constant.InvoiceCheckStatus.CONFLICT);
                //发票规则校验
            }else if (this.isNoAndCodeInVaildNew(invoice)){
                invoice.setCheckStatus(Constant.InvoiceCheckStatus.NOT_PASS);
            }else {
                invoice.setCheckStatus(Constant.InvoiceCheckStatus.PASS);
            }
        }catch (Exception e) { // 加入异常处理，防止异常导致上传失败
            e.printStackTrace();
        }
        return invoice;
    }

    /***
     * 判断发票代码和发票号码是否存在重复于数据库
     * @param invNo
     * @param invCode
     * @return
     */
    private boolean isRepeat(String invNo, String invCode) {
        boolean flag = false;
        Invoice invoice = new Invoice();
        invoice.setInvCode(invCode);
        invoice.setInvNo(invNo);
        List<Invoice> list = this.query(invoice);
        if (list != null && list.size() > 1) {
            flag = true;
        }
        return flag;
    }

    /***
     * 判断发票代码和发票号码是否存在重复于数据库
     * @param invNo
     * @param invCode
     * @return
     */
    private boolean isRepeatNoUpdate(String invNo, String invCode) {
        boolean flag = false;
        Invoice invoice = new Invoice();
        invoice.setInvCode(invCode);
        invoice.setInvNo(invNo);
        List<Invoice> list = this.query(invoice);
        if (list != null && list.size() >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * 发票代码和发票号码的规则校验
     * @param invNo
     * @param invCode
     * @return
     */
    private boolean isNoAndCodeInVaild(String invNo,String invCode) {
        if (invNo.length()!=8||invCode.length()!=10) {
            return true;
        }
        return false;
    }

    private boolean isNoAndCodeInVaildRenRenLeDetails(Invoice invoice) {
        if (12!=invoice.getInvCode().length() && 10!=invoice.getInvCode().length()) {
            return true;
        }
        if (8!=invoice.getInvNo().length()) {
            return true;
        }
        /*if (!"0".equals(String.valueOf(invoice.getInvCode().charAt(0)))
                &&!"1".equals(String.valueOf(invoice.getInvCode().charAt(0)))
                &&!"2".equals(String.valueOf(invoice.getInvCode().charAt(0)))) {
            return true;
        }*/
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(invoice.getInvNo());
        if (!isNum.matches()) {
            return true;
        }
        if (!isInvDateInVaild(invoice.getInvDate())) {
            return true;
        }
        if (!isNumber(invoice.getInvAmount())) {
            return true;
        }
        return false;
    }

    private boolean isInvDateInVaild(String invDate) {
        boolean convertSuccess = true;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            format.parse(invDate);
        }catch (Exception e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }


    /**
     * renrenle
     增值税普通发票

     发票代码    12位  第一位 ：0/1/2
     发票号码   纯数字    8位
     发票日期      日期格式
     后六位校验码   纯数字


     增值税专用发票

     发票代码   12位  第一位 ：0/1/2
     发票号码   纯数字   8位
     发票日期    日期格式
     金额      数值格式
     * @param invoice
     * @return
     */
    private boolean isNoAndCodeInVaildRenRenLe(Invoice invoice) {
        // 非空判断
        if (BeanUtil.isEmpty(invoice.getInvCode())) return true;
        if (BeanUtil.isEmpty(invoice.getInvNo())) return true;
        if (BeanUtil.isEmpty(invoice.getInvDate())) return true;
        if (BeanUtil.isEmpty(invoice.getInvAmount())) return true;
        if (Constant.InvoiceType.VATN_INV.equals(invoice.getInvType())) {
            if (BeanUtil.isEmpty(invoice.getCheckCode())) return true;
        }
        // 规则判断
        if (Constant.InvoiceType.INV_NORMAL.equals(invoice.getInvType())) { // 电子发票
            if (invoice.getInvNo().length()!=8 && invoice.getInvCode().length()!=12) {
                return true;
            }
        }else if (Constant.InvoiceType.VATS_INV.equals(invoice.getInvType())) { // 增专
            return isNoAndCodeInVaildRenRenLeDetails(invoice);
        }else if (Constant.InvoiceType.VATN_INV.equals(invoice.getInvType())) { // 增普
            boolean isReturn = isNoAndCodeInVaildRenRenLeDetails(invoice);
            if (isReturn) {
                return isReturn;
            }else {
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher isNum = pattern.matcher(invoice.getCheckCode());
                if (!isNum.matches()) {
                    return true;
                }
            }
        }else { // 其他发票
            // TODO 待补充
        }
        return false;
    }

    /**
     * 发票代码和号码规则校验，新增电子发票校验逻辑
     * @param invoice
     * @return
     */
    private boolean isNoAndCodeInVaildNew(Invoice invoice) {
        if (invoice.getInvType().equals(Constant.InvoiceType.INV_NORMAL)) { //电子发票
            if (invoice.getInvNo().length()!=8||invoice.getInvCode().length()!=12) {
                return true;
            }
        }else { //其他发票
            if (invoice.getInvNo().length() !=8|| (invoice.getInvCode().length()!=10 && invoice.getInvCode().length()!=12)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<InvoiceModel> queryInputInvoiceTaxPage(InvoiceModel invoiceModel) {
        return invoiceMapper.queryInputInvoiceTaxPage(invoiceModel);
    }

    @Override
    public Page<InvoiceModel> queryInputInvoiceItem(InvoiceModel invoiceModel) {
        return invoiceMapper.queryInputInvoiceItem(invoiceModel);
    }

    @Override
    public InvoiceItem queryByInvId_sum(Long invId) {
        return invoiceMapper.queryByInvId_sum(invId);
    }

    @Override
    public Page<InvoiceModel> exportWaitCertInv_Part(InvoiceModel invoiceModel) {
        return invoiceMapper.exportWaitCertInv_Part(invoiceModel);
    }

    @Override
    public Page<InvoiceModel> exportWaitCertInv_All(InvoiceModel invoiceModel) {
        return invoiceMapper.exportWaitCertInv_All(invoiceModel);
    }

    @Override
    public int deleteInvoiceByImageId(Long imageId) {
        return invoiceMapper.deleteInvoiceByImageId(imageId);
    }

    @Override
    public List<Invoice> queryInvsByEdocHeaderId(String edocNo) {
        return invoiceMapper.queryInvsByEdocHeaderId(edocNo);
    }

    @Override
    public List<Invoice> queryWaitCheckRealInv() {
        return invoiceMapper.selectWaitCheckRealInv();
    }

    @Override
    public List<Invoice> queryInvByEdocHeader(Long edocHeaderId) {
        return invoiceMapper.selectInvByEdocHeader(edocHeaderId);
    }

    @Override
    public Long getEdocHeaderIdByInvId(Long invId) {
        return invoiceMapper.getEdocHeaderIdByInvId(invId);
    }
}
