import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zynsun.platform.edoc.domain.EdocHeader;
import webservice.oa.oautil.OAUtil;
import webservice.oa.service.ILbpmextWebserviceService;
import webservice.oa.service.ILbpmextWebserviceServiceService;
import webservice.oa.service.ILbpmextWebserviceServiceServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-07-21 18:26
 **/
public class WSTest {

    public static void main(String[] args) throws Exception {

        Map<String, String> requestMap = new HashMap<>();
        EdocHeader edocHeader = new EdocHeader();
        edocHeader.setProcessId("1649d2d06251f5849a2d84c43cbbec14");



        requestMap.put("processId", edocHeader.getProcessId());
        requestMap.put("fdModelName", "");
        requestMap.put("operationType", "handler_refuse");
        requestMap.put("auditNote", "不同意");
        requestMap.put("handler", "yangcm");

        Map<String, String> responseMap = approveProcess(requestMap);
        System.out.println("code: " + responseMap.get("code"));
        System.out.println("msg: " + responseMap.get("msg"));

    }


    public static Map<String, String> approveProcess(Map<String, String> requestParm) throws ServiceException, RemoteException {
        ILbpmextWebserviceServiceService service = new ILbpmextWebserviceServiceServiceLocator();
        ILbpmextWebserviceService port = service.getILbpmextWebserviceServicePort();

        String requestStr = JSON.toJSONString(requestParm);
        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("fdModelName",requestParm.get("fdModelName"));
        jsonObject.put("processId",requestParm.get("processId"));
        String str = jsonObject.toString();

        //LOGGER.info("OA请求："+requestStr);
        System.out.println(str);

        String responseStr = port.approveProcess(str);
        JSONObject responseJasonObject = JSON.parseObject(responseStr);
        Map<String, String> responseMap = (Map)responseJasonObject;
        return responseMap;
    }
}
