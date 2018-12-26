package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocImageMarkDTO;
import com.zynsun.platform.edoc.dto.EdocImageMarkDetailsDTO;

import java.util.List;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:43
 **/
public interface EdocImageMarkFacade {

    ExecuteResult<EdocImageMarkDTO> saveMarkRecord(EdocImageMarkDTO edocImageMarkDTO);

    ExecuteResult<EdocImageMarkDetailsDTO> saveMarkDetail(EdocImageMarkDetailsDTO edocImageMarkDetailsDTO);

    ExecuteResult<String> deleteMarkRecordAndDetails(long edocMarkRecordId);

    ExecuteResult<String> modifyMarkRecordAndDetails(EdocImageMarkDTO edocImageMarkDTO,List<EdocImageMarkDetailsDTO> edocImageMarkDetailsDTOList);

    ExecuteResult<String> saveMarkRecordAndDetails(EdocImageMarkDTO edocImageMarkDTO,List<EdocImageMarkDetailsDTO> edocImageMarkDetailsDTOList);

    ExecuteResult<List<EdocImageMarkDTO>> queryMarkRecordsByEdocImageId(Long edocImageId);

    ExecuteResult<PageInfo<EdocImageMarkDTO>> queryMarkRecordsPageList(EdocImageMarkDTO edocImageMarkDTO);

    ExecuteResult<List<EdocImageMarkDetailsDTO>> queryMarkDetailsByMarkId(Long edocImageMarkId);

    ExecuteResult<EdocImageMarkDTO> queryById(Long edocMarkRecordId);








}
