package com.github.zkoalas.pay.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class NotifyUtil {

    /**
     * 支付宝支付转换回调参数到对象的工具类
     * @param notifyData
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T convertAliNotifyData(String notifyData,Class<T> clazz){
        try {
            if(StringUtils.isNotBlank(notifyData)){
                notifyData = URLDecoder.decode(notifyData, "UTF-8");
                HashMap<String, String> params = MapUtil.form2MapWithCamelCase(notifyData);
                T obj = MapUtil.mapToObject(params, clazz);
                return obj;
            }
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return null;
    }

    /**
     * 微信支付转换回调参数到对象的工具类
     * @param notifyData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertWxNotifyData(String notifyData,Class<T> clazz){
        if(StringUtils.isNotBlank(notifyData)){
            T obj = XmlUtil.toObject(notifyData, clazz);
            return obj;
        }
        return null;
    }

    /**
     * 支付宝支付回调通知成功的返回结果
     * @return
     */
    public static String  successNotifyAli(){
        return "success";
    }

    /**
     * 微信支付回调通知成功的返回结果
     * @return
     */
    public  static String successNotifyWx(){
        return "<xml>"+
                "<return_code><![CDATA[SUCCESS]]></return_code>" +
                "<return_msg><![CDATA[OK]]></return_msg>" +
                "</xml>";
    }
}
