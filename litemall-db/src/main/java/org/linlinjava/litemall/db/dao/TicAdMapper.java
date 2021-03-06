package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.TicAd;
import org.linlinjava.litemall.db.domain.TicAdExample;
import org.linlinjava.litemall.db.request.TicAdRequest;
import org.linlinjava.litemall.db.response.TicAdResponse;

public interface TicAdMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int countByExample(TicAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int deleteByExample(TicAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer adId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int insert(TicAd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int insertSelective(TicAd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    List<TicAd> selectByExample(TicAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    TicAd selectByPrimaryKey(Integer adId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TicAd record, @Param("example") TicAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TicAd record, @Param("example") TicAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TicAd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_ad
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TicAd record);
    /**
     * 条件查询（排序）
     * @param
     * @return
     * @author liuyu
     */
    List<TicAdResponse> selectAd(TicAdRequest adRequest);
}