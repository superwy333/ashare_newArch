package constant;

/**
 * Created by Jary on 2018/11/19/019.
 */
public class EdocHeaderConstant {

    /**
     * 封单状态
     */
    public static class Seal {
        /**
         * 60:未封单
         */
        public static final String UNSEALED_BILL = "60";
        /**
         * 61:已封单
         */
        public static final String SEAL_BILL = "61";
        /**
         * 62:已解单
         */
        public static final String SOLUTION_BILL = "62";
    }

    /**
     * 审核状态
     */
    public static class EdocTaskStatus {
        /**
         * 70:审核中
         */
        public static final String TASK_AUDIT = "70";
        /**
         * 71:审核通过
         */
        public static final String TASK_PASS = "71";
        /**
         * 72:审核不通过
         */
        public static final String TASK_FAILED = "72";

        //0:已新建
        public static final String NEWLY_BUILD = "0";

        //3:已更新
        public static final String UPDATE = "3";

        //6:作废中
        public static final String INVALIDATE = "6";

        //9:已作废
        public static final String DELETED = "9";

        /**
         * 21:待作废
         */
        public static final String SCRAP = "21";
        /**
         * 22:待重采
         */
        public static final String SCAVENGING_ALL = "22";
        /**
         * 23：待不猜
         */
        public static final String SCAVENGING_SINGLE = "23";

        //24:异常
        public static final String DATA_INCOMPLETE = "24";

    }


}
