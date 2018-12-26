package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.alibaba.fastjson.JSONObject;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DateUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.AccountingArchives;
import com.zynsun.platform.edoc.domain.AccountingArchivesBorrow;
import com.zynsun.platform.edoc.domain.AccountingSection;
import com.zynsun.platform.edoc.domain.AccountingSectionItem;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesBorrowDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.AccountingArchivesBorrowFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.service.AccountingArchivesBorrowService;
import com.zynsun.platform.edoc.service.AccountingArchivesService;
import com.zynsun.platform.edoc.service.AccountingSectionItemService;
import com.zynsun.platform.edoc.service.AccountingSectionService;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 下午 1:12
 * @Modified By:
 */

@Service(value="accountingArchivesBorrowFacade")
public class AccountingArchivesBorrowFacadeImpl implements AccountingArchivesBorrowFacade {

    private static final Logger ACCOUNTINGBOX = LoggerFactory.getLogger(AccountingArchivesBorrowFacadeImpl.class);

//    @Autowired
//    private WfProcessFacade wProcessFacade;
//
//    @Autowired
//    private WfTaskFacade wfTaskFacade;

    @Autowired
    private EdocHeaderFacade edocHeaderFacade;

    @Autowired
    private AccountingArchivesBorrowService accountingArchivesBorrowService;

    @Autowired
    private AccountingArchivesService accountingArchivesService;

    @Autowired
    private AccountingSectionService accountingSectionService;

    @Autowired
    private AccountingSectionItemService accountingSectionItemService;

