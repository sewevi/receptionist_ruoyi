package com.ruoyi.receptionist.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.receptionist.domain.TStore;
import com.ruoyi.receptionist.domain.TStoreAllocation;
import com.ruoyi.receptionist.exception.ExceptionCode;
import com.ruoyi.receptionist.mapper.TStoreAllocationMapper;
import com.ruoyi.receptionist.service.ITStoreAllocationService;
import com.ruoyi.receptionist.service.ITStoreService;
import com.ruoyi.receptionist.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 仓库调拨记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
@Service
public class TStoreAllocationServiceImpl extends ServiceImpl<TStoreAllocationMapper,TStoreAllocation> implements ITStoreAllocationService
{
    @Autowired
    private TStoreAllocationMapper tStoreAllocationMapper;

    @Autowired
    private ITStoreService storeService;

    /**
     * 查询仓库调拨记录
     * 
     * @param id 仓库调拨记录主键
     * @return 仓库调拨记录
     */
    @Override
    public TStoreAllocation selectTStoreAllocationById(Long id)
    {
        return tStoreAllocationMapper.selectTStoreAllocationById(id);
    }

    /**
     * 查询仓库调拨记录列表
     * 
     * @param tStoreAllocation 仓库调拨记录
     * @return 仓库调拨记录
     */
    @Override
    public List<TStoreAllocation> selectTStoreAllocationList(TStoreAllocation tStoreAllocation)
    {
        return tStoreAllocationMapper.selectTStoreAllocationList(tStoreAllocation);
    }

    /**
     * 新增仓库调拨记录
     * 
     * @param tStoreAllocation 仓库调拨记录
     * @return 结果
     */
    @Override
    public int insertTStoreAllocation(TStoreAllocation tStoreAllocation)
    {
        tStoreAllocation.setCreateTime(DateUtils.getNowDate());
        return tStoreAllocationMapper.insertTStoreAllocation(tStoreAllocation);
    }

    @Override
    public int saveBatchTStoreAllocation(List<TStoreAllocation> tStoreAllocation) {
        if (ListUtils.isNotEmpty(tStoreAllocation)) {
            tStoreAllocation.stream().forEach(o -> {
                TStore store = storeService.selectTStoreById(o.getStoreId());
                // 没有库存报错
                if (store!=null) {
                    if (store.getCount() - store.getReceive() <= 0) {
                        throw new ServiceException("没有库存");
                    }
                }
                store.setReceive(store.getReceive()+o.getStoreCount());
                storeService.updateTStore(store);
                insertTStoreAllocation(o);
            });
        }
        return 0;
    }

    /**
     * 修改仓库调拨记录
     * 
     * @param tStoreAllocation 仓库调拨记录
     * @return 结果
     */
    @Override
    public int updateTStoreAllocation(TStoreAllocation tStoreAllocation)
    {
        tStoreAllocation.setUpdateTime(DateUtils.getNowDate());
        return tStoreAllocationMapper.updateTStoreAllocation(tStoreAllocation);
    }

    /**
     * 批量删除仓库调拨记录
     * 
     * @param ids 需要删除的仓库调拨记录主键
     * @return 结果
     */
    @Override
    public int deleteTStoreAllocationByIds(Long[] ids)
    {
        return tStoreAllocationMapper.deleteTStoreAllocationByIds(ids);
    }

    /**
     * 删除仓库调拨记录信息
     * 
     * @param id 仓库调拨记录主键
     * @return 结果
     */
    @Override
    public int deleteTStoreAllocationById(Long id)
    {
        return tStoreAllocationMapper.deleteTStoreAllocationById(id);
    }
}
