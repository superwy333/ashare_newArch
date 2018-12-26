package com.zynsun.platform.edoc.facade.impl.scanTaskManage;


import com.alibaba.dubbo.common.utils.Assert;
import com.github.pagehelper.Page;
import com.zynsun.openplatform.exception.BusinessException;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.BillAccountInfo;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.dto.BillAccountModelDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.BillAccountFacade;
import com.zynsun.platform.edoc.model.BillAccountModel;
import com.zynsun.platform.edoc.service.BillAccountService;
import com.zynsun.platform.edoc.service.EdocHeaderService;
//import com.zynsun.platform.workflow.facade.WfProcessFacade;
import constant.Constant;
import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Administrator
 * @create 2017/12/27
 * @modify
 */
@Service("billAccountFacade")
public class BillAccountFacadeImpl implements BillAccountFacade {

    private static final Logger BILLACCOUNT_LOGGER = LoggerFactory.getLogger(BillAccountFacadeImpl.class);

    @Autowired
    private BillAccountService billAccountService;
//    @Autowired
//    private WfProcessFacade wProcessFacade;
    @Autowired
    private EdocHeaderService edocHeaderService;

/*
    @Compensable(confirmMethod = "confirmAddBillAccount", cancelMethod = "cancelAddBillAccount")
*/
    @Override
    public ExecuteResult<BillAccountModelDTO> addBillAccount(BillAccountModelDTO billAccountModelDTO) {
        ExecuteResult<BillAccountModelDTO> result = new ExecuteResult<>();
        /*ExecuteResult<SysConfDictItemDTO> accountBizType = dataDictCliFacade.findByEnNameAndItemCode("EDOC", "account");
        if (BeanUtil.isEmpty(accountBizType.getResult())) {
            result.addErrorMessage("没有对应业务数据类型");
            return result;
        }
        ExecuteResult<SysConfDictItemDTO> wfAccount = dataDictCliFacade.findByEnNameAndItemCode("EDOC_WORKFLOW", "account");
        if (BeanUtil.isEmpty(wfAccount)) {
            result.addErrorMessage("没有对应业务流程类型");
            return result;
        }
        billAccountModelDTO.setEdocType(accountBizType.getResult().getDicItemValue());*/
        billAccountModelDTO.setEdocIsMatched("0");
        billAccountModelDTO.setEdocTaskStatus(Constant.BizStatus.ON_UP_DATA);
        BillAccountModel billAccountInfo = new BillAccountModel();
        try {
            billAccountInfo = billAccountService.addBillAccount(DozerUtil.map(billAccountModelDTO, BillAccountModel.class));
        }catch (Exception e){
            BILLACCOUNT_LOGGER.error("[添加报账单] 添加报账单信息失败,失败原因为:<{}>", e);
            result.addErrorMessage("".equals(e.getMessage())?"添加报账单信息失败":e.getMessage());
            return result;
        }
        if (!BeanUtil.isEmpty(billAccountInfo)) {
            Map<String, Object> variables = new HashMap<>();
            variables.put("edocNo", billAccountModelDTO.getEdocNo());
            //variables.put("bizType", accountBizType.getResult().getDicItemValue());
            variables.put("accountId",billAccountInfo.getId());

            /*List<String> list = new ArrayList<>();
            list.add("test001");
            list.add("superuser");
            variables.put("assigneeList",list);*/

            //启动流程
//            wProcessFacade.startProcess(wfAccount.getResult().getDicItemValue(), String.valueOf(billAccountInfo.getEdocHeaderId()), variables).getResult();
            result.setResult(DozerUtil.map(billAccountInfo, BillAccountModelDTO.class));
        }
        return result;
    }

    @Override
    public ExecuteResult<String> editBillAccount(BillAccountModelDTO billAccountModelDTO) {
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        try {
            Assert.notNull(billAccountModelDTO,"参数不能为空");
            BillAccountModel billAccountModel = DozerUtil.map(billAccountModelDTO,BillAccountModel.class);
            billAccountModel.setLastModifyTime(new Date());
            Integer integer = this.billAccountService.editBillAccount(billAccountModel);

            if (integer == 1) {
                executeResult.setSuccessMessage("修改报账单信息成功");
                executeResult.setResult("修改报账单成功");
                return executeResult;
            }
        } catch (Exception e) {
            BILLACCOUNT_LOGGER.error("[修改报账单] 修改报账单信息失败,失败原因为:<{}>", e);
            executeResult.addErrorMessage("系统异常,请联系管理员");
            executeResult.setResult("系统异常,请联系管理员");
            return executeResult;
        }

        executeResult.setErrorMessages(Arrays.asList("[修改报账单] 修改报账单信息失败,请联系管理员"));
        executeResult.setResult("修改报账单失败.");
        return executeResult;
    }

    /**
     * 确认报账单创建方法 tcc阶段的confirm阶段,需要做等幂性判断
     * @param billAccountModelDTO
     */
    public void confirmAddBillAccount(BillAccountModelDTO billAccountModelDTO){
        BILLACCOUNT_LOGGER.info("报账单{}新增tcc事务确认阶段补偿处理",billAccountModelDTO.getEdocNo());
    }

