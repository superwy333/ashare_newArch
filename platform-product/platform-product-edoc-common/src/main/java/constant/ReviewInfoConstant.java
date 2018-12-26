package constant;

/**
 * Created by Jary on 2018/11/20/020.
 */
public class ReviewInfoConstant {
    /**
     *评价状态
     */
    public static class ReviewStatus {


        public static final String zero = "0";
        /**
         * 1:整单作废
         */
        public static final String SCRAP = "1";
        /**
         * 2:整单重采集
         */
        public static final String SCAVENGING_ALL = "2";
        /**
         * 3:补采集
         */
        public static final String SCAVENGING_SINGLE = "3";

        //4:数据不完整
        public static final String DATA_INCOMPLETE = "4";
    }



    public static class getProcess {
        /**
         * 已处理
         */
        public static final String isProcess = "1";

        /**
         * 未处理
         */
        public static final String unProcess = "0";
    }
}
