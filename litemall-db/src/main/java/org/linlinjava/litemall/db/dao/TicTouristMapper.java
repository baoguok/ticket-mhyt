package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.TicTourist;
import org.linlinjava.litemall.db.domain.TicTouristExample;
import org.linlinjava.litemall.db.request.TicTouristRequest;
import org.linlinjava.litemall.db.response.TicTouristResponse;

public interface TicTouristMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int countByExample(TicTouristExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int deleteByExample(TicTouristExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer touId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int insert(TicTourist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int insertSelective(TicTourist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    List<TicTourist> selectByExample(TicTouristExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    TicTourist selectByPrimaryKey(Integer touId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TicTourist record, @Param("example") TicTouristExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TicTourist record, @Param("example") TicTouristExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TicTourist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TicTourist record);

    /**
     * 条件查询
     * @param touristRequest
     * @return
     */
    List<TicTouristResponse> listTourist(TicTouristRequest touristRequest);

    /**
     * 单个查询
     * @param touUserId
     * @return
     */
    TicTourist query(Integer touUserId);
}