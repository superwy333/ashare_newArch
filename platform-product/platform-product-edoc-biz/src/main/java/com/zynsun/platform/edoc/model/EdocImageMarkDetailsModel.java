package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:28
 **/
public class EdocImageMarkDetailsModel extends PageModel {

    private Long edocImageMarkId;

    private String markDetailName;

    private String markDetailRemark;

    private String markDetailNo;

    private String content;

    private String available;

    private String color;

    private Double x;

    private Double y;

    public Long getEdocImageMarkId() {
        return edocImageMarkId;
    }

    public void setEdocImageMarkId(Long edocImageMarkId) {
        this.edocImageMarkId = edocImageMarkId;
    }

    public String getMarkDetailName() {
        return markDetailName;
    }

    public void setMarkDetailName(String markDetailName) {
        this.markDetailName = markDetailName;
    }

    public String getMarkDetailRemark() {
        return markDetailRemark;
    }

    public void setMarkDetailRemark(String markDetailRemark) {
        this.markDetailRemark = markDetailRemark;
    }

    public String getMarkDetailNo() {
        return markDetailNo;
    }

    public void setMarkDetailNo(String markDetailNo) {
        this.markDetailNo = markDetailNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

}
