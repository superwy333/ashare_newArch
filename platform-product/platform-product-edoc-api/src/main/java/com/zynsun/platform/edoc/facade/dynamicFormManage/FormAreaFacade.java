package com.zynsun.platform.edoc.facade.dynamicFormManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.dynamicForm.FormAreaDTO;
import com.zynsun.platform.edoc.model.FormAreaModelDTO;
import com.zynsun.platform.edoc.vo.FormAreaVO;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:00
 **/
public interface FormAreaFacade {

    Map<String ,Object > findFormAreas();

    Map<String ,Object > findFileds( Long bizTypeId);

    /**
     * 查询区域列表
     * @param formAreaDTO
     * @return
     */
    ExecuteResult<List<FormAreaDTO>> findFormArea(FormAreaDTO formAreaDTO);

    /**
     * 根据id删除表单区域
     * @param id
     * @return
     */
    ExecuteResult<String> deleteFormAreaById(Long id);

    /**
     * 新增区域
     * @param formAreaDTO
     * @return
     */
    ExecuteResult<String> createFormArea(FormAreaDTO formAreaDTO);

    /**
     * 查询区域及关联
     * @param id
     * @return
     */
    ExecuteResult<FormAreaModelDTO> queryAreaModelDTOById(Long id);

    /**
     * 编辑区域
     * @param formAreaModelDTO
     * @return
     */
    ExecuteResult<String> editArea(FormAreaModelDTO formAreaModelDTO);

    ExecuteResult<FormAreaDTO> findOneFormArea(String areaCode);
}
