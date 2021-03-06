package com.ruoyi.receptionist.controller;

import java.util.List;

import com.ruoyi.receptionist.domain.TOrder;
import com.ruoyi.receptionist.dto.UserOrderSaveParams;
import com.ruoyi.receptionist.service.ITOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2021-10-26
 */
@RestController
@RequestMapping("/api/order")
@Api(value = "订单内部操作接口", tags = "订单相关内部操作接口")
public class TOrderController extends BaseController
{
    @Autowired
    private ITOrderService tOrderService;

    /**
     * 查询订单列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询订单列表")
    public TableDataInfo list(TOrder tOrder)
    {
        startPage();
        List<TOrder> list = tOrderService.selectTOrderList(tOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TOrder tOrder)
    {
        List<TOrder> list = tOrderService.selectTOrderList(tOrder);
        ExcelUtil<TOrder> util = new ExcelUtil<TOrder>(TOrder.class);
        return util.exportExcel(list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tOrderService.selectTOrderById(id));
    }

    /**
     * 新增订单
     */
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TOrder tOrder)
    {
        return toAjax(tOrderService.insertTOrder(tOrder));
    }

    /**
     * 用户新增订单
     */

    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping(value = "saveUserOrder")
    @ApiOperation(value = "用户新增订单")
    public AjaxResult saveUserOrder(@RequestBody UserOrderSaveParams tOrder)
    {
        return toAjax(tOrderService.saveUserOrder(tOrder));
    }

    /**
     * 修改订单
     */
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TOrder tOrder)
    {
        return toAjax(tOrderService.updateTOrder(tOrder));
    }

    /**
     * 删除订单
     */
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tOrderService.deleteTOrderByIds(ids));
    }

    /**
     * 修改订单状态判断订单是否付款
     */
    @GetMapping(value = "judgeOrderPay")
    @ApiOperation(value = "修改订单状态判断订单是否付款")
    public AjaxResult judgeOrderPay(){
        return toAjax(tOrderService.judgeOrderPay());
    }
}
