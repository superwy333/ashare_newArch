package com.zynsun.platform.edoc.dubboRestApi.web;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dubboRestApi.CommonApi;

import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-15 22:29
 **/
public interface CommonWebApi extends CommonApi {

    ExecuteResult<Object> dataGrid(Map<String,Object> map);

    ExecuteResult<Object> save(Map<String,Object> map);

    ExecuteResult<Object> update(Map<String,Object> map);

    ExecuteResult<Object> remove(Map<String,Object> map);

    ExecuteResult<Object> combobox(Map<String,Object> map);

}
