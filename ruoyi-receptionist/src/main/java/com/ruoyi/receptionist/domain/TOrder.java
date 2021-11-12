package com.ruoyi.receptionist.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单对象 t_order
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
@Data
public class TOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String outTradeNo;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 收纳时长(小时单位) */
    @Excel(name = "收纳时长(小时单位)")
    private BigDecimal orderTime;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reserveTime;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String img;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 订单状态(0:未开始 1:开始中 2:已完成) */
    @Excel(name = "订单状态(0:未开始 1:开始中 2:已完成)")
    private String status;

    /** 订单类型(0:指定收纳师 1:抢单模式) */
    @Excel(name = "订单类型(0:指定收纳师 1:抢单模式)")
    private String type;

    /** 收纳师主键 */
    @Excel(name = "收纳师主键")
    private Long receptionId;

    /** 收纳师名称 */
    @Excel(name = "收纳师名称")
    private String receptionName;

    /** 付款状态(0:未付款 1:已付款) */
    @Excel(name = "付款状态(0:未付款 1:已付款)")
    private String payStatus;

    /** 收纳师联系方式 */
    @Excel(name = "收纳师联系方式")
    private String receptionTel;

    /** 评论 */
    @Excel(name = "评论")
    private String comment;

    private List<TStoreAllocation> allocationList;

    /** 用户主键 */
    @Excel(name = "用户主键")
    private Long userId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userNick;

    /** 用户联系方式 */
    @Excel(name = "用户联系方式")
    private String userTel;

}
