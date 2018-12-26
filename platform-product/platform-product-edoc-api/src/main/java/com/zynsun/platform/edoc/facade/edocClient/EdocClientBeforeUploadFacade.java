package com.zynsun.platform.edoc.facade.edocClient;

import com.zynsun.openplatform.util.ExecuteResult;
import com.zynsun.platform.edoc.dto.edocClient.baseConfig.request.BaseConfigRequest;
import com.zynsun.platform.edoc.dto.edocClient.baseConfig.response.BaseConfigResponse;
import com.zynsun.platform.edoc.dto.edocClient.extConfig.request.ExtConfigRequest;
import com.zynsun.platform.edoc.dto.edocClient.extConfig.response.ExtConfigResponse;
import com.zynsun.platform.edoc.dto.edocClient.login.request.LoginCheckRequest;
import com.zynsun.platform.edoc.dto.edocClient.login.response.LoginCheckResponse;

/**
 * 采集客户端上传前接口
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/05 12:55
 */
public interface EdocClientBeforeUploadFacade {

    /**
     * 采集客户端登录
     *
     * @param loginCheckRequest
     * @return
     */
    public ExecuteResult<LoginCheckResponse> login(LoginCheckRequest loginCheckRequest);

    /**
     * 业务配置信息获取
     *
     * @param baseConfigRequest
     * @return
     */
    public ExecuteResult<BaseConfigResponse> loadConfig(BaseConfigRequest baseConfigRequest);

    /**
     * 扩展业务信息获取
     * @param extConfigRequest
     * @return
     */
    public ExecuteResult<ExtConfigResponse> loadExtConfig(ExtConfigRequest extConfigRequest);
}
