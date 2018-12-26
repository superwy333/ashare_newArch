package utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class MuleConfig {
    private static Properties properties = null;

    static {
        ClassPathResource resource = new ClassPathResource(
                "/mule.properties");
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 获取配置
     *
     * @see ${fns:getConfig('Path')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = properties.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

}
