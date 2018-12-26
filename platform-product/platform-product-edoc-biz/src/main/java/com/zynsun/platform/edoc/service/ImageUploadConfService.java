package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.ImageUploadConf;
import com.zynsun.platform.edoc.model.ImageUploadConfModel;

import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 11:04 2018/1/11
 * @Modified By:
 */
public interface ImageUploadConfService extends BaseService<ImageUploadConf> {

    void addImageUploadConf(ImageUploadConfModel uploadConfModel);

    ImageUploadConfModel findConf();
}
