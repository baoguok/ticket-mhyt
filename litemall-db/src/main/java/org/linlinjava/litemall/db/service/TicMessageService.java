package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicMessageMapper;
import org.linlinjava.litemall.db.domain.TicMessage;
import org.linlinjava.litemall.db.domain.TicMessageExample;
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
public class TicMessageService {
    @Resource
    private TicMessageMapper messageMapper;

//    public List<TicMessage> queryList(int offset, int limit) {
//        return queryList(offset, limit, "mes_sort_order", "asc");
//    }

//    public List<TicMessage> queryList(int offset, int limit, String sort, String order) {
//        TicMessageExample example = new TicMessageExample();
//        example.or().andMesDeletedEqualTo(false);
//        example.setOrderByClause(sort + " " + order);
//        PageHelper.startPage(offset, limit);
//        return messageMapper.selectByExampleWithBLOBs(example);
//    }

//    public int queryTotal() {
//        TicMessageExample example = new TicMessageExample();
//        example.or().andMesDeletedEqualTo(false);
//        return (int) messageMapper.countByExample(example);
//    }


//    public List<TicMessage> queryRelatedList(Integer id, int offset, int limit) {
//        TicMessageExample example = new TicMessageExample();
//        example.or().andMesIdEqualTo(id).andMesDeletedEqualTo(false);
//        List<TicMessage> topics = messageMapper.selectByExample(example);
//        if (topics.size() == 0) {
//            return queryList(offset, limit, "add_time", "desc");
//        }
//        TicMessage topic = topics.get(0);
//
//        example = new TicMessageExample();
//        example.or().andMesIdNotEqualTo(topic.getMesId()).andMesDeletedEqualTo(false);
//        PageHelper.startPage(offset, limit);
//        List<TicMessage> relateds = messageMapper.selectByExampleWithBLOBs(example);
//        if (relateds.size() != 0) {
//            return relateds;
//        }
//
//        return queryList(offset, limit, "add_time", "desc");
//    }

//    public List<TicMessage> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
//        TicMessageExample example = new TicMessageExample();
//        TicMessageExample.Criteria criteria = example.createCriteria();
//
//        criteria.andMesDeletedEqualTo(false);
//
//        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
//            example.setOrderByClause(sort + " " + order);
//        }
//
//        PageHelper.startPage(page, limit);
//        return messageMapper.selectByExampleWithBLOBs(example);
//    }

//    public int updateById(TicMessage topic) {
//        Date date = LocalToDate(LocalDateTime.now());
//        topic.setMesUpdateTime(date);
//        TicMessageExample example = new TicMessageExample();
//        example.or().andMesIdEqualTo(topic.getMesId());
//        return messageMapper.updateByExampleSelective(topic, example);
//    }

//    public void deleteById(Integer id) {
//    	messageMapper.logicalDeleteByPrimaryKey(id);
//    }

//    /**
//     * 创建景区信息
//     * @param topic
//     */
//    public void add(TicMessage topic) {
//        Date date = LocalToDate(LocalDateTime.now());
//        topic.setMesAddTime(date);
//        topic.setMesUpdateTime(date);
//        messageMapper.insertSelective(topic);
//    }

    /**
     * 条件查询
     * @param page
     * @param limit
     * @return
     * @author liuyu
     */
    public List<TicMessage> list(Integer page,Integer limit){
        TicMessageExample example = new TicMessageExample();
        example.or().andMesDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        return messageMapper.selectByExample(example);
    }

    /**
     * 创建景区信息
     * @param ticMessage
     * @return
     * @author liuyu
     */
    public int create(TicMessage ticMessage){
        Date date = LocalToDate(LocalDateTime.now());
        ticMessage.setMesAddTime(date);
        ticMessage.setMesUpdateTime(date);
        return messageMapper.insertSelective(ticMessage);
    }

    /**
     * 修改景区信息
     * @param ticMessage
     * @return
     * @author liuyu
     */
    public int update(TicMessage ticMessage){
        Date date = LocalToDate(LocalDateTime.now());
        if(StringUtils.isEmpty(ticMessage.getMesId())){
            return 0;
        }
        ticMessage.setMesUpdateTime(date);
        return messageMapper.updateByPrimaryKeySelective(ticMessage);
    }

    /**
     * 逻辑删除景区信息
     * @param ticMessageId
     * @return
     * @author liuyu
     */
    public int delete(Integer ticMessageId){
        Date date = LocalToDate(LocalDateTime.now());
        if(StringUtils.isEmpty(ticMessageId)){
            return 0;
        }
        TicMessage ticMessage = new TicMessage();
        ticMessage.setMesId(ticMessageId);
        ticMessage.setMesUpdateTime(date);
        ticMessage.setMesDeleted(true);
        return messageMapper.updateByPrimaryKeySelective(ticMessage);
    }
}
