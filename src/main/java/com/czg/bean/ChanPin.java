package com.czg.bean;

import java.io.Serializable;
import java.util.Date;

public class ChanPin implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer cid;

    private Integer cnum;

    private String cname;

    private Integer hot;

    private String materials;

    private String seasoning;

    private String note;

    private Short money;

    private String minImg;

    private String maxImg;

    private String extendImg;

    private Integer status;

    private Date dateLine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCnum() {
        return cnum;
    }

    public void setCnum(Integer cnum) {
        this.cnum = cnum;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getSeasoning() {
        return seasoning;
    }

    public void setSeasoning(String seasoning) {
        this.seasoning = seasoning;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Short getMoney() {
        return money;
    }

    public void setMoney(Short money) {
        this.money = money;
    }

    public String getMinImg() {
        return minImg;
    }

    public void setMinImg(String minImg) {
        this.minImg = minImg;
    }

    public String getMaxImg() {
        return maxImg;
    }

    public void setMaxImg(String maxImg) {
        this.maxImg = maxImg;
    }

    public String getExtendImg() {
        return extendImg;
    }

    public void setExtendImg(String extendImg) {
        this.extendImg = extendImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateLine() {
        return dateLine;
    }

    public void setDateLine(Date dateLine) {
        this.dateLine = dateLine;
    }
}