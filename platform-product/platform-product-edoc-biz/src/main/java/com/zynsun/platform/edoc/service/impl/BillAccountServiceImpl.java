package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.mapper.*;
import com.zynsun.platform.edoc.model.BillAccountModel;
import com.zynsun.platform.edoc.service.BillAccountService;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import com.zynsun.platform.edoc.service.basic.impl.EdocBaseServiceImpl;
import exception.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 10:51 2017/12/27
 * @Modified By:
 */
@Service
public class BillAccountServiceImpl extends EdocBaseServiceImpl<BillAccountInfo> implements BillAccountService {

    @Autowired
    private BillAccountInfoMapper billAccountInfoMapper;
    @Autowired
    private EdocHeaderMapper edocHeaderMapper;
    @Autowired
    private EdocImageMapper edocImageMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceItemMapper invoiceItemMapper;
    @Autowired
    private EdocHeaderService edocHeaderService;

    @Override
    protected IMapper<EdocHeader> getEdocHeaderMapper() {
        return edocHeaderMapper;
    }
    @Override
    protected IMapper<EdocImage> getEdocImageMapper() {
        return edocImageMapper;
    }
    @Override
    protected IMapper<Invoice> getInvoiceMapper() {
        return invoiceMapper;
    }
    @Override
    protected IMapper<InvoiceItem> getInvoiceItemMapper() {
        return invoiceItemMapper;
    }
    @Override
    protected IMapper<BillAccountInfo> getBaseMapper() {
        return billAccountInfoMapper;
    }

    @Override
    public BillAccountModel addBillAccount(BillAccountModel billAccountModel) {

        if(edocHeaderService.checkEdocIsExist(billAccountModel.getEdocNo(),billAccountModel.getEdocType())){
            throw new BusinessException("单证编号已经存在");
        }

        // 增加edocHeader
        EdocHeader edocHeader = DozerUtil.map(billAccountModel, EdocHeader.class);
        edocHeaderService.add(edocHeader);
        BillAccountInfo billAccountInfo = DozerUtil.map(billAccountModel, BillAccountInfo.class);
        if (add(billAccountInfo) == 1) {
            billAccountInfo = (BillAccountInfo)queryById(billAccountInfo.getId());
            billAccountModel = DozerUtil.map(billAccountInfo,BillAccountModel.class);
            billAccountModel.setEdocHeaderId(edocHeader.getId());
            return billAccountModel;
        } else {
            return null;
        }

    }

    @Override
    public Integer editBillAccount(BillAccountModel billAccountModel) {

        EdocHeader edocHeaderOld = edocHeaderService.queryById(billAccountModel.getEdocHeaderId());
        if (!(edocHeaderOld.getEdocNo().equals(billAccountModel.getEdocNo()) && edocHeaderOld.getEdocType().equals(billAccountModel.getEdocType()))
                && edocHeaderService.checkEdocIsExist(billAccountModel.getEdocNo(),billAccountModel.getEdocType())){
            throw new BusinessException("单证编号已经存在");
        }
        // 更新edocHeader
        EdocHeader edocHeader = DozerUtil.map(billAccountModel, EdocHeader.class);
        edocHeader.setId(billAccountModel.getEdocHeaderId());
        edocHeader.setVersion(billAccountModel.gethVersion());
        edocHeaderService.updateByIdSelective(edocHeader);
        //更新account
        BillAccountInfo billAccountInfo = DozerUtil.map(billAccountModel, BillAccountInfo.class);

        return updateByIdSelective(billAccountInfo);

    }

    @Override
    public void delBilLAccount(BillAccountModel billAccountModel) {
        BillAccountInfo billAccountInfo = (BillAccountInfo) queryOne(DozerUtil.map(billAccountModel, BillAccountInfo.class));
        if (!BeanUtil.isEmpty(billAccountInfo)) {
            delCascadeEdocHeaderAndEdocImageAndInvoice(billAccountInfo);
            // remove(billAccountInfo);
        }
    }

    @Override
    public BillAccountInfo queryBillAccountByBusinessKey(String businessKey) {
        if(StringUtils.isEmpty(businessKey)){
            return null;
        }
        EdocHeader edocHeader = edocHeaderService.queryEdocHeaderByBusinessKey(businessKey);
        if(BeanUtil.isEmpty(edocHeader)){
            return null;
        }
        BillAccountInfo billAccountInfo = new BillAccountInfo();
        billAccountInfo.setEdocNo(edocHeader.getEdocNo());
        return  (BillAccountInfo) queryOne(billAccountInfo);
    }

    @Override
    public Page<BillAccountModel> queryBillAccountPageList(BillAccountModel billAccountModel) {
        return billAccountInfoMapper.selectBillAccountPageList(billAccountModel);
    }

    @Override
    public List<BillAccountModel> queryByIds(List<Long> ids){
        if (BeanUtil.isEmpty(ids)) {
            throw new OptimisticLockException("id集合不能为空，查询失败。");
        }
        List<BillAccountModel> list = super.queryById(ids);

        return list;
    }


}
