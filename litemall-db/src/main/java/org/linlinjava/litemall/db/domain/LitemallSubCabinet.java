package org.linlinjava.litemall.db.domain;

import java.util.Date;

public class LitemallSubCabinet {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.main_cab_id
     *
     * @mbggenerated
     */
    private Integer mainCabId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.main_cab_id
     *
     * @mbggenerated
     */
    private String mainCabNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.sub_cab_num
     *
     * @mbggenerated
     */
    private String subCabNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.standard
     *
     * @mbggenerated
     */
    private String standard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.price
     *
     * @mbggenerated
     */
    private Float price;

    /**
     * 封顶时间（多少时间封顶）
     */
    private Integer ruleCapTime;

    /**
     * 免费时间（多少分钟内免费）
     */
    private Integer ruleFreeTime;

    /**
     * 超出时间（超出多少时间算一个小时）
     */
    private Integer ruleExceedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.add_time
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_sub_cabinet.deleted
     *
     * @mbggenerated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.id
     *
     * @return the value of litemall_sub_cabinet.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.id
     *
     * @param id the value for litemall_sub_cabinet.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.main_cab_id
     *
     * @return the value of litemall_sub_cabinet.main_cab_id
     *
     * @mbggenerated
     */
    public Integer getMainCabId() {
        return mainCabId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.main_cab_id
     *
     * @param mainCabId the value for litemall_sub_cabinet.main_cab_id
     *
     * @mbggenerated
     */
    public void setMainCabId(Integer mainCabId) {
        this.mainCabId = mainCabId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.sub_cab_num
     *
     * @return the value of litemall_sub_cabinet.sub_cab_num
     *
     * @mbggenerated
     */
    public String getSubCabNum() {
        return subCabNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.sub_cab_num
     *
     * @param subCabNum the value for litemall_sub_cabinet.sub_cab_num
     *
     * @mbggenerated
     */
    public void setSubCabNum(String subCabNum) {
        this.subCabNum = subCabNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.status
     *
     * @return the value of litemall_sub_cabinet.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.status
     *
     * @param status the value for litemall_sub_cabinet.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.standard
     *
     * @return the value of litemall_sub_cabinet.standard
     *
     * @mbggenerated
     */
    public String getStandard() {
        return standard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.standard
     *
     * @param standard the value for litemall_sub_cabinet.standard
     *
     * @mbggenerated
     */
    public void setStandard(String standard) {
        this.standard = standard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.price
     *
     * @return the value of litemall_sub_cabinet.price
     *
     * @mbggenerated
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.price
     *
     * @param price the value for litemall_sub_cabinet.price
     *
     * @mbggenerated
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.add_time
     *
     * @return the value of litemall_sub_cabinet.add_time
     *
     * @mbggenerated
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.add_time
     *
     * @param addTime the value for litemall_sub_cabinet.add_time
     *
     * @mbggenerated
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.update_time
     *
     * @return the value of litemall_sub_cabinet.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.update_time
     *
     * @param updateTime the value for litemall_sub_cabinet.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_sub_cabinet.deleted
     *
     * @return the value of litemall_sub_cabinet.deleted
     *
     * @mbggenerated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_sub_cabinet.deleted
     *
     * @param deleted the value for litemall_sub_cabinet.deleted
     *
     * @mbggenerated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getMainCabNum() {
        return mainCabNum;
    }

    public void setMainCabNum(String mainCabNum) {
        this.mainCabNum = mainCabNum;
    }

    public Integer getRuleCapTime() {
        return ruleCapTime;
    }

    public void setRuleCapTime(Integer ruleCapTime) {
        this.ruleCapTime = ruleCapTime;
    }

    public Integer getRuleFreeTime() {
        return ruleFreeTime;
    }

    public void setRuleFreeTime(Integer ruleFreeTime) {
        this.ruleFreeTime = ruleFreeTime;
    }

    public Integer getRuleExceedTime() {
        return ruleExceedTime;
    }

    public void setRuleExceedTime(Integer ruleExceedTime) {
        this.ruleExceedTime = ruleExceedTime;
    }
}