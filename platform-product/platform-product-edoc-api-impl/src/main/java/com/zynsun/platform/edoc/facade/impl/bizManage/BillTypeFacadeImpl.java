package com.zynsun.platform.edoc.facade.impl.bizManage;

import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.openplatform.util.PlatformBeanUtils;
import com.zynsun.platform.edoc.domain.BillType;
import com.zynsun.platform.edoc.domain.BizTypeStyle;
import com.zynsun.platform.edoc.dto.bizType.BillTypeDTO;
import com.zynsun.platform.edoc.facade.bizManage.BillTypeFacade;
import com.zynsun.platform.edoc.model.BillTypeModel;
import com.zynsun.platform.edoc.model.BillTypeModelDTO;
import com.zynsun.platform.edoc.service.BillTypeService;
import com.zynsun.platform.edoc.service.BizTypeStyleService;
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
 * Created by Administrator on 2017/6/1 0001.
 */
@Service(value = "billTypeFacade")
public class BillTypeFacadeImpl implements BillTypeFacade {

    private static final Logger BILLTYPE_LOGGER = LoggerFactory.getLogger(BillTypeFacadeImpl.class);

    @Resource
    private BillTypeService billTypeService;
    @Resource
    private BizTypeStyleService bizTypeStyleService;

    /**
     * @Description：查询所有有效的单证类型信息
     * @Author：FeiyueYang
     * @Param：
     * @Date：2017/6/1 0001 下午 5:56
     */
    @Override
    public ExecuteResult<List<BillTypeDTO>> findBillTypes(BillTypeDTO dto){

        ExecuteResult<List<BillTypeDTO>> result = new ExecuteResult<List<BillTypeDTO>>();
        try {
            BillType billType = new BillType();
            if(dto != null){
                PlatformBeanUtils.copyProperties(billType, dto);
            }
            billType.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<BillType> billTypeList = billTypeService.query(billType);
            result.setSuccessMessage("查询所有有效的单证类型信息成功");
            BILLTYPE_LOGGER.info("查询所有有效的单证类型信息成功");
            result.setResult(DozerUtil.mapList(billTypeList,BillTypeDTO.class));
        }catch (Exception e){
            result.addErrorMessage("查询所有有效的单证类型信息出错");
            BILLTYPE_LOGGER.error("查询所有有效的单证类型信息出错", e);
        }
        return result;
    }

    /**
     * @Description：保存单证类型信息
     * @Author：FeiyueYang
     * @Param：dto
     * @Date：2017/6/3 0003 下午 2:56
     */
    @Override
    public ExecuteResult<Boolean> saveBillType(BillTypeDTO dto){

        ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
        try {
            BillType billType = new BillType();
            if(dto != null){
                PlatformBeanUtils.copyProperties(billType, dto);
            }

            billType.setDeleted(Constant.DeleteFlag.DEL_NO);
            int i = billTypeService.createBillType(billType);
            if (i > 0){
                result.setSuccessMessage("保存单证类型信息成功");
                BILLTYPE_LOGGER.info("保存单证类型信息成功");
                result.setResult(true);
            }else {
                result.addErrorMessage("保存单证类型信息失败");
                BILLTYPE_LOGGER.info("保存单证类型信息失败");
                result.setResult(false);
            }
        }catch (Exception e){
            result.addErrorMessage("保存单证类型信息出错");
            BILLTYPE_LOGGER.error("保存单证类型信息出错", e);
        }
        return result;
    }

    /**
     * @Description：根据id查询单证类型信息
     * @Author：FeiyueYang
     * @Param：id
     * @Date：2017/6/3 0003 下午 8:10
     */
    @Override
    public ExecuteResult<BillTypeModelDTO> findBillTypeById(Long id) {
        ExecuteResult<BillTypeModelDTO> result = new ExecuteResult<BillTypeModelDTO>();
        try {
            Assert.notNull(id, "id不能为空");
            BillTypeModel billTypeModel = billTypeService.queryBillTypeModelById(id);
            BillTypeModelDTO billTypeModelDTO = DozerUtil.map(billTypeModel, BillTypeModelDTO.class);
            result.setSuccessMessage("查询单证类型信息成功");
            BILLTYPE_LOGGER.info("查询单证类型信息成功");
            result.setResult(billTypeModelDTO);
            return result;
        }catch (Exception e){
            result.addErrorMessage("查询单证类型信息出错");
            BILLTYPE_LOGGER.error("查询单证类型信息出错", e);
            return result;
        }
    }

