package com.github.zkoalas.pay.ali.param;

import com.github.zkoalas.pay.enums.AliPayTypeEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AliOrderParam {

    /**
     * 支付方式.
     */
    private AliPayTypeEnum aliPayTypeEnum;
    /**
     * 订单号.
     */
    private String orderId;

    /**
     * 订单金额.
     */
    private Double orderAmount;

    /**
     * 订单名字.
     */
    private String orderName;


    /**
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。
     */
    private String passBackParams;


    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m
     */
    private String timeoutExpress;
}
