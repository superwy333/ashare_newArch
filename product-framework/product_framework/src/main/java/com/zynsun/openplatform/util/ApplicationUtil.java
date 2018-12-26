package com.zynsun.openplatform.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationUtil {
    public static String getOneMatchXMLName(String pn) {
        int liushui = (int) (Math.random() * 100000000);
        return pn + BeanUtil.formatDate(new Date(), "yyyyMMddHHmmss") + BeanUtil.leftZeroNumber(liushui, 8) + ".xml";
    }

    public static String getInvoiceImageName() {
        int liushui = (int) (Math.random() * 100000000);
        return BeanUtil.formatDate(new Date(), "yyyyMMddHHmmss") + BeanUtil.leftZeroNumber(liushui, 8) + ".jpg";
    }

    /**
     * 按照指定分隔符分隔账单编号
     *
     * @param stmtCode
     * @return
     * @author jemi
     * 2016年8月7日上午11:52:00
     */
    public static List<String> getStmtCodeList(String input, String regex) {
        List<String> listStmtCodes = new ArrayList<String>();
        if (BeanUtil.isEmpty(input)) {
            return listStmtCodes;
        }

        String[] stmtCodes = input.split(regex);
        for (int i = 0; i < stmtCodes.length; i++) {
            if ((stmtCodes[i] != null) && !stmtCodes[i].trim().equals("")) {
                listStmtCodes.add(stmtCodes[i].trim());
            }
        }
        return listStmtCodes;
    }

    public static List<String> getSourceList(String source) {
        String[] sources = source.split("/");
        List<String> listSource = new ArrayList<String>();
        for (int i = 0; i < sources.length; i++) {
            if ((sources[i] != null) && !sources[i].trim().equals("")) {
                listSource.add(sources[i].trim());
            }
        }
        return listSource;
    }
}
