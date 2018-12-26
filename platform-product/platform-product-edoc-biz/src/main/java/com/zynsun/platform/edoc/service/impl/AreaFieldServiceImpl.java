package com.zynsun.platform.edoc.service.impl;

import com.zynsun.openplatform.mapper.IMapper;
import com.zynsun.openplatform.service.impl.BaseServiceImpl;
import com.zynsun.platform.edoc.domain.AreaField;
import com.zynsun.platform.edoc.domain.FormArea;
import com.zynsun.platform.edoc.mapper.AreaFieldMapper;
import com.zynsun.platform.edoc.service.AreaFieldService;
import com.zynsun.platform.edoc.service.FormAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：
 * @Author：YongChen
 * @Date：Created in 2018/5/23 14:13
 * @Modified By：
 */
@Service
public class AreaFieldServiceImpl  extends BaseServiceImpl<AreaField> implements AreaFieldService {

    @Autowired
    private AreaFieldMapper areaFieldMapper;

    @Autowired
    private FormAreaService formAreaService;

    @Override
    protected IMapper<AreaField> getBaseMapper() {
        return areaFieldMapper;
    }

    @Override
    public int AddAreaAndField(FormArea waitAddArea, List<AreaField> areaFieldList) {
        int result = 0;
        // 新增区域
        int addAreaResult = formAreaService.add(waitAddArea);
        if (addAreaResult <= 0) {
            return result;
        }
        Long formAreaId = waitAddArea.getId();
        // 新增区域和字段关联关系
        for (AreaField areaField : areaFieldList) {
            areaField.setFormAreaId(formAreaId);
            int addAreaFieldResult = this.add(areaField);
            if (addAreaFieldResult <= 0) {
                return result;
            }
        }
        result = 1;
        return result;
    }

    @Override
    public int editAreaAndField(FormArea waitEditArea, List<AreaField> areaFieldList) {
        int result = 0;
        int editAreaResult = formAreaService.updateByIdSelective(waitEditArea);
        if (editAreaResult <= 0) {
            return result;
        }
        areaFieldMapper.deleteByAreaId(waitEditArea.getId());
        for (AreaField areaField : areaFieldList) {
            areaField.setFormAreaId(waitEditArea.getId());
            int addAreaFieldResult = this.add(areaField);
            if (addAreaFieldResult <= 0) {
                return result;
            }
        }
        result = 1;
        return result;
    }
}
