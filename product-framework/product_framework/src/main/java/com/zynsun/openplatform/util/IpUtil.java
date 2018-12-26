package com.zynsun.openplatform.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nicyea on 2016/4/27.
 */
public class IpUtil {

    private static final String[] REQUEST_HEADERS = { "x-forwarded-for", "HEADER_X_FORWARDED_FOR", "Proxy-Client-IP", "WL-Proxy-Client-IP" };

    public static String getIpAddress(HttpServletRequest request)
    {
        for (String header : REQUEST_HEADERS)
        {
            String ip = request.getHeader(header);
            if (isValidIp(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    private static boolean isValidIp(String ip)
    {
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            return false;
        }
        return true;
    }
}
