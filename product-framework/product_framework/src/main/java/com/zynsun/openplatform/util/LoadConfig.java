package com.zynsun.openplatform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-04-20 上午 9:18
 * @Modified By:
 */
public class LoadConfig {
    public static final String CONFIG = "config.properties";

    private static Properties prop;
    private LoadConfig(){}

    static{
        try {
            prop = new Properties();

            InputStream is = null;
            if(new File(CONFIG).exists()){
                is = new FileInputStream(new File(CONFIG));
            } else {
                is = LoadConfig.class.getResourceAsStream("/" + CONFIG);
            }
            if(is != null){
                prop.load(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String name){
        return prop.getProperty(name, "").trim();
    }
}
