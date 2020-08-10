package com.github.zkoalas.pay.wx;

import com.github.zkoalas.pay.WxPayProperties;
import com.github.zkoalas.pay.constants.WxLets;
import com.github.zkoalas.pay.utils.MapUtil;
import com.github.zkoalas.pay.utils.MoneyUtil;
import com.github.zkoalas.pay.utils.RandomUtil;
import com.github.zkoalas.pay.utils.XmlUtil;
import com.github.zkoalas.pay.wx.model.WxCloseOrder;
import com.github.zkoalas.pay.wx.model.WxPayAsyncResponse;
import com.github.zkoalas.pay.wx.model.WxUnifiedOrder;
import com.github.zkoalas.pay.wx.param.WxCloseOrderParam;
import com.github.zkoalas.pay.wx.param.WxUnifiedOrderParam;
import com.github.zkoalas.pay.wx.request.WxCloseOrderRequest;
import com.github.zkoalas.pay.wx.request.WxUnifiedOrderRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.ObjectUtils;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
public class WxPayHandlerImpl implements WxPayHandler {

    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    WxPayProperties wxPayProperties;

    public void setWxPayProperties(WxPayProperties wxPayProperties) {
        this.wxPayProperties = wxPayProperties;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(WxLets.WX_GATEWAY)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(new OkHttpClient.Builder()
                    .addInterceptor((new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)))
                    .build()
            )
            .build();


    public WxUnifiedOrder wxUnifiedOrder(WxUnifiedOrderParam param) throws Exception {
        if(ObjectUtils.isEmpty(param)){
            throw new RuntimeException("【微信统一支付】参选不能为空");
        }
        WxUnifiedOrderRequest request = new WxUnifiedOrderRequest();
        request.setTradeType(param.getWxPayTypeEnum().getCode());
        request.setOutTradeNo(param.getOrderId());
        request.setBody(param.getOrderName());
        request.setTotalFee(MoneyUtil.Yuan2Fen(param.getOrderAmount()));
        request.setAppId(wxPayProperties.getAppId());
        request.setMchId(wxPayProperties.getMchId());
        request.setNotifyUrl(wxPayProperties.getNotifyUrl());
        request.setNonceStr(RandomUtil.getRandomStr());
        request.setSpBillCreateIp("8.8.8.8");
        request.setAttach(param.getAttach());
        request.setTimeExpire(param.getTimeExpire().format(formatter));
        request.setSign(WxPaySignature.sign(MapUtil.buildMap(request), wxPayProperties.getMchKey()));
        RequestBody body = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), XmlUtil.toString(request));
        Call<WxUnifiedOrder> call = retrofit.create(WxPayApi.class).unifiedOrder(body);
        Response<WxUnifiedOrder> response = call.execute();
        if(!response.isSuccessful()){
            throw new RuntimeException("【微信统一支付】统一支付, 网络异常");
        }
        return response.body();
    }


    public WxCloseOrder wxCloseOrder(WxCloseOrderParam param) throws Exception {
        Objects.requireNonNull(param,"【微信关闭订单】参选不能为空");
        WxCloseOrderRequest request = new WxCloseOrderRequest();
        request.setAppId(wxPayProperties.getAppId());
        request.setMchId(wxPayProperties.getMchId());
        request.setNonceStr(RandomUtil.getRandomStr());
        request.setOutTradeNo(param.getOrderId());
        request.setSign(WxPaySignature.sign(MapUtil.buildMap(request), wxPayProperties.getMchKey()));
        RequestBody body = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), XmlUtil.toString(request));
        Call<WxCloseOrder> call = retrofit.create(WxPayApi.class).closeOrder(body);
        Response<WxCloseOrder> response = call.execute();
        if(!response.isSuccessful()){
            throw new RuntimeException("【微信关闭订单 网络异常");
        }
        return response.body();
    }



    public WxPayAsyncResponse asyncNotify(String notifyData) {
        //签名校验
        if (!WxPaySignature.verify(XmlUtil.toMap(notifyData), wxPayProperties.getMchKey())) {
            log.error("【微信支付异步通知】签名验证失败, response={}", notifyData);
            throw new RuntimeException("【微信支付异步通知】签名验证失败");
        }
        //xml解析为对象
        WxPayAsyncResponse asyncResponse = XmlUtil.toObject(notifyData, WxPayAsyncResponse.class);
        if(!asyncResponse.getReturnCode().equals(WxLets.SUCCESS)) {
            throw new RuntimeException("【微信支付异步通知】发起支付, returnCode != SUCCESS, returnMsg = " + asyncResponse.getReturnMsg());
        }
        //该订单已支付直接返回
        if (!asyncResponse.getResultCode().equals(WxLets.SUCCESS)
                && asyncResponse.getErrCode().equals("ORDERPAID")) {
            return asyncResponse;
        }
        if (!asyncResponse.getResultCode().equals(WxLets.SUCCESS)) {
            throw new RuntimeException("【微信支付异步通知】发起支付, resultCode != SUCCESS, err_code = " + asyncResponse.getErrCode() + " err_code_des=" + asyncResponse.getErrCodeDes());
        }
        return asyncResponse;
    }



}
