package com.zhierxi.cloudcc;


import com.zhierxi.cloudcc.com.g3cloud.platform.common.UserInfo;
import com.zhierxi.cloudcc.com.g3cloud.platform.object.CCObject;
import com.zhierxi.cloudcc.com.g3cloud.syseg.servicecore.ServiceResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：Gorden
 * @date ：Created in 2022/3/26 10:25
 * @description：CloudccCCService类重构
 * @modified By：
 * @version: $
 */
public class CCService  {
    private UserInfo userInfo;
    public CCService(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ServiceResult insertLt(CCObject c ) throws Exception {

        return null;
    }


    public ServiceResult updateLt(CCObject c ){

        return null;
    }

    public List<CCObject> cqueryWithRoleRight(String objname,String cond ){

        return null;
    }

    public static ServiceResult getUserInfo(String siteId, String username, String md5pwd){

        return null;
    }

    public Map getListCustomSetting(String express100, String kuaidi100) {
        return null;
    }

    public List<Map> showPendingHis(String userId){

        return null;
    }

    public String process(String stat,String workItemid,String userid,String commit,String s){
        return "";
    }

    public List<CCObject> cquery(String ccuser, String s) {
        return  new ArrayList<CCObject>();
    }
}
