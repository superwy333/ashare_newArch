package com.zynsun.platform.edoc.service.impl;
import java.util.Date;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.context.PlatformContext;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.LoginUser;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.DateUtil;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.domain.ReviewInfo;
import com.zynsun.platform.edoc.mapper.EdocHeaderMapper;
import com.zynsun.platform.edoc.mapper.ReviewInfoMapper;
import com.zynsun.platform.edoc.model.ReviewInfoModel;
import com.zynsun.platform.edoc.service.EdocHeaderService;
import com.zynsun.platform.edoc.service.ReviewInfoService;
import com.zynsun.platform.edoc.utils.MapUtil;
import com.zynsun.platform.edoc.webservice.BaseInter;
import constant.Constant;
import constant.EdocHeaderConstant;
import constant.ReviewInfoConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Jary on 2018/11/20/020.
 */
@Service
public class ReviewInfoServiceImpl extends BaseServiceImpl<ReviewInfo> implements ReviewInfoService, BaseInter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewInfoServiceImpl.class);
    @Autowired
    ReviewInfoMapper reviewInfoMapper;
    @Autowired
    EdocHeaderService edocHeaderService;

    @Override
    public Page<ReviewInfoModel> queryReviewInfoPage(ReviewInfoModel reviewInfoModel) {
        return reviewInfoMapper.selectReviewInfoPage(reviewInfoModel);
    }

    @Override
    public List<ReviewInfoModel> queryReviewInfos(ReviewInfoModel reviewInfoModel) {
        return reviewInfoMapper.selectReviewInfos(reviewInfoModel);
    }

    @Override
    public Map<String, Object> updateScrapByTaskId(ReviewInfoModel reviewInfoReq) throws Exception {
        EdocHeader edocHeader = null;
        edocHeader = this.edocHeaderService.queryById(Long.valueOf(reviewInfoReq.getTaskId()));
        String reviewStatus = reviewInfoReq.getReviewStatus();
        String taskStatus;
        if (StringUtils.isEmpty(reviewInfoReq.getEdocTaskStatus())) taskStatus = edocHeader.getEdocTaskStatus();
        else taskStatus = reviewInfoReq.getEdocTaskStatus();

        String edocTaskStatus = edocHeader.getEdocTaskStatus();
        edocHeader.setReview(reviewInfoReq.getEdocTaskStatus());

        //审核通过或审核不通过，自动封单
        if (taskStatus.equals(Constant.Review.TASK_PASS) || taskStatus.equals(Constant.Review.TASK_FAILED)) edocHeader.setSeal(Constant.Seal.SEALED);

        switch (reviewStatus) {
            case ReviewInfoConstant.ReviewStatus.zero:edocTaskStatus = Constant.BizStatus.WAIT_CONFIRM;break;   //已采集
            case ReviewInfoConstant.ReviewStatus.SCRAP:edocTaskStatus = Constant.BizStatus.WAIT_INVALID; break; // 整单作废
            case ReviewInfoConstant.ReviewStatus.SCAVENGING_ALL:edocTaskStatus = Constant.BizStatus.AGAIN_SCAN; break; // 整单重采集
            case ReviewInfoConstant.ReviewStatus.SCAVENGING_SINGLE:edocTaskStatus = Constant.BizStatus.WAIT_ADD_DATA;break; // 补采集
            case ReviewInfoConstant.ReviewStatus.DATA_INCOMPLETE:
                /*edocHeader.setIsAbnormal("1");
                edocHeader.setAbnormalType("4");*/
                break; // 数据不完整
        }
        edocHeader.setEdocTaskStatus(edocTaskStatus);
        this.edocHeaderService.updateByIdSelective(edocHeader);
        LOGGER.info("任务状态更新成功，任务单号：<{}>,操作申请人：<{}>,申请时间：<{}>", edocHeader.getEdocNo(), null, reviewInfoReq.getReviewTime());

        if (reviewStatus.equals(ReviewInfoConstant.ReviewStatus.zero)) return MapUtil.getResultMap("000", "审核流程已结束", RESP_SUCCESS);

        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setEdocHeaderId(String.valueOf(edocHeader.getId()));
        reviewInfo.setReviewSource(reviewInfoReq.getReviewSource());
        reviewInfo.setReviewStatus(reviewInfoReq.getReviewStatus());
        reviewInfo.setReviewMessage(reviewInfoReq.getReviewMessage());
//        LoginUser user = SubjectUtil.getUser();
        reviewInfo.setReviewName((String) PlatformContext.getVariable("userName"));
//        reviewInfo.setReviewName(user.getLoginName());
//        reviewInfo.setReviewCode(user.getUserCode());
        reviewInfo.setReviewCode((String) PlatformContext.getVariable("loginName"));
        reviewInfo.setReviewTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        reviewInfo.setReviewTime(reviewInfoReq.getReviewTime());
        reviewInfo.setIsProcessed(ReviewInfoConstant.getProcess.unProcess);
        this.add(reviewInfo);
        LOGGER.info("评价信息生成成功，任务单号：<{}>,操作人：<{}>,操作时间：<{}>", edocHeader.getEdocNo(), null, reviewInfoReq.getReviewTime());
        return MapUtil.getResultMap("000", "资料处理成功", RESP_SUCCESS);
    }

    private Map<String, Object> validateReviewInfo(ReviewInfoModel reviewInfoReq) {
        if (StringUtils.isEmpty(reviewInfoReq.getReviewStatus()))
            return MapUtil.getResultMap("001", "评价标识为空", RESP_FAIL);
        if (StringUtils.isEmpty(reviewInfoReq.getReviewMessage()))
            return MapUtil.getResultMap("001", "评价信息为空", RESP_FAIL);
        if (StringUtils.isEmpty(reviewInfoReq.getReviewMessage()))
            return MapUtil.getResultMap("001", "评价时间为空", RESP_FAIL);
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setDeleted(0);
        reviewInfo.setIsProcessed("0");
        reviewInfo.setEdocHeaderId(reviewInfoReq.getTaskId());
        List<ReviewInfo> queryReviewInfoList = this.query(reviewInfo);
        if (!BeanUtil.isEmpty(queryReviewInfoList))
            return MapUtil.getResultMap("001", "有未处理完的评价信息，操作失败", RESP_FAIL);
        return MapUtil.getResultMap("001", "参数验证成功", RESP_SUCCESS);
    }

    @Override
    protected IMapper<ReviewInfo> getBaseMapper() {
        return reviewInfoMapper;
    }
}
