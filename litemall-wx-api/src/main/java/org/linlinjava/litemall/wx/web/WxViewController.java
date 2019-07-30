package org.linlinjava.litemall.wx.web;

import com.github.pagehelper.PageInfo;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicViewpoint;
import org.linlinjava.litemall.db.service.TicViewpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/wx/view")
@Validated
public class WxViewController {
  @Autowired
  private TicViewpointService viewService;
  
  /**
   * 条件查询
   * @param page
   * @param limit
   * @return
   * @author liuyu
   */
  @GetMapping("list")
  public Object list(Integer page,Integer limit) {
	  List<TicViewpoint> list = viewService.list(null, null, page, limit);
	  int allTotal = viewService.queryCount();
	  Map<String, Object> map = new HashMap<String, Object>();
	  Long total = PageInfo.of(list).getTotal();
	  map.put("items",list);
	  map.put("total",total);
	  map.put("allTotal",allTotal);
	  return ResponseUtil.ok(map);
  }
  
  /**
   * 单个查询
   * @param vieId
   * @return
   * @author liuyu
   */
  @GetMapping("query")
  public Object query(Integer vieId) {
	  if(StringUtils.isEmpty(vieId)) {
		  return ResponseUtil.badArgument();
	  }
	  TicViewpoint viewpoint = viewService.query(vieId);
	  Map<String, Object> map = new HashMap<String, Object>();
	  map.put("items",viewpoint);
	  return ResponseUtil.ok(map);
  }

	/**
	 * 修改阅读量(增加)
	 * @param vieId
	 * @return
	 */
  	@GetMapping("updateRead")
  	public Object updateRead(Integer vieId){
	  	int i = viewService.updateRead(vieId);
	  	if(i == 0){
	  		return ResponseUtil.badArgument();
	  	}
	  	return ResponseUtil.ok();
  	}
}