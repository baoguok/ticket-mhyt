package org.linlinjava.litemall.admin.web;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicUser;
import org.linlinjava.litemall.db.request.TicUserRequest;
import org.linlinjava.litemall.db.service.TicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/ticuser")
@Validated
public class TicUserController {
    @Autowired
    private TicUserService ticUserService;

    @RequiresPermissions("admin:ticuser:list")
    @RequiresPermissionsDesc(menu={"用户管理" , "会员管理"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicUserRequest userReq){
        String useNickname = userReq.getUseNickname();
        String useMobile = userReq.getUseMobile();
        Integer page = userReq.getPage();
        Integer limit = userReq.getLimit();
        String order = userReq.getOrder();
        String sort = userReq.getSort();
        List<TicUser> userList = ticUserService.querySelective(useNickname,useMobile,page,limit,sort,order);
        long total = PageInfo.of(userList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", userList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:ticuser:create")
    @RequiresPermissionsDesc(menu={"用户管理" , "会员管理"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicUser ticUser){
    	int i = ticUserService.add(ticUser);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticuser:update")
    @RequiresPermissionsDesc(menu={"用户管理" , "会员管理"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicUser ticUser){
    	int i = ticUserService.updateById(ticUser);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticuser:delete")
    @RequiresPermissionsDesc(menu={"用户管理" , "会员管理"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicUser ticUser){
        Integer useId = ticUser.getUseId();
        if(StringUtils.isEmpty(useId)){
            return ResponseUtil.badArgument();
        }
        int i = ticUserService.deleteById(useId);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
