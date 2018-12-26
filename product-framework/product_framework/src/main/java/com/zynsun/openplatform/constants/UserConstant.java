package com.zynsun.openplatform.constants;

/**
 * Created by tanzhen on 2017/4/13.
 */
public class UserConstant {

    /**
     * 扫描用户
     */
    public static final String OCR_USER = "OCR_SCAN";

    /**
     * 抽取用户
     */
    public static final String CLIENT_USER = "CHOUQU";

    /**
     * 定时匹配用户
     */
    public static final String AUTO_MATCHER = "AUTO_MATCHER";

    /**
     * 定时票核用户
     */
    public static final String AUTO_CHECKER = "AUTO_CHECKER";

    /**
     * 导入公司、供应商、发票用户
     */
    public static final class Importer {

        /**
         * 手工用户
         */
        public static final String MANUAL_IMPORTER = "MANUAL_IMPORTER";

        /**
         * 接口用户
         */
        public static final String INTERFACE_IMPORTER = "INTERFACE_IMPORTER";
    }
}
