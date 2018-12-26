package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-04 下午 1:40
 * @Modified By:
 */

@Service("billWorkTodoFacade")
public interface BillWorkTodoFacade {


    /**
     * 自助签单
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<List<EdocHeaderDTO>> saveReceive(EdocHeaderDTO edocHeaderDTO);


    /**
     * 自助退单
     * @param edocHeaderDTO
     * @return
     */
    ExecuteResult<List<EdocHeaderDTO>> saveBack(EdocHeaderDTO edocHeaderDTO);





}
