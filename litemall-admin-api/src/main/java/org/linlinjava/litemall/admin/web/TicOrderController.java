package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.request.TicOrderRequest;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/ticorder")
@Validated
public class TicOrderController {
    @Autowired
    private TicOrderService ticOrderService;

    @RequiresPermissions("admin:ticorder:list")
    @RequiresPermissionsDesc(menu={"景区管理" , "订单信息"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicOrderRequest orderRequest){
    	List<TicOrder> orderList = ticOrderService.list(orderRequest);
    	long total = PageInfo.of(orderList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", orderList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:ticorder:create")
    @RequiresPermissionsDesc(menu={"景区管理" , "订单信息"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicOrder ticOrder){
    	int i = ticOrderService.create(ticOrder);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticorder:update")
    @RequiresPermissionsDesc(menu={"景区管理" , "订单信息"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicOrder ticOrder){
    	int i = ticOrderService.update(ticOrder);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticorder:delete")
    @RequiresPermissionsDesc(menu={"景区管理" , "订单信息"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicOrder ticOrder){
    	int i = ticOrderService.delete(ticOrder.getOrdId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
    
    @RequiresPermissions("admin:ticorder:queryTic")
    @PostMapping("/queryTic")
    public Object queryTicTotal(@RequestBody TicOrderRequest orderRequest){

    	
        return ResponseUtil.ok();
    }
    
}
