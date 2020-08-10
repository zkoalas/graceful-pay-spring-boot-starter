package com.github.zkoalas.pay;



import com.github.zkoalas.pay.ali.AliPayHandler;
import com.github.zkoalas.pay.ali.AliPayHandlerImpl;
import com.github.zkoalas.pay.wx.WxPayHandler;
import com.github.zkoalas.pay.wx.WxPayHandlerImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AliPayProperties.class, WxPayProperties.class})
public class PayConfiguration {


    /**
     * 微信配置
     * @param wxPayProperties
     * @return
     */
    @Bean
    public WxPayHandler wxPayHandler(WxPayProperties wxPayProperties) {
        WxPayHandlerImpl wxPayHandler = new WxPayHandlerImpl();
        wxPayHandler.setWxPayProperties(wxPayProperties);
        return wxPayHandler;
    }

    @Bean
    public AliPayHandler aliPayHandler(AliPayProperties aliPayProperties) {
        AliPayHandlerImpl aliPayHandler = new AliPayHandlerImpl();
        aliPayHandler.setAliPayProperties(aliPayProperties);
        return aliPayHandler;
    }
}
