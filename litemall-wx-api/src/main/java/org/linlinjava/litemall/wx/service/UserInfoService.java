package org.linlinjava.litemall.wx.service;

import org.linlinjava.litemall.db.domain.TicUser;
import org.linlinjava.litemall.db.service.TicUserService;
import org.linlinjava.litemall.wx.dao.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserInfoService {
    @Autowired
    private TicUserService userService;


    public UserInfo getInfo(Integer userId) {
        TicUser user = userService.findById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getUseNickname());
        userInfo.setAvatarUrl(user.getUseAvatar());
        return userInfo;
    }
}
