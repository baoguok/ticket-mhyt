package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.TicCouponUserMapper;
import org.linlinjava.litemall.db.domain.CustomTicCouAndCos;
import org.linlinjava.litemall.db.domain.TicCouponUser;
import org.linlinjava.litemall.db.domain.TicCouponUserExample;
import org.linlinjava.litemall.db.util.CouponUserConstant;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicCouponUserService {
    @Resource
    private TicCouponUserMapper couponUserMapper;

    /**
     * 可使用优惠券
     * @param userId
     * @return
     */
    public List<CustomTicCouAndCos> MayUseCoupon(Integer userId) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userId", userId);
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    	map.put("date", df.format(new Date()) );
       return couponUserMapper.MayUseCoupon(map);
    }
    public Integer countCoupon(Integer couponId) {
        TicCouponUserExample example = new TicCouponUserExample();
        example.or().andCosCouponIdEqualTo(couponId).andCosDeletedEqualTo(false);
        return (int)couponUserMapper.countByExample(example);
    }

    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
    	TicCouponUserExample example = new TicCouponUserExample();
        example.or().andCosUserIdEqualTo(userId).andCosCouponIdEqualTo(couponId).andCosDeletedEqualTo(false);
        return (int)couponUserMapper.countByExample(example);
    }

    public void add(TicCouponUser couponUser) {
        couponUser.setCosAddTime(LocalDateTime.now());
        couponUser.setCosUpdateTime(LocalDateTime.now());
        couponUserMapper.insertSelective(couponUser);
    }

    public List<TicCouponUser> queryList(Integer userId, Integer couponId, Short status) {
    	TicCouponUserExample example = new TicCouponUserExample();
    	TicCouponUserExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andCosUserIdEqualTo(userId);
        }
        if(couponId != null){
            criteria.andCosCouponIdEqualTo(couponId);
        }
        if (status != null) {
            criteria.andCosStatusEqualTo(status);
        }
        example.setOrderByClause("cos_add_time" + " " + "desc");
        return couponUserMapper.selectByExample(example);
    }
    
    public TicCouponUser queryOneByOrder(Integer userId, Integer orderId) {
    	TicCouponUserExample example = new TicCouponUserExample();
    	TicCouponUserExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andCosUserIdEqualTo(userId);
        }
        if(orderId != null) {
            criteria.andCosCouponIdEqualTo(orderId);
        }
        criteria.andCosDeletedEqualTo(false);
        List<TicCouponUser> couponUserList = couponUserMapper.selectByExample(example);
        if(couponUserList.size() == 0){
            return null;
        }
        return couponUserList.get(0);
    }
    
    public List<TicCouponUser> queryAll(Integer userId, Integer couponId) {
        return queryList(userId, couponId, CouponUserConstant.STATUS_USABLE);
    }

    public List<TicCouponUser> queryAll(Integer userId) {
        return queryList(userId, null, CouponUserConstant.STATUS_USABLE);
    }

    public TicCouponUser queryOne(Integer userId, Integer couponId) {
        List<TicCouponUser> couponUserList = queryList(userId, couponId, CouponUserConstant.STATUS_USABLE);
        if(couponUserList.size() == 0){
            return null;
        }
        return couponUserList.get(0);
    }

    public TicCouponUser findById(Integer id) {
        return couponUserMapper.selectByPrimaryKey(id);
    }


    public int update(TicCouponUser couponUser) {
        couponUser.setCosUpdateTime(LocalDateTime.now());
        return couponUserMapper.updateByPrimaryKeySelective(couponUser);
    }

    public List<TicCouponUser> queryExpired() {
    	TicCouponUserExample example = new TicCouponUserExample();
        example.or().andCosStatusEqualTo(CouponUserConstant.STATUS_USABLE).andCosEndTimeLessThan(LocalDateTime.now()).andCosDeletedEqualTo(false);
        return couponUserMapper.selectByExample(example);
    }

    /**
     * 条件修改（userId,couponId）
     * @param couponUser
     * @return
     * @author liuyu
     */
    public int updateCou(TicCouponUser couponUser){
        couponUser.setCosUpdateTime(LocalDateTime.now());
        return couponUserMapper.updateSelect(couponUser);
    }
}
