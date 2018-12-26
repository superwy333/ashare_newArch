package com.zynsun.platform.edoc.facade.impl.edocClient;

import com.alibaba.fastjson.JSON;
import com.zynsun.openplatform.util.Base64Util;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.BillType;
import com.zynsun.platform.edoc.domain.BillTypeAttr;
import com.zynsun.platform.edoc.domain.BizType;
import com.zynsun.platform.edoc.dto.edocClient.ResponseHeader;
import com.zynsun.platform.edoc.dto.edocClient.baseConfig.request.BaseConfigRequest;
import com.zynsun.platform.edoc.dto.edocClient.baseConfig.response.*;
import com.zynsun.platform.edoc.dto.edocClient.extConfig.request.ExtConfigRequest;
import com.zynsun.platform.edoc.dto.edocClient.extConfig.response.ExtConfigResponse;
import com.zynsun.platform.edoc.dto.edocClient.extConfig.response.ExtConfigResponseBody;
import com.zynsun.platform.edoc.dto.edocClient.login.request.LoginCheckRequest;
import com.zynsun.platform.edoc.dto.edocClient.login.request.LoginCheckRequestBody;
import com.zynsun.platform.edoc.dto.edocClient.login.response.LoginCheckResponse;
import com.zynsun.platform.edoc.dto.edocClient.login.response.LoginCheckResponseBody;
import com.zynsun.platform.edoc.facade.edocClient.EdocClientBeforeUploadFacade;
import com.zynsun.platform.edoc.service.BillTypeAttrService;
import com.zynsun.platform.edoc.service.BillTypeService;
import com.zynsun.platform.edoc.service.BizTypeService;
import com.zynsun.platform.utils.EdocClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 客户端上传前服务实现类
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/05 14:37
 */
@Service(value = "edocClientBeforeUploadFacade")
public class EdocClientBeforeUploadFacadeImpl implements EdocClientBeforeUploadFacade {

    private static final Logger EDOC_CLIENT_LOGGER = LoggerFactory.getLogger(EdocClientBeforeUploadFacadeImpl.class);

    @Autowired
    private BizTypeService bizTypeService;
    @Autowired
    private BillTypeService billTypeService;
    /*@Autowired
    private AacUserCliFacade aacUserCliFacade;*/
    @Autowired
    private BillTypeAttrService billTypeAttrService;
    /*@Autowired
    private AacPrivilegeCliFacade aacPrivilegeCliFacade;*/

