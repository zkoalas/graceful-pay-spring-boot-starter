package com.github.zkoalas.pay.wx.request;

import lombok.Data;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@ToString
@Root(name = "xml", strict = false)
public class WxUnifiedOrderRequest {

    /**
     * 必填
     * 微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    @Element(name = "appid")
    private String appId;

    /**
     * 必填
     * 微信支付分配的商户号
     */
    @Element(name = "mch_id")
    private String mchId;

    /**
     * 非必填
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @Element(name = "device_info",required=false)
    private String deviceInfo;

    /**
     * 必填
     * 随机字符串，长度要求在32位以内。推荐随机数生成算法
     */
    @Element(name = "nonce_str")
    private String nonceStr;

    /**
     * 必填
     * 通过签名算法计算得出的签名值，详见签名生成算法
     */
    @Element(name = "sign")
    private String sign;


    /**
     * 非必填
     * 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     */
    @Element(name = "sign_type",required=false)
    private String signType;

    /**
     * 必填
     * 商品简单描述，该字段请按照规范传递，具体请见参数规定
     */
    @Element(name = "body",required = false)
    private String body;

    /**
     * 非必填
     * 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传，详见“单品优惠参数说明”
     */
    @Element(name = "detail", required = false)
    private String detail;

    /**
     * 非必填
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    @Element(name = "attach", required = false)
    private String attach;

    /**
     * 必填
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
     */
    @Element(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 非必填
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     */
    @Element(name = "fee_type",required=false)
    private String feeType;

    /**
     * 必填
     * 订单总金额，单位为分，详见支付金额
     */
    @Element(name = "total_fee")
    private Integer totalFee;

    /**
     * 必填
     * 支持IPV4和IPV6两种格式的IP地址。用户的客户端IP
     */
    @Element(name = "spbill_create_ip")
    private String spBillCreateIp;

    /**
     * 非必填
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    @Element(name = "time_start",required=false)
    private String timeStart;

    /**
     * 非必填
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     * time_expire只能第一次下单传值，不允许二次修改，二次修改系统将报错。如用户支付失败后，需再次支付，需更换原订单号重新下单。
     * 建议：最短失效时间间隔大于1分钟
     */
    @Element(name = "time_expire",required=false)
    private String timeExpire;

    /**
     * 非必填
     * 订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     */
    @Element(name = "goods_tag",required=false)
    private String goodsTag;

    /**
     * 必填
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @Element(name = "notify_url")
    private String notifyUrl;

    /**
     * 必填
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     * 说明详见参数规定
     */
    @Element(name = "trade_type")
    private String tradeType;

    /**
     * 非必填
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    @Element(name = "product_id",required=false)
    private String productId;

    /**
     * 非必填
     * 上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    @Element(name = "limit_pay",required=false)
    private String limitPay;


    /**
     * 非必填
     * trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     */
    @Element(name = "openid", required=false)
    private String openId;


    /**
     * 非必填
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    @Element(name = "receipt", required=false)
    private String receipt;

    /**
     * 非必填
     * 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开
     */
    @Element(name = "scene_info", required=false)
    private String sceneInfo;

}
