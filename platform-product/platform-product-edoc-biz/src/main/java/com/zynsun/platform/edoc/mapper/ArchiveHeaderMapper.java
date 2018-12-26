package com.zynsun.platform.edoc.mapper;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.ArchiveHeader;
import com.zynsun.platform.edoc.domain.EdocHeader;
import com.zynsun.platform.edoc.model.ArchiveHeaderModel;
import org.apache.ibatis.annotations.Param;

public interface ArchiveHeaderMapper extends IMapper<ArchiveHeader> {

    Page<ArchiveHeaderModel> findArchiveHeaderModelList(@Param(value = "archiveHeader") ArchiveHeaderModel archiveHeaderModel);

    ArchiveHeader findArchiveHeaderByCompanyId(@Param(value = "companyId")String companyId);

    ArchiveHeaderModel findArchiveInfo(@Param(value = "companyId")String companyId);


}