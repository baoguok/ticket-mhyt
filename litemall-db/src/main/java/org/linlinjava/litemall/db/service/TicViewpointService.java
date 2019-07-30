package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.TicViewpointMapper;

import org.linlinjava.litemall.db.domain.TicViewpoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

/**
 * 景点信息表
 * @author liuyu
 */
@Service
public class TicViewpointService {
    @Resource
    private TicViewpointMapper viewpointMapper;

    /**
     * 条件查询景点信息
     * @param vieTitle
     * @param vieSubtitle
     * @param page
     * @param limit
     * @return
     * @author liuyu
     */
    public List<TicViewpoint> list(String vieTitle, String vieSubtitle, Integer page, Integer limit){
    	if(!StringUtils.isEmpty(page) && !StringUtils.isEmpty(!StringUtils.isEmpty(page))) {
    		page = (page - 1) * limit;
    	}
        Map<String,Object> map =new HashMap<>();
        map.put("page",page);
        map.put("limit",limit);
        map.put("vieTitle", vieTitle);
        map.put("vieSubtitle", vieSubtitle);
        return viewpointMapper.selectVie(map);
    }

    public int queryCount(){
        return viewpointMapper.selectVieCount();
    }

    /**
     * 创建景点信息
     * @param ticViewpoint
     * @return
     * @author liuyu
     */
    public int create(TicViewpoint ticViewpoint){
        Date date = LocalToDate(LocalDateTime.now());
        ticViewpoint.setVieAddTime(date);
        ticViewpoint.setVieUpdateTime(date);
        return viewpointMapper.insertSelective(ticViewpoint);
    }

    /**
     * 修改景点信息
     * @param ticViewpoint
     * @return
     * @author liuyu
     */
    public int update(TicViewpoint ticViewpoint){
        if(StringUtils.isEmpty(ticViewpoint.getVieId())){
            return 0;
        }
        Date date = LocalToDate(LocalDateTime.now());
        ticViewpoint.setVieUpdateTime(date);
        return viewpointMapper.updateByPrimaryKeySelective(ticViewpoint);
    }

    /**
     * 逻辑删除景点信息
     * @param viePointId
     * @return
     * @author liuyu
     */
    public int delete(Integer viePointId){
        Date date = LocalToDate(LocalDateTime.now());
        if(StringUtils.isEmpty(viePointId)){
            return 0;
        }
        TicViewpoint ticViewpoint = new TicViewpoint();
        ticViewpoint.setVieId(viePointId);
        ticViewpoint.setVieUpdateTime(date);
        ticViewpoint.setVieDeleted(true);
        return viewpointMapper.updateByPrimaryKeySelective(ticViewpoint);
    }

    /**
     * 单个查询
     * @author liuyu
     */
    public TicViewpoint query(Integer vieId){
        if (StringUtils.isEmpty(vieId)) {
            return null;
        }
        return viewpointMapper.selectByPrimaryKey(vieId);
    }

    /**
     * 修改阅读记录
     * @param vieId
     * @return
     * @author liuyu
     */
    public int updateRead(Integer vieId){
        if(StringUtils.isEmpty(vieId)) {
            return 0;
        }
        return viewpointMapper.updateRead(vieId);
    }
}
