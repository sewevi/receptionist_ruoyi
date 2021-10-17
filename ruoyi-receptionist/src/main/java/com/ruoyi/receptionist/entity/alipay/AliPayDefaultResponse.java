package com.ruoyi.receptionist.entity.alipay;

import lombok.Data;

/**
 * @author xdayi@linewell.com
 * @Description 支付宝请求常规出参
 * @date 2021/4/29
 */
@Data
public class AliPayDefaultResponse {

    private AliPayResponse alipay_fund_account_query_response;
    private String alipay_cert_sn;
    private String sign;

}
