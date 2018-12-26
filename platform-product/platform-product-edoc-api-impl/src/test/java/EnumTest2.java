import com.zynsun.openplatform.util.EdocLoadConfig;

/**
 * @description:
 * @author: Wy
 * @create: 2018-11-20 09:23
 **/
public class EnumTest2 {

    private static final String INVENUMPREFIX = "invoice.";


    public enum InvEnum {
        invoiceType1("1"),invoiceType2("2"),invoiceType4("4"),invoiceType10("10");

        private final String code;

        private InvEnum(String code)
        {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return EdocLoadConfig.get(INVENUMPREFIX + this.name());

        }

        public static InvEnum parse(String code) {
            EnumTest2.InvEnum result = null; // Default
            for (InvEnum item : InvEnum.values()) {
                if (item.getCode()==code) {
                    result = item;
                    break;
                }
            }
            return result;
        }


    }

    public static void main(String[] args) {
        InvEnum invEnum = InvEnum.parse("10");
        String name  = invEnum.getName();
        System.out.println(name);
    }


}
