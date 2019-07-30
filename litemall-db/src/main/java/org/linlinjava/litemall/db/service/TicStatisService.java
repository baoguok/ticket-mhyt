package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.TicOrderGoodsMapper;
import org.linlinjava.litemall.db.dao.TicStatisticsMapper;
import org.linlinjava.litemall.db.domain.TicStatistics;
import org.linlinjava.litemall.db.domain.TicStatisticsExample;
import org.linlinjava.litemall.db.request.TicStaticRequest;
import org.linlinjava.litemall.db.response.TicOrderNumResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
  *  票务统计表
 * @author liuyu
 */
@Service
public class TicStatisService {
    @Resource
    private TicStatisticsMapper ticStatisMapper;

    @Resource
    private TicOrderGoodsMapper ticOrderGoodsMapper;

    /**
     * 添加信息
     * @param statistics
     * @return
     */
    public int add(TicStatistics statistics){
        if(StringUtils.isEmpty(statistics.getStaSelId())){
            return 0;
        }
        return ticStatisMapper.insert(statistics);
    }

    /**
     * 售票
     * @param request
     * @return
     */
    public int updateSold(TicStaticRequest request){
        Integer insert;
        // 查询记录
        TicStatisticsExample example = new TicStatisticsExample();
        TicStatisticsExample.Criteria criteria = example.createCriteria();
        criteria.andStaSelIdEqualTo(request.getStaSelId());
        List<TicStatistics> list = ticStatisMapper.selectByExample(example);
        if(StringUtils.isEmpty(list) || list.size() == 0){
            request.setStaTotal(request.getNum());
            request.setStaSold(request.getNum());
            request.setStaRetired(0);
            insert = ticStatisMapper.insert(request);
            if(insert == 0){
                return 0;
            }
        } else {
            insert = ticStatisMapper.soldNum(request);
        }
        return insert;
    }

    /**
     * 退票
     * @param request
     * @return
     */
    public int updateRetired(TicStaticRequest request){
        request.setBool(false);
        return ticStatisMapper.retiredNum(request);
    }

    /**
     *  统计信息
     * @param selId
     * @return
     * @author liuyu
     */
    public List<TicStatistics> list(Integer selId){
    	TicStatisticsExample example = new TicStatisticsExample();
    	TicStatisticsExample.Criteria criteria = example.createCriteria();
    	// 售票员Id(-1:线上)
    	if(!StringUtils.isEmpty(selId)) {
    		criteria.andStaSelIdEqualTo(selId);
    	}
        return ticStatisMapper.selectByExample(example);
    }

    /**
     * 每天凌晨12点30定时更新
     * 查询所有信息
     * 将总售票累加
     * 清理每日数据
     * @return
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public int updateStatis(){
        //查询所有
        TicStatisticsExample example = new TicStatisticsExample();
        List<TicStatistics> list = ticStatisMapper.selectByExample(example);
        if(StringUtils.isEmpty(list) && list.size() == 0){
            return 0;
        }
        //总售票累加
        for(TicStatistics sta: list){
            Integer staSold = sta.getStaSold();
            Integer staTotal = sta.getStaTotal();
            sta.setStaTotal(staSold+staTotal);
            sta.setStaRetired(0);
            sta.setStaSold(0);
        }
        //更新
        return ticStatisMapper.updateBatch(list);
    }

    /**
     * 查询售票信息
     * @param ticStaticRequest 售票员Id,订单状态
     * @return
     */
    public Integer queryTicNum(TicStaticRequest ticStaticRequest){
        return ticOrderGoodsMapper.queryNum(ticStaticRequest);
    }

    /**
     * 查询所有售票信息
     *
     */
    public Integer queryAll(TicStaticRequest ticStaticRequest){
        return ticOrderGoodsMapper.queryNumAll(ticStaticRequest);
    }
}
