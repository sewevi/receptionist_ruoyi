package com.receptionist.entity.alipay;

import lombok.Data;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/4/29
 */
@Data
public class AliPayResponse {
    private String code;
    private String msg;
    private String available_amount;
}
