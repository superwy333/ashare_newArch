package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.BaseDTO;
import com.zynsun.openplatform.util.BeanUtil;
import com.zynsun.openplatform.util.EdocLoadConfig;
import com.zynsun.platform.edoc.dto.baseDTO.BaseEnum;


/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 15:20 2018/1/2
 * @Modified By:
 */
public class EdocImageDTO extends BaseDTO {
    /**
     * 影像任务父级ID（为满足影像任务树状结构设计）
     */
    private Long imageParentId;

    /**
     * 对应的影像任务ID
     */
    private Long edocHeaderId;

    /**
     * 图像/附件名称
     */
    private String imageName;

    /**
     * 图像/附件url地址
     */
    private String imageUrl;

    /**
     * 图像/附件保存根目录
     */
    private String imageRootPath;

    /**
     * 图像/附件后缀名称
     */
    private String docFormat;

    /**
     * 图像/附件排序编号
     */
    private Long pageNo;

    /**
     * 是否替换 0 未替换 1 替换（替扫使用）
     */
    private String isReplace;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 图片/附件生成模式（客户端扫描/web端上传等，详见数据字典配置）
     */
    private String generateModel;

    /**
     * 图像/附件类型（普票/专票/单据封面等等，详细见数据字典配置）
     */
    private String edocImageType;

    /**
     * 扫描人
     */
    private String scanner;

    /**
     * 扫描代码
     */
    private String scannerCode;


    private String extField1;

    private String extField2;

    private String extField3;

    private String extField4;

    private String extField5;

