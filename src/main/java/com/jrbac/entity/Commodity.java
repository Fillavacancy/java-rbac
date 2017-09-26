package com.jrbac.entity;

import java.io.Serializable;

/**
 * 商品类
 * 
 * @author Administrator
 */
public class Commodity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2337129893850171457L;
    /**
     * 编号
     */
    private Integer           comId;
    /**
     * 名称
     */
    private String            comName;
    /**
     * 标题
     */
    private String            comTitle;
    /**
     * 内容
     */
    private String            comContent;
    /**
     * 价格
     */
    private Double            comPrice;
    /**
     * 数量
     */
    private Integer           comNumber;
    /**
     * 类型
     */
    private String            comType;
    /**
     * 创建时间
     */
    private String            comCreatedate;
    /**
     * 修改时间
     */
    private String            comUpdate;
    /**
     * 销量
     */
    private Integer           comSalesvolume;
    /**
     * 品牌
     */
    private Brand             comBrand;
    /**
     * 状态（true：上架 false：下架）
     */
    private Long              comState;
    /**
     * 商品图片路径
     */
    private String            comImgurl;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComTitle() {
        return comTitle;
    }

    public void setComTitle(String comTitle) {
        this.comTitle = comTitle;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Double getComPrice() {
        return comPrice;
    }

    public void setComPrice(Double comPrice) {
        this.comPrice = comPrice;
    }

    public Integer getComNumber() {
        return comNumber;
    }

    public void setComNumber(Integer comNumber) {
        this.comNumber = comNumber;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getComCreatedate() {
        return comCreatedate;
    }

    public void setComCreatedate(String comCreatedate) {
        this.comCreatedate = comCreatedate;
    }

    public String getComUpdate() {
        return comUpdate;
    }

    public void setComUpdate(String comUpdate) {
        this.comUpdate = comUpdate;
    }

    public Integer getComSalesvolume() {
        return comSalesvolume;
    }

    public void setComSalesvolume(Integer comSalesvolume) {
        this.comSalesvolume = comSalesvolume;
    }

    public Brand getComBrand() {
        return comBrand;
    }

    public void setComBrand(Brand comBrand) {
        this.comBrand = comBrand;
    }

    public Long getComState() {
        return comState;
    }

    public void setComState(Long comState) {
        this.comState = comState;
    }

    public String getComImgurl() {
        return comImgurl;
    }

    public void setComImgurl(String comImgurl) {
        this.comImgurl = comImgurl;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Commodity [comId=" + comId + ", comName=" + comName + ", comTitle=" + comTitle + ", comContent=" + comContent + ", comPrice=" + comPrice + ", comNumber=" + comNumber + ", comType=" + comType + ", comCreatedate=" + comCreatedate + ", comUpdate=" + comUpdate + ", comSalesvolume=" + comSalesvolume + ", comBrand=" + comBrand + ", comState=" + comState + ", comImgurl=" + comImgurl + "]";
    }
}
