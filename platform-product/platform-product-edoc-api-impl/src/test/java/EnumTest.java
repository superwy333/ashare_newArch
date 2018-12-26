/**
 * @description:
 * @author: Wy
 * @create: 2018-11-19 14:41
 **/
public class EnumTest {

    /*public enum SeasonEnum {
        SPRING("春天"),SUMMER("夏天"),FALL("秋天"),WINTER("冬天");

        private final String name;

        private SeasonEnum(String name)
        {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }*/


    public enum InvEnum {

        INVTYPE1("增值税专用发票","1"),invType2("增值税普通发票","2"),invType4("增值税普通电子发票","2"),invType10("多发票","4");

        private final String name;
        private final String code;

        public String getCode() {
            return code;
        }

        private InvEnum(String name,String code)
        {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public static String parse(String code) {
            InvEnum result = null; // Default
            for (InvEnum item : InvEnum.values()) {
                if (item.getCode()==code) {
                    result = item;
                    break;
                }
            }
            return result.name;
        }
    }

    public final static String INVTYPE1 = "1";


    public static void main(String[] args) {
        String name = InvEnum.parse("1");
    }


}
