package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocScanRemarks;
import com.zynsun.platform.edoc.dto.EdocScanRemarksDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocScanRemarksFacade;
import com.zynsun.platform.edoc.model.EdocScanRemarksModel;
import com.zynsun.platform.edoc.service.EdocScanRemarksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-11 14:03
 **/
@Service("edocScanRemarksFacade")
public class EdocScanRemarksFacadeImpl implements EdocScanRemarksFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(EdocHeaderFacadeImpl.class);

    @Autowired
    private EdocScanRemarksService edocScanRemarksService;

    @Override
    public ExecuteResult<String> saveEdocScanRemarks(EdocScanRemarksDTO edocScanRemarksDTO) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocScanRemarks edocScanRemarks = DozerUtil.map(edocScanRemarksDTO,EdocScanRemarks.class);
            edocScanRemarksService.add(edocScanRemarks);
            result.setSuccessMessage("新增成功");
        }catch (Exception e) {
            LOGGER.error("新增失败{}",e);
            result.addErrorMessage("新增失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<EdocScanRemarksDTO>> queryImageHis(EdocScanRemarksDTO edocScanRemarksDTO) {
        ExecuteResult<PageInfo<EdocScanRemarksDTO>> result = new ExecuteResult<>();
        try {
            EdocScanRemarksModel edocScanRemarksModel = DozerUtil.map(edocScanRemarksDTO,EdocScanRemarksModel.class);
            Page<EdocScanRemarksModel> page = edocScanRemarksService.queryByPage(edocScanRemarksModel);
            PageInfo<EdocScanRemarksDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),EdocScanRemarksDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("分页查询成功");
        }catch (Exception e) {
            LOGGER.error("分页查询失败{}",e);
            result.addErrorMessage("新增失败");
        }
        return result;
    }
}
