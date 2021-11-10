package com.ruoyi.receptionist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.receptionist.domain.TStoreAllocation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author xdayi@linewell.com
 * @Description
 * @date 2021/11/10
 */
@Data
public class UserOrderSaveParams {
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("地址")
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//输出格式
    @ApiModelProperty("预约时间")
    private Date reserveTime;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("调拨")
    List<TStoreAllocation> tStoreAllocations;
}
