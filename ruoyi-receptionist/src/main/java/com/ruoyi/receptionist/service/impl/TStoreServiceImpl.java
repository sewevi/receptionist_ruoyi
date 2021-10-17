package com.ruoyi.receptionist.service.impl;

import com.ruoyi.receptionist.domain.TStore;
import com.ruoyi.receptionist.mapper.TStoreMapper;
import com.ruoyi.receptionist.service.ITStoreService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 仓库Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-10-17
 */
@Service
public class TStoreServiceImpl implements ITStoreService
{
    @Autowired
    private TStoreMapper tStoreMapper;

    /**
     * 查询仓库
     * 
     * @param id 仓库主键
     * @return 仓库
     */
    @Override
    public TStore selectTStoreById(Long id)
    {
        return tStoreMapper.selectTStoreById(id);
    }

    /**
     * 查询仓库列表
     * 
     * @param tStore 仓库
     * @return 仓库
     */
    @Override
    public List<TStore> selectTStoreList(TStore tStore)
    {
        return tStoreMapper.selectTStoreList(tStore);
    }

    /**
     * 新增仓库
     * 
     * @param tStore 仓库
     * @return 结果
     */
    @Override
    public int insertTStore(TStore tStore)
    {
        tStore.setCreateTime(DateUtils.getNowDate());
        return tStoreMapper.insertTStore(tStore);
    }

    /**
     * 修改仓库
     * 
     * @param tStore 仓库
     * @return 结果
     */
    @Override
    public int updateTStore(TStore tStore)
    {
        tStore.setUpdateTime(DateUtils.getNowDate());
        return tStoreMapper.updateTStore(tStore);
    }

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的仓库主键
     * @return 结果
     */
    @Override
    public int deleteTStoreByIds(Long[] ids)
    {
        return tStoreMapper.deleteTStoreByIds(ids);
    }

    /**
     * 删除仓库信息
     * 
     * @param id 仓库主键
     * @return 结果
     */
    @Override
    public int deleteTStoreById(Long id)
    {
        return tStoreMapper.deleteTStoreById(id);
    }
}
