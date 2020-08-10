package com.github.zkoalas.pay.ali.model;


import lombok.Data;
import lombok.ToString;

/**
 * 必填
 * 请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
 */
@Data
@ToString
public class BizContent {

    /**
     * 必填
     * 商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
     */
    private String outTradeNo;

    /**
     * 必填
     * 销售产品码，与支付宝签约的产品码名称。
     * 注：目前仅支持FAST_INSTANT_TRADE_PAY
     */
    private String productCode;

    /**
     * 必填
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
     */
    private String totalAmount;

    /**
     * 必填
     * 订单标题
     */
    private String subject;

    /**
     * 可选
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m
     */
    private String timeoutExpress;

    /**
     * 可选
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。
     * 支付宝会在异步通知时将该参数原样返回。
     * 本参数必须进行UrlEncode之后才可以发送给支付宝。
     */
    private String passbackParams;

}
