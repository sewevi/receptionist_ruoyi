package com.ruoyi.receptionist.controller;

import java.util.List;

import com.ruoyi.receptionist.domain.TStoreAllocation;
import com.ruoyi.receptionist.service.ITStoreAllocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 仓库调拨记录Controller
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
@RestController
@RequestMapping("/api/allocation")
public class TStoreAllocationController extends BaseController
{
    @Autowired
    private ITStoreAllocationService tStoreAllocationService;

    /**
     * 查询仓库调拨记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TStoreAllocation tStoreAllocation)
    {
        startPage();
        List<TStoreAllocation> list = tStoreAllocationService.selectTStoreAllocationList(tStoreAllocation);
        return getDataTable(list);
    }

    /**
     * 导出仓库调拨记录列表
     */
    @Log(title = "仓库调拨记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TStoreAllocation tStoreAllocation)
    {
        List<TStoreAllocation> list = tStoreAllocationService.selectTStoreAllocationList(tStoreAllocation);
        ExcelUtil<TStoreAllocation> util = new ExcelUtil<TStoreAllocation>(TStoreAllocation.class);
        return util.exportExcel(list, "仓库调拨记录数据");
    }

    /**
     * 获取仓库调拨记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tStoreAllocationService.selectTStoreAllocationById(id));
    }

    /**
     * 新增仓库调拨记录
     */
    @Log(title = "仓库调拨记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TStoreAllocation tStoreAllocation)
    {
        return toAjax(tStoreAllocationService.insertTStoreAllocation(tStoreAllocation));
    }

    /**
     * 修改仓库调拨记录
     */
    @Log(title = "仓库调拨记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TStoreAllocation tStoreAllocation)
    {
        return toAjax(tStoreAllocationService.updateTStoreAllocation(tStoreAllocation));
    }

    /**
     * 删除仓库调拨记录
     */
    @Log(title = "仓库调拨记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tStoreAllocationService.deleteTStoreAllocationByIds(ids));
    }
}
