package webservice.fssc.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * 返回数据集
 */
public class F_LIST implements Serializable {
    private static final long serialVersionUID = 8136027377519301770L;
    /**
     * 请求单据编号
     */
    @JSONField(name = "F_DJBH")
    private String f_DJBH;
    /**
     * 返回单据编号
     */
    @JSONField(name = "F_BILL_ID")
    private String f_BILL_ID;
    /**
     * 单据类型
     */
    @JSONField(name = "F_DJLX")
    private String f_DJLX;
    /**
     * 状态值
     *      一、归档状态
     *          0 不可归档
     *          1 可归档
     *          2 已归档
     *      二、认证状态
     *          0 不可认证
     *          1 待认证
     *          2 已认证
     */
    @JSONField(name = "F_STA_VALUE")
    private String f_STA_VALUE;

    /**
     * 核算系统编号 EBS/JDE
     */
    @JSONField(name = "F_HSXT")
    private String f_HSXT;

    @JSONField(name = "F_PZBH")
    private String f_PZBH;

    /***
     * 核算账套（获取pdf时需要）
     */
    @JSONField(name = "F_HSZT")
    private String f_HSZT;

    /**
     * 历史冲销凭证数据集，历史凭证编号和冲销凭证编号成对出现
     */
    @JSONField(name = "F_CXPZ_LIST")
    private List<F_CXPZ_LIST> f_CXPZ_LIST;

    @JSONField(name = "F_ZFHH")
    private String f_ZFHH;

    public String getF_ZFHH() {
        return f_ZFHH;
    }

    public void setF_ZFHH(String f_ZFHH) {
        this.f_ZFHH = f_ZFHH;
    }

    public String getF_DJBH() {
        return f_DJBH;
    }

    public void setF_DJBH(String f_DJBH) {
        this.f_DJBH = f_DJBH;
    }

    public String getF_BILL_ID() {
        return f_BILL_ID;
    }

    public void setF_BILL_ID(String f_BILL_ID) {
        this.f_BILL_ID = f_BILL_ID;
    }

    public String getF_DJLX() {
        return f_DJLX;
    }

    public void setF_DJLX(String f_DJLX) {
        this.f_DJLX = f_DJLX;
    }

    public String getF_STA_VALUE() {
        return f_STA_VALUE;
    }

    public void setF_STA_VALUE(String f_STA_VALUE) {
        this.f_STA_VALUE = f_STA_VALUE;
    }

    public String getF_HSXT() {
        return f_HSXT;
    }

    public void setF_HSXT(String f_HSXT) {
        this.f_HSXT = f_HSXT;
    }

    public String getF_PZBH() {
        return f_PZBH;
    }

    public void setF_PZBH(String f_PZBH) {
        this.f_PZBH = f_PZBH;
    }

    public List<F_CXPZ_LIST> getF_CXPZ_LIST() {
        return f_CXPZ_LIST;
    }

    public void setF_CXPZ_LIST(List<F_CXPZ_LIST> f_CXPZ_LIST) {
        this.f_CXPZ_LIST = f_CXPZ_LIST;
    }

    public String getF_HSZT() {
        return f_HSZT;
    }

    public void setF_HSZT(String f_HSZT) {
        this.f_HSZT = f_HSZT;
    }
}
