package com.book.pojo;

import java.util.Date;

public class Appraise {
    private Integer id;

    private Integer userId;

    private String appraiseDesc;

    private Date createTime;

    private Date updateTime;

    private Integer bookId;

    public Appraise(Integer id, Integer userId, String appraiseDesc, Date createTime, Date updateTime, Integer bookId) {
        this.id = id;
        this.userId = userId;
        this.appraiseDesc = appraiseDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.bookId = bookId;
    }

    public Appraise() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAppraiseDesc() {
        return appraiseDesc;
    }

    public void setAppraiseDesc(String appraiseDesc) {
        this.appraiseDesc = appraiseDesc == null ? null : appraiseDesc.trim();
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}