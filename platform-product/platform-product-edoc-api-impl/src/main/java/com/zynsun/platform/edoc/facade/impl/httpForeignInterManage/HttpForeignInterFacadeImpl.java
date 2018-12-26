package com.zynsun.platform.edoc.facade.impl.httpForeignInterManage;

import com.zynsun.openplatform.util.*;
import com.zynsun.platform.edoc.annotation.MapUtil;
import com.zynsun.platform.edoc.domain.*;
import com.zynsun.platform.edoc.facade.httpForeignInterManage.HttpForeignInterFacade;
import com.zynsun.platform.edoc.facade.impl.scanTaskManage.AccountingArchivesBorrowFacadeImpl;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import com.zynsun.platform.edoc.model.EdocHeaderModel;
import com.zynsun.platform.edoc.model.EdocImageModel;
import com.zynsun.platform.edoc.model.InvoiceModel;
import com.zynsun.platform.edoc.model.ReviewInfoModel;
import com.zynsun.platform.edoc.service.*;
import com.zynsun.platform.edoc.vo.EvaluateVo;
import com.zynsun.platform.edoc.vo.InterReqVo;
import com.zynsun.platform.edoc.vo.InvoiceReqVo;
import com.zynsun.platform.edoc.vo.VoucherDetailsVo;
import com.zynsun.platform.edoc.webservice.BaseInter;
import com.zynsun.platform.utils.JsonUtil;
import constant.Constant;
import constant.ReviewInfoConstant;
import org.apache.commons.lang3.StringUtils;
import org.mortbay.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author jary
 * @creatTime 2018/11/29 3:36 PM
 */
@Path("httpForeignInter")
@Service("httpForeignInterFacade")
public class HttpForeignInterFacadeImpl implements BaseInter,HttpForeignInterFacade {
    private static final Logger LOGGER= LoggerFactory.getLogger(AccountingArchivesBorrowFacadeImpl.class);
    @Autowired EdocHeaderService edocHeaderService;
    @Autowired InvoiceService invoiceService;
    @Autowired InvoiceItemService invoiceItemService;
    @Autowired EdocHeaderFacade edocHeaderFacade;
    @Autowired ReviewInfoService reviewInfoService;
    @Autowired EdocCredentialsService edocCredentialsService;

