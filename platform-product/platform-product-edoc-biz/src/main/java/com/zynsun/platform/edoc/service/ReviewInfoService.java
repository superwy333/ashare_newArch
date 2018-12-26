package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.ReviewInfo;
import com.zynsun.platform.edoc.model.ReviewInfoModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Jary on 2018/11/20/020.
 */
public interface ReviewInfoService extends BaseService<ReviewInfo> {
    /**
     * 作废任务信息并生成评价信息
     */
    Map<String, Object> updateScrapByTaskId(ReviewInfoModel reviewInfoReq) throws Exception ;


    Page<ReviewInfoModel> queryReviewInfoPage(ReviewInfoModel reviewInfoModel);

    List<ReviewInfoModel> queryReviewInfos(ReviewInfoModel reviewInfoModel);



}
