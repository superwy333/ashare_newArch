package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.domain.AccountingSectionItem;
import com.zynsun.platform.edoc.domain.EdocCredentials;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.mapper.AccountingSectionItemMapper;
import com.zynsun.platform.edoc.service.AccountingSectionItemService;
import com.zynsun.platform.edoc.service.AccountingSectionService;
import com.zynsun.platform.edoc.service.EdocCredentialsService;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 1:00
 * @Modified By:
 */
@Service

public class AccountingSectionItemServiceImpl extends BaseServiceImpl<AccountingSectionItem> implements AccountingSectionItemService {

    @Autowired
    private AccountingSectionItemMapper accountingSectionItemMapper;
    @Autowired
    private EdocHeaderService edocHeaderService;
    @Autowired
    private AccountingSectionService accountingSectionService;

    @Autowired
    private EdocCredentialsService edocCredentialsService;


    @Override
    protected IMapper<AccountingSectionItem> getBaseMapper() {
        return accountingSectionItemMapper;
    }

    @Override
    public String saveAccountingSectionItems(List<Long> ids, AccountingSection accountingSection) {
        String message = "";
        List<EdocHeader> headers = edocHeaderService.queryById(ids);
        for(EdocHeader header:headers){
            AccountingSectionItem  item = new AccountingSectionItem();
            item.setEdocId(header.getId().toString());
            item.setDeleted(Constant.DeleteFlag.DEL_NO);
            item = accountingSectionItemMapper.selectOne(item);
            if(item == null){
                AccountingSectionItem newItem = new AccountingSectionItem();
                newItem.setEdocId(header.getId().toString());
                newItem.setEdocNo(header.getEdocNo());
                newItem.setCreateTime(new Date());
                newItem.setCreateBy(SubjectUtil.getUser().getLoginName());
                newItem.setAccountingSection(accountingSection.getId().toString());
                accountingSectionItemMapper.insert(newItem);

                header.setEdocTaskStatus(Constant.BizStatus.TOBEARCHIVED);
                header.setLastModifyBy(SubjectUtil.getUser().getLoginName());
                header.setLastModifyTime(new Date());
               // header.setEdocArchiveStatus(4);
                List<EdocCredentials> edocCredentialsList=edocCredentialsService.queryByEdocId(header.getId());
                for(EdocCredentials edocCredentials:edocCredentialsList){
                    edocCredentials.setVoucherStatus("5");
                    edocCredentialsService.updateById(edocCredentials);
                }
                try{
                    edocHeaderService.updateByIdSelective(header);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                StringBuffer sb = new StringBuffer(message);
                if(sb.length()==0){
                    sb.append(header.getEdocNo());
                }else{
                    sb.append("," + header.getEdocNo());
                }
                message = sb.toString();
            }

        }
        StringBuffer sb = new StringBuffer(message);
        if("".equals(message)){
            sb.append("分册成功");
        }else{
            sb.append("单据编号为"+message+"已分册");
        }
        return sb.toString();
    }

    @Override
    public List<Long> findBillHeaderIdsBySectionIds(List<String> sectionIds) {
        List<Long> ids = new ArrayList<>();
        if(sectionIds!=null&&!sectionIds.isEmpty()){
            for(String sectionId:sectionIds){
                AccountingSectionItem item = new AccountingSectionItem();
                item.setAccountingSection(sectionId);
                item.setDeleted(Constant.DeleteFlag.DEL_NO);
                List<AccountingSectionItem> list = this.query(item);
                for(AccountingSectionItem accountingSectionItem:list){
                    ids.add(Long.parseLong(accountingSectionItem.getEdocId()));
                }
            }
        }
        return ids;
    }

    @Override
    public List<Long> findBillHeaderIdsBySectionId(String id) {
        List<Long> ids = new ArrayList<>();
        AccountingSectionItem item = new AccountingSectionItem();
        item.setAccountingSection(id);
        item.setDeleted(Constant.DeleteFlag.DEL_NO);
        List<AccountingSectionItem> list = this.query(item);
        for(AccountingSectionItem accountingSectionItem:list){
            ids.add(Long.parseLong(accountingSectionItem.getEdocId()));
        }
        return ids;
    }

    @Override
    public void deleteSectionItemBySectionId(Long id) {
        AccountingSectionItem record = new AccountingSectionItem();
        record.setAccountingSection(id.toString().trim());
        record.setDeleted(Constant.DeleteFlag.DEL_NO);
        List<AccountingSectionItem> items = accountingSectionItemMapper.select(record);
        if(items!=null&&!items.isEmpty()){
            items.forEach(accountingSectionItem -> {
                this.deleteByIdLogic(accountingSectionItem.getId());
            });
        }
    }

    @Override
    public String editAccountingSectionItems(List<Long> ids, AccountingSection accountingSection) {
        String message = "";
        List<EdocHeader> headers = edocHeaderService.findSectionByIds(ids);
        for(EdocHeader header:headers){
            AccountingSectionItem  item = new AccountingSectionItem();
            item.setEdocId(header.getId().toString());
            item.setDeleted(Constant.DeleteFlag.DEL_NO);
            item = accountingSectionItemMapper.selectOne(item);
            if(item == null){
                AccountingSectionItem newItem = new AccountingSectionItem();
                newItem.setEdocId(header.getId().toString());
                newItem.setEdocNo(header.getEdocNo());
                newItem.setCreateTime(new Date());
                newItem.setCreateBy(SubjectUtil.getUser().getLoginName());
                newItem.setAccountingSection(accountingSection.getId().toString());
                accountingSectionItemMapper.insert(newItem);

                accountingSection.setAttachmentSum(accountingSection.getAttachmentSum().intValue()+1);
                accountingSectionService.updateByIdSelective(accountingSection);

                header.setEdocTaskStatus(Constant.BizStatus.TOBEARCHIVED);
                header.setLastModifyBy(SubjectUtil.getUser().getLoginName());
                header.setLastModifyTime(new Date());
                try{
                    edocHeaderService.updateByIdSelective(header);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                StringBuffer sb = new StringBuffer(message);
                if(sb.length()==0){
                    sb.append(header.getEdocNo());
                }else{
                    sb.append("," + header.getEdocNo());
                }
                message = sb.toString();
            }

        }
        StringBuffer sb = new StringBuffer(message);
        if("".equals(message)){
            sb.append("分册成功");
        }else{
            sb.append("单据编号为"+message+"已分册");
        }
        return sb.toString();
    }


    @Override
    public Integer deleteItemByBillHeaderIdAndSectionId(Long billHeaderId, Long sectionId) {
        AccountingSectionItem record = new AccountingSectionItem();
        record.setDeleted(Constant.DeleteFlag.DEL_NO);
        record.setEdocId(billHeaderId.toString());
        record.setAccountingSection(sectionId.toString());
        record = queryOne(record);
        if(record.getId()!=null){
            deleteByIdLogic(record.getId());
        }

        AccountingSection accountingSection = accountingSectionService.queryById(sectionId);
        accountingSection.setAttachmentSum((accountingSection.getAttachmentSum()).intValue()-1);
        accountingSectionService.updateByIdSelective(accountingSection);
        // 凭证状态调整
        List<EdocCredentials> edocCredentials = edocCredentialsService.queryByEdocId(billHeaderId);
        if (!BeanUtil.isEmpty(edocCredentials)) {
            for (EdocCredentials edocCredential : edocCredentials) {
                edocCredential.setVoucherStatus("4");
                edocCredentialsService.updateByIdSelective(edocCredential);
            }
        }

        EdocHeader edocHeader = edocHeaderService.queryById(billHeaderId);
        edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_ARCHIVED);
        edocHeader.setLastModifyBy(SubjectUtil.getUser().getLoginName());
        edocHeader.setLastModifyTime(new Date());
        return edocHeaderService.updateByIdSelective(edocHeader);
    }







}
