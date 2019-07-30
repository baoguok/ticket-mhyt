package org.linlinjava.litemall.wx.web;

import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.request.TicStaticRequest;
import org.linlinjava.litemall.db.service.TicStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx/statis")
@Validated
public class WxStatisticsController {
    @Autowired
    private TicStatisService statisService;

    /**
     * @param （true 售票；false:退票）
     * @return
     */
    @PostMapping("update")
    public Object update(@RequestBody TicStaticRequest request) {
        int update;
        if (StringUtils.isEmpty(request.getStaSelId())) {
            return ResponseUtil.badArgument();
        }
        if (request.getBool()) {
            update = statisService.updateSold(request);
        } else {
            update = statisService.updateRetired(request);
            int up = statisService.updateSold(request);
            if( up == 0){
                return ResponseUtil.badArgument();
            }
        }
        if (update == 0) {
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok();
    }
}
