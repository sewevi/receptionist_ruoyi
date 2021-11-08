package com.ruoyi.receptionist.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.receptionist.domain.TStore;
import com.ruoyi.receptionist.service.ITStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@Api(value = "仓库内部操作接口", tags = "仓库相关内部操作接口")
public class TStoreController extends BaseController
{
    @Autowired
    private ITStoreService tStoreService;

    /**
     * 查询仓库列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询仓库列表")
    public TableDataInfo list(TStore tStore)
    {
        startPage();
        List<TStore> list = tStoreService.selectTStoreList(tStore);
        return getDataTable(list);
    }

    /**
     * 导出仓库列表
     */
    @Log(title = "仓库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation(value = "导出仓库列表")
    public AjaxResult export(TStore tStore)
    {
        List<TStore> list = tStoreService.selectTStoreList(tStore);
        ExcelUtil<TStore> util = new ExcelUtil<TStore>(TStore.class);
        return util.exportExcel(list, "仓库数据");
    }

    /**
     * 获取仓库详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取仓库详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tStoreService.selectTStoreById(id));
    }

    /**
     * 新增仓库测试上传
     */
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "新增仓库")
    public AjaxResult add(@RequestBody TStore tStore)
    {
        return toAjax(tStoreService.insertTStore(tStore));
    }

    /**
     * 修改仓库
     */
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value = "修改仓库")
    public AjaxResult edit(@RequestBody TStore tStore)
    {
        return toAjax(tStoreService.updateTStore(tStore));
    }

    /**
     * 删除仓库
     */
    @Log(title = "仓库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除仓库")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tStoreService.deleteTStoreByIds(ids));
    }
}
