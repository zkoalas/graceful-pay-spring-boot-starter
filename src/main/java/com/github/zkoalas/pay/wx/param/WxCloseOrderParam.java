package com.github.zkoalas.pay.wx.param;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WxCloseOrderParam {

    /**
     * 订单号.
     */
    private String orderId;
}
