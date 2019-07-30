package org.linlinjava.litemall.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.GoodsStock;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.domain.TicGoodsExample;
import org.linlinjava.litemall.db.request.TicGoodRequest;
import org.linlinjava.litemall.db.response.TicGoodResponse;

public interface TicGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int countByExample(TicGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int deleteByExample(TicGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer gooId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int insert(TicGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int insertSelective(TicGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
//    List<TicGoods> selectByExampleWithBLOBs(TicGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    List<TicGoods> selectByExample(TicGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    TicGoods selectByPrimaryKey(Integer gooId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TicGoods record, @Param("example") TicGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
//    int updateByExampleWithBLOBs(@Param("record") TicGoods record, @Param("example") TicGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TicGoods record, @Param("example") TicGoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TicGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
//    int updateByPrimaryKeyWithBLOBs(TicGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_goods
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TicGoods record);
    /**
     * 条件查询（排序）
     * @param goodRequest
     * @return
     */
    List<TicGoodResponse> selectGood(TicGoodRequest goodRequest);
    
    
    /**
     * 查询剩余库存
     * @param map
     * @return
     */
	List<GoodsStock> GetStock(@SuppressWarnings("rawtypes") Map map);

    /**
     * 查询单门票剩余库存
     * @param map
     * @return
     */
    GoodsStock getOneStock(@SuppressWarnings("rawtypes") Map map);
}