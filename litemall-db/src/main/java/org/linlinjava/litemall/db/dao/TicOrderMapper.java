package org.linlinjava.litemall.db.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.TicOrder;
import org.linlinjava.litemall.db.domain.TicOrderExample;
import org.linlinjava.litemall.db.domain.TicOrderGoods;
import org.linlinjava.litemall.db.request.TicOrderRequest;
import org.linlinjava.litemall.db.response.TicOrderNumResponse;
import org.linlinjava.litemall.db.response.TicOrderResponse;

public interface TicOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int countByExample(TicOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int deleteByExample(TicOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer ordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int insert(TicOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int insertSelective(TicOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    List<TicOrder> selectByExample(TicOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    TicOrder selectByPrimaryKey(Integer ordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TicOrder record, @Param("example") TicOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TicOrder record, @Param("example") TicOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TicOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TicOrder record);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    TicOrder selectOneByExample(TicOrderExample example);
    
    int logicalDeleteByPrimaryKey(Integer id);
    /**
     * 订单集合
     * @param orderRequest
     * @return
     */
    List<TicOrderResponse> listOrder(TicOrderRequest orderRequest);
    
    /**
     * 订单详情
     * @param ordId
     * @return
     */
    TicOrderResponse details(Integer ordId);
    
    /**
     * 自助取票机取票
     * @param map
     * @return
     */
    List<TicOrderResponse> UserDetails(@SuppressWarnings("rawtypes") Map map);
    
    /**
     * 修改已过期订单
     */

	void BeOverdue();

    /**
     * 修改订单状态(by 订单编号)
     * @param orderSn
     * @return
     */
    int updateOrderStatus(String orderSn);

    /**
     * 查询订单以及订单门票信息(可用于售票)
     * @param order
     * @return
     */
    List<TicOrderResponse> orderGoodList(TicOrder order);


    /*当日售票信息*/
    List<TicOrderNumResponse> ticNumber(Map map);
    /*所有售票信息*/
    List<TicOrderNumResponse> ticNumberAll(Map map);

//    List<TicOrderGoods> queryNum(TicOrderGoods org);
}