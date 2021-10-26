package com.ruoyi.receptionist.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.receptionist.domain.TStoreAllocation;

/**
 * 仓库调拨记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
public interface TStoreAllocationMapper extends BaseMapper<TStoreAllocation>
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
     * 删除仓库调拨记录
     * 
     * @param id 仓库调拨记录主键
     * @return 结果
     */
    public int deleteTStoreAllocationById(Long id);

    /**
     * 批量删除仓库调拨记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTStoreAllocationByIds(Long[] ids);
}
