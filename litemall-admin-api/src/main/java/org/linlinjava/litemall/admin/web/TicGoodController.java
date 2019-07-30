package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.request.TicGoodRequest;
import org.linlinjava.litemall.db.response.TicGoodResponse;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/ticgood")
@Validated
public class TicGoodController {
	@Autowired
	private TicGoodsService ticGoodService;

	@RequiresPermissions("admin:ticgood:list")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票列表"}, button = "查询")
	@PostMapping("/list")
	public Object list(@RequestBody TicGoodRequest goodReq) {
		List<TicGoodResponse> goodList = ticGoodService.list(goodReq);
		long total = PageInfo.of(goodList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", goodList);
		return ResponseUtil.ok(data);
	}

	@RequiresPermissions("admin:ticgood:create")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票列表"}, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody TicGoods ticGoods) {
		int i = ticGoodService.create(ticGoods);
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:ticgood:update")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票列表"}, button = "修改")
	@PostMapping("/update")
	public Object update(@RequestBody TicGoods ticGoods) {
		int i = ticGoodService.update(ticGoods);
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:ticgood:delete")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票列表"}, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody TicGoods ticGoods) {
		int i = ticGoodService.delete(ticGoods.getGooId());
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}
}
