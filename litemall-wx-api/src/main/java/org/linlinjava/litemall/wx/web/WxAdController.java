package org.linlinjava.litemall.wx.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicAd;
import org.linlinjava.litemall.db.domain.TicAdCategory;
import org.linlinjava.litemall.db.service.TicAdCategoryService;
import org.linlinjava.litemall.db.service.TicAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/wx/ad")
@Validated
public class WxAdController {
	private final Log logger = LogFactory.getLog(WxAdController.class);
	
    @Autowired
    private TicAdService adService;
    
    @Autowired
    private TicAdCategoryService adCategoryService;
    //查询所有图片
    @GetMapping("/list")
    public Object getAdPhotos(@RequestParam(defaultValue = "0") Integer showType,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
    	Map<String,Object> data = new HashMap<>(); 
    	List<Object> ad = new ArrayList<>();
    	List<TicAd> adList = adService.querySelective(showType, page, size, "ad_add_time", "desc");
    	for (TicAd ticAd : adList) {
    		TicAdCategory adCate = adCategoryService.getCategory(ticAd.getAdCatId());
    		Map<String, Object> c = new HashMap<>();
    		c.put("cateName", adCate.getAdcName());
    		c.put("url", ticAd.getAdUrl());
    		ad.add(c);
		}
    	data.put("adList", ad);
    	data.put("count", PageInfo.of(adList).getTotal());
    	return ResponseUtil.ok(data);
    }
    //查询所有类及类别下图片的张数
    @GetMapping("/cateCount")
    public Object getCateCount() {
    	List<TicAdCategory> adCate = adCategoryService.getAllCategory();
    	int count = adService.countBycatId(0);
    	Map<String, Object> data = new HashMap<>();
    	data.put("all", count);
    	List<Object> cateList = new ArrayList<>();
    	for (TicAdCategory ticAdCategory : adCate) {
    		int countBycatId = adService.countBycatId(ticAdCategory.getAdcId());
    		Map<String, Object> c = new HashMap<>();
    		c.put("cateName", ticAdCategory.getAdcName());
    		c.put("cateCount", countBycatId);
    		cateList.add(c);
		}
    	data.put("cateList", cateList);
    	return ResponseUtil.ok(data);
    }
	
}
