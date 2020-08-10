package com.github.zkoalas.pay.wx;

import com.github.zkoalas.pay.wx.model.WxCloseOrder;
import com.github.zkoalas.pay.wx.model.WxPayAsyncResponse;
import com.github.zkoalas.pay.wx.model.WxUnifiedOrder;
import com.github.zkoalas.pay.wx.param.WxCloseOrderParam;
import com.github.zkoalas.pay.wx.param.WxUnifiedOrderParam;

public interface WxPayHandler {

    /**
     * 微信统一下单
     * @param param
     * @return
     * @throws Exception
     */
    WxUnifiedOrder wxUnifiedOrder(WxUnifiedOrderParam param) throws Exception;

    /**
     * 微信关闭订单
     * @param param
     * @return
     * @throws Exception
     */
    WxCloseOrder wxCloseOrder(WxCloseOrderParam param) throws Exception;


    /**
     * 微信回调通知校验签名
     * @param notifyData
     * @return
     */
    WxPayAsyncResponse asyncNotify(String notifyData);
}
