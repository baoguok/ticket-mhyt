package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicViewpoint;
import org.linlinjava.litemall.db.service.TicViewpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/view")
@Validated
public class TicViePointController {
    @Autowired
    private TicViewpointService ticViewpointService;

    @RequiresPermissions("admin:view:list")
    @RequiresPermissionsDesc(menu={"景区管理" , "景点信息"}, button="查询")
    @GetMapping("/list")
    public Object list(String vieTitle, String vieSubtitle,
                       @RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "20")Integer limit){
    	List<TicViewpoint> vieList = ticViewpointService.list(vieTitle,vieSubtitle,page,limit);
    	long total = PageInfo.of(vieList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", vieList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:view:create")
    @RequiresPermissionsDesc(menu={"景区管理" , "景点信息"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicViewpoint ticViewpoint){
    	int i = ticViewpointService.create(ticViewpoint);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:view:update")
    @RequiresPermissionsDesc(menu={"景区管理" , "景点信息"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicViewpoint ticViewpoint){
    	int i = ticViewpointService.update(ticViewpoint);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:view:delete")
    @RequiresPermissionsDesc(menu={"景区管理" , "景点信息"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicViewpoint ticViewpoint){
    	int i = ticViewpointService.delete(ticViewpoint.getVieId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
