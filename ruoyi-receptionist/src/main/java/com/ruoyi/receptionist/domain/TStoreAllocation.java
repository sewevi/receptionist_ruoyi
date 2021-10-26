package com.ruoyi.receptionist.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库调拨记录对象 t_store_allocation
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
@Data
public class TStoreAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单主键 */
    @Excel(name = "订单主键")
    private Long orderId;

    /** 仓库物品主键 */
    @Excel(name = "仓库物品主键")
    private Long storeId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String storeName;

    /** 商品购买个数 */
    @Excel(name = "商品购买个数")
    private String storeCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setStoreCount(String storeCount) 
    {
        this.storeCount = storeCount;
    }

    public String getStoreCount() 
    {
        return storeCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("storeId", getStoreId())
            .append("storeName", getStoreName())
            .append("storeCount", getStoreCount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
