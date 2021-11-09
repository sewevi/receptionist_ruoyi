package com.ruoyi.receptionist.timer;

import com.ruoyi.receptionist.domain.TOrder;
import com.ruoyi.receptionist.service.ITOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xdayi@linewell.com
 * @Description 订单付款定时器
 * @date 2021/11/9
 */

@EnableScheduling
@Component
@Slf4j
public class OrderTimer {

    @Autowired
    private ITOrderService itOrderService;

//    @Scheduled(cron = "0/5 * * * * ? ") // 每天5秒刷新订单是否付款
    public void judgeOrderPayTimer(){
        itOrderService.judgeOrderPay();
    }
}
