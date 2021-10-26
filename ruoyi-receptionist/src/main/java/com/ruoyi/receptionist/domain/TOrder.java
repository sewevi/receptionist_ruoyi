package com.ruoyi.receptionist.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

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
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String img;

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

    /** 收纳师联系方式 */
    @Excel(name = "收纳师联系方式")
    private String receptionTel;

    /** 评论 */
    @Excel(name = "评论")
    private String comment;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setReceptionId(Long receptionId) 
    {
        this.receptionId = receptionId;
    }

    public Long getReceptionId() 
    {
        return receptionId;
    }
    public void setReceptionName(String receptionName) 
    {
        this.receptionName = receptionName;
    }

    public String getReceptionName() 
    {
        return receptionName;
    }
    public void setReceptionTel(String receptionTel) 
    {
        this.receptionTel = receptionTel;
    }

    public String getReceptionTel() 
    {
        return receptionTel;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("address", getAddress())
            .append("img", getImg())
            .append("status", getStatus())
            .append("type", getType())
            .append("receptionId", getReceptionId())
            .append("receptionName", getReceptionName())
            .append("receptionTel", getReceptionTel())
            .append("comment", getComment())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
