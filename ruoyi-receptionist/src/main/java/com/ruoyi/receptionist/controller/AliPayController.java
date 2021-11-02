package com.ruoyi.receptionist.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.ijpay.alipay.AliPayApi;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.receptionist.config.AliPayBaseConfig;
import com.ruoyi.receptionist.dto.AbstractRestService;
import com.ruoyi.receptionist.dto.ResponseResult;
import com.ruoyi.receptionist.entity.alipay.AliPayResponse;
import com.ruoyi.receptionist.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/5/13
 */
@RestController
@RequestMapping("/aliPay")
@Api(value = "支付宝相关内部操作接口", tags = "支付宝相关内部操作接口")
public class AliPayController extends AbstractRestService {

    @Autowired
    private AliPayBaseConfig aliPayBean;
    @Autowired
    private AliPayService aliPayService;

    public AliPayApiConfig getApiConfig() throws AlipayApiException {
        AliPayApiConfig aliPayApiConfig;
        try {
            aliPayApiConfig = AliPayApiConfigKit.getApiConfig(aliPayBean.getAppId());
        } catch (Exception e) {
            aliPayApiConfig = AliPayApiConfig.builder()
                    .setAppId(aliPayBean.getAppId())
                    .setAliPayPublicKey(aliPayBean.getPublicKey())
                    .setAppCertPath(aliPayBean.getAppCertPath())
                    .setAliPayCertPath(aliPayBean.getAliPayCertPath())
                    .setAliPayRootCertPath(aliPayBean.getAliPayRootCertPath())
                    .setCharset("UTF-8")
                    .setPrivateKey(aliPayBean.getPrivateKey())
                    .setServiceUrl(aliPayBean.getServerUrl())
                    .setSignType("RSA2")
                    // 证书模式
                    .buildByCert();

        }
        return aliPayApiConfig;
    }

    /**
     *@Description 查询账户剩余余额
     *@Author xdayi@linewell.com
     *@Date 2021/4/29
     *@Version 0.1
     *@param aliPayUserId 2088621955689180
     */
    @GetMapping(value = "/accountQuery")
    @ApiOperation(value = "查询账户剩余余额")
    public ResponseResult<AliPayResponse> accountQuery(@RequestParam("aliPayUserId") String aliPayUserId) {
        try {
            return this.buildSuccesssResult(aliPayService.accountQuery(aliPayUserId));
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResult("error");
        }
    }

    /**
     *@Description 扫码支付
     *@Author xdayi@linewell.com
     *@Date 2021/4/29
     *@Version 0.1
     */
    @GetMapping(value = "/tradePreCreatePay")
    @ApiOperation(value = "扫码支付")
    public ResponseResult<String> tradePreCreatePay(@RequestParam(name = "paymentValue") String paymentValue
    ,@RequestParam("outTradeNo") String outTradeNo){
        try {
            return this.buildSuccesssResult(aliPayService.tradePreCreatePay(paymentValue,outTradeNo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.buildErrorResult("error");
    }

    /**
     * 交易查询
     */
    @GetMapping(value = "/tradeQuery")
    @ApiOperation(value = "交易查询")
    @ResponseBody
    public String tradeQuery(@RequestParam(required = false, name = "outTradeNo") String outTradeNo, @RequestParam(required = false, name = "tradeNo") String tradeNo) {
        try {
            AlipayTradeQueryModel model = new AlipayTradeQueryModel();
            if (StringUtils.isNotEmpty(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StringUtils.isNotEmpty(tradeNo)) {
                model.setTradeNo(tradeNo);
            }
            return AliPayApi.tradeQueryToResponse(model).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 单笔转账到支付宝账户
     * https://docs.open.alipay.com/309/106235/
     */
    @PostMapping(value = "/paymentValue")
    @ApiOperation(value = "单笔转账到支付宝账户")
    public ResponseResult<String> transfer(@RequestParam(name = "paymentValue") String paymentValue) {
        try {
            return this.buildSuccesssResult(aliPayService.transfer(paymentValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.buildErrorResult("error");
    }
}