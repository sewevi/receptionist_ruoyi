package com.ruoyi.receptionist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.receptionist.domain.TOrder;
import com.ruoyi.receptionist.domain.TStoreAllocation;

import java.util.List;

/**
 * 仓库调拨记录Service接口
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
public interface ITStoreAllocationService extends IService<TStoreAllocation>
{
    /**
     * 查询仓库调拨记录
     * 
     * @param id 仓库调拨记录主键
     * @return 仓库调拨记录
     */
    public TStoreAllocation selectTStoreAllocationById(Long id);

    /**
     * 查询仓库调拨记录列表
     * 
     * @param tStoreAllocation 仓库调拨记录
     * @return 仓库调拨记录集合
     */
    public List<TStoreAllocation> selectTStoreAllocationList(TStoreAllocation tStoreAllocation);

    /**
     * 新增仓库调拨记录
     * 
     * @param tStoreAllocation 仓库调拨记录
     * @return 结果
     */
    public int insertTStoreAllocation(TStoreAllocation tStoreAllocation);

    /**
     * 修改仓库调拨记录
     * 
     * @param tStoreAllocation 仓库调拨记录
     * @return 结果
     */
    public int updateTStoreAllocation(TStoreAllocation tStoreAllocation);

    /**
     * 批量删除仓库调拨记录
     * 
     * @param ids 需要删除的仓库调拨记录主键集合
     * @return 结果
     */
    public int deleteTStoreAllocationByIds(Long[] ids);

    /**
     * 删除仓库调拨记录信息
     * 
     * @param id 仓库调拨记录主键
     * @return 结果
     */
    public int deleteTStoreAllocationById(Long id);
}
