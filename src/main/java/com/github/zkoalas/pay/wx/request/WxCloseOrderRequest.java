package com.github.zkoalas.pay.wx.request;

import lombok.Data;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@ToString
@Root(name = "xml", strict = false)
public class WxCloseOrderRequest {

    /**
     * 必填
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    @Element(name = "appid")
    private String appId;

    /**
     * 必填
     * 微信支付分配的商户号
     */
    @Element(name = "mch_id")
    private String mchId;

    /**
     * 必填
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @Element(name = "out_trade_no")
    private String outTradeNo;


    /**
     * 必填
     * 随机字符串，长度要求在32位以内。推荐随机数生成算法
     */
    @Element(name = "nonce_str")
    private String nonceStr;

    /**
     * 必填
     * 通过签名算法计算得出的签名值，详见签名生成算法
     */
    @Element(name = "sign")
    private String sign;


    /**
     * 非必填
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     */
    @Element(name = "sign_type",required=false)
    private String signType;
}
