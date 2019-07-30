package org.linlinjava.litemall.wx.web;

import com.alibaba.druid.support.json.JSONUtils;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.GoodsStock;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 门票服务
 */
@RestController
@RequestMapping("/wx/goods")
@Validated
public class WxGoodsController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private TicGoodsService goodsService;

	@GetMapping("getStock")
	public Object getStock(String Time, Integer catId) {
		if(StringUtils.isEmpty(Time) || StringUtils.isEmpty(catId)) {
	 		return ResponseUtil.badArgument();
		}
		// key值
		String key = Time + "-" + catId;
		if (!stringRedisTemplate.hasKey(key)){
			List<GoodsStock> goodList = goodsService.GetStock(Time, catId);
			String goodStr = JSONUtils.toJSONString(goodList);
			stringRedisTemplate.opsForValue().set(key, goodStr,5 * 60, TimeUnit.SECONDS);
		}
		return ResponseUtil.ok(stringRedisTemplate.opsForValue().get(key));
	}
}