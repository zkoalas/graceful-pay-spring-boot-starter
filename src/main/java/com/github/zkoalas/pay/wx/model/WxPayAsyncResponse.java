package com.github.zkoalas.pay.wx.model;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 异步返回参数
 * Created by 廖师兄
 * 2017-07-02 20:55
 */
@Data
@Root(name = "xml", strict = false)
public class WxPayAsyncResponse {

    /**
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @Element(name = "return_code")
    private String returnCode;

    /**
     * 返回信息，如非空，为错误原因
     * 签名失败
     * 参数格式校验错误
     */
    @Element(name = "return_msg", required = false)
    private String returnMsg;

    /** 以下字段在return_code为SUCCESS的时候有返回. */

    /**
     *  微信分配的小程序ID
     */
    @Element(name = "appid", required = false)
    private String appId;

    /**
     * 微信支付分配的商户号
     */
    @Element(name = "mch_id", required = false)
    private String mchId;

    /**
     * 微信支付分配的终端设备号
     */
    @Element(name = "device_info", required = false)
    private String deviceInfo;

    /**
     * 随机字符串，不长于32位
     */
    @Element(name = "nonce_str", required = false)
    private String nonceStr;

    /**
     * 签名，详见签名算法
     */
    @Element(name = "sign", required = false)
    private String sign;


    /**
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    @Element(name = "sign_type", required = false)
    private String signType;

    /**
     * SUCCESS/FAIL
     */
    @Element(name = "result_code", required = false)
    private String resultCode;

    /**
     * 错误返回的信息描述
     */
    @Element(name = "err_code", required = false)
    private String errCode;

    /**
     * 错误返回的信息描述
     */
    @Element(name = "err_code_des", required = false)
    private String errCodeDes;

    /**
     * 用户在商户appid下的唯一标识
     */
    @Element(name = "openid", required = false)
    private String openId;

    /**
     * 用户是否关注公众账号，Y-关注，N-未关注
     */
    @Element(name = "is_subscribe", required = false)
    private String isSubscribe;

    /**
     * JSAPI、NATIVE、APP
     */
    @Element(name = "trade_type", required = false)
    private String tradeType;

    /**
     * 银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    @Element(name = "bank_type", required = false)
    private String bankType;

    /**
     * 订单总金额，单位为分
     */
    @Element(name = "total_fee", required = false)
    private Integer totalFee;


    /**
     * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    @Element(name = "settlement_total_fee", required = false)
    private String settlementTotalFee;

    /**
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @Element(name = "fee_type", required = false)
    private String feeType;

    /**
     * 现金支付金额订单现金支付金额，详见支付金额
     */
    @Element(name = "cash_fee", required = false)
    private String cashFee;

    /**
     * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @Element(name = "cash_fee_type", required = false)
    private String cashFeeType;

    /**
     * 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
     */
    @Element(name = "coupon_fee", required = false)
    private String couponFee;

    /**
     * 代金券使用数量
     */
    @Element(name = "coupon_count", required = false)
    private String couponCount;

    /**
     * 代金券类型
     */
    @Element(name = "coupon_type_0", required = false)
    private String couponType0;

    /**
     * 代金券ID
     */
    @Element(name = "coupon_id_0", required = false)
    private String couponId0;

    /**
     * 微信支付订单号
     */
    @Element(name = "transaction_id", required = false)
    private String transactionId;


    /**
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @Element(name = "out_trade_no", required = false)
    private String outTradeNo;

    /**
     * 商家数据包，原样返回
     */
    @Element(name = "attach", required = false)
    private String attach;

    /**
     * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    @Element(name = "time_end", required = false)
    private String timeEnd;

}