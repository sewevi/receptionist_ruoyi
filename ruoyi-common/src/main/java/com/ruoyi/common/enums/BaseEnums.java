package com.ruoyi.common.enums;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/9/24
 */
public enum BaseEnums {
    SYS_SERVER_EXCEPTION(4002, "请求异常，请核对请求报文或联系技术人员"),
    SYS_SERVER_ERROR(4000, "网络不稳定"),
    SYS_SUCCESS(200, "请求成功"),

    ;
    /**
     * 异常编码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;


    BaseEnums( Integer code, String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode( Integer code ) {
        this.code = code;
    }

    public void setMsg( String msg ) {
        this.msg = msg;
    }
}
