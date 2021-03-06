package com.ruoyi.receptionist.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayFundAccountQueryModel;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.ijpay.alipay.AliPayApi;
import com.ruoyi.receptionist.config.AliPayBaseConfig;
import com.ruoyi.receptionist.entity.alipay.AliPayDefaultResponse;
import com.ruoyi.receptionist.entity.alipay.AliPayResponse;
import com.ruoyi.receptionist.service.AliPayService;
import com.ruoyi.receptionist.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/5/14
 */
@Service
public class AliPayServiceImpl implements AliPayService {
    @Autowired
    private AliPayBaseConfig aliPayBean;

    /**
     * 查询账户剩余余额
     * @param aliPayUserId aliPay用户主键
     * @return
     */
    @Override
    public AliPayResponse accountQuery(String aliPayUserId) {
        AlipayFundAccountQueryModel model = new AlipayFundAccountQueryModel();
        model.setAlipayUserId(aliPayUserId);
        model.setAccountType("ACCTRANS_ACCOUNT");
        try {
            String response = AliPayApi.accountQueryToResponse(model, null).getBody();
            AliPayDefaultResponse aliPayDefaultResponse = JSON.parseObject(response,AliPayDefaultResponse.class);
            return aliPayDefaultResponse.getAlipay_fund_account_query_response();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 扫码支付
     * @param paymentValue
     * @return
     */
    @Override
    public String tradePreCreatePay(String paymentValue,String outTradeNo) {
        String subject = "Javen 支付宝扫码支付测试";
        String totalAmount = paymentValue;
        String storeId = "2088621955689180";
        String notifyUrl = aliPayBean.getDomain() + "/aliPay/cert_notify_url";

        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setStoreId(storeId);
        model.setTimeoutExpress("5m");
        model.setOutTradeNo(outTradeNo);
        try {
            String resultStr = AliPayApi.tradePrecreatePayToResponse(model, notifyUrl).getBody();
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            return jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 单笔转账到支付宝账户
     * @param paymentValue
     * @return
     */
    @Override
    public String transfer(String paymentValue) {
        String totalAmount = paymentValue;
        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
        model.setOutBizNo(StringUtils.getOutTradeNo());
        model.setPayeeType("ALIPAY_LOGONID");
        model.setPayeeAccount("eaxqbo1716@sandbox.com");
        model.setAmount(totalAmount);
        model.setRemark("javen测试单笔转账到支付宝");
        try {
            return AliPayApi.transferToResponse(model).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二维码生成账单必须扫过后才会生成
     * 订单查询 成功为TRADE_SUCCESS
     * 未付款为 WAIT_BUYER_PAY
     * 不存在为null
     * @param outTradeNo
     * @return
     */
    @Override
    public String tradeQuery(String outTradeNo) {
        try {
            AlipayTradeQueryModel model = new AlipayTradeQueryModel();
            if (com.ruoyi.common.utils.StringUtils.isNotEmpty(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            String resultStr = AliPayApi.tradeQueryToResponse(model).getBody();
            JSONObject jsonObject = JSONObject.parseObject(resultStr);
            if (StringUtils.isNotBlank(jsonObject.getJSONObject("alipay_trade_query_response").getString("trade_status"))){
                return jsonObject.getJSONObject("alipay_trade_query_response").getString("trade_status");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
