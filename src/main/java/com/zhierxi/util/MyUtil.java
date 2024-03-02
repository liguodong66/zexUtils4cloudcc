package com.zhierxi.util;

/**
 * Description:
 * Author:Gorden
 * Date:2024/3/2 9:06
 * Version:1.0
 **/



import com.zhierxi.cloudcc.CCService;
import com.zhierxi.cloudcc.com.g3cloud.platform.common.UserInfo;
import com.zhierxi.cloudcc.com.g3cloud.platform.object.CCObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyUtil{
    public MyUtil(){}
    /**
     * BASE64加密
     * @param str
     * @return String
     */
    public static String encodeBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
//            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    /**
     * BASE64解密
     * @param str
     * @return String
     */
    public static String decodeBase64(String str) {
        byte[] b = null;
        String result = null;
        if (str != null) {
//            BASE64Decoder decoder = new BASE64Decoder();
            try {
//                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * URL转码
     * @param url
     * @return String
     */
    public static String encodeUrl(String url){
        String str = "";
        try {
            str = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 参数转码
     * @param param
     * @return String
     */
    public static String encodeParam(String param){
        if(param!=null&&!param.isEmpty()){
            byte[] iso;
            try {
                iso = param.getBytes("ISO-8859-1");
                if(param.equals(new String(iso, "ISO-8859-1"))){
                    param = new String(iso, "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            param = "";
        }
        return param;
    }

    /**
     * 参数转码，指定字符集
     * @param param 参数
     * @param from 来自字符集
     * @param to 转向字符集
     * @return String
     */
    public static String encodeParam(String param, String from, String to){
        if(param!=null&&!param.isEmpty()){
            byte[] iso;
            try {
                iso = param.getBytes(from);
                if(param.equals(new String(iso, from))){
                    param = new String(iso, to);
                }
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            param = "";
        }
        return param;
    }

    /**
     * 非null取值，不带返回值，用于HashMap(如record_new)取值
     * @param record_new
     * @param apiname
     * @return String 没有就返回空串
     */
    public static String getParamFromMap(Map<String, String> record_new, String apiname){
        return record_new.get(apiname)==null?"":record_new.get(apiname).toString();
    }

    /**
     * 非null取值，带返回值，用于HashMap(如record_new)取值
     * @param map
     * @param apiname
     * @param rtn 默认值
     * @return String 没有就返回默认值
     */
    public static String getParamFromMap(Map<String, String> map, String apiname, String rtn){
        return map.get(apiname)==null?rtn:map.get(apiname).toString();
    }

    /**
     * 非null取值，不带返回值，用于CCOject取值
     * @param cco
     * @param apiname
     * @return String 没有就返回空串
     */
    public static String getParamFromCCO(CCObject cco, String apiname){
        return cco.get(apiname)==null?"":cco.get(apiname).toString();
    }

    /**
     * 非null取值，带返回值，用于CCOject取值
     * @param cco
     * @param apiname
     * @param rtn 默认值
     * @return String 没有就返回默认值
     */
    public static String getParamFromCCO(CCObject cco, String apiname, String rtn){
        return cco.get(apiname)==null?rtn:cco.get(apiname).toString();
    }

    /**
     * MD5转码
     * @param map
     * @return String
     */
    public static String getMD5(Map<String, String> map) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(map.get("str").toString().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BigInteger(1, md.digest()).toString(16);
    }

    /**
     * MD5转码
     * @param str
     * @return String
     */
    public static String getMD5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BigInteger(1, md.digest()).toString(16);
    }
    // public static String getMD5(String str) {
    // MessageDigest md = null;
    // try {
    // md = MessageDigest.getInstance("MD5");
    // md.update(str.getBytes("UTF-8"));
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return new BigInteger(1, md.digest()).toString(16);
    // }

    /**
     * 格式化输给定日历日期的本年月,默认格式输出年月【“yyyyMM”】
     * @param calendar 指定日历日期
     * @return 按默认格式输出年月【“yyyyMM”】
     */
    public static String getCurrentYM(Calendar calendar){
        return getCurrentYMFmt(calendar,"yyyyMM");
    }

    /**
     * 格式化输给定日历日期的本月年月
     * @param calendar 指定日历日期
     * @param fmt 指定格式
     * @return 按指定格式输出年月
     */
    public static String getCurrentYMFmt(Calendar calendar,String fmt){
        if(fmt == null){
            fmt = "yyyyMM";
        }
        return formatCalendar(calendar,fmt);
    }

    /**
     * 格式化输给定日历日期的上一年月,默认格式输出年月【“yyyyMM”】
     * @param calendar 指定日历日期
     * @return 按默认格式输出年月【“yyyyMM”】
     */
    public static String getLastYm(Calendar calendar){

        return getLastYmFmt(calendar,"yyyyMM");
    }

    /**
     * 格式化输给定日历日期的上一年月
     * @param calendar 指定日历日期
     * @param fmt 指定格式
     * @return 按指定格式输出年月
     */
    public static String getLastYmFmt(Calendar calendar,String fmt){
        calendar.add(Calendar.MONTH,-1);

        if(fmt == null){
            fmt = "yyyyMM";
        }

        return formatCalendar(calendar,fmt);
    }

//	public static String get

    /**
     * 格式化输出日历
     * @param calendar 日历日期
     * @param fmt 输出格式
     * @return 根据fmt格式出输出的
     */
    public static String formatCalendar(Calendar calendar,String fmt){
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(calendar.getTime());
    }

    /**
     * 转化日期格式，指定格式
     * @param date 日期字符串
     * @param formatFrom 现在的格式，如:yyyy_MM-dd HH:mm:ss
     * @param formatTo 要转化的格式，如：yyyy_MM-dd
     * @return String 日期字符串
     */
    public static String formatDate(String date, String formatFrom, String formatTo){
        try {
            date = new SimpleDateFormat(formatTo).format(new SimpleDateFormat(formatFrom).parse(date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转化日期格式，不定格式
     * @param date 日期字符串
     * @return String yyyy-MM-dd
     */
    public static String formatDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年M月d日");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/M/d");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-M-d");
        SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy.M.d");
        SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMMdd");
        try {
            date = sdf.format(sdf1.parse(date));
        } catch (ParseException e1) {
            try {
                date = sdf.format(sdf2.parse(date));
            } catch (ParseException e2) {
                try {
                    date = sdf.format(sdf3.parse(date));
                } catch (ParseException e3) {
                    try {
                        date = sdf.format(sdf4.parse(date));
                    } catch (ParseException e4) {
                        try {
                            date = sdf.format(sdf5.parse(date));
                        } catch (ParseException e5) {
                            try {
                                date = sdf.format(sdf6.parse(date));
                            } catch (ParseException e6) {
                                try {
                                    date = sdf.format(sdf7.parse(date));
                                } catch (ParseException e7) {
                                    try {
                                        date = sdf.format(sdf8.parse(date));
                                    } catch (ParseException e8) {
                                        return "";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return date;
    }

    /**
     * 取得昨天的日期
     * @return String yyyy-MM-dd
     */
    public static String getYestoday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 取得明天的日期
     * @return String yyyy-MM-dd
     */
    public static String getTomorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 取得某日期前一天的日期
     * @param day
     * @return String yyyy-MM-dd
     */
    public static String getPrevDay(String day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = sdf.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 取得某日期后一天的日期
     * @param day
     * @return String yyyy-MM-dd
     */
    public static String getNextDay(String day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = sdf.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 取得某日期前后某天数的日期
     * @param day 某日期
     * @param days 天数，向前为负数，向后为正数
     * @return String yyyy-MM-dd
     */
    public static String getDay(String day, int days){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = sdf.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return sdf.format(calendar.getTime());
    }

    /**
     * 取得以今天为基准前后某天数的日期
     * @param days 天数，向前为负数，向后为正数
     * @return String yyyy-MM-dd
     */
    public static String getDay(int days){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return sdf.format(calendar.getTime());
    }

    /**
     * 计算两个日期之间相差的天数
     * @param date1 日期1
     * @param date2 日期2
     * @param isAbs 是否取绝对值
     * @return int 相差天数
     */
    public static int getDurationDays(Date date1,Date date2, Boolean isAbs){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long between_days = 0;
        try {
            date1 = sdf.parse(sdf.format(date1));
            date2 = sdf.parse(sdf.format(date2));
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(date2);
            long time2 = cal.getTimeInMillis();
            between_days = isAbs?Math.abs((time1-time2)/(1000*3600*24)):(time1-time2)/(1000*3600*24);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     * @param d1 日期1 yyyy-MM-dd
     * @param d2 日期2 yyyy-MM-dd
     * @param isAbs 是否取绝对值
     * @return 相差天数
     */
    public static int getDurationDays(String d1,String d2, Boolean isAbs){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long between_days = 0;
        try {
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(date2);
            long time2 = cal.getTimeInMillis();
            between_days = isAbs?Math.abs((time1-time2)/(1000*3600*24)):(time1-time2)/(1000*3600*24);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算某日期到今天的天数
     * @param date 日期  yyyy-MM-dd
     * @param isAbs 是否取绝对值
     * @return int 相差天数，今天之前为负数，今天之后为正数
     */
    public static int getDurationDays(String date, Boolean isAbs){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        long between_days = 0;
        try {
            Date date1 = sdf.parse(date);
            Date date2 = sdf.parse(sdf.format(new Date()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(date2);
            long time2 = cal.getTimeInMillis();
            between_days = isAbs?Math.abs((time1-time2)/(1000*3600*24)):(time1-time2)/(1000*3600*24);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两时间的秒数差，取绝对值
     * @param beginTime yyyy-MM-dd HH:mm:ss
     * @param endTime yyyy-MM-dd HH:mm:ss
     * @return int 秒数
     */
    public static int getDurationSeconds(String beginTime, String endTime){
        int durationSecs = 0;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            durationSecs = Math.abs((int)((sdf1.parse(endTime).getTime() - sdf1.parse(beginTime).getTime())/1000L));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return durationSecs;
    }

    /**
     * 计算两时间差
     * @param beginTime
     * @param endTime
     * @return String HH:mm:ss，超过一天的累加到小时上，例如：36:45:08
     */
    public static String getDurationTime(String beginTime, String endTime){
        int durationSecs = 0;
        String duration = "";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("mm:ss");
        try {
            durationSecs = Math.abs((int)((sdf1.parse(endTime).getTime() - sdf1.parse(beginTime).getTime())/1000L));
            int hours = durationSecs/3600;
            int minutes = durationSecs%3600/60;
            int seconds = durationSecs%60;
            duration = (hours<10?("0"+hours):hours) + ":" + sdf2.format(sdf2.parse(minutes+":"+seconds));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return duration;
    }

    /**
     * 计算两个年月之间的相隔月数，括首尾，如202105与202106，相隔为2
     * @param beginTime  	yyyyMM
     * @param endTime		yyyyMM
     * @return dura 		相隔月数
     * @throws ParseException
     */
    public static int getDurationMonths(String beginTime,String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar beginC = new GregorianCalendar();
        beginC.setTime(sdf.parse(beginTime));
        Calendar endC = new GregorianCalendar();
        endC.setTime(sdf.parse(endTime));
        int beginY = beginC.get(Calendar.YEAR);
        int beginM = beginC.get(Calendar.MONTH);
        int endY = endC.get(Calendar.YEAR);
        int endM = endC.get(Calendar.MONTH);


        int dura = (endY-beginY)*12 + endM-beginM+1;

        return dura;
    }

    /**
     * 获取给定日期的周一
     * @param c 日历
     * @return yyyy-MM-dd
     */
    public static String getFirstDayOfWeek(Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int firstD = c.getFirstDayOfWeek();
        int nowd = c.get(Calendar.DAY_OF_WEEK);
        int daysOfGap = nowd-firstD;
        c.add(Calendar.DAY_OF_MONTH,-daysOfGap);

        return sdf.format(c.getTime());
    }

    /**
     * 获取给定日期的周日
     * @param c 日历
     * @return yyyy-MM-dd
     */
    public static String getLastDayOfWeek(Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int firstD = c.getFirstDayOfWeek();
        int nowd = c.get(Calendar.DAY_OF_WEEK);
        int daysOfGap = 7-nowd;
        c.add(Calendar.DAY_OF_MONTH,daysOfGap);

        return sdf.format(c.getTime());
    }

    /**
     * 获取当前日期的所在月的第一天
     * @param c
     * @return
     */
    public static String getFirstDayOfMonth(Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.set(Calendar.DAY_OF_MONTH,1);

        return sdf.format(c.getTime());
    }

    /**
     * 获取当前日期的所在月的最后一天
     * @param c
     * @return
     */
    public static String getLastDayOfMonth(Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(c.getTime());
    }

    /**
     * 根据日期字符串获取指定年月的最后一天
     * @param ym		年月字符串
     * @param fmt		字符串格式，如：yyyyMM。SimpleDateFormat格式
     * @return	年月字符串的所在年月的月份最后一天
     */
    public static String getLastDayOfMonth(String ym,String fmt){
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        Date d = null;
        try {
            d = sdf.parse(ym);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = new GregorianCalendar();
        c.setTime(d);

        return getLastDayOfMonth(c);
    }

    /**
     * 比较两个相同格式日期，第一个参数与第二个参数比较，早于是-1，相等是0，晚于是1
     * @param startDate 第一个参数
     * @param endDate	第二个参数
     * @throws ParseException
     */
    public static int compareDate(String startDate,String endDate)  {
        return compareDate(startDate,endDate,"yyyy-MM-dd");
    }

    /**
     * 比较两个相同格式日期，第一个参数与第二个参数比较，早于是-1，相等是0，晚于是1,异常返回-2
     * @param startDate 第一个参数
     * @param endDate	第二个参数
     * @param dateFmt	日期格式
     * @return rst,早于-1，相等0，晚于1，异常返回-2
     * @throws ParseException
     */
    private static int compareDate(String startDate, String endDate, String dateFmt)  {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFmt);
        int rst = 0;
        try{
            Date startD = sdf.parse(startDate);
            Date endD = sdf.parse(endDate);
            if(startD.before(endD)){
                rst = -1;
                return rst;
            }
            if(startD.after(endD)){
                rst = 1;
                return rst;
            }
        }catch (Exception exception){
            return -2;
        }
        return rst;
    }

    /**
     * 月份转中文
     * @param mon
     * @return
     */
    public static String getMonthStr(String mon){
        mon = mon.length()==1?"0"+mon:mon;
        String month = "";
        switch (mon){
            case "01":
                month = "一月";
                break;
            case "02":
                month = "二月";
                break;
            case "03":
                month = "三月";
                break;
            case "04":
                month = "四月";
                break;
            case "05":
                month = "五月";
                break;
            case "06":
                month = "六月";
                break;
            case "07":
                month = "七月";
                break;
            case "08":
                month = "八月";
                break;
            case "09":
                month = "九月";
                break;
            case "10":
                month = "十月";
                break;
            case "11":
                month = "十一月";
                break;
            case "12":
                month = "十二月";
                break;
            default:
                break;
        }
        return month;
    }



    /**
     * 向指定 URL 发送GET方法的请求
     * @param path 发送请求的 URL
     * @return String
     */
    public static String sendGet(String path){
        StringBuffer sb = new StringBuffer();
        String exception = "";
        String result = "";
        try{
            URL url = new URL(path);
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;//读取数据
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
            br.close();
            isr.close();
            is.close();
        }catch(Exception e){
            e.printStackTrace();
            exception = "发送 GET 请求出现异常！"+e;
        }

        if(sb.toString().isEmpty()){
            result = exception;
        }else{
            result = sb.toString();
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param path 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return String
     */
    public static String sendPost(String path, String param) {
        OutputStreamWriter out = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        String exception = "";
        String result = "";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = null;
            // 打开和URL之间的连接
            conn = (HttpURLConnection) url.openConnection();

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法
            conn.setConnectTimeout(15 * 1000);// 设置连接超时时间为5秒
            conn.setReadTimeout(20 * 1000);// 设置读取超时时间为20秒

            // 设置通用的请求属性
//        	conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            // 发送请求参数
            BufferedWriter bw = new BufferedWriter(out);
            bw.write(param);
            // flush输出流的缓冲
            bw.flush();
            // 定义BufferedReader输入流来读取URL的响应
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            exception = "发送 POST 请求出现异常！"+e;
            e.printStackTrace();
        }finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(br!=null){
                    br.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        if(sb.toString().isEmpty()){
            result = exception;
        }else{
            result = sb.toString();
        }
        return result;
    }

    /**
     * 获取项目物理地址
     * @return
     */
    public static String getAbsPathOfProject() {
        String url = MyUtil.class.getClassLoader().getResource("").toString();
        String reg = "file:(.+)WEB-INF";
        Matcher mat = Pattern.compile(reg, Pattern.CASE_INSENSITIVE).matcher(
                url);
        if (mat.find()) {
            String path = mat.group(1);
            path = path.replaceAll("/", "\\" + File.separator);
            if (File.separator.equals("\\"))// windows
                return path.substring(1);
            return path;
        }
        return null;
    }

    /**
     * 正则验证手机号
     * @return boolean
     */
    public static boolean isMobile(String str){
        Pattern p = Pattern.compile("^((13[0-9])|(14[1,4-8])|(15[^4,\\D])|(166)|(17[0,1,3,4,6-8])|(18[0-9])|(19[8,9]))\\d{8}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 正则验证邮箱
     * @return boolean
     */
    public static boolean isEmail(String str){
        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /***
     * 检查字符串中是否有数字
     * @param s
     * @return hasDigit
     */

    public boolean hasNum(String s) {
        boolean hasDigit = false;//定义一个boolean值，用来表示是否包含数字
//        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母

        for(int i=0 ; i<s.length() ; i++){ //循环遍历字符串
            if(Character.isDigit(s.charAt(i))){     //用char包装类中的判断数字的方法判断每一个字符
                hasDigit = true;
                break;
            }
//            if(Character.isLetter(str.charAt(i))){   //用char包装类中的判断字母的方法判断每一个字符
//                isLetter = true;
//            }
        }
        return hasDigit;
    }

    /**
     * 获取CCObject的字符串
     * @param c
     * @param colApi
     * @return
     */
    public static String getStr(CCObject c ,String colApi ){
        String s = c==null?"":c.get(colApi)==null?"":String.valueOf(c.get(colApi));
        return "null".equals(s)?"":s;
    }

    /**
     * 获取CCObject的字符串
     * @param c
     * @param colApi
     * @return
     */
    public static String getStrTrim(CCObject c ,String colApi ){
        return getStr(c,colApi).trim();
    }

    /**
     * 获取Map的字段值
     * @param c
     * @param colApi
     * @return
     */
    public static String getStr(Map c ,String colApi ){
        String s = c==null?"":c.get(colApi)==null?"":String.valueOf(c.get(colApi));
        return "null".equals(s)?"":s;
    }

    /**
     * 获取Json的字段值
     * @param j JSONObject
     * @param colApi String
     * @return
     */
    public static String getStr(JSONObject j  , String colApi ){
        String s = j.containsKey(colApi)?j.getString(colApi):"";
        return "null".equals(s)?"":s.trim();
    }

    /**
     * 获取Json的字段值
     * @param j JSONObject
     * @param colApi String
     * @return
     */
    public static String getStrTrim(JSONObject j  , String colApi ){
        return getStr(j,colApi).trim();
    }

    /**
     * 获取CCObject的double
     * @param s
     * @return
     */
    public static  double getDb(String s){

        if(s==null||"".equals(s.trim())||s.trim().length()==0){
            return 0;
        }else{
            return Double.parseDouble(s.trim());
        }
    }

    /**
     * 转换整数
     * @param s
     * @return
     */
    public static Integer getInt(String s){
        if(s==null||"".equals(s)){
            return 0;
        }else{
            return getBigD(s).intValue();
        }
    }


    /**
     * 转换整数
     * @param s
     * @return
     */
    public static Integer formatInt(String s){
        if(s==null||"".equals(s)){
            return 0;
        }else{
            if(s.indexOf(".")>0){
                s = s.substring(0,s.indexOf("."));
            }
            return Integer.parseInt(s) ;
        }
    }


    /**
     * String日期格式转换Calendar
     * @param calStr
     * @param simDatFmt 格式
     * @return
     */
    public static Calendar getCal(String calStr,String simDatFmt){
        SimpleDateFormat sdf = new SimpleDateFormat(simDatFmt);
        Calendar c = new GregorianCalendar();
        try {
            Date d =  sdf.parse(calStr);
            c.setTime(d);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return c;
    }


    /**
     * 日期格式字符串调整计算
     * @param calStr 原日期串
     * @param amount	调整数量
     * @param type	调整单位
     * @param simDatFmt	日期格式
     * @return
     */
    public static String transCal(String calStr,int amount,int type,String simDatFmt){
        SimpleDateFormat sdf = new SimpleDateFormat(simDatFmt);
        Calendar c = new GregorianCalendar();
        try {
            Date d =  sdf.parse(calStr);
            c.setTime(d);
            c.add(type,amount);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return sdf.format(c.getTime());
    }

    /**
     * 获取JSONObject的两个值的和
     * @param j
     * @param colApi
     * @param je
     * @return
     */
    public static  String sumCol(JSONObject j, String colApi, String je){
        if(j.containsKey(colApi)){
            return (new BigDecimal(j.getString(colApi))).add(new BigDecimal(je)).toString();
        }else{
            return je;
        }
    }

    /**
     * String类型数字转BigDecimal
     * @param s
     * @return
     */
    public static BigDecimal  getBigD(String s){
        return new BigDecimal(s==null||s.equals("null")||s.trim().equals("")?"0":s);
    }

    /**
     * String类型数字转BigDecimal
     * @param j JSONObject
     * @param api String 字段
     * @return
     */
    public static BigDecimal getBigD(JSONObject j , String api){
        String s = j.containsKey(api)?j.getString(api):"";
        return new BigDecimal(s.equals("")||s==null?"0":s);
    }

    /**
     * 数字变号
     */
    public static String antonymy(String amount){
        return multiString(amount,"-1");
    }

    /**
     * 乘法计算
     * @param amountA
     * @param amountB
     * @return
     */
    public static String multiString(String amountA,String amountB){
        return getBigD(amountA).multiply(getBigD(amountB)).setScale(6,BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 乘法计算
     * @param amountA
     * @param amountB
     * @param digits	保留小数位数
     * @return
     */
    public static String multiString(String amountA,String amountB,int digits){
        BigDecimal temp = getBigD(amountA).multiply(getBigD(amountB));
        BigDecimal rst = temp.setScale(digits,BigDecimal.ROUND_HALF_UP);
        return rst.toPlainString();
    }

    /**
     * 乘法计算
     * @param amount
     * @param digits	保留小数位数
     * @return
     */
    public static String multiString(int digits,String ... amount){
        BigDecimal temp = new BigDecimal("1");
        for(String s : amount){
            temp = temp.multiply(new BigDecimal(s));
        }

        BigDecimal rst = temp.setScale(digits,BigDecimal.ROUND_HALF_UP);

        return rst.toPlainString();
    }

    /**
     * 对String类型的数字进行加法计算
     * @param amountA
     * @param amountB
     * @return
     */
    public static String addString(String amountA,String amountB ){
        return getBigD(amountA).add(getBigD(amountB)).toPlainString();
    }

    /**
     * 对String类型的数字进行加法计算
     * @param amountA
     * @param amountB
     * @return
     */
    public static String addString(String amountA,String amountB,int scale ){
        return getBigD(amountA).add(getBigD(amountB)).setScale(scale,BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 对String类型的数字进行加法计算
     * @param amount
     * @param scale		小数位置
     * @return
     */
    public static String addString(int scale ,String ... amount){
        BigDecimal d = new BigDecimal("0.00");
        for(String s : amount){
            d.add(getBigD(s));
        }
        return d.setScale(scale,BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 兩個對象不同記錄相同key值加和
     * @param j		JSON對象
     * @param c		CCObject
     * @param key
     * @return 加和
     */
    public static String addString(JSONObject j,CCObject c,String key){
        return addString(getStr(j,key),getStr(c,key));
    }

    /**
     * 兩個對象不同記錄相同key值加和
     * @param j		JSON對象
     * @param c		CCObject
     * @param key
     * @return 加和
     */
    public static String addString(JSONObject j,CCObject c,String key ,int scale){
        return addString(getStr(j,key),getStr(c,key),scale);
    }

    /**
     * 对String类型的数字进行减法计算
     * @param subtractor	减数
     * @param minuend	被减数
     * @return 减数 - 被减数
     */
    public static String subtractStr(String subtractor,String minuend){
        return getBigD(subtractor).subtract(getBigD(minuend)).toString();
    }

    /**
     * 计算分字、分母的比例
     * @param num 分字
     * @param tark	分母
     * @return 比例
     */
    public static String DoubStrDivPercent(String num,String tark){
        double numDouble = getDb(num);
        double tarkDouble = getDb(tark);
        double pct = tarkDouble==0?0:(numDouble/tarkDouble*100);
        return String.format("%.2f",pct);
    }

    /**
     * 计算分字、分母的比例
     * @param num 分字
     * @param tark	分母
     * @return 比例,返回两位小数
     */
    public static String divStr(String num,String tark){
        return (tark==null||tark.equals("")||(getBigD(tark).compareTo(BigDecimal.ZERO)==0))?"0":getBigD(num).divide(getBigD(tark),6, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 计算分字、分母的比例，默认小数位数
     * @param num 分字
     * @param tark	分母
     * @return 比例
     */
    public static String divStr(String num,String tark,int digits){
        return tark==null||tark.equals("")||tark.equals("")?"0":String.format("%s",getBigD(num).divide(getBigD(tark),digits, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 计算分字、分母的百分比，默认小数位数
     * @param num 分字
     * @param tark	分母
     * @return 百分比
     */
    public static String divStrPercent(String num,String tark){
        return divStrPercent(num,tark,2);
    }

    /**
     * 计算分字、分母的百分比，指定小数位数
     * @param num 分字
     * @param tark	分母
     * @param digits	小数位数
     * @return 百分比
     */
    public static String divStrPercent(String num,String tark,int digits){
        return tark==null||tark.equals("0")||tark.equals("")?"0":String.format("%s",getBigD(num).multiply(getBigD("100")).divide(getBigD(tark),digits, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 比较大小，如果A大于B，则返回1，如果相等，则返回0，如果小于，返回-1
     * @param amountA
     * @param amountB
     * @return
     */
    public static int compareStr(String amountA,String amountB){
        return getBigD(amountA).compareTo(getBigD(amountB));
    }

    /**
     * 比较绝对值大小，如果A大于B，则返回1，如果相等，则返回0，如果小于，返回-1
     * @param amountA
     * @param amountB
     * @return
     */
    public static int compareStrAbs(String amountA,String amountB){
        return getBigD(amountA).abs().compareTo(getBigD(amountB).abs());
    }

    /**
     * 设置数字小数位数
     * @param amount
     * @param scale
     * @return
     */
    public static String setScale(String amount,int scale){
        BigDecimal num = getBigD(amount);
        return num.setScale(0,BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 查询用户对象
     * @param userInfo
     * @param userId
     * @return userObj
     */
    public static CCObject getUser(UserInfo userInfo,String userId){
        CCObject userObj = new CCObject("ccuser");
        CCService cs = new CCService(userInfo);
        List<CCObject> userList = cs.cquery("ccuser","id='"+userId+"'");
        if(userList.size()>0){
            userObj = userList.get(0);
        }

        return userObj;
    }


    /**
     * 查询用户姓名
     * @param userInfo
     * @param userId
     * @return userName
     */
    public static String getUserName(UserInfo userInfo,String userId){
        String userName="";
        CCService cs = new CCService(userInfo);
        List<CCObject> userList = cs.cquery("ccuser","id='"+userId+"'");
        if(userList.size()>0){
            userName = MyUtil.getStr(userList.get(0),"name");
        }
        return userName;
    }

    /**
     * 把JSONObject对象值，赋值给CCObject
     * @param j JSONObject
     * @param c CCObject
     * @param ignoreCapital 忽略大小写
     * @return
     */
    public static  CCObject assignJSON2CCObject(JSONObject j, CCObject c, boolean ignoreCapital){
//		System.out.println(j.toString());
        Iterator iter = j.entrySet().iterator();
//		System.out.println(iter.toString());
        while(iter.hasNext()){
            Map.Entry entry =  (Map.Entry)  iter.next();
            String key = entry.getKey().toString();
            String value = entry.getValue().toString().equals("null")?"":entry.getValue().toString();
            c.put(key.toLowerCase(),value);
        }

        return c;
    }

    /**
     * 左补位
     * @param str 原始字符串
     * @param pad_length 补位后的长度
     * @param pad_char 补位的字符，只有一位
     * @return
     */
    public static String lpad(String str,int pad_length,char pad_char){
        StringBuffer strSB = new StringBuffer();

        //只有填充之后的字符串长度超过原字符长度才需要补位
        for(int i=0;i<(pad_length-str.length());i++){
            strSB.append(pad_char);
        }
        //最后添加原字符串
        strSB.append(str);

        return strSB.toString();
    }

    /**
     * 获取对象的字段值
     * @return String
     */
    public static String getFieldValueStr(CCService cs ,String ObjName,String objId,String fieldName){
        List<CCObject> objList = cs.cquery(ObjName,"id='"+objId+"'");
        if(objList.size()>0){
            return getStr(objList.get(0),fieldName);
        }
        return "";
    }

    /**
     * 审批时间记录
     * @param record_new 新记录
     * @param record_old 旧记录
     */
    public static void spDate(Map<String,String> record_new,Map<String,String> record_old){
        spDate(record_new,record_old,"spzt");
    }

    /**
     * 审批时间记录
     * @param record_new 新记录
     * @param record_old 旧记录
     * @param spztField 审批状态字段
     */
    public static void spDate(Map<String,String> record_new,Map<String,String> record_old,String spztField){
        spDate(record_new,record_old,spztField,"sqfqsj","sptgsj");
    }

    /**
     * 审批时间记录
     * @param record_new 新记录
     * @param record_old 旧记录
     * @param spztField 审批状态字段
     * @param sqfqsjField 申请发起时间字段
     * @param sptgsjField 审批通过时间字段
     */
    public static void spDate(Map<String,String> record_new,Map<String,String> record_old,String spztField,String sqfqsjField,String sptgsjField){
        String old_fjspzt = MyUtil.getStr(record_old,spztField);
        String new_fjspzt = MyUtil.getStr(record_new,spztField);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //提交时更新提交时间
        if("审批中".equals(new_fjspzt)&&!"审批中".equals(old_fjspzt)){
            record_new.put(sqfqsjField,sdf.format(new Date()));
        }
        //审批通过时，记录通过的时间
        if("审批通过".equals(new_fjspzt)&&!"审批通过".equals(old_fjspzt)){
            record_new.put(sptgsjField,sdf.format(new Date()));
        }
    }

    /**
     * 自定义方法去空格、换行、回车、CA20空格
     * @param str
     * @return
     */
    public static String trim(String str){
        String det="";
        byte bytes[] = {(byte) 0xC2,(byte) 0xA0};
        String UTFSpace = null;
        try {
            UTFSpace = new String(bytes,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(str!=null) {
            Pattern p = Pattern.compile("\t|\r|\n|"+UTFSpace);
            Matcher m = p.matcher(str);
            det = m.replaceAll("");
        }
        return det;
    }

    /**
     * CCObject 转 JSONObject
     * @param c
     * @return
     */
    public static JSONObject ccobj2Json(CCObject c ){
        JSONObject j = new JSONObject();
        Set<String> keySet = c.keySet();
        for(String key :keySet){
            j.put(key,getStr(c,key));
        }

        return j;
    }

    /**
     * List<CCObject> 转 JSONArray
     * @param ccObjectList
     * @return
     */
    public static JSONArray ccList2JA(List<CCObject> ccObjectList){
        JSONArray ja = new JSONArray();
        for (CCObject c : ccObjectList) {
            ja.add(ccobj2Json(c));
        }
        return ja;
    }


    /**
     * 数字月份转中文季度
     * @param mon
     * @return
     */
    public String getMonthQuarter(String mon){
        String quarter = "";
        switch (mon){
            case "01":
            case "02":
            case "03":

                quarter = "Q1";
                break;
            case "04":
            case "05":
            case "06":

                quarter = "Q2";
                break;
            case "07":
            case "08":
            case "09":

                quarter = "Q3";
                break;
            case "10":
            case "11":
            case "12":

                quarter = "Q4";
                break;
            default:
                break;
        }
        return quarter;
    }

    public static void main(String[] args) {
//		System.out.println(compareStr("2022-02-16","2022-03-31"));
        String s=" Sss ";
        String ss = s.toLowerCase();
        System.out.println("["+MyUtil.trim(ss)+"]");
    }

}
