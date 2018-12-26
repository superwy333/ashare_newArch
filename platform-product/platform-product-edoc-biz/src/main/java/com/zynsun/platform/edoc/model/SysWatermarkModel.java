package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

/**
 * Created by Tiger on 2017/7/12.
 */
public class SysWatermarkModel extends PageModel {

    /**
     * 水印名称
     */
    private String name;

    /**
     * 水印文本
     */
    private String watermarkText;
    /**
     * 不透明度
     */
    private String opacity;

    /**
     * 字形大小
     */
    private String fontSize;

    /**
     * 颜色
     */
    private String color;

    /**
     * 对齐方式
     */
    private String textAlign;

    /**
     * 宽
     */
    private String width;

    /**
     * 高
     */
    private String height;

    /**
     * 倾斜度
     */
    private String gradient;

    /**
     * 1-下载水印，2-浏览水印
     */
    private String type;

    private String status;

    /**
     * 描述
     */
    private String remarks;



    /**
     * 获取不透明度
     *
     * @return opacity - 不透明度
     */
    public String getOpacity() {
        return opacity;
    }

    /**
     * 设置不透明度
     *
     * @param opacity 不透明度
     */
    public void setOpacity(String opacity) {
        this.opacity = opacity == null ? null : opacity.trim();
    }


    /**
     * 获取颜色
     *
     * @return color - 颜色
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置颜色
     *
     * @param color 颜色
     */
    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    /**
     * 获取宽
     *
     * @return width - 宽
     */
    public String getWidth() {
        return width;
    }

    /**
     * 设置宽
     *
     * @param width 宽
     */
    public void setWidth(String width) {
        this.width = width == null ? null : width.trim();
    }

    /**
     * 获取高
     *
     * @return height - 高
     */
    public String getHeight() {
        return height;
    }

    /**
     * 设置高
     *
     * @param height 高
     */
    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    /**
     * 获取倾斜度
     *
     * @return gradient - 倾斜度
     */
    public String getGradient() {
        return gradient;
    }

    /**
     * 设置倾斜度
     *
     * @param gradient 倾斜度
     */
    public void setGradient(String gradient) {
        this.gradient = gradient == null ? null : gradient.trim();
    }

    /**
     * 获取1-下载水印，2-浏览水印
     *
     * @return type - 1-下载水印，2-浏览水印
     */
    public String getType() {
        return type;
    }

    /**
     * 设置1-下载水印，2-浏览水印
     *
     * @param type 1-下载水印，2-浏览水印
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }
}