    @Override
    public ExecuteResult<LoginCheckResponse> login(LoginCheckRequest loginCheckRequest) {
        EDOC_CLIENT_LOGGER.info("login request: {}", JSON.toJSONString(loginCheckRequest));

        ExecuteResult<LoginCheckResponse> result = new ExecuteResult<>();
        LoginCheckResponse response = new LoginCheckResponse();
        try {
            // 验证客户端请求头信息
            response.setHeader(EdocClientUtil.getResponseIsClientHeaderNull(loginCheckRequest.getHeader(), false));
            if (!EdocClientUtil.isEmpty(response.getHeader())) {
                result.setResult(response);
                return result;
            }

            if (EdocClientUtil.isNotEmpty(loginCheckRequest.getBody())) {
                // 获取客户端请求参数
                LoginCheckRequestBody requestBody = loginCheckRequest.getBody();
                String loginName = requestBody.getLoginName();
                String password = EdocClientUtil.isEmpty(requestBody.getPassword()) ? "" : new String(Base64Util.decode(requestBody.getPassword()));

                // 登录信息验证
                //ExecuteResult<AacUserResultDTO> aacUserResultDTOExecuteResult = aacUserCliFacade.userLogin(loginName, password);
                //EDOC_CLIENT_LOGGER.info("[用户" + loginName + "] 登录接口返回信息：[{}]", JSON.toJSONString(aacUserResultDTOExecuteResult));

                // 登录验证成功
                /*if (aacUserResultDTOExecuteResult != null && aacUserResultDTOExecuteResult.getResult() != null) {
                    // 设置返回参数
                    LoginCheckResponseBody body = new LoginCheckResponseBody();
                    AacUserResultDTO login = aacUserResultDTOExecuteResult.getResult();
                    BeanUtils.copyProperties(login, body, new String[]{});
                    body.setUserNameCn(aacUserResultDTOExecuteResult.getResult().getLoginName());
                    body.setUserCode(aacUserResultDTOExecuteResult.getResult().getLoginName());
                    response.setBody(body);
                    response.setHeader(new ResponseHeader(aacUserResultDTOExecuteResult.getSuccessMessage(), true));
                // 登录验证失败
                } else {
                    if (aacUserResultDTOExecuteResult == null) {
                        response = EdocClientUtil.setResult("N", "登录验证服务异常", LoginCheckResponse.class);
                    } else {
                        List<String> list = aacUserResultDTOExecuteResult.getErrorMessages();
                        response = EdocClientUtil.setResult("N", (list != null && list.size() >= 1) ? list.get(0) : "用户不存在", LoginCheckResponse.class);
                    }
                }*/
                LoginCheckResponseBody body = new LoginCheckResponseBody();
                body.setUserNameCn(loginName);
                body.setUserCode(loginName);
                response.setBody(body);
                response.setHeader(new ResponseHeader("登录成功", true));

            } else {
                response = EdocClientUtil.setResult("N", "请求参数不能为空.", LoginCheckResponse.class);
            }
        } catch (Exception e) {
            response.setHeader(new ResponseHeader("系统异常", false));
            EDOC_CLIENT_LOGGER.error("LOGIN_EXCEPTION --->", e);
        }

        EDOC_CLIENT_LOGGER.info("login response: {}", JSON.toJSONString(response));
        result.setResult(response);
        return result;
    }

    @Override
    public ExecuteResult<BaseConfigResponse> loadConfig(BaseConfigRequest baseConfigRequest) {
        EDOC_CLIENT_LOGGER.info("loadConfig request: {}", JSON.toJSONString(baseConfigRequest));

        ExecuteResult<BaseConfigResponse> result = new ExecuteResult<>();
        BaseConfigResponse response = new BaseConfigResponse();
        BaseConfigResponseBody responseBody = new BaseConfigResponseBody();
        responseBody.setCategories(new BaseConfigResponseBodyCategories());
        responseBody.getCategories().setCategory(new ArrayList<>());

        try {
            // 验证客户端请求头信息
            response.setHeader(EdocClientUtil.getResponseIsClientHeaderNull(baseConfigRequest.getHeader(), false));
            if (!EdocClientUtil.isEmpty(response.getHeader())) {
                result.setResult(response);
                return result;
            }
            List<BizType> bizTypes = new ArrayList<>();
            Map<String,Object> authoritiesMaps = baseConfigRequest.getAuthoritiesMaps();
            if (!BeanUtil.isEmpty(authoritiesMaps)) {
                for (String key:authoritiesMaps.keySet()) {
                    if (key.contains("imageSet")) {
                        String[] keyStr = key.split("imageSet");
                        BizType bizType = bizTypeService.queryByCode(keyStr[1]);
                        if (!BeanUtil.isEmpty(bizType)) {
                            bizTypes.add(bizType);
                        }
                    }
                }
            }
            // 手工加入业务类型
            // TODO 客户端发布新版本之后去掉这个快逻辑
            BizType FY = bizTypeService.queryByCode("FY");
            BizType HT = bizTypeService.queryByCode("HT");
            BizType CG = bizTypeService.queryByCode("CG");
            BizType ZL = bizTypeService.queryByCode("ZL");
            bizTypes.add(FY);
            bizTypes.add(HT);
            bizTypes.add(CG);
            bizTypes.add(ZL);


            // 根据用户代码获取权限内业务配置
            //List<BizType> bizTypes = getBizTypesByUserCode(baseConfigRequest.getBody().getUserCode());
            // 组装返回业务信息
            responseBody.getCategories().setCategory(packageCategories(bizTypes));

            response.setHeader(new ResponseHeader("获取业务类型成功", true));
            response.setBody(responseBody);
        } catch (Exception e) {
            response.setHeader(new ResponseHeader("系统异常", false));
            EDOC_CLIENT_LOGGER.error("LOAD_CONFIG_EXCEPTION --->", e);
        }
        EDOC_CLIENT_LOGGER.info("loadConfig response: {}", JSON.toJSONString(response));
        result.setResult(response);
        return result;
    }

