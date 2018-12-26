package com.zynsun.platform.edoc.service.impl;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.platform.edoc.domain.ArchiveCompany;
import com.zynsun.platform.edoc.domain.ArchiveHeader;
import com.zynsun.platform.edoc.dto.archive.ArchiveHeaderDTO;
import com.zynsun.platform.edoc.mapper.ArchiveCompanyMapper;
import com.zynsun.platform.edoc.mapper.ArchiveHeaderMapper;
import com.zynsun.platform.edoc.model.ArchiveHeaderModel;
import com.zynsun.platform.edoc.service.ArchiveCompanyService;
import com.zynsun.platform.edoc.service.ArchiveHeaderService;
import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-21 上午 9:29
 * @Modified By:
 */

@Service
public class ArchiveHeaderServiceImpl extends BaseServiceImpl<ArchiveHeader> implements ArchiveHeaderService {

    private static final Logger ARCHIVE_HEADER_LOGGER = LoggerFactory.getLogger(ArchiveHeaderServiceImpl.class);
    @Resource
    ArchiveHeaderMapper archiveHeaderMapper;

    @Resource
    ArchiveCompanyMapper archiveCompanyMapper;

    @Resource
    private ArchiveCompanyService archiveCompanyService;


    @Override
    protected IMapper<ArchiveHeader> getBaseMapper() {
        return archiveHeaderMapper;
    }

    @Override
    public Page<ArchiveHeaderModel> findArchiveHeaderModelList(ArchiveHeaderModel archiveHeaderModel) {
        return archiveHeaderMapper.findArchiveHeaderModelList(archiveHeaderModel);
    }

    @Override
    public Integer addArchiveHeadAndCompany(ArchiveHeader archiveHeader, List<ArchiveCompany> archiveCompanyList) {
        int resultNum = 0;//0:添加操作失败，1：添加操作成功
        ArchiveHeader archiveHeader1 = new ArchiveHeader();
        archiveHeader1.setHeaderCode(archiveHeader.getHeaderCode());
        archiveHeader1.setDeleted(Constant.DeleteFlag.DEL_NO);
        List<ArchiveHeader> select = this.query(archiveHeader1);
        //全宗设置更新，保存
        if (!StringUtils.isEmpty(archiveHeader.getId())){//存在全宗ID则直接更新
            archiveHeader.setLastModifyBy(SubjectUtil.getUser().getUserCode());
            archiveHeader.setLastModifyTime(new Date());
            resultNum = this.updateById(archiveHeader);
//            resultNum = archiveHeaderMapper.updateCASByPrimaryKey(archiveHeader);
        }else if (select == null || select.size() == 0){//未查到全宗可以插入
            archiveHeader.setCreateBy(SubjectUtil.getUser().getUserCode());
            archiveHeader.setCreateTime(new Date());
            resultNum = this.add(archiveHeader);
        }else {
            ARCHIVE_HEADER_LOGGER.error("[Service]全宗编码不能重复");
            resultNum = 0;
        }
        //全宗公司更新，保存
        if (1 == resultNum) {
            ArchiveCompany qArchiveCompany = new ArchiveCompany();
            qArchiveCompany.setArchiveId(archiveHeader.getId().toString());
            qArchiveCompany.setDeleted(Constant.DeleteFlag.DEL_NO);
            List<ArchiveCompany> query = archiveCompanyService.query(qArchiveCompany);
            if (null != query && query.size()>0){ //删除当前全宗下的所有全宗公司信息
                for (ArchiveCompany archiveCompany:query){
                    archiveCompanyService.deleteByIdLogic(archiveCompany.getId());
                }
            }
//            resultNum = archiveCompanyMapper.deleteByArchiveId(archiveHeader.getId().toString());
            for (ArchiveCompany archiveCompany : archiveCompanyList) {
                ArchiveCompany archiveCompany1 = new ArchiveCompany();
                archiveCompany1.setCompanyId(archiveCompany.getCompanyId());
                archiveCompany1.setDeleted(Constant.DeleteFlag.DEL_NO);
                List<ArchiveCompany> select1 = archiveCompanyMapper.select(archiveCompany1);
                if (select1 != null && select1.size() > 0) {
                    ARCHIVE_HEADER_LOGGER.error("[Service]公司不能重复设置全宗");
                    return 0;
                } else {
                    archiveCompany.setArchiveId(archiveHeader.getId().toString());
                    archiveCompany.setDeleted(Constant.DeleteFlag.DEL_NO);
                    resultNum = archiveCompanyMapper.insert(archiveCompany);
                    if (1 != resultNum) {
                        ARCHIVE_HEADER_LOGGER.error("[Service]全宗的公司信息插入失败");
                        return 0;
                    }
                }
            }
            return 1;
        } else {
            ARCHIVE_HEADER_LOGGER.error("[Service]全宗对象插入失败");
            return 0;
        }
    }


    @Override
    public ArchiveHeader findArchiveHeaderByCompanyId(String companyId) {
        return archiveHeaderMapper.findArchiveHeaderByCompanyId(companyId);
    }

    @Override
    public ArchiveHeaderModel findArchiveInfo(String companyId) {
        return archiveHeaderMapper.findArchiveInfo(companyId);
    }
}
