package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.platform.edoc.domain.AccountingBox;
import com.zynsun.platform.edoc.domain.AccountingBoxItem;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.mapper.AccountingBoxItemMapper;
import com.zynsun.platform.edoc.service.AccountingBoxItemService;
import com.zynsun.platform.edoc.service.AccountingSectionService;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 3:13
 * @Modified By:
 */
@Service
public class AccountingBoxItemServiceImpl extends BaseServiceImpl<AccountingBoxItem> implements AccountingBoxItemService {

    @Autowired
    private AccountingBoxItemMapper accountingBoxItemMapper;

    @Autowired
    private AccountingSectionService accountingSectionService;

    @Autowired
    private EdocHeaderService edocHeaderService;

    @Override
    protected IMapper<AccountingBoxItem> getBaseMapper() {
        return accountingBoxItemMapper;
    }

    @Override
    public String saveSectionBoxItems(List<Long> sections, AccountingBox accountingBox) {
        String message = "";
        for (Long sectionId : sections) {
            AccountingBoxItem accountingBoxItem = new AccountingBoxItem();
            accountingBoxItem.setAccountingSection(sectionId.toString());
            accountingBoxItem.setDeleted(Constant.DeleteFlag.DEL_NO);
            AccountingBoxItem boxItems = this.queryOne(accountingBoxItem);
            if (boxItems == null) {
                AccountingBoxItem newAccountingBoxItem = new AccountingBoxItem();
                newAccountingBoxItem.setAccountingBox(accountingBox.getId().toString());
                newAccountingBoxItem.setCreateTime(new Date());
                newAccountingBoxItem.setCreateBy(SubjectUtil.getUser().getLoginName());
                newAccountingBoxItem.setAccountingSection(sectionId.toString());
                accountingBoxItemMapper.insert(newAccountingBoxItem);

                AccountingSection accountingSection = accountingSectionService.queryById(sectionId);
                accountingSection.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_YES);
                accountingSectionService.updateByIdSelective(accountingSection);
            } else {
                StringBuffer sb = new StringBuffer(message);
                if (sb.length() == 0) {
                    sb.append(accountingSectionService.queryById(sectionId).getRecordsNo());
                } else {
                    sb.append("," + accountingSectionService.queryById(sectionId).getRecordsNo());
                }
                message = sb.toString();
            }
        }
        List<EdocHeader> edocHeaders=edocHeaderService.findEditSectionByIds(sections);
        for (EdocHeader edocHeader:edocHeaders){
            edocHeader.setEdocTaskStatus("27");
        }
        StringBuffer sb = new StringBuffer(message);
        if ("".equals(message)) {
            sb.append("分盒成功");
        } else {
            sb.append(message + "已分盒");
        }
        return sb.toString();
    }

    @Override
    public List<String> findSectionIdByAccountingBoxId(Long id) {
        return accountingBoxItemMapper.findSectionIdByAccountingBoxId(id);
    }

    @Override
    public void deleteItemsByBoxId(Long boxId) {
        AccountingBoxItem record = new AccountingBoxItem();
        record.setAccountingBox(boxId.toString().trim());
        record.setDeleted(Constant.DeleteFlag.DEL_NO);
        List<AccountingBoxItem> items = accountingBoxItemMapper.select(record);
        if (items != null && !items.isEmpty()) {
            for (AccountingBoxItem item : items) {
                deleteByIdLogic(item.getId());
            }
        }
    }

    @Override
    public Integer deleteSectionItems(String sectionId, String boxId) {
        AccountingBoxItem accountingBoxItem = new AccountingBoxItem();
        accountingBoxItem.setAccountingSection(sectionId);
        accountingBoxItem.setAccountingBox(boxId);
        accountingBoxItem.setDeleted(Constant.DeleteFlag.DEL_NO);
        accountingBoxItem = queryOne(accountingBoxItem);
        if (accountingBoxItem != null) {
            deleteByIdLogic(accountingBoxItem.getId());
        }
        Integer result = null;
        AccountingSection accountingSection = accountingSectionService.queryById(Long.parseLong(sectionId));
        if (accountingSection != null) {
            accountingSection.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
            result = accountingSectionService.updateByIdSelective(accountingSection);
        }
        return result;
    }
}
