package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.ArchiveIdentifies;
import com.zynsun.platform.edoc.model.ArchiveIdentifiesModel;

/**
 * @Author: liuss
 * @Description:
 * @Date:Created in 2018-08-26 上午 10:03
 * @Modified By:
 */
public interface ArchiveIdentifiesService extends BaseService<ArchiveIdentifies> {


    Page<ArchiveIdentifiesModel> archivedIdentifiesList(ArchiveIdentifiesModel model);

    //根据edocHearderId查询是否已经存在鉴定记录
    ArchiveIdentifiesModel queryByEdocHearderId(Long edocHeaderId);
}
