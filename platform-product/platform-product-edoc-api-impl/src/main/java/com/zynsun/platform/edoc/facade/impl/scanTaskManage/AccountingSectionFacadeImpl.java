package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingArchivesDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingBoxDTO;
import com.zynsun.platform.edoc.dto.archive.AccountingSectionDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.AccountingSectionFacade;
import com.zynsun.platform.edoc.model.AccountingSectionModel;
import com.zynsun.platform.edoc.service.*;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-20 下午 12:38
 * @Modified By:
 */
@Service(value = "accountingSectionFacade")
public class AccountingSectionFacadeImpl implements AccountingSectionFacade {

    private static final Logger SECTION_LOGGOR = LoggerFactory.getLogger(AccountingSectionFacadeImpl.class);
    @Autowired
    private AccountingSectionService accountingSectionService;
    @Autowired
    private AccountingBoxService accountingBoxService;
    @Autowired
    private AccountingArchivesService accountingArchivesService;
    @Autowired
    private AccountingSectionItemService accountingSectionItemService;
    @Autowired
    private EdocHeaderService edocHeaderService;
    @Autowired
    private AccountingBoxItemService accountingBoxItemService;
    @Autowired
    private EdocCredentialsService edocCredentialsService;
    @Autowired
    private EdocImageService edocImageService;

