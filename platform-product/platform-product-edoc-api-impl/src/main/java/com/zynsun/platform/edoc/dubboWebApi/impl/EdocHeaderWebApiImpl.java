package com.zynsun.platform.edoc.dubboWebApi.impl;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.EdocHeaderDTO;
import com.zynsun.platform.edoc.dubboRestApi.web.EdocHeaderWebApi;
import com.zynsun.platform.edoc.facade.scanTaskManage.EdocHeaderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-16 11:22
 **/
@Path("edocHeaderWebApi")
@Service("edocHeaderWebApi")
public class EdocHeaderWebApiImpl implements EdocHeaderWebApi {

    @Autowired
    private EdocHeaderFacade edocHeaderFacade;

    @POST
    @Path("selectEdocHeaderById")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public ExecuteResult<EdocHeaderDTO> selectEdocHeaderById(Long id) {
        return edocHeaderFacade.selectEdocHeaderById(id);
    }
}
