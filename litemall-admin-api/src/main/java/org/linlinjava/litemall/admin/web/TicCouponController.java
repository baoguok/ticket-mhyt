package org.linlinjava.litemall.admin.web;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicCoupon;
import org.linlinjava.litemall.db.request.TicCateRequest;
import org.linlinjava.litemall.db.request.TicCouponRequest;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.linlinjava.litemall.db.service.TicCouponService;
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
@RequestMapping("/admin/ticcoupon")
@Validated
public class TicCouponController {
    @Autowired
    private TicCouponService ticCouponService;

    @RequiresPermissions("admin:ticcoupon:list")
    @RequiresPermissionsDesc(menu={"推广管理" , "优惠券"}, button="查询")
    @PostMapping("/list")
    public Object list(@RequestBody TicCouponRequest cateRequest){

    	List<TicCoupon> couponList = ticCouponService.list(cateRequest);
    	long total = PageInfo.of(couponList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", couponList);
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:ticcoupon:create")
    @RequiresPermissionsDesc(menu={"推广管理" , "优惠券"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody TicCoupon ticCoupon){
    	int i = ticCouponService.create(ticCoupon);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticcoupon:update")
    @RequiresPermissionsDesc(menu={"推广管理" , "优惠券"}, button="修改")
    @PostMapping("/update")
    public Object update(@RequestBody TicCoupon ticCoupon){
    	int i = ticCouponService.update(ticCoupon);
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ticcoupon:delete")
    @RequiresPermissionsDesc(menu={"推广管理" , "优惠券"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody TicCoupon ticCoupon){
    	int i = ticCouponService.delete(ticCoupon.getCouId());
    	if(i == 0) {
    		return ResponseUtil.badArgument();
    	}
        return ResponseUtil.ok();
    }
}