    @Override
    public ExecuteResult<ExtConfigResponse> loadExtConfig(ExtConfigRequest extConfigRequest) {
        EDOC_CLIENT_LOGGER.info("loadExtConfig request: {}", JSON.toJSONString(extConfigRequest));

        ExecuteResult<ExtConfigResponse> result = new ExecuteResult<>();
        ExtConfigResponse response = new ExtConfigResponse();
        ExtConfigResponseBody responseBody = new ExtConfigResponseBody();
        try {

            response.setHeader(EdocClientUtil.getResponseIsClientHeaderNull(extConfigRequest.getHeader(), true));
            if (!EdocClientUtil.isEmpty(response.getHeader())) {
                result.setResult(response);
                return result;
            }
            // TODO 获取扩展业务信息，暂略...
            response.setHeader(new ResponseHeader("获取扩展业务类型成功", true));
            response.setBody(responseBody);
        } catch (Exception e) {
            response.setHeader(new ResponseHeader("系统异常", false));
            EDOC_CLIENT_LOGGER.error("LOAD_EXT_CONFIG_EXCEPTION --->", e);
        }
        EDOC_CLIENT_LOGGER.info("loadExtConfig response: {}", JSON.toJSONString(response));
        result.setResult(response);
        return result;
    }

    /**
     * 根据用户代码查询对应业务类型的集合
     *
     * @param userCode
     * @return
     */
    /*private List<BizType> getBizTypesByUserCode(String userCode) {
        List<BizType> bizTypes = new ArrayList<>();
        ExecuteResult<List<AacDataPrivilegeResultDTO>> dptBiz = aacPrivilegeCliFacade.findAllByDpTypeAndUserCode("DPT_BIZTYPE", userCode);
        if (dptBiz.getResult() != null) {
            for (AacDataPrivilegeResultDTO aacDataPrivilegeResultDTO : dptBiz.getResult()) {
                BizType bizType = bizTypeService.queryByCode(aacDataPrivilegeResultDTO.getDpCode());
                bizTypes.add(bizType);
            }
        }
        return bizTypes;
    }*/

