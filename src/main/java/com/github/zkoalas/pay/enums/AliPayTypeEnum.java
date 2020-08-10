package com.github.zkoalas.pay.enums;

import lombok.Getter;


/**
 * ali支付方式
 */
@Getter
public enum AliPayTypeEnum {

    PAY_APP("alipay_app", "支付宝app"),
    PAY_PC("alipay_pc", "支付宝pc"),
    PAY_WAP("alipay_wap", "支付宝wap"),
    PAY_H5("alipay_h5", "支付宝统一下单(h5)");

    private String code;


    private String desc;

    AliPayTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
