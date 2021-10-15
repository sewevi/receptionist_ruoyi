package com.receptionist.dto;

import com.receptionist.constant.Values;
import com.receptionist.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/5/13
 */
@Slf4j
public abstract class AbstractRestService {
    /**
     * 返回有内容成功结果
     */
    protected <T> ResponseResult<T> buildSuccesssResult(T data ) {
        ResponseResult<T> result = new ResponseResult<T>()
                .setStatus(Values.SUCCESS_RST)
                .setCode(ExceptionCode.SYS_SUCCESS.getCode())
                .setMsg(ExceptionCode.SYS_SUCCESS.getMsg())
                .setContent(data);
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

}