    /*603*/
    @POST
    @Path("getTaskDetailsInter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public String getTaskDetailsInter(String req) {
        LOGGER.info("************************【开始】**************************");

        if (StringUtils.isEmpty(req)) return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));
        String configFlag = LoadConfig.get("inter.taskDetailsInte");
        String resp = null;
        List<EdocHeaderModel> respList = new ArrayList<>();
        EdocHeaderModel headerModel = new EdocHeaderModel();
        try {

            //参数解析
            @SuppressWarnings("unchecked")
            Map<String, Object> reqMap = (Map<String, Object>) JSON.parse(req);

            //接口调用标识验证
            String reqParamStr = MapUtil.getInterfaceFlag(reqMap).toString();
            Map<String, Object> validateInterfaceFlagMap = this.validateInterfaceFlag(reqParamStr, configFlag);
            if (!MapUtil.getBoolean(validateInterfaceFlagMap)) return JsonUtil.bean2JsonString(validateInterfaceFlagMap);

            List<InterReqVo> reqParamList = JsonUtil.jsonToList(JsonUtil.bean2JsonString(MapUtil.getReqParam(reqMap)), InterReqVo.class);
            if (BeanUtil.isEmpty(reqParamList)) return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));

            for (InterReqVo interReqVo : reqParamList) {

                String edocNo = interReqVo.getCoverNo();
                LOGGER.info("接口:「{}」，封面条码：<{}>,请求参数；<{}>", configFlag, edocNo, JsonUtil.bean2JsonString(interReqVo));
                EdocHeaderModel edocHeaderModel = new EdocHeaderModel();
                edocHeaderModel.setEdocNo(edocNo);

                List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.getBillImagesByBillHeaderId(edocHeaderModel);
                if (BeanUtil.isEmpty(edocHeaderModelList)) {
                    respList.add(new EdocHeaderModel(edocNo, Constant.getCode.fail, "任务信息不存在或已作废"));
                    continue;
                }

                headerModel = edocHeaderModelList.get(0);
                headerModel.setCode(Constant.getCode.success);

                List<EdocImageModel> edocImageList = headerModel.getEdocImageList();
                if (BeanUtil.isEmpty(edocImageList)) continue;

                for (EdocImageModel edocImageModel : edocImageList) {
                    Invoice invoice = invoiceService.queryInvoiceByImgId(edocImageModel.getImageId());
                    if (BeanUtil.isEmpty(invoice)) continue;

                    InvoiceModel invoiceModel = DozerUtil.map(invoice, InvoiceModel.class);
                    if (!BeanUtil.isEmpty(invoiceModel)) {
                        edocImageModel.setInvoiceModel(invoiceModel);
                        InvoiceItem invoiceItem = new InvoiceItem();
                        invoiceItem.setInvId(invoice.getId());
                        List<InvoiceItem> query = invoiceItemService.query(invoiceItem);
                        invoiceModel.setInvoiceItemList(query);
                    }
                }

                //搜集返回结果
                respList.add(headerModel);
            }

            resp = MapUtil.interResp(200, "调用成功", respList);
            LOGGER.info("************************【结束】**************************");
        } catch (Exception e) {
            LOGGER.info("接口：「{}」,系统异常:", configFlag, e);
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "系统异常", RESP_FAIL));
        }

        return resp;
    }

    /*604*/
    @POST
    @Path("getTaskEvaluateInter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public String getTaskEvaluateInter(String req) {
        LOGGER.info("************************【开始】**************************");

        if (StringUtils.isEmpty(req)) return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));
        String configFlag = LoadConfig.get("inter.taskEvaluateInter");
        String resp ;
        List<EdocHeaderModel> respList = new ArrayList<>();

        try {
            //参数解析
            @SuppressWarnings("unchecked")
            Map<String, Object> reqMap = (Map<String, Object>) JSON.parse(req);

            //接口调用标识验证
            String reqParamStr = MapUtil.getInterfaceFlag(reqMap).toString();
            Map<String, Object> validateInterfaceFlagMap = this.validateInterfaceFlag(reqParamStr, configFlag);
            if (!MapUtil.getBoolean(validateInterfaceFlagMap)) return JsonUtil.bean2JsonString(validateInterfaceFlagMap);

            List<EvaluateVo> reqParamList = JsonUtil.jsonToList(JsonUtil.bean2JsonString(MapUtil.getReqParam(reqMap)), EvaluateVo.class);
            if (BeanUtil.isEmpty(reqParamList)) return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));

            for (EvaluateVo evaluateVo : reqParamList) {
                LOGGER.info("接口:「{}」，封面条码：<{}>,请求参数；<{}>",configFlag, evaluateVo.getCoverNo(), JsonUtil.bean2JsonString(evaluateVo));
                //参数验证
                Map<String, Object> validateEvaluateMap = this.validateEvaluateInfo(evaluateVo);
                if (!MapUtil.getBoolean(validateEvaluateMap)) {
                    respList.add(new EdocHeaderModel(evaluateVo.getCoverNo(), Constant.getCode.fail, (String) MapUtil.getMsg(validateEvaluateMap)));
                    continue;
                }

                ReviewInfoModel reviewInfoReq = new ReviewInfoModel();
                reviewInfoReq.setEdocNo(evaluateVo.getCoverNo());
                reviewInfoReq.setReviewSource(evaluateVo.getReviewSource());
                reviewInfoReq.setTaskId(String.valueOf(evaluateVo.getTaskId()));
                reviewInfoReq.setReviewStatus(evaluateVo.getType());
                reviewInfoReq.setEdocTaskStatus(evaluateVo.getStatus());
                reviewInfoReq.setReviewCode(evaluateVo.getReviewCode());
                reviewInfoReq.setReviewMessage(evaluateVo.getReviewMessage());
                reviewInfoReq.setReviewTime(evaluateVo.getReviewTime());

                //更新
                Map<String, Object> scrapMap = this.reviewInfoService.updateScrapByTaskId(reviewInfoReq);
                if (!MapUtil.getBoolean(scrapMap)) {
                    respList.add(new EdocHeaderModel(evaluateVo.getCoverNo(), Constant.getCode.fail,(String) MapUtil.getMsg (validateEvaluateMap)));
                    continue;
                }

                //搜集返回结果
                respList.add(new EdocHeaderModel(evaluateVo.getCoverNo(), Constant.getCode.success));
            }

            resp = MapUtil.interResp(200, "调用成功", respList);
            LOGGER.info("************************【结束】**************************");
        } catch (Exception e) {
            LOGGER.info("接口：「{}」,系统异常:", configFlag, e);
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "系统异常", RESP_FAIL));
        }
        return resp;
    }

    private Map<String, Object> validateInterfaceFlag(String reqInterFlag, String configFlag) {
        if (StringUtils.isEmpty(reqInterFlag))
            return MapUtil.getResultMap("001", "接口调用标识为空，操作失败", RESP_FAIL);
        if (!reqInterFlag.equals(configFlag))
            return MapUtil.getResultMap("001", "接口调用标识传递错误，操作失败", RESP_FAIL);
        return MapUtil.getResultMap("200", "信息验证成功", RESP_SUCCESS);
    }
    private Map<String, Object> validateEvaluateInfo(EvaluateVo evaluateVo) throws Exception {
        if (StringUtils.isEmpty(evaluateVo.getCoverNo())) return MapUtil.getResultMap("001", "封面条码为空", RESP_FAIL);

        //审核状态校验
        if (StringUtils.isEmpty(evaluateVo.getStatus())) return MapUtil.getResultMap("001", "审核状态为空", RESP_FAIL);
        List<String> statusList = Arrays.asList(EdocLoadConfig.get("edocHeader.reviewList").split(","));
        if (!statusList.contains(evaluateVo.getStatus())) return MapUtil.getResultMap("001", "审核状态不存在", RESP_FAIL);

        //评价类型校验
        if (StringUtils.isEmpty(evaluateVo.getType())) return MapUtil.getResultMap("001", "评价类型为空",  RESP_FAIL);
        List<String> reviewTypeList =  Arrays.asList(EdocLoadConfig.get("reviewInfo.reviewTypeList").split(","));
        if (!reviewTypeList.contains(evaluateVo.getType())) return MapUtil.getResultMap("001", "评价类型不存在", RESP_FAIL);

        //评价来源校验
        if (StringUtils.isEmpty(evaluateVo.getReviewSource())) return MapUtil.getResultMap("001", "评价来源为空", RESP_FAIL);
        List<String> reviewSourceList =  Arrays.asList(EdocLoadConfig.get("reviewInfo.reviewSourceList").split(","));
        if (!reviewSourceList.contains(evaluateVo.getReviewSource())) return MapUtil.getResultMap("001", "评价来源不存在", RESP_FAIL);

        if (StringUtils.isEmpty(evaluateVo.getReviewCode())) return MapUtil.getResultMap("001", "操作人编号为空", RESP_FAIL);
        if (StringUtils.isEmpty(evaluateVo.getReviewMessage())) return MapUtil.getResultMap("001", "评价信息为空", RESP_FAIL);
        if (StringUtils.isEmpty(evaluateVo.getReviewTime())) return MapUtil.getResultMap("001", "评价时间为空", RESP_FAIL);

//        edocHeader =  edocHeaderService.findSectionByEdocNo(evaluateVo.getCoverNo());
        List<EdocHeaderModel> edocHeaderModels = edocHeaderService.queryEdocNoCheckRealResult(evaluateVo.getCoverNo());
        if (BeanUtil.isEmpty(edocHeaderModels)) return MapUtil.getResultMap("001", "任务为空或已作废", RESP_FAIL);
        EdocHeaderModel edocHeader = edocHeaderModels.get(0);
        if ((evaluateVo.getStatus().equals(Constant.Review.TASK_PASS) && !evaluateVo.getType().equals(ReviewInfoConstant.ReviewStatus.zero)))
            return MapUtil.getResultMap("001", "参数传递错误，任务状态为"+Constant.Review.TASK_PASS+"时，评价类型只能为0", RESP_FAIL);
        if (evaluateVo.getStatus().equals(Constant.Review.TASK_FAILED) && !evaluateVo.getType().equals(ReviewInfoConstant.ReviewStatus.zero))
            return MapUtil.getResultMap("001", "参数传递错误，任务状态为"+Constant.Review.TASK_FAILED+"时，评价类型只能为0", RESP_FAIL);
        evaluateVo.setTaskId(edocHeader.getId());
        //if (StringUtils.isEmpty(edocHeader.getSeal())) return MapUtil.getResultMap(map,"001", "审核状态为空，操作失败", false);
//        if (edocHeader.getSeal().equals(EdocHeaderConstant.EdocTaskStatus.TASK_AUDIT))
//            return MapUtil.getResultMap(map,"001", "任务审核中，操作失败", false);
        /*if (edocHeader.getSeal().equals(EdocHeaderConstant.EdocTaskStatus.TASK_PASS))
            return MapUtil.getResultMap(map,"001", "任务审核已通过，操作失败", false);*/
        if (StringUtils.isEmpty(edocHeader.getSeal())) return MapUtil.getResultMap("001", "锁定状态为空，操作失败", RESP_FAIL);
        if (edocHeader.getSeal().equals(Constant.Seal.SEALED))
            return MapUtil.getResultMap("001", "任务审核已封单，操作失败", RESP_FAIL);
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setDeleted(0);
        reviewInfo.setIsProcessed(ReviewInfoConstant.getProcess.unProcess);
        reviewInfo.setEdocHeaderId(String.valueOf(edocHeader.getId()));
        List<ReviewInfo> queryReviewInfoList = reviewInfoService.query(reviewInfo);
        if (!BeanUtil.isEmpty(queryReviewInfoList))
            return MapUtil.getResultMap("001", "有未处理完的评价信息，操作失败", RESP_FAIL);
        return MapUtil.getResultMap("200", "信息验证成功", RESP_SUCCESS);
    }

    /*605*/
    @POST
    @Path("getTaskListInter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public String getTaskListInter(String req) {
        LOGGER.info("************************【开始】**************************");

        if (StringUtils.isEmpty(req)) return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));
        String configFlag = LoadConfig.get("inter.taskListInter");
        String resp = null;
        List<EdocHeaderModel> respList = new ArrayList<>();
        try {
            //参数解析
            @SuppressWarnings("unchecked")
            Map<String, Object> reqMap = (Map<String, Object>) JSON.parse(req);

            //接口调用标识验证
            String reqParamStr = MapUtil.getInterfaceFlag(reqMap).toString();
            Map<String, Object> validateInterfaceFlagMap = this.validateInterfaceFlag(reqParamStr, configFlag);
            if (!MapUtil.getBoolean(validateInterfaceFlagMap))
                return JsonUtil.bean2JsonString(validateInterfaceFlagMap);

            List<InterReqVo> reqParamList = JsonUtil.jsonToList(JsonUtil.bean2JsonString(MapUtil.getReqParam(reqMap)), InterReqVo.class);
            if (BeanUtil.isEmpty(reqParamList))
                return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));

            for (InterReqVo interReqVo : reqParamList) {
                String edocNo = interReqVo.getCoverNo();
                LOGGER.info("接口:「{}」，封面条码：<{}>,请求参数；<{}>", configFlag, edocNo, JsonUtil.bean2JsonString(interReqVo));
                EdocHeaderModel edocHeaderModel = new EdocHeaderModel();
                edocHeaderModel.setEdocNo(edocNo);
                edocHeaderModel.setEdocTaskStatus(interReqVo.getEdocTaskStatus());
                List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.getBillImagesByBillHeaderId(edocHeaderModel);

                //搜集返回结果
                if (BeanUtil.isEmpty(edocHeaderModelList)) respList.add(new EdocHeaderModel(edocNo, Constant.getCode.fail));
                else respList.add(new EdocHeaderModel(edocNo, Constant.getCode.success));
            }

            resp = MapUtil.interResp(200, "调用成功", respList);
        } catch (Exception e) {
            LOGGER.info("接口：「{}」,系统异常:", configFlag, e);
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "系统异常", RESP_FAIL));
        }
        return resp;
    }

    /*606*/
    @POST
    @Path("getInvoiceDetailsInter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public String getInvoiceDetailsInter(String req) {
        LOGGER.info("************************【开始】**************************");
        LOGGER.info("接口:「{}」，请求参数；<{}>", LoadConfig.get("inter.invoiceDetailsInter"), req);
        if (StringUtils.isEmpty(req))
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));
        String configFlag = LoadConfig.get("inter.invoiceDetailsInter");
        String resp = null;
        try {
            //参数解析
            @SuppressWarnings("unchecked")
            Map<String, Object> reqMap = (Map<String, Object>) JSON.parse(req);

            //接口调用标识验证
            String reqParamStr =  MapUtil.getInterfaceFlag(reqMap).toString();
            Map<String, Object> validateInterfaceFlagMap = this.validateInterfaceFlag(reqParamStr, configFlag);
            if (!MapUtil.getBoolean(validateInterfaceFlagMap))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, MapUtil.getMsg(validateInterfaceFlagMap), RESP_FAIL));

            InvoiceReqVo invoiceReqVo = JsonUtil.json2Bean(req, InvoiceReqVo.class);
            Map<String, Object> reqParamInfoMap = this.validateReqParamInfo(invoiceReqVo);
            if (!MapUtil.getBoolean(reqParamInfoMap))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, MapUtil.getMsg(reqParamInfoMap), RESP_FAIL));

            InvoiceModel invoiceModel = new InvoiceModel();
            invoiceModel.setEdocHeaderId(invoiceReqVo.getTaskId());
            invoiceModel.setInvCode(invoiceReqVo.getInvCode());
            invoiceModel.setInvNo(invoiceReqVo.getInvNo());
            List<InvoiceModel> invoiceModelList = invoiceService.queryInvoicesNoPage(invoiceModel);
            if (BeanUtil.isEmpty(invoiceModelList))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, "未找到该发票信息", RESP_FAIL));

            invoiceModel = invoiceModelList.get(0);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvId(invoiceModel.getId());
            

            invoiceModel.setInvoiceItemList(invoiceItemService.query(invoiceItem));
            invoiceModel.setCode(Constant.getCode.success);

            resp = MapUtil.interResp(200, "调用成功", invoiceModelList.get(0));
            LOGGER.info("************************【结束】**************************");
        } catch (Exception e) {
            LOGGER.info("接口：「{}」,系统异常:", configFlag, e);
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "系统异常", RESP_FAIL));
        }
        return resp;
    }

    private Map<String, Object> validateReqParamInfo(InvoiceReqVo invoiceReqVo) {
        EdocHeader edocHeader;
        if (invoiceReqVo.getInterfaceFlag().equals(LoadConfig.get("inter.invoiceListInter"))) {
            if (StringUtils.isEmpty(invoiceReqVo.getInvDateBegin()) && !StringUtils.isEmpty(invoiceReqVo.getInvDateEnd()))
                return MapUtil.getResultMap("001", "发票开始日期为空，操作失败", RESP_FAIL);
            if (!StringUtils.isEmpty(invoiceReqVo.getInvDateBegin()) && StringUtils.isEmpty(invoiceReqVo.getInvDateEnd()))
                return MapUtil.getResultMap("001", "发票结束日期为空，操作失败", RESP_FAIL);
            if (StringUtils.isEmpty(invoiceReqVo.getScanDateBegin()) && !StringUtils.isEmpty(invoiceReqVo.getScanDateEnd()))
                return MapUtil.getResultMap("001", "扫描开始日期为空，操作失败", RESP_FAIL);
            if (!StringUtils.isEmpty(invoiceReqVo.getScanDateBegin()) && StringUtils.isEmpty(invoiceReqVo.getScanDateEnd()))
                return MapUtil.getResultMap("001", "扫描结束日期为空，操作失败", RESP_FAIL);
//            if (StringUtils.isEmpty(invoiceReqVo.getSalerTaxCode())) return MapUtil.getResultMap("001", "销方税号为空，操作失败", RESP_FAIL);
        } else if (invoiceReqVo.getInterfaceFlag().equals(LoadConfig.get("inter.invoiceDetailsInter"))) {
            if (StringUtils.isEmpty(invoiceReqVo.getCoverNo()))
                return MapUtil.getResultMap("001", "封面条码为空，操作失败", RESP_FAIL);
            edocHeader = edocHeaderService.findSectionByEdocNo(invoiceReqVo.getCoverNo());

            if (BeanUtil.isEmpty(edocHeader)) return MapUtil.getResultMap("001", "任务为空或已作废", RESP_FAIL);
            invoiceReqVo.setTaskId(edocHeader.getId());

            if (StringUtils.isEmpty(invoiceReqVo.getInvCode()))
                return MapUtil.getResultMap("001", "发票代码为空，操作失败", RESP_FAIL);
            if (StringUtils.isEmpty(invoiceReqVo.getInvNo()))
                return MapUtil.getResultMap("001", "发票号码为空，操作失败", RESP_FAIL);
        }else {
            return MapUtil.getResultMap("001", "接口调用标识传递错误，操作失败", RESP_FAIL);
        }
        return MapUtil.getResultMap("200", "信息验证成功", RESP_SUCCESS);
    }


    /*607*/
    @POST
    @Path("getInvoiceListInter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public String getInvoiceListInter(String req) {
        LOGGER.info("************************【开始】**************************");
        String configFlag = LoadConfig.get("inter.invoiceListInter");
        String resp = null;
        try {
            InvoiceReqVo invoiceReqVo = JsonUtil.json2Bean(req, InvoiceReqVo.class);

            //接口调用标识验证
            LOGGER.info("接口:「{}」，请求参数；<{}>", LoadConfig.get("inter.invoiceListInter"), req);
            if (StringUtils.isEmpty(req)) return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));

            Map<String, Object> validateInterfaceFlagMap = this.validateInterfaceFlag(invoiceReqVo.getInterfaceFlag(), configFlag);
            if (!MapUtil.getBoolean(validateInterfaceFlagMap))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, MapUtil.getMsg(validateInterfaceFlagMap), RESP_FAIL));

            Map<String, Object> reqParamInfoMap = this.validateReqParamInfo(invoiceReqVo);
            if (!MapUtil.getBoolean(reqParamInfoMap))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, MapUtil.getMsg(reqParamInfoMap), RESP_FAIL));

            InvoiceModel invoiceModel = new InvoiceModel();
            invoiceModel.setInvDateBegin(invoiceReqVo.getInvDateBegin());
            invoiceModel.setInvDateEnd1(invoiceReqVo.getInvDateEnd());
            invoiceModel.setScanDateBegin(invoiceReqVo.getScanDateBegin());
            invoiceModel.setScanDateEnd(invoiceReqVo.getScanDateEnd());
            invoiceModel.setSalerTaxCode(invoiceReqVo.getSalerTaxCode());
            invoiceModel.setScanDateBegin(invoiceReqVo.getScanDateBegin());
            List<InvoiceModel> invoiceModelList = invoiceService.queryInvoicesNoPage(invoiceModel);
            if (BeanUtil.isEmpty(invoiceModelList))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, "未找到该发票信息", RESP_FAIL));
            resp = MapUtil.interResp(200, "调用成功", invoiceModelList);
            LOGGER.info("************************【结束】**************************");
        } catch (Exception e) {
            LOGGER.info("接口：「{}」,系统异常:", configFlag, e);
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "系统异常", RESP_FAIL));
        }
        return resp;
    }

    /*608*/
    @POST
    @Path("updateVoucherDetailsInter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public String updateVoucherDetailsInter(String req) {
        LOGGER.info("************************【开始】**************************");

        if (StringUtils.isEmpty(req))
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));
        String configFlag = LoadConfig.get("inter.toucherDetailsInter");
        String resp = null;
        List<EdocHeaderModel> respList = new ArrayList<>();
        try {
            //参数解析
            @SuppressWarnings("unchecked")
            Map<String, Object> reqMap = (Map<String, Object>) JSON.parse(req);
            String reqParamStr = MapUtil.getInterfaceFlag(reqMap).toString();

            //接口调用标识验证
            Map<String, Object> validateInterfaceFlagMap = this.validateInterfaceFlag(reqParamStr, configFlag);
            if (!MapUtil.getBoolean(validateInterfaceFlagMap)) return JsonUtil.bean2JsonString(validateInterfaceFlagMap);

            List<VoucherDetailsVo> reqParamList = JsonUtil.jsonToList(JsonUtil.bean2JsonString(MapUtil.getReqParam(reqMap)), VoucherDetailsVo.class);
            if (BeanUtil.isEmpty(reqParamList)) return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));

            for (VoucherDetailsVo voucherDetailsVoItem : reqParamList) {
                LOGGER.info("接口:「{}」，封面条码：<{}>,回写状态标识：<{}>,请求参数；<{}>", LoadConfig.get("inter.toucherDetailsInter"), voucherDetailsVoItem.getCoverNo(),voucherDetailsVoItem.getWriteBackStatus(), JsonUtil.bean2JsonString(voucherDetailsVoItem));

                //参数校验
                if (StringUtils.isEmpty(voucherDetailsVoItem.getCoverNo())) {
                    respList.add(new EdocHeaderModel(voucherDetailsVoItem.getCoverNo(), Constant.getCode.fail, "封面条码为空"));
                    continue;
                }
                if (StringUtils.isEmpty(voucherDetailsVoItem.getWriteBackStatus())) {
                    respList.add(new EdocHeaderModel(voucherDetailsVoItem.getCoverNo(), Constant.getCode.fail, "回写状态标识为空"));
                    continue;
                }
                Map<String, Object> reqParamInfo = this.validateReqParamInfo(voucherDetailsVoItem);
                if (!MapUtil.getBoolean(reqParamInfo)) {
                    respList.add(new EdocHeaderModel(voucherDetailsVoItem.getCoverNo(), Constant.getCode.fail, (String) MapUtil.getMsg(reqParamInfo)));
                    continue;
                }

                //凭证唯一性校验
                Map<String, Object> voucherDetailsMap = this.validateVoucherDetailsInfo(voucherDetailsVoItem);
                if (!MapUtil.getBoolean(voucherDetailsMap)) {
                    respList.add(new EdocHeaderModel(voucherDetailsVoItem.getCoverNo(), Constant.getCode.fail, (String) MapUtil.getMsg(voucherDetailsMap)));
                    continue;
                }

                //更新
                this.edocHeaderService.updateVoucherDetails(voucherDetailsVoItem);
                LOGGER.info("接口:「{}」，封面条码：<{}>,接收成功。", LoadConfig.get("inter.toucherDetailsInter"), voucherDetailsVoItem.getCoverNo());

                //搜集返回结果
                respList.add(new EdocHeaderModel(voucherDetailsVoItem.getCoverNo(), Constant.getCode.success));
            }

            resp = MapUtil.interResp(200, "调用成功", respList);
            LOGGER.info("************************【结束】**************************");
        } catch (Exception e) {
            LOGGER.info("接口：「{}」,系统异常:", configFlag, e);
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "系统异常", RESP_FAIL));
        }
        return resp;
    }

    private Map<String, Object> validateReqParamInfo(VoucherDetailsVo voucherDetailsVo) throws Exception{

//        if (StringUtils.isEmpty(voucherDetailsVo.getCoverNo())) return MapUtil.getResultMap("001", "封面条码为空", RESP_FAIL);
        if (StringUtils.isEmpty(voucherDetailsVo.getCredentialsNum())) return MapUtil.getResultMap("001", "凭证号为空", RESP_FAIL);
//        if (StringUtils.isEmpty(voucherDetailsVo.getErpId())) return MapUtil.getResultMap("001", "erpId为空", RESP_FAIL);
//        if (StringUtils.isEmpty(voucherDetailsVo.getType())) return MapUtil.getResultMap("001", "凭证类型为空", RESP_FAIL);
//        if (StringUtils.isEmpty(voucherDetailsVo.getStatus())) return MapUtil.getResultMap("001", "凭证状态为空", RESP_FAIL);
        if (StringUtils.isEmpty(voucherDetailsVo.getAccountingLedger())) return MapUtil.getResultMap("001", "会计帐套为空", RESP_FAIL);
//        if (StringUtils.isEmpty(voucherDetailsVo.getCreateDate())) return MapUtil.getResultMap("001", "凭证开单日期为空", RESP_FAIL);
        if (!StringUtils.isEmpty(voucherDetailsVo.getType())){
            //凭证类型校验
            List<String> credentialTypeList = Arrays.asList(EdocLoadConfig.get("credential.TypeList").split(","));
            if (!credentialTypeList.contains(voucherDetailsVo.getType())) return MapUtil.getResultMap("001", "凭证类型不存在", RESP_FAIL);
        }
        if (!StringUtils.isEmpty(voucherDetailsVo.getType())){
            //凭证状态校验
            List<String> credentialStatusList = Arrays.asList(EdocLoadConfig.get("credential.StatusList").split(","));
            if (!credentialStatusList.contains(voucherDetailsVo.getType())) return MapUtil.getResultMap("001", "凭证状态不存在", RESP_FAIL);
        }

        return MapUtil.getResultMap("200", "信息验证成功", RESP_SUCCESS);
    }

    private Map<String, Object> validateVoucherDetailsInfo(VoucherDetailsVo voucherDetailsVo){
        EdocHeader edocHeader;
        edocHeader =  edocHeaderService.findSectionByEdocNo(voucherDetailsVo.getCoverNo());
        if (BeanUtil.isEmpty(edocHeader)) return MapUtil.getResultMap("001", "任务为空或已作废", RESP_FAIL);
        voucherDetailsVo.setTaskId(edocHeader.getId());

        if (StringUtils.isEmpty(edocHeader.getSeal())) return MapUtil.getResultMap("001", "锁定状态为空，操作失败", RESP_FAIL);
        if (edocHeader.getSeal().equals(Constant.Seal.SEALED))
            return MapUtil.getResultMap("001", "任务审核已封单，操作失败", RESP_FAIL);

        EdocCredentials edocCredentials = new EdocCredentials();
        edocCredentials.setAccountinLedger(voucherDetailsVo.getAccountingLedger());
        edocCredentials.setErpId(voucherDetailsVo.getErpId());
        edocCredentials.setEdocHeaderId(voucherDetailsVo.getTaskId());
        edocCredentials.setDeleted(0);
        List<EdocCredentials> edocCredentialsList = edocCredentialsService.query(edocCredentials);

        if (edocCredentialsList.size() > 1) return MapUtil.getResultMap("001", "凭证信息存在多条", RESP_FAIL);
        else if (edocCredentialsList.size() == 1) voucherDetailsVo.setId(edocCredentialsList.get(0).getId());
        return MapUtil.getResultMap("200", "信息验证成功", RESP_SUCCESS);

    }

    /*609*/
    @POST
    @Path("getImageByVoucherInter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public String getImageByVoucherInter(String req) {
        LOGGER.info("************************【开始】**************************");

        if (StringUtils.isEmpty(req))
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));
        String configFlag = LoadConfig.get("inter.imageByVoucherInter");
        String resp = null;
        List<EdocHeaderModel> respList = new ArrayList<>();
        try {
            //参数解析
//            @SuppressWarnings("unchecked")
//            String reqParamStr = JsonUtil.bean2JsonString(req);

            VoucherDetailsVo voucherDetailsVo = JsonUtil.json2Bean(req, VoucherDetailsVo.class);
            if (BeanUtil.isEmpty(voucherDetailsVo))
                return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "参数为空，接收失败", RESP_FAIL));

            //接口调用标识验证
            Map<String, Object> validateInterfaceFlagMap = this.validateInterfaceFlag(voucherDetailsVo.getInterfaceFlag(), configFlag);
            if (!MapUtil.getBoolean(validateInterfaceFlagMap))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, MapUtil.getMsg(validateInterfaceFlagMap), RESP_FAIL));
            LOGGER.info("接口:「{}」，封面条码：<{}>,请求参数；<{}>", configFlag, voucherDetailsVo.getCoverNo(), JsonUtil.bean2JsonString(voucherDetailsVo));

            //参数校验
            Map<String, Object> reqParamInfoMap = this.validateReqParamInfo(voucherDetailsVo);
            if (!MapUtil.getBoolean(reqParamInfoMap))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, MapUtil.getMsg(reqParamInfoMap), RESP_FAIL));

            //获取凭证结果集
            EdocCredentials edocCredentials = new EdocCredentials();
            edocCredentials.setAccountinLedger(voucherDetailsVo.getAccountingLedger());
            edocCredentials.setErpId(voucherDetailsVo.getErpId());
            edocCredentials.setCreateDate(voucherDetailsVo.getCreateDate());
            edocCredentials.setCredentialsNum(voucherDetailsVo.getCredentialsNum());
            edocCredentials.setCredentialsStatus(voucherDetailsVo.getCredentialStatus());
            edocCredentials.setCredentialsType(voucherDetailsVo.getType());
            List<EdocCredentials> edocCredentialsList = edocCredentialsService.query(edocCredentials);
            if (BeanUtil.isEmpty(edocCredentialsList))
                return MapUtil.interResp(200, "调用成功", MapUtil.getResultMap(Constant.getCode.fail, "凭证信息为空", RESP_FAIL));

            //获取返回结果
            for (EdocCredentials edocCredentialsItem : edocCredentialsList) {
                EdocHeaderModel edocHeaderModel = new EdocHeaderModel();
                edocHeaderModel.setId(edocCredentialsItem.getEdocHeaderId());
                List<EdocHeaderModel> edocHeaderModelList = edocHeaderService.getBillImagesByBillHeaderId(edocHeaderModel);
                if (BeanUtil.isEmpty(edocHeaderModelList)) continue;
                edocHeaderModelList.get(0).setCode(Constant.getCode.success);
                //搜集返回结果
                respList.add(edocHeaderModelList.get(0));
            }

            resp = MapUtil.interResp(200, "调用成功", respList);
            LOGGER.info("************************【结束】**************************");
        } catch (Exception e) {
            LOGGER.info("接口：「{}」,系统异常:", configFlag, e);
            return JsonUtil.bean2JsonString(MapUtil.getResultMap("001", "系统异常", RESP_FAIL));
        }
        return resp;
    }
}