    /**
     * 取消报账单创建方法 tcc阶段的cancel阶段,需要做等幂性判断
     * @param billAccountModelDTO
     */
    public void cancelAddBillAccount(BillAccountModelDTO billAccountModelDTO){
        /*ExecuteResult<SysConfDictItemDTO> accountBizType = dataDictCliFacade.findByEnNameAndItemCode("EDOC","account");
        if(!BeanUtil.isEmpty(accountBizType.getResult())){
            //获取影像任务
            EdocHeader edocHeader = edocHeaderService.queryEdocHeaderByEdocNo(
                    billAccountModelDTO.getEdocNo(), accountBizType.getResult().getDicItemValue());
            BILLACCOUNT_LOGGER.info("报账单{}新增tcc事务取消阶段补偿处理", billAccountModelDTO.getEdocNo());
            //等幂性判断
            if(!BeanUtil.isEmpty(edocHeader)){
                //回滚启动的流程
//                wProcessFacade.deleteProcessInstance(String.valueOf(edocHeader.getId()));
            }
            //回滚创建的影像任务
            billAccountService.delBilLAccount(DozerUtil.map(billAccountModelDTO,BillAccountModel.class));
            BILLACCOUNT_LOGGER.info("报账单{}新增tcc事务取消阶段补偿处理完成",billAccountModelDTO.getEdocNo());
        }else {
            throw new BusinessException("报账单"+billAccountModelDTO.getEdocNo()+"没有对应业务数据类型");
        }*/

    }

    @Override
    public ExecuteResult<BillAccountModelDTO> queryBillAccountByBusinessKey(String businessKey) {
        ExecuteResult<BillAccountModelDTO> result = new ExecuteResult<>();
        try{
            BillAccountInfo billAccountInfo = billAccountService.queryBillAccountByBusinessKey(businessKey);
            if(BeanUtil.isEmpty(billAccountInfo)){
                result.addErrorMessage("不存在该报账单");
            }
            result.setResult(DozerUtil.map(billAccountInfo,BillAccountModelDTO.class));
        }catch(Exception e){
            BILLACCOUNT_LOGGER.error("报账单查询失败",e);
            result.addErrorMessage("报账单查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<BillAccountModelDTO>> queryBillAccountPageList(BillAccountModelDTO billAccountModelDTO) {
        ExecuteResult<PageInfo<BillAccountModelDTO>> result=new ExecuteResult<>();
        BillAccountModel billAccountModel=DozerUtil.map(billAccountModelDTO,BillAccountModel.class);//转换实体查询对象
        Page<BillAccountModel> billAccountModelPageInfo=billAccountService.queryBillAccountPageList(billAccountModel);//调用service层查询分页数据
        //拷贝分页数据
        PageInfo<BillAccountModelDTO> billAccountModelDTOPageInfo=DozerUtil.mapPage(billAccountModelPageInfo.toPageInfo(),BillAccountModelDTO.class);
        result.setSuccessMessage("查询分页数据成功");//设置返回信息
        result.setResult(billAccountModelDTOPageInfo);
        return result;
    }



//    @Override
//    public ExecuteResult<List<ExpressDetailDTO>> queryBillAccountListByIds(List<Long> list) {
//        ExecuteResult<List<ExpressDetailDTO>> result = new ExecuteResult<>();
//        try{
//            List<BillAccountModel> billAccountModels = billAccountService.queryByIds(list);
//            if(billAccountModels!=null){
//                List<ExpressDetailDTO> expDetailDTOList = new ArrayList<>();
//                for (BillAccountModel billAccountModelDTO:billAccountModels){
//                    ExpressDetailDTO expDetailDTO = new ExpressDetailDTO();
//                    expDetailDTO.setBillNo(billAccountModelDTO.getEdocNo());
//                    expDetailDTO.setBillId(billAccountModelDTO.getId().toString());
//                    expDetailDTO.setBillType("2");
//                    expDetailDTO.setEdocSource("edoc");
//                    expDetailDTO.setBillInfo(JSON.toJSONString(billAccountModelDTO));
//                    expDetailDTOList.add(expDetailDTO);
//                }
//                result.setResult(expDetailDTOList);
//                result.setSuccessMessage("查询成功");
//            }
//        }catch (Exception e){
//            BILLACCOUNT_LOGGER.error("查询失败", e);
//            result.addErrorMessage("查询失败");
//        }
//        return result;
//    }

    @Override
    public ExecuteResult<BillAccountModelDTO> findBillAccountById(Long id) {
        ExecuteResult<BillAccountModelDTO> result=new ExecuteResult<>();
        BillAccountInfo billAccountInfo= (BillAccountInfo) billAccountService.queryById(id);
        if(!BeanUtil.isEmpty(billAccountInfo)){
            result.setSuccessMessage("查询成功");
            result.setResult(DozerUtil.map(billAccountInfo,BillAccountModelDTO.class));
        }else{
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<Long> getEdocHeaderIdByAccountId(Long id,Integer edocLevel) {
        ExecuteResult<Long> result = new ExecuteResult<>();
        try{
            Long edocHeaderId = edocHeaderService.selectEdocHeaderIdByBillId(id,edocLevel);
            if(edocHeaderId!=null){
                result.setResult(edocHeaderId);
                result.setSuccessMessage("查询成功");
            }
        }catch (Exception e){
            BILLACCOUNT_LOGGER.error("查询失败",e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

}
