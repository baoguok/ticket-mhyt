package org.linlinjava.litemall.db.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.linlinjava.litemall.db.dao.TicAdCategoryMapper;
import org.linlinjava.litemall.db.domain.TicAdCategory;
import org.linlinjava.litemall.db.domain.TicAdCategoryExample;
import org.linlinjava.litemall.db.request.TicAdCateRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

@Service
public class TicAdCategoryService {
    @Resource
	private TicAdCategoryMapper  adCategoryMapper;
       
    public TicAdCategory getCategory(Integer catId){
    	TicAdCategoryExample example = new TicAdCategoryExample();
    	TicAdCategoryExample.Criteria c = example.createCriteria();
    	c.andAdcIdEqualTo(catId).andAdcDeletedEqualTo(false);
    	return adCategoryMapper.selectByPrimaryKey(catId);
    }

	public List<TicAdCategory> getAllCategory() {
		TicAdCategoryExample example = new TicAdCategoryExample();
    	TicAdCategoryExample.Criteria c = example.createCriteria();
    	c.andAdcDeletedEqualTo(false);
		return adCategoryMapper.selectByExample(example);
	}

	/**
	 * 条件查询景区图片类目
	 * @param adCateReq
	 * @return
	 * @author liuyu
	 */
	public List<TicAdCategory> list(TicAdCateRequest adCateReq){
		Integer page = adCateReq.getPage();
		Integer limit = adCateReq.getLimit();
		if(!StringUtils.isEmpty(page) && !StringUtils.isEmpty(limit)) {
			page = (page - 1) * limit;
			adCateReq.setPage(page);
		}
		return adCategoryMapper.selectAdCate(adCateReq);
	}

	/**
	 * 创建景区图片类目
	 * @param ticAdCate
	 * @return
	 * @author liuyu
	 */
	public int create(TicAdCategory ticAdCate){
		Date date = LocalToDate(LocalDateTime.now());
		ticAdCate.setAdcAddTime(date);
		ticAdCate.setAdcUpdateTime(date);
		return adCategoryMapper.insertSelective(ticAdCate);
	}

	/**
	 * 修改门票信息
	 * @param ticAdCate
	 * @return
	 * @author liuyu
	 */
	public int update(TicAdCategory ticAdCate){
		if(StringUtils.isEmpty(ticAdCate.getAdcId())){
			return 0;
		}
		Date date = LocalToDate(LocalDateTime.now());
		ticAdCate.setAdcUpdateTime(date);
		return adCategoryMapper.updateByPrimaryKeySelective(ticAdCate);
	}

	/**
	 * 逻辑删除门票信息
	 * @param adCateId
	 * @return
	 * @author liuyu
	 */
	public int delete(Integer adCateId){
		Date date = LocalToDate(LocalDateTime.now());
		if(StringUtils.isEmpty(adCateId)){
			return 0;
		}
		TicAdCategory ticAdCate = new TicAdCategory();
		ticAdCate.setAdcId(adCateId);
		ticAdCate.setAdcUpdateTime(date);
		ticAdCate.setAdcDeleted(true);
		return adCategoryMapper.updateByPrimaryKeySelective(ticAdCate);
	}
}
