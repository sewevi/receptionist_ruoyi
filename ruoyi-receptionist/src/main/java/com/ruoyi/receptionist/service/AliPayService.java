package com.ruoyi.receptionist.service;

import com.ruoyi.receptionist.entity.alipay.AliPayResponse;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/5/14
 */
public interface AliPayService {
    /**
     * 查询账户剩余余额
     * @param aliPayUserId aliPay用户主键
     * @return
     */
    AliPayResponse accountQuery(String aliPayUserId);

    /**
     * 扫码支付
     * @param paymentValue
     * @return
     */
    String tradePreCreatePay(String paymentValue);

    /**
     * 单笔转账到支付宝账户
     * @param paymentValue
     * @return
     */
    String transfer(String paymentValue);
}
