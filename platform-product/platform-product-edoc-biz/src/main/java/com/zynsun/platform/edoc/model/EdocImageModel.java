package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:28 2018/1/2
 * @Modified By:
 */
public class EdocImageModel extends PageModel{
    /**
     * 影像任务ID
     */
    private Long edocHeaderId;

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 文件名称
     */
    private String imageName;

    /**
     * 影像根目录
     */
    private String imageRootPath;

    private Long imageId;

    /**
     * 文件后缀
     */
    private String imageFormat;

    private String pageNo;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 关联的单证编号
     */
    private String edocNo;

    /**
     * 费控接口，3.3.2.5 附件信息接口用，业务类型,
     * 1.报账单业务类型；
     * 2.合同业务类型;
     */
    private String edocImageType;

    private String isReplace;

    private String addDataFlag;

    private InvoiceModel invoiceModel;

    private Long imageParentId;

    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    private String imageSource;

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getExtField1() {
        return extField1;
    }

    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    public String getExtField2() {
        return extField2;
    }

    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    public String getExtField3() {
        return extField3;
    }

    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    public String getExtField4() {
        return extField4;
    }

    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    public String getExtField5() {
        return extField5;
    }

    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }

    public Long getImageParentId() {
        return imageParentId;
    }

    public void setImageParentId(Long imageParentId) {
        this.imageParentId = imageParentId;
    }

    public String getAddDataFlag() {
        return addDataFlag;
    }

    public void setAddDataFlag(String addDataFlag) {
        this.addDataFlag = addDataFlag;
    }

    /**
     * 获取影像任务ID
     *
     * @return edoc_header_id - 影像任务ID
     */
    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    /**
     * 设置影像任务ID
     *
     * @param edocHeaderId 影像任务ID
     */
    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    /**
     * 获取图片路径
     *
     * @return image_url - 图片路径
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片路径
     *
     * @param imageUrl 图片路径
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取影像根目录
     *
     * @return image_root_path - 影像根目录
     */
    public String getImageRootPath() {
        return imageRootPath;
    }

    /**
     * 设置影像根目录
     *
     * @param imageRootPath 影像根目录
     */
    public void setImageRootPath(String imageRootPath) {
        this.imageRootPath = imageRootPath;
    }


    /**
     * 获取文件后缀
     *
     * @return image_format - 文件后缀
     */
    public String getImageFormat() {
        return imageFormat;
    }

    /**
     * 设置文件后缀
     *
     * @param imageFormat 文件后缀
     */
    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * @return page_no
     */
    public String getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo
     */
    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
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
        this.remarks = remarks;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public String getEdocImageType() {
        return edocImageType;
    }

    public void setEdocImageType(String edocImageType) {
        this.edocImageType = edocImageType;
    }

    public String getIsReplace() {
        return isReplace;
    }

    public void setIsReplace(String isReplace) {
        this.isReplace = isReplace;
    }

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
