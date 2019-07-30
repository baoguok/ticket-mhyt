package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicAdCategory;
import org.linlinjava.litemall.db.request.TicAdCateRequest;
import org.linlinjava.litemall.db.service.TicAdCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/adCate")
@Validated
public class TicAdCateController {
    @Autowired
    private TicAdCategoryService ticAdCateService;

    @RequiresPermissions("admin:adCate:list")
    @RequiresPermissionsDesc(menu={"门票管理" , "门票类目"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicAdCateRequest ticAdCateRequest){
    	List<TicAdCategory> adCateList = ticAdCateService.list(ticAdCateRequest);
    	long total = PageInfo.of(adCateList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", adCateList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:adCate:create")
    @RequiresPermissionsDesc(menu={"门票管理" , "门票类目"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicAdCategory ticAdCategory){
    	int i = ticAdCateService.create(ticAdCategory);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:adCate:update")
    @RequiresPermissionsDesc(menu={"门票管理" , "门票类目"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicAdCategory ticAdCategory){
    	int i = ticAdCateService.update(ticAdCategory);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:adCate:delete")
    @RequiresPermissionsDesc(menu={"门票管理" , "门票类目"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicAdCategory ticAdCategory){
    	int i = ticAdCateService.delete(ticAdCategory.getAdcId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
