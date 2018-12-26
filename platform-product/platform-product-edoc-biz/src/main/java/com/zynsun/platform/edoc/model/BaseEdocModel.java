package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

import java.util.Date;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 14:08 2017/12/26
 * @Modified By:
 */
public class BaseEdocModel extends PageModel{

    /**
     * 业务类型
     */
    private String edocType;

    /**
     * 业务状态
     */
    private String edocTaskStatus;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 影像任务是否匹配
     */
    private String edocIsMatched;

    /**
     * 业务层级
     */
    private String edocLevel;

    /**
     * 影像结束上传时间
     */
    private Date endUpData;

    /**
     * 实物状态
     */
    private String edocPhysicalStatus;

    /**
     * 系统来源
     */
    private String edocSource;

    /**
     * 上传影像总张数
     */
    private Integer totalNum;

    /**
     * 是否锁定
     */
    private String isLocked;

    public String getEdocType() {
        return edocType;
    }

    public void setEdocType(String edocType) {
        this.edocType = edocType;
    }

    public String getEdocTaskStatus() {
        return edocTaskStatus;
    }

    public void setEdocTaskStatus(String edocTaskStatus) {
        this.edocTaskStatus = edocTaskStatus;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getEdocIsMatched() {
        return edocIsMatched;
    }

    public void setEdocIsMatched(String edocIsMatched) {
        this.edocIsMatched = edocIsMatched;
    }

    public String getEdocLevel() {
        return edocLevel;
    }

    public void setEdocLevel(String edocLevel) {
        this.edocLevel = edocLevel;
    }

    public Date getEndUpData() {
        return endUpData;
    }

    public void setEndUpData(Date endUpData) {
        this.endUpData = endUpData;
    }

    public String getEdocPhysicalStatus() {
        return edocPhysicalStatus;
    }

    public void setEdocPhysicalStatus(String edocPhysicalStatus) {
        this.edocPhysicalStatus = edocPhysicalStatus;
    }

    public String getEdocSource() {
        return edocSource;
    }

    public void setEdocSource(String edocSource) {
        this.edocSource = edocSource;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }
}
