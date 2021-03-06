package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.domain.TicStatistics;
import org.linlinjava.litemall.db.domain.TicStatisticsExample;
import org.linlinjava.litemall.db.request.TicStaticRequest;
import org.linlinjava.litemall.db.request.TicTouristRequest;
import org.linlinjava.litemall.db.response.TicOrderNumResponse;

public interface TicStatisticsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int countByExample(TicStatisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int deleteByExample(TicStatisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer staId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int insert(TicStatistics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int insertSelective(TicStatistics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    List<TicStatistics> selectByExample(TicStatisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    TicStatistics selectByPrimaryKey(Integer staId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TicStatistics record, @Param("example") TicStatisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TicStatistics record, @Param("example") TicStatisticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TicStatistics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_statistics
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TicStatistics record);

    /**
     * 批量修改
     * @param sta
     * @return
     */
    int updateBatch(List<TicStatistics> sta);

    /**
     * 售票
     * @param req
     * @return
     */
    int soldNum (TicStaticRequest req);

    /**
     * 退票
     * @param req
     * @return
     */
    int retiredNum (TicStaticRequest req);

    /**
     *
     * @return
     */
    List<TicOrderNumResponse> queryNum(TicStaticRequest staticRequest);
}