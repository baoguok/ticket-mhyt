package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicCategoryMapper;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicCategoryExample;
import org.linlinjava.litemall.db.request.TicCateRequest;
import org.linlinjava.litemall.db.response.TicCateResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

@Service
public class TicCategoryService {
	@Resource
	private TicCategoryMapper categoryMapper;

	public List<TicCategory> getCategory(String sort, String order) {
		TicCategoryExample example = new TicCategoryExample();
		TicCategoryExample.Criteria c = example.createCriteria();
		c.andCatDeletedEqualTo(false);
		example.setOrderByClause(sort + " " + order);
		return categoryMapper.selectByExample(example);
	}

	/**
	 * 人工窗口查询门票类目
	 * @return
	 */
	public List<TicCateResponse> WinCateList(){
		return categoryMapper.WinGoodList();
	}

	/**
	 * 自主取票机查询门票类目
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<TicCategory> SelfHelpList(Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("page", (page-1) * limit);
		map.put("limit", limit);
		return categoryMapper.SelfHelpList(map);
	}
	
	
	/**
	 * 条件查询门票类目信息
	 * 
	 * @param cateReq
	 * @return
	 * @author liuyu
	 */
	public List<TicCategory> list(TicCateRequest cateReq) {
		Integer page = cateReq.getPage();
		Integer limit = cateReq.getLimit();
		PageHelper.startPage(page,limit);
		return categoryMapper.selectCate(cateReq);
	}

	/**
	 * 单个查询门票类目信息
	 * 
	 * @param catId
	 * @return
	 * @author liuyu
	 */
	public TicCategory query(Integer catId) {
		if (StringUtils.isEmpty(catId)) {
			return null;
		}
		return categoryMapper.selectByPrimaryKey(catId);
	}

	/**
	 * 创建门票类目
	 * 
	 * @param ticCate
	 * @return
	 * @author liuyu
	 */
	public int create(TicCategory ticCate) {
		Date date = LocalToDate(LocalDateTime.now());
		ticCate.setCatAddTime(date);
		ticCate.setCatUpdateTime(date);
		return categoryMapper.insertSelective(ticCate);
	}

	/**
	 * 修改门票类目
	 * 
	 * @param ticCate
	 * @return
	 * @author liuyu
	 */
	public int update(TicCategory ticCate) {
		Date date = LocalToDate(LocalDateTime.now());
		if (StringUtils.isEmpty(ticCate.getCatId())) {
			return 0;
		}
		ticCate.setCatUpdateTime(date);
		return categoryMapper.updateByPrimaryKeySelective(ticCate);
	}

	/**
	 * 逻辑删除门票类目
	 * 
	 * @param ticCateId
	 * @return
	 * @author liuyu
	 */
	public int delete(Integer ticCateId) {
		Date date = LocalToDate(LocalDateTime.now());
		if (StringUtils.isEmpty(ticCateId)) {
			return 0;
		}
		TicCategory ticCate = new TicCategory();
		ticCate.setCatId(ticCateId);
		ticCate.setCatUpdateTime(date);
		ticCate.setCatDeleted(true);
		return categoryMapper.updateByPrimaryKeySelective(ticCate);
	}

}
