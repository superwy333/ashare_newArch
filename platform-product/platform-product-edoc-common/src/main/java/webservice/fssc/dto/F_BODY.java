package webservice.fssc.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * 结果集
 */
public class F_BODY implements Serializable {
    private static final long serialVersionUID = 6465082057617211221L;
    /**
     * 服务返回状态码
     */
    @JSONField(name = "F_CODE")
    private String f_CODE;

    /**
     * 服务返回消息
     */
    @JSONField(name = "F_MSG")
    private String f_MSG;

    /**
     * 来源系统
     */
    @JSONField(name = "F_LYXT")
    private String f_LYXT;

    /**
     * 凭证类型 1、费用凭证 2、付款凭证
     */
    @JSONField(name = "F_PZ_TYPE")
    private String f_PZ_TYPE;

    /**
     * 返回数据集
     */
    @JSONField(name = "F_LIST")
    private List<F_LIST> f_LIST;

    /********************S 费用类型相关字段**********************/
    /**
     * 类型表结构，只取明细，过滤条件：F_LEAF=1 and F_ENABLE=1
     */

    /**
     * 编号
     */
    @JSONField(name = "F_ID")
    private String f_ID;

    /**
     * 名称
     */
    @JSONField(name = "F_NAME")
    private String f_NAME;

    /**
     * 级数 1、2、3，每级的宽度是3
     */
    @JSONField(name = "F_LEVEL")
    private String f_LEVEL;

    /**
     *  0 非明细 1 明细，明细级位最末级
     */
    @JSONField(name = "F_LEAF")
    private String f_LEAF;

    /**
     * 父级类型
     */
    @JSONField(name = "F_PARENT")
    private String f_PARENT;

    /**
     * 是否有效
     *   1 有效
     */
    @JSONField(name = "F_ENABLE")
    private String f_ENABLE;

    /********************E 费用类型相关字段**********************/

    public String getF_CODE() {
        return f_CODE;
    }

    public void setF_CODE(String f_CODE) {
        this.f_CODE = f_CODE;
    }

    public String getF_MSG() {
        return f_MSG;
    }

    public void setF_MSG(String f_MSG) {
        this.f_MSG = f_MSG;
    }

    public String getF_LYXT() {
        return f_LYXT;
    }

    public void setF_LYXT(String f_LYXT) {
        this.f_LYXT = f_LYXT;
    }

    public List<F_LIST> getF_LIST() {
        return f_LIST;
    }

    public void setF_LIST(List<F_LIST> f_LIST) {
        this.f_LIST = f_LIST;
    }

    public String getF_PZ_TYPE() {
        return f_PZ_TYPE;
    }

    public void setF_PZ_TYPE(String f_PZ_TYPE) {
        this.f_PZ_TYPE = f_PZ_TYPE;
    }

    public String getF_ID() {
        return f_ID;
    }

    public void setF_ID(String f_ID) {
        this.f_ID = f_ID;
    }

    public String getF_NAME() {
        return f_NAME;
    }

    public void setF_NAME(String f_NAME) {
        this.f_NAME = f_NAME;
    }

    public String getF_LEVEL() {
        return f_LEVEL;
    }

    public void setF_LEVEL(String f_LEVEL) {
        this.f_LEVEL = f_LEVEL;
    }

    public String getF_LEAF() {
        return f_LEAF;
    }

    public void setF_LEAF(String f_LEAF) {
        this.f_LEAF = f_LEAF;
    }

    public String getF_PARENT() {
        return f_PARENT;
    }

    public void setF_PARENT(String f_PARENT) {
        this.f_PARENT = f_PARENT;
    }

    public String getF_ENABLE() {
        return f_ENABLE;
    }

    public void setF_ENABLE(String f_ENABLE) {
        this.f_ENABLE = f_ENABLE;
    }

}
