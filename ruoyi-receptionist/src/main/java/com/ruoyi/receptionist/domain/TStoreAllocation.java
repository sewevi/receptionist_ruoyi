package com.ruoyi.receptionist.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id", type = IdType.AUTO)
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
    private Long storeCount;

}
