package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicMessage;
import org.linlinjava.litemall.db.service.TicMessageService;
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
@RequestMapping("/admin/message")
@Validated
public class TicMesController {
    @Autowired
    private TicMessageService ticMessageService;

    @RequiresPermissions("admin:message:list")
    @RequiresPermissionsDesc(menu={"景区管理" , "景区信息"}, button="查询")
    @GetMapping("/list")
    public Object list(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "20")Integer limit){
    	List<TicMessage> mesList = ticMessageService.list(page,limit);
    	long total = PageInfo.of(mesList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", mesList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:message:create")
    @RequiresPermissionsDesc(menu={"景区管理" , "景区信息"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicMessage ticMessage){
    	int i = ticMessageService.create(ticMessage);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:message:update")
    @RequiresPermissionsDesc(menu={"景区管理" , "景区信息"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicMessage ticMessage){
    	int i = ticMessageService.update(ticMessage);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:message:delete")
    @RequiresPermissionsDesc(menu={"景区管理" , "景区信息"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicMessage ticMessage){
    	int i = ticMessageService.delete(ticMessage.getMesId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
