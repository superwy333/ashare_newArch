package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.platform.edoc.domain.AccountingBox;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.mapper.AccountingBoxMapper;
import com.zynsun.platform.edoc.model.AccountingBoxModel;
import com.zynsun.platform.edoc.service.*;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 3:09
 * @Modified By:
 */
@Service
public class AccountingBoxServiceImpl extends BaseServiceImpl<AccountingBox> implements AccountingBoxService {

    @Autowired
    private AccountingBoxMapper accountingBoxMapper;
    @Autowired
    private AccountingBoxItemService accountingBoxItemService;
    @Autowired
    private AccountingSectionService accountingSectionService;
    @Autowired
    private EdocCredentialsService edocCredentialsService;
    @Autowired
    private EdocHeaderService edocHeaderService;

    @Override
    protected IMapper<AccountingBox> getBaseMapper() {
        return accountingBoxMapper;
    }

    @Override
    public String sectionBoxCreateOrFindBox(AccountingBox accountingBox, List<Long> ids) {
        AccountingBox record = new AccountingBox();
        record.setBoxNo(accountingBox.getBoxNo());
        record.setDeleted(Constant.DeleteFlag.DEL_NO);
        record.setBoxFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
        AccountingBox selectResult = queryOne(record);
        if (null == selectResult){
            this.saveAccountingBox(accountingBox);
            selectResult = this.findAccountingBoxByBoxNo(accountingBox.getBoxNo());
        }
        String message = accountingBoxItemService.saveSectionBoxItems(ids, accountingBox);
        if (message != null && !"".equals(message)) {
            for (Long id : ids) {
                AccountingSection accountingSection = accountingSectionService.queryById(id);
                accountingSection.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_YES);
                accountingSectionService.updateByIdSelective(accountingSection);
            }
            for(Long id :ids){
                List<EdocHeader> edocHeaders=edocHeaderService.findBillHeaderBySectionId(id);
                for(EdocHeader edocHeader:edocHeaders){
                    List<EdocCredentials> edocCredentialsList=edocCredentialsService.queryByEdocId(edocHeader.getId());
                    if(!BeanUtil.isEmpty(edocCredentialsList)){
                        for(EdocCredentials edocCredentials:edocCredentialsList){
                            edocCredentials.setVoucherStatus("6");
                            edocCredentialsService.updateById(edocCredentials);
                        }
                }
                    edocHeader.setEdocTaskStatus(Constant.BizStatus.TOBEARCHIVED );
                    edocHeaderService.updateByIdSelective(edocHeader);
            }
            }
        }
        return message;
    }

    @Override
    public Integer saveAccountingBox(AccountingBox box) {
        box.setCreateBy(SubjectUtil.getUser().getLoginName());
        box.setCreateTime(new Date());
        return accountingBoxMapper.insert(box);
    }

    @Override
    public AccountingBox findAccountingBoxByBoxNo(String boxNo) {
        AccountingBox box = new AccountingBox();
        box.setBoxNo(boxNo);
        box.setBoxFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
        return accountingBoxMapper.selectOne(box);
    }

    @Override
    public Page<AccountingBoxModel> findSectionBoxs(AccountingBoxModel model) {
        return accountingBoxMapper.findSectionBoxs(model);
    }

    @Override
    public Integer findVolumes(String bizType, String startDate, String endDate, String companyCode) {
        AccountingBox accountingBox = new AccountingBox();
        accountingBox.setCompanyCode(companyCode);
        accountingBox.setBoxStatus(Constant.AccountingBoxStatus.SECTION);
        accountingBox.setBoxFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
        accountingBox.setDeleted(Constant.DeleteFlag.DEL_NO);
        accountingBox.setStartDate(startDate);
        accountingBox.setEndDate(endDate);
        accountingBox.setBizType(bizType);
        return queryCount(accountingBox);
    }

    @Override
    public AccountingBoxModel findContractByBoxNo(String boxNo,String boxStatus) {
        return accountingBoxMapper.findAccountingBoxByBoxNo(boxNo,boxStatus);
    }

    @Override
    public AccountingBoxModel findContractByBoxNoAlready(String boxNo) {
        return accountingBoxMapper.findAccountingBoxByBoxNoAlreadyArchive(boxNo);
    }
}
