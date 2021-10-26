package com.ruoyi.receptionist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.receptionist.domain.TOrder;
import com.ruoyi.receptionist.domain.TStore;

import java.util.List;

/**
 * 仓库Service接口
 * 
 * @author ruoyi
 * @date 2021-10-17
 */
public interface ITStoreService extends IService<TStore>
{
    /**
     * 查询仓库
     * 
     * @param id 仓库主键
     * @return 仓库
     */
    public TStore selectTStoreById(Long id);

    /**
     * 查询仓库列表
     * 
     * @param tStore 仓库
     * @return 仓库集合
     */
    public List<TStore> selectTStoreList(TStore tStore);

    /**
     * 新增仓库
     * 
     * @param tStore 仓库
     * @return 结果
     */
    public int insertTStore(TStore tStore);

    /**
     * 修改仓库
     * 
     * @param tStore 仓库
     * @return 结果
     */
    public int updateTStore(TStore tStore);

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的仓库主键集合
     * @return 结果
     */
    public int deleteTStoreByIds(Long[] ids);

    /**
     * 删除仓库信息
     * 
     * @param id 仓库主键
     * @return 结果
     */
    public int deleteTStoreById(Long id);
}
