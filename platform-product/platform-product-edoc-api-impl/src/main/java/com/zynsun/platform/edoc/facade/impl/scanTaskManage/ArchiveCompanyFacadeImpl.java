package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;

import com.zynsun.platform.edoc.domain.ArchiveCompany;
import com.zynsun.platform.edoc.dto.archive.ArchiveCompanyDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.ArchiveCompanyFacade;
import com.zynsun.platform.edoc.model.ArchiveCompanyModel;
import com.zynsun.platform.edoc.model.CompanyModel;
import com.zynsun.platform.edoc.service.ArchiveCompanyService;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author：FeiyueYang
 * @Description：
 * @Date：Created in 2017/8/11 0011 下午 6:43
 * @Modified By：
 */
@Service(value="archiveCompanyFacade")
public class ArchiveCompanyFacadeImpl implements ArchiveCompanyFacade {
    private static final Logger ARCHIVE_COMPANY_LOGGOR = LoggerFactory.getLogger(ArchiveCompanyFacadeImpl.class);

    @Resource
    private ArchiveCompanyService archiveCompanyService;


    @Override
    public ExecuteResult<List<ArchiveCompanyDTO>> findArchiveCompanyByArchiveId(String archiveId) {
        ExecuteResult<List<ArchiveCompanyDTO>> result = new ExecuteResult<>();
        try {
            ArchiveCompany archiveCompany = new ArchiveCompany();
            archiveCompany.setArchiveId(archiveId);
            archiveCompany.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<ArchiveCompany> query = archiveCompanyService.query(archiveCompany);
            result.setResult(DozerUtil.mapList(query,ArchiveCompanyDTO.class));
            result.setSuccessMessage("全宗公司查询成功");
            return result;
        }catch (Exception e){
            result.addErrorMessage("全宗公司查询失败");
            ARCHIVE_COMPANY_LOGGOR.error("全宗公司查询失败", e);
            return result;
        }
    }

    @Override
    public ExecuteResult<ArchiveCompanyDTO> findArchiveCompanyByCompanyName(String companyName) {
        ExecuteResult<ArchiveCompanyDTO> result = new ExecuteResult<>();
        try{
            CompanyModel archiveCompanyModel = archiveCompanyService.selectCompanyByName(companyName);
            if(archiveCompanyModel!=null&&archiveCompanyModel.getArchiveCode()!=null&&!"".equals(archiveCompanyModel.getArchiveCode())){
                result.setResult(DozerUtil.map(archiveCompanyModel,ArchiveCompanyDTO.class));
                result.setSuccessMessage("查询成功");
            }else{
                result.setSuccessMessage("该公司没有对应的全宗号");
            }
        }catch (Exception e){
            result.addErrorMessage("全宗公司查询失败");
            ARCHIVE_COMPANY_LOGGOR.error("全宗公司查询失败", e);
            return result;
        }
        return result;
    }
}
