package utils;


import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by baipan
 * Date: 2018-08-23
 */
public class Base64Utils {
    private final static String encoding = "UTF-8";

    public static String encoder(String data){
        byte[] bytes = data.getBytes();
        bytes = Base64.getEncoder().encode(bytes);
        return new String(bytes, Charset.forName(encoding));
    }

    public static String decoder(String data){
        byte[] bytes = Base64.getDecoder().decode(data);
        return new String(bytes, Charset.forName(encoding));
    }





}
