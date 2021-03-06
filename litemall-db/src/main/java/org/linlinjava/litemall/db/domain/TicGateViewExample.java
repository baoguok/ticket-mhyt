package org.linlinjava.litemall.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicGateViewExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    public TicGateViewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
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
     * This method corresponds to the database table tic_gate_view
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
     * This method corresponds to the database table tic_gate_view
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_gate_view
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
     * This class corresponds to the database table tic_gate_view
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

        public Criteria andGateIdIsNull() {
            addCriterion("gate_id is null");
            return (Criteria) this;
        }

        public Criteria andGateIdIsNotNull() {
            addCriterion("gate_id is not null");
            return (Criteria) this;
        }

        public Criteria andGateIdEqualTo(Integer value) {
            addCriterion("gate_id =", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotEqualTo(Integer value) {
            addCriterion("gate_id <>", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdGreaterThan(Integer value) {
            addCriterion("gate_id >", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gate_id >=", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdLessThan(Integer value) {
            addCriterion("gate_id <", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdLessThanOrEqualTo(Integer value) {
            addCriterion("gate_id <=", value, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdIn(List<Integer> values) {
            addCriterion("gate_id in", values, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotIn(List<Integer> values) {
            addCriterion("gate_id not in", values, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdBetween(Integer value1, Integer value2) {
            addCriterion("gate_id between", value1, value2, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gate_id not between", value1, value2, "gateId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdIsNull() {
            addCriterion("gate_view_id is null");
            return (Criteria) this;
        }

        public Criteria andGateViewIdIsNotNull() {
            addCriterion("gate_view_id is not null");
            return (Criteria) this;
        }

        public Criteria andGateViewIdEqualTo(Integer value) {
            addCriterion("gate_view_id =", value, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdNotEqualTo(Integer value) {
            addCriterion("gate_view_id <>", value, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdGreaterThan(Integer value) {
            addCriterion("gate_view_id >", value, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gate_view_id >=", value, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdLessThan(Integer value) {
            addCriterion("gate_view_id <", value, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdLessThanOrEqualTo(Integer value) {
            addCriterion("gate_view_id <=", value, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdIn(List<Integer> values) {
            addCriterion("gate_view_id in", values, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdNotIn(List<Integer> values) {
            addCriterion("gate_view_id not in", values, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdBetween(Integer value1, Integer value2) {
            addCriterion("gate_view_id between", value1, value2, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gate_view_id not between", value1, value2, "gateViewId");
            return (Criteria) this;
        }

        public Criteria andGateViewNameIsNull() {
            addCriterion("gate_view_name is null");
            return (Criteria) this;
        }

        public Criteria andGateViewNameIsNotNull() {
            addCriterion("gate_view_name is not null");
            return (Criteria) this;
        }

        public Criteria andGateViewNameEqualTo(String value) {
            addCriterion("gate_view_name =", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameNotEqualTo(String value) {
            addCriterion("gate_view_name <>", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameGreaterThan(String value) {
            addCriterion("gate_view_name >", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameGreaterThanOrEqualTo(String value) {
            addCriterion("gate_view_name >=", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameLessThan(String value) {
            addCriterion("gate_view_name <", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameLessThanOrEqualTo(String value) {
            addCriterion("gate_view_name <=", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameLike(String value) {
            addCriterion("gate_view_name like", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameNotLike(String value) {
            addCriterion("gate_view_name not like", value, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameIn(List<String> values) {
            addCriterion("gate_view_name in", values, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameNotIn(List<String> values) {
            addCriterion("gate_view_name not in", values, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameBetween(String value1, String value2) {
            addCriterion("gate_view_name between", value1, value2, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateViewNameNotBetween(String value1, String value2) {
            addCriterion("gate_view_name not between", value1, value2, "gateViewName");
            return (Criteria) this;
        }

        public Criteria andGateNumIsNull() {
            addCriterion("gate_num is null");
            return (Criteria) this;
        }

        public Criteria andGateNumIsNotNull() {
            addCriterion("gate_num is not null");
            return (Criteria) this;
        }

        public Criteria andGateNumEqualTo(String value) {
            addCriterion("gate_num =", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumNotEqualTo(String value) {
            addCriterion("gate_num <>", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumGreaterThan(String value) {
            addCriterion("gate_num >", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumGreaterThanOrEqualTo(String value) {
            addCriterion("gate_num >=", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumLessThan(String value) {
            addCriterion("gate_num <", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumLessThanOrEqualTo(String value) {
            addCriterion("gate_num <=", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumLike(String value) {
            addCriterion("gate_num like", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumNotLike(String value) {
            addCriterion("gate_num not like", value, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumIn(List<String> values) {
            addCriterion("gate_num in", values, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumNotIn(List<String> values) {
            addCriterion("gate_num not in", values, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumBetween(String value1, String value2) {
            addCriterion("gate_num between", value1, value2, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateNumNotBetween(String value1, String value2) {
            addCriterion("gate_num not between", value1, value2, "gateNum");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeIsNull() {
            addCriterion("gate_add_time is null");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeIsNotNull() {
            addCriterion("gate_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeEqualTo(Date value) {
            addCriterion("gate_add_time =", value, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeNotEqualTo(Date value) {
            addCriterion("gate_add_time <>", value, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeGreaterThan(Date value) {
            addCriterion("gate_add_time >", value, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gate_add_time >=", value, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeLessThan(Date value) {
            addCriterion("gate_add_time <", value, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("gate_add_time <=", value, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeIn(List<Date> values) {
            addCriterion("gate_add_time in", values, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeNotIn(List<Date> values) {
            addCriterion("gate_add_time not in", values, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeBetween(Date value1, Date value2) {
            addCriterion("gate_add_time between", value1, value2, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("gate_add_time not between", value1, value2, "gateAddTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeIsNull() {
            addCriterion("gate_update_time is null");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeIsNotNull() {
            addCriterion("gate_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeEqualTo(Date value) {
            addCriterion("gate_update_time =", value, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeNotEqualTo(Date value) {
            addCriterion("gate_update_time <>", value, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeGreaterThan(Date value) {
            addCriterion("gate_update_time >", value, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gate_update_time >=", value, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeLessThan(Date value) {
            addCriterion("gate_update_time <", value, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("gate_update_time <=", value, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeIn(List<Date> values) {
            addCriterion("gate_update_time in", values, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeNotIn(List<Date> values) {
            addCriterion("gate_update_time not in", values, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("gate_update_time between", value1, value2, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("gate_update_time not between", value1, value2, "gateUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGateDeletedIsNull() {
            addCriterion("gate_deleted is null");
            return (Criteria) this;
        }

        public Criteria andGateDeletedIsNotNull() {
            addCriterion("gate_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andGateDeletedEqualTo(Boolean value) {
            addCriterion("gate_deleted =", value, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedNotEqualTo(Boolean value) {
            addCriterion("gate_deleted <>", value, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedGreaterThan(Boolean value) {
            addCriterion("gate_deleted >", value, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("gate_deleted >=", value, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedLessThan(Boolean value) {
            addCriterion("gate_deleted <", value, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("gate_deleted <=", value, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedIn(List<Boolean> values) {
            addCriterion("gate_deleted in", values, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedNotIn(List<Boolean> values) {
            addCriterion("gate_deleted not in", values, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("gate_deleted between", value1, value2, "gateDeleted");
            return (Criteria) this;
        }

        public Criteria andGateDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("gate_deleted not between", value1, value2, "gateDeleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_gate_view
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
     * This class corresponds to the database table tic_gate_view
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