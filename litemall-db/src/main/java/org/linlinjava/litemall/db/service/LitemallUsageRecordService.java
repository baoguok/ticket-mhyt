package org.linlinjava.litemall.db.service;

import java.util.List;

import javax.annotation.Resource;
import org.linlinjava.litemall.db.dao.LitemallUsageRecordMapper;
import org.linlinjava.litemall.db.domain.LitemallUsageRecord;
import org.linlinjava.litemall.db.domain.LitemallUsageRecordExample;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 使用记录
 * @author Administrator
 *
 */
@Service
public class LitemallUsageRecordService {
	@Resource
	private LitemallUsageRecordMapper usagerecordMapper;
	
	/**
	 * 分页条件查询主柜
	 * @param
	 * @param page
	 * @param size
	 * @return
	 */
    public List<LitemallUsageRecord> querySelective(Integer status,Integer page, Integer size) {
    	LitemallUsageRecordExample example = new LitemallUsageRecordExample();
    	LitemallUsageRecordExample.Criteria criteria =  example.createCriteria();
    	if(status != -1){
    		criteria.andStatusEqualTo(status);
    	}
    	criteria.andDeletedEqualTo(false);
        return usagerecordMapper.selectByExample(example);
    }

	/**
	 * 查询订单信息 (by userId,status)
	 * @param userId
	 * @param status
	 * @return
	 */
    public List<LitemallUsageRecord> listUsageRecord(Integer userId, Integer status){
		LitemallUsageRecordExample example = new LitemallUsageRecordExample();
		//声明 “条件约束”
		LitemallUsageRecordExample.Criteria criteria =  example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andStatusEqualTo(status);
		criteria.andDeletedEqualTo(false);
		return usagerecordMapper.selectByExample(example);
	}

	/**
	 * 查询订单详情
	 * @param id
	 * @return
	 */
	public LitemallUsageRecord queryUsageRecord(Integer id){
    	if(StringUtils.isEmpty(id)){
    		return null;
		}
		return usagerecordMapper.queryUsage(id);
	}

}
