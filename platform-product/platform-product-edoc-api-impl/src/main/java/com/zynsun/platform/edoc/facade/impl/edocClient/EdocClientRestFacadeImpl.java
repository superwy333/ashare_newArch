package com.zynsun.platform.edoc.facade.impl.edocClient;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.edocClient.baseConfig.request.BaseConfigRequest;
import com.zynsun.platform.edoc.dto.edocClient.baseConfig.response.BaseConfigResponse;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.request.BeforeUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.beforeUpload.response.BeforeUploadResponse;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.request.DataUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.dataUpload.response.DataUploadResponse;
import com.zynsun.platform.edoc.dto.edocClient.endUpload.request.EndUploadRequest;
import com.zynsun.platform.edoc.dto.edocClient.endUpload.response.EndUploadResponse;
import com.zynsun.platform.edoc.dto.edocClient.extConfig.request.ExtConfigRequest;
import com.zynsun.platform.edoc.dto.edocClient.extConfig.response.ExtConfigResponse;
import com.zynsun.platform.edoc.dto.edocClient.login.request.LoginCheckRequest;
import com.zynsun.platform.edoc.dto.edocClient.login.response.LoginCheckResponse;
import com.zynsun.platform.edoc.facade.edocClient.EdocClientBeforeUploadFacade;
import com.zynsun.platform.edoc.facade.edocClient.EdocClientRestFacade;
import com.zynsun.platform.edoc.facade.edocClient.EdocClientUploadFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-22 09:02
 **/
@Path("edocClient")
@Service("edocClientRestFacade")
public class EdocClientRestFacadeImpl implements EdocClientRestFacade {


    @Autowired
    private EdocClientBeforeUploadFacade edocClientBeforeUploadFacade;
    @Autowired
    private EdocClientUploadFacade edocClientUploadFacade;


    @POST
    @Path("csTest")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public Map<String, Object> csTest() {
        Map<String,Object> result = new HashMap<>();
        result.put("code","SUCCESS");
        return result;
    }

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public LoginCheckResponse login(LoginCheckRequest loginCheckRequest) {
        return edocClientBeforeUploadFacade.login(loginCheckRequest).getResult();
    }

    @POST
    @Path("loadConfig")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public BaseConfigResponse loadConfig(BaseConfigRequest baseConfigRequest) {
        return edocClientBeforeUploadFacade.loadConfig(baseConfigRequest).getResult();
    }

    @POST
    @Path("loadExtConfig")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public ExtConfigResponse loadExtConfig(ExtConfigRequest extConfigRequest) {
        return edocClientBeforeUploadFacade.loadExtConfig(extConfigRequest).getResult();
    }

    @POST
    @Path("beforeUpload")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public BeforeUploadResponse beforeUpload(BeforeUploadRequest beforeUploadRequest) {
        return edocClientUploadFacade.beforeUpload(beforeUploadRequest).getResult();
    }

    @POST
    @Path("dataUpload")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public DataUploadResponse dataUpload(DataUploadRequest dataUploadRequest) {
        return edocClientUploadFacade.dataUpload(dataUploadRequest).getResult();
    }

    @POST
    @Path("endUpload")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    @Override
    public EndUploadResponse endUpload(EndUploadRequest uploadRequest) {
        return edocClientUploadFacade.endUpload(uploadRequest).getResult();
    }
}
