package com.github.zkoalas.pay.ali.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by this on 2019/9/13 2:36
 */
@Data
public class AliPayAsyncResponse implements Serializable {


    /**
     * 通知时间
     */
    private String notifyTime;

    /**
     * 	通知的类型
     */
    private String notifyType;

    /**
     * 通知校验ID
     */
    private String notifyId;

    /**
     * 支付宝分配给开发者的应用Id
     */
    private String appId;

    /**
     * 编码格式，如utf-8、gbk、gb2312等
     */
    private String charset;

    /**
     * 接口版本
     */
    private String version;

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String signType;

    /**
     * 请参考文末 异步返回结果的验签
     */
    private String sign;

    /**
     * 支付宝交易凭证号
     */
    private  String tradeNo;

    /**
     * 原支付请求的商户订单号
     */
    private String outTradeNo;

    /**
     * 商户业务ID，主要是退款通知中返回退款申请的流水号
     */
    private String outBizNo;

    /**
     * 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字
     */
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    private String buyerLogonId;

    /**
     * 卖家支付宝用户号
     */
    private String seller_id;

    /**
     * 卖家支付宝账号
     */
    private String sellerEmail;

    /**
     * 交易目前所处的状态，见下张表 交易状态说明
     */
    private String tradeStatus;

    /**
     * 本次交易支付的订单金额，单位为人民币（元）
     */
    private String totalAmount;

    /**
     * 商家在交易中实际收到的款项，单位为元
     */
    private String receiptAmount;


    /**
     * 用户在交易中支付的可开发票的金额
     */
    private Double invoiceAmount;

    /**
     * 用户在交易中支付的金额
     */
    private Double buyerPayAmount;

    /**
     * 使用集分宝支付的金额
     */
    private Double pointAmount;

    /**
     * 退款通知中，返回总退款金额，单位为元，支持两位小数
     */
    private  Double refundFee;

    /**
     * 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来
     */
    private String subject;

    /**
     * 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来
     */
    private String body;

    /**
     * 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private Date gmtCreate;

    /**
     * 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private Date gmtPayment;

    /**
     * 该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S
     */
    private Date gmtRefund;

    /**
     * 该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss
     */
    private Date gmtClose;

    /**
     * 支付成功的各个渠道金额信息，详见下表 资金明细信息说明
     */
    private String fundBillList;


    /**
     * 公共回传参数，如果请求时传递了该参数，
     * 则返回给商户时会在异步通知时将该参数原样返回。
     * 本参数必须进行UrlEncode之后才可以发送给支付宝
     */
    private String passbackParams;

    /**
     * 本交易支付时所使用的所有优惠券信息，详见下表 优惠券信息说明
     */
    private String voucherDetailList;
}
