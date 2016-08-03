package com.casecollection.backend.model;

import java.util.Date;

public class SysUser {
    private Long id;

    private String loginName;

    private String realName;

    private String password;

    private String schoolCode;

    private String email;

    private String qqNum;

    private String phoneNum;

    private String wxOpenid;

    private Integer status;

    private Integer cusLevel;

    private Integer dataLevel;

    private Integer handlingNum;

    private Integer isHandling;

    private Integer hasApprove;

    private String description;

    private String creator;

    private String modifier;

    private Date createTime;

    private Date modifyTime;

    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCusLevel() {
        return cusLevel;
    }

    public void setCusLevel(Integer cusLevel) {
        this.cusLevel = cusLevel;
    }

    public Integer getDataLevel() {
        return dataLevel;
    }

    public void setDataLevel(Integer dataLevel) {
        this.dataLevel = dataLevel;
    }

    public Integer getHandlingNum() {
        return handlingNum;
    }

    public void setHandlingNum(Integer handlingNum) {
        this.handlingNum = handlingNum;
    }

    public Integer getIsHandling() {
        return isHandling;
    }

    public void setIsHandling(Integer isHandling) {
        this.isHandling = isHandling;
    }

    public Integer getHasApprove() {
        return hasApprove;
    }

    public void setHasApprove(Integer hasApprove) {
        this.hasApprove = hasApprove;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}