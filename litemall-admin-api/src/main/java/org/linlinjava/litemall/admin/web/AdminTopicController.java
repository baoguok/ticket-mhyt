package org.linlinjava.litemall.admin.web;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.dao.LitemallGoodsMapper;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.domain.LitemallGoodsExample;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.db.domain.LitemallTopic;
import org.linlinjava.litemall.db.domain.TicMessage;
import org.linlinjava.litemall.db.domain.LitemallGoods.Column;
import org.linlinjava.litemall.db.service.TicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/topic")
@Validated
public class AdminTopicController {
	private final Log logger = LogFactory.getLog(AdminTopicController.class);
	Column[] columns = new Column[] { Column.id };

	@Autowired
	private TicMessageService topicService;
	@Autowired
	private LitemallGoodsService goodsService;
	@Autowired
	private LitemallGoodsMapper goodsMapper;

//	@RequiresPermissions("admin:topic:list")
//	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "查询")
//	@GetMapping("/list")
//	public Object list(String title, String subtitle, @RequestParam(defaultValue = "1") Integer page,
//			@RequestParam(defaultValue = "10") Integer limit,
//			@Sort @RequestParam(defaultValue = "add_time") String sort,
//			@Order @RequestParam(defaultValue = "desc") String order) {
//		List<TicMessage> topicList = topicService.querySelective(title, subtitle, page, limit, sort, order);
//
//		Map<String, Object> dataList = new HashMap<>();
//		List<Object> topicLists = new ArrayList<>();
//
//		for (TicMessage topic : topicList) {
//			Map<String, Object> data = new HashMap<>();
//			/*String goods = "";
//			if (topic.getMesGoods() != null && !topic.getGoods().trim().isEmpty()) {
//				String[] proId = topic.getGoods().split(",");
//				for (String i : proId) {
//					Integer id = Integer.valueOf(i);
//					LitemallGoods good = goodsService.findByIdVO(id);
//					if (null != good) {
//						if ("".equals(goods)) {
//							goods = goods + good.getName();
//						} else {
//							goods = goods + "," + good.getName();
//						}
//					}
//				}
//			}*/
//			data.put("id", topic.getMesId());
//			data.put("title", topic.getMesTitle());
//			data.put("subtitle", topic.getMesSubtitle());
//			//data.put("picUrl", topic.getMesPicUrl());
//			data.put("content", topic.getMesContent());
//			//data.put("price", topic.getMesPrice());
//			//data.put("readCount", topic.getMesReadCount());
//			//data.put("goods", goods);
//			topicLists.add(data);
//		}
//
//		long total = PageInfo.of(topicList).getTotal();
//		dataList.put("total", total);
//		dataList.put("items", topicLists);
//
//		return ResponseUtil.ok(dataList);
//	}

	private Object validate(LitemallTopic topic) {
		String title = topic.getTitle();
		if (StringUtils.isEmpty(title)) {
			return ResponseUtil.badArgument();
		}
		String content = topic.getContent();
		if (StringUtils.isEmpty(content)) {
			return ResponseUtil.badArgument();
		}
		BigDecimal price = topic.getPrice();
		if (price == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	/*@RequiresPermissions("admin:topic:create")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "添加")
	@PostMapping("/create")
	public Object create(@RequestBody TicMessage topic) {
		System.out.println(topic.getGoods());
		if (topic.getGoods() != null && !topic.getGoods().trim().isEmpty()) {
			String[] goods = topic.getGoods().split(",");
			System.out.println(goods.length);
			String[] goodsId = new String[goods.length];
			for (int i = 0; i < goods.length; i++) {
				String pro = goods[i];
				LitemallGoodsExample example = new LitemallGoodsExample();
				LitemallGoodsExample.Criteria criteria = example.createCriteria();
				criteria.andNameLike(pro);
				LitemallGoods pros = goodsMapper.selectOneByExampleSelective(example, columns);
				if (pros == null) {
					return ResponseUtil.badGoodsValue();
				} else {
					goodsId[i] = pros.getId().toString();
				}
			}
			String pros = String.join(",", goodsId);
			System.out.println(pros);
			topic.setGoods(pros);
		}

		Object error = validate(topic);
		if (error != null) {
			return error;
		}
		topicService.add(topic);
		return ResponseUtil.ok(topic);
	}
*/
/*	@RequiresPermissions("admin:topic:read")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "详情")
	@GetMapping("/read")
	public Object read(@NotNull Integer id) {
		LitemallTopic topic = topicService.findById(id);
		return ResponseUtil.ok(topic);
	}*/

	/*@RequiresPermissions("admin:topic:update")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "编辑")
	@PostMapping("/update")
	public Object update(@RequestBody LitemallTopic topic) {
		System.out.println("---" + topic.getGoods());
		if (topic.getGoods() != null) {
			String[] goods = topic.getGoods().split(",");
			if (!goods[0].trim().isEmpty()) {
				String[] goodsId = new String[goods.length];
				for (int i = 0; i < goods.length; i++) {
					String pro = goods[i];
					LitemallGoodsExample example = new LitemallGoodsExample();
					LitemallGoodsExample.Criteria criteria = example.createCriteria();
					criteria.andNameLike(pro);
					LitemallGoods pros = goodsMapper.selectOneByExampleSelective(example, columns);
					if (pros == null) {
						return ResponseUtil.badGoodsValue();
					} else {
						goodsId[i] = pros.getId().toString();
					}
				}
				String pros = String.join(",", goodsId);
				System.out.println(pros);
				topic.setGoods(pros);
			}
		}
		Object error = validate(topic);
		if (error != null) {
			return error;
		}
		if (topicService.updateById(topic) == 0) {
			return ResponseUtil.updatedDataFailed();
		}
		return ResponseUtil.ok(topic);
	}

	@RequiresPermissions("admin:topic:delete")
	@RequiresPermissionsDesc(menu = { "推广管理", "专题管理" }, button = "删除")
	@PostMapping("/delete")
	public Object delete(@RequestBody LitemallTopic topic) {
		topicService.deleteById(topic.getId());
		return ResponseUtil.ok();
	}*/

}
