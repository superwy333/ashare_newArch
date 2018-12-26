package com.zynsun.platform.utils;


import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jary on 2018/11/19/019.
 */
public class MapUtil {

    private static final String REQ_PARAM = "reqParam";
    private static final String REQ_INTERFACE_FLAG = "interfaceFlag";

    private static final String RESP_RESULT = "result";
    private static final String RESP_CODE = "code";
    private static final String RESP_MSG = "msg";
    private static final String RESP_SUCCESS = "success";

    /**
     * 返回前端结果集
     *
     * @param code
     * @param msg
     * @param success
     * @param result
     * @return
     */
    public static Map<String, Object> getResultMap(Map<String, Object> mapPram, Object code, Object msg, Object success, Object result) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESP_RESULT, result);
        map.put(RESP_CODE, code);
        map.put(RESP_MSG, msg);
        map.put(RESP_SUCCESS, success);
        return map;
    }


    public static Map<String, Object> getResultMap(Map<String, Object> mapPram, Object code, Object msg, Object success) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESP_CODE, code);
        map.put(RESP_MSG, msg);
        map.put(RESP_SUCCESS, success);
        return map;
    }

    public static Map<String, Object> getResultMap(Object code, Object msg, Object success) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESP_CODE, code);
        map.put(RESP_MSG, msg);
        map.put(RESP_SUCCESS, success);
        return map;
    }

    /**
     * 接口返回结果集
     *
     * @param code
     * @param msg
     * @param result
     * @return
     */
    public static String interResp(Integer code, Object msg, Object result) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESP_RESULT, result);
        map.put(RESP_CODE, code);
        map.put(RESP_MSG, msg);
        return new Gson().toJson(map);
    }

    /**
     * map转对象
     *
     * @param map
     * @param obj
     * @throws Exception
     */
    public static void map2Bean(Map<String, Object> map, Object obj) throws Exception {
        if (map == null || obj == null) {
            return;
        }
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPatterns(
                new String[]{
                        "yyyy-MM-dd",
                        "yyyy-MM-dd HH",
                        "yyyy-MM-dd HH:mm",
                        "yyyy-MM-dd HH:mm:ss"
                }
        );
        ConvertUtils.register(dateConverter, Date.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        BeanUtils.populate(obj, map);
    }

    public MapUtil() {
    }

    public static boolean getBoolean(Map<String, Object> map) throws Exception {
        return (boolean) map.get(RESP_SUCCESS);
    }

    public static Object getReqParam(Map<String, Object> map) throws Exception {
        return map.get(REQ_PARAM);
    }

    public static Object getInterfaceFlag(Map<String, Object> map) throws Exception {
        return String.valueOf(map.get(REQ_INTERFACE_FLAG));
    }

    public static Object getMsg(Map<String, Object> map) throws Exception {
        return map.get(RESP_MSG);
    }

    public static String getRESP_RESULT() {
        return RESP_RESULT;
    }

    public static String getRESP_CODE() {
        return RESP_CODE;
    }

    public static String getRESP_MSG() {
        return RESP_MSG;
    }

    public static String getRESP_SUCCESS() {
        return RESP_SUCCESS;
    }
}
