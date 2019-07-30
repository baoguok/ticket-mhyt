package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicGateViewMapper;
import org.linlinjava.litemall.db.dao.TicGoodsMapper;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.request.TicGoodRequest;
import org.linlinjava.litemall.db.response.TicGoodResponse;
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
public class TicGateViewService {
	@Resource
	private TicGateViewMapper gateViewMapper;

	/**
	 * 条件查询
	 * @return
	 * @author 刘宇
	 */
	public List<TicGateView> list(Integer gateViewId, String gateViewName,String gateNum, Integer page, Integer size) {
		TicGateViewExample example = new TicGateViewExample();
		TicGateViewExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(gateViewId)){
			criteria.andGateViewIdEqualTo(gateViewId);
		}
		if(!StringUtils.isEmpty(gateViewName)){
			criteria.andGateViewNameLike(gateViewName);
		}
		if(!StringUtils.isEmpty(gateNum)){
			criteria.andGateNumLike(gateNum);
		}
		criteria.andGateDeletedEqualTo(false);
		if(!StringUtils.isEmpty(page) || !StringUtils.isEmpty(size)){
			PageHelper.startPage(page,size);
		}
		return gateViewMapper.selectByExample(example);
	}


	/**
	 * 修改信息
	 * @param gateView
	 * @return
	 * @author liuyu
	 */
	public int update(TicGateView gateView){
		if(StringUtils.isEmpty(gateView.getGateId())){
			return 0;
		}
		gateView.setGateUpdateTime(LocalDateTime.now());
		return gateViewMapper.updateByPrimaryKeySelective(gateView);
	}

	/**
	 * 添加景点闸机信息
	 * @param gateView
	 * @return
	 */
	public int add(TicGateView gateView){
		gateView.setGateAddTime(LocalDateTime.now());
		gateView.setGateUpdateTime(LocalDateTime.now());
		gateView.setGateDeleted(false);
		return gateViewMapper.insertSelective(gateView);
	}

	/**
	 * 逻辑删除
	 * @param id
	 */
	public int deleteById(Integer id) {
		return gateViewMapper.logicalDeleteByPrimaryKey(id);
	}

	/**
	 * 模糊查询
	 */
	public TicGateView selectLike(String gateNum){
		if(StringUtils.isEmpty(gateNum)){
			return null;
		}
		return gateViewMapper.selectLike(gateNum);
	}

}
