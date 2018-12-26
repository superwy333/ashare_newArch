package com.zynsun.openplatform.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class BigDecimalUtil {

    public static String toString(BigDecimal one) {
        return toString(one, null);
    }

    public static String toString(BigDecimal one, String def) {
        return one == null ? def : one.toString();
    }

    /**
     * 获取小数点，如果位数不大于2，设置为2位
     *
     * @author gongdaowen
     * @create 2015年6月29日 下午5:38:47
     */
    public static BigDecimal getScale2(BigDecimal one) {
        return getScale(one, 2);
    }

    public static BigDecimal getScale(BigDecimal one, int scale) {
        BigDecimal decimal = get(one);
        return decimal.scale() > 2 ? decimal : decimal.setScale(scale);
    }

    /**
     * 获取BigDecimal,如果为空返回默认值
     *
     * @author gongdaowen
     * @create 2015年6月25日 下午1:12:39
     */
    public static BigDecimal get(BigDecimal one, BigDecimal def) {
        return one == null ? def : one;
    }

    /**
     * 获取BigDecimal,如果为空返回 BigDecimal.ZERO
     *
     * @author gongdaowen
     * @create 2015年6月25日 下午1:12:39
     */
    public static BigDecimal get(BigDecimal one) {
        return get(one, BigDecimal.ZERO);
    }

    /**
     * 相加
     *
     * @author gongdaowen
     * @create 2015年6月25日 下午1:12:39
     */
    public static BigDecimal add(BigDecimal one, BigDecimal two,
                                 BigDecimal... others) {
        BigDecimal rs = get(one).add(get(two));
        if ((others != null) && (others.length > 0)) {
            for (BigDecimal b : others) {
                rs = rs.add(get(b));
            }
        }
        return rs;
    }

    /**
     * 相减
     *
     * @author gongdaowen
     * @create 2015年6月25日 下午1:12:39
     */
    public static BigDecimal substuct(BigDecimal one, BigDecimal two,
                                      BigDecimal... others) {
        BigDecimal rs = get(one).subtract(get(two));
        if ((others != null) && (others.length > 0)) {
            for (BigDecimal b : others) {
                rs = rs.subtract(get(b));
            }
        }
        return rs;
    }

    /**
     * 相乘
     *
     * @author gongdaowen
     * @create 2015年6月25日 下午1:12:39
     */
    public static BigDecimal multiply(BigDecimal one, BigDecimal two,
                                      BigDecimal... others) {
        BigDecimal rs = get(one).multiply(get(two));
        if ((others != null) && (others.length > 0)) {
            for (BigDecimal b : others) {
                rs = rs.multiply(get(b));
            }
        }
        return rs;
    }

    /**
     * 相除
     *
     * @author gongdaowen
     * @create 2015年6月25日 下午1:12:39
     */
    public static BigDecimal divide(BigDecimal one, BigDecimal two,
                                    BigDecimal... others) {
        BigDecimal rs = get(one).divide(get(two), 2, BigDecimal.ROUND_HALF_UP);
        if ((others != null) && (others.length > 0)) {
            for (BigDecimal b : others) {
                rs = rs.divide(get(b), 2, BigDecimal.ROUND_HALF_UP);
            }
        }
        return rs;
    }

    /**
     * BigDecimal类型比较
     * <p>
     * 1为大于 -1为小于 0为等于
     *
     * @author : tanzhen
     * @date : 2016年9月12日下午1:35:53
     */
    public static int compare(BigDecimal one, BigDecimal two) {
        return one.compareTo(two);
    }

    public static String number2Word(BigDecimal number) {
        return NumberToCN.number2CNMontrayUnit(number);
    }

    public static class NumberToCN {
        /**
         * 汉语中数字大写
         */
        private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁",
                "肆", "伍", "陆", "柒", "捌", "玖"};
        /**
         * 汉语中货币单位大写，这样的设计类似于占位符
         */
        private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元",
                "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆",
                "拾", "佰", "仟"};
        /**
         * 特殊字符：整
         */
        private static final String CN_FULL = "整";
        /**
         * 特殊字符：负
         */
        private static final String CN_NEGATIVE = "负";
        /**
         * 金额的精度，默认值为2
         */
        private static final int MONEY_PRECISION = 2;
        /**
         * 特殊字符：零元整
         */
        private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

        /**
         * 把输入的金额转换为汉语中人民币的大写
         *
         * @param numberOfMoney 输入的金额
         * @return 对应的汉语大写
         */
        public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
            StringBuffer sb = new StringBuffer();
            // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
            // positive.
            int signum = numberOfMoney.signum();
            // 零元整的情况
            if (signum == 0) {
                return CN_ZEOR_FULL;
            }
            // 这里会进行金额的四舍五入
            long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                    .setScale(0, 4).abs().longValue();
            // 得到小数点后两位值
            long scale = number % 100;
            int numUnit = 0;
            int numIndex = 0;
            boolean getZero = false;
            // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
            if (!(scale > 0)) {
                numIndex = 2;
                number = number / 100;
                getZero = true;
            }
            if ((scale > 0) && (!((scale % 10) > 0))) {
                numIndex = 1;
                number = number / 10;
                getZero = true;
            }
            int zeroSize = 0;
            while (true) {
                if (number <= 0) {
                    break;
                }
                // 每次获取到最后一个数
                numUnit = (int) (number % 10);
                if (numUnit > 0) {
                    if ((numIndex == 9) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                    }
                    if ((numIndex == 13) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                    }
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    getZero = false;
                    zeroSize = 0;
                } else {
                    ++zeroSize;
                    if (!(getZero)) {
                        sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    }
                    if (numIndex == 2) {
                        if (number > 0) {
                            sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                        }
                    } else if ((((numIndex - 2) % 4) == 0)
                            && ((number % 1000) > 0)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                    getZero = true;
                }
                // 让number每次都去掉最后一个数
                number = number / 10;
                ++numIndex;
            }
            // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
            if (signum == -1) {
                sb.insert(0, CN_NEGATIVE);
            }
            // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
            if (!(scale > 0)) {
                sb.append(CN_FULL);
            }
            return sb.toString();
        }
    }

    /**
     * 判断是否为小数
     *
     * @author : tanzhen
     * @date ：2016年11月28日上午11:37:23
     */
    public static boolean isDigits(String str) {
        return str.matches("\\d+\\.\\d+$|-\\d+\\.\\d+$");
    }

    /**
     * 集合中某一字段值合计
     *
     * @param cls
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static BigDecimal addCollection(List<?> cls, String field) throws NoSuchFieldException, IllegalAccessException {
        BigDecimal bigDecimal = new BigDecimal(0);

        for (Object obj : cls) {
            if (BeanUtil.isEmpty(obj)) {
                continue;
            }

            Field declaredField = obj.getClass().getDeclaredField(field);
            declaredField.setAccessible(true);


           /* if (declaredField.getType().equals(String.class)    ) {
                bigDecimal = BigDecimalUtil.add(bigDecimal,new BigDecimal ((String) declaredField.get(obj)) );
                continue;
            }*/

            bigDecimal = BigDecimalUtil.add(bigDecimal, getBigDecimal(declaredField.get(obj)));
        }
        return bigDecimal;
    }

    /**
     * Object对象转Bigdecimal类型
     *
     * @param value
     * @return
     */
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }
}
