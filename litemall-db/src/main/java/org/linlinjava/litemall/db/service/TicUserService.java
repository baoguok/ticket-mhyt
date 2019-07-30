package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicUserMapper;
import org.linlinjava.litemall.db.domain.TicUser;
import org.linlinjava.litemall.db.domain.TicUserExample;
import org.linlinjava.litemall.db.domain.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicUserService {
    @Resource
    private TicUserMapper userMapper;

    public TicUser findById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public UserVo findUserVoById(Integer userId) {
    	TicUser user = findById(userId);
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getUseNickname());
        userVo.setAvatar(user.getUseAvatar());
        return userVo;
    }

    public TicUser queryByOid(String openId) {
    	TicUserExample example = new TicUserExample();
        example.or().andUseWeixinOpenidEqualTo(openId).andUseDeletedEqualTo(false);
        return userMapper.selectOneByExample(example);
    }

    public int add(TicUser user) {
        user.setUseAddTime(LocalDateTime.now());
        user.setUseUpdateTime(LocalDateTime.now());
        return userMapper.insertSelective(user);
    }

    public int updateById(TicUser user) {
        user.setUseUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public List<TicUser> querySelective(String nickname, String mobile, Integer page, Integer size, String sort, String order) {
    	TicUserExample example = new TicUserExample();
    	TicUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(nickname)) {
            criteria.andUseNicknameLike("%" + nickname + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andUseMobileLike("%" + mobile + "%");
        }
        criteria.andUseDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return userMapper.selectByExample(example);
    }

    public int count() {
    	TicUserExample example = new TicUserExample();
        example.or().andUseDeletedEqualTo(false);

        return (int) userMapper.countByExample(example);
    }

    public List<TicUser> queryByUsername(String username) {
    	TicUserExample example = new TicUserExample();
        example.or().andUseNameEqualTo(username).andUseDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public boolean checkByUsername(String username) {
    	TicUserExample example = new TicUserExample();
        example.or().andUseNameEqualTo(username).andUseDeletedEqualTo(false);
        return userMapper.countByExample(example) != 0;
    }

    public List<TicUser> queryByMobile(String mobile) {
    	TicUserExample example = new TicUserExample();
        example.or().andUseMobileEqualTo(mobile).andUseDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public List<TicUser> queryByOpenid(String openid) {
    	TicUserExample example = new TicUserExample();
        example.or().andUseWeixinOpenidEqualTo(openid).andUseDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public int deleteById(Integer id) {
        return userMapper.logicalDeleteByPrimaryKey(id);
    }
}