    @Override
    public ExecuteResult<PageInfo<AccountingArchivesBorrowDTO>> queryList(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        ExecuteResult<PageInfo<AccountingArchivesBorrowDTO>> result = new ExecuteResult<>();
        try{
            if(BeanUtil.isEmpty(accountingArchivesBorrowDTO)){
                result.addErrorMessage("查询条件不能为空");
                return result;
            }
            //DozerUtil.map(accountingArchivesBorrowDTO,AccountingArchivesBorrow.class)
            PageInfo<AccountingArchivesBorrowDTO> pageInfoExecuteResult = accountingArchivesBorrowService.queryList(accountingArchivesBorrowDTO);
            result.setResult(pageInfoExecuteResult);
            result.setSuccessMessage("查询成功");
        }catch (Exception e){
            ACCOUNTINGBOX.error("查询失败", e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> insertArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try{
            accountingArchivesBorrowService.insertArch(accountingArchivesBorrowDTO);
            result.setSuccessMessage("新增成功");
        }catch (Exception e){
            ACCOUNTINGBOX.error("新增成功", e);
            result.addErrorMessage("新增成功");
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> updateArch(AccountingArchivesBorrowDTO accountingArchivesBorrowDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try{
            accountingArchivesBorrowService.updateArch(accountingArchivesBorrowDTO);
            result.setSuccessMessage("更新成功");
        }catch (Exception e){
            ACCOUNTINGBOX.error("更新成功", e);
            result.addErrorMessage("更新成功");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> applyForBorrow(Long edocId , Long archivesId ,String createBy ,String createTime, String saveBackTime) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try{
            ExecuteResult<EdocHeaderDTO> edocHeaderDTOExecuteResult = edocHeaderFacade.selectEdocHeaderById(edocId);
            AccountingArchivesBorrowDTO accountingArchivesBorrowDTO = new AccountingArchivesBorrowDTO();
            accountingArchivesBorrowDTO.setEdocHeadId(edocId);
            accountingArchivesBorrowDTO.setEdocNo(edocHeaderDTOExecuteResult.getResult().getEdocNo());
            accountingArchivesBorrowDTO.setAccountingArchivesId(archivesId);
            accountingArchivesBorrowDTO.setCreateBy(createBy);
            accountingArchivesBorrowDTO.setIsSaveBack("2");
            accountingArchivesBorrowDTO.setCreateTime(DateUtil.stringToDate(createTime,"yyyy-MM-dd"));
            accountingArchivesBorrowDTO.setSaveBackTime(DateUtil.stringToDate(saveBackTime,"yyyy-MM-dd"));
            accountingArchivesBorrowDTO.setStatus("待审批");
            accountingArchivesBorrowService.insertArch(accountingArchivesBorrowDTO);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result","success");
//            wProcessFacade.startProcess("edocRead" , edocId+"");
            //将档案得状态变为已借出
            AccountingArchives accountingArchives=accountingArchivesService.queryById(archivesId);
            accountingArchives.setStatus("2");
            accountingArchivesService.updateById(accountingArchives);
            result.setSuccessMessage("申请调阅成功");
            result.setResult("申请调阅成功");

        }catch (Exception e){
            ACCOUNTINGBOX.error("新增成功", e);
            result.addErrorMessage("申请调阅失败");
        }
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ExecuteResult<String> applyForBorrowBySection(Long sectionId,String createBy ,String createTime, String saveBackTime) {
        ExecuteResult<String> result = new ExecuteResult<>();

        AccountingSection accountingSection = accountingSectionService.queryById(sectionId);
        if (accountingSection == null){
            result.addErrorMessage("未找到该分册信息");
            return result;
        }

        EdocHeaderDTO edocHeaderDTO=new EdocHeaderDTO();
        edocHeaderDTO.setSectionId(sectionId);
        ExecuteResult<PageInfo<EdocHeaderDTO>> headers = edocHeaderFacade.queryArchivedAccount(edocHeaderDTO);

        if (headers!=null && headers.getResult()!=null && headers.getResult().getList()!=null){
            AccountingArchives accountingArchives = new AccountingArchives();
            accountingArchives.setDeleted(Constant.DeleteFlag.DEL_NO);
            Date date = new Date();
            for (EdocHeaderDTO header : headers.getResult().getList()) {

                AccountingArchivesBorrowDTO accountingArchivesBorrowDTO = new AccountingArchivesBorrowDTO();
                accountingArchivesBorrowDTO.setEdocHeadId(header.getId());
                accountingArchivesBorrowDTO.setEdocNo(header.getEdocNo());
                accountingArchivesBorrowDTO.setCreateTime(date);
                accountingArchivesBorrowDTO.setCreateBy(createBy);
                accountingArchivesBorrowDTO.setIsSaveBack("2");
                accountingArchivesBorrowDTO.setCreateTime(DateUtil.stringToDate(createTime,"yyyy-MM-dd"));
                accountingArchivesBorrowDTO.setSaveBackTime(DateUtil.stringToDate(saveBackTime,"yyyy-MM-dd"));
                accountingArchivesBorrowDTO.setStatus("待审批");
                accountingArchivesBorrowDTO.setAccountingArchivesId(header.getArchivesId());
                accountingArchivesBorrowDTO.setSectionId(sectionId);
                accountingArchivesBorrowDTO.setSectionNo(accountingSection.getRecordsNo());
                accountingArchivesBorrowService.insertArch(accountingArchivesBorrowDTO);

//                wProcessFacade.startProcess("edocRead" , header.getId()+"");

                //将档案得状态变为已借出
                accountingArchives=accountingArchivesService.queryById(header.getArchivesId());
                accountingArchives.setStatus("2");
                accountingArchivesService.updateById(accountingArchives);
            }

            accountingArchivesBorrowService.updateCreateTime(sectionId, date);
        }else{
            result.addErrorMessage("该分册下未找到凭证记录");
            return result;
        }

        result.setSuccessMessage("SUCCESS");
        result.setResult("申请调阅成功");
        return result;
    }

    @Override
    public ExecuteResult<Boolean> finishExamineWorkTask(String taskId, String param , Long edocId ) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        try{
            AccountingArchivesBorrowDTO queryArch = new AccountingArchivesBorrowDTO();
            queryArch.setCreateBy(SubjectUtil.getUser().getLoginName());
            queryArch.setEdocHeadId(edocId);
            List<AccountingArchivesBorrowDTO> lists = accountingArchivesBorrowService.queryArchModelList(queryArch);
            if(lists != null && !lists.isEmpty()){
                // 先循环全部删除
                if(lists.size() > 1){
                    for(int i = 0 , y = lists.size() ; i < y ; i++){
                        // 排除第一个 因为已经倒叙了
                        if(i == 0 ){
                            continue;
                        }
                        lists.get(i).setDeleted(1);
                        accountingArchivesBorrowService.updateArch(lists.get(i));
                    }
                }
                AccountingArchivesBorrowDTO accountingArchivesBorrowDTO = lists.get(0);
                accountingArchivesBorrowDTO.setDeleted(0);
                if("0".equals(param)){
                    accountingArchivesBorrowDTO.setStatus("同意");
                    accountingArchivesBorrowDTO.setIsSaveBack("0");
                }else{
                    accountingArchivesBorrowDTO.setStatus("拒绝");
                }
                accountingArchivesBorrowService.updateArch(accountingArchivesBorrowDTO);
            }
            result.setSuccessMessage("操作成功");
            result.setResult(true);
            Map<String,Object> variables = new HashMap<>();
            variables.put("msg",param); // 流程变量 0 同意 1拒绝
//            wfTaskFacade.completeTask(null, taskId, null, variables);
        }catch (Exception e){
            ACCOUNTINGBOX.error("操作失败", e);
            result.addErrorMessage("操作失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<String> archiveSaveBack(Long id) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            AccountingArchivesBorrow accountingArchivesBorrow = accountingArchivesBorrowService.queryById(id);
            if (accountingArchivesBorrow.getIsSaveBack().equals("1")) {
                result.addErrorMessage("没有借出的档案不允许归还");
                return result;
            }
            accountingArchivesBorrow.setIsSaveBack("1");
            accountingArchivesBorrow.setDeleted(1);
            accountingArchivesBorrow.setSaveBackTime(new Date());
            accountingArchivesBorrowService.updateByIdSelective(accountingArchivesBorrow);
            AccountingArchives accountingArchives=accountingArchivesService.queryById(accountingArchivesBorrow.getAccountingArchivesId());
            accountingArchives.setStatus("1");
            accountingArchivesService.updateById(accountingArchives);
            result.setSuccessMessage("归还成功");
        }catch (Exception e) {
            ACCOUNTINGBOX.error("归还异常{}", e);
            result.addErrorMessage("归还异常");
        }
        return result;
    }

    @Transactional
    @Override
    public ExecuteResult<String> archiveSaveBackBySection(Long sectionId) {
        ExecuteResult<String> result = new ExecuteResult<>();

        EdocHeaderDTO edocHeaderDTO=new EdocHeaderDTO();
        edocHeaderDTO.setSectionId(sectionId);
        ExecuteResult<PageInfo<EdocHeaderDTO>> headers = edocHeaderFacade.queryArchivedAccount(edocHeaderDTO);

        if (headers!=null && headers.getResult()!=null && headers.getResult().getList()!=null){
            AccountingArchivesBorrow accountingArchivesBorrow = new AccountingArchivesBorrow();
            Date date = new Date();
            for (EdocHeaderDTO header : headers.getResult().getList()) {
                accountingArchivesBorrow.setEdocHeadId(header.getId());
                List<AccountingArchivesBorrow> borrows = accountingArchivesBorrowService.query(accountingArchivesBorrow);
                if (borrows!=null && borrows.size()>0){
                    for (AccountingArchivesBorrow b : borrows) {
                        if (b.getIsSaveBack().equals("1")) {
                            result.addErrorMessage("没有借出的档案不允许归还【"+b.getEdocNo()+"】");
                            return result;
                        }
                        b.setIsSaveBack("1");
                        b.setDeleted(1);
                        b.setSaveBackTime(date);
                        accountingArchivesBorrowService.updateByIdSelective(b);
                        AccountingArchives accountingArchives=accountingArchivesService.queryById(b.getAccountingArchivesId());
                        accountingArchives.setStatus("1");
                        accountingArchivesService.updateById(accountingArchives);
                        result.setSuccessMessage("归还成功");
                    }
                }
            }
            accountingArchivesBorrowService.updateSaveBackTime(sectionId, date);
        }else{
            result.addErrorMessage("该分册下未找到凭证记录");
            return result;
        }

        result.setSuccessMessage("SUCCESS");
        result.setResult("归还成功");
        return result;
    }
}
