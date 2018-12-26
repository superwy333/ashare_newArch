package com.zynsun.platform.edoc.facade.scanTaskManage;

import com.zynsun.openplatform.page.PageInfo;
import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-15 21:48
 **/
public interface BaseFacade<T> {

    ExecuteResult<PageInfo<T>> dataGrid(T t);
//    ExecuteResult<EdocHeaderDTO> dataDetails(T t);

    ExecuteResult<T> save(T t);

}
