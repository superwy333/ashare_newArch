package com.zynsun.platform.edoc.utils;


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
    /**
     *返回前端结果集
     * @param code
     * @param msg
     * @param success
     * @param result
     * @return
     */
    public static Map<String, Object> getResultMap(Map<String, Object> mapPram, Object code, Object msg, Object success, Object result) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        map.put("code", code);
        map.put("msg", msg);
        map.put("success", success);
        return map;
    }

    public static Map<String, Object> getResultMap(Map<String, Object> mapPram, Object code, Object msg, Object success) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("success", success);
        return map;
    }

    public static Map<String, Object> getResultMap(Object code, Object msg, Object success) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("success", success);
        return map;
    }
    /**
     * map转对象
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

}
