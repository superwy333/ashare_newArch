package com.zynsun.platform.edoc.facade.impl.bizManage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.openplatform.util.PlatformBeanUtils;
import com.zynsun.platform.edoc.domain.BillType;
import com.zynsun.platform.edoc.domain.BizType;
import com.zynsun.platform.edoc.domain.BizTypeStyle;
import com.zynsun.platform.edoc.dto.bizType.BizTypeDTO;
import com.zynsun.platform.edoc.facade.bizManage.BizTypeFacade;
import com.zynsun.platform.edoc.model.BizTypeModel;
import com.zynsun.platform.edoc.model.BizTypeModelDTO;
import com.zynsun.platform.edoc.service.BizTypeService;
import com.zynsun.platform.edoc.service.BizTypeStyleService;
import com.zynsun.platform.edoc.service.FormAreaService;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service(value = "bizTypeFacade")
public class BizTypeFacadeImpl implements BizTypeFacade {

    private static final Logger BIZTYPE_LOGGER = LoggerFactory.getLogger(BizTypeFacadeImpl.class);

    @Resource
    private BizTypeService bizTypeService;
    @Resource
    private BizTypeStyleService bizTypeStyleService;
    @Resource
    private FormAreaService formAreaService;

    /**
     * @Description：查询所有有效的业务类型信息
     * @Author：FeiyueYang
     * @Param：
     * @Date：2017/6/1 0001 下午 5:47
     */
    @Override
    public ExecuteResult<List<BizTypeDTO>> findBizTypes(BizTypeDTO dto) {

        ExecuteResult<List<BizTypeDTO>> result = new ExecuteResult<List<BizTypeDTO>>();
        try{
            BizType bizType = new BizType();
            if(dto != null){
                PlatformBeanUtils.copyProperties(bizType, dto);
            }
            bizType.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<BizType> bizTypeList = bizTypeService.query(bizType);
            result.setSuccessMessage("查询所有有效的业务类型信息成功");
            BIZTYPE_LOGGER.info("查询所有有效的业务类型信息成功");
            result.setResult(DozerUtil.mapList(bizTypeList,BizTypeDTO.class));
        }catch (Exception e){
            result.addErrorMessage("查询所有有效的业务类型信息出错");
            BIZTYPE_LOGGER.error("查询所有有效的业务类型信息出错", e);
        }
        return result;
    }

    /**
     * @Description：根据id查询对应的业务类型信息
     * @Author：FeiyueYang
     * @Param：id
     * @Date：2017/6/1 0001 下午 5:48
     */
//    @Override
//    public ExecuteResult<BizType> queryById(Long id) {
//
//        ExecuteResult<BizType> result = new ExecuteResult<BizType>();
//        try{
//            if (id != null){
//                BizType bizType = bizTypeService.queryById(id);
//                result.setSuccessMessage("根据id查询对应的业务类型信息成功");
//                BIZTYPE_LOGGER.info("根据id查询对应的业务类型信息成功");
//                result.setResult(bizType);
//            }else {
//                result.setResult(new BizType());
//                result.addErrorMessage("id为空，无法查到对应信息");
//            }
//        }catch (Exception e){
//            result.addErrorMessage("根据id查询对应的业务类型信息出错");
//            BIZTYPE_LOGGER.error("根据id查询对应的业务类型信息出错", e);
//        }
//        return result;
//    }

    @Override
    public ExecuteResult<String> createBizType(BizTypeDTO bizTypeDTO) {
        ExecuteResult<String> createBizTypeExecuteResult = new ExecuteResult<String>();
        try {
            Assert.notNull(bizTypeDTO,"新增的业务类型不能为空");
            //关联的单证类型ids
            String billTypeIds = bizTypeDTO.getBillTypeParentIds();
            BizType bizType = DozerUtil.map(bizTypeDTO,BizType.class);
            bizType.setCreateTime(new Date());
            //新增关联的单证类型
            if (!StringUtils.isEmpty(billTypeIds)) {
                String[] billTypeIdArr = billTypeIds.split(";");
                if (billTypeIdArr != null && billTypeIdArr.length > 0) {
                    List<BizTypeStyle> bizTypeStyleList = new ArrayList<>();
                    for (int i=0; i<billTypeIdArr.length; i++) {
                        BizTypeStyle bizTypeStyle = new BizTypeStyle();
                        bizTypeStyle.setBillTypeId(Long.valueOf(billTypeIdArr[i]));
                        bizTypeStyle.setBizTypeId(bizType.getId());
                        bizTypeStyle.setCreateBy(bizType.getCreateBy());
                        bizTypeStyle.setCreateTime(bizType.getCreateTime());
                        bizTypeStyleList.add(bizTypeStyle);
                    }
                    int num = bizTypeService.addBizAndBills(bizType, bizTypeStyleList);
                    if (num == 1) {
                        createBizTypeExecuteResult.setSuccessMessage("新增业务类型成功");
                        createBizTypeExecuteResult.setResult("新增业务类型成功");
                        return createBizTypeExecuteResult;
                    }
                }
            }
            createBizTypeExecuteResult.setErrorMessages(Arrays.asList("新增业务类型失败!"));
            return createBizTypeExecuteResult;
        } catch (Exception e) {
            BIZTYPE_LOGGER.error("对象转换失败,失败原因<{}>",e);
            createBizTypeExecuteResult.addErrorMessage("系统异常,请联系管理员!");
            return createBizTypeExecuteResult;
        }
    }