    @Override
    public ExecuteResult<PageInfo<AccountingSectionDTO>> findAccountingSection(AccountingSectionDTO accountingSectionDTO) {
        ExecuteResult<PageInfo<AccountingSectionDTO>> result = new ExecuteResult<>();
        try {
            Page<AccountingSectionModel> page = accountingSectionService.findAccountingSections(DozerUtil.map(accountingSectionDTO, AccountingSectionModel.class));
            PageInfo<AccountingSectionDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(), AccountingSectionDTO.class);
            List<AccountingSectionDTO> list = pageInfo.getList();
            if (list != null && !list.isEmpty()) {
                for (AccountingSectionDTO accountingSection1 : list) {
                    accountingSection1.setVolumes(accountingSectionService.findVolumesByBizType(accountingSection1.getBizType(), accountingSection1.getStartDate(), accountingSection1.getEndDate(), accountingSection1.getOfficeId()));
                }
                pageInfo.setList(list);
            }
            result.setResult(pageInfo);
            result.setSuccessMessage("分册列表查询成功");
        } catch (Exception e) {
            result.addErrorMessage("分册列表查询失败");
        }
        return result;
    }


    @Override
    public ExecuteResult<Integer> saveAccountingSection(List<Long> ids, AccountingSectionDTO accountingSectionDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            // 做有无影像件的处理
            boolean isEdocImageExists = false;
            for (Long edocHeaderId:ids) {
                EdocImage edocImage = new EdocImage();
                edocImage.setEdocHeaderId(edocHeaderId);
                List<EdocImage> edocImageList = edocImageService.queryEdocImage(edocImage);
                if (!BeanUtil.isEmpty(edocImageList)) {
                    isEdocImageExists = true;
                    break;
                }
            }

            if (isEdocImageExists) { // 有影像件
                accountingSectionDTO.setEdocImageExists("1");
            }else { // 没有影像件
                accountingSectionDTO.setEdocImageExists("0");
            }

            String message = accountingSectionService.findOrCreateAccountingSection(DozerUtil.map(accountingSectionDTO, AccountingSection.class),ids);
            if (message != null && !"".equals(message)) {
                result.setResult(1);
                result.setSuccessMessage(message);
            }
        }catch (Exception e) {
            result.addErrorMessage(e.toString());
            SECTION_LOGGOR.error("分册失败", e);
            return result;
        }
        return result;
    }


    @Override
    public ExecuteResult<List<AccountingSectionDTO>> findAccountingSectionsByIds(List<Long> ids) {
        ExecuteResult<List<AccountingSectionDTO>> result = new ExecuteResult<>();
        try {
            List<AccountingSection> list = accountingSectionService.findAccountingSectionsByIds(ids);
            List<AccountingSectionDTO> accountingSectionDTOList = DozerUtil.mapList(list,AccountingSectionDTO.class);
            result.setSuccessMessage("查询成功");
            result.setResult(accountingSectionDTOList);
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            SECTION_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<AccountingSectionDTO> findByRecordNo(String recordNo) {
        ExecuteResult<AccountingSectionDTO> result = new ExecuteResult<>();
        try {
            AccountingSection accountingSection = null;
            accountingSection = accountingSectionService.findAccountingSectionByRecordsNo(recordNo);
            if (accountingSection != null) {
                AccountingSectionDTO accountingSectionDTO = DozerUtil.map(accountingSection,AccountingSectionDTO.class);
                result.setResult(accountingSectionDTO);
            } else {
                result.setSuccessMessage("该流水号不存在");
            }
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            SECTION_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> sectionBoxing(AccountingBoxDTO accountingBoxDTO, List<Long> ids) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            AccountingSection record = null;
            if (ids != null && !ids.isEmpty()) { 
                record = accountingSectionService.queryById(ids.get(0));
            }
            AccountingBox boxRecord = DozerUtil.map(accountingBoxDTO, AccountingBox.class);
            boxRecord.setBoxStatus(Constant.AccountingBoxStatus.SECTION);
            boxRecord.setBoxFlag(Constant.AccountingBoxArchivedStatus.ARCHIVED_NO);
            boxRecord.setBoxNo(accountingSectionService.findBoxNo(record));
            if ("报账单业务".equals(boxRecord.getBizType())) {
                boxRecord.setBizType("01");
            } else if ("手工凭证业务".equals(boxRecord.getBizType())) {
                boxRecord.setBizType("3");
            } else if ("销项发票业务".equals(boxRecord.getBizType())) {
                boxRecord.setBizType("BIZ_AP");
            } else if ("银行回单业务".equals(boxRecord.getBizType())) {
                boxRecord.setBizType("03");
            }
            String message = accountingBoxService.sectionBoxCreateOrFindBox(boxRecord,ids);
            if (message != null && !"".equals(message)) {
                result.setResult(1);
                result.setSuccessMessage(message);
            }
        } catch (Exception e) {
            result.addErrorMessage("分盒失败");
            SECTION_LOGGOR.error("分盒失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> archive(List<Long> ids, AccountingArchivesDTO accountingArchivesDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            String message = accountingArchivesService.archive(ids,DozerUtil.map(accountingArchivesDTO,AccountingArchives.class));
            if(message==null||"".equals(message)){
                result.setResult(1);
                result.setSuccessMessage("归档成功");
                return result;
            }else{
                result.setSuccessMessage(message);
                return result;
            }

        } catch (Exception e) {
            result.addErrorMessage("归档失败");
            SECTION_LOGGOR.error("归档失败", e);
            return result;
        }
    }

    @Override
    public ExecuteResult<List<AccountingSectionDTO>> findSectionByBoxId(Long id) {
        ExecuteResult<List<AccountingSectionDTO>> result = new ExecuteResult<>();
        try {
            List<AccountingSectionModel> accountingSectionModels = accountingSectionService.findSectionsBoxId(id);
            accountingSectionModels.forEach(accountingSectionModel -> {
                accountingSectionModel.setVolumes(this.findVolumesByBizType(accountingSectionModel.getBizType(), accountingSectionModel.getStartDate(), accountingSectionModel.getEndDate(), accountingSectionModel.getOfficeId()).getResult());
            });

            if (accountingSectionModels != null && !accountingSectionModels.isEmpty()) {
                result.setResult(DozerUtil.mapList(accountingSectionModels, AccountingSectionDTO.class));
            } else {
                result.setSuccessMessage("没有找到对应的数据");
                return result;
            }
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            SECTION_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> findVolumesByBizType(String bizType, String startDate, String endDate, String companyCode) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            result.setSuccessMessage("查询成功");
            result.setResult(accountingSectionService.findVolumesByBizType(bizType, startDate, endDate, companyCode));
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            SECTION_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<AccountingSectionDTO> findSectionById(Long id) {
        ExecuteResult<AccountingSectionDTO> result = new ExecuteResult<>();
        try {
            AccountingSection accountingSection = accountingSectionService.queryById(id);
            if (accountingSection != null) {
                AccountingSectionDTO accountingSectionDTO = DozerUtil.map(accountingSection, AccountingSectionDTO.class);
                result.setResult(accountingSectionDTO);
            } else {
                result.setSuccessMessage("没有找到对应的数据");
                return result;
            }
        } catch (Exception e) {
            result.addErrorMessage("查询失败");
            SECTION_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> deleteSectionById(Long id) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            List<Long> edocHeaderIds = accountingSectionItemService.findBillHeaderIdsBySectionId(id.toString().trim());
            if (edocHeaderIds != null && !edocHeaderIds.isEmpty()) {
                for (Long billHeaderId : edocHeaderIds) {
                    EdocHeader edocHeader = edocHeaderService.queryById(billHeaderId);
                    if (edocHeader != null) {
                        edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_ARCHIVED);
                        edocHeaderService.updateByIdSelective(edocHeader);
                    }
                    List<EdocCredentials> edocCredentialsList=edocCredentialsService.queryByEdocId(billHeaderId);
                    for(EdocCredentials edocCredentials:edocCredentialsList){
                        edocCredentials.setVoucherStatus("4");
                        edocCredentialsService.updateById(edocCredentials);
                    }
                }
            }
            accountingSectionService.deleteByIdLogic(id);
            accountingSectionItemService.deleteSectionItemBySectionId(id);
            result.setResult(1);
            result.setSuccessMessage("删除分册成功");
        } catch (Exception e) {
            result.addErrorMessage("删除分册失败");
            SECTION_LOGGOR.error("删除分册失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<List<Long>> findBillHeaderIdsBySectionId(String sectionId) {
        ExecuteResult<List<Long>> result = new ExecuteResult<>();
        try {
            List<Long> edocHeaderIds = accountingSectionItemService.findBillHeaderIdsBySectionId(sectionId);
            result.setResult(edocHeaderIds);
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            result.addErrorMessage("查询失败");
            SECTION_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> editAccountingSection(AccountingSectionDTO accountingSectionDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            AccountingSection accountingSection = accountingSectionService.queryById(accountingSectionDTO.getId());
            if(!BeanUtil.isEmpty(accountingSection)){
                accountingSectionDTO.setLastModifyBy(SubjectUtil.getUser().getLoginName());
                accountingSectionDTO.setLastModifyTime(new Date());
                accountingSectionDTO.setVersion(accountingSection.getVersion());
                accountingSectionService.updateByIdSelective(DozerUtil.map(accountingSectionDTO,AccountingSection.class));
                String[] ids = accountingSectionDTO.getIds().split(",");
                List<Long> idsList = new ArrayList<>();
                for (int i = 0;i<ids.length;i++) {
                    idsList.add(Long.parseLong(ids[i]));
                }

                accountingSectionItemService.saveAccountingSectionItems(idsList,DozerUtil.map(accountingSectionDTO,AccountingSection.class));
            }else{
                result.setSuccessMessage("没有查到对应的分册信息");
                return result;
            }
            result.setSuccessMessage("修改成功");
            return result;
        }catch(javax.persistence.OptimisticLockException e){
            SECTION_LOGGOR.error("乐观锁异常", e);
            result.addErrorMessage("单据已经被其他人修改，保存失败!");
            return result;
        }
        catch (Exception e) {
            result.addErrorMessage("修改分册失败");
            SECTION_LOGGOR.error("修改分册失败", e);
            return result;
        }
    }

    @Override
    public ExecuteResult<AccountingSectionDTO> addSectionBoxItem(String recordNo, Long boxId) {
        ExecuteResult<AccountingSectionDTO> result = new ExecuteResult<>();
        try {
            AccountingSection accountingSection = accountingSectionService.findAccountingSectionByRecordsNo(recordNo);
            AccountingBox accountingBox = accountingBoxService.queryById(boxId);
            if (accountingBox == null) {
                result.setSuccessMessage("没有对应的编盒信息");
                return result;
            }
            if (accountingSection == null) {
                result.setSuccessMessage("没有对应的分册信息");
                return result;
            }
            if (!accountingBox.getCompanyName().equals(accountingSection.getOfficeName())) {
                result.setSuccessMessage("非同一公司下分册无法编盒");
                return result;
            }
            if (!accountingBox.getBizType().equals(accountingSection.getBizType())) {
                result.setSuccessMessage("非同一业务类型分册无法编盒");
                return result;
            }
            if (!accountingBox.getStartDate().equals(accountingSection.getStartDate())) {
                result.setSuccessMessage("非同一起始月份分册无法编盒");
                return result;
            }
            if (!accountingBox.getEndDate().equals(accountingSection.getEndDate())) {
                result.setSuccessMessage("非同一截止月份分册无法编盒");
                return result;
            }
            List<Long> sectionIds = new ArrayList<>();
            sectionIds.add(accountingSection.getId());
            String message = accountingBoxItemService.saveSectionBoxItems(sectionIds, accountingBox);

            if ("分盒成功".equals(message)) {
                accountingSection.setVolumes(accountingSectionService.findVolumesByBizType(accountingSection.getBizType(),accountingSection.getStartDate(),accountingSection.getEndDate(),accountingSection.getOfficeId()));
                result.setResult(DozerUtil.map(accountingSection, AccountingSectionDTO.class));
                result.setSuccessMessage("添加编盒数据成功");
                return result;
            } else {
                result.setSuccessMessage(message);
            }

        } catch (Exception e) {
            result.addErrorMessage("添加编盒数据失败");
            SECTION_LOGGOR.error("添加编盒数据失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocHeaderDTO>> findEdocHeaderBySectionId(String sectionId) {
        ExecuteResult<List<EdocHeaderDTO>> result = new ExecuteResult<>();
        try {
           List<EdocHeaderDTO> edocHeaderDTOList = new ArrayList<>();
           List<Long> edocHeaderIdList = accountingSectionItemService.findBillHeaderIdsBySectionId(sectionId);
            for (Long edocHeaderId:edocHeaderIdList) {
                EdocHeader edocHeader = edocHeaderService.queryById(edocHeaderId);
                if (!BeanUtil.isEmpty(edocHeader)) {
                    edocHeaderDTOList.add(DozerUtil.map(edocHeader,EdocHeaderDTO.class));
                }
            }
            result.setSuccessMessage("查询成功");
            result.setResult(edocHeaderDTOList);
        }catch (Exception e) {
            result.addErrorMessage("查询失败");
            SECTION_LOGGOR.error("查询失败{}", e);
        }
        return result;
    }
}
