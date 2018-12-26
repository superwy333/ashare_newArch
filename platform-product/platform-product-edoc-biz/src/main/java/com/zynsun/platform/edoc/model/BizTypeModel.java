package com.zynsun.platform.edoc.model;

import com.zynsun.platform.edoc.domain.BillType;
import com.zynsun.platform.edoc.domain.BizType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/8 21:28
 * @Modified By：
 */
public class BizTypeModel extends BizType {
    private BizType parentBizType;

    public List<BillType> billTypeList = new ArrayList<>();

    public List<BillType> getBillTypeList() {
        return billTypeList;
    }

    public void setBillTypeList(List<BillType> billTypeList) {
        this.billTypeList = billTypeList;
    }

    public BizType getParentBizType() {
        return parentBizType;
    }

    public void setParentBizType(BizType parentBizType) {
        this.parentBizType = parentBizType;
    }
}
