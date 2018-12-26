package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.openplatform.util.DozerUtil;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.domain.EdocAttach;
import com.zynsun.platform.edoc.domain.EdocImage;
import com.zynsun.platform.edoc.mapper.EdocAttachMapper;
import com.zynsun.platform.edoc.model.EdocAttachModel;
import com.zynsun.platform.edoc.service.EdocAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liangjiawei
 * @date 2018/4/26 13:48
 */
@Service
public class EdocAttachServiceImpl extends BaseServiceImpl<EdocAttach> implements EdocAttachService{

    @Autowired
    private EdocAttachMapper edocAttachMapper;

    @Override
    protected IMapper<EdocAttach> getBaseMapper() {
        return edocAttachMapper;
    }






    @Override
    public ExecuteResult<Integer> delAttachment(Long id) {

        return null;
    }

    /**
     * 根据条件查询图像和文件
     * @param edocAttachModel
     * @return
     */
    @Override
    public List<EdocAttachModel> selectImages(EdocAttachModel edocAttachModel) {
        List<EdocAttach> list =this.query(DozerUtil.map(edocAttachModel, EdocAttach.class));
        if (list != null && !list.isEmpty()) {
            return DozerUtil.mapList(list, EdocAttachModel.class);
        } else {
            return null;
        }
    }

    /**
     * 查询最大页码号
     * @param billHeaderId
     * @return
     */
    @Override
    public int queryMaxPagNum(Long billHeaderId) {
        return edocAttachMapper.queryMaxPageNoByBillHeaderId(billHeaderId);
    }

    /**
     * 根据条件查询edocAttach
     * @param edocAttach
     * @return
     */
    @Override
    public List<EdocAttach> queryEdcoAttach(EdocAttach edocAttach) {
        return edocAttachMapper.selectEdocAttach(edocAttach);
    }

    /**
     * 根据parentId查询
     * @param id
     * @return
     */
    @Override
    public List<EdocAttach> queryEdocAttachByParentId(Long id) {
        return edocAttachMapper.selectEdocAttachByParentId(id);
    }

    /**
     * 根据edcoHeaderId查找文件
     * @param id
     * @return
     */
    @Override
    public List<EdocAttach> selectImagesByEdocHeardId(Long id) {
        return edocAttachMapper.selectImagesByEdocHeardId(id);
    }

    @Override
    public List<EdocImage> queryByEdocAttach(EdocAttach edocAttach) {
        return edocAttachMapper.queryByEdocAttach(edocAttach);
    }
}
