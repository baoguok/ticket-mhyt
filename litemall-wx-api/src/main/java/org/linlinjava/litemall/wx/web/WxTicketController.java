package org.linlinjava.litemall.wx.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/ticket")
@Validated
public class WxTicketController {
	private final Log logger = LogFactory.getLog(WxHomeController.class);
    @Autowired
    private TicGoodsService goodsService;
    
    @GetMapping("/list")
	private List<TicGoods> getGoodsList() {
        List<TicGoods> goodsList = goodsService.query();
        return goodsList;
    }
}
