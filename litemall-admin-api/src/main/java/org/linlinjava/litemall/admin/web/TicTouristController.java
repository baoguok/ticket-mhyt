package org.linlinjava.litemall.admin.web;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicTourist;
import org.linlinjava.litemall.db.request.TicTouristRequest;
import org.linlinjava.litemall.db.response.TicTouristResponse;
import org.linlinjava.litemall.db.service.TicTouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/tourist")
@Validated
public class TicTouristController {
    @Autowired
    private TicTouristService touristService;

    @RequiresPermissions("admin:tourist:list")
    @RequiresPermissionsDesc(menu={"用户管理" , "游客信息"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicTouristRequest touristRequest){
    	List<TicTouristResponse> touList = touristService.list(touristRequest);
    	long total = PageInfo.of(touList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", touList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:tourist:delete")
    @RequiresPermissionsDesc(menu={"景区管理" , "反馈信息"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicTourist tourist){
    	int i = touristService.delete(tourist.getTouId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
