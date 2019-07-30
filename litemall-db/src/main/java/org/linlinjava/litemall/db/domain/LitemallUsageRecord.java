package org.linlinjava.litemall.db.domain;

import java.util.Date;

public class LitemallUsageRecord {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.id
	 *
	 * @mbggenerated
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.user_id
	 *
	 * @mbggenerated
	 */
	private Integer userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.sub_cab_id
	 *
	 * @mbggenerated
	 */
	private Integer subCabId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.status
	 *
	 * @mbggenerated
	 */
	private Integer status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.start_time
	 *
	 * @mbggenerated
	 */
	private Date startTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.end_time
	 *
	 * @mbggenerated
	 */
	private Date endTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.pre_amount
	 *
	 * @mbggenerated
	 */
	private Float preAmount;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.actual_amount
	 *
	 * @mbggenerated
	 */
	private Float actualAmount;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.pre_time
	 *
	 * @mbggenerated
	 */
	private String preTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.actual_time
	 *
	 * @mbggenerated
	 */
	private String actualTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.add_time
	 *
	 * @mbggenerated
	 */
	private Date addTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.phone_pass
	 *
	 * @mbggenerated
	 */
	private String phonePass;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.update_time
	 *
	 * @mbggenerated
	 */
	private Date updateTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column litemall_usage_record.deleted
	 *
	 * @mbggenerated
	 */
	private Boolean deleted;

	/**
	 * 柜组区域
	 */
	private String region;

	/**
	 * 子柜编号
	 */
	private String subCabNum;

	/**
	 * 子柜规格
	 */
	private String standard;

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getSubCabNum() {
		return subCabNum;
	}

	public void setSubCabNum(String subCabNum) {
		this.subCabNum = subCabNum;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.id
	 *
	 * @return the value of litemall_usage_record.id
	 *
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.id
	 *
	 * @param id
	 *            the value for litemall_usage_record.id
	 *
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.user_id
	 *
	 * @return the value of litemall_usage_record.user_id
	 *
	 * @mbggenerated
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.user_id
	 *
	 * @param userId
	 *            the value for litemall_usage_record.user_id
	 *
	 * @mbggenerated
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.sub_cab_id
	 *
	 * @return the value of litemall_usage_record.sub_cab_id
	 *
	 * @mbggenerated
	 */
	public Integer getSubCabId() {
		return subCabId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.sub_cab_id
	 *
	 * @param subCabId
	 *            the value for litemall_usage_record.sub_cab_id
	 *
	 * @mbggenerated
	 */
	public void setSubCabId(Integer subCabId) {
		this.subCabId = subCabId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.status
	 *
	 * @return the value of litemall_usage_record.status
	 *
	 * @mbggenerated
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.status
	 *
	 * @param status
	 *            the value for litemall_usage_record.status
	 *
	 * @mbggenerated
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.start_time
	 *
	 * @return the value of litemall_usage_record.start_time
	 *
	 * @mbggenerated
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.start_time
	 *
	 * @param startTime
	 *            the value for litemall_usage_record.start_time
	 *
	 * @mbggenerated
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.end_time
	 *
	 * @return the value of litemall_usage_record.end_time
	 *
	 * @mbggenerated
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.end_time
	 *
	 * @param endTime
	 *            the value for litemall_usage_record.end_time
	 *
	 * @mbggenerated
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.pre_amount
	 *
	 * @return the value of litemall_usage_record.pre_amount
	 *
	 * @mbggenerated
	 */
	public Float getPreAmount() {
		return preAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.pre_amount
	 *
	 * @param preAmount
	 *            the value for litemall_usage_record.pre_amount
	 *
	 * @mbggenerated
	 */
	public void setPreAmount(Float preAmount) {
		this.preAmount = preAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.actual_amount
	 *
	 * @return the value of litemall_usage_record.actual_amount
	 *
	 * @mbggenerated
	 */
	public Float getActualAmount() {
		return actualAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.actual_amount
	 *
	 * @param actualAmount
	 *            the value for litemall_usage_record.actual_amount
	 *
	 * @mbggenerated
	 */
	public void setActualAmount(Float actualAmount) {
		this.actualAmount = actualAmount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.pre_time
	 *
	 * @return the value of litemall_usage_record.pre_time
	 *
	 * @mbggenerated
	 */
	public String getPreTime() {
		return preTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.pre_time
	 *
	 * @param preTime
	 *            the value for litemall_usage_record.pre_time
	 *
	 * @mbggenerated
	 */
	public void setPreTime(String preTime) {
		this.preTime = preTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.actual_time
	 *
	 * @return the value of litemall_usage_record.actual_time
	 *
	 * @mbggenerated
	 */
	public String getActualTime() {
		return actualTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.actual_time
	 *
	 * @param actualTime
	 *            the value for litemall_usage_record.actual_time
	 *
	 * @mbggenerated
	 */
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.add_time
	 *
	 * @return the value of litemall_usage_record.add_time
	 *
	 * @mbggenerated
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.add_time
	 *
	 * @param addTime
	 *            the value for litemall_usage_record.add_time
	 *
	 * @mbggenerated
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.phone_pass
	 *
	 * @return the value of litemall_usage_record.phone_pass
	 *
	 * @mbggenerated
	 */
	public String getPhonePass() {
		return phonePass;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.phone_pass
	 *
	 * @param phonePass
	 *            the value for litemall_usage_record.phone_pass
	 *
	 * @mbggenerated
	 */
	public void setPhonePass(String phonePass) {
		this.phonePass = phonePass;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.update_time
	 *
	 * @return the value of litemall_usage_record.update_time
	 *
	 * @mbggenerated
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.update_time
	 *
	 * @param updateTime
	 *            the value for litemall_usage_record.update_time
	 *
	 * @mbggenerated
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column litemall_usage_record.deleted
	 *
	 * @return the value of litemall_usage_record.deleted
	 *
	 * @mbggenerated
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column litemall_usage_record.deleted
	 *
	 * @param deleted
	 *            the value for litemall_usage_record.deleted
	 *
	 * @mbggenerated
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * 关联用户表
	 */
	private LitemallUser user;
	/**
	 * 关联子柜表
	 */
	private LitemallSubCabinet subCabinet;

	public LitemallUser getUser() {
		return user;
	}

	public void setUser(LitemallUser user) {
		this.user = user;
	}

	public LitemallSubCabinet getSubCabinet() {
		return subCabinet;
	}

	public void setSubCabinet(LitemallSubCabinet subCabinet) {
		this.subCabinet = subCabinet;
	}

}