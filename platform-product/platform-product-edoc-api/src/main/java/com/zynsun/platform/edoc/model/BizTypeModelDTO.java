package com.zynsun.platform.edoc.model;

import com.zynsun.platform.edoc.dto.bizType.BillTypeDTO;
import com.zynsun.platform.edoc.dto.bizType.BizTypeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/8 22:01
 * @Modified By：
 */
public class BizTypeModelDTO extends BizTypeDTO {

    private String billTypeIds;
    private String billTypeNames;
    private String formTemplate;
    private BizTypeDTO parentBizType;
    private List<BillTypeDTO> billTypeList = new ArrayList<>();


    public String getFormTemplate() {
        return formTemplate;
    }

    public void setFormTemplate(String formTemplate) {
        this.formTemplate = formTemplate;
    }

    public List<BillTypeDTO> getBillTypeList() {
        return billTypeList;
    }

    public void setBillTypeList(List<BillTypeDTO> billTypeList) {
        this.billTypeList = billTypeList;
    }

    public BizTypeDTO getParentBizType() {
        return parentBizType;
    }

    public void setParentBizType(BizTypeDTO parentBizType) {
        this.parentBizType = parentBizType;
    }

    public String getBillTypeIds() {
        return billTypeIds;
    }

    public void setBillTypeIds(String billTypeIds) {
        this.billTypeIds = billTypeIds;
    }

    public String getBillTypeNames() {
        return billTypeNames;
    }

    public void setBillTypeNames(String billTypeNames) {
        this.billTypeNames = billTypeNames;
    }
}
