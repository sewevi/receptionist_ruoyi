package com.ruoyi.receptionist.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回结果
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = {"handler"})
@ApiModel("返回响应信息")
public class ResponseResult<T> {

    /**
     * 请求状态
     */
    @ApiModelProperty(value = "请求状态")
    private Integer status;

    /**
     * 返回状态码
     */
    @ApiModelProperty(value = "返回状态码")
    private Integer code;

    /**
     * 返回消息
     */
    @ApiModelProperty(value = "返回消息")
    private String msg;

    /**
     * 返回内容
     */
    @ApiModelProperty(value = "返回内容")
    private T content;

}
