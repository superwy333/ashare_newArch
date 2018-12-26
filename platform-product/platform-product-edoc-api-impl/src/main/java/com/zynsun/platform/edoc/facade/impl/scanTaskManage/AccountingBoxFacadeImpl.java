package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DateUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingBoxDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.AccountingBoxFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.model.AccountingBoxModel;
import com.zynsun.platform.edoc.service.*;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 下午 1:12
 * @Modified By:
 */

@Service(value="accountingBoxFacade")
public class AccountingBoxFacadeImpl implements AccountingBoxFacade {

    private static final Logger ACCOUNTINGBOX = LoggerFactory.getLogger(AccountingBoxFacadeImpl.class);

    @Autowired
    private AccountingBoxService accountingBoxService;

    @Autowired
    private AccountingBoxItemService accountingBoxItemService;

    @Autowired
    private AccountingSectionService accountingSectionService;

    @Autowired
    private AccountingSectionItemService accountingSectionItemService;

    @Autowired
    private EdocHeaderFacade edocHeaderFacade;
    @Autowired
    private EdocCredentialsService edocCredentialsService;



    @Override
    public ExecuteResult<PageInfo<AccountingBoxDTO>> findSectionBoxs(AccountingBoxDTO accountingBoxDTO) {
        ExecuteResult<PageInfo<AccountingBoxDTO>> result = new ExecuteResult<>();
        try {
            accountingBoxDTO.setBoxFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
            accountingBoxDTO.setBoxStatus(Constant.AccountingBoxStatus.SECTION);
            AccountingBoxModel model = DozerUtil.map(accountingBoxDTO, AccountingBoxModel.class);
            Page<AccountingBoxModel> page = accountingBoxService.findSectionBoxs(model);
            PageInfo<AccountingBoxDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),AccountingBoxDTO.class);
            List<AccountingBoxDTO> dtos = pageInfo.getList();
            for(AccountingBoxDTO accountingBoxDTO1:dtos){
                if(accountingBoxDTO1.getBizType()!=null&&!"".equals(accountingBoxDTO1.getBizType())){
                    accountingBoxDTO1.setVolumes(accountingBoxService.findVolumes(accountingBoxDTO1.getBizType(),accountingBoxDTO1.getStartDate(),accountingBoxDTO1.getEndDate(),accountingBoxDTO1.getCompanyCode()).toString());
                }
            }
            result.setResult(pageInfo);
            result.setSuccessMessage("会计分盒列表查询成功");
        }catch (Exception e){
            ACCOUNTINGBOX.error("会计分盒列表查询失败", e);
            result.addErrorMessage("会计分盒列表查询失败");
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<AccountingBoxDTO> findAccountingBoxByBoxNo(AccountingBoxDTO accountingBoxDTO) {
        ExecuteResult<AccountingBoxDTO> result = new ExecuteResult<>();
        try{
            AccountingBoxModel box = accountingBoxService.findContractByBoxNo(accountingBoxDTO.getBoxNo(),accountingBoxDTO.getBoxStatus());
            if(box!=null){
                AccountingBoxDTO dto = DozerUtil.map(box,AccountingBoxDTO.class);
                if(dto.getCreateTime()!=null){
                    dto.setTime(DateUtil.dateToString(dto.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                }
                result.setResult(dto);
                result.setSuccessMessage("查询编盒信息成功");
                return result;
            }else{
                result.setSuccessMessage("没有查到该编盒信息");
                return result;
            }
        }catch(Exception e){
            ACCOUNTINGBOX.error("查询编盒信息失败", e);
            result.addErrorMessage("查询编盒信息失败");
            return result;
        }
    }

    @Override
    public ExecuteResult<AccountingBoxDTO> findAccountingBoxByBoxNoAlreadyArchive(AccountingBoxDTO accountingBoxDTO) {
        ExecuteResult<AccountingBoxDTO> result = new ExecuteResult<>();
        try{
            AccountingBoxModel box = accountingBoxService.findContractByBoxNoAlready(accountingBoxDTO.getBoxNo());
            if(box!=null){
                AccountingBoxDTO dto = DozerUtil.map(box,AccountingBoxDTO.class);
                if(dto.getCreateTime()!=null){
                    dto.setTime(DateUtil.dateToString(dto.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                }
                result.setResult(dto);
                result.setSuccessMessage("查询编盒信息成功");
                return result;
            }else{
                result.setSuccessMessage("没有查到该编盒信息");
                return result;
            }
        }catch(Exception e){
            ACCOUNTINGBOX.error("查询编盒信息失败", e);
            result.addErrorMessage("查询编盒信息失败");
            return result;
        }
    }

    @Override
    public ExecuteResult<AccountingBoxDTO> findAccountingBoxById(Long id) {
        ExecuteResult<AccountingBoxDTO> result = new ExecuteResult<>();
        try{
            AccountingBox box = accountingBoxService.queryById(id);
            if(box!=null){
                AccountingBoxDTO dto = DozerUtil.map(box,AccountingBoxDTO.class);
                if(dto.getCreateTime()!=null){
                    dto.setTime(DateUtil.dateToString(dto.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                }
                result.setResult(dto);
                result.setSuccessMessage("查询编盒信息成功");
                return result;
            }else{
                result.setSuccessMessage("没有查到该编盒信息");
                return result;
            }
        }catch(Exception e){
            ACCOUNTINGBOX.error("查询编盒信息失败", e);
            result.addErrorMessage("查询编盒信息失败");
            return result;
        }
    }

    @Override
    public ExecuteResult<Integer> deleteSectionBox(Long id) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try{
            List<String> sectionIds = accountingBoxItemService.findSectionIdByAccountingBoxId(id);
            if(sectionIds!=null&&!sectionIds.isEmpty()){
                for(String sectionId:sectionIds){
                    AccountingSection accountingSection = accountingSectionService.queryById(Long.parseLong(sectionId));
                    accountingSection.setFileFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
                    accountingSectionService.updateByIdSelective(accountingSection);
                    ExecuteResult<List<EdocHeaderDTO>> listExecuteResult= edocHeaderFacade.findBillHeaderBySectionId(Long.parseLong(sectionId));
                    if(listExecuteResult.isSuccess()){
                        List<EdocHeaderDTO> edocHeaderDTOS=listExecuteResult.getResult();
                        if(!BeanUtil.isEmpty(edocHeaderDTOS)){
                            for(EdocHeaderDTO  edocHeaderDTO:edocHeaderDTOS){
                                List<EdocCredentials> credentialsList=edocCredentialsService.queryByEdocId(edocHeaderDTO.getId());
                                if(!BeanUtil.isEmpty(credentialsList)){
                                    for(EdocCredentials edocCredentials:credentialsList){
                                        edocCredentials.setVoucherStatus("5");
                                        edocCredentialsService.updateById(edocCredentials);
                                    }
                                }
                                edocHeaderDTO.setEdocTaskStatus(Constant.BizStatus.WAIT_BOXING);
                                edocHeaderFacade.updateEdocHeader(edocHeaderDTO);
                            }
                        }
                    }
                }
            }
            accountingBoxService.deleteByIdLogic(id);
            accountingBoxItemService.deleteItemsByBoxId(id);
            result.setResult(1);
        }catch(Exception e){
            ACCOUNTINGBOX.error("删除封盒失败", e);
            result.addErrorMessage("删除封盒失败");
            return result;
        }
        return result;
    }


    @Override
    public ExecuteResult<Integer> editBox(AccountingBoxDTO accountingBoxDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try{
            AccountingBox accountingBox = accountingBoxService.queryById(accountingBoxDTO.getId());
            if(!BeanUtil.isEmpty(accountingBox)){
                accountingBoxDTO.setLastModifyBy(SubjectUtil.getUser().getLoginName());
                accountingBoxDTO.setLastModifyTime(new Date());
                accountingBoxService.updateByIdSelective(DozerUtil.map(accountingBoxDTO,AccountingBox.class));
            }else{
                result.setSuccessMessage("没有查到对应的编盒信息");
                return result;
            }
            result.setResult(1);
            result.setSuccessMessage("修改编盒信息成功");
        }catch(javax.persistence.OptimisticLockException e){
            ACCOUNTINGBOX.error("乐观锁异常", e);
            result.addErrorMessage("单据已经被其他人修改，保存失败!");
            return result;
        }
        catch (Exception e){
            ACCOUNTINGBOX.error("修改编盒信息失败", e);
            result.addErrorMessage("修改编盒信息失败");
            return result;
        }
        return result;
    }


    @Override
    public ExecuteResult<Integer> deleteSectionBoxItem(String sectionId, String boxId) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try{
            Integer deleteResult = accountingBoxItemService.deleteSectionItems(sectionId,boxId);
            if(deleteResult==1){
                result.setResult(deleteResult);
                return result;
            }else{
                result.setSuccessMessage("删除编盒数据失败");
            }
        }catch (Exception e){
            result.addErrorMessage("删除编盒数据失败");
            ACCOUNTINGBOX.error("删除编盒数据失败", e);
            return result;
        }
        return result;
    }

    @Transactional
    @Override
    public ExecuteResult<String> cancelArchive(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            //EdocHeaderDTO edocHeaderDTOInDb = edocHeaderFacade.findValidEdocHeader(edocHeaderDTO).getResult();
            AccountingSectionItem accountingSectionItem = new AccountingSectionItem();
            accountingSectionItem.setEdocNo(edocHeaderDTO.getEdocNo());
            List<AccountingSectionItem> accountingSectionItemList = accountingSectionItemService.query(accountingSectionItem);
            if (BeanUtil.isEmpty(accountingSectionItemList)) {
                result.addErrorMessage("不存单号为"+ edocHeaderDTO.getEdocNo()+"的归档单据");
                return result;
            }
            //已经完成归档的，则直接逻辑删除section_item的数据
            AccountingSectionItem accountingSectionItemInDB = accountingSectionItemList.get(0);
            accountingSectionItemService.remove(accountingSectionItemInDB);
            //修改edoc状态为凭证冲销
            EdocHeaderDTO edocHeaderDTOInDB = edocHeaderFacade.findValidEdocHeader(edocHeaderDTO).getResult();
            if (!BeanUtil.isEmpty(edocHeaderDTOInDB)) {
                edocHeaderDTOInDB.setEdocTaskStatus(Constant.BizStatus.WRITE_OFF);
                edocHeaderDTOInDB.setEdocVoucherStatus(Constant.FsscEdocStatus.UN_HANDLE);
                edocHeaderDTOInDB.setEdocCertStatus(Constant.FsscEdocStatus.UN_HANDLE);
                edocHeaderDTOInDB.setEdocArchiveStatus(Constant.FsscEdocStatus.UN_HANDLE);
                edocHeaderFacade.updateEdocHeader(edocHeaderDTOInDB);
            }

            //edocHeaderDTO.setEdocTaskStatus(Constant.BizStatus.WRITE_OFF);
            //edocHeaderFacade.updateEdocHeader(edocHeaderDTO);

            //修改凭证表voucher_status = 4 ==> 待归档
            edocCredentialsService.updateVoucherStatus(edocHeaderDTOInDB.getId(), "4");

            result.setSuccessMessage("归档逆向流程完成");
        }catch (Exception e) {
            result.addErrorMessage("归档逆向流程异常");
            ACCOUNTINGBOX.error("归档逆向流程异常{}", e);
            return result;
        }
        return result;
    }


    @Transactional
    @Override
    public ExecuteResult<String> cancelArchiveBySection(Long sectionId) {
//        EdocHeaderDTO edocHeaderDTO=new EdocHeaderDTO();
//        edocHeaderDTO.setSectionId(sectionId);
//        ExecuteResult<PageInfo<EdocHeaderDTO>> headers = edocHeaderFacade.queryArchivedAccount(edocHeaderDTO);
//
//        if (headers!=null && headers.getResult()!=null && headers.getResult().getList()!=null){
//
//        }

        ExecuteResult<String> result = new ExecuteResult<>();
        AccountingSectionItem item = new AccountingSectionItem();
        item.setAccountingSection(sectionId+"");
        item.setDeleted(Constant.DeleteFlag.DEL_NO);
        List<AccountingSectionItem> items = accountingSectionItemService.query(item);
        if (items!=null && items.size()>0) {
            AccountingArchives accountingArchives = new AccountingArchives();
            accountingArchives.setDeleted(Constant.DeleteFlag.DEL_NO);
            for (AccountingSectionItem i : items) {
                //已经完成归档的，则直接逻辑删除section_item的数据
                accountingSectionItemService.remove(i);

                //修改edoc状态为凭证冲销
                ExecuteResult<EdocHeaderDTO> edocHeaderDTOInDB = edocHeaderFacade.findEdocHeaderById(Long.valueOf(i.getEdocId()));
                if ((edocHeaderDTOInDB != null) && (edocHeaderDTOInDB.getResult() != null)) {
                    edocHeaderDTOInDB.getResult().setEdocTaskStatus(Constant.BizStatus.WRITE_OFF);
                    edocHeaderDTOInDB.getResult().setEdocVoucherStatus(Constant.FsscEdocStatus.UN_HANDLE);
                    edocHeaderDTOInDB.getResult().setEdocCertStatus(Constant.FsscEdocStatus.UN_HANDLE);
                    edocHeaderDTOInDB.getResult().setEdocArchiveStatus(Constant.FsscEdocStatus.UN_HANDLE);
                    edocHeaderFacade.updateEdocHeader(edocHeaderDTOInDB.getResult());
                }

                //修改凭证表voucher_status = 4 ==> 待归档
                edocCredentialsService.updateVoucherStatus(Long.valueOf(i.getEdocId()), "4");
            }
        }else{
            result.addErrorMessage("不存在归档单据");
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<List<String>> findSectionNoByBoxId(Long id) {
        ExecuteResult<List<String>> result = new ExecuteResult<>();
        try {
            List<String> sectionNoList = new ArrayList<>();
            List<String> sectionIdList = accountingBoxItemService.findSectionIdByAccountingBoxId(id);
            for (String sectionId:sectionIdList) {
                AccountingSection accountingSection = accountingSectionService.queryById(Long.valueOf(sectionId));
                if (!BeanUtil.isEmpty(accountingSection)) {
                    sectionNoList.add(accountingSection.getRecordsNo());
                }
            }
            result.setResult(sectionNoList);
        }catch (Exception e) {
            result.addErrorMessage("归档逆向流程异常");
            ACCOUNTINGBOX.error("归档逆向流程异常{}", e);
            return result;
        }
        return result;
    }
}
