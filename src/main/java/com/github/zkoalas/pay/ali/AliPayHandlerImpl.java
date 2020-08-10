package com.github.zkoalas.pay.ali;

import com.alibaba.fastjson.JSON;
import com.github.zkoalas.pay.AliPayProperties;
import com.github.zkoalas.pay.ali.model.AliPayAsyncResponse;
import com.github.zkoalas.pay.ali.param.AliOrderParam;
import com.github.zkoalas.pay.ali.request.AliUnifiedOrderRequest;
import com.github.zkoalas.pay.ali.model.BizContent;
import com.github.zkoalas.pay.constants.AliLets;
import com.github.zkoalas.pay.enums.AliPayTypeEnum;
import com.github.zkoalas.pay.utils.JsonUtil;
import com.github.zkoalas.pay.utils.MapUtil;
import com.github.zkoalas.pay.utils.WebUtil;
import com.google.gson.JsonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AliPayHandlerImpl implements AliPayHandler {

    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    AliPayProperties aliPayProperties;

    public void setAliPayProperties(AliPayProperties aliPayProperties) {
        this.aliPayProperties = aliPayProperties;
    }

    public String aliUnifiedOrder(AliOrderParam param){
        BizContent bizContent = new BizContent();
        AliUnifiedOrderRequest orderRequest = new AliUnifiedOrderRequest();
        try {
            bizContent.setPassbackParams(URLEncoder.encode(param.getPassBackParams(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        bizContent.setOutTradeNo(param.getOrderId());
        bizContent.setTotalAmount(String.valueOf(param.getOrderAmount()));
        bizContent.setSubject(String.valueOf(param.getOrderName()));
        bizContent.setTimeoutExpress(param.getTimeoutExpress());
        if (param.getAliPayTypeEnum() == AliPayTypeEnum.PAY_PC) {
            bizContent.setProductCode(AliLets.FAST_INSTANT_TRADE_PAY);
            orderRequest.setMethod(AliLets.ALI_TRADE_PAGE_PAY);
        }
        Map<String, String> bizContentMap = MapUtil.object2MapWithUnderline(bizContent);

        orderRequest.setAppId(aliPayProperties.getAppId());
        orderRequest.setCharset(AliLets.CHARSET_UTF8);
        orderRequest.setSignType(AliLets.SIGN_TYPE_RSA2);
        orderRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());
        orderRequest.setReturnUrl(aliPayProperties.getReturnUrl());
        orderRequest.setTimestamp(LocalDateTime.now().format(formatter));
        orderRequest.setVersion(AliLets.VERSION);
        orderRequest.setBizContent(JsonUtil.toJson(bizContentMap).replaceAll("\\s*", ""));
        // 剔除空格、制表符、换行
        orderRequest.setSign(AliPaySignature.sign(MapUtil.object2MapWithUnderline(orderRequest), aliPayProperties.getPrivateKey()));

        //对象转map,将字段转换为下划线形式
        Map<String, String> parameters = MapUtil.object2MapWithUnderline(orderRequest);
        parameters.remove("biz_content");
        Map<String, String> applicationParams = new HashMap();
        applicationParams.put("biz_content", orderRequest.getBizContent());
        String baseUrl = WebUtil.getRequestUrl(parameters);
        String body = WebUtil.buildForm(baseUrl, applicationParams);
        // pc 网站支付 只需返回body
        return body;
    }



    public AliPayAsyncResponse asyncNotify(String notifyData) {
        try {
            notifyData = URLDecoder.decode(notifyData,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //签名校验
        if (!AliPaySignature.verify(MapUtil.form2Map(notifyData), aliPayProperties.getPublicKey())) {
            log.error("【支付宝支付异步通知】签名验证失败, response={}", notifyData);
            throw new RuntimeException("【支付宝支付异步通知】签名验证失败");
        }
        HashMap<String, String> params = MapUtil.form2MapWithCamelCase(notifyData);
        AliPayAsyncResponse response = MapUtil.mapToObject(params, AliPayAsyncResponse.class);
        String tradeStatus = response.getTradeStatus();
        if(!tradeStatus.equals(AliLets.TRADE_FINISHED) &&
                !tradeStatus.equals(AliLets.TRADE_SUCCESS)) {
            throw new RuntimeException("【支付宝支付异步通知】发起支付, trade_status != SUCCESS | FINISHED");
        }
        return response;
    }




}
