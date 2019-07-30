package org.linlinjava.litemall.wx.web;

import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/wx/redis")
@Validated
public class WxRedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("delredis")
    public Object delredis(String keyUser,@LoginUser Integer userId) {
        Map<Object, Object> data = new HashMap<Object, Object>();
        if (stringRedisTemplate.delete(keyUser)) {
            data.put("recode", "1000");// 删除用户访问记录成功
        } else {
            data.put("recode", "1001");// 删除用户访问记录失败
        }
        return ResponseUtil.ok(data);
    }
}
