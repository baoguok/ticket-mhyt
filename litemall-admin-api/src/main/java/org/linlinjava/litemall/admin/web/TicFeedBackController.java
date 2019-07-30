package org.linlinjava.litemall.admin.web;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicFeedback;
import org.linlinjava.litemall.db.domain.TicViewpoint;
import org.linlinjava.litemall.db.request.TicFeedBackRequest;
import org.linlinjava.litemall.db.service.TicFeedBackService;
import org.linlinjava.litemall.db.service.TicViewpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/ticback")
@Validated
public class TicFeedBackController {
    @Autowired
    private TicFeedBackService feedBackService;

    @RequiresPermissions("admin:ticback:list")
    @RequiresPermissionsDesc(menu={"用户管理" , "反馈信息"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicFeedBackRequest feedBackRequest){
    	List<TicFeedback> backList = feedBackService.list(feedBackRequest);
    	long total = PageInfo.of(backList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", backList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:view:delete")
    @RequiresPermissionsDesc(menu={"景区管理" , "反馈信息"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicFeedback TicFeedback){
    	int i = feedBackService.delete(TicFeedback.getFeeId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
