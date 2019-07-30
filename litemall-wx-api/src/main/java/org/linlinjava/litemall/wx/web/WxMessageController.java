package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.domain.LitemallTopic;
import org.linlinjava.litemall.db.domain.TicMessage;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.db.service.TicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 景点信息服务
 */
@RestController
@RequestMapping("/wx/message")
@Validated
public class WxMessageController {
    private final Log logger = LogFactory.getLog(WxMessageController.class);

    @Autowired
    private TicMessageService messageService;

    /**
     * 专题列表
     *
     * @param page 分页页数
     * @param size 分页大小
     * @return 专题列表
     */
    @GetMapping("list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "20") Integer size) {
        List<TicMessage> messageList = messageService.list(page, size);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", messageList);
        return ResponseUtil.ok(data);
    }

}