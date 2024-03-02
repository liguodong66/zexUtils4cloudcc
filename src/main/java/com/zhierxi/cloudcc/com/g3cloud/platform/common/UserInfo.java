package com.zhierxi.cloudcc.com.g3cloud.platform.common;

/**
 * Description:模拟Cloudcc的UserInfo类，仅模拟
 * Author:Gorden
 * Date:2024/3/2 9:12
 * Version:1.0
 **/

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 6598732976198457530L;
    private String userName;
    private String loginName;
    private String alias;
    private String realName;
    private String userId;
    private String orgName;
    private String orgId;
    private String dbIndex;
    private String profileName;
    private String profileId;
    private String roleName;
    private String roleId;
    private String language;
    private boolean isAdmin;
    private String email;
    private String department;
    private String phone;
    private String mobilePhone;
    private String managerId;
    private String title;
    private String loginType;
    private String standalone;
    private String orgPackage;

    public UserInfo() {
    }

    protected String getLoginName() {
        return this.loginName;
    }

    protected void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginType() {
        return this.loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getManagerId() {
        return this.managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrgPackage() {
        return this.orgPackage;
    }

    protected void setOrgPackage(String orgPackage) {
        this.orgPackage = orgPackage;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getProfileId() {
        return this.profileId;
    }

    protected void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    protected void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return this.userId;
    }

    protected void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgId() {
        return this.orgId;
    }

    protected void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDbIndex() {
        return this.dbIndex;
    }

    protected void setDbIndex(String dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getProfileName() {
        return this.profileName;
    }

    protected void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    protected void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {
        return this.roleId;
    }

    protected void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getStandalone() {
        return this.standalone;
    }

    protected void setStandalone(String standalone) {
        this.standalone = standalone;
    }
}
