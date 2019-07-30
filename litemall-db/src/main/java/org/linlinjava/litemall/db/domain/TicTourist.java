package org.linlinjava.litemall.db.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TicTourist {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_id
     *
     * @mbggenerated
     */
    private Integer touId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_user_id
     *
     * @mbggenerated
     */
    private Integer touUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_tourist
     *
     * @mbggenerated
     */
    private String touTourist;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_mobile
     *
     * @mbggenerated
     */
    private String touMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_ucard_id
     *
     * @mbggenerated
     */
    private String touUcardId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_add_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date touAddTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_update_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date touUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_tourist.tou_deleted
     *
     * @mbggenerated
     */
    private Boolean touDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_id
     *
     * @return the value of tic_tourist.tou_id
     *
     * @mbggenerated
     */
    public Integer getTouId() {
        return touId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_id
     *
     * @param touId the value for tic_tourist.tou_id
     *
     * @mbggenerated
     */
    public void setTouId(Integer touId) {
        this.touId = touId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_user_id
     *
     * @return the value of tic_tourist.tou_user_id
     *
     * @mbggenerated
     */
    public Integer getTouUserId() {
        return touUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_user_id
     *
     * @param touUserId the value for tic_tourist.tou_user_id
     *
     * @mbggenerated
     */
    public void setTouUserId(Integer touUserId) {
        this.touUserId = touUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_tourist
     *
     * @return the value of tic_tourist.tou_tourist
     *
     * @mbggenerated
     */
    public String getTouTourist() {
        return touTourist;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_tourist
     *
     * @param touTourist the value for tic_tourist.tou_tourist
     *
     * @mbggenerated
     */
    public void setTouTourist(String touTourist) {
        this.touTourist = touTourist;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_mobile
     *
     * @return the value of tic_tourist.tou_mobile
     *
     * @mbggenerated
     */
    public String getTouMobile() {
        return touMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_mobile
     *
     * @param touMobile the value for tic_tourist.tou_mobile
     *
     * @mbggenerated
     */
    public void setTouMobile(String touMobile) {
        this.touMobile = touMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_ucard_id
     *
     * @return the value of tic_tourist.tou_ucard_id
     *
     * @mbggenerated
     */
    public String getTouUcardId() {
        return touUcardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_ucard_id
     *
     * @param touUcardId the value for tic_tourist.tou_ucard_id
     *
     * @mbggenerated
     */
    public void setTouUcardId(String touUcardId) {
        this.touUcardId = touUcardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_add_time
     *
     * @return the value of tic_tourist.tou_add_time
     *
     * @mbggenerated
     */
    public Date getTouAddTime() {
        return touAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_add_time
     *
     * @param touAddTime the value for tic_tourist.tou_add_time
     *
     * @mbggenerated
     */
    public void setTouAddTime(Date touAddTime) {
        this.touAddTime = touAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_update_time
     *
     * @return the value of tic_tourist.tou_update_time
     *
     * @mbggenerated
     */
    public Date getTouUpdateTime() {
        return touUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_update_time
     *
     * @param touUpdateTime the value for tic_tourist.tou_update_time
     *
     * @mbggenerated
     */
    public void setTouUpdateTime(Date touUpdateTime) {
        this.touUpdateTime = touUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_tourist.tou_deleted
     *
     * @return the value of tic_tourist.tou_deleted
     *
     * @mbggenerated
     */
    public Boolean getTouDeleted() {
        return touDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_tourist.tou_deleted
     *
     * @param touDeleted the value for tic_tourist.tou_deleted
     *
     * @mbggenerated
     */
    public void setTouDeleted(Boolean touDeleted) {
        this.touDeleted = touDeleted;
    }
}