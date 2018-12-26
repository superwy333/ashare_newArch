import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-22 14:28
 **/
public class ZZTest {


    public static void main(String[] args) {
        String reggex = "^FY";
        String str = "FY2018112200901";
        Pattern p= Pattern.compile(reggex);
        Matcher m=p.matcher(str);
        System.out.println(m.find());
    }


}
