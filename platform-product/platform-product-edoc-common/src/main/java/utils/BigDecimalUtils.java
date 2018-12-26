package utils;

import java.math.BigDecimal;

/**
 * Created by baipan
 * Date: 2018-08-23
 */
public class BigDecimalUtils {


    public static String getDoubleVal(BigDecimal bigDecimal){
        return bigDecimal == null ? null : bigDecimal.doubleValue() + "";
    }

}
