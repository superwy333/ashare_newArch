package com.zynsun.platform.edoc.facade.impl.watermarkManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import constant.Constant;
import com.zynsun.platform.edoc.domain.SysWatermark;
import com.zynsun.platform.edoc.dto.watermark.SysWatermarkDTO;
import com.zynsun.platform.edoc.facade.watermarkManage.SysWatermarkFacade;
import com.zynsun.platform.edoc.model.SysWatermarkModel;
import com.zynsun.platform.edoc.service.SysWatermarkService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Tiger on 2017/7/12.
 */
@Service(value="sysWatermarkFacade")
public class SysWatermarkFacadeImpl implements SysWatermarkFacade {

    private static final Logger logger = LoggerFactory.getLogger(SysWatermarkFacadeImpl.class);

    @Resource
    public SysWatermarkService sysWatermarkService;

    @Override
    public ExecuteResult<PageInfo<SysWatermarkDTO>> selectWatermarks(SysWatermarkDTO sysWatermarkDTO) {
        ExecuteResult<PageInfo<SysWatermarkDTO>> result = new ExecuteResult<>();
        try {
            SysWatermarkModel model = DozerUtil.map(sysWatermarkDTO, SysWatermarkModel.class);
            Page<SysWatermarkModel> page = this.sysWatermarkService.selectWatermarks(model);
            PageInfo<SysWatermarkDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),SysWatermarkDTO.class);
            this.transType(pageInfo);
            result.setResult(pageInfo);
            result.setSuccessMessage("水印配置查询成功");
        }catch (Exception e){
            logger.error("水印配置查询失败",e);
            result.addErrorMessage("水印配置查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> createSysWatermark(SysWatermarkDTO sysWatermarkDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            Integer insertResult = this.sysWatermarkService.createSysWatermark(DozerUtil.map(sysWatermarkDTO, SysWatermark.class));
            result.setResult(insertResult);
            logger.info("水印保存成功");
            result.setSuccessMessage("水印保存成功");
        }catch (Exception e){
            result.addErrorMessage("水印保存失败");
            logger.error("水印保存失败", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> updateSysWatermark(SysWatermarkDTO sysWatermarkDTO) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            SysWatermark sysWatermark = new SysWatermark();
            DozerUtil.map(sysWatermarkDTO, sysWatermark);
            Integer insertResult = this.sysWatermarkService.updateSysWatermark(sysWatermark);
            result.setResult(insertResult);
            logger.info("水印保存成功");
            result.setSuccessMessage("水印保存成功");
        }catch (Exception e){
            result.addErrorMessage("水印保存失败");
            logger.error("水印保存失败", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<Integer> intoEffect(Long id) {
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            SysWatermark sysWatermark = this.sysWatermarkService.queryById(id);
            if(sysWatermark == null){
                result.addErrorMessage("设置失败");
                return result;
            }
            this.sysWatermarkService.updateOtherWatermarkStatusToDisable(id, sysWatermark.getType());
            sysWatermark.setStatus("1");
            sysWatermark.setLastModifyBy(SubjectUtil.getUser().getLoginName());
//            sysWatermark.setLastModifyTime(new Date());
            int updateId = this.sysWatermarkService.updateById(sysWatermark);
            logger.info("水印设置成功");
            result.setResult(updateId);
            result.setSuccessMessage("水印设置成功");
        }catch (Exception e){
            result.addErrorMessage("设置失败");
            logger.error("水印设置有效失败", e);
        }
        return result;
    }

    //手工处理枚举
    private void transType(PageInfo<SysWatermarkDTO> pageInfo){
        if(pageInfo != null && CollectionUtils.isNotEmpty(pageInfo.getList())){
            for(SysWatermarkDTO sysWatermarkDTO : pageInfo.getList()){
                try {
                    String typeDesc = Constant.WatermarkType.getByValue(sysWatermarkDTO.getType()).getDesc();
                    sysWatermarkDTO.setTypeDesc(typeDesc);
                    String textColorDesc = Constant.WatermarkTextColor.getByValue(sysWatermarkDTO.getColor()).getDesc();
                    sysWatermarkDTO.setColorDesc(textColorDesc);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ExecuteResult<Integer> delete(Long id){
        ExecuteResult<Integer> result = new ExecuteResult<>();
        try {
            SysWatermark sysWatermark = this.sysWatermarkService.queryById(id);
            if(sysWatermark == null){
                result.addErrorMessage("删除失败");
                return result;
            }
            sysWatermark.setDeleted(1);
            sysWatermark.setLastModifyTime(new Date());
            sysWatermark.setLastModifyBy(SubjectUtil.getUser().getLoginName());
            int updateId = this.sysWatermarkService.updateById(sysWatermark);
            result.setResult(updateId);
            logger.info("水印删除成功");
            result.setSuccessMessage("删除成功");
        }catch (Exception e){
            result.addErrorMessage("删除失败");
            logger.error("水印删除失败", e);
        }
        return result;
    }

    @Override
    public ExecuteResult<SysWatermarkDTO> selectWatermarkById(Long id){
        ExecuteResult<SysWatermarkDTO> result = new ExecuteResult<>();
        try {
            SysWatermarkModel model = new SysWatermarkModel();
            model.setId(id);
            model.setPageSize(10);
            model.setPage(1);
            Page<SysWatermarkModel> page = this.sysWatermarkService.selectWatermarks(model);
            PageInfo<SysWatermarkDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),SysWatermarkDTO.class);
            this.transType(pageInfo);
            if(pageInfo != null && CollectionUtils.isNotEmpty(pageInfo.getList())) {
                result.setResult(pageInfo.getList().get(0));
            }
            result.setSuccessMessage("水印查询成功");
            logger.error("水印查询成功");
        }catch (Exception e){
            logger.error("水印查询失败",e);
            result.addErrorMessage("水印配置查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<SysWatermarkDTO> selectWatermark(SysWatermarkDTO sysWatermarkDTO) {
        ExecuteResult<SysWatermarkDTO> result = new ExecuteResult<>();
        try {
            SysWatermark sysWatermark = sysWatermarkService.queryOne(DozerUtil.map(sysWatermarkDTO, SysWatermark.class));
            result.setResult(DozerUtil.map(sysWatermark , SysWatermarkDTO.class));
        }catch (Exception e){
            logger.error("水印查询失败",e);
            result.addErrorMessage("水印配置查询失败");
        }

        return result;
    }
}
