package com.zynsun.platform.edoc.mapper;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.platform.edoc.domain.EdocAttach;
import com.zynsun.platform.edoc.domain.EdocImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Liangjiawei
 * @date 2018/4/19 16:40
 */
public interface EdocAttachMapper extends IMapper<EdocAttach> {
    /**
     * 查询当前最大页码
     * @param billHeaderId
     * @return
     */
    int queryMaxPageNoByBillHeaderId(@Param(value = "billHeaderId") Long billHeaderId);

    /**
     * 根据条件查询edocAttach
     * @param edocAttach
     * @return
     */
    List<EdocAttach> selectEdocAttach(@Param(value = "edocAttach") EdocAttach edocAttach);

    /**
     * 根据parentId查询
     * @param id
     * @return
     */
    List<EdocAttach> selectEdocAttachByParentId(Long id);

    /**
     * 根据edcoHeaderId查找文件
     * @param id
     * @return
     */
    List<EdocAttach> selectImagesByEdocHeardId(Long id);

    List<EdocImage> queryByEdocAttach(@Param(value = "edocAttach")EdocAttach edocAttach);
}
