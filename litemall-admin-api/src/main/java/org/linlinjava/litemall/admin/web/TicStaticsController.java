package org.linlinjava.litemall.admin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.domain.TicStatistics;
import org.linlinjava.litemall.db.response.TicStaResponse;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.linlinjava.litemall.db.service.TicStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/admin/statis")
@Validated
public class TicStaticsController {
		@Autowired
		private TicStatisService ticStatisService;
		@Autowired
		private TicGoodsService ticGoodService;

		@RequiresPermissions("admin:statis:list")
		@RequiresPermissionsDesc(menu = {"景区管理", "统计信息"}, button = "查询")
		@GetMapping("/list")
		public Object list(Integer selId) {
				// 查询余票
				TicGoods goods = new TicGoods();
				List<TicGoods> goodList = ticGoodService.listGood(goods);
				// 余票
				Integer residualTatal = 0;
				for (TicGoods good : goodList) {
						Integer gooTotal = good.getGooTotal();
						residualTatal = residualTatal + gooTotal;
				}
				// 查询统计数量
				List<TicStatistics> statisList = ticStatisService.list(selId);
				TicStaResponse ticStaResponse = new TicStaResponse();
				ticStaResponse.setResidual(residualTatal);
				long total = PageInfo.of(statisList).getTotal();
				Map<String, Object> data = new HashMap<>();
				Integer soldTotal = 0;      // 已售
				Integer retiredTotal = 0;    // 已退
				Integer staTotal = 0;      // 已售总数
				if (total > 1) {
						for (TicStatistics statis : statisList) {
								soldTotal = soldTotal + statis.getStaSold();
								retiredTotal = retiredTotal + statis.getStaRetired();
								staTotal = staTotal + statis.getStaTotal();
						}
						ticStaResponse.setSold(soldTotal);
						ticStaResponse.setRetired(retiredTotal);
						ticStaResponse.setStaTotal(staTotal);
				} else if (total == 1) {
						TicStatistics statis = statisList.get(0);
						ticStaResponse.setSold(statis.getStaSold());
						ticStaResponse.setRetired(statis.getStaRetired());
						ticStaResponse.setStaTotal(statis.getStaTotal());
				} else {
						TicStatistics ticStatistics = new TicStatistics();
						ticStatistics.setStaSelId(selId);
						ticStatisService.add(ticStatistics);
						ticStaResponse.setSold(0);
						ticStaResponse.setRetired(0);
						ticStaResponse.setStaTotal(0);
				}
				data.put("items", ticStaResponse);
				return ResponseUtil.ok(data);
		}
}
