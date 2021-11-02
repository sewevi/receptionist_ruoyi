package com.ruoyi.receptionist.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.receptionist.domain.TOrder;
import com.ruoyi.receptionist.domain.TStoreAllocation;
import com.ruoyi.receptionist.mapper.TOrderMapper;
import com.ruoyi.receptionist.mapper.TStoreAllocationMapper;
import com.ruoyi.receptionist.service.AliPayService;
import com.ruoyi.receptionist.service.ITOrderService;
import com.ruoyi.receptionist.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService
{
    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private AliPayService aliPayService;
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public TOrder selectTOrderById(Long id)
    {
        return tOrderMapper.selectTOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param tOrder 订单
     * @return 订单
     */
    @Override
    public List<TOrder> selectTOrderList(TOrder tOrder)
    {
        return tOrderMapper.selectTOrderList(tOrder);
    }

    /**
     * 新增订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    @Override
    public int insertTOrder(TOrder tOrder)
    {
        tOrder.setCreateTime(DateUtils.getNowDate());
        // 生成订单编码
        String outTradeNo = StringUtils.getOutTradeNo();
        tOrder.setOutTradeNo(outTradeNo);
        return tOrderMapper.insertTOrder(tOrder);
    }

    /**
     * 修改订单
     * 
     * @param tOrder 订单
     * @return 结果
     */
    @Override
    public int updateTOrder(TOrder tOrder)
    {
        tOrder.setUpdateTime(DateUtils.getNowDate());
        return tOrderMapper.updateTOrder(tOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteTOrderByIds(Long[] ids)
    {
        return tOrderMapper.deleteTOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteTOrderById(Long id)
    {
        return tOrderMapper.deleteTOrderById(id);
    }
}
