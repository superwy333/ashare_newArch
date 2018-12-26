package utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 14:59 2017/9/28
 * @Modified By:
 */
public class App {
    private static Properties properties = null;

    static {
        ClassPathResource resource = new ClassPathResource(
                "/application.properties");
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

    /**
     * 获取ap路径
     */
    public static String getApPath() {
        return getConfig("apPath");
    }

    /**
     * 获取ar路径
     */
    public static String getArPath(){
        return getConfig("arPath");
    }

    /**
     * 获取集中认证路径
     */
    public static String getCertPath() {
        return getConfig("certPath");
    }

    /**
     * 获取工作流路径
     */
    public static String getWfPath() {
        return getConfig("wfPath");
    }

    /**
     * 获取影像管理路径
     */
    public static String getEdocPath() {
        return getConfig("edocPath");
    }

    /**
     * 获取寄送管理路径
     */
    public static String getExpressPath() {
        return getConfig("expressPath");
    }
    /**
     * 获取index路径
     */
    public static String getIndexPath() {
        return getConfig("indexPath");
    }

    /**
     * 获取URL后缀
     */
    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }

    /**
     * 获得帮助维护文档URL后缀
     * @return
     */
    public static String getBasicPath() {
        return getConfig("basicPath");
    }

    /**
     * 获得供应商地址路径
     * @return
     */
    public static String getVssPath() {
        return getConfig("vssPath");
    }

    /**
     * 获得发票地址路径
     * @return
     */
    public static String getInvoicePath() {
        return getConfig("invoicePath");
    }
}
