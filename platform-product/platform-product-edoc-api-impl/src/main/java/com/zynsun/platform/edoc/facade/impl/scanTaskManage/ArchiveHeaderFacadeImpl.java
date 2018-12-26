package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;

import com.zynsun.platform.edoc.domain.ArchiveCompany;
import com.zynsun.platform.edoc.domain.ArchiveHeader;
import com.zynsun.platform.edoc.dto.archive.ArchiveHeaderDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.ArchiveHeaderFacade;
import com.zynsun.platform.edoc.model.ArchiveHeaderModel;
import com.zynsun.platform.edoc.service.ArchiveCompanyService;
import com.zynsun.platform.edoc.service.ArchiveHeaderService;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 上午 9:24
 * @Modified By:
 */
@Service(value="archiveHeaderFacade")
public class ArchiveHeaderFacadeImpl implements ArchiveHeaderFacade {


    private static final Logger ARCHIVE_HEADER_LOGGOR = LoggerFactory.getLogger(ArchiveHeaderFacadeImpl.class);

    @Autowired
    private ArchiveHeaderService archiveHeaderService;

    /*@Autowired
    private CompanyCliFacade companyCliFacade;*/

    @Autowired
    private ArchiveCompanyService archiveCompanyService;

    @Override
    public ExecuteResult<PageInfo<ArchiveHeaderDTO>> findArchiveHeaderList(ArchiveHeaderDTO archiveHeaderDTO) {
        ExecuteResult<PageInfo<ArchiveHeaderDTO>> result = new ExecuteResult<>();
        try{
            Page<ArchiveHeaderModel> archiveHeaderList = archiveHeaderService.findArchiveHeaderModelList(DozerUtil.map(archiveHeaderDTO, ArchiveHeaderModel.class));
            if (archiveHeaderList!=null){
                result.setResult(DozerUtil.mapPage(archiveHeaderList.toPageInfo(),ArchiveHeaderDTO.class));
                result.setSuccessMessage("查询成功");
            }else {
                result.addErrorMessage("查询失败");
            }
        }catch (Exception e){
            result.addErrorMessage(e.toString());
            ARCHIVE_HEADER_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<String> createArchiveHeader(ArchiveHeaderDTO archiveHeaderDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try{
            int num = 0;
            Assert.notNull(archiveHeaderDTO,"新增的全宗不能为空");
            //关联的公司ids
            String companyIds = archiveHeaderDTO.getCompanyIds();
            ArchiveHeader archiveHeader = DozerUtil.map(archiveHeaderDTO, ArchiveHeader.class);
            archiveHeader.setCreateTime(new Date());
            //保存变更后的全宗公司信息
            companyIds=(companyIds==null)?"":companyIds;
            num = this.initArchiveCompany(companyIds, archiveHeader);
            if (num == 1) {
                if (!StringUtils.isEmpty(companyIds)){
                    result.setSuccessMessage("全宗、全宗公司保存成功");
                }else {
                    result.setSuccessMessage("全宗、全宗公司保存成功");
                }
                result.setResult("操作成功");
            }else {
                result.addErrorMessage("全宗、全宗公司保存失败!");
            }
            return result;
        }catch (Exception e){
            result.addErrorMessage("全宗、全宗公司保存失败");
            ARCHIVE_HEADER_LOGGOR.error("全宗、全宗公司保存失败", e);
            return result;
        }
    }

    /**
     * @Description：全宗保存，更新和对应的全宗公司删除、保存
     * @Author：FeiyueYang
     * @Param：null
     * @Date：2017/8/12 0012 下午 4:52
     */
    private Integer initArchiveCompany(String companyIds,ArchiveHeader archiveHeader) {
        String[] companyIdArr = companyIds.split(";");
        List<ArchiveCompany> archiveCompanyList = new ArrayList<ArchiveCompany>();
        //初始化待保存的全宗公司的信息
        for (int i = 0; i < companyIdArr.length; i++) {
            ArchiveCompany archiveCompany = new ArchiveCompany();
            archiveCompany.setDeleted(Constant.DeleteFlag.DEL_NO);
            archiveCompany.setCompanyId(companyIdArr[i]);
            //查询公司是否已有全宗设置
            /*CompanyResultDTO companyResultDTO = companyCliFacade.queryById(companyIdArr[i], Constant.DeleteFlag.DEL_NO).getResult();
            if (companyResultDTO != null && companyResultDTO.getCompanyCode() != null && companyResultDTO.getCompanyName() != null) {
                archiveCompany.setCompanyCode(companyResultDTO.getCompanyCode());
                archiveCompany.setCompanyName(companyResultDTO.getCompanyName());
            }*/
            archiveCompany.setCreateTime(new Date());
            archiveCompanyList.add(archiveCompany);
        }
        //保存全宗和全宗公司
        return archiveHeaderService.addArchiveHeadAndCompany(archiveHeader, archiveCompanyList);

    }

    /**
     * @Description：根据全宗id删除全宗和全宗公司
     * @Author：FeiyueYang
     * @Param：null
     * @Date：2017/8/13 0013 下午 4:15
     */
    @Override
    public ExecuteResult<Boolean> deleteArchiveHeaderAndCompanyById(Long id) {
        ExecuteResult<Boolean> result = new ExecuteResult<>();
        int i = 0;
        try {
            i = archiveCompanyService.deleteArchiveHeadAndCompany(id);
            if (i>0){
                result.setResult(true);
                result.setSuccessMessage("删除成功");
            }else {
                result.addErrorMessage("删除失败");
            }
        }catch (Exception e){
            result.addErrorMessage("删除失败");
            ARCHIVE_HEADER_LOGGOR.error("删除失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<ArchiveHeaderDTO> findArchiveHeaderById(Long id) {
        ExecuteResult<ArchiveHeaderDTO> result = new ExecuteResult<>();
        try {
            ArchiveHeader archiveHeader = archiveHeaderService.queryById(id);
            result.setResult(DozerUtil.map(archiveHeader,ArchiveHeaderDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e){
            result.addErrorMessage("查询失败");
            ARCHIVE_HEADER_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<ArchiveHeaderDTO> findArchiveHeaderByCompanyId(String companyId) {
        ExecuteResult<ArchiveHeaderDTO> result = new ExecuteResult<>();
        try {
            ArchiveHeader archiveHeader = archiveHeaderService.findArchiveHeaderByCompanyId(companyId);
            result.setResult(DozerUtil.map(archiveHeader,ArchiveHeaderDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e){
            result.addErrorMessage("查询失败");
            ARCHIVE_HEADER_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }

    @Override
    public ExecuteResult<ArchiveHeaderDTO> findArchiveInfo(String companyId) {
        ExecuteResult<ArchiveHeaderDTO> result = new ExecuteResult<>();
        try {
            ArchiveHeaderModel archiveHeaderModel = archiveHeaderService.findArchiveInfo(companyId);
            result.setResult(DozerUtil.map(archiveHeaderModel,ArchiveHeaderDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            result.addErrorMessage("查询失败");
            ARCHIVE_HEADER_LOGGOR.error("查询失败", e);
            return result;
        }
        return result;
    }
}
