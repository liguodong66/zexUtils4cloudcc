package com.zhierxi.cloudcc.com.g3cloud.syseg.servicecore;

/**
 * Description:模拟Cloudcc的ServiceResult类，仅模拟
 * Author:Gorden
 * Date:2024/3/2 9:16
 * Version:1.0
 **/

import java.util.HashMap;

public class ServiceResult extends HashMap {
    private static final long serialVersionUID = 4635763131021232482L;
    private String rtnInfo;
    private String errorMessage = "NOERROR_911";
    private String rtnCode;
    private String rtnPage;
    private boolean isSuccess;

    public ServiceResult() {
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRtnPage() {
        return this.rtnPage;
    }

    public void setRtnPage(String rtnPage) {
        this.rtnPage = rtnPage;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getRtnInfo() {
        if (this.rtnInfo == null) {
            this.rtnInfo = "";
        }

        return this.rtnInfo;
    }

    public void setRtnInfo(String rtnInfo) {
        this.rtnInfo = rtnInfo;
    }

    public String getRtnCode() {
        return this.rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getString(String key) {
        return (String)this.get(key);
    }
}
