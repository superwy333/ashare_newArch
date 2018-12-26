package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.BizAreaField;
import com.zynsun.platform.edoc.model.BizAreaFieldModel;

import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:00
 **/
public interface BizAreaFieldService extends BaseService<BizAreaField> {

    List<BizAreaFieldModel> selectFieldByBizIdAndAreaId(BizAreaField record);


}
