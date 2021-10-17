package com.ruoyi.receptionist.interceptor;

import com.alipay.api.AlipayApiException;
import com.ijpay.alipay.AliPayApiConfigKit;
import com.ruoyi.receptionist.controller.AliPayController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/5/13
 */

public class AliPayInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws AlipayApiException {
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
//            if (!(controller instanceof AbstractAliPayApiController)) {
//                throw new RuntimeException("控制器需要继承 AbstractAliPayApiController");
//            }
            AliPayApiConfigKit.setThreadLocalAliPayApiConfig(((AliPayController) controller).getApiConfig());
            return true;
        }
        return false;
    }
}
