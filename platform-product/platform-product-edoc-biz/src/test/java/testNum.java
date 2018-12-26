import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zynsun.openplatform.util.BeanUtil.isNumber;
import static org.jsoup.helper.StringUtil.isNumeric;

/**
 * @description:
 * @author: Wy
 * @create: 2018-12-11 10:52
 **/
public class testNum {

    public static void main(String[] args) {
        String numStr = "123121.0000A";
        System.out.println(isNumeric(numStr));
        System.out.println(isNumber(numStr));


        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher("29");
        System.out.println(isNum.matches());
    }


}
