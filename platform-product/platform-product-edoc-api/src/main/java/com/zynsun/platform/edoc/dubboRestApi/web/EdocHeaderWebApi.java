package com.zynsun.platform.edoc.dubboRestApi.web;


import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-16 11:20
 **/


public interface EdocHeaderWebApi {

    ExecuteResult<EdocHeaderDTO> selectEdocHeaderById(Long id);



}
