package com.ruoyi.common.vo.result;


import com.ruoyi.common.enums.BaseEnums;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 微服务 方法返回结果抽象类
 *
 * @author dzhiqin@linewell.com
 * @since 2018-12-17
 */
@Slf4j
public abstract class AbstractRestService {
    //响应成功状态
    public final static Integer SUCCESS_RST = 0;
    //响应失败状态
    public final static Integer FAILED_RST = 1;

    /**
     * 返回统一熔断结果
     */
    protected  ResponseResult buildHystrixResult( StackTraceElement[] stackTraceElements ) {
        String className = stackTraceElements[ 1 ].getClassName();
        String methodName = stackTraceElements[ 1 ].getMethodName();
        StringBuilder allMeg = new StringBuilder();
        log.error("操作失败，方法熔断");
        log.error("调用类：" + className);
        log.error("调用方法：" + methodName);
        ResponseResult result = new ResponseResult()
                .setStatus(FAILED_RST)
                .setCode(BaseEnums.SYS_SERVER_ERROR.getCode())
                .setMsg(BaseEnums.SYS_SERVER_ERROR.getMsg());
        return result;
    }

    /**
     * 返回异常结果
     *
     * @param e 异常
     */
    protected  ResponseResult buildExceptionResult( Exception e ) {
        log.error("操作异常，请检查程序代码！", e);
        ResponseResult<String> result = new ResponseResult()
                .setStatus(FAILED_RST)
                .setCode(BaseEnums.SYS_SERVER_EXCEPTION.getCode())
                .setMsg(BaseEnums.SYS_SERVER_EXCEPTION.getMsg());
        return result;
    }

    /**
     * 返回异常结果
     *
     * @param e 异常
     */
    protected <T> ResponseResult buildExceptionResult( Throwable e ) {
        log.error("操作异常，请检查程序代码！", e);
        ResponseResult<T> result = new ResponseResult<T>()
                .setStatus(FAILED_RST)
                .setCode(BaseEnums.SYS_SERVER_EXCEPTION.getCode())
                .setMsg(BaseEnums.SYS_SERVER_EXCEPTION.getMsg());
        return result;
    }


    /**
     * 返回错误结果
     *
     * @param BaseEnums 错误枚举
     */
    protected <T> ResponseResult buildErrorResult( BaseEnums BaseEnums ) {
        log.error("操作失败，" + BaseEnums.getMsg());
        ResponseResult<T> result = new ResponseResult<T>()
                .setStatus(FAILED_RST)
                .setCode(BaseEnums.getCode())
                .setMsg(BaseEnums.getMsg());
        return result;
    }

    /**
     * 返回错误信息对象
     *
     * @param msg 错误的信息
     * @author: wjingying
     * @date: 2020/4/10
     */
    protected <T> ResponseResult<T> buildErrorResult( String msg ) {
        log.error("操作失败，" + msg);
        ResponseResult<T> result = new ResponseResult<T>()
                .setCode(4000)
                .setStatus(0)
                .setMsg(msg);
        return result;
    }

    /**
     * 返回错误信息对象
     *
     * @param msg 错误的信息
     * @author: Dqiqun
     * @date: 2020/4/10
     */
    protected <T> ResponseResult<T> buildErrorResult( Integer code, String msg ) {
        log.error("操作失败，" + msg);
        ResponseResult<T> result = new ResponseResult<T>()
                .setStatus(FAILED_RST)
                .setCode(code)
                .setMsg(msg);
        return result;
    }

    /**
     * 返回无内容成功结果
     */
    protected <T> ResponseResult buildSuccesssResult() {
        ResponseResult result = new ResponseResult()
                .setStatus(SUCCESS_RST)
                .setCode(BaseEnums.SYS_SUCCESS.getCode())
                .setMsg(BaseEnums.SYS_SUCCESS.getMsg());
        return result;
    }

    /**
     * 返回有内容成功结果
     */
    protected <T> ResponseResult<T> buildSuccesssResult( T data ) {
        ResponseResult<T> result = new ResponseResult<T>()
                .setStatus(SUCCESS_RST)
                .setCode(BaseEnums.SYS_SUCCESS.getCode())
                .setMsg(BaseEnums.SYS_SUCCESS.getMsg())
                .setContent(data);
        return result;
    }

    /**
     * 返回有内容成功结果
     */
    protected <T> ResponseResult<List<T>> buildSuccesssResult( List<T> data ) {
        ResponseResult<List<T>> result = new ResponseResult<List<T>>()
                .setStatus(SUCCESS_RST)
                .setCode(BaseEnums.SYS_SUCCESS.getCode())
                .setMsg(BaseEnums.SYS_SUCCESS.getMsg())
                .setContent(data);
        return result;
    }

    /**
     * 返回有内容成功结果
     */
    protected ResponseResult<String> buildSuccesssResult( BaseEnums e ) {
        ResponseResult<String> result = new ResponseResult<String>()
                .setStatus(SUCCESS_RST)
                .setCode(BaseEnums.SYS_SUCCESS.getCode())
                .setMsg(BaseEnums.SYS_SUCCESS.getMsg())
                .setContent(e.getMsg());
        return result;
    }

}
