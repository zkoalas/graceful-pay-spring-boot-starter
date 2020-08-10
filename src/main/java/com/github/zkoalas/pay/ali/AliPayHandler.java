package com.github.zkoalas.pay.ali;


import com.github.zkoalas.pay.ali.model.AliPayAsyncResponse;
import com.github.zkoalas.pay.ali.param.AliOrderParam;

public interface AliPayHandler {

    /**
     * 统一下单接口
     * @param orderParam
     * @return
     */
    String aliUnifiedOrder(AliOrderParam orderParam);

    /**
     * 通知回调签名验证
     * @param notifyData
     * @return
     */
    AliPayAsyncResponse asyncNotify(String notifyData);
}
