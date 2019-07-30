package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.request.TicCateRequest;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/cate")
@Validated
public class TicCateController {
	@Autowired
	private TicCategoryService ticCateService;

	@RequiresPermissions("admin:cate:list")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票类目"}, button = "查询")
	@PostMapping("/list")
	public Object list(@RequestBody TicCateRequest cateRequest) {
		List<TicCategory> cateList = ticCateService.list(cateRequest);
		long total = PageInfo.of(cateList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", cateList);
		return ResponseUtil.ok(data);
	}

	@RequiresPermissions("admin:cate:create")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票类目"}, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody TicCategory ticCate) {
		int i = ticCateService.create(ticCate);
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:cate:update")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票类目"}, button = "修改")
	@PostMapping("/update")
	public Object update(@RequestBody TicCategory ticCate) {
		int i = ticCateService.update(ticCate);
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}

	@RequiresPermissions("admin:cate:delete")
	@RequiresPermissionsDesc(menu = {"门票管理", "门票类目"}, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody TicCategory ticCate) {
		int i = ticCateService.delete(ticCate.getCatId());
		if (i == 0) {
			return ResponseUtil.badArgument();
		}
		return ResponseUtil.ok();
	}
}
