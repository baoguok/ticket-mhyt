package org.linlinjava.litemall.db.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicFeedbackMapper;
import org.linlinjava.litemall.db.dao.TicGoodsMapper;
import org.linlinjava.litemall.db.domain.TicFeedback;
import org.linlinjava.litemall.db.domain.TicFeedbackExample;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.domain.TicGoodsExample;
import org.linlinjava.litemall.db.request.TicFeedBackRequest;
import org.linlinjava.litemall.db.request.TicGoodRequest;
import org.linlinjava.litemall.db.response.TicFeedBackResponse;
import org.linlinjava.litemall.db.response.TicGoodResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

/**
 * 意见反馈
 */
@Service
public class TicFeedBackService {
    @Resource
    private TicFeedbackMapper feedbackMapper;

	/**
	 * 条件查询反馈
	 * @param feedbackReq
	 * @return
	 * @author liuyu
	 */
	public List<TicFeedback> list(TicFeedBackRequest feedbackReq){
		Integer page = feedbackReq.getPage();
		Integer limit = feedbackReq.getLimit();
		TicFeedbackExample example = new TicFeedbackExample();
		TicFeedbackExample.Criteria criteria = example.createCriteria();
		// 用户名称
		String feeUsername = feedbackReq.getFeeUsername();
		if(!StringUtils.isEmpty(feeUsername)){
			criteria.andFeeUsernameLike(feeUsername);
		}
		// 反馈Id
		Integer feeId = feedbackReq.getFeeId();
		if(!StringUtils.isEmpty(feeId)){
			criteria.andFeeIdEqualTo(feeId);
		}
		PageHelper.startPage(page, limit);
		return feedbackMapper.selectByExample(example);
	}

	/**
	 * 逻辑删除反馈
	 * @param backId
	 * @return
	 * @author liuyu
	 */
	public int delete(Integer backId){
		Date date = LocalToDate(LocalDateTime.now());
		if(StringUtils.isEmpty(backId)){
			return 0;
		}
		TicFeedback feedback = new TicFeedback();
		feedback.setFeeId(backId);
		feedback.setFeeUpdateTime(date);
		feedback.setFeeDeleted(true);
		return feedbackMapper.updateByPrimaryKeySelective(feedback);
	}
}
