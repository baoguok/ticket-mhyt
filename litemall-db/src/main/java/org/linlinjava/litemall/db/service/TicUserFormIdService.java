package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallUserFormidMapper;
import org.linlinjava.litemall.db.dao.TicUserFormidMapper;
import org.linlinjava.litemall.db.domain.LitemallUserFormid;
import org.linlinjava.litemall.db.domain.LitemallUserFormidExample;
import org.linlinjava.litemall.db.domain.TicUserFormid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

@Service
public class TicUserFormIdService {
    @Resource
    private TicUserFormidMapper formidMapper;

    /**
     * 查找是否有可用的FormId
     *
     * @param openId
     * @return
     */
//    public LitemallUserFormid queryByOpenId(String openId) {
//        LitemallUserFormidExample example = new LitemallUserFormidExample();
//        //符合找到该用户记录，且可用次数大于1，且还未过期
//        example.or().andOpenidEqualTo(openId).andExpireTimeGreaterThan(LocalDateTime.now());
//        return formidMapper.selectOneByExample(example);
//    }

    /**
     * 更新或删除FormId
     *
     * @param userFormid
     */
    public int updateUserFormId(TicUserFormid userFormid) {
        Date date = LocalToDate(LocalDateTime.now());
        //更新或者删除缓存
        if (userFormid.getForIsprepay() && userFormid.getForUseamount() > 1) {
            userFormid.setForUseamount(userFormid.getForUseamount() - 1);
            userFormid.setForUpdateTime(date);
            return formidMapper.updateByPrimaryKey(userFormid);
        } else {
            return formidMapper.deleteByPrimaryKey(userFormid.getForId());
        }
    }

    /**
     * 添加一个 FormId
     *
     * @param userFormid
     */
    public void addUserFormid(TicUserFormid userFormid) {
        Date date = LocalToDate(LocalDateTime.now());
        userFormid.setForAddTime(date);
        userFormid.setForUpdateTime(date);
        formidMapper.insertSelective(userFormid);
    }
}
