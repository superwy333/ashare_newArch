package com.zynsun.platform.edoc.model;


import com.zynsun.platform.edoc.domain.BillType;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/14 9:34
 * @Modified By：
 */
public class BillTypeModel extends BillType {

    private BillType parent;

    public BillType getParent() {
        return parent;
    }

    public void setParent(BillType parent) {
        this.parent = parent;
    }
}
