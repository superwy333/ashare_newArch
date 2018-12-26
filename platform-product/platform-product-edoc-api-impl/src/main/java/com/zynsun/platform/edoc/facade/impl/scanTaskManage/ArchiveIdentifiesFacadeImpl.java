package com.zynsun.platform.edoc.facade.impl.scanTaskManage;


import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;

import com.zynsun.openplatform.util.BeanUtil;

import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;

import com.zynsun.platform.edoc.domain.ArchiveIdentifies;

import com.zynsun.platform.edoc.dto.archive.ArchiveIdentifiesDTO;

import com.zynsun.platform.edoc.facade.scanTaskManage.ArchiveIdentifiesFacade;
import com.zynsun.platform.edoc.model.ArchiveIdentifiesModel;

import com.zynsun.platform.edoc.service.ArchiveIdentifiesService;
import com.zynsun.platform.edoc.service.ArchiveMoveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuss
 * @Description:
 * @Date:Created in 2018-08-26 上午 10:03
 * @Modified By:
 */
@Service(value="archiveIdentifiesFacade")
public class ArchiveIdentifiesFacadeImpl implements ArchiveIdentifiesFacade {

    private static final Logger ARCHIVE_MOVE_LOGGOR = LoggerFactory.getLogger(ArchiveIdentifiesFacadeImpl.class);

    @Autowired
    private ArchiveMoveService archiveMoveService;
    @Autowired
    private ArchiveIdentifiesService archiveIdentifiesService;


    @Override
    public ExecuteResult<PageInfo<ArchiveIdentifiesDTO>> archivedIdentifiesList(ArchiveIdentifiesDTO archiveIdentifiesDTO) {
        ExecuteResult<PageInfo<ArchiveIdentifiesDTO>> result = new ExecuteResult<>();
        try {
            ArchiveIdentifiesModel model = DozerUtil.map(archiveIdentifiesDTO, ArchiveIdentifiesModel.class);
            Page<ArchiveIdentifiesModel> pages = archiveIdentifiesService.archivedIdentifiesList(model);
            PageInfo<ArchiveIdentifiesDTO> pageInfo = DozerUtil.mapPage(pages.toPageInfo(), ArchiveIdentifiesDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("查询档案鉴定列表成功");
        } catch (Exception e) {
            result.addErrorMessage("查询档案鉴定列表失败");
            ARCHIVE_MOVE_LOGGOR.error("查询档案鉴定列表异常{}", e);
            return result;
        }
        return result;
    }

    //保存鉴定结果
    @Override
    public ExecuteResult<String> saveArchiveIdenti(ArchiveIdentifiesDTO archiveIdentifiesDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try{
            //ArchiveIdentifiesModel model = DozerUtil.map(archiveIdentifiesDTO, ArchiveIdentifiesModel.class);
            //根据edocHearderId查询是否已经存在鉴定记录
            ArchiveIdentifiesModel model = archiveIdentifiesService.queryByEdocHearderId(archiveIdentifiesDTO.getEdocHeaderId());
            ArchiveIdentifies archiveIdentifies = new ArchiveIdentifies();
            if (BeanUtil.isEmpty(model) ){//新建
                archiveIdentifies.setEdocHeaderId(archiveIdentifiesDTO.getEdocHeaderId());
                if ( !BeanUtil.isEmpty(archiveIdentifiesDTO.getIdentifiesArchiveYear())){
                    archiveIdentifies.setIdentifiesArchiveYear(archiveIdentifiesDTO.getIdentifiesArchiveYear());
                }
                archiveIdentifies.setIdentifiesFlag(archiveIdentifiesDTO.getIdentifiesFlag());
                archiveIdentifies.setOfficeName(archiveIdentifiesDTO.getOfficeName());
                archiveIdentifies.setIdentifier(archiveIdentifiesDTO.getIdentifier());
                archiveIdentifies.setIdentifiesTime(archiveIdentifiesDTO.getIdentifiesTime());
                archiveIdentifies.setRemarks(archiveIdentifiesDTO.getRemarks());
                int num = archiveIdentifiesService.add(archiveIdentifies);
                //
                if (num > 0){
                    result.setSuccessMessage("保存鉴定成功");
                    result.setResult("保存鉴定成功");
                }else {
                    result.addErrorMessage("保存鉴定失败");
                }
            }else{  //修改
                archiveIdentifies.setId(model.getId());
                archiveIdentifies.setVersion(model.getVersion());
                archiveIdentifies.setEdocHeaderId(archiveIdentifiesDTO.getEdocHeaderId());
                if ( !BeanUtil.isEmpty(archiveIdentifiesDTO.getIdentifiesArchiveYear())){
                    archiveIdentifies.setIdentifiesArchiveYear(archiveIdentifiesDTO.getIdentifiesArchiveYear());
                }
                archiveIdentifies.setIdentifiesFlag(archiveIdentifiesDTO.getIdentifiesFlag());
                archiveIdentifies.setOfficeName(archiveIdentifiesDTO.getOfficeName());
                archiveIdentifies.setIdentifier(archiveIdentifiesDTO.getIdentifier());
                archiveIdentifies.setIdentifiesTime(archiveIdentifiesDTO.getIdentifiesTime());
                archiveIdentifies.setRemarks(archiveIdentifiesDTO.getRemarks());
                int num = archiveIdentifiesService.updateById(archiveIdentifies);
                //updateById
                if (num > 0){
                    result.setSuccessMessage("保存鉴定成功");
                    result.setResult("保存鉴定成功");
                }else {
                    result.addErrorMessage("保存鉴定失败");
                }
            }

        }catch (Exception e){
            ARCHIVE_MOVE_LOGGOR.error("保存鉴定失败", e);
            result.addErrorMessage("保存鉴定失败");
        }
        return result;
    }
}




