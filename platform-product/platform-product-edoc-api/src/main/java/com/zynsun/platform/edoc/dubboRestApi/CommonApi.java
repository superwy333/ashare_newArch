package com.zynsun.platform.edoc.dubboRestApi;


import com.zynsun.openplatform.util.ExecuteResult;

import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-15 21:12
 **/
public interface CommonApi {

    ExecuteResult<Object> doFunc(Map<String,Object> map);



}
