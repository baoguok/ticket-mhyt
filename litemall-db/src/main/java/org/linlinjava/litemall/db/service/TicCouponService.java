package org.linlinjava.litemall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicCouponMapper;
import org.linlinjava.litemall.db.dao.TicCouponUserMapper;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.domain.TicCoupon.Column;
import org.linlinjava.litemall.db.request.TicCouponRequest;
import org.linlinjava.litemall.db.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

@Service
public class TicCouponService {
    @Resource
    private TicCouponMapper couponMapper;
    @Resource
    private TicCouponUserMapper couponUserMapper;

    private Column[] result = new Column[]{Column.couId, Column.couName, Column.couDesc, Column.couTag,Column.couTotal,
                                            Column.couDays, Column.couStartTime, Column.couEndTime, Column.couGetCount,
                                            Column.couDiscount, Column.couMin};
    /**
     * 查询，空参数
     *
     * @param offset
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<TicCoupon> queryList(int offset, int limit, String sort, String order) {
        return queryLists(offset, limit, sort, order);
    }

    /**
     * 查询
     *
     * @param criteria 可扩展的条件
     * @param offset
     * @param limit
     * @param sort
     * @param order
     * @return
     */
	public List<TicCoupon> queryLists(int offset, int limit, String sort, String order) {        
        TicCouponExample example = new TicCouponExample();
    	TicCouponExample.Criteria criteria = example.createCriteria();
    	criteria.andCouTypeEqualTo(CouponConstant.TYPE_COMMON).andCouStatusEqualTo(CouponConstant.STATUS_NORMAL).andCouDeletedEqualTo(false);
    	example.setOrderByClause(sort+" "+order);
        PageHelper.startPage(offset, limit);
        List<TicCoupon> couponList = couponMapper.selectByExampleSelective(example, result);
        List<TicCoupon> coupons = new ArrayList<>();
        for (TicCoupon coupon : couponList) {
			int total=coupon.getCouTotal();	
			 if(total!=0) { 
				 coupons.add(coupon); 
			}
		}
        return coupons;
    }

    public int queryTotal() {
    	TicCouponExample example = new TicCouponExample();
        example.or().andCouTypeEqualTo(CouponConstant.TYPE_COMMON).andCouStatusEqualTo(CouponConstant.STATUS_NORMAL).andCouDeletedEqualTo(false);
        return (int) couponMapper.countByExample(example);
    }

    /**
     * 已登陆用户优惠券展示
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    public List<TicCoupon> queryAvailableList(Integer userId, int offset, int limit) {
        assert userId != null;
        // 过滤掉登录账号已经领取过的coupon
        TicCouponExample example = new TicCouponExample();
    	TicCouponExample.Criteria c = example.createCriteria();
    	TicCouponUserExample userExample = new TicCouponUserExample();
    	userExample.or().andCosUserIdEqualTo(userId);
        List<TicCouponUser> used = couponUserMapper.selectByExample(userExample);
        if(used!=null && !used.isEmpty()){
            c.andCouIdNotIn(used.stream().map(TicCouponUser::getCosCouponId).collect(Collectors.toList()));
        }
        c.andCouTypeEqualTo(CouponConstant.TYPE_COMMON).andCouStatusEqualTo(CouponConstant.STATUS_NORMAL).andCouDeletedEqualTo(false);
    	example.setOrderByClause("cou_add_time"+" "+"desc");
        PageHelper.startPage(offset, limit);
        List<TicCoupon> couponList = couponMapper.selectByExampleSelective(example, result);
        List<TicCoupon> coupons = new ArrayList<>();
        for (TicCoupon coupon : couponList) {
			int total=coupon.getCouTotal();	
			 if(total!=0) { 
				 coupons.add(coupon); 
			}
		}
        return coupons;
    }

    public List<TicCoupon> queryList(int offset, int limit) {
        return queryList(offset, limit, "cou_add_time", "desc");
    }

    public TicCoupon findById(Integer id) {
        return couponMapper.selectByPrimaryKey(id);
    }


    public TicCoupon findByCode(String code) {
    	TicCouponExample example = new TicCouponExample();
        example.or().andCouCodeEqualTo(code).andCouTypeEqualTo(CouponConstant.TYPE_CODE).andCouStatusEqualTo(CouponConstant.STATUS_NORMAL).andCouDeletedEqualTo(false);
        List<TicCoupon> couponList =  couponMapper.selectByExample(example);
        if(couponList.size() > 1){
            throw new RuntimeException("");
        }
        else if(couponList.size() == 0){
            return null;
        }
        else {
            return couponList.get(0);
        }
    }

    /**
     * 查询新用户注册优惠券
     *
     * @return
     */
    public List<TicCoupon> queryRegister() {
    	TicCouponExample example = new TicCouponExample();
        example.or().andCouTypeEqualTo(CouponConstant.TYPE_REGISTER).andCouStatusEqualTo(CouponConstant.STATUS_NORMAL).andCouDeletedEqualTo(false);
        return couponMapper.selectByExample(example);
    }

