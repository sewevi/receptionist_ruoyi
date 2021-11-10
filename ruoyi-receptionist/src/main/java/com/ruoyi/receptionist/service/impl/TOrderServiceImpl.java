package com.ruoyi.receptionist.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.receptionist.domain.TOrder;
import com.ruoyi.receptionist.domain.TStoreAllocation;
import com.ruoyi.receptionist.dto.UserOrderSaveParams;
import com.ruoyi.receptionist.mapper.TOrderMapper;
import com.ruoyi.receptionist.mapper.TStoreAllocationMapper;
import com.ruoyi.receptionist.service.AliPayService;
import com.ruoyi.receptionist.service.ITOrderService;
import com.ruoyi.receptionist.service.ITStoreAllocationService;
import com.ruoyi.receptionist.util.ListUtils;
import com.ruoyi.receptionist.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
@Service
public class TOrderServiceImpl implements ITOrderService
{
    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private ITStoreAllocationService allocationService;

    public static final String PAY_SUCCESS = "TRADE_SUCCESS";
    public static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(15);
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
        judgeOrderPay();
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
        // 默认未付款
        tOrder.setPayStatus("0");
        // 默认订单状态未开始
        tOrder.setStatus("0");
        return tOrderMapper.insertTOrder(tOrder);
    }

    @Override
    @Transactional
    public int saveUserOrder(UserOrderSaveParams params) {
        TOrder order = new TOrder();
        BeanUtils.copyProperties(params,order);
        insertTOrder(order);
        if (ListUtils.isNotEmpty(params.getTStoreAllocations())){
            params.getTStoreAllocations().stream().forEach(o->{
                o.setOrderId(order.getId());
            });
        }
        allocationService.saveBatchTStoreAllocation(params.getTStoreAllocations());
        return 1;
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
        if (tOrder.getOrderTime() != null){
            tOrder.setAmount(tOrder.getOrderTime().multiply(DEFAULT_AMOUNT).setScale(
                    2,BigDecimal.ROUND_HALF_UP));
        }

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

    @Override
    public int judgeOrderPay() {
        TOrder tOrder = new TOrder();
        tOrder.setStatus("2");
        List<TOrder> ordersList = tOrderMapper.selectTOrderList(tOrder);
        if (ListUtils.isNotEmpty(ordersList)){
            ordersList.stream().forEach(o->{
                String payStatus = aliPayService.tradeQuery(o.getOutTradeNo());
                if (PAY_SUCCESS.equals(payStatus)){
                    o.setPayStatus("1");
                    tOrderMapper.updateTOrder(o);
                }
            });
        }
        return 0;
    }
}
