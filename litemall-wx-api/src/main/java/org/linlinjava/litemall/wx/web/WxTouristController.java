package org.linlinjava.litemall.wx.web;

import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.TicTourist;
import org.linlinjava.litemall.db.service.TicTouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/tourist")
@Validated
public class WxTouristController {

    @Autowired
    private TicTouristService touristService;

    @GetMapping("query")
    public Object query(Integer touUserId){
        if(StringUtils.isEmpty(touUserId)){
            return ResponseUtil.badArgumentValue();
        }
        Map<String,Object> map = new HashMap();
        TicTourist query = touristService.query(touUserId);
        if (StringUtils.isEmpty(query)) {
            map.put("total", 0);
        } else {
            map.put("total", 1);
        }
        map.put("items",query);
        return ResponseUtil.ok(map);
    }
}
