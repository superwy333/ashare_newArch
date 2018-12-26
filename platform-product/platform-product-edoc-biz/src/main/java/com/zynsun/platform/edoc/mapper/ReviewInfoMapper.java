package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.ReviewInfo;
import com.zynsun.platform.edoc.model.ReviewInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Jary on 2018/11/20/020.
 */
public interface ReviewInfoMapper extends IMapper<ReviewInfo> {


    Page<ReviewInfoModel> selectReviewInfoPage(@Param(value = "reviewInfoModel") ReviewInfoModel reviewInfoModel);

    List<ReviewInfoModel> selectReviewInfos(@Param(value = "reviewInfoModel") ReviewInfoModel reviewInfoModel);


}
