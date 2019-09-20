package com.book.pojo;

import java.util.Date;

public class Shop {
    private Integer id;

    private String shopName;

    private String shopDesc;

    private String shopAddr;

    private String phone;

    private String shopMainImage;

    private String subImages;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer ownerId;

    public Shop(Integer id, String shopName, String shopDesc, String shopAddr, String phone, String shopMainImage, String subImages, Integer status, Date createTime, Date updateTime, Integer ownerId) {
        this.id = id;
        this.shopName = shopName;
        this.shopDesc = shopDesc;
        this.shopAddr = shopAddr;
        this.phone = phone;
        this.shopMainImage = shopMainImage;
        this.subImages = subImages;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.ownerId = ownerId;
    }

    public Shop() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc == null ? null : shopDesc.trim();
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr == null ? null : shopAddr.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getShopMainImage() {
        return shopMainImage;
    }

    public void setShopMainImage(String shopMainImage) {
        this.shopMainImage = shopMainImage == null ? null : shopMainImage.trim();
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages == null ? null : subImages.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}