package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicCommentMapper;
import org.linlinjava.litemall.db.domain.TicComment;
import org.linlinjava.litemall.db.domain.TicCommentExample;
import org.linlinjava.litemall.db.request.TicCommentRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicCommentService {
    @Resource
    private TicCommentMapper commentMapper;

    public List<TicComment> queryList(int offset, int limit) {
    	TicCommentExample example = new TicCommentExample();
        example.setOrderByClause(TicComment.Column.comAddTime.desc());
        example.or().andComDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);
    }

    public List<TicComment> query(Integer showType, Integer offset, Integer limit) {
    	TicCommentExample example = new TicCommentExample();
        example.setOrderByClause(TicComment.Column.comAddTime.desc());
        if (showType == 0) {
            example.or().andComDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andComHasPictureEqualTo(true).andComDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);
    }

    public int count(Integer showType) {
    	TicCommentExample example = new TicCommentExample();
        if (showType == 0) {
            example.or().andComDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andComHasPictureEqualTo(true).andComDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        return (int) commentMapper.countByExample(example);
    }

    public int countUsers() {
    	TicCommentExample example = new TicCommentExample();
        example.or().andComDeletedEqualTo(false);
        return (int) commentMapper.countUserByExample(example);
    }
    
    public int save(TicComment comment) {
        comment.setComAddTime(LocalDateTime.now());
        comment.setComUpdateTime(LocalDateTime.now());
        return commentMapper.insertSelective(comment);
    }

    public List<TicComment> querySelective(String userId, Integer page, Integer size, String sort, String order) {
    	TicCommentExample example = new TicCommentExample();
    	TicCommentExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andComUserIdEqualTo(Integer.valueOf(userId));
        }
        criteria.andComDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return commentMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        commentMapper.logicalDeleteByPrimaryKey(id);
    }

    public String queryReply(Integer id) {
    	TicCommentExample example = new TicCommentExample();
        example.or().andComOrderIdEqualTo(id);
//        List<TicComment> commentReply = commentMapper.selectByExampleSelective(example, TicComment.Column.comContent);
        // 目前业务只支持回复一次
//        if (commentReply.size() == 1) {
//            return commentReply.get(0).getComContent();
//        }
        return null;
    }

    public TicComment findById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 条件查询评论信息
     * @param comReq
     * @return
     * @author liuyu
     */
    public List<TicComment> list(TicCommentRequest comReq){
        TicCommentExample example = new TicCommentExample();
        TicCommentExample.Criteria criteria = example.createCriteria();
        // 订单Id
        if(!StringUtils.isEmpty(comReq.getComOrderId())) {
            criteria.andComOrderIdEqualTo(comReq.getComOrderId());
        }
        // 用户Id
        if(!StringUtils.isEmpty(comReq.getComUserId())) {
            criteria.andComUserIdEqualTo(comReq.getComUserId());
        }
        // 是否推荐
        if(!StringUtils.isEmpty(comReq.getComIsgood())) {
            criteria.andComIsgoodEqualTo(comReq.getComIsgood());
        }
        // 是否有图片
        if(!StringUtils.isEmpty(comReq.getComHasPicture())) {
            criteria.andComHasPictureEqualTo(comReq.getComHasPicture());
        }
        criteria.andComDeletedEqualTo(false);
        Integer page = comReq.getPage();
        Integer limit = comReq.getLimit();
        PageHelper.startPage(page, limit);
        return commentMapper.selectByExample(example);
    }

    /**
     * 创建评论信息
     * @param ticComment
     * @return
     * @author liuyu
     */
    public int create(TicComment ticComment){
        ticComment.setComAddTime(LocalDateTime.now());
        ticComment.setComUpdateTime(LocalDateTime.now());
        return commentMapper.insertSelective(ticComment);
    }

    /**
     * 修改评论信息
     * @param ticComment
     * @return
     * @author liuyu
     */
    public int update(TicComment ticComment){
        if(StringUtils.isEmpty(ticComment.getComId())){
            return 0;
        }
        ticComment.setComUpdateTime(LocalDateTime.now());
        return commentMapper.updateByPrimaryKeySelective(ticComment);
    }

    /**
     * 逻辑删除订单信息
     * @param ticCommentId
     * @return
     * @author liuyu
     */
    public int delete(Integer ticCommentId){
        if(StringUtils.isEmpty(ticCommentId)){
            return 0;
        }
        TicComment ticComment = new TicComment();
        ticComment.setComId(ticCommentId);
        ticComment.setComUpdateTime(LocalDateTime.now());
        ticComment.setComDeleted(true);
        return commentMapper.updateByPrimaryKeySelective(ticComment);
    }
}
