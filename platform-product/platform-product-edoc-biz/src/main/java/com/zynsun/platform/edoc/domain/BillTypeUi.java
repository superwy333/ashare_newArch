package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "bill_type_ui")
public class BillTypeUi extends BaseDomain {
    /**
     * 表单ID
     */
    @Column(name = "style_id")
    private String styleId;

    /**
     * 属性ID
     */
    @Column(name = "attr_id")
    private String attrId;

    /**
     * 表单属性组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 分组标志
     */
    @Column(name = "group_symbol")
    private String groupSymbol;

    /**
     * 表单属性行号
     */
    @Column(name = "line_no")
    private Integer lineNo;

    /**
     * 表单属性序号
     */
    @Column(name = "sequence_no")
    private Integer sequenceNo;

    /**
     * 输入框类型
     */
    @Column(name = "input_type")
    private String inputType;

    /**
     * 输入框类型名称
     */
    @Column(name = "input_type_name")
    private String inputTypeName;

    /**
     * 输入框宽度
     */
    @Column(name = "input_width")
    private Integer inputWidth;

    /**
     * 输入框长度
     */
    @Column(name = "input_height")
    private Integer inputHeight;

    /**
     * 输入框值格式
     */
    @Column(name = "value_pattern")
    private String valuePattern;

    /**
     * 格式错误提示信息
     */
    @Column(name = "pattern_wrong_tip")
    private String patternWrongTip;

    /**
     * 输入框值选项
     */
    @Column(name = "value_option")
    private String valueOption;

    /**
     * 是否必填项（0：否；1：是）
     */
    @Column(name = "is_required")
    private String isRequired;

    /**
     * 识别位置
     */
    @Column(name = "recognition_position")
    private String recognitionPosition;

    /**
     * 识别类型
     */
    @Column(name = "recognition_type")
    private String recognitionType;

    /**
     * 识别结果
     */
    @Column(name = "recognition_result")
    private String recognitionResult;

    /**
     * 描述
     */
    private String remarks;

    /**
     * 获取表单ID
     *
     * @return style_id - 表单ID
     */
    public String getStyleId() {
        return styleId;
    }

    /**
     * 设置表单ID
     *
     * @param styleId 表单ID
     */
    public void setStyleId(String styleId) {
        this.styleId = styleId == null ? null : styleId.trim();
    }

    /**
     * 获取属性ID
     *
     * @return attr_id - 属性ID
     */
    public String getAttrId() {
        return attrId;
    }

    /**
     * 设置属性ID
     *
     * @param attrId 属性ID
     */
    public void setAttrId(String attrId) {
        this.attrId = attrId == null ? null : attrId.trim();
    }

    /**
     * 获取表单属性组名称
     *
     * @return group_name - 表单属性组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置表单属性组名称
     *
     * @param groupName 表单属性组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * 获取分组标志
     *
     * @return group_symbol - 分组标志
     */
    public String getGroupSymbol() {
        return groupSymbol;
    }

    /**
     * 设置分组标志
     *
     * @param groupSymbol 分组标志
     */
    public void setGroupSymbol(String groupSymbol) {
        this.groupSymbol = groupSymbol == null ? null : groupSymbol.trim();
    }

    /**
     * 获取表单属性行号
     *
     * @return line_no - 表单属性行号
     */
    public Integer getLineNo() {
        return lineNo;
    }

    /**
     * 设置表单属性行号
     *
     * @param lineNo 表单属性行号
     */
    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    /**
     * 获取表单属性序号
     *
     * @return sequence_no - 表单属性序号
     */
    public Integer getSequenceNo() {
        return sequenceNo;
    }

    /**
     * 设置表单属性序号
     *
     * @param sequenceNo 表单属性序号
     */
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /**
     * 获取输入框类型
     *
     * @return input_type - 输入框类型
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置输入框类型
     *
     * @param inputType 输入框类型
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 获取输入框类型名称
     *
     * @return input_type_name - 输入框类型名称
     */
    public String getInputTypeName() {
        return inputTypeName;
    }

