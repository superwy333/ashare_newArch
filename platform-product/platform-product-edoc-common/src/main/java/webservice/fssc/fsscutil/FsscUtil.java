package webservice.fssc.fsscutil;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webservice.fssc.dto.F_BODY;
import webservice.fssc.service.*;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.List;

public class FsscUtil {
    private static final Logger FSSC_LOGGER = LoggerFactory.getLogger(FsscUtil.class);
    /**
     * 使用接口：是否可归档，是否可认证，获取凭证号
     *
     * @param fbody 请求数据
     * @param serviceId 接口编号
     * @return
     * @throws RemoteException
     * @throws ServiceException
     */
    public static F_BODY getBody(String fbody, String serviceId, String url) throws RemoteException, ServiceException {
        ApiService_callApiService service = new ApiService_callApiServiceLocator();
        ((ApiService_callApiServiceLocator) service).setBISSendPortEndpointAddress(url);
        ApiService_callApi bisSendPort = service.getBISSendPort();
        Input input = new Input();
        input.setF_BODY(fbody);
        input.setF_IS_BASE64("0");
        input.setF_SERVICE_ID(serviceId);
        input.setF_SYSTEM_ID("EDOC");
        input.setF_SYSTEM_USER_PASS("newhope1234");
        FSSC_LOGGER.info("FSSC接口请求信息：{}",JSON.toJSONString(input));
        System.out.println(JSON.toJSONString(input));
        Output output = bisSendPort.callApi(input);
        String f_body = output.getF_BODY();
        F_BODY returnFbody = JSON.parseObject(f_body, F_BODY.class);
        FSSC_LOGGER.info("FSSC接口返回结果：{}", JSON.toJSONString(returnFbody));

        return returnFbody;
    }

    /**
     * 获取费用类型
     *
     * @param serviceId
     * @return
     * @throws RemoteException
     * @throws ServiceException
     */
    public static List<F_BODY> getCostTypeBody(String serviceId, String url) throws RemoteException, ServiceException {
        ApiService_callApiService service = new ApiService_callApiServiceLocator();
        ((ApiService_callApiServiceLocator) service).setBISSendPortEndpointAddress(url);
        ApiService_callApi bisSendPort = service.getBISSendPort();
        Input input = new Input();
        input.setF_IS_BASE64("0");
        input.setF_SERVICE_ID(serviceId);
        input.setF_SYSTEM_ID("EDOC");
        input.setF_SYSTEM_USER_PASS("newhope1234");
        FSSC_LOGGER.info("FSSC接口【获取费用类型】请求信息：{}",JSON.toJSONString(input));
        Output output = bisSendPort.callApi(input);
        String f_body = output.getF_BODY();
        List<F_BODY> f_bodies = JSON.parseArray(f_body, F_BODY.class);
        System.out.println(JSON.toJSONString(f_bodies));
        FSSC_LOGGER.info("FSSC接口【获取费用类型】返回结果：{}", JSON.toJSONString(f_bodies));

        return f_bodies;
    }

    public static void main(String[] args) throws RemoteException, ServiceException {
        String url = "http://118.122.93.246:8082/NewHope/EAIServer?Invoke=BizSoapService%7CApiService%7CcallApi";
        List<F_BODY>  mdm_api_oa_ywsx = getCostTypeBody("MDM_API_OA_YWSX", url);
        System.out.println(mdm_api_oa_ywsx.size());
        for (F_BODY mdmApiOaYwsx : mdm_api_oa_ywsx) {
            String f_id = mdmApiOaYwsx.getF_ID();
            System.out.println(f_id);
        }
    }

}
