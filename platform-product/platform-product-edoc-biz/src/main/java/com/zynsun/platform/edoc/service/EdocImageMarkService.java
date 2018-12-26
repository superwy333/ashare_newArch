package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.EdocImageMark;
import com.zynsun.platform.edoc.model.EdocImageMarkModel;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:41
 **/
public interface EdocImageMarkService extends BaseService<EdocImageMark> {

    Page<EdocImageMarkModel> queryMarkRecordsPageList(EdocImageMarkModel edocImageMarkModel);

}
