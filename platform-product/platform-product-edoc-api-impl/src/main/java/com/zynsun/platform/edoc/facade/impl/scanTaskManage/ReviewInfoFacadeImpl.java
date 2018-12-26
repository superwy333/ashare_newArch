package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.dubboRestLoginFilter.annotation.Login;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.domain.ReviewInfo;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dto.ReviewInfoDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.ReviewInfoFacade;
import com.zynsun.platform.edoc.model.ReviewInfoModel;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import com.zynsun.platform.edoc.service.ReviewInfoService;
import com.zynsun.platform.utils.EnumUtil;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-22 11:15
 **/
@Path("reviewInfoFacade")
@Service("reviewInfoFacade")
public class ReviewInfoFacadeImpl implements ReviewInfoFacade {

    @Autowired
    private ReviewInfoService reviewInfoService;

    @Autowired
    private EdocHeaderService edocHeaderService;

    private static final Logger REVIEWINFO_LOGGER = LoggerFactory.getLogger(EdocHeaderFacadeImpl.class);

    @Login
    @POST
    @Path("reviewInfoPass")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    @Transactional
    public Map<String, Object> reviewInfoPass(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            /**
             * 评价人工放行的逻辑
             * 1通过请求中的评价ID找到任务
             * 2评价状态修改为已处理
             * 3任务状态修改为已采集
             */
            String reviewInfoId = (String)map.get("reviewId");
            ReviewInfo reviewInfo = reviewInfoService.queryById(Long.valueOf(reviewInfoId));
            if ("1".equals(reviewInfo.getIsProcessed())) {
                executeResult.put("code","001");
                executeResult.put("msg","人工处理失败！此条评价已被处理！");
                executeResult.put("success",false);
                return executeResult;
            }
            EdocHeader edocHeader = edocHeaderService.queryById(Long.valueOf(reviewInfo.getEdocHeaderId()));
            edocHeader.setEdocTaskStatus(Constant.BizStatus.WAIT_CONFIRM);
            reviewInfo.setIsProcessed("1");
            edocHeaderService.updateByIdSelective(edocHeader);
            reviewInfoService.updateByIdSelective(reviewInfo);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            REVIEWINFO_LOGGER.error("评价人工放行操作失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","评价人工放行操作失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Login
    @POST
    @Path("reviewInfoCombobox")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> reviewInfoCombobox(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        try {
            Integer processType = (Integer)map.get("processType");
            if (BeanUtil.isEmpty(processType)) {
                executeResult.put("code","001");
                executeResult.put("msg","processType为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            switch (processType) {
                case 1: result = EnumUtil.IntEnumToMap(EdocHeaderDTO.EdocTaskStatusEnum.class); break;
                case 2: result = EnumUtil.IntEnumToMap(ReviewInfoDTO.ReviewInfoStatus.class); break;
                case 3: result = EnumUtil.IntEnumToMap(ReviewInfoDTO.ReviewSourceEnum.class); break;
                case 4: result = EnumUtil.IntEnumToMap(ReviewInfoDTO.ReviewInfoIsProcessed.class); break;
            }
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            REVIEWINFO_LOGGER.error("查询评价下拉框数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","查询评价下拉框数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }

    @Login
    @POST
    @Path("reviewInfoDataGrid")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> reviewInfoDataGrid(Map<String, Object> map) {
        Map<String, Object> executeResult = new HashMap<>();
        try {
            Integer inputPage = (Integer) map.get("page");
            Integer rows = (Integer) map.get("limit");
            if (BeanUtil.isEmpty(inputPage)||BeanUtil.isEmpty(rows)) {
                executeResult.put("code","001");
                executeResult.put("msg","分页参数不能为空！");
                executeResult.put("success",false);
                return executeResult;
            }
            ReviewInfoModel reviewInfoModel = new ReviewInfoModel();
            reviewInfoModel.setPage(inputPage);
            reviewInfoModel.setPageSize(rows);
            reviewInfoModel.setEdocNo(BeanUtil.isEmpty(map.get("edocNo"))?null:(String) map.get("edocNo"));
            reviewInfoModel.setEdocTaskStatus(BeanUtil.isEmpty(map.get("edocTaskStatus"))?null:(String) map.get("edocTaskStatus"));
            reviewInfoModel.setCompanyName(BeanUtil.isEmpty(map.get("companyName"))?null:(String) map.get("companyName"));
            reviewInfoModel.setReviewStatus(BeanUtil.isEmpty(map.get("reviewStatus"))?null:(String) map.get("reviewStatus"));
            reviewInfoModel.setIsProcessed(BeanUtil.isEmpty(map.get("isProcessed"))?null:(String) map.get("isProcessed"));
            List<String> reviewTime = (List<String>)map.get("reviewTime");
            if (!BeanUtil.isEmpty(reviewTime)) {
                reviewInfoModel.setReviewTimeStart(BeanUtil.isEmpty(reviewTime.get(0))?null:reviewTime.get(0));
                reviewInfoModel.setReviewTimeEnd(BeanUtil.isEmpty(reviewTime.get(1))?null:reviewTime.get(1));
            }
            Page<ReviewInfoModel> page = reviewInfoService.queryReviewInfoPage(reviewInfoModel);
            if (!BeanUtil.isEmpty(page.getResult())) {
                List<ReviewInfoModel> reviewInfoModelList = page.getResult();
                for (ReviewInfoModel r:reviewInfoModelList) {
                    if (!BeanUtil.isEmpty(r.getEdocType())) {
                        EdocHeaderDTO.EdocTypeEnum edocTypeEnum = EdocHeaderDTO.EdocTypeEnum.parse(r.getEdocType());
                        if (!BeanUtil.isEmpty(edocTypeEnum)) {
                            r.setEdocType(edocTypeEnum.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(r.getEdocTaskStatus())) {
                        EdocHeaderDTO.EdocTaskStatusEnum edocTaskStatusEnum = EdocHeaderDTO.EdocTaskStatusEnum.parse(r.getEdocTaskStatus());
                        if (!BeanUtil.isEmpty(edocTaskStatusEnum)) {
                            r.setEdocTaskStatus(edocTaskStatusEnum.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(r.getReviewStatus())) {
                        ReviewInfoDTO.ReviewInfoStatus reviewInfoStatus = ReviewInfoDTO.ReviewInfoStatus.parse(r.getReviewStatus());
                        if (!BeanUtil.isEmpty(reviewInfoStatus)) {
                            r.setReviewStatus(reviewInfoStatus.getName());
                        }
                    }
                    if (!BeanUtil.isEmpty(r.getIsProcessed())) {
                        ReviewInfoDTO.ReviewInfoIsProcessed reviewInfoIsProcessed = ReviewInfoDTO.ReviewInfoIsProcessed.parse(r.getIsProcessed());
                        if (!BeanUtil.isEmpty(reviewInfoIsProcessed)) {
                            r.setIsProcessed(reviewInfoIsProcessed.getName());
                        }

                    }
                    if (!BeanUtil.isEmpty(r.getReviewSource())) {
                        ReviewInfoDTO.ReviewSourceEnum reviewSourceEnum = ReviewInfoDTO.ReviewSourceEnum.parse(r.getReviewSource());
                        if (!BeanUtil.isEmpty(reviewSourceEnum)) {
                            r.setReviewSource(reviewSourceEnum.getName());
                        }

                    }
                }

            }
            Map<String,Object> result = new HashMap<>();
            result.put("dataGrid",page.getResult());
            reviewInfoModel.setPage(null);
            reviewInfoModel.setPageSize(null);
            List<ReviewInfoModel> reviewInfoModelListTotal = reviewInfoService.queryReviewInfos(reviewInfoModel);
            result.put("totalRows",BeanUtil.isEmpty(reviewInfoModelListTotal)?0:reviewInfoModelListTotal.size());
            executeResult.put("result",result);
            executeResult.put("code","000");
            executeResult.put("msg","资料处理成功！");
            executeResult.put("success",true);
        }catch (Exception e) {
            REVIEWINFO_LOGGER.error("查询评价分页数据失败", e);
            executeResult.put("code","001");
            executeResult.put("msg","查询评价分页数据失败！");
            executeResult.put("success",false);
            return executeResult;
        }
        return executeResult;
    }
}
