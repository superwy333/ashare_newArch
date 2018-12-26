package com.zynsun.platform.edoc.model;

import com.zynsun.platform.edoc.dto.bizType.BillTypeDTO;

/**
 * @Description：[]
 * @Author：PeidongWang
 * @Date：Created in 2017/6/14 9:32
 * @Modified By：
 */
public class BillTypeModelDTO extends BillTypeDTO {

    private BillTypeDTO parent;

    public BillTypeDTO getParent() {
        return parent;
    }

    public void setParent(BillTypeDTO parent) {
        this.parent = parent;
    }
}
