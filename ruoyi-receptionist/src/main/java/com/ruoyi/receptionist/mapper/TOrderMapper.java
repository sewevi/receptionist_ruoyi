package com.ruoyi.receptionist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.receptionist.domain.TOrder;

import java.util.List;

/**
 * 订单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
public interface TOrderMapper extends BaseMapper<TOrder>
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    public TOrder selectTOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param tOrder 订单
     * @return 订单集合
     */
    public List<TOrder> selectTOrderList(TOrder tOrder);

    /**
     * 新增订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    public int insertTOrder(TOrder tOrder);

    /**
     * 修改订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    public int updateTOrder(TOrder tOrder);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    public int deleteTOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTOrderByIds(Long[] ids);
}
