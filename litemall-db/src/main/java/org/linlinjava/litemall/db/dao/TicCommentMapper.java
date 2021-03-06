package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.TicComment;
import org.linlinjava.litemall.db.domain.TicCommentExample;

public interface TicCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int countByExample(TicCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int countUserByExample(TicCommentExample example);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int deleteByExample(TicCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer comId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int insert(TicComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int insertSelective(TicComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    List<TicComment> selectByExample(TicCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    TicComment selectByPrimaryKey(Integer comId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TicComment record, @Param("example") TicCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TicComment record, @Param("example") TicCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TicComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_comment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TicComment record);
    
    int logicalDeleteByPrimaryKey(Integer id);
    
    
}