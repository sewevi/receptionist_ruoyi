package com.ruoyi.receptionist.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 仓库对象 t_store
 * 
 * @author ruoyi
 * @date 2021-10-17
 */
@Data
public class TStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "仓库主键", cellType = ColumnType.NUMERIC, prompt = "仓库主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String name;

    /** 总数 */
    @Excel(name = "总数")
    private Long count;

    /** 领用数量 */
    @Excel(name = "领用数量")
    private Long receive;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imgUrl;
}
