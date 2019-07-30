package org.linlinjava.litemall.db.domain;

import java.util.Date;

public class TicPermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_permission.per_id
     *
     * @mbggenerated
     */
    private Integer perId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_permission.per_role_id
     *
     * @mbggenerated
     */
    private Integer perRoleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_permission.per_permission
     *
     * @mbggenerated
     */
    private String perPermission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_permission.per_add_time
     *
     * @mbggenerated
     */
    private Date perAddTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_permission.per_update_time
     *
     * @mbggenerated
     */
    private Date perUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_permission.per_deleted
     *
     * @mbggenerated
     */
    private Boolean perDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_permission.per_id
     *
     * @return the value of tic_permission.per_id
     *
     * @mbggenerated
     */
    public Integer getPerId() {
        return perId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_permission.per_id
     *
     * @param perId the value for tic_permission.per_id
     *
     * @mbggenerated
     */
    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_permission.per_role_id
     *
     * @return the value of tic_permission.per_role_id
     *
     * @mbggenerated
     */
    public Integer getPerRoleId() {
        return perRoleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_permission.per_role_id
     *
     * @param perRoleId the value for tic_permission.per_role_id
     *
     * @mbggenerated
     */
    public void setPerRoleId(Integer perRoleId) {
        this.perRoleId = perRoleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_permission.per_permission
     *
     * @return the value of tic_permission.per_permission
     *
     * @mbggenerated
     */
    public String getPerPermission() {
        return perPermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_permission.per_permission
     *
     * @param perPermission the value for tic_permission.per_permission
     *
     * @mbggenerated
     */
    public void setPerPermission(String perPermission) {
        this.perPermission = perPermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_permission.per_add_time
     *
     * @return the value of tic_permission.per_add_time
     *
     * @mbggenerated
     */
    public Date getPerAddTime() {
        return perAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_permission.per_add_time
     *
     * @param perAddTime the value for tic_permission.per_add_time
     *
     * @mbggenerated
     */
    public void setPerAddTime(Date perAddTime) {
        this.perAddTime = perAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_permission.per_update_time
     *
     * @return the value of tic_permission.per_update_time
     *
     * @mbggenerated
     */
    public Date getPerUpdateTime() {
        return perUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_permission.per_update_time
     *
     * @param perUpdateTime the value for tic_permission.per_update_time
     *
     * @mbggenerated
     */
    public void setPerUpdateTime(Date perUpdateTime) {
        this.perUpdateTime = perUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_permission.per_deleted
     *
     * @return the value of tic_permission.per_deleted
     *
     * @mbggenerated
     */
    public Boolean getPerDeleted() {
        return perDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_permission.per_deleted
     *
     * @param perDeleted the value for tic_permission.per_deleted
     *
     * @mbggenerated
     */
    public void setPerDeleted(Boolean perDeleted) {
        this.perDeleted = perDeleted;
    }
}