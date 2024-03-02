package com.zhierxi.cloudcc.com.g3cloud.platform.object;

/**
 * Description: 模拟Cloudcc的CCObject类，仅模拟
 * Author:Gorden
 * Date:2024/3/2 9:11
 * Version:1.0
 **/

import java.util.HashMap;

public class CCObject extends HashMap {
    public static final String OBJECT_API = "CCObjectAPI";
    public static final String IS_SHARED = "isShared";

    public CCObject() {
    }

    public CCObject(String ccobj) {
        this.put("CCObjectAPI", ccobj);
        this.put("ownerid", "");
    }

    public CCObject(String ccobj, String isShared) {
        this.put("CCObjectAPI", ccobj);
        this.put("isShared", "isShared");
    }

    public void putSumValue(String sumValueKey, String value) {
        this.put("sum" + sumValueKey, value);
    }

    public Object getSumValue(String sumValueKey) {
        return this.get("sum" + sumValueKey);
    }

    public String getObjectApiName() {
        return (String)this.get("CCObjectAPI");
    }
}
