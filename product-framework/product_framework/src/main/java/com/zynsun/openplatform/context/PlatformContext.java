package com.zynsun.openplatform.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Ashare平台上下。可以保存登录信息等
 * @author david
 * @create 2017/5/17 21:57
 */
public class PlatformContext {

    /**
     * 上下文信息
     */
    private static final ThreadLocal<Map<String, Object>> variables = new ThreadLocal<Map<String, Object>>(){
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    public static void setVariable(String key, Object value){
        variables.get().put(key, value);
    }

    public static Object getVariable(String key){
        return variables.get().get(key);
    }

    public static Map<String, Object> getVariables() {
        return variables.get();
    }
}
