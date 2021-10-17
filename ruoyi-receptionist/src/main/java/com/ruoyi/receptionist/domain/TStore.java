package com.ruoyi.receptionist.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 仓库对象 t_store
 * 
 * @author ruoyi
 * @date 2021-10-17
 */
@Data
@TableName("t_store")
public class TStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
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

}
