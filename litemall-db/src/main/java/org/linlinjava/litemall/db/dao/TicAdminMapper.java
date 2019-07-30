package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.TicAdmin;
import org.linlinjava.litemall.db.domain.TicAdminExample;

public interface TicAdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int countByExample(TicAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int deleteByExample(TicAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer admId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int insert(TicAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int insertSelective(TicAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    List<TicAdmin> selectByExample(TicAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    TicAdmin selectByPrimaryKey(Integer admId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TicAdmin record, @Param("example") TicAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TicAdmin record, @Param("example") TicAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TicAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_admin
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TicAdmin record);
}