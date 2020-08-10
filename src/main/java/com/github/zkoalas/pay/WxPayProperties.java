package com.github.zkoalas.pay;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wx-pay")
@Data
@ToString
public class WxPayProperties {

    /**
     * app应用appId
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 支付完成后的异步通知地址.
     */
    private String notifyUrl;

    /**
     * 支付完成后的同步返回地址.
     * 用于支付完成后跳转到想跳转的页面
     */
    private String returnUrl;

}
