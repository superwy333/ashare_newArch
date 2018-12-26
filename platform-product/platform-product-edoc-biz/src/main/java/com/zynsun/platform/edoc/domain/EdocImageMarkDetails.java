package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.PageModel;

import javax.persistence.Column;

/**
 * @description:
 * @author: Wy
 * @create: 2018-09-06 16:28
 **/
public class EdocImageMarkDetails extends PageModel {

    @Column(name = "edoc_image_mark_id")
    private Long edocImageMarkId;
    @Column(name = "mark_detail_name")
    private String markDetailName;
    @Column(name = "mark_detail_remark")
    private String markDetailRemark;
    @Column(name = "mark_detail_no")
    private String markDetailNo;
    @Column(name = "content")
    private String content;
    @Column(name = "available")
    private String available;
    @Column(name = "color")
    private String color;
    @Column(name = "x")
    private Double x;
    @Column(name = "y")
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
