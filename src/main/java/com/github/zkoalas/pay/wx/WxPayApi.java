package com.github.zkoalas.pay.wx;

import com.github.zkoalas.pay.wx.model.WxCloseOrder;
import com.github.zkoalas.pay.wx.model.WxUnifiedOrder;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WxPayApi {

    /**
     * 统一下单
     * @param body
     * @return
     */
    @POST("/pay/unifiedorder")
    Call<WxUnifiedOrder> unifiedOrder(@Body RequestBody body);

    /**
     * 关闭订单
     * @param body
     * @return
     */
    @POST("/pay/closeorder")
    Call<WxCloseOrder> closeOrder(@Body RequestBody body);
}
