package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicAdMapper;
import org.linlinjava.litemall.db.domain.TicAd;
import org.linlinjava.litemall.db.domain.TicAdExample;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.domain.TicOrderExample;
import org.linlinjava.litemall.db.request.TicAdRequest;
import org.linlinjava.litemall.db.response.TicAdResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

@Service
public class TicAdService {
    @Resource
    private TicAdMapper adMapper;

    public List<TicAd> queryIndex() {
    	TicAdExample example = new TicAdExample();
        example.or().andAdPositionEqualTo((byte) 1).andAdDeletedEqualTo(false).andAdEnabledEqualTo(true);
        return adMapper.selectByExample(example);
    }
    
    public int countIndex() {
    	TicAdExample example = new TicAdExample();
        example.or().andAdDeletedEqualTo(false).andAdEnabledEqualTo(true);
        return adMapper.countByExample(example);
    }
    
    
    public List<TicAd> querySelective(Integer showTab, Integer page, Integer limit, String sort, String order) {
    	TicAdExample example = new TicAdExample();
    	TicAdExample.Criteria criteria = example.createCriteria();
        if (showTab != 0) {
           criteria.andAdCatIdEqualTo(showTab);
        }
        criteria.andAdDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return adMapper.selectByExample(example);
    }

	public int countBycatId(Integer adcId) {
		TicAdExample example = new TicAdExample();
    	TicAdExample.Criteria criteria = example.createCriteria();
    	if(adcId != 0) {
    	   criteria.andAdCatIdEqualTo(adcId);
    	}
    	criteria.andAdDeletedEqualTo(false);
		return 	(int)adMapper.countByExample(example);	
	}

    /**
     * 条件查询景点图片
     * @param adRequest
     * @return
     */
    public List<TicAdResponse> list(TicAdRequest adRequest) {
        Integer limit = adRequest.getLimit();
        Integer page = adRequest.getPage();
        if(!StringUtils.isEmpty(page) && !StringUtils.isEmpty(limit)){
            page = (page -1) * limit;
            adRequest.setPage(page);
        }
        return adMapper.selectAd(adRequest);
    }

    /**
     * 创建景点图片
     * @param ad
     * @return
     * @author liuyu
     */
    public int create(TicAd ad){
        ad.setAdAddTime(LocalDateTime.now());
        ad.setAdUpdateTime(LocalDateTime.now());
        return adMapper.insertSelective(ad);
    }

    /**
     * 修改
     * @param ad
     * @return
     * @author liuyu
     */
    public int updateById(TicAd ad) {
        ad.setAdUpdateTime(LocalDateTime.now());
        return adMapper.updateByPrimaryKeySelective(ad);
    }

    /**
     * 物理删除门票信息
     * @param adId
     * @return
     * @author liuyu
     */
    public int delete(Integer adId){
        if(StringUtils.isEmpty(adId)){
            return 0;
        }
        return adMapper.deleteByPrimaryKey(adId);
    }

}
