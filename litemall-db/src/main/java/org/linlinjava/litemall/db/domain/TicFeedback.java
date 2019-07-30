package org.linlinjava.litemall.db.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;
import java.util.Date;

public class TicFeedback {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_id
     *
     * @mbggenerated
     */
    private Integer feeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_user_id
     *
     * @mbggenerated
     */
    private Integer feeUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_username
     *
     * @mbggenerated
     */
    private String feeUsername;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_mobile
     *
     * @mbggenerated
     */
    private String feeMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_type
     *
     * @mbggenerated
     */
    private String feeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_content
     *
     * @mbggenerated
     */
    private String feeContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_status
     *
     * @mbggenerated
     */
    private Integer feeStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_has_picture
     *
     * @mbggenerated
     */
    private Boolean feeHasPicture;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_pic_urls
     *
     * @mbggenerated
     */
    private String[] feePicUrls;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_add_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date feeAddTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_update_time
     *
     * @mbggenerated
     */
    private Date feeUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tic_feedback.fee_deleted
     *
     * @mbggenerated
     */
    private Boolean feeDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_id
     *
     * @return the value of tic_feedback.fee_id
     *
     * @mbggenerated
     */
    public Integer getFeeId() {
        return feeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_id
     *
     * @param feeId the value for tic_feedback.fee_id
     *
     * @mbggenerated
     */
    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_user_id
     *
     * @return the value of tic_feedback.fee_user_id
     *
     * @mbggenerated
     */
    public Integer getFeeUserId() {
        return feeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_user_id
     *
     * @param feeUserId the value for tic_feedback.fee_user_id
     *
     * @mbggenerated
     */
    public void setFeeUserId(Integer feeUserId) {
        this.feeUserId = feeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_username
     *
     * @return the value of tic_feedback.fee_username
     *
     * @mbggenerated
     */
    public String getFeeUsername() {
        return feeUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_username
     *
     * @param feeUsername the value for tic_feedback.fee_username
     *
     * @mbggenerated
     */
    public void setFeeUsername(String feeUsername) {
        this.feeUsername = feeUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_mobile
     *
     * @return the value of tic_feedback.fee_mobile
     *
     * @mbggenerated
     */
    public String getFeeMobile() {
        return feeMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_mobile
     *
     * @param feeMobile the value for tic_feedback.fee_mobile
     *
     * @mbggenerated
     */
    public void setFeeMobile(String feeMobile) {
        this.feeMobile = feeMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_type
     *
     * @return the value of tic_feedback.fee_type
     *
     * @mbggenerated
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_type
     *
     * @param feeType the value for tic_feedback.fee_type
     *
     * @mbggenerated
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_content
     *
     * @return the value of tic_feedback.fee_content
     *
     * @mbggenerated
     */
    public String getFeeContent() {
        return feeContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_content
     *
     * @param feeContent the value for tic_feedback.fee_content
     *
     * @mbggenerated
     */
    public void setFeeContent(String feeContent) {
        this.feeContent = feeContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_status
     *
     * @return the value of tic_feedback.fee_status
     *
     * @mbggenerated
     */
    public Integer getFeeStatus() {
        return feeStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_status
     *
     * @param feeStatus the value for tic_feedback.fee_status
     *
     * @mbggenerated
     */
    public void setFeeStatus(Integer feeStatus) {
        this.feeStatus = feeStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_has_picture
     *
     * @return the value of tic_feedback.fee_has_picture
     *
     * @mbggenerated
     */
    public Boolean getFeeHasPicture() {
        return feeHasPicture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_has_picture
     *
     * @param feeHasPicture the value for tic_feedback.fee_has_picture
     *
     * @mbggenerated
     */
    public void setFeeHasPicture(Boolean feeHasPicture) {
        this.feeHasPicture = feeHasPicture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_pic_urls
     *
     * @return the value of tic_feedback.fee_pic_urls
     *
     * @mbggenerated
     */
    public String[] getFeePicUrls() {
        return feePicUrls;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_pic_urls
     *
     * @param feePicUrls the value for tic_feedback.fee_pic_urls
     *
     * @mbggenerated
     */
    public void setFeePicUrls(String[] feePicUrls) {
        this.feePicUrls = feePicUrls;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_add_time
     *
     * @return the value of tic_feedback.fee_add_time
     *
     * @mbggenerated
     */
    public Date getFeeAddTime() {
        return feeAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_add_time
     *
     * @param feeAddTime the value for tic_feedback.fee_add_time
     *
     * @mbggenerated
     */
    public void setFeeAddTime(Date feeAddTime) {
        this.feeAddTime = feeAddTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_update_time
     *
     * @return the value of tic_feedback.fee_update_time
     *
     * @mbggenerated
     */
    public Date getFeeUpdateTime() {
        return feeUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_update_time
     *
     * @param feeUpdateTime the value for tic_feedback.fee_update_time
     *
     * @mbggenerated
     */
    public void setFeeUpdateTime(Date feeUpdateTime) {
        this.feeUpdateTime = feeUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tic_feedback.fee_deleted
     *
     * @return the value of tic_feedback.fee_deleted
     *
     * @mbggenerated
     */
    public Boolean getFeeDeleted() {
        return feeDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tic_feedback.fee_deleted
     *
     * @param feeDeleted the value for tic_feedback.fee_deleted
     *
     * @mbggenerated
     */
    public void setFeeDeleted(Boolean feeDeleted) {
        this.feeDeleted = feeDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_feedback
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", feeId=").append(feeId);
        sb.append(", feeUserId=").append(feeUserId);
        sb.append(", feeUsername=").append(feeUsername);
        sb.append(", feeMobile=").append(feeMobile);
        sb.append(", feeType=").append(feeType);
        sb.append(", feeContent=").append(feeContent);
        sb.append(", feeStatus=").append(feeStatus);
        sb.append(", feeHasPicture=").append(feeHasPicture);
        sb.append(", feePicUrls=").append(feePicUrls);
        sb.append(", feeAddTime=").append(feeAddTime);
        sb.append(", feeUpdateTime=").append(feeUpdateTime);
        sb.append(", feeDeleted=").append(feeDeleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_feedback
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TicFeedback other = (TicFeedback) that;
        return (this.getFeeId() == null ? other.getFeeId() == null : this.getFeeId().equals(other.getFeeId()))
            && (this.getFeeUserId() == null ? other.getFeeUserId() == null : this.getFeeUserId().equals(other.getFeeUserId()))
            && (this.getFeeUsername() == null ? other.getFeeUsername() == null : this.getFeeUsername().equals(other.getFeeUsername()))
            && (this.getFeeMobile() == null ? other.getFeeMobile() == null : this.getFeeMobile().equals(other.getFeeMobile()))
            && (this.getFeeType() == null ? other.getFeeType() == null : this.getFeeType().equals(other.getFeeType()))
            && (this.getFeeContent() == null ? other.getFeeContent() == null : this.getFeeContent().equals(other.getFeeContent()))
            && (this.getFeeStatus() == null ? other.getFeeStatus() == null : this.getFeeStatus().equals(other.getFeeStatus()))
            && (this.getFeeHasPicture() == null ? other.getFeeHasPicture() == null : this.getFeeHasPicture().equals(other.getFeeHasPicture()))
            && (Arrays.equals(this.getFeePicUrls(), other.getFeePicUrls()))
            && (this.getFeeAddTime() == null ? other.getFeeAddTime() == null : this.getFeeAddTime().equals(other.getFeeAddTime()))
            && (this.getFeeUpdateTime() == null ? other.getFeeUpdateTime() == null : this.getFeeUpdateTime().equals(other.getFeeUpdateTime()))
            && (this.getFeeDeleted() == null ? other.getFeeDeleted() == null : this.getFeeDeleted().equals(other.getFeeDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_feedback
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFeeId() == null) ? 0 : getFeeId().hashCode());
        result = prime * result + ((getFeeUserId() == null) ? 0 : getFeeUserId().hashCode());
        result = prime * result + ((getFeeUsername() == null) ? 0 : getFeeUsername().hashCode());
        result = prime * result + ((getFeeMobile() == null) ? 0 : getFeeMobile().hashCode());
        result = prime * result + ((getFeeType() == null) ? 0 : getFeeType().hashCode());
        result = prime * result + ((getFeeContent() == null) ? 0 : getFeeContent().hashCode());
        result = prime * result + ((getFeeStatus() == null) ? 0 : getFeeStatus().hashCode());
        result = prime * result + ((getFeeHasPicture() == null) ? 0 : getFeeHasPicture().hashCode());
        result = prime * result + (Arrays.hashCode(getFeePicUrls()));
        result = prime * result + ((getFeeAddTime() == null) ? 0 : getFeeAddTime().hashCode());
        result = prime * result + ((getFeeUpdateTime() == null) ? 0 : getFeeUpdateTime().hashCode());
        result = prime * result + ((getFeeDeleted() == null) ? 0 : getFeeDeleted().hashCode());
        return result;
    }
}