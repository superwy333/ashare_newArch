package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocAttachDTO;

import java.util.List;

/**
 * @author Liangjiawei
 * @date 2018/4/26 13:39
 */
public interface EdocAttachFacade {

    ExecuteResult<Integer> delAttachment(Long id);

    /**
     * 根据条件查询图像和文件
     * @param edocAttachDTO
     * @return
     */
    ExecuteResult<List<EdocAttachDTO>> selectImages(EdocAttachDTO edocAttachDTO);

    /**
     * 根据影像任务id查询图片页码
     * @param billHeaderId
     * @return
     */
    ExecuteResult<Integer> queryMaxPagNum(Long billHeaderId);

    /**
     * 上传文件
     * @param fullPath
     * @param fileName
     * @param fileExtName
     * @param pageNo
     * @param imageUrl
     * @param billHeaderId
     * @return
     */
    ExecuteResult<String> uploadFiles(String fullPath, String fileName, String fileExtName, String pageNo, String imageUrl, Long billHeaderId);

    /**
     * 根据条件查询图像和文件
     * @param edocAttachDTO
     * @return
     */
    ExecuteResult<EdocAttachDTO> findOneEdcoAttach(EdocAttachDTO edocAttachDTO);

    /**
     * 根据id查找文件
     * @param id
     * @return
     */
    ExecuteResult<List<EdocAttachDTO>> findEdocAttachByParentId(Long id);

    /**
     * 根据edcoHeaderId查找文件
     * @param edcoHeaderId
     * @return
     */
    ExecuteResult<List<EdocAttachDTO>> selectImagesByEdocHeardId(Long edcoHeaderId);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ExecuteResult<List<EdocAttachDTO>> findByBillHeader(Long id);

}
