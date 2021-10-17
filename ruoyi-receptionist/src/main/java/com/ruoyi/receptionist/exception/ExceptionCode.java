package com.ruoyi.receptionist.exception;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/5/13
 */
public enum ExceptionCode {

    /**
     * 请求成功
     */
    SYS_SUCCESS(200, "请求成功");

    /**
     * 异常编码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    ExceptionCode(Integer code, String msg ) {
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
