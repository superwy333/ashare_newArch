package com.zynsun.openplatform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-04-20 上午 9:18
 * @Modified By:
 */
public class EdocLoadConfig {
    public static final String CONFIG = "language_ZN.properties";

    private static Properties prop;
    private EdocLoadConfig(){}

    static{
        try {
            prop = new Properties();

            InputStreamReader is = null;

            if(new File(CONFIG).exists()){
                is = new InputStreamReader(new FileInputStream(new File(CONFIG)));
            } else {
                //is = com.zynsun.openplatform.util.EdocLoadConfig.class.getResourceAsStream("/" + CONFIG);

                is = new InputStreamReader(LoadConfig.class.getResourceAsStream("/" + CONFIG), "UTF-8");

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
