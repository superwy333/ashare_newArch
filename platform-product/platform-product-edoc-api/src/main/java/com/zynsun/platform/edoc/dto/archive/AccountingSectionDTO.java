package com.zynsun.platform.edoc.dto.archive;

import com.zynsun.openplatform.dto.PageDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by WZH on 2017/7/28.
 */
public class AccountingSectionDTO extends PageDTO {

    /**
     * 是否属于档案岗标识 0:否 1:是
     */
    private String archiveGroupFlag;

    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * 全宗号
     */
    private String fondsCode;

    /**
     * 单位名称
     */
    private String officeName;

    /**
     * 单位编码
     */
    private String officeId;

    /**
     * 单据份数
     */
    private Integer billNum;

    /**
     * 案卷/册的编号，全宗内唯一
     */
    private String recordsNo;

    /**
     * 案卷题名
     */
    private String recordsTitle;

    /**
     * 起始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 总册数
     */
    private Integer volumes;

    /**
     * 册次
     */
    private Integer volumesNo;

    /**
     * 凭证数量
     */
    private Integer attachmentSum;

    /**
     * 分册人编码
     */
    private String filingPersonId;

    /**
     * 分册人名称
     */
    private String filingPersonName;

    private String bizType;

    private String edocImageExists;

    public String getEdocImageExists() {
        return edocImageExists;
    }

    public void setEdocImageExists(String edocImageExists) {
        this.edocImageExists = edocImageExists;
    }

    /**
     * 分册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date filingDate;

    /**
     * 盒号
     */
    private Integer recordsBoxNo;

    /**
     * 归档人编码
     */
    private String filedPersonId;

    /**
     * 归档人名称
     */
    private String filedPersonName;

    /**
     * 归档时间
     */
    private Date filedDate;

    /**
     * 密级
     */
    private String fileSecretLevel;

    /**
     * 保管期限
     */
    private String fileStoragePeriod;

    /**
     * 存放位置
     */
    private String fileLocation;

    /**
     * 分册归档标志：0：未归档；1：已归档
     */
    private String fileFlag;

    /**
     * 装订人
     */
    private String bander;

    /**
     * 描述
     */
    private String remarks;

    private String userName;


    //自动提交阈值
    private int boxNumThreshold;

    //分册自动提交标识
    private boolean boxAutoCommit;

    private List<SectionItemForIreport> sectionItemForIreportList;

    public List<SectionItemForIreport> getSectionItemForIreportList() {
        return sectionItemForIreportList;
    }

    public void setSectionItemForIreportList(List<SectionItemForIreport> sectionItemForIreportList) {
        this.sectionItemForIreportList = sectionItemForIreportList;
    }

    public int getBoxNumThreshold() {
        return boxNumThreshold;
    }

    public void setBoxNumThreshold(int boxNumThreshold) {
        this.boxNumThreshold = boxNumThreshold;
    }

    public boolean isBoxAutoCommit() {
        return boxAutoCommit;
    }

    public void setBoxAutoCommit(boolean boxAutoCommit) {
        this.boxAutoCommit = boxAutoCommit;
    }

    /**
     * 获取全宗号
     *
     * @return fonds_code - 全宗号
     */
    public String getFondsCode() {
        return fondsCode;
    }

    /**
     * 设置全宗号
     *
     * @param fondsCode 全宗号
     */
    public void setFondsCode(String fondsCode) {
        this.fondsCode = fondsCode == null ? null : fondsCode.trim();
    }

    /**
     * 获取单位名称
     *
     * @return office_name - 单位名称
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * 设置单位名称
     *
     * @param officeName 单位名称
     */
    public void setOfficeName(String officeName) {
        this.officeName = officeName == null ? null : officeName.trim();
    }

    /**
     * 获取单位编码
     *
     * @return office_id - 单位编码
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * 设置单位编码
     *
     * @param officeId 单位编码
     */
    public void setOfficeId(String officeId) {
        this.officeId = officeId == null ? null : officeId.trim();
    }

    /**
     * 获取单据份数
     *
     * @return bill_num - 单据份数
     */
    public Integer getBillNum() {
        return billNum;
    }

    /**
     * 设置单据份数
     *
     * @param billNum 单据份数
     */
    public void setBillNum(Integer billNum) {
        this.billNum = billNum;
    }

    /**
     * 获取案卷/册的编号，全宗内唯一
     *
     * @return records_no - 案卷/册的编号，全宗内唯一
     */
    public String getRecordsNo() {
        return recordsNo;
    }

    /**
     * 设置案卷/册的编号，全宗内唯一
     *
     * @param recordsNo 案卷/册的编号，全宗内唯一
     */
    public void setRecordsNo(String recordsNo) {
        this.recordsNo = recordsNo == null ? null : recordsNo.trim();
    }

    /**
     * 获取案卷题名
     *
     * @return records_title - 案卷题名
     */
    public String getRecordsTitle() {
        return recordsTitle;
    }

    /**
     * 设置案卷题名
     *
     * @param recordsTitle 案卷题名
     */
    public void setRecordsTitle(String recordsTitle) {
        this.recordsTitle = recordsTitle == null ? null : recordsTitle.trim();
    }

    /**
     * 获取总册数
     *
     * @return volumes - 总册数
     */
    public Integer getVolumes() {
        return volumes;
    }

    /**
     * 设置总册数
     *
     * @param volumes 总册数
     */
    public void setVolumes(Integer volumes) {
        this.volumes = volumes;
    }