    private String addDataFlag;
    private String imageSource;

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }


    public void setAddDataFlag(String addDataFlag) {
        this.addDataFlag = addDataFlag;
    }

    public String getAddDataFlag() {
        return addDataFlag;
    }


    /**
     * 获取影像任务父级ID（为满足影像任务树状结构设计）
     *
     * @return parent_id - 影像任务父级ID（为满足影像任务树状结构设计）
     */
    public Long getImageParentId() {
        return imageParentId;
    }

    /**
     * 设置影像任务父级ID（为满足影像任务树状结构设计）
     *
     * @param imageParentId 影像任务父级ID（为满足影像任务树状结构设计）
     */
    public void setImageParentId(Long imageParentId) {
        this.imageParentId = imageParentId;
    }

    /**
     * 获取对应的影像任务ID
     *
     * @return edoc_header_id - 对应的影像任务ID
     */
    public Long getEdocHeaderId() {
        return edocHeaderId;
    }

    /**
     * 设置对应的影像任务ID
     *
     * @param edocHeaderId 对应的影像任务ID
     */
    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    /**
     * 获取图像/附件名称
     *
     * @return image_name - 图像/附件名称
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * 设置图像/附件名称
     *
     * @param imageName 图像/附件名称
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * 获取图像/附件url地址
     *
     * @return image_url - 图像/附件url地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图像/附件url地址
     *
     * @param imageUrl 图像/附件url地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取图像/附件保存根目录
     *
     * @return image_root_path - 图像/附件保存根目录
     */
    public String getImageRootPath() {
        return imageRootPath;
    }

    /**
     * 设置图像/附件保存根目录
     *
     * @param imageRootPath 图像/附件保存根目录
     */
    public void setImageRootPath(String imageRootPath) {
        this.imageRootPath = imageRootPath;
    }

    /**
     * 获取图像/附件后缀名称
     *
     * @return image_format - 图像/附件后缀名称
     */
    public String getDocFormat() {
        return docFormat;
    }

    /**
     * 设置图像/附件后缀名称
     *
     * @param docFormat 图像/附件后缀名称
     */
    public void setDocFormat(String docFormat) {
        this.docFormat = docFormat;
    }

    /**
     * 获取图像/附件排序编号
     *
     * @return page_no - 图像/附件排序编号
     */
    public Long getPageNo() {
        return pageNo;
    }

    /**
     * 设置图像/附件排序编号
     *
     * @param pageNo 图像/附件排序编号
     */
    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取是否替换 0 未替换 1 替换（替扫使用）
     *
     * @return is_replace - 是否替换 0 未替换 1 替换（替扫使用）
     */
    public String getIsReplace() {
        return isReplace;
    }

    /**
     * 设置是否替换 0 未替换 1 替换（替扫使用）
     *
     * @param isReplace 是否替换 0 未替换 1 替换（替扫使用）
     */
    public void setIsReplace(String isReplace) {
        this.isReplace = isReplace;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取图片/附件生成模式（客户端扫描/web端上传等，详见数据字典配置）
     *
     * @return generate_model - 图片/附件生成模式（客户端扫描/web端上传等，详见数据字典配置）
     */
    public String getGenerateModel() {
        return generateModel;
    }

    /**
     * 设置图片/附件生成模式（客户端扫描/web端上传等，详见数据字典配置）
     *
     * @param generateModel 图片/附件生成模式（客户端扫描/web端上传等，详见数据字典配置）
     */
    public void setGenerateModel(String generateModel) {
        this.generateModel = generateModel;
    }

    /**
     * 获取图像/附件类型（普票/专票/单据封面等等，详细见数据字典配置）
     *
     * @return edoc_image_type - 图像/附件类型（普票/专票/单据封面等等，详细见数据字典配置）
     */
    public String getEdocImageType() {
        return edocImageType;
    }

    /**
     * 设置图像/附件类型（普票/专票/单据封面等等，详细见数据字典配置）
     *
     * @param edocImageType 图像/附件类型（普票/专票/单据封面等等，详细见数据字典配置）
     */
    public void setEdocImageType(String edocImageType) {
        this.edocImageType = edocImageType;
    }

    /**
     * 获取扫描人
     *
     * @return scanner - 扫描人
     */
    public String getScanner() {
        return scanner;
    }

    /**
     * 设置扫描人
     *
     * @param scanner 扫描人
     */
    public void setScanner(String scanner) {
        this.scanner = scanner;
    }

    /**
     * 获取扫描代码
     *
     * @return scanner_code - 扫描代码
     */
    public String getScannerCode() {
        return scannerCode;
    }

    /**
     * 设置扫描代码
     *
     * @param scannerCode 扫描代码
     */
    public void setScannerCode(String scannerCode) {
        this.scannerCode = scannerCode;
    }


    /**
     * @return ext_field1
     */
    public String getExtField1() {
        return extField1;
    }

    /**
     * @param extField1
     */
    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    /**
     * @return ext_field2
     */
    public String getExtField2() {
        return extField2;
    }

    /**
     * @param extField2
     */
    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    /**
     * @return ext_field3
     */
    public String getExtField3() {
        return extField3;
    }

    /**
     * @param extField3
     */
    public void setExtField3(String extField3) {
        this.extField3 = extField3;
    }

    /**
     * @return ext_field4
     */
    public String getExtField4() {
        return extField4;
    }

    /**
     * @param extField4
     */
    public void setExtField4(String extField4) {
        this.extField4 = extField4;
    }

    /**
     * @return ext_field5
     */
    public String getExtField5() {
        return extField5;
    }

    /**
     * @param extField5
     */
    public void setExtField5(String extField5) {
        this.extField5 = extField5;
    }

    private static final String EDOCIMAGEPREFIX = "edocImage.";


    public enum EdocImageSourceEnum implements BaseEnum {
        edocImageSource1("1"),
        edocImageSource2("2");

        private final String code;

        EdocImageSourceEnum(String code)
        {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return EdocLoadConfig.get(EDOCIMAGEPREFIX + this.name());

        }

        public static EdocImageSourceEnum parse(String code) {
            EdocImageSourceEnum result = null; // Default
            for (EdocImageSourceEnum item : EdocImageSourceEnum.values()) {
                if (BeanUtil.equals(item.getCode(),code)) {
                    result = item;
                    break;
                }
            }
            return result;
        }

    }

}
