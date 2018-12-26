package com.zynsun.platform.edoc.facade.impl.bizManage;

import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.BillTypeAttr;
import com.zynsun.platform.edoc.dto.bizType.BillTypeAttrDTO;
import com.zynsun.platform.edoc.facade.bizManage.BillTypeAttrFacade;
import com.zynsun.platform.edoc.service.BillTypeAttrService;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/13 10:16
 * @Modified By：
 */
@Service(value = "billTypeAttrFacade")
public class BillTypeAttrFacadeImpl implements BillTypeAttrFacade {

    private static final Logger BILLTYPE_ATTR_LOGGER = LoggerFactory.getLogger(BillTypeAttrFacadeImpl.class);

    @Resource
    private BillTypeAttrService billTypeAttrService;

    @Override
    public ExecuteResult<BillTypeAttrDTO> createBillTypeAttr(BillTypeAttrDTO billTypeAttrDTO) {
        ExecuteResult<BillTypeAttrDTO> billTypeAttrDTOExecuteResult = new ExecuteResult<>();
        try {
            Assert.notNull(billTypeAttrDTO,"保存的单证类型属性不能为空");
            BillTypeAttr billTypeAttr = DozerUtil.map(billTypeAttrDTO,BillTypeAttr.class);
            billTypeAttr.setCreateTime(new Date());
            int num = 0;
            if (billTypeAttrDTO.getId() == null) {//新增
                BillTypeAttr billTypeAttrQuery = billTypeAttrService.queryByCode(billTypeAttr.getCode());
                if (billTypeAttrQuery != null && billTypeAttrQuery.getId() != null) {
                    billTypeAttrDTOExecuteResult.setErrorMessages(Arrays.asList("保存单证类型属性失败!"));
                    return billTypeAttrDTOExecuteResult;
                } else{
                    num = billTypeAttrService.add(billTypeAttr);

                }
            } else{//修改
                num = billTypeAttrService.updateByIdSelective(billTypeAttr);
            }
            if (num > 0) {
                billTypeAttrDTO.setId(billTypeAttr.getId());
                billTypeAttrDTOExecuteResult.setResult(billTypeAttrDTO);
                billTypeAttrDTOExecuteResult.setSuccessMessage("保存单证类型属性成功!");
                return billTypeAttrDTOExecuteResult;
            }
            billTypeAttrDTOExecuteResult.setErrorMessages(Arrays.asList("保存单证类型属性失败!"));
            return billTypeAttrDTOExecuteResult;
        } catch (Exception e) {
            BILLTYPE_ATTR_LOGGER.error("对象转换失败,失败原因<{}>",e);
            billTypeAttrDTOExecuteResult.addErrorMessage("系统异常,请联系管理员!");
            return billTypeAttrDTOExecuteResult;
        }
    }

    @Override
    public ExecuteResult<BillTypeAttrDTO> findOneBillTypeAttr(String code) {
        ExecuteResult<BillTypeAttrDTO> executeResult = new ExecuteResult<BillTypeAttrDTO>();
        try {
            Assert.notNull(code,"code不能为空");
            BillTypeAttr bizType = billTypeAttrService.queryByCode(code);
            if(bizType != null){
                BillTypeAttrDTO resultDTO = DozerUtil.map(bizType,BillTypeAttrDTO.class);
                executeResult.setResult(resultDTO);
                executeResult.setSuccessMessage("查询单证类型属性成功.");
                return executeResult;
            }
            executeResult.addErrorMessage("无对应单证类型属性");
            return executeResult;
        } catch (Exception e) {
            BILLTYPE_ATTR_LOGGER.error("BillTypeAttrDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<List<BillTypeAttrDTO>> findAttrsByBillTypeId(Long billTypeId) {
        ExecuteResult<List<BillTypeAttrDTO>> executeResult = new ExecuteResult<List<BillTypeAttrDTO>>();
        try {
            Assert.notNull(billTypeId,"billTypeId不能为空");
            BillTypeAttr billTypeAttr = new BillTypeAttr();
            billTypeAttr.setBillTypeId(billTypeId);
            billTypeAttr.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<BillTypeAttr> billTypeAttrList = billTypeAttrService.query(billTypeAttr);
            List<BillTypeAttrDTO> resultDTO = new ArrayList<>();
            if(billTypeAttrList != null && !billTypeAttrList.isEmpty()){
                resultDTO = DozerUtil.mapList(billTypeAttrList,BillTypeAttrDTO.class);
            }
            executeResult.setResult(resultDTO);
            executeResult.setSuccessMessage("查询单证类型属性成功.");
            return executeResult;
        } catch (Exception e) {
            BILLTYPE_ATTR_LOGGER.error("BillTypeAttrDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<BillTypeAttrDTO> findAttrsById(Long id) {
        ExecuteResult<BillTypeAttrDTO> executeResult = new ExecuteResult<BillTypeAttrDTO>();
        try {
            Assert.notNull(id,"id不能为空");
            BillTypeAttr billTypeAttr = billTypeAttrService.queryById(id);
            if (billTypeAttr != null && billTypeAttr.getId() != null) {
                BillTypeAttrDTO billTypeAttrDTO = DozerUtil.map(billTypeAttr, BillTypeAttrDTO.class);
                executeResult.setResult(billTypeAttrDTO);
                executeResult.setSuccessMessage("查询单证类型属性成功.");
                return executeResult;
            }
            executeResult.addErrorMessage("无对应单证类型属性");
            return executeResult;
        } catch (Exception e) {
            BILLTYPE_ATTR_LOGGER.error("BillTypeAttrDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<String> removeAttr(Long id) {
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        try {
            Assert.notNull(id,"id不能为空");
            BillTypeAttr billTypeAttr = billTypeAttrService.queryById(id);
            int num = billTypeAttrService.remove(billTypeAttr);
            if (num > 0) {
                executeResult.setResult("删除单证类型属性成功");
                executeResult.setSuccessMessage("删除单证类型属性成功.");
                return executeResult;
            }
            executeResult.addErrorMessage("删除单证类型属性失败");
            return executeResult;
        } catch (Exception e) {
            BILLTYPE_ATTR_LOGGER.error("BillTypeAttrDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }
}