    /**
     * 获取册次
     *
     * @return volumes_no - 册次
     */
    public Integer getVolumesNo() {
        return volumesNo;
    }

    /**
     * 设置册次
     *
     * @param volumesNo 册次
     */
    public void setVolumesNo(Integer volumesNo) {
        this.volumesNo = volumesNo;
    }

    /**
     * 获取凭证数量
     *
     * @return attachment_sum - 凭证数量
     */
    public Integer getAttachmentSum() {
        return attachmentSum;
    }

    /**
     * 设置凭证数量
     *
     * @param attachmentSum 凭证数量
     */
    public void setAttachmentSum(Integer attachmentSum) {
        this.attachmentSum = attachmentSum;
    }

    /**
     * 获取分册人编码
     *
     * @return filing_person_id - 分册人编码
     */
    public String getFilingPersonId() {
        return filingPersonId;
    }

    /**
     * 设置分册人编码
     *
     * @param filingPersonId 分册人编码
     */
    public void setFilingPersonId(String filingPersonId) {
        this.filingPersonId = filingPersonId == null ? null : filingPersonId.trim();
    }

    /**
     * 获取分册人名称
     *
     * @return filing_person_name - 分册人名称
     */
    public String getFilingPersonName() {
        return filingPersonName;
    }

    /**
     * 设置分册人名称
     *
     * @param filingPersonName 分册人名称
     */
    public void setFilingPersonName(String filingPersonName) {
        this.filingPersonName = filingPersonName == null ? null : filingPersonName.trim();
    }

    /**
     * 获取分册时间
     *
     * @return filing_date - 分册时间
     */
    public Date getFilingDate() {
        return filingDate;
    }

    /**
     * 设置分册时间
     *
     * @param filingDate 分册时间
     */
    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    /**
     * 获取盒号
     *
     * @return records_box_no - 盒号
     */
    public Integer getRecordsBoxNo() {
        return recordsBoxNo;
    }

    /**
     * 设置盒号
     *
     * @param recordsBoxNo 盒号
     */
    public void setRecordsBoxNo(Integer recordsBoxNo) {
        this.recordsBoxNo = recordsBoxNo;
    }

    /**
     * 获取归档人编码
     *
     * @return filed_person_id - 归档人编码
     */
    public String getFiledPersonId() {
        return filedPersonId;
    }

    /**
     * 设置归档人编码
     *
     * @param filedPersonId 归档人编码
     */
    public void setFiledPersonId(String filedPersonId) {
        this.filedPersonId = filedPersonId == null ? null : filedPersonId.trim();
    }

    /**
     * 获取归档人名称
     *
     * @return filed_person_name - 归档人名称
     */
    public String getFiledPersonName() {
        return filedPersonName;
    }

    /**
     * 设置归档人名称
     *
     * @param filedPersonName 归档人名称
     */
    public void setFiledPersonName(String filedPersonName) {
        this.filedPersonName = filedPersonName == null ? null : filedPersonName.trim();
    }

    /**
     * 获取归档时间
     *
     * @return filed_date - 归档时间
     */
    public Date getFiledDate() {
        return filedDate;
    }

    /**
     * 设置归档时间
     *
     * @param filedDate 归档时间
     */
    public void setFiledDate(Date filedDate) {
        this.filedDate = filedDate;
    }

    /**
     * 获取密级
     *
     * @return file_secret_level - 密级
     */
    public String getFileSecretLevel() {
        return fileSecretLevel;
    }

    /**
     * 设置密级
     *
     * @param fileSecretLevel 密级
     */
    public void setFileSecretLevel(String fileSecretLevel) {
        this.fileSecretLevel = fileSecretLevel == null ? null : fileSecretLevel.trim();
    }

    /**
     * 获取保管期限
     *
     * @return file_storage_period - 保管期限
     */
    public String getFileStoragePeriod() {
        return fileStoragePeriod;
    }

    /**
     * 设置保管期限
     *
     * @param fileStoragePeriod 保管期限
     */
    public void setFileStoragePeriod(String fileStoragePeriod) {
        this.fileStoragePeriod = fileStoragePeriod == null ? null : fileStoragePeriod.trim();
    }

    /**
     * 获取存放位置
     *
     * @return file_location - 存放位置
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * 设置存放位置
     *
     * @param fileLocation 存放位置
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation == null ? null : fileLocation.trim();
    }

    /**
     * 获取分册归档标志：0：未归档；1：已归档
     *
     * @return file_flag - 分册归档标志：0：未归档；1：已归档
     */
    public String getFileFlag() {
        return fileFlag;
    }

    /**
     * 设置分册归档标志：0：未归档；1：已归档
     *
     * @param fileFlag 分册归档标志：0：未归档；1：已归档
     */
    public void setFileFlag(String fileFlag) {
        this.fileFlag = fileFlag == null ? null : fileFlag.trim();
    }

    /**
     * 获取装订人
     *
     * @return bander - 装订人
     */
    public String getBander() {
        return bander;
    }

    /**
     * 设置装订人
     *
     * @param bander 装订人
     */
    public void setBander(String bander) {
        this.bander = bander == null ? null : bander.trim();
    }

    /**
     * 获取描述
     *
     * @return remarks - 描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置描述
     *
     * @param remarks 描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getArchiveGroupFlag() {
        return archiveGroupFlag;
    }

    public void setArchiveGroupFlag(String archiveGroupFlag) {
        this.archiveGroupFlag = archiveGroupFlag;
    }
}
