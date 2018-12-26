package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.mapper.AccountingSectionMapper;
import com.zynsun.platform.edoc.model.AccountingSectionModel;
import com.zynsun.platform.edoc.service.AccountingSectionItemService;
import com.zynsun.platform.edoc.service.AccountingSectionService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 12:44
 * @Modified By:
 */
@Service
public class AccountingSectionServiceImpl extends BaseServiceImpl<AccountingSection> implements AccountingSectionService {

    @Autowired
    private AccountingSectionMapper accountingSectionMapper;
    @Autowired
    private AccountingSectionItemService accountingSectionItemService;

    @Override
    protected IMapper<AccountingSection> getBaseMapper() {
        return accountingSectionMapper;
    }

    @Override
    public String findOrCreateAccountingSection(AccountingSection accountingSection, List<Long> ids) {
        if(accountingSection.getRecordsNo()==null||"".equals(accountingSection.getRecordsNo())){
            accountingSection.setRecordsNo(findBookNo(accountingSection));
        }
        AccountingSection selectResult = this.findAccountingSectionByRecordsNo(accountingSection.getRecordsNo());
        if(selectResult==null){
            saveAccountingSection(accountingSection);
            selectResult = findAccountingSectionByRecordsNo(accountingSection.getRecordsNo());
        }else{
            //增加影像数
            int oldNum = selectResult.getAttachmentSum() == null ? 0 : selectResult.getAttachmentSum();
            selectResult.setAttachmentSum(oldNum + ids.size());
            if ("1".equals(accountingSection.getEdocImageExists()))
                selectResult.setEdocImageExists("1");
            this.updateById(selectResult);
        }
        return accountingSectionItemService.saveAccountingSectionItems(ids, selectResult);
    }

    @Override
    public Page<AccountingSectionModel> findAccountingSections(AccountingSectionModel accountingSectionModel) {
        return accountingSectionMapper.findAccountingSections(accountingSectionModel);
    }

    @Override
    public String findBookNo(AccountingSection accountingSection) {
        Map<String,Object> map = new HashMap<>();
        map.put("companyCode",accountingSection.getOfficeId());
        map.put("startDate",accountingSection.getStartDate().replace("-",""));
        map.put("endDate",accountingSection.getEndDate().replace("-",""));

        //默认bookType = "AP"
        map.put("bookType","AP");

        if(Constant.BizTypeCode.ACCOUNT_TYPE_CODE.equals(accountingSection.getBizType())||
                Constant.BizTypeCode.BANK_TICKET_TYPE_CODE.equals(accountingSection.getBizType())){
            map.put("bookType","AP");
        /*}else if(Constant.BizTypeCode.MANUAL_TYPE_CODE.equals(accountingSection.getBizType())){
            map.put("bookType","VOU");*/
        }else if(Constant.BizTypeCode.AP_TYPE_CODE.equals(accountingSection.getBizType())){
            map.put("bookType","INV");
        }
        map.put("book_code","");
        map.put("dataType","BOOK");
        String recordNo = accountingSectionMapper.findBookNo(map);
        return recordNo;
    }

    @Override
    public AccountingSection findAccountingSectionByRecordsNo(String recordsNo) {
        AccountingSection record = new AccountingSection();
        record.setRecordsNo(recordsNo);
        record.setDeleted(Constant.DeleteFlag.DEL_NO);
        record.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
        AccountingSection accountingSection = accountingSectionMapper.selectOne(record);
        if(accountingSection!=null){
            accountingSection.setVolumes(findVolumesByBizType(accountingSection.getBizType(),accountingSection.getStartDate(),accountingSection.getEndDate(),accountingSection.getOfficeId()));
        }
        return accountingSection;
    }

    @Override
    public Integer findVolumesByBizType(String bizType, String startDate, String endDate, String companyCode) {
        AccountingSection accountingSection = new AccountingSection();
        accountingSection.setBizType(bizType);
        accountingSection.setDeleted(Constant.DeleteFlag.DEL_NO);
        accountingSection.setStartDate(startDate);
        accountingSection.setEndDate(endDate);
        accountingSection.setOfficeId(companyCode);
        accountingSection.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
        return queryCount(accountingSection);
    }

    @Override
    public Integer saveAccountingSection(AccountingSection accountingSection) {
        accountingSection.setFileFlag("0");
        accountingSection.setCreateTime(new Date());
        accountingSection.setCreateBy(SubjectUtil.getUser().getLoginName());
        return accountingSectionMapper.insert(accountingSection);
    }

    @Override
    public List<AccountingSection> findAccountingSectionsByIds(List<Long> ids) {
        List<AccountingSection> list = new ArrayList<>();
        for(Long id:ids){
            AccountingSection accountingSection = new AccountingSection();
            accountingSection.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
            accountingSection.setDeleted(0);
            accountingSection.setId(id);
            AccountingSection accountingSection1 = accountingSectionMapper.selectOne(accountingSection);
            accountingSection1.setVolumes(this.findVolumesByBizType(accountingSection1.getBizType(),accountingSection1.getStartDate(),accountingSection1.getEndDate(),accountingSection1.getOfficeId()));
            list.add(accountingSection1);
        }
        return list;
    }

    @Override
    public String findBoxNo(AccountingSection accountingSection) {
        Map<String,Object> map = new HashMap<>();
        map.put("companyCode",accountingSection.getOfficeId());
        map.put("startDate",accountingSection.getStartDate().replace("-",""));
        map.put("endDate",accountingSection.getEndDate().replace("-",""));
        if(Constant.BizTypeCode.ACCOUNT_TYPE_CODE.equals(accountingSection.getBizType())||
                Constant.BizTypeCode.BANK_TICKET_TYPE_CODE.equals(accountingSection.getBizType())){
            map.put("bookType","AP");
        /*}else if(Constant.BizTypeCode.MANUAL_TYPE_CODE.equals(accountingSection.getBizType())){
            map.put("bookType","VOU");*/
        }else if(Constant.BizTypeCode.AP_TYPE_CODE.equals(accountingSection.getBizType())){
            map.put("bookType","INV");
        }
        map.put("book_code","");
        map.put("dataType","BOX");
        String recordNo = accountingSectionMapper.findBookNo(map);;
        return recordNo;
    }

    @Override
    public List<AccountingSectionModel> findSectionsBoxId(Long id){
        return accountingSectionMapper.findSectionsByBoxId(id);
    }
}