    /**
     * 根据业务类型组装
     * @return
     */
    private List<BaseConfigResponseBodyCategory> packageCategories(List<BizType> bizTypes) {
        Map<Long, BaseConfigResponseBodyCategory> bodyCategoryMap = new HashMap<>();
        Map<Long, BaseConfigResponseBodyCategory> topCategoryMap = new HashMap<>();
        List<BaseConfigResponseBodyCategory> categories = new ArrayList<>();
        Set<Long> bodyCategoryMapKeys = new HashSet<>();

        for (BizType bizType : bizTypes) {
            // 当前业务类型
            BaseConfigResponseBodyCategory bodyCategory = null;
            if (!bodyCategoryMapKeys.contains(bizType.getId())) {
                bodyCategoryMapKeys.add(bizType.getId());
                bodyCategory = new BaseConfigResponseBodyCategory(bizType.getCode(), bizType.getName());

                // 设置关联单证
                bodyCategory.setDocTypeRelation(getCategoryDocTypeRelation(bizType));
                // 设置业务扩展属性
                bodyCategory.setFields(getCategoryExtFields(bizType));
                bodyCategoryMap.put(bizType.getId(), bodyCategory);
            } else {
                bodyCategory = bodyCategoryMap.get(bizType.getId());
            }

            // 父业务类型
            if (null != bizType.getParentId() && 0 != bizType.getParentId()) {
                BaseConfigResponseBodyCategory parentBodyCategory = null;
                if (!bodyCategoryMapKeys.contains(bizType.getParentId())) {
                    bodyCategoryMapKeys.add(bizType.getParentId());
                    parentBodyCategory = new BaseConfigResponseBodyCategory();
                    bodyCategoryMap.put(bizType.getParentId(), parentBodyCategory);
                } else {
                    parentBodyCategory = bodyCategoryMap.get(bizType.getParentId());
                }
                if (EdocClientUtil.isEmpty(parentBodyCategory.getSubCategories())) {
                    BaseConfigResponseBodyCategories subCategories = new BaseConfigResponseBodyCategories();
                    subCategories.setCategory(new ArrayList<>());
                    parentBodyCategory.setSubCategories(subCategories);
                }

                // 添加在父业务的下面
                parentBodyCategory.getSubCategories().getCategory().add(bodyCategory);
            } else {
                // 最顶层业务类型
                topCategoryMap.put(bizType.getId(), bodyCategory);
            }
        }
        categories.addAll(topCategoryMap.values());
        return categories;
    }

    /**
     * 设置当前业务的扩展属性信息 TODO 暂略...
     *
     * @param bizType
     * @return
     */
    private BaseConfigResponseBodyCategoryFields getCategoryExtFields(BizType bizType) {
        return null;
    }

    /**
     * 获取当前业务的关联单证
     *
     * @param bizType
     * @return
     */
    private BaseConfigResponseBodyDocTypeRelation getCategoryDocTypeRelation(BizType bizType) {
        BaseConfigResponseBodyDocTypeRelation docTypeRelation = new BaseConfigResponseBodyDocTypeRelation();
        docTypeRelation.setDocType(new ArrayList<>());
        // 根据业务类型ID获取所有关联的单证
        List<BillType> billTypeList = billTypeService.findBillTypeByBizId(bizType.getId());
        if (EdocClientUtil.isNotEmpty(billTypeList)) {
            for (BillType billType : billTypeList) {
                BaseConfigResponseBodyDocType docType = new BaseConfigResponseBodyDocType();
                docType.setCode(billType.getCode());
                docType.setName(billType.getName());
                docType.setOcrType(billType.getOcrType());
                docType.setIsCover(billType.getIsCover());
                docType.setOcrArea(billType.getOcrArea());
                docType.setFields(findDocTypeFields(billType));
                docTypeRelation.getDocType().add(docType);
            }
        }
        return docTypeRelation;
    }

    /**
     * 获取单证的属性设置
     *
     * @param billType
     * @return
     */
    private BaseConfigResponseBodyDocTypeFields findDocTypeFields(BillType billType) {
        BaseConfigResponseBodyDocTypeFields docTypeFields = new BaseConfigResponseBodyDocTypeFields();
        docTypeFields.setField(new ArrayList<>());
        // 单证属性收集
        List<BillTypeAttr> billTypeAttrList = billTypeAttrService.findBillAttrByBillId(billType.getId());
        if (billTypeAttrList != null && billTypeAttrList.size() > 0) {
            for (BillTypeAttr billTypeAttr : billTypeAttrList) {
                BaseConfigResponseBodyDocTypeField field = new BaseConfigResponseBodyDocTypeField();
                field.setFieldName(billTypeAttr.getName());
                field.setFieldNameEn(billTypeAttr.getNameEn());
                field.setIsRequired(billTypeAttr.getIsNull());
                field.setFieldLength(String.valueOf(billTypeAttr.getLength()));
                field.setFieldType(billTypeAttr.getType());
                field.setValidRegex(billTypeAttr.getValidRegex());
                docTypeFields.getField().add(field);
            }
        }
        return docTypeFields;
    }
}
