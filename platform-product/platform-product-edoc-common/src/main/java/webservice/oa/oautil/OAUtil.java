package webservice.oa.oautil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webservice.oa.service.ILbpmextWebserviceService;
import webservice.oa.service.ILbpmextWebserviceServiceService;
import webservice.oa.service.ILbpmextWebserviceServiceServiceLocator;
import webservice.oa.service.automatictaskservice.IAutomaticTaskWebservice;
import webservice.oa.service.automatictaskservice.IAutomaticTaskWebserviceService;
import webservice.oa.service.automatictaskservice.IAutomaticTaskWebserviceServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * 费控接口
 */
public class OAUtil {
    private static final Logger OA_LOGGER = LoggerFactory.getLogger(OAUtil.class);


    /**
     * 通知接口: 通知费控发起流程
     *
     * @param requestParm 请求参数map
     * @return 返回map结果集
     */
    public static Map<String, String> approveProcess(Map<String, String> requestParm, String url) throws ServiceException, RemoteException {
        ILbpmextWebserviceServiceService service = new ILbpmextWebserviceServiceServiceLocator();
        ((ILbpmextWebserviceServiceServiceLocator) service).setILbpmextWebserviceServicePortEndpointAddress(url);
        ILbpmextWebserviceService port = service.getILbpmextWebserviceServicePort();

        String requestStr = JSON.toJSONString(requestParm);
        OA_LOGGER.info("OA通知接口请求信息：{}", requestStr);
        String responseStr = port.approveProcess(requestStr);
        OA_LOGGER.info("OA通知接口返回结果：{}", JSON.toJSONString(responseStr));

        JSONObject responseJasonObject = JSON.parseObject(responseStr);
        Map<String, String> responseMap = (Map)responseJasonObject;
        return responseMap;
    }

    /**
     * 影像自动任务接口
     *
     * @param url 请求路径
     * @param edocNo 单号
     */
    public static void automaticTask(String url, String edocNo) throws RemoteException, ServiceException {

        IAutomaticTaskWebserviceService service = new IAutomaticTaskWebserviceServiceLocator();
        ((IAutomaticTaskWebserviceServiceLocator)service).setIAutomaticTaskWebservicePortEndpointAddress(url);

        IAutomaticTaskWebservice port =  service.getIAutomaticTaskWebservicePort();
        OA_LOGGER.info("影像自动任务请求信息：{}", edocNo);
        String result = port.getAutomaticTask(edocNo);
        OA_LOGGER.info("影像自动任务返回结果：{}", result);

    }


    /**
     * @param requestParm 请求参数map
     * @return 返回map结果集
     */
    public static Map<String, String> approveProcessForJson(Map<String, String> requestParm) throws ServiceException, RemoteException {
        ILbpmextWebserviceServiceService service = new ILbpmextWebserviceServiceServiceLocator();
        ILbpmextWebserviceService port = service.getILbpmextWebserviceServicePort();

        //String requestStr = JSON.toJSONString(requestParm);

        String requestStr;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("processId", requestParm.get("processId"));
        jsonObject.put("fdModelName", requestParm.get("fdModelName"));
        jsonObject.put("operationType", requestParm.get("operationType"));
        jsonObject.put("auditNote", requestParm.get("auditNote"));
        jsonObject.put("handler", requestParm.get("handler"));
        requestStr = jsonObject.toString();





        String responseStr = port.approveProcess(requestStr);
        JSONObject responseJasonObject = JSON.parseObject(responseStr);
        Map<String, String> responseMap = (Map)responseJasonObject;
        return responseMap;
    }



    /**
     * 接口测试
     * @param args
     * @throws ServiceException
     * @throws RemoteException
     */
    public static void main(String[] args) throws ServiceException, RemoteException {

        String url = "http://testcoa.newhopegroup.com:8080/sys/webservice/automaticTaskWebservice?wsdl";

        automaticTask(url, "OFZBTJ20180928002");
    }

}