    /**
     * 设置输入框类型名称
     *
     * @param inputTypeName 输入框类型名称
     */
    public void setInputTypeName(String inputTypeName) {
        this.inputTypeName = inputTypeName == null ? null : inputTypeName.trim();
    }

    /**
     * 获取输入框宽度
     *
     * @return input_width - 输入框宽度
     */
    public Integer getInputWidth() {
        return inputWidth;
    }

    /**
     * 设置输入框宽度
     *
     * @param inputWidth 输入框宽度
     */
    public void setInputWidth(Integer inputWidth) {
        this.inputWidth = inputWidth;
    }

    /**
     * 获取输入框长度
     *
     * @return input_height - 输入框长度
     */
    public Integer getInputHeight() {
        return inputHeight;
    }

    /**
     * 设置输入框长度
     *
     * @param inputHeight 输入框长度
     */
    public void setInputHeight(Integer inputHeight) {
        this.inputHeight = inputHeight;
    }

    /**
     * 获取输入框值格式
     *
     * @return value_pattern - 输入框值格式
     */
    public String getValuePattern() {
        return valuePattern;
    }

    /**
     * 设置输入框值格式
     *
     * @param valuePattern 输入框值格式
     */
    public void setValuePattern(String valuePattern) {
        this.valuePattern = valuePattern == null ? null : valuePattern.trim();
    }

    /**
     * 获取格式错误提示信息
     *
     * @return pattern_wrong_tip - 格式错误提示信息
     */
    public String getPatternWrongTip() {
        return patternWrongTip;
    }

    /**
     * 设置格式错误提示信息
     *
     * @param patternWrongTip 格式错误提示信息
     */
    public void setPatternWrongTip(String patternWrongTip) {
        this.patternWrongTip = patternWrongTip == null ? null : patternWrongTip.trim();
    }

    /**
     * 获取输入框值选项
     *
     * @return value_option - 输入框值选项
     */
    public String getValueOption() {
        return valueOption;
    }

    /**
     * 设置输入框值选项
     *
     * @param valueOption 输入框值选项
     */
    public void setValueOption(String valueOption) {
        this.valueOption = valueOption == null ? null : valueOption.trim();
    }

    /**
     * 获取是否必填项（0：否；1：是）
     *
     * @return is_required - 是否必填项（0：否；1：是）
     */
    public String getIsRequired() {
        return isRequired;
    }

    /**
     * 设置是否必填项（0：否；1：是）
     *
     * @param isRequired 是否必填项（0：否；1：是）
     */
    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired == null ? null : isRequired.trim();
    }

    /**
     * 获取识别位置
     *
     * @return recognition_position - 识别位置
     */
    public String getRecognitionPosition() {
        return recognitionPosition;
    }

    /**
     * 设置识别位置
     *
     * @param recognitionPosition 识别位置
     */
    public void setRecognitionPosition(String recognitionPosition) {
        this.recognitionPosition = recognitionPosition == null ? null : recognitionPosition.trim();
    }

    /**
     * 获取识别类型
     *
     * @return recognition_type - 识别类型
     */
    public String getRecognitionType() {
        return recognitionType;
    }

    /**
     * 设置识别类型
     *
     * @param recognitionType 识别类型
     */
    public void setRecognitionType(String recognitionType) {
        this.recognitionType = recognitionType == null ? null : recognitionType.trim();
    }

    /**
     * 获取识别结果
     *
     * @return recognition_result - 识别结果
     */
    public String getRecognitionResult() {
        return recognitionResult;
    }

    /**
     * 设置识别结果
     *
     * @param recognitionResult 识别结果
     */
    public void setRecognitionResult(String recognitionResult) {
        this.recognitionResult = recognitionResult == null ? null : recognitionResult.trim();
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
}