package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.ArchiveMove;
import com.zynsun.platform.edoc.dto.archive.ArchiveMoveDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.ArchiveMoveFacade;
import com.zynsun.platform.edoc.model.ArchiveMoveModel;
import com.zynsun.platform.edoc.service.ArchiveMoveService;
//import com.zynsun.platform.workflow.facade.WfProcessFacade;
//import com.zynsun.platform.workflow.facade.WfTaskFacade;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-26 上午 10:03
 * @Modified By:
 */
@Service(value="archiveMoveFacade")
public class ArchiveMoveFacadeImpl implements ArchiveMoveFacade {

    private static final Logger ARCHIVE_MOVE_LOGGOR = LoggerFactory.getLogger(ArchiveHeaderFacadeImpl.class);

    @Autowired
    private ArchiveMoveService archiveMoveService;
//    @Autowired
//    private WfProcessFacade wfProcessFacade;
    /*@Autowired
    private DataDictCliFacade dataDictCliFacade;*/
//    @Autowired
//    private WfTaskFacade wfTaskFacade;





    @Override
    public ExecuteResult<PageInfo<ArchiveMoveDTO>> findArchiveMoveList(ArchiveMoveDTO archiveMoveDTO) {
        ExecuteResult<PageInfo<ArchiveMoveDTO>> result = new ExecuteResult<>();
        try {
            ArchiveMoveModel archiveMove = DozerUtil.map(archiveMoveDTO,ArchiveMoveModel.class);
            Page<ArchiveMoveModel> archiveMovesPage = archiveMoveService.queryArchiveMoveList(archiveMove);
            PageInfo<ArchiveMoveDTO> pageInfo = DozerUtil.mapPage(archiveMovesPage.toPageInfo(),ArchiveMoveDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询成功");
        }catch (Exception e) {
            ARCHIVE_MOVE_LOGGOR.error("档案移交分页查询失败{}",e);
            result.setErrorMessages(Arrays.asList("分页查询失败"));
            return result;
        }
        return result;
    }


    @Override
    public ExecuteResult<String> addArchiveMoveRecord(ArchiveMoveDTO archiveMoveDTO,String companyId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            ArchiveMove archiveMove = DozerUtil.map(archiveMoveDTO,ArchiveMove.class);
            archiveMove.setDeleted(Constant.DeleteFlag.DEL_NO);
            archiveMove.setVersion(0L);
            archiveMove.setCreateTime(new Date());
            archiveMove.setStatus("0");
            archiveMove.setProcessStatus("0");
            archiveMoveService.add(archiveMove);
            //ArchiveMove archiveMove1 = archiveMoveService.query(archiveMove>.get(0);
            archiveMoveDTO.setId(archiveMove.getId());
            //启动流程
            //ExecuteResult<SysConfDictItemDTO> wfAccount = dataDictCliFacade.findByEnNameAndItemCode("EDOC_WORKFLOW","ARCHIVEMOVE");
            HashMap<String,Object> variables = new HashMap<>();
            variables.put("toChargeUser",archiveMoveDTO.getToChargePerson());
            variables.put("fromChargeUser",archiveMoveDTO.getFromChargePerson());
            variables.put("archiveId",archiveMoveDTO.getArchiveId());
            variables.put("archiveType",archiveMoveDTO.getArchiveType());
            variables.put("toCompany",archiveMoveDTO.getToCompany());
            variables.put("toArchiveHeaderName",archiveMoveDTO.getToArchiveHeaderName());
            variables.put("companyId",companyId);
            variables.put("archiveMoveId",archiveMove.getId());
//            wfProcessFacade.startProcess("edocMove",String.valueOf(archiveMoveDTO.getId()),variables);
            result.setSuccessMessage("新增档案移交成功");
        }catch (Exception e) {
            ARCHIVE_MOVE_LOGGOR.error("新增档案移交失败{}",e);
            result.setErrorMessages(Arrays.asList("新增档案移交失败"));
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<ArchiveMoveDTO>> queryAllWorkList(ArchiveMoveDTO archiveMoveDTO) {
        ExecuteResult<PageInfo<ArchiveMoveDTO>> result = new ExecuteResult<>();
        try {
            if(StringUtils.isBlank(archiveMoveDTO.getTaskAssingee())){
                archiveMoveDTO.setTaskAssingee(SubjectUtil.getUser().getLoginName());
            }
            archiveMoveDTO.setTenantId("AP");
            ArchiveMoveModel archiveMoveModel = DozerUtil.map(archiveMoveDTO,ArchiveMoveModel.class);
            Page<ArchiveMoveModel> page = archiveMoveService.queryToBeWorkList(archiveMoveModel);
            PageInfo<ArchiveMoveDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),ArchiveMoveDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询档案移交待办成功");
        }catch (Exception e) {
            ARCHIVE_MOVE_LOGGOR.error("分页查询档案移交待办失败{}",e);
            result.setErrorMessages(Arrays.asList("分页查询档案移交待办失败"));
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<ArchiveMoveDTO>> queryDoneWorkList(ArchiveMoveDTO archiveMoveDTO) {
        ExecuteResult<PageInfo<ArchiveMoveDTO>> result = new ExecuteResult<>();
        try {
            if(StringUtils.isBlank(archiveMoveDTO.getTaskAssingee())){
                archiveMoveDTO.setTaskAssingee(SubjectUtil.getUser().getLoginName());
            }
            archiveMoveDTO.setTenantId("AP");
            ArchiveMoveModel archiveMoveModel = DozerUtil.map(archiveMoveDTO,ArchiveMoveModel.class);
            Page<ArchiveMoveModel> page = archiveMoveService.queryDoneWorkList(archiveMoveModel);
            PageInfo<ArchiveMoveDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),ArchiveMoveDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询档案移交待办成功");
        }catch (Exception e) {
            ARCHIVE_MOVE_LOGGOR.error("分页查询档案移交待办失败{}",e);
            result.setErrorMessages(Arrays.asList("分页查询档案移交待办失败"));
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<String> archiveMoveCheck(String id, String businessKey, String taskDefinitionKey,String passOrReject) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            Assert.notNull(passOrReject,"没有同意信息");
            Map<String,Object> variables = new HashMap<>();
            variables.put("message",passOrReject);
            //工作流完成任务
//            wfTaskFacade.completeTask(businessKey,id,taskDefinitionKey,variables);
            result.setSuccessMessage("档案移交申请同意成功");
        }catch (Exception e) {
            ARCHIVE_MOVE_LOGGOR.error("档案移交申请同意异常{}",e);
            result.setErrorMessages(Arrays.asList("档案移交申请同意异常"));
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<String> updateArchiveMove(ArchiveMoveDTO archiveMoveDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            ArchiveMove archiveMove = DozerUtil.map(archiveMoveDTO,ArchiveMove.class);
            archiveMoveService.updateByIdSelective(archiveMove);
            result.setSuccessMessage("更新成功");
        }catch (Exception e) {
            ARCHIVE_MOVE_LOGGOR.error("更新异常{}",e);
            result.setErrorMessages(Arrays.asList("更新异常"));
            return result;
        }
        return null;
    }

    @Override
    public ExecuteResult<ArchiveMoveDTO> queryArchiveMoveById(Long id) {
        ExecuteResult<ArchiveMoveDTO> result = new ExecuteResult<>();
        try {
            ArchiveMove archiveMove = archiveMoveService.queryById(id);
            result.setResult(DozerUtil.map(archiveMove,ArchiveMoveDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            ARCHIVE_MOVE_LOGGOR.error("查询失败{}",e);
            result.setErrorMessages(Arrays.asList("查询失败"));
            return result;
        }
        return result;
    }
}
