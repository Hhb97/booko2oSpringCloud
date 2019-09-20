package com.book.pojo;

import java.util.Date;

public class BookDesc {
    private Integer bookId;

    private String bookDesc;

    private Date createTime;

    private Date updateTime;

    public BookDesc(Integer bookId, String bookDesc, Date createTime, Date updateTime) {
        this.bookId = bookId;
        this.bookDesc = bookDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BookDesc() {
        super();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc == null ? null : bookDesc.trim();
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
}