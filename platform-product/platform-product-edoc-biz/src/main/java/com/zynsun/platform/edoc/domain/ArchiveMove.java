package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;
import com.zynsun.openplatform.domain.PageModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: Wy
 * @Description:
 * @Date:Created in 2018-06-26 上午 10:05
 * @Modified By:
 */
@Table(name = "accounting_archives_move")
public class ArchiveMove extends PageModel {


    //关联档案id
    @Column(name = "archives_id")
    private Long archiveId;

    @Column(name = "archive_no")
    private String archiveNo;

    //移交类型：单据、册、盒、箱
    @Column(name = "archive_type")
    private String archiveType;

    //描述
    @Column(name = "remark")
    private String remark;

    //转入机构
    @Column(name = "to_company")
    private String toCompany;

    //转入全宗
    @Column(name = "to_header")
    private String toArchiveHeaderName;

    //转出机构
    @Column(name = "from_company")
    private String fromCompany;

    //转出全宗
    @Column(name = "from_header")
    private String fromArchiveHeaderName;

    //责任人
    @Column(name = "from_charge_person")
    private String fromChargePerson;

    //责任人
    @Column(name = "to_charge_person")
    private String toChargePerson;

    //状态
    @Column(name = "status")
    private String status;

    //流程操作状态
    @Column(name = "process_status")
    private String processStatus;

    //移交日期
    @Column(name = "move_date")
    private String moveDate;


    public String getArchiveNo() {
        return archiveNo;
    }

    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo;
    }

    public String getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(String archiveType) {
        this.archiveType = archiveType;
    }


    public String getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(String moveDate) {
        this.moveDate = moveDate;
    }

    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getToCompany() {
        return toCompany;
    }

    public void setToCompany(String toCompany) {
        this.toCompany = toCompany;
    }

    public String getToArchiveHeaderName() {
        return toArchiveHeaderName;
    }

    public void setToArchiveHeaderName(String toArchiveHeaderName) {
        this.toArchiveHeaderName = toArchiveHeaderName;
    }

    public String getFromCompany() {
        return fromCompany;
    }

    public void setFromCompany(String fromCompany) {
        this.fromCompany = fromCompany;
    }

    public String getFromArchiveHeaderName() {
        return fromArchiveHeaderName;
    }

    public void setFromArchiveHeaderName(String fromArchiveHeaderName) {
        this.fromArchiveHeaderName = fromArchiveHeaderName;
    }

    public String getFromChargePerson() {
        return fromChargePerson;
    }

    public void setFromChargePerson(String fromChargePerson) {
        this.fromChargePerson = fromChargePerson;
    }

    public String getToChargePerson() {
        return toChargePerson;
    }

    public void setToChargePerson(String toChargePerson) {
        this.toChargePerson = toChargePerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

}