    public List<TicCoupon> querySelective(String name, Short type, Short status, Integer page, Integer limit, String sort, String order) {
    	TicCouponExample example = new TicCouponExample();
    	TicCouponExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andCouNameLike("%" + name + "%");
        }
        if (type != null) {
            criteria.andCouTypeEqualTo(type);
        }
        if (status != null) {
            criteria.andCouStatusEqualTo(status);
        }
        criteria.andCouDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return couponMapper.selectByExample(example);
    }

    public void add(TicCoupon coupon) {
        coupon.setCouAddTime(LocalDateTime.now());
        coupon.setCouUpdateTime(LocalDateTime.now());
        couponMapper.insertSelective(coupon);
    }

    public int updateById(TicCoupon coupon) {
        coupon.setCouUpdateTime(LocalDateTime.now());
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }

//    public void deleteById(Integer id) {
//        couponMapper.logicalDeleteByPrimaryKey(id);
//    }

    private String getRandomNum(Integer num) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        base += "0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成优惠码
     *
     * @return 可使用优惠码
     */
    public String generateCode() {
        String code = getRandomNum(8);
        while(findByCode(code) != null){
            code = getRandomNum(8);
        }
        return code;
    }

    /**
     * 查询过期的优惠券:
     * 注意：如果timeType=0, 即基于领取时间有效期的优惠券，则优惠券不会过期
     *
     * @return
     */
    public List<TicCoupon> queryExpired() {
    	TicCouponExample example = new TicCouponExample();
        Date date = LocalToDate(LocalDateTime.now());
        example.or().andCouStatusEqualTo(CouponConstant.STATUS_NORMAL).andCouTimeTypeEqualTo(CouponConstant.TIME_TYPE_TIME).andCouEndTimeLessThan(date).andCouDeletedEqualTo(false);
        return couponMapper.selectByExample(example);
    }

    /**
     * 条件查询优惠券
     * @param couponReq
     * @return
     * @author liuyu
     */
    public List<TicCoupon> list(TicCouponRequest couponReq){
        Integer page = couponReq.getPage();
        Integer limit = couponReq.getLimit();
        TicCouponExample example = new TicCouponExample();
        TicCouponExample.Criteria criteria = example.createCriteria();
        // 优惠券名称
        String couName = couponReq.getCouName();
        if(!org.springframework.util.StringUtils.isEmpty(couName)){
            criteria.andCouNameLike(couName);
        }
        // 赠送类型
        Short couType = couponReq.getCouType();
        if(!org.springframework.util.StringUtils.isEmpty(couType)){
            criteria.andCouTypeEqualTo(couType);
        }
        //优惠券状态
        Short couStatus = couponReq.getCouStatus();
        if(!org.springframework.util.StringUtils.isEmpty(couStatus)){
            criteria.andCouStatusEqualTo(couStatus);
        }
        //使用限制类型
        Short couGoodsType = couponReq.getCouGoodsType();
        if(!org.springframework.util.StringUtils.isEmpty(couGoodsType)){
            criteria.andCouGoodsTypeEqualTo(couGoodsType);
        }
        PageHelper.startPage(page, limit);
        return couponMapper.selectByExample(example);
    }

    /**
     * 创建优惠券
     * @param ticCoupon
     * @return
     * @author liuyu
     */
    public int create(TicCoupon ticCoupon){
        ticCoupon.setCouAddTime(LocalDateTime.now());
        ticCoupon.setCouUpdateTime(LocalDateTime.now());
        return couponMapper.insertSelective(ticCoupon);
    }

    /**
     * 修改优惠券规则
     * @param ticCoupon
     * @return
     * @author liuyu
     */
    public int update(TicCoupon ticCoupon){
        if(org.springframework.util.StringUtils.isEmpty(ticCoupon.getCouId())){
            return 0;
        }
        ticCoupon.setCouUpdateTime(LocalDateTime.now());
        return couponMapper.updateByPrimaryKeySelective(ticCoupon);
    }

    /**
     * 逻辑删除优惠券信息
     * @param couId
     * @return
     * @author liuyu
     */
    public int delete(Integer couId){
        if(org.springframework.util.StringUtils.isEmpty(couId)){
            return 0;
        }
        TicCoupon ticCoupon = new TicCoupon();
        ticCoupon.setCouId(couId);
        ticCoupon.setCouUpdateTime(LocalDateTime.now());
        ticCoupon.setCouDeleted(true);
        return couponMapper.updateByPrimaryKeySelective(ticCoupon);
    }
}
