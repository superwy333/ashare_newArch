package com.zynsun.platform.edoc.facade.edocClient;

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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-22 09:02
 **/
public interface EdocClientRestFacade {



    Map<String,Object> csTest();

    LoginCheckResponse login(@RequestBody LoginCheckRequest loginCheckRequest);

    BaseConfigResponse loadConfig(@RequestBody BaseConfigRequest baseConfigRequest);

    ExtConfigResponse loadExtConfig(@RequestBody ExtConfigRequest extConfigRequest);

    BeforeUploadResponse beforeUpload(@RequestBody BeforeUploadRequest beforeUploadRequest);

    DataUploadResponse dataUpload(@RequestBody DataUploadRequest dataUploadRequest);

    EndUploadResponse endUpload(@RequestBody EndUploadRequest uploadRequest);






}
