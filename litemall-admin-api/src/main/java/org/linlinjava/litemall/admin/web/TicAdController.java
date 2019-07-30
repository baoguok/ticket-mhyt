package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicAd;
import org.linlinjava.litemall.db.request.TicAdRequest;
import org.linlinjava.litemall.db.response.TicAdResponse;
import org.linlinjava.litemall.db.service.TicAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/ticad")
@Validated
public class TicAdController {
    @Autowired
    private TicAdService ticAdService;

    @RequiresPermissions("admin:ticad:list")
    @RequiresPermissionsDesc(menu={"推广管理" , "景点图片"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicAdRequest adRequest){
    	List<TicAdResponse> adList = ticAdService.list(adRequest);
    	long total = PageInfo.of(adList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", adList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:ticad:update")
    @RequiresPermissionsDesc(menu={"推广管理" , "景点图片"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicAd ticAd){
    	int i = ticAdService.updateById(ticAd);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticad:create")
    @RequiresPermissionsDesc(menu={"推广管理" , "景点图片"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicAd ticAd){
        int i = ticAdService.create(ticAd);
        if(i == 0) {
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticad:delete")
    @RequiresPermissionsDesc(menu={"推广管理" , "景点图片"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicAd ticAd){
    	int i = ticAdService.delete(ticAd.getAdId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
