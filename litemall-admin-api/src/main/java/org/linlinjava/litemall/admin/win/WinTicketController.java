package org.linlinjava.litemall.admin.win;

import com.github.pagehelper.PageInfo;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.response.TicCateResponse;
import org.linlinjava.litemall.db.service.TicCategoryService;
import org.linlinjava.litemall.db.service.TicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/win/ticket")
@Validated
public class WinTicketController {
	@Autowired
	private TicCategoryService categoryService;
	@Autowired
	private TicOrderService orderService;

	/**
	 * 查询所有门票类别,以及门票列表
	 *
	 * @param
	 * @return
	 */
	@GetMapping("list")
	public Object listTicket() {
		List<TicCateResponse> ticCateList = categoryService.WinCateList();
		long total = PageInfo.of(ticCateList).getTotal();
		Map<String, Object> data = new HashMap<>();
		data.put("total", total);
		data.put("items", ticCateList);
		return ResponseUtil.ok(data);
	}


}
