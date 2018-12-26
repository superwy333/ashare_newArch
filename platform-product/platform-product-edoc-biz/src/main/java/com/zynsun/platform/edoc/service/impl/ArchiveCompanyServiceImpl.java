package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.ArchiveCompany;
import com.zynsun.platform.edoc.mapper.ArchiveCompanyMapper;
import com.zynsun.platform.edoc.model.CompanyModel;
import com.zynsun.platform.edoc.service.ArchiveCompanyService;
import com.zynsun.platform.edoc.service.ArchiveHeaderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArchiveCompanyServiceImpl extends BaseServiceImpl<ArchiveCompany> implements ArchiveCompanyService {

    @Resource
    ArchiveCompanyMapper archiveCompanyMapper;
    @Resource
    ArchiveHeaderService archiveHeaderService;
    @Override
    protected IMapper<ArchiveCompany> getBaseMapper() {
        return archiveCompanyMapper;
    }

    @Override
    public Integer deleteArchiveHeadAndCompany(Long id) {
        int num = archiveHeaderService.deleteByIdLogic(id);
        if ( num>0 ){
            ArchiveCompany company = new ArchiveCompany();
            company.setArchiveId(id.toString());
            List<ArchiveCompany> query = this.query(company);
            if (null != query && 0<query.size()){
                for (ArchiveCompany archiveCompany:query){
                    this.deleteByIdLogic(archiveCompany.getId());
                }
            }
        }
        return num;
    }


    @Override
    public CompanyModel selectCompanyByName(String companyName) {
        return archiveCompanyMapper.selectCompanyByName(companyName);
    }
}
