package com.zynsun.platform.edoc.service;

import com.zynsun.openplatform.service.BaseService;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocAttach;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.model.EdocAttachModel;

import java.util.List;

/**
 * @author Liangjiawei
 * @date 2018/4/26 13:48
 */
public interface EdocAttachService extends BaseService<EdocAttach>{

    /**
     * 删除附件
     * @param id
     * @return
     */
    ExecuteResult<Integer> delAttachment(Long id);

    /**
     * 根据条件查询图像和文件
     * @param edocAttachModel
     * @return
     */
    List<EdocAttachModel> selectImages(EdocAttachModel edocAttachModel);

    /**
     * 查询最大页码号
     * @param billHeaderId
     * @return
     */
    int queryMaxPagNum(Long billHeaderId);

    /**
     * 根据条件查询edocAttach
     * @param edocAttach
     * @return
     */
    List<EdocAttach> queryEdcoAttach(EdocAttach edocAttach);

    /**
     * 根据parentId查询图片
     * @param id
     * @return
     */
    List<EdocAttach> queryEdocAttachByParentId(Long id);

    /**
     * 根据edcoHeaderId查找文件
     * @param id
     * @return
     */
    List<EdocAttach> selectImagesByEdocHeardId(Long id);

    ////根据附件信息去查询影像信息
    List<EdocImage> queryByEdocAttach(EdocAttach edocAttach);
}
