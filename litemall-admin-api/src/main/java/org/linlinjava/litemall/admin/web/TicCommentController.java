package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicComment;
import org.linlinjava.litemall.db.request.TicCommentRequest;
import org.linlinjava.litemall.db.service.TicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/ticComment")
@Validated
public class TicCommentController {
    @Autowired
    private TicCommentService ticCommentService;

    @RequiresPermissions("admin:ticComment:list")
    @RequiresPermissionsDesc(menu={"景区管理" , "订单信息"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicCommentRequest ticCommentRequest){
    	List<TicComment> comList = ticCommentService.list(ticCommentRequest);
    	long total = PageInfo.of(comList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", comList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:ticComment:create")
    @RequiresPermissionsDesc(menu={"景区管理" , "评论信息"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicComment ticComment){
    	int i = ticCommentService.create(ticComment);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticComment:update")
    @RequiresPermissionsDesc(menu={"景区管理" , "评论信息"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicComment ticComment){
    	int i = ticCommentService.update(ticComment);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticComment:delete")
    @RequiresPermissionsDesc(menu={"景区管理" , "订单信息"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicComment ticComment){
    	int i = ticCommentService.delete(ticComment.getComId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
