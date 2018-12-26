package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.TaskParamDTO;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 16:27 2018/1/4
 * @Modified By:
 */
public interface ReviewFacade {

    ExecuteResult<Boolean> review(TaskParamDTO taskParamDTO);
}
