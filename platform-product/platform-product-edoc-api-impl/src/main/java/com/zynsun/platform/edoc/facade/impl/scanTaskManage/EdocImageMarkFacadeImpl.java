package com.zynsun.platform.edoc.facade.impl.scanTaskManage;

import com.github.pagehelper.Page;
import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.domain.EdocImageMark;
import com.zynsun.platform.edoc.domain.EdocImageMarkDetails;
import com.zynsun.platform.edoc.dto.EdocImageMarkDTO;
import com.zynsun.platform.edoc.dto.EdocImageMarkDetailsDTO;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocImageMarkFacade;
import com.zynsun.platform.edoc.mapper.EdocImageMarkMapper;
import com.zynsun.platform.edoc.model.EdocImageMarkModel;
import com.zynsun.platform.edoc.service.EdocImageMarkDetailsService;
import com.zynsun.platform.edoc.service.EdocImageMarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:43
 **/
@Service("edocImageMarkFacade")
public class EdocImageMarkFacadeImpl implements EdocImageMarkFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(EdocHeaderFacadeImpl.class);

    @Autowired
    private EdocImageMarkService edocImageMarkService;
    @Autowired
    private EdocImageMarkDetailsService edocImageMarkDetailsService;


    @Override
    public ExecuteResult<EdocImageMarkDTO> saveMarkRecord(EdocImageMarkDTO edocImageMarkDTO) {
        ExecuteResult<EdocImageMarkDTO> result = new ExecuteResult<>();
        try {
            Assert.notNull(edocImageMarkDTO,"保存的批注不能为空");
            EdocImageMark edocImageMark = DozerUtil.map(edocImageMarkDTO,EdocImageMark.class);
            edocImageMarkService.add(edocImageMark);
            result.setResult(DozerUtil.map(edocImageMark,EdocImageMarkDTO.class));
            result.setSuccessMessage("批注记录保存成功");
        }catch (Exception e) {
            LOGGER.error("批注记录保存失败{}",e);
            result.addErrorMessage("批注记录保存失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocImageMarkDetailsDTO> saveMarkDetail(EdocImageMarkDetailsDTO edocImageMarkDetailsDTO) {
        ExecuteResult<EdocImageMarkDetailsDTO> result = new ExecuteResult<>();
        try {
            Assert.notNull(edocImageMarkDetailsDTO,"保存的批注内容不能为空");
            EdocImageMarkDetails edocImageMarkDetails = DozerUtil.map(edocImageMarkDetailsDTO,EdocImageMarkDetails.class);
            edocImageMarkDetailsService.add(edocImageMarkDetails);
            result.setResult(DozerUtil.map(edocImageMarkDetails,EdocImageMarkDetailsDTO.class));
            result.setSuccessMessage("批注记录保存成功");
        }catch (Exception e) {
            LOGGER.error("批注记录保存失败{}",e);
            result.addErrorMessage("批注记录保存失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<EdocImageMarkDTO> queryById(Long edocMarkRecordId) {
        ExecuteResult<EdocImageMarkDTO> result = new ExecuteResult<>();
        try {
            EdocImageMark edocImageMark = edocImageMarkService.queryById(edocMarkRecordId);
            result.setResult(DozerUtil.map(edocImageMark,EdocImageMarkDTO.class));
            result.setSuccessMessage("查询成功");
        }catch (Exception e) {
            LOGGER.error("查询失败{}",e);
            result.addErrorMessage("查询失败");
        }
        return result;
    }

    @Transactional
    @Override
    public ExecuteResult<String> deleteMarkRecordAndDetails(long edocMarkRecordId) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocImageMark edocImageMark = edocImageMarkService.queryById(edocMarkRecordId);
            edocImageMarkService.remove(edocImageMark);
            EdocImageMarkDetails edocImageMarkDetails = new EdocImageMarkDetails();
            edocImageMarkDetails.setEdocImageMarkId(edocMarkRecordId);
            List<EdocImageMarkDetails> edocImageMarkDetailsList = edocImageMarkDetailsService.query(edocImageMarkDetails);
            for (EdocImageMarkDetails e:edocImageMarkDetailsList) {
                edocImageMarkDetailsService.remove(e);
            }
            result.setSuccessMessage("删除影像批注成功");
        }catch (Exception e) {
            LOGGER.error("删除影像批注失败{}",e);
            result.addErrorMessage("删除影像批注失败");
        }
        return result;
    }

    @Transactional
    @Override
    public ExecuteResult<String> modifyMarkRecordAndDetails(EdocImageMarkDTO edocImageMarkDTO, List<EdocImageMarkDetailsDTO> edocImageMarkDetailsDTOList) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            List<EdocImageMarkDetailsDTO> edocImageMarkDetailsDTOListInDB = queryMarkDetailsByMarkId(edocImageMarkDTO.getId()).getResult();
            for (EdocImageMarkDetailsDTO edocImageMarkDetailsDTO:edocImageMarkDetailsDTOListInDB) {
                edocImageMarkDetailsService.remove(DozerUtil.map(edocImageMarkDetailsDTO,EdocImageMarkDetails.class));
            }
            for (EdocImageMarkDetailsDTO e:edocImageMarkDetailsDTOList) {
                // 特殊处理一下Id
                e.setEdocImageMarkId(edocImageMarkDTO.getId());
                saveMarkDetail(e);
            }
            result.setSuccessMessage("保存影像批注成功");
        }catch (Exception e) {
            LOGGER.error("保存影像批注失败{}",e);
            result.addErrorMessage("保存影像批注失败");
        }
        return result;
    }

    @Transactional
    @Override
    public ExecuteResult<String> saveMarkRecordAndDetails(EdocImageMarkDTO edocImageMarkDTO, List<EdocImageMarkDetailsDTO> edocImageMarkDetailsDTOList) {
        ExecuteResult<String> result = new ExecuteResult<>();
        try {
            EdocImageMarkDTO edocImageMarkDTOInDB = saveMarkRecord(edocImageMarkDTO).getResult();
            for (EdocImageMarkDetailsDTO e:edocImageMarkDetailsDTOList) {
                // 特殊处理一下Id
                e.setEdocImageMarkId(edocImageMarkDTOInDB.getId());
                saveMarkDetail(e);
            }
            result.setSuccessMessage("保存影像批注成功");
        }catch (Exception e) {
            LOGGER.error("批注保存失败{}",e);
            result.addErrorMessage("批注保存失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocImageMarkDTO>> queryMarkRecordsByEdocImageId(Long edocImageId) {
        ExecuteResult<List<EdocImageMarkDTO>> result = new ExecuteResult<>();
        try {
            EdocImageMark queryCondition = new EdocImageMark();
            queryCondition.setEdocImageId(edocImageId);
            List<EdocImageMark> edocImageList = edocImageMarkService.query(queryCondition);
            result.setResult(DozerUtil.mapList(edocImageList,EdocImageMarkDTO.class));
            result.setSuccessMessage("批注查询成功");
        }catch (Exception e) {
            LOGGER.error("批注查询失败{}",e);
            result.addErrorMessage("批注查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<PageInfo<EdocImageMarkDTO>> queryMarkRecordsPageList(EdocImageMarkDTO edocImageMarkDTO) {
        ExecuteResult<PageInfo<EdocImageMarkDTO>> result = new ExecuteResult<>();
        try {
            EdocImageMarkModel edocImageMarkModel = DozerUtil.map(edocImageMarkDTO, EdocImageMarkModel.class);
            Page<EdocImageMarkModel> page = edocImageMarkService.queryMarkRecordsPageList(edocImageMarkModel);
            PageInfo<EdocImageMarkDTO> pageInfo = DozerUtil.mapPage(page.toPageInfo(),EdocImageMarkDTO.class);
            result.setResult(pageInfo);
            result.setSuccessMessage("批注分页查询成功");
        }catch (Exception e) {
            LOGGER.error("批注分页查询失败{}",e);
            result.addErrorMessage("批注分页查询失败");
        }
        return result;
    }

    @Override
    public ExecuteResult<List<EdocImageMarkDetailsDTO>> queryMarkDetailsByMarkId(Long edocImageMarkId) {
        ExecuteResult<List<EdocImageMarkDetailsDTO>> result = new ExecuteResult<>();
        try {
            EdocImageMarkDetails queryCondition = new EdocImageMarkDetails();
            queryCondition.setEdocImageMarkId(edocImageMarkId);
            List<EdocImageMarkDetails> edocImageMarkDetailsList = edocImageMarkDetailsService.query(queryCondition);
            result.setResult(DozerUtil.mapList(edocImageMarkDetailsList,EdocImageMarkDetailsDTO.class));
            result.setSuccessMessage("批注内容查询成功");
        }catch (Exception e) {
            LOGGER.error("批注内容查询失败{}",e);
            result.addErrorMessage("批注内容查询失败");
        }
        return result;
    }
}
