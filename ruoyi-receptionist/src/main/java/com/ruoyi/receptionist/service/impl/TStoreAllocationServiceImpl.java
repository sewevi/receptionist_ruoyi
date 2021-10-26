package com.ruoyi.receptionist.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.receptionist.domain.TStoreAllocation;
import com.ruoyi.receptionist.mapper.TStoreAllocationMapper;
import com.ruoyi.receptionist.service.ITStoreAllocationService;
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
