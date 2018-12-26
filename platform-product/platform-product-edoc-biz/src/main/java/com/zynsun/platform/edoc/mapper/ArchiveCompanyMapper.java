package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.ArchiveCompany;
import com.zynsun.platform.edoc.model.CompanyModel;
import org.apache.ibatis.annotations.Param;

public interface ArchiveCompanyMapper extends IMapper<ArchiveCompany> {

    /**
     * @Description：根据全宗id删除全宗公司
     * @Author：FeiyueYang
     * @Param：null
     * @Date：2017/8/15 0015 下午 2:49
     */
    Integer deleteByArchiveId(String archiveId);


    CompanyModel selectCompanyByName(@Param(value = "companyName") String companyName);

}