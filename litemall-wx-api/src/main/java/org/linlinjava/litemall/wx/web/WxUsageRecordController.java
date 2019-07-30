package org.linlinjava.litemall.wx.web;

import com.github.pagehelper.PageInfo;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallUsageRecord;
import org.linlinjava.litemall.db.service.LitemallUsageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询订单
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/wx/usageRecord")
@Validated
public class WxUsageRecordController {

	@Autowired
	private LitemallUsageRecordService usageRecordService;

	@RequestMapping(value = "/usageList" ,method = RequestMethod.POST)
	public Object listUsage(Integer userId, Integer status) {
		//判断userId和status不为空
		if((StringUtils.isEmpty(userId) || userId.equals(0)) && StringUtils.isEmpty(status)){
			return ResponseUtil.badArgument();
		}
		//查询订单信息
		List<LitemallUsageRecord> listUsageRecord = usageRecordService.listUsageRecord(userId, status);
		long total = PageInfo.of(listUsageRecord).getTotal();
		//查询信息存入集合
		Map<String, Object> data = new HashMap<>();
		data.put("total",total);
		data.put("items",listUsageRecord);
		return ResponseUtil.ok(data);
	}
}
