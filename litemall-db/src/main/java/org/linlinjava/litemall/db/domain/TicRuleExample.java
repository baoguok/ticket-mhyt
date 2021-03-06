package org.linlinjava.litemall.db.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicRuleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public TicRuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
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
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_rule
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_rule
     *
     * @mbggenerated
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

        public Criteria andRulIdIsNull() {
            addCriterion("rul_id is null");
            return (Criteria) this;
        }

        public Criteria andRulIdIsNotNull() {
            addCriterion("rul_id is not null");
            return (Criteria) this;
        }

        public Criteria andRulIdEqualTo(Integer value) {
            addCriterion("rul_id =", value, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdNotEqualTo(Integer value) {
            addCriterion("rul_id <>", value, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdGreaterThan(Integer value) {
            addCriterion("rul_id >", value, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rul_id >=", value, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdLessThan(Integer value) {
            addCriterion("rul_id <", value, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdLessThanOrEqualTo(Integer value) {
            addCriterion("rul_id <=", value, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdIn(List<Integer> values) {
            addCriterion("rul_id in", values, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdNotIn(List<Integer> values) {
            addCriterion("rul_id not in", values, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdBetween(Integer value1, Integer value2) {
            addCriterion("rul_id between", value1, value2, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rul_id not between", value1, value2, "rulId");
            return (Criteria) this;
        }

        public Criteria andRulNameIsNull() {
            addCriterion("rul_name is null");
            return (Criteria) this;
        }

        public Criteria andRulNameIsNotNull() {
            addCriterion("rul_name is not null");
            return (Criteria) this;
        }

        public Criteria andRulNameEqualTo(String value) {
            addCriterion("rul_name =", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameNotEqualTo(String value) {
            addCriterion("rul_name <>", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameGreaterThan(String value) {
            addCriterion("rul_name >", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameGreaterThanOrEqualTo(String value) {
            addCriterion("rul_name >=", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameLessThan(String value) {
            addCriterion("rul_name <", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameLessThanOrEqualTo(String value) {
            addCriterion("rul_name <=", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameLike(String value) {
            addCriterion("rul_name like", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameNotLike(String value) {
            addCriterion("rul_name not like", value, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameIn(List<String> values) {
            addCriterion("rul_name in", values, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameNotIn(List<String> values) {
            addCriterion("rul_name not in", values, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameBetween(String value1, String value2) {
            addCriterion("rul_name between", value1, value2, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulNameNotBetween(String value1, String value2) {
            addCriterion("rul_name not between", value1, value2, "rulName");
            return (Criteria) this;
        }

        public Criteria andRulTypeIsNull() {
            addCriterion("rul_type is null");
            return (Criteria) this;
        }

        public Criteria andRulTypeIsNotNull() {
            addCriterion("rul_type is not null");
            return (Criteria) this;
        }

        public Criteria andRulTypeEqualTo(Integer value) {
            addCriterion("rul_type =", value, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeNotEqualTo(Integer value) {
            addCriterion("rul_type <>", value, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeGreaterThan(Integer value) {
            addCriterion("rul_type >", value, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rul_type >=", value, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeLessThan(Integer value) {
            addCriterion("rul_type <", value, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeLessThanOrEqualTo(Integer value) {
            addCriterion("rul_type <=", value, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeIn(List<Integer> values) {
            addCriterion("rul_type in", values, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeNotIn(List<Integer> values) {
            addCriterion("rul_type not in", values, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeBetween(Integer value1, Integer value2) {
            addCriterion("rul_type between", value1, value2, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("rul_type not between", value1, value2, "rulType");
            return (Criteria) this;
        }

        public Criteria andRulDetailsIsNull() {
            addCriterion("rul_details is null");
            return (Criteria) this;
        }

        public Criteria andRulDetailsIsNotNull() {
            addCriterion("rul_details is not null");
            return (Criteria) this;
        }

        public Criteria andRulDetailsEqualTo(String value) {
            addCriterion("rul_details =", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsNotEqualTo(String value) {
            addCriterion("rul_details <>", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsGreaterThan(String value) {
            addCriterion("rul_details >", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("rul_details >=", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsLessThan(String value) {
            addCriterion("rul_details <", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsLessThanOrEqualTo(String value) {
            addCriterion("rul_details <=", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsLike(String value) {
            addCriterion("rul_details like", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsNotLike(String value) {
            addCriterion("rul_details not like", value, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsIn(List<String> values) {
            addCriterion("rul_details in", values, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsNotIn(List<String> values) {
            addCriterion("rul_details not in", values, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsBetween(String value1, String value2) {
            addCriterion("rul_details between", value1, value2, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulDetailsNotBetween(String value1, String value2) {
            addCriterion("rul_details not between", value1, value2, "rulDetails");
            return (Criteria) this;
        }

        public Criteria andRulPriceIsNull() {
            addCriterion("rul_price is null");
            return (Criteria) this;
        }

        public Criteria andRulPriceIsNotNull() {
            addCriterion("rul_price is not null");
            return (Criteria) this;
        }

        public Criteria andRulPriceEqualTo(BigDecimal value) {
            addCriterion("rul_price =", value, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceNotEqualTo(BigDecimal value) {
            addCriterion("rul_price <>", value, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceGreaterThan(BigDecimal value) {
            addCriterion("rul_price >", value, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rul_price >=", value, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceLessThan(BigDecimal value) {
            addCriterion("rul_price <", value, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rul_price <=", value, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceIn(List<BigDecimal> values) {
            addCriterion("rul_price in", values, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceNotIn(List<BigDecimal> values) {
            addCriterion("rul_price not in", values, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rul_price between", value1, value2, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rul_price not between", value1, value2, "rulPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceIsNull() {
            addCriterion("rul_dec_price is null");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceIsNotNull() {
            addCriterion("rul_dec_price is not null");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceEqualTo(BigDecimal value) {
            addCriterion("rul_dec_price =", value, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceNotEqualTo(BigDecimal value) {
            addCriterion("rul_dec_price <>", value, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceGreaterThan(BigDecimal value) {
            addCriterion("rul_dec_price >", value, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rul_dec_price >=", value, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceLessThan(BigDecimal value) {
            addCriterion("rul_dec_price <", value, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rul_dec_price <=", value, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceIn(List<BigDecimal> values) {
            addCriterion("rul_dec_price in", values, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceNotIn(List<BigDecimal> values) {
            addCriterion("rul_dec_price not in", values, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rul_dec_price between", value1, value2, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulDecPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rul_dec_price not between", value1, value2, "rulDecPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceIsNull() {
            addCriterion("rul_cut_price is null");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceIsNotNull() {
            addCriterion("rul_cut_price is not null");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceEqualTo(BigDecimal value) {
            addCriterion("rul_cut_price =", value, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceNotEqualTo(BigDecimal value) {
            addCriterion("rul_cut_price <>", value, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceGreaterThan(BigDecimal value) {
            addCriterion("rul_cut_price >", value, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rul_cut_price >=", value, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceLessThan(BigDecimal value) {
            addCriterion("rul_cut_price <", value, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rul_cut_price <=", value, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceIn(List<BigDecimal> values) {
            addCriterion("rul_cut_price in", values, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceNotIn(List<BigDecimal> values) {
            addCriterion("rul_cut_price not in", values, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rul_cut_price between", value1, value2, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulCutPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rul_cut_price not between", value1, value2, "rulCutPrice");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeIsNull() {
            addCriterion("rul_buy_time is null");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeIsNotNull() {
            addCriterion("rul_buy_time is not null");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeEqualTo(Integer value) {
            addCriterion("rul_buy_time =", value, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeNotEqualTo(Integer value) {
            addCriterion("rul_buy_time <>", value, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeGreaterThan(Integer value) {
            addCriterion("rul_buy_time >", value, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rul_buy_time >=", value, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeLessThan(Integer value) {
            addCriterion("rul_buy_time <", value, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeLessThanOrEqualTo(Integer value) {
            addCriterion("rul_buy_time <=", value, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeIn(List<Integer> values) {
            addCriterion("rul_buy_time in", values, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeNotIn(List<Integer> values) {
            addCriterion("rul_buy_time not in", values, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeBetween(Integer value1, Integer value2) {
            addCriterion("rul_buy_time between", value1, value2, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulBuyTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("rul_buy_time not between", value1, value2, "rulBuyTime");
            return (Criteria) this;
        }

        public Criteria andRulEnableIsNull() {
            addCriterion("rul_enable is null");
            return (Criteria) this;
        }

        public Criteria andRulEnableIsNotNull() {
            addCriterion("rul_enable is not null");
            return (Criteria) this;
        }

        public Criteria andRulEnableEqualTo(Boolean value) {
            addCriterion("rul_enable =", value, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableNotEqualTo(Boolean value) {
            addCriterion("rul_enable <>", value, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableGreaterThan(Boolean value) {
            addCriterion("rul_enable >", value, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("rul_enable >=", value, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableLessThan(Boolean value) {
            addCriterion("rul_enable <", value, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableLessThanOrEqualTo(Boolean value) {
            addCriterion("rul_enable <=", value, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableIn(List<Boolean> values) {
            addCriterion("rul_enable in", values, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableNotIn(List<Boolean> values) {
            addCriterion("rul_enable not in", values, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableBetween(Boolean value1, Boolean value2) {
            addCriterion("rul_enable between", value1, value2, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulEnableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("rul_enable not between", value1, value2, "rulEnable");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeIsNull() {
            addCriterion("rul_start_time is null");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeIsNotNull() {
            addCriterion("rul_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeEqualTo(Date value) {
            addCriterion("rul_start_time =", value, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeNotEqualTo(Date value) {
            addCriterion("rul_start_time <>", value, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeGreaterThan(Date value) {
            addCriterion("rul_start_time >", value, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rul_start_time >=", value, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeLessThan(Date value) {
            addCriterion("rul_start_time <", value, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("rul_start_time <=", value, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeIn(List<Date> values) {
            addCriterion("rul_start_time in", values, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeNotIn(List<Date> values) {
            addCriterion("rul_start_time not in", values, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeBetween(Date value1, Date value2) {
            addCriterion("rul_start_time between", value1, value2, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("rul_start_time not between", value1, value2, "rulStartTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeIsNull() {
            addCriterion("rul_end_time is null");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeIsNotNull() {
            addCriterion("rul_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeEqualTo(Date value) {
            addCriterion("rul_end_time =", value, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeNotEqualTo(Date value) {
            addCriterion("rul_end_time <>", value, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeGreaterThan(Date value) {
            addCriterion("rul_end_time >", value, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rul_end_time >=", value, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeLessThan(Date value) {
            addCriterion("rul_end_time <", value, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("rul_end_time <=", value, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeIn(List<Date> values) {
            addCriterion("rul_end_time in", values, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeNotIn(List<Date> values) {
            addCriterion("rul_end_time not in", values, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeBetween(Date value1, Date value2) {
            addCriterion("rul_end_time between", value1, value2, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("rul_end_time not between", value1, value2, "rulEndTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeIsNull() {
            addCriterion("rul_add_time is null");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeIsNotNull() {
            addCriterion("rul_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeEqualTo(Date value) {
            addCriterion("rul_add_time =", value, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeNotEqualTo(Date value) {
            addCriterion("rul_add_time <>", value, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeGreaterThan(Date value) {
            addCriterion("rul_add_time >", value, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rul_add_time >=", value, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeLessThan(Date value) {
            addCriterion("rul_add_time <", value, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("rul_add_time <=", value, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeIn(List<Date> values) {
            addCriterion("rul_add_time in", values, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeNotIn(List<Date> values) {
            addCriterion("rul_add_time not in", values, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeBetween(Date value1, Date value2) {
            addCriterion("rul_add_time between", value1, value2, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("rul_add_time not between", value1, value2, "rulAddTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeIsNull() {
            addCriterion("rul_update_time is null");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeIsNotNull() {
            addCriterion("rul_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeEqualTo(Date value) {
            addCriterion("rul_update_time =", value, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeNotEqualTo(Date value) {
            addCriterion("rul_update_time <>", value, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeGreaterThan(Date value) {
            addCriterion("rul_update_time >", value, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rul_update_time >=", value, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeLessThan(Date value) {
            addCriterion("rul_update_time <", value, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("rul_update_time <=", value, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeIn(List<Date> values) {
            addCriterion("rul_update_time in", values, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeNotIn(List<Date> values) {
            addCriterion("rul_update_time not in", values, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("rul_update_time between", value1, value2, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("rul_update_time not between", value1, value2, "rulUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRulDeletedIsNull() {
            addCriterion("rul_deleted is null");
            return (Criteria) this;
        }

        public Criteria andRulDeletedIsNotNull() {
            addCriterion("rul_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andRulDeletedEqualTo(Boolean value) {
            addCriterion("rul_deleted =", value, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedNotEqualTo(Boolean value) {
            addCriterion("rul_deleted <>", value, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedGreaterThan(Boolean value) {
            addCriterion("rul_deleted >", value, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("rul_deleted >=", value, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedLessThan(Boolean value) {
            addCriterion("rul_deleted <", value, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("rul_deleted <=", value, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedIn(List<Boolean> values) {
            addCriterion("rul_deleted in", values, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedNotIn(List<Boolean> values) {
            addCriterion("rul_deleted not in", values, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("rul_deleted between", value1, value2, "rulDeleted");
            return (Criteria) this;
        }

        public Criteria andRulDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("rul_deleted not between", value1, value2, "rulDeleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_rule
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_rule
     *
     * @mbggenerated
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