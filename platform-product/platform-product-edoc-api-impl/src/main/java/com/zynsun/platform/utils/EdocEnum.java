package com.zynsun.platform.utils;

/**
 * @description: Edoc模块枚举类，用于和前端交互的映射
 * @author: Wy
 * @create: 2018-11-19 15:12
 **/
public class EdocEnum {

    public enum InvTypeEnum {
        invType1("增值税专用发票","1"),invType2("增值税普通发票","2"),invType4("增值税普通电子发票","4"),invType10("多发票","10");
        private final String name;
        private final String code;
        public String getCode() {
            return code;
        }
        private InvTypeEnum(String name,String code)
        {
            this.name = name;
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public static String parse(String code) {
            InvTypeEnum result = null; // Default
            for (InvTypeEnum item : InvTypeEnum.values()) {
                if (item.getCode()==code) {
                    result = item;
                    break;
                }
            }
            return result.name;
        }
    }

    public enum CheckReal {
        CheckReal1("待验真","1"),CheckReal2("已验真","2");
        private final String name;
        private final String code;
        public String getCode() {
            return code;
        }
        private CheckReal(String name,String code)
        {
            this.name = name;
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public static String parse(String code) {
            InvTypeEnum result = null; // Default
            for (InvTypeEnum item : InvTypeEnum.values()) {
                if (item.getCode()==code) {
                    result = item;
                    break;
                }
            }
            return result.name;
        }
    }

    public enum CheckDetails {
        CheckDetails1("真票","11"),CheckDetails2("存疑","12");
        private final String name;
        private final String code;
        public String getCode() {
            return code;
        }
        private CheckDetails(String name,String code)
        {
            this.name = name;
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public static String parse(String code) {
            InvTypeEnum result = null; // Default
            for (InvTypeEnum item : InvTypeEnum.values()) {
                if (item.getCode()==code) {
                    result = item;
                    break;
                }
            }
            return result.name;
        }
    }


}
