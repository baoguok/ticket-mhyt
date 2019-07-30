package org.linlinjava.litemall.admin.win;

import com.github.pagehelper.PageInfo;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.linlinjava.litemall.admin.dao.TicStaticReq;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.GoodsStock;
import org.linlinjava.litemall.db.domain.TicStatistics;
import org.linlinjava.litemall.db.request.TicStaticRequest;
import org.linlinjava.litemall.db.response.TicStaResponse;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.linlinjava.litemall.db.service.TicStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/win/statics")
@Validated
public class WinStaticsController {

	@Autowired
	private TicStatisService ticStatisService;
	@Autowired
	private TicGoodsService ticGoodService;

	/**
	 * 查询每日售票信息
	 *
	 * @param staticReq
	 * @return
	 */
	@PostMapping("list")
	public Object list(@RequestBody TicStaticReq staticReq) {
		if (StringUtils.isEmpty(staticReq)) {
			return ResponseUtil.badArgument();
		}
		Integer selId = staticReq.getId();
		// 获取当天时间
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String format = df.format(LocalDateTime.now());
		// 查询余票
		List<GoodsStock> goodList = ticGoodService.GetStock(format, null);
		// 初始化余票
		Integer residualTatal = 0;
		if(!StringUtils.isEmpty(goodList) || goodList.size() != 0){
			for (GoodsStock good : goodList) {
				Integer gooTotal = good.getStock();
				residualTatal = residualTatal + gooTotal;
			}
		}
		TicStaticRequest staticRequest = new TicStaticRequest();
		staticRequest.setOrdOrderStatus(301);
		Integer soldTotal = ticStatisService.queryTicNum(staticRequest);//今日已售
		Integer staTotal = ticStatisService.queryAll(staticRequest); //总售
		staticRequest.setOrdOrderStatus(203);
		Integer retiredTotal = ticStatisService.queryTicNum(staticRequest);//今日已售
		TicStaResponse staResponse = new TicStaResponse();
		staResponse.setRetired(retiredTotal);
		staResponse.setStaTotal(staTotal);
		staResponse.setSold(soldTotal);
		staResponse.setResidual(residualTatal);
		Map<String, Object> map = new HashMap<>();
		map.put("date",staResponse);
		return ResponseUtil.ok(map);
	}

}
