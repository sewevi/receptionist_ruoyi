package com.ruoyi.receptionist.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/4/29
 */
@Data
@Component
public class AliPayBaseConfig {
    @Value("${ijpay.alipay.appId}")
    private String appId;

    @Value("${ijpay.alipay.privateKey}")
    private String privateKey;

    @Value("${ijpay.alipay.publicKey}")
    private String publicKey;

    @Value("${ijpay.alipay.appCertPath}")
    private String appCertPath;

    @Value("${ijpay.alipay.aliPayCertPath}")
    private String aliPayCertPath;

    @Value("${ijpay.alipay.aliPayRootCertPath}")
    private String aliPayRootCertPath;

    @Value("${ijpay.alipay.serverUrl}")
    private String serverUrl;

    @Value("${ijpay.alipay.domain}")
    private String domain;
}
