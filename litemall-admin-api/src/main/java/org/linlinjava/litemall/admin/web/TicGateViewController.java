package org.linlinjava.litemall.admin.web;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicGateView;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.request.TicGateViewRequest;
import org.linlinjava.litemall.db.request.TicGoodRequest;
import org.linlinjava.litemall.db.response.TicGoodResponse;
import org.linlinjava.litemall.db.service.TicGateViewService;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/ticGateView")
@Validated
public class TicGateViewController {
	@Autowired
	private TicGateViewService ticGateViewService;

	@RequiresPermissions("admin:ticGateView:list")
	@RequiresPermissionsDesc(menu = {"景区管理", "景点管理"}, button = "查询")
	@GetMapping("/list")
	public Object list(Integer gateViewId, String gateViewName,String gateNum,
	                   @RequestParam(defaultValue = "1")Integer page,
	                   @RequestParam(defaultValue = "20")Integer size) {
		List<TicGateView> list = ticGateViewService.list(gateViewId,gateViewName,gateNum,page,size);
		long total = PageInfo.of(list).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", list);
		return ResponseUtil.ok(data);
	}

	@RequiresPermissions("admin:ticGateView:create")
	@RequiresPermissionsDesc(menu = {"景区管理", "景点管理"}, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody TicGateView ticGateView) {
		int i = ticGateViewService.add(ticGateView);
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:ticGateView:update")
	@RequiresPermissionsDesc(menu = {"景区管理", "景点管理"}, button = "修改")
	@PostMapping("/update")
	public Object update(@RequestBody TicGateView ticGateView) {
		int i = ticGateViewService.update(ticGateView);
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:ticGateView:delete")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票列表"}, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody TicGateView ticGateView) {
		int i = ticGateViewService.deleteById(ticGateView.getGateId());
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	@GetMapping("/like")
	public Object selectLike(String  gateNum) {
		TicGateView gateView = ticGateViewService.selectLike(gateNum);
		if (gateView == null) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok(gateView);
	}
}
