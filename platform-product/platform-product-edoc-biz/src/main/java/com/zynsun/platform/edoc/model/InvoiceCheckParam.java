package com.zynsun.platform.edoc.model;

import utils.App;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 13:15 2018/2/1
 * @Modified By:
 */
public class InvoiceCheckParam {
    /**
     * 用户id
     */
    private Long id = Long.parseLong(App.getConfig("invoiceUserId"));

    /**
     * 用户密码
     */
    private String password = App.getConfig("invoiceUserPwd");

    /**
     * 接口调用类型 0:实时调用（一次请求只支持验真一张发票），1:非实时录入，2:非实时查询
     */
    private String type = App.getConfig("invoiceCheckType").trim();


    List<InvParams> invList = new ArrayList<>();


    public List<InvParams> getInvList() {
        return invList;
    }

    class InvParams{
        /**
         * 发票代码
         */
        private String invoiceCode;

        /**
         * 发票号码
         */
        private String invoiceNum;

        /**
         * 发票日期 格式为yyyy-MM-dd
         */
        private String invoiceDate;

        /**
         * 发票金额 发票类型为增值税专票、机动车发票时必输（查询）
         */
        private String amountTotal;

        /**
         * 校验码 发票类型为增值税普票、电子发票、卷式发票时必输（查询）
         */
        private String checkCode;

        public String getInvoiceCode() {
            return invoiceCode;
        }

        public void setInvoiceCode(String invoiceCode) {
            this.invoiceCode = invoiceCode;
        }

        public String getInvoiceNum() {
            return invoiceNum;
        }

        public void setInvoiceNum(String invoiceNum) {
            this.invoiceNum = invoiceNum;
        }

        public String getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(String invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public String getAmountTotal() {
            return amountTotal;
        }

        public void setAmountTotal(String amountTotal) {
            this.amountTotal = amountTotal;
        }

        public String getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
        }

        public InvParams(String invoiceCode, String invoiceNum, String invoiceDate, String amountTotal, String checkCode) {
            this.invoiceCode = invoiceCode;
            this.invoiceNum = invoiceNum;
            this.invoiceDate = invoiceDate;
            this.amountTotal = amountTotal;
            this.checkCode = checkCode;
        }
    }

    public void addInvoiceParam(String invoiceCode,String invoiceNum,String invoiceDate,String amountTotal,String checkCode){
        invList.add(new InvParams(invoiceCode,invoiceNum,invoiceDate,amountTotal,checkCode));
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

}
