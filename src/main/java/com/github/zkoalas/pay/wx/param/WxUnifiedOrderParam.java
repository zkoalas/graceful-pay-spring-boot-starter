package com.github.zkoalas.pay.wx.param;


import com.github.zkoalas.pay.enums.WxPayTypeEnum;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
public class WxUnifiedOrderParam {

    /**
     * 支付方式.
     */
    private WxPayTypeEnum wxPayTypeEnum;

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
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    private String attach;



    private LocalDateTime timeExpire;



}
