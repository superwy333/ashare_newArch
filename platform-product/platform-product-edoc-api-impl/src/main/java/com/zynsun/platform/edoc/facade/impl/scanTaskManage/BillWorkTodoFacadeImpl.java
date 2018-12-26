package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.InvoiceDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.BillWorkTodoFacade;
import com.zynsun.platform.edoc.facade.scanTaskManage.InvoiceFacade;
import com.zynsun.platform.edoc.service.EdocHeaderService;

//import com.zynsun.platform.express.dto.ExpDetailDTO;
//
//import com.zynsun.platform.express.facade.ExpDetailFacade;
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
 * @Date:Created in 2018-06-04 下午 1:42
 * @Modified By:
 */
@Service("billWorkTodoFacade")
public class BillWorkTodoFacadeImpl implements BillWorkTodoFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(EdocHeaderFacadeImpl.class);

    @Autowired
    private EdocHeaderService edocHeaderService;
/*    @Autowired
    private FitCheckTaskFacade fitCheckTaskFacade;
    @Autowired
    private FitCheckTaskHistoryFacade fitCheckTaskHistoryFacade;*/
    @Autowired
    private InvoiceFacade invoiceFacade;
    /*@Autowired
    private DataDictCliFacade dataDictCliFacade;*/
//    @Autowired
//    private ExpDetailFacade expDetailFacade;

    @Transactional
    @Override
    public ExecuteResult<List<EdocHeaderDTO>> saveReceive(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<List<EdocHeaderDTO>> result = new ExecuteResult<List<EdocHeaderDTO>>();
        result.setResult(new ArrayList<EdocHeaderDTO>());
        edocHeaderDTO.setDeleted(Constant.DeleteFlag.DEL_NO);
        try {
            EdocHeader edocHeader = new EdocHeader();
            edocHeader.setEdocNo(edocHeaderDTO.getEdocNo());
            //根据edocno和不是作废状态查询单据是否存在 ,同时该单据不存在在邮包中
            List<EdocHeader> edocHeaderList = edocHeaderService.findValidEdocHeader(edocHeader);
            //查询单据是否存在在邮包中 或实物签收等于签退
            //Integer num = expDetailFacade.findExpDetail(edocHeader.getEdocNo()).getResult();
            Integer num = edocHeaderService.findEdocPhysicalStatus(edocHeader.getEdocNo());
            if (null==edocHeaderList || edocHeaderList.size()==0){
                result.addErrorMessage("没有找到符合条件的单据");
            }else if (edocHeaderList.size()>1){
                result.addErrorMessage("存在多条符合条件的数据");
            }else if (edocHeaderList.size()==1 && num> 0){// && (null==edocHeaderList ||expDetailList.size() == 0)存在该单据并且不存在在邮包中
                edocHeader = edocHeaderList.get(0);
                /*boolean flag1 = edocHeader.getEdocPhysicalStatus().equals(EdocConstant.physicalStatus.UNCHECK.getValue().toString());
                boolean flag2 = edocHeader.getEdocPhysicalStatus().equals(EdocConstant.physicalStatus.RETURNBILL.getValue().toString());
                if (flag1||flag2){
                    CuxReceiveInfo cuxReceiveInfo = new CuxReceiveInfo();
                    cuxReceiveInfo.setEdocNo(billHeader.getEdocNo());
                    cuxReceiveInfo.setSourceType("RECEIVE");
                    cuxReceiveInfo.setReceiveTime(new Date());
                    cuxReceiveInfo.setBizTypeCode(billHeader.getBizTypeCode());
                    cuxReceiveInfoTService.createReceiveInfo(cuxReceiveInfo);
                    billHeader.setPhysicalStatus(EdocConstant.physicalStatus.CHECKED.getValue().toString());
                    billHeaderService.updateByIdSelective(billHeader);
                    result.setResult(new ArrayList<EdocHeaderDTO>());
                    result.setSuccessMessage("自助签收成功");
                }else {
                    result.addErrorMessage("状态为：已签收,自助签收失败");
                }*/
                if (BeanUtil.isEmpty(edocHeader.getPublicType())) {
                    edocHeader.setPublicType("0");
                }

                if (BeanUtil.isEmpty(edocHeader.getEdocPhysicalStatus())||!edocHeader.getEdocPhysicalStatus().equals(Constant.EdocPhysicalStatus.NORMAL_RECEIVED)) {
                    edocHeader.setEdocPhysicalStatus(Constant.EdocPhysicalStatus.NORMAL_RECEIVED);
                    edocHeader.setEdocPhysicalReceiver(edocHeaderDTO.getEdocPhysicalReceiver());
                    edocHeader.setExtField2("1"); //实物签收-1 邮包签收-0
                    edocHeaderService.updateByIdSelective(edocHeader);

                    //签收时，一致性校验任务处理
                    /*physicalFitTask(edocHeader);
                    expDetailFacade.addPhysicalReceiveDetail(edocHeader.getEdocNo(), 0);*/
                    result.setSuccessMessage("自助签收成功");
                }else {
                    result.addErrorMessage("状态为：已签收,自助签收失败");
                }
            }else if (num == 0){
                result.addErrorMessage("该单据已在邮包中或已签收!");
            }
        } catch (Exception e) {
            LOGGER.error("签收失败");
            result.addErrorMessage("系统签收失败");
        }
        return result;
    }

    /*// 实物签收时一致性任务处理
    private void physicalFitTask(EdocHeader edocHeader) {
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>S 对公单据处理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if ("0".equals(edocHeader.getPublicType())) {
            FitCheckTaskDTO fitCheckTaskDTODB = fitCheckTaskFacade.queryFitCheckTaskByEdocNo(edocHeader.getEdocNo()).getResult();
            // 对公单据，如果不存在需要先创建一致性任务
            if (BeanUtil.isEmpty(fitCheckTaskDTODB)) {
                FitCheckTaskDTO fitCheckTaskDTO = new FitCheckTaskDTO();
                fitCheckTaskDTO.setEdocNo(edocHeader.getEdocNo());
                fitCheckTaskDTO.setEdocType("01");
                fitCheckTaskDTO.setFitStatus(Byte.parseByte("2"));
                fitCheckTaskDTO.setFitType("0");
                fitCheckTaskDTO.setCreateBy(SubjectUtil.getUser().getLoginName());
                fitCheckTaskDTO.setCreateTime(new Date());
                fitCheckTaskDTO.setDeleted(0);
                fitCheckTaskDTO.setVersion(0L);
                fitCheckTaskDTO.setEdocHeaderId(edocHeader.getId());
                fitCheckTaskDTO.setTaskStatus(Constant.FitCheckTaskStatus.WAITING_DEAL);
                fitCheckTaskFacade.saveFitCheckTask(fitCheckTaskDTO);
            } else {
                // 存在需要更新签收人
                fitCheckTaskDTODB.setCreateBy(SubjectUtil.getUser().getLoginName());

                // 若task_status=0待处理和 task_status=1已处理(检验结果是待定时) 以及补扫退回,则不在创建  若task_status=1已处理(检验结果是退回或通过时),则把原来的变成历史记录 重新创建一条ßß
                historyFitTask(fitCheckTaskDTODB);
            }
        }
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>E 对公单据处理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>S 对私单据处理>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        if ("1".equals(edocHeader.getPublicType())) {
            String threshold = dataDictCliFacade.findByEnNameAndItemCode("SYNC_FROM_SHARE", "NEED_CREATE_INV_AMOUNT").getResult().getDicItemValue();
            if (BeanUtil.isEmpty(threshold)) {
                threshold = "500";
            }
            //如果明细中存在对公的单据，则创建一条一致性检查任务
            boolean needCreate = false;
            List<InvoiceDTO> invoiceDTOList = invoiceFacade.queryInvoicesByEdocNo(edocHeader.getEdocNo()).getResult().getList();
            if (!BeanUtil.isEmpty(invoiceDTOList)) {
                boolean createFlag = false;
                for (InvoiceDTO i:invoiceDTOList) {
                    try {
                        createFlag = Double.parseDouble(i.getInvTotal() == null? i.getInvAmount() : i.getInvTotal()) > Double.parseDouble(threshold);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    if (createFlag) {
                        needCreate = true;
                        break;
                    }
                }
            }

            //查询是否存在一致性任务
            FitCheckTaskDTO existFitCheck = fitCheckTaskFacade.queryFitCheckTaskByEdocNo(edocHeader.getEdocNo()).getResult();
            if (existFitCheck == null) {//不存在
                if (needCreate) {
                    FitCheckTaskDTO fitCheckTaskDTO = new FitCheckTaskDTO();
                    fitCheckTaskDTO.setEdocNo(edocHeader.getEdocNo());
                    fitCheckTaskDTO.setEdocType("01");
                    fitCheckTaskDTO.setFitType("1");
                    fitCheckTaskDTO.setCreateBy(SubjectUtil.getUser().getLoginName());
                    fitCheckTaskDTO.setCreateTime(new Date());
                    fitCheckTaskDTO.setDeleted(0);
                    fitCheckTaskDTO.setVersion(0L);
                    fitCheckTaskDTO.setEdocHeaderId(edocHeader.getId());
                    fitCheckTaskDTO.setTaskStatus(Constant.FitCheckTaskStatus.WAITING_DEAL);
                    fitCheckTaskFacade.saveFitCheckTask(fitCheckTaskDTO);
                }
            } else {//存在
                if (needCreate) {
                    existFitCheck.setCreateBy(SubjectUtil.getUser().getLoginName());

                    //存在一致性检验的任务
                    // 若task_status=0待处理和 task_status=1已处理(检验结果是待定时) 以及补扫退回,则不在创建  若task_status=1已处理(检验结果是退回或通过时),则把原来的变成历史记录 重新创建一条
                    historyFitTask(existFitCheck);
                } else {
                    existFitCheck.setCreateBy(SubjectUtil.getUser().getLoginName());
                    fitCheckTaskFacade.updateFitCheckTask(existFitCheck);
                }
            }

        }
    }
    //  判断是否需要创建历史一致性任务
    private void historyFitTask(FitCheckTaskDTO fitCheckTaskDTODB) {
        if (*//*"0".equals(fitCheckTaskDTODB.getTaskStatus()) ||*//*
                ("1".equals(fitCheckTaskDTODB.getTaskStatus()) && 2 == (fitCheckTaskDTODB.getFitStatus()))
                        *//*|| ("2".equals(fitCheckTaskDTODB.getTaskStatus()))*//*) {
        } else if (("1".equals(fitCheckTaskDTODB.getTaskStatus()) && 0 == (fitCheckTaskDTODB.getFitStatus())) ||
                ("1".equals(fitCheckTaskDTODB.getTaskStatus()) && 1 == (fitCheckTaskDTODB.getFitStatus()))) {
            fitCheckTaskDTODB.setExtField1(fitCheckTaskDTODB.getId().toString());
            //将数据存入历史记录中
            fitCheckTaskHistoryFacade.saveFitCheckTask_history(fitCheckTaskDTODB);

            //同时创建一条新的一致性检查任务
            fitCheckTaskDTODB.setExtField1(null);
            fitCheckTaskDTODB.setFitStatus(null);//任务状态未处理fit_status` --- '0-通过 1-退回 2-待定',
            fitCheckTaskDTODB.setTaskStatus(Constant.FitCheckTaskStatus.WAITING_DEAL);//0 -未处理 1-已处理
            fitCheckTaskDTODB.setReason(null);
            fitCheckTaskDTODB.setRemarks(null);
            fitCheckTaskFacade.updateFitCheckTaskById(fitCheckTaskDTODB);
        }
    }*/

    @Override
    public ExecuteResult<List<EdocHeaderDTO>> saveBack(EdocHeaderDTO edocHeaderDTO) {
        ExecuteResult<List<EdocHeaderDTO>> result = new ExecuteResult<List<EdocHeaderDTO>>();
        result.setResult(new ArrayList<EdocHeaderDTO>());
        edocHeaderDTO.setDeleted(Constant.DeleteFlag.DEL_NO);
        //EdocHeaderDTO edocHeaderDTO1 = new EdocHeaderDTO();
        try {
            //edocHeaderDTO.setEdocNo(EdocHeaderDTO.getEdocNo());
            //edocHeaderDTO.setDeleted(Constant.DeleteFlag.DEL_NO);
            EdocHeader edocHeader = new EdocHeader();
            DozerUtil.map(edocHeaderDTO,edocHeader);
            List<EdocHeader> edocHeaderList = edocHeaderService.query(edocHeader);
            if (null==edocHeaderList || edocHeaderList.size()==0){
                result.addErrorMessage("没有找到符合条件的单据");
            }else if (edocHeaderList.size()>1){
                result.addErrorMessage("存在多条符合条件的数据");
            }else if (edocHeaderList.size()==1){
                edocHeader = edocHeaderList.get(0);
                /*boolean flag1 = edocHeader.getEdocPhysicalStatus().equals(EdocConstant.physicalStatus.UNCHECK.getValue().toString());
                boolean flag2 = edocHeader.getEdocPhysicalStatus().equals(EdocConstant.physicalStatus.RETURNBILL.getValue().toString());
                if (flag1||flag2){
                    CuxReceiveInfo cuxReceiveInfo = new CuxReceiveInfo();
                    cuxReceiveInfo.setEdocNo(billHeader.getEdocNo());
                    cuxReceiveInfo.setSourceType("RECEIVE");
                    cuxReceiveInfo.setReceiveTime(new Date());
                    cuxReceiveInfo.setBizTypeCode(billHeader.getBizTypeCode());
                    cuxReceiveInfoTService.createReceiveInfo(cuxReceiveInfo);
                    billHeader.setPhysicalStatus(EdocConstant.physicalStatus.CHECKED.getValue().toString());
                    billHeaderService.updateByIdSelective(billHeader);
                    result.setResult(new ArrayList<EdocHeaderDTO>());
                    result.setSuccessMessage("自助签收成功");
                }else {
                    result.addErrorMessage("状态为：已签收,自助签收失败");
                }*/
                if (!edocHeader.getEdocPhysicalStatus().equals(Constant.EdocPhysicalStatus.BACK_RECEIVED)) {
                    edocHeader.setEdocPhysicalStatus(Constant.EdocPhysicalStatus.BACK_RECEIVED);
                    edocHeaderService.updateByIdSelective(edocHeader);

                    // 添加实物签退列表
                    /*expDetailFacade.addPhysicalReceiveDetail(edocHeader.getEdocNo(), 1);*/
                    result.setSuccessMessage("自助签退成功");
                }else {
                    result.addErrorMessage("状态为：已签退,自助签退失败");
                }
            }
        } catch (Exception e) {
            LOGGER.error("签退失败");
            result.addErrorMessage("系统签退失败");
        }
        return result;
    }
}
