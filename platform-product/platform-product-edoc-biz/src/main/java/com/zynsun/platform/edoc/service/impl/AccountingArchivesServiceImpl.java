package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.mapper.AccountingArchivesMapper;
import com.zynsun.platform.edoc.service.*;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 下午 2:11
 * @Modified By:
 */
@Service
public class AccountingArchivesServiceImpl extends BaseServiceImpl<AccountingArchives> implements AccountingArchivesService {

    @Autowired
    private AccountingArchivesMapper accountingArchivesMapper;
    @Autowired
    private AccountingBoxService accountingBoxService;
    @Autowired
    private AccountingSectionService accountingSectionService;
    @Autowired
    private AccountingBoxItemService accountingBoxItemService;
    @Autowired
    private AccountingSectionItemService accountingSectionItemService;
    @Autowired
    private EdocHeaderService edocHeaderService;
    @Autowired
    private  EdocCredentialsService edocCredentialsService;

    @Override
    protected IMapper<AccountingArchives> getBaseMapper() {
        return accountingArchivesMapper;
    }

    @Override
    public String archive(List<Long> ids, AccountingArchives accountingArchivesDTO) {
        String message = boxNoIsExist(ids);
        if (!"".equals(message)) {
            return message;
        }
        for (Long id : ids) {
            AccountingArchives accountingArchives = DozerUtil.map(accountingArchivesDTO, AccountingArchives.class);
            AccountingBox box = accountingBoxService.queryById(id);
            box.setBoxFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_YES);
            accountingArchives.setRecordsBoxNo(box.getBoxNo());
            List<String> sectionIds = accountingBoxItemService.findSectionIdByAccountingBoxId(id);
            if (sectionIds != null && !sectionIds.isEmpty()) {
                for (String sectionId : sectionIds) {
                    AccountingSection accountingSection = accountingSectionService.queryById(Long.parseLong(sectionId));
                    accountingSection.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_YES);
                    accountingSectionService.updateById(accountingSection);
                }

                List<Long> edocHeaderIds = accountingSectionItemService.findBillHeaderIdsBySectionIds(sectionIds);
                if (edocHeaderIds != null && !edocHeaderIds.isEmpty()) {
                    for (Long edocHeaderId : edocHeaderIds) {
                        EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderId);
                        edocHeader.setEdocTaskStatus(Constant.BizStatus.ARCHIVED);
                        edocHeaderService.updateByIdSelective(edocHeader);
                        List<EdocCredentials> edocCredentialsList=edocCredentialsService.queryByEdocId(edocHeaderId);
                        if(!BeanUtil.isEmpty(edocCredentialsList)){
                            for(EdocCredentials edocCredentials:edocCredentialsList){
                                edocCredentials.setVoucherStatus("7");
                                edocCredentialsService.updateById(edocCredentials);
                            }
                        }
                    }
                } else {
                    return "没有对应的业务流水号";
                }
                accountingBoxService.updateById(box);
                saveAccountingArchives(accountingArchives);
            } else {
                return "没有对应的分册记录";
            }
        }
        return null;
    }

    @Override
    public String boxNoIsExist(List<Long> ids) {
        String message = "";
        for (Long id:ids){
            AccountingBox box = accountingBoxService.queryById(id);
            AccountingArchives accountingArchives = new AccountingArchives();
            accountingArchives.setRecordsBoxNo(box.getBoxNo());
            if(findAccountingArchivesByFileNo(accountingArchives)!=null){
                StringBuffer sb = new StringBuffer(message);
                if("".equals(message)){
                    sb.append(box.getBoxNo());
                    message = sb.toString();
                }else{
                    sb.append(","+box.getBoxNo());
                    message = sb.toString();
                }
            }
        }
        if(!"".equals(message)){
            StringBuffer sb = new StringBuffer();
            sb.append("盒号为"+message+"的封盒已归档");
            message = sb.toString();
        }
        return message;
    }

    @Override
    public AccountingArchives findAccountingArchivesByFileNo(AccountingArchives accountingArchives) {
        accountingArchives.setDeleted(Constant.DeleteFlag.DEL_NO);
        return accountingArchivesMapper.selectOne(accountingArchives);
    }

    @Override
    public Integer saveAccountingArchives(AccountingArchives accountingArchives) {
        accountingArchives.setCreateTime(new Date());
        accountingArchives.setCreateBy(SubjectUtil.getUser().getLoginName());
        accountingArchives.setStatus("1");
        return accountingArchivesMapper.insert(accountingArchives);
    }


}
