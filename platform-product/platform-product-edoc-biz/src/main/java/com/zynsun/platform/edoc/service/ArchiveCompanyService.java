package com.zynsun.platform.edoc.service;


import com.zynsun.openplatform.service.BaseService;
import com.zynsun.platform.edoc.domain.ArchiveCompany;
import com.zynsun.platform.edoc.model.CompanyModel;


public interface ArchiveCompanyService extends BaseService<ArchiveCompany> {

    CompanyModel selectCompanyByName(String companyName);


    public Integer deleteArchiveHeadAndCompany(Long id);
}