    /**
     * @Description：删除单证信息
     * @Author：FeiyueYang
     * @Param：id
     * @Date：2017/6/3 0003 下午 8:37
     */
    @Override
    public ExecuteResult<String> removeBillType(Long id) {
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        try {
            Assert.notNull(id, "id不能为空");

            BillType billType = this.billTypeService.queryById(id);

            //是否被业务类型绑定
            BizTypeStyle bizTypeStyle = new BizTypeStyle();
            bizTypeStyle.setBillTypeId(id);
            bizTypeStyle.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<BizTypeStyle> bizTypeStyleList = bizTypeStyleService.query(bizTypeStyle);
            if (bizTypeStyleList.size() > 0){
                executeResult.addErrorMessage("该单证类型被业务绑定,无法直接删除,须解除绑定,才能删除该单证类型");
                return executeResult;
            }


            int result = billTypeService.remove(billType);
            if (result == 1) {
                executeResult.setSuccessMessage("单证类型删除成功.");
                executeResult.setResult("单证类型源删除成功.");
                return executeResult;
            }
            executeResult.setErrorMessages(Arrays.asList("单证类型删除失败."));
            executeResult.setResult("单证类型删除失败.");
            return executeResult;
        }catch (Exception e){
            BILLTYPE_LOGGER.error("删除失败,失败原因<{}>",e);
            executeResult.addErrorMessage("系统异常,请联系管理员.");
            return executeResult;
        }
    }

    @Override
    public ExecuteResult<BillTypeDTO> findOneBillType(String billTypeCode) {
        ExecuteResult<BillTypeDTO> executeResult = new ExecuteResult<BillTypeDTO>();
        try {
            Assert.notNull(billTypeCode,"billTypeCode不能为空");
            BillType bizType = billTypeService.queryByCode(billTypeCode);
            if(bizType != null){
                BillTypeDTO resultDTO = DozerUtil.map(bizType,BillTypeDTO.class);
                executeResult.setResult(resultDTO);
                executeResult.setSuccessMessage("查询单证类型成功.");
                return executeResult;
            }
            executeResult.addErrorMessage("无对应单证类型");
            return executeResult;
        } catch (Exception e) {
            BILLTYPE_LOGGER.error("BillTypeDTO - 对象转换失败.",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }

    public ExecuteResult<Long> createBillType(BillTypeDTO billTypeDTO){
        ExecuteResult<Long> billTypeDTOExecuteResult = new ExecuteResult<>();
        try {
            Assert.notNull(billTypeDTO,"新增的单证类型不能为空");
            BillType billType = DozerUtil.map(billTypeDTO,BillType.class);
            billType.setCreateTime(new Date());
            int num = 0;
            if (billTypeDTO.getId() != null) {//修改
                num = billTypeService.updateByIdSelective(billType);
                billTypeDTOExecuteResult.setSuccessMessage("保存单证类型成功!");
            } else{//新增
                BillType billTypeQuery = billTypeService.queryByCode(billType.getCode());
                if (billTypeQuery == null || billTypeQuery.getId() == null) {
                    num = billTypeService.add(billType);
                    billTypeDTOExecuteResult.setSuccessMessage("新增单证类型成功!");
                }
            }
            if (num > 0) {
                billTypeDTOExecuteResult.setResult(billType.getId());
                return billTypeDTOExecuteResult;
            }
            billTypeDTOExecuteResult.setErrorMessages(Arrays.asList("保存单证类型失败!"));
            return billTypeDTOExecuteResult;
        } catch (Exception e) {
            BILLTYPE_LOGGER.error("对象转换失败,失败原因<{}>",e);
            billTypeDTOExecuteResult.addErrorMessage("系统异常,请联系管理员!");
            return billTypeDTOExecuteResult;
        }
    }

    public ExecuteResult<Long> editBillType(BillTypeDTO billTypeDTO){
        ExecuteResult<Long> billTypeDTOExecuteResult = new ExecuteResult<>();
        try {
            Assert.notNull(billTypeDTO,"修改的单证类型不能为空");
            BillType billType = DozerUtil.map(billTypeDTO,BillType.class);
            billType.setCreateTime(new Date());
            int num = billTypeService.updateByIdSelective(billType);
            if (num > 0) {
                billTypeDTOExecuteResult.setSuccessMessage("修改单证类型成功!");
                billTypeDTOExecuteResult.setResult(billType.getId());
                return billTypeDTOExecuteResult;
            }
            billTypeDTOExecuteResult.setErrorMessages(Arrays.asList("修改单证类型失败!"));
            return billTypeDTOExecuteResult;
        } catch (Exception e) {
            BILLTYPE_LOGGER.error("对象转换失败,失败原因<{}>",e);
            billTypeDTOExecuteResult.addErrorMessage("系统异常,请联系管理员!");
            return billTypeDTOExecuteResult;
        }
    }

    @Override
    public ExecuteResult<List<BillTypeDTO>> findBillTypesByBizCode(String bizTypeCode) {
        ExecuteResult<List<BillTypeDTO>> executeResult = new ExecuteResult<>();
        List<BillTypeDTO> billTypeDTOList = new ArrayList<>();
        try {
            Assert.notNull(bizTypeCode,"业务类型编码不能为空");
            List<BillType> billTypeList = billTypeService.queryBillTypesByBizCode(bizTypeCode);
            if (billTypeList != null && !billTypeList.isEmpty()) {
                billTypeDTOList = DozerUtil.mapList(billTypeList, BillTypeDTO.class);
            }
            executeResult.setResult(billTypeDTOList);
            return executeResult;
        } catch (Exception e) {
            BILLTYPE_LOGGER.error("对象转换失败,失败原因<{}>",e);
            executeResult.addErrorMessage("系统异常,请联系管理员!");
            return executeResult;
        }
    }


}