    @Override
    public ExecuteResult<BizTypeDTO> findOneBizType(String bizTypeCode) {
        ExecuteResult<BizTypeDTO> executeResult = new ExecuteResult<BizTypeDTO>();
        try {
            Assert.notNull(bizTypeCode,"bizTypeCode不能为空");
            BizType bizType = bizTypeService.queryByCode(bizTypeCode);
            if(bizType != null){
                BizTypeDTO resultDTO = DozerUtil.map(bizType,BizTypeDTO.class);
                executeResult.setResult(resultDTO);
                executeResult.setSuccessMessage("查询业务类型成功.");
                return executeResult;
            }
            executeResult.addErrorMessage("无对应业务类型");
            return executeResult;
        } catch (Exception e) {
            BIZTYPE_LOGGER.error("BizTypeDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<BizTypeDTO> findOneBizType(String bizTypeCode, Integer delete) {
        ExecuteResult<BizTypeDTO> executeResult = new ExecuteResult<BizTypeDTO>();
        try {
            Assert.notNull(bizTypeCode,"bizTypeCode不能为空");
            Assert.notNull(delete,"delete不能为空");
            BizType newBizType = new BizType();
            newBizType.setCode(bizTypeCode);
            newBizType.setDeleted(delete);
            BizType bizType = bizTypeService.queryOne(newBizType);
            if(bizType != null){
                BizTypeDTO resultDTO = DozerUtil.map(bizType,BizTypeDTO.class);
                executeResult.setResult(resultDTO);
                executeResult.setSuccessMessage("查询业务类型成功.");
                return executeResult;
            }
            executeResult.addErrorMessage("无对应业务类型");
            return executeResult;
        } catch (Exception e) {
            BIZTYPE_LOGGER.error("BizTypeDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<BizTypeModelDTO> queryBizTypeModelDTO(Long id) {
        ExecuteResult<BizTypeModelDTO> executeResult = new ExecuteResult<BizTypeModelDTO>();
        try {
            Assert.notNull(id,"id不能为空");
            BizTypeModel bizTypeModel = bizTypeService.queryBizTypeModelById(id);
            BizTypeModelDTO bizTypeModelDTO = DozerUtil.map(bizTypeModel, BizTypeModelDTO.class);
            if (!bizTypeModel.getBillTypeList().isEmpty()) {
                String billTypeIds = "";
                String billTypeNames = "";
                for (BillType billType : bizTypeModel.getBillTypeList()) {
                    billTypeIds = String.valueOf(billType.getId()) + ";" + billTypeIds;
                    billTypeNames = billType.getName() + ";" + billTypeNames;
                }
                bizTypeModelDTO.setBillTypeIds(billTypeIds);
                bizTypeModelDTO.setBillTypeNames(billTypeNames);
            }
            executeResult.setResult(bizTypeModelDTO);
            executeResult.setSuccessMessage("查询业务类型成功.");
            return executeResult;
        } catch (Exception e) {
            BIZTYPE_LOGGER.error("BizTypeModelDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    /**
     * 根据ID删除
     * @param bizTypeId
     * @return 删除成功or失败信息
     */
    @Override
    public ExecuteResult<String> removeBizType(Long bizTypeId){
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        try {
            Assert.notNull(bizTypeId, "bizTypeId不能为空");

            //是否存在子节点
            BizType bizType = new BizType();
            bizType.setParentId(bizTypeId);
            List<BizType>  bizTypeList = bizTypeService.query(bizType);
            if (bizTypeList.size() > 0){
                executeResult.addErrorMessage("存在子元素,故无法直接删除,须将子节点删除,才能删除该业务类型");
                return executeResult;
            }

            int result = bizTypeService.deleteBizAndBills(bizTypeId);
            if (result == 1) {
                executeResult.setSuccessMessage("业务类型删除成功.");
                executeResult.setResult("业务类型源删除成功.");
                return executeResult;
            }
            executeResult.setErrorMessages(Arrays.asList("业务类型删除失败."));
            executeResult.setResult("业务类型删除失败.");
            return executeResult;
        }catch (Exception e){
            BIZTYPE_LOGGER.error("删除失败,失败原因<{}>",e);
            executeResult.addErrorMessage("系统异常,请联系管理员.");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<String> modifiedBizType(BizTypeModelDTO bizTypeModelDTO) {
        ExecuteResult<String> modifiedBizTypeExecuteResult = new ExecuteResult<String>();
        try {
            Assert.notNull(bizTypeModelDTO,"修改的业务类型不能为空");
            //关联的单证类型ids
            String billTypeIds = bizTypeModelDTO.getBillTypeParentIds();
            BizType bizType = DozerUtil.map(bizTypeModelDTO,BizType.class);
            bizType.setLastModifyTime(new Date());
            //修改关联的单证类型
            if (!StringUtils.isEmpty(billTypeIds)) {
                String[] billTypeIdArr = billTypeIds.split(";");
                if (billTypeIdArr != null && billTypeIdArr.length > 0) {
                    List<BizTypeStyle> bizTypeStyleList = new ArrayList<>();
                    for (int i=0; i<billTypeIdArr.length; i++) {
                        BizTypeStyle bizTypeStyle = new BizTypeStyle();
                        bizTypeStyle.setBillTypeId(Long.valueOf(billTypeIdArr[i]));
                        bizTypeStyle.setBizTypeId(bizType.getId());
                        bizTypeStyle.setCreateBy(bizType.getCreateBy());
                        bizTypeStyle.setCreateTime(bizType.getCreateTime());
                        bizTypeStyleList.add(bizTypeStyle);
                    }
                    int num = bizTypeService.modifiedBizType(bizType, bizTypeStyleList);
                    if (num == 1) {
                        modifiedBizTypeExecuteResult.setSuccessMessage("修改业务类型成功");
                        modifiedBizTypeExecuteResult.setResult("修改业务类型成功");
                        return modifiedBizTypeExecuteResult;
                    }
                }
            }
            modifiedBizTypeExecuteResult.setErrorMessages(Arrays.asList("修改业务类型失败!"));
            return modifiedBizTypeExecuteResult;
        } catch (Exception e) {
            BIZTYPE_LOGGER.error("对象转换失败,失败原因<{}>",e);
            modifiedBizTypeExecuteResult.addErrorMessage("系统异常,请联系管理员!");
            return modifiedBizTypeExecuteResult;
        }
    }

    @Override
    public ExecuteResult<String> saveForm(String jsonStr) {
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        try{
            if(org.apache.commons.lang3.StringUtils.isBlank(jsonStr)){
                executeResult.addErrorMessage("入參不能为空");
                return executeResult;
            }
            JSONArray jsonArray = JSON.parseArray(jsonStr);
            executeResult = formAreaService.saveForm(jsonArray);
            return executeResult;
        }catch (JSONException e){
            BIZTYPE_LOGGER.error("json解析失败,失败原因<{}>",e);
            executeResult.addErrorMessage("入參解析失败");
            return executeResult;
        }

    }

    @Override
    public ExecuteResult<BizTypeModelDTO> selectFromTepByBizTypeCode(String code, Integer deleted) {
        ExecuteResult<BizTypeModelDTO> executeResult = new ExecuteResult<BizTypeModelDTO>();
        try{
            if(org.apache.commons.lang3.StringUtils.isBlank(code)){
                executeResult.addErrorMessage("code不能为空");
                return executeResult;
            }
            BizTypeModelDTO bizTypeModelDTO = bizTypeService.selectFromTepByBizTypeCode(code, deleted);
            executeResult.setResult(bizTypeModelDTO);
            return executeResult;
        }catch (JSONException e){
            BIZTYPE_LOGGER.error("查询selectFromTepByBizTypeCode失败,失败原因<{}>",e);
            executeResult.addErrorMessage("查询selectFromTepByBizTypeCode失败");
            return executeResult;
        }
    }
}
