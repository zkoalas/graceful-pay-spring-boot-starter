package com.github.zkoalas.pay.enums;

import lombok.Getter;

/**
 * wx支付方式
 */
@Getter
public enum WxPayTypeEnum {


    PAY_MP("JSAPI", "微信公众账号支付"),
    PAY_MWEB("MWEB",  "微信H5支付"),
    PAY_NATIVE("NATIVE",  "微信Native支付"),
    PAY_MINI("JSAPI", "微信小程序支付"),
    PAY_APP("APP", "微信APP支付");

    private String code;

    private String description;

    WxPayTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
