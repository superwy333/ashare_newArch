package com.zynsun.openplatform.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-04-19 下午 8:06
 * @Modified By:
 */
public class HttpClientUtil {


    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    public static String httpPostWithJSON(String url,String par) {
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        //解决中文乱码问题
        StringEntity entity = new StringEntity(par,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        try{
            HttpResponse resp = client.execute(httpPost);
            //System.out.println(resp.getStatusLine().getStatusCode());
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he,"UTF-8");
            }
        } catch (Exception e) {
        }
        return respContent;
    }


    public static String httpPostWithForm(String url, Map<String, Object> map) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(new BasicHeader("contentType", "application/x-www-form-urlencoded;charset=UTF-8"));

        if (map != null) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        try{
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 返回响应体的内容
                return EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            }
        } catch (Exception e) {
            logger.error("调用http服务失败{}", e);
        }
        return null;
    }


    /*public static String doPost(String url, Map<String,String> map, String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpClient.getParams().setIntParameter("http.socket.timeout",60000);
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }*/
}
