import java.util.*;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-21 16:08
 **/
public class StringTest {


    public static void main(String[] args) {

        String kints =
                "Then, when you have found the shrubbery, you must " +
                        "cut down the  tree in the forest.... " +
                        "with ... a herring!";

        String exp = "[A-Z]\\w+.$";
        String exp2 = "the";
        boolean r = Pattern.matches(exp,"haa.");
        System.out.println(kints.replaceAll("[abc]","_"));
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "address:" + this;
    }
}
