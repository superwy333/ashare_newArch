package com.zynsun.platform.edoc.service;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.ArchiveCompany;
import com.zynsun.platform.edoc.domain.ArchiveHeader;
import com.zynsun.platform.edoc.dto.archive.ArchiveHeaderDTO;
import com.zynsun.platform.edoc.model.ArchiveHeaderModel;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 上午 9:27
 * @Modified By:
 */
public interface ArchiveHeaderService extends BaseService<ArchiveHeader> {

    public Page<ArchiveHeaderModel> findArchiveHeaderModelList(ArchiveHeaderModel archiveHeaderModel);

    public Integer addArchiveHeadAndCompany(ArchiveHeader archiveHeader,List<ArchiveCompany> archiveCompanyList);

    ArchiveHeader findArchiveHeaderByCompanyId(String companyId);

    ArchiveHeaderModel findArchiveInfo(String companyId);



}
