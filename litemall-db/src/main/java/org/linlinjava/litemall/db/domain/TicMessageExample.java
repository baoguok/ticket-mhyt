package org.linlinjava.litemall.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicMessageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public TicMessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMesIdIsNull() {
            addCriterion("mes_id is null");
            return (Criteria) this;
        }

        public Criteria andMesIdIsNotNull() {
            addCriterion("mes_id is not null");
            return (Criteria) this;
        }

        public Criteria andMesIdEqualTo(Integer value) {
            addCriterion("mes_id =", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdNotEqualTo(Integer value) {
            addCriterion("mes_id <>", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdGreaterThan(Integer value) {
            addCriterion("mes_id >", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mes_id >=", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdLessThan(Integer value) {
            addCriterion("mes_id <", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdLessThanOrEqualTo(Integer value) {
            addCriterion("mes_id <=", value, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdIn(List<Integer> values) {
            addCriterion("mes_id in", values, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdNotIn(List<Integer> values) {
            addCriterion("mes_id not in", values, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdBetween(Integer value1, Integer value2) {
            addCriterion("mes_id between", value1, value2, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mes_id not between", value1, value2, "mesId");
            return (Criteria) this;
        }

        public Criteria andMesNameIsNull() {
            addCriterion("mes_name is null");
            return (Criteria) this;
        }

        public Criteria andMesNameIsNotNull() {
            addCriterion("mes_name is not null");
            return (Criteria) this;
        }

        public Criteria andMesNameEqualTo(String value) {
            addCriterion("mes_name =", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameNotEqualTo(String value) {
            addCriterion("mes_name <>", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameGreaterThan(String value) {
            addCriterion("mes_name >", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameGreaterThanOrEqualTo(String value) {
            addCriterion("mes_name >=", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameLessThan(String value) {
            addCriterion("mes_name <", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameLessThanOrEqualTo(String value) {
            addCriterion("mes_name <=", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameLike(String value) {
            addCriterion("mes_name like", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameNotLike(String value) {
            addCriterion("mes_name not like", value, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameIn(List<String> values) {
            addCriterion("mes_name in", values, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameNotIn(List<String> values) {
            addCriterion("mes_name not in", values, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameBetween(String value1, String value2) {
            addCriterion("mes_name between", value1, value2, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesNameNotBetween(String value1, String value2) {
            addCriterion("mes_name not between", value1, value2, "mesName");
            return (Criteria) this;
        }

        public Criteria andMesOfferIsNull() {
            addCriterion("mes_offer is null");
            return (Criteria) this;
        }

        public Criteria andMesOfferIsNotNull() {
            addCriterion("mes_offer is not null");
            return (Criteria) this;
        }

        public Criteria andMesOfferEqualTo(String value) {
            addCriterion("mes_offer =", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferNotEqualTo(String value) {
            addCriterion("mes_offer <>", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferGreaterThan(String value) {
            addCriterion("mes_offer >", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferGreaterThanOrEqualTo(String value) {
            addCriterion("mes_offer >=", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferLessThan(String value) {
            addCriterion("mes_offer <", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferLessThanOrEqualTo(String value) {
            addCriterion("mes_offer <=", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferLike(String value) {
            addCriterion("mes_offer like", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferNotLike(String value) {
            addCriterion("mes_offer not like", value, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferIn(List<String> values) {
            addCriterion("mes_offer in", values, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferNotIn(List<String> values) {
            addCriterion("mes_offer not in", values, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferBetween(String value1, String value2) {
            addCriterion("mes_offer between", value1, value2, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOfferNotBetween(String value1, String value2) {
            addCriterion("mes_offer not between", value1, value2, "mesOffer");
            return (Criteria) this;
        }

        public Criteria andMesOpenIsNull() {
            addCriterion("mes_open is null");
            return (Criteria) this;
        }

        public Criteria andMesOpenIsNotNull() {
            addCriterion("mes_open is not null");
            return (Criteria) this;
        }

        public Criteria andMesOpenEqualTo(String value) {
            addCriterion("mes_open =", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenNotEqualTo(String value) {
            addCriterion("mes_open <>", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenGreaterThan(String value) {
            addCriterion("mes_open >", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenGreaterThanOrEqualTo(String value) {
            addCriterion("mes_open >=", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenLessThan(String value) {
            addCriterion("mes_open <", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenLessThanOrEqualTo(String value) {
            addCriterion("mes_open <=", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenLike(String value) {
            addCriterion("mes_open like", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenNotLike(String value) {
            addCriterion("mes_open not like", value, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenIn(List<String> values) {
            addCriterion("mes_open in", values, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenNotIn(List<String> values) {
            addCriterion("mes_open not in", values, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenBetween(String value1, String value2) {
            addCriterion("mes_open between", value1, value2, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesOpenNotBetween(String value1, String value2) {
            addCriterion("mes_open not between", value1, value2, "mesOpen");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeIsNull() {
            addCriterion("mes_sug_time is null");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeIsNotNull() {
            addCriterion("mes_sug_time is not null");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeEqualTo(String value) {
            addCriterion("mes_sug_time =", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeNotEqualTo(String value) {
            addCriterion("mes_sug_time <>", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeGreaterThan(String value) {
            addCriterion("mes_sug_time >", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeGreaterThanOrEqualTo(String value) {
            addCriterion("mes_sug_time >=", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeLessThan(String value) {
            addCriterion("mes_sug_time <", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeLessThanOrEqualTo(String value) {
            addCriterion("mes_sug_time <=", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeLike(String value) {
            addCriterion("mes_sug_time like", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeNotLike(String value) {
            addCriterion("mes_sug_time not like", value, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeIn(List<String> values) {
            addCriterion("mes_sug_time in", values, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeNotIn(List<String> values) {
            addCriterion("mes_sug_time not in", values, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeBetween(String value1, String value2) {
            addCriterion("mes_sug_time between", value1, value2, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesSugTimeNotBetween(String value1, String value2) {
            addCriterion("mes_sug_time not between", value1, value2, "mesSugTime");
            return (Criteria) this;
        }

        public Criteria andMesNoticeIsNull() {
            addCriterion("mes_notice is null");
            return (Criteria) this;
        }

        public Criteria andMesNoticeIsNotNull() {
            addCriterion("mes_notice is not null");
            return (Criteria) this;
        }

        public Criteria andMesNoticeEqualTo(String value) {
            addCriterion("mes_notice =", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeNotEqualTo(String value) {
            addCriterion("mes_notice <>", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeGreaterThan(String value) {
            addCriterion("mes_notice >", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("mes_notice >=", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeLessThan(String value) {
            addCriterion("mes_notice <", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeLessThanOrEqualTo(String value) {
            addCriterion("mes_notice <=", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeLike(String value) {
            addCriterion("mes_notice like", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeNotLike(String value) {
            addCriterion("mes_notice not like", value, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeIn(List<String> values) {
            addCriterion("mes_notice in", values, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeNotIn(List<String> values) {
            addCriterion("mes_notice not in", values, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeBetween(String value1, String value2) {
            addCriterion("mes_notice between", value1, value2, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesNoticeNotBetween(String value1, String value2) {
            addCriterion("mes_notice not between", value1, value2, "mesNotice");
            return (Criteria) this;
        }

        public Criteria andMesTitleIsNull() {
            addCriterion("mes_title is null");
            return (Criteria) this;
        }

        public Criteria andMesTitleIsNotNull() {
            addCriterion("mes_title is not null");
            return (Criteria) this;
        }

        public Criteria andMesTitleEqualTo(String value) {
            addCriterion("mes_title =", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotEqualTo(String value) {
            addCriterion("mes_title <>", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleGreaterThan(String value) {
            addCriterion("mes_title >", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleGreaterThanOrEqualTo(String value) {
            addCriterion("mes_title >=", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleLessThan(String value) {
            addCriterion("mes_title <", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleLessThanOrEqualTo(String value) {
            addCriterion("mes_title <=", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleLike(String value) {
            addCriterion("mes_title like", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotLike(String value) {
            addCriterion("mes_title not like", value, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleIn(List<String> values) {
            addCriterion("mes_title in", values, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotIn(List<String> values) {
            addCriterion("mes_title not in", values, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleBetween(String value1, String value2) {
            addCriterion("mes_title between", value1, value2, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesTitleNotBetween(String value1, String value2) {
            addCriterion("mes_title not between", value1, value2, "mesTitle");
            return (Criteria) this;
        }

        public Criteria andMesAddressIsNull() {
            addCriterion("mes_address is null");
            return (Criteria) this;
        }

        public Criteria andMesAddressIsNotNull() {
            addCriterion("mes_address is not null");
            return (Criteria) this;
        }

        public Criteria andMesAddressEqualTo(String value) {
            addCriterion("mes_address =", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressNotEqualTo(String value) {
            addCriterion("mes_address <>", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressGreaterThan(String value) {
            addCriterion("mes_address >", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mes_address >=", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressLessThan(String value) {
            addCriterion("mes_address <", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressLessThanOrEqualTo(String value) {
            addCriterion("mes_address <=", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressLike(String value) {
            addCriterion("mes_address like", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressNotLike(String value) {
            addCriterion("mes_address not like", value, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressIn(List<String> values) {
            addCriterion("mes_address in", values, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressNotIn(List<String> values) {
            addCriterion("mes_address not in", values, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressBetween(String value1, String value2) {
            addCriterion("mes_address between", value1, value2, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesAddressNotBetween(String value1, String value2) {
            addCriterion("mes_address not between", value1, value2, "mesAddress");
            return (Criteria) this;
        }

        public Criteria andMesFacilityIsNull() {
            addCriterion("mes_facility is null");
            return (Criteria) this;
        }

        public Criteria andMesFacilityIsNotNull() {
            addCriterion("mes_facility is not null");
            return (Criteria) this;
        }

        public Criteria andMesFacilityEqualTo(String value) {
            addCriterion("mes_facility =", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityNotEqualTo(String value) {
            addCriterion("mes_facility <>", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityGreaterThan(String value) {
            addCriterion("mes_facility >", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityGreaterThanOrEqualTo(String value) {
            addCriterion("mes_facility >=", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityLessThan(String value) {
            addCriterion("mes_facility <", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityLessThanOrEqualTo(String value) {
            addCriterion("mes_facility <=", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityLike(String value) {
            addCriterion("mes_facility like", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityNotLike(String value) {
            addCriterion("mes_facility not like", value, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityIn(List<String> values) {
            addCriterion("mes_facility in", values, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityNotIn(List<String> values) {
            addCriterion("mes_facility not in", values, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityBetween(String value1, String value2) {
            addCriterion("mes_facility between", value1, value2, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFacilityNotBetween(String value1, String value2) {
            addCriterion("mes_facility not between", value1, value2, "mesFacility");
            return (Criteria) this;
        }

        public Criteria andMesFieldIsNull() {
            addCriterion("mes_field is null");
            return (Criteria) this;
        }

        public Criteria andMesFieldIsNotNull() {
            addCriterion("mes_field is not null");
            return (Criteria) this;
        }

        public Criteria andMesFieldEqualTo(String value) {
            addCriterion("mes_field =", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldNotEqualTo(String value) {
            addCriterion("mes_field <>", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldGreaterThan(String value) {
            addCriterion("mes_field >", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldGreaterThanOrEqualTo(String value) {
            addCriterion("mes_field >=", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldLessThan(String value) {
            addCriterion("mes_field <", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldLessThanOrEqualTo(String value) {
            addCriterion("mes_field <=", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldLike(String value) {
            addCriterion("mes_field like", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldNotLike(String value) {
            addCriterion("mes_field not like", value, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldIn(List<String> values) {
            addCriterion("mes_field in", values, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldNotIn(List<String> values) {
            addCriterion("mes_field not in", values, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldBetween(String value1, String value2) {
            addCriterion("mes_field between", value1, value2, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesFieldNotBetween(String value1, String value2) {
            addCriterion("mes_field not between", value1, value2, "mesField");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeIsNull() {
            addCriterion("mes_add_time is null");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeIsNotNull() {
            addCriterion("mes_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeEqualTo(Date value) {
            addCriterion("mes_add_time =", value, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeNotEqualTo(Date value) {
            addCriterion("mes_add_time <>", value, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeGreaterThan(Date value) {
            addCriterion("mes_add_time >", value, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mes_add_time >=", value, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeLessThan(Date value) {
            addCriterion("mes_add_time <", value, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("mes_add_time <=", value, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeIn(List<Date> values) {
            addCriterion("mes_add_time in", values, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeNotIn(List<Date> values) {
            addCriterion("mes_add_time not in", values, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeBetween(Date value1, Date value2) {
            addCriterion("mes_add_time between", value1, value2, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("mes_add_time not between", value1, value2, "mesAddTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeIsNull() {
            addCriterion("mes_update_time is null");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeIsNotNull() {
            addCriterion("mes_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeEqualTo(Date value) {
            addCriterion("mes_update_time =", value, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeNotEqualTo(Date value) {
            addCriterion("mes_update_time <>", value, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeGreaterThan(Date value) {
            addCriterion("mes_update_time >", value, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mes_update_time >=", value, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeLessThan(Date value) {
            addCriterion("mes_update_time <", value, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("mes_update_time <=", value, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeIn(List<Date> values) {
            addCriterion("mes_update_time in", values, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeNotIn(List<Date> values) {
            addCriterion("mes_update_time not in", values, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("mes_update_time between", value1, value2, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("mes_update_time not between", value1, value2, "mesUpdateTime");
            return (Criteria) this;
        }

        public Criteria andMesDeletedIsNull() {
            addCriterion("mes_deleted is null");
            return (Criteria) this;
        }

        public Criteria andMesDeletedIsNotNull() {
            addCriterion("mes_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andMesDeletedEqualTo(Boolean value) {
            addCriterion("mes_deleted =", value, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedNotEqualTo(Boolean value) {
            addCriterion("mes_deleted <>", value, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedGreaterThan(Boolean value) {
            addCriterion("mes_deleted >", value, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mes_deleted >=", value, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedLessThan(Boolean value) {
            addCriterion("mes_deleted <", value, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("mes_deleted <=", value, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedIn(List<Boolean> values) {
            addCriterion("mes_deleted in", values, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedNotIn(List<Boolean> values) {
            addCriterion("mes_deleted not in", values, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("mes_deleted between", value1, value2, "mesDeleted");
            return (Criteria) this;
        }

        public Criteria andMesDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mes_deleted not between", value1, value2, "mesDeleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_message
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_message
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}