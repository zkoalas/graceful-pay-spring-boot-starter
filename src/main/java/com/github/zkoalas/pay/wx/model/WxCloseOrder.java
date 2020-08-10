package com.github.zkoalas.pay.wx.model;

import lombok.Data;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@ToString
@Root(name = "xml", strict = false)
public class WxCloseOrder {

    /**
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
     */
    @Element(name = "return_code")
    private String returnCode;


    /**
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
     * 调用接口提交的公众账号ID
     */
    @Element(name = "appid")
    private String appId;

    /**
     * 调用接口提交的商户号
     */
    @Element(name = "mch_id")
    private String mchId;

    /**
     * 微信返回的随机字符串
     */
    @Element(name = "nonce_str")
    private String nonceStr;

    /**
     * 微信返回的签名值，详见签名算法
     */
    @Element(name = "sign")
    private String sign;

    /**
     * SUCCESS/FAIL
     */
    @Element(name = "result_code")
    private String resultCode;

    /**
     * 对业务结果的补充说明
     */
    @Element(name = "result_msg")
    private String resultMsg;

    /**
     * 当result_code为FAIL时返回错误代码，详细参见下文错误列表
     */
    @Element(name = "err_code", required = false)
    private String errCode;

    /**
     * 当result_code为FAIL时返回错误描述，详细参见下文错误列表
     */
    @Element(name = "err_code_des", required = false)
    private String errCodeDes;
}
