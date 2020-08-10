package com.github.zkoalas.pay.wx.model;

import lombok.Data;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 同步返回参数
 */
@Data
@ToString
@Root(name = "xml", strict = false)
public class WxUnifiedOrder {

    /**
     * 必填
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @Element(name = "return_code")
    private String returnCode;


    /**
     * 必填
     * 当return_code为FAIL时返回信息为错误原因 ，例如
     * 签名失败
     * 参数格式校验错误
     */
    @Element(name = "return_msg")
    private String returnMsg;


    /**
     * -----------------------------
     * 以下字段在return_code为SUCCESS的时候有返回
     */


    /**
     * 必填
     * 调用接口提交的公众账号ID
     */
    @Element(name = "appid", required = false)
    private String appId;

    /**
     * 必填
     * 调用接口提交的商户号
     */
    @Element(name = "mch_id", required = false)
    private String mchId;

    /**
     * 非必填
     * 自定义参数，可以为请求支付的终端设备号等
     */
    @Element(name = "device_info", required = false)
    private String deviceInfo;

    /**
     * 必填
     * 微信返回的随机字符串
     */
    @Element(name = "nonce_str", required = false)
    private String nonceStr;

    /**
     * 必填
     * 微信返回的签名值，详见签名算法
     */
    @Element(name = "sign", required = false)
    private String sign;

    /**
     * 必填
     * SUCCESS/FAIL
     */
    @Element(name = "result_code", required = false)
    private String resultCode;

    /**
     * 非必填
     * 当result_code为FAIL时返回错误代码，详细参见下文错误列表
     */
    @Element(name = "err_code", required = false)
    private String errCode;

    /**
     * 非必填
     * 当result_code为FAIL时返回错误描述，详细参见下文错误列表
     */
    @Element(name = "err_code_des", required = false)
    private String errCodeDes;


    /**
     * -----------------------------
     * 以下字段在return_code 和result_code都为SUCCESS的时候有返回
     */

    /**
     * 必填
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     * 说明详见参数规定
     */
    @Element(name = "trade_type", required = false)
    private String tradeType;

    /**
     * 必填
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @Element(name = "prepay_id", required = false)
    private String prepayId;

    /**
     * 非必填
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
     */
    @Element(name = "code_url", required = false)
    private String codeUrl;

}
