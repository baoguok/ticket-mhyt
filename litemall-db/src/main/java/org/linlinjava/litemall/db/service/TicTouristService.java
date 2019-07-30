package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.TicTouristMapper;
import org.linlinjava.litemall.db.domain.TicTourist;
import org.linlinjava.litemall.db.request.TicTouristRequest;
import org.linlinjava.litemall.db.response.TicTouristResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

@Service
public class TicTouristService {
    @Resource
    private TicTouristMapper touristMapper;

    /**
     * 查询游客信息信息(多表查询)
     * @param touristRequest
     * @return
     * @author liuyu
     */
    public List<TicTouristResponse> list(TicTouristRequest touristRequest){
        Integer page = touristRequest.getPage();
        Integer limit = touristRequest.getLimit();
        if(!StringUtils.isEmpty(page) && !StringUtils.isEmpty(limit)) {
            page = (page - 1) * limit;
            touristRequest.setPage(page);
        }
        return touristMapper.listTourist(touristRequest);
    }

    /**
     * 逻辑删除门票信息
     * @param touId
     * @return
     * @author liuyu
     */
    public int delete(Integer touId){
        Date date = LocalToDate(LocalDateTime.now());
        if(StringUtils.isEmpty(touId)){
            return 0;
        }
        TicTourist tourist = new TicTourist();
        tourist.setTouId(touId);
        tourist.setTouUpdateTime(date);
        tourist.setTouDeleted(true);
        return touristMapper.updateByPrimaryKeySelective(tourist);
    }

    /**
     * 单个查询游客信息
     * @param touUserId
     * @return
     */
    public TicTourist query(Integer touUserId){
        if(StringUtils.isEmpty(touUserId)){
            return null;
        }
        return touristMapper.query(touUserId);
    }
}
