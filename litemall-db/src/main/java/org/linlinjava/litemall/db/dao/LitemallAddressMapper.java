package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallAddress;
import org.linlinjava.litemall.db.domain.LitemallAddressExample;

public interface LitemallAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int countByExample(LitemallAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int deleteByExample(LitemallAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int insert(LitemallAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int insertSelective(LitemallAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    List<LitemallAddress> selectByExample(LitemallAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    LitemallAddress selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") LitemallAddress record, @Param("example") LitemallAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") LitemallAddress record, @Param("example") LitemallAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LitemallAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_address
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LitemallAddress record);
}