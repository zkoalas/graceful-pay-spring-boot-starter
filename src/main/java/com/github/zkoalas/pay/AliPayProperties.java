package com.github.zkoalas.pay;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ali-pay")
@Data
@ToString
public class AliPayProperties {

    /**
     * appId
     */
    private String appId;

    /**
     * 商户私钥
     */
    private String privateKey;

    /**
     * 支付宝公钥
     */
    private String publicKey;

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
