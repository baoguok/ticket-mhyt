package org.linlinjava.litemall.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicRoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    public TicRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
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
     * This method corresponds to the database table tic_role
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
     * This method corresponds to the database table tic_role
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_role
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
     * This class corresponds to the database table tic_role
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

        public Criteria andRolIdIsNull() {
            addCriterion("rol_id is null");
            return (Criteria) this;
        }

        public Criteria andRolIdIsNotNull() {
            addCriterion("rol_id is not null");
            return (Criteria) this;
        }

        public Criteria andRolIdEqualTo(Integer value) {
            addCriterion("rol_id =", value, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdNotEqualTo(Integer value) {
            addCriterion("rol_id <>", value, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdGreaterThan(Integer value) {
            addCriterion("rol_id >", value, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rol_id >=", value, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdLessThan(Integer value) {
            addCriterion("rol_id <", value, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdLessThanOrEqualTo(Integer value) {
            addCriterion("rol_id <=", value, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdIn(List<Integer> values) {
            addCriterion("rol_id in", values, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdNotIn(List<Integer> values) {
            addCriterion("rol_id not in", values, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdBetween(Integer value1, Integer value2) {
            addCriterion("rol_id between", value1, value2, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rol_id not between", value1, value2, "rolId");
            return (Criteria) this;
        }

        public Criteria andRolNameIsNull() {
            addCriterion("rol_name is null");
            return (Criteria) this;
        }

        public Criteria andRolNameIsNotNull() {
            addCriterion("rol_name is not null");
            return (Criteria) this;
        }

        public Criteria andRolNameEqualTo(String value) {
            addCriterion("rol_name =", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameNotEqualTo(String value) {
            addCriterion("rol_name <>", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameGreaterThan(String value) {
            addCriterion("rol_name >", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameGreaterThanOrEqualTo(String value) {
            addCriterion("rol_name >=", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameLessThan(String value) {
            addCriterion("rol_name <", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameLessThanOrEqualTo(String value) {
            addCriterion("rol_name <=", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameLike(String value) {
            addCriterion("rol_name like", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameNotLike(String value) {
            addCriterion("rol_name not like", value, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameIn(List<String> values) {
            addCriterion("rol_name in", values, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameNotIn(List<String> values) {
            addCriterion("rol_name not in", values, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameBetween(String value1, String value2) {
            addCriterion("rol_name between", value1, value2, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolNameNotBetween(String value1, String value2) {
            addCriterion("rol_name not between", value1, value2, "rolName");
            return (Criteria) this;
        }

        public Criteria andRolDescIsNull() {
            addCriterion("rol_desc is null");
            return (Criteria) this;
        }

        public Criteria andRolDescIsNotNull() {
            addCriterion("rol_desc is not null");
            return (Criteria) this;
        }

        public Criteria andRolDescEqualTo(String value) {
            addCriterion("rol_desc =", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescNotEqualTo(String value) {
            addCriterion("rol_desc <>", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescGreaterThan(String value) {
            addCriterion("rol_desc >", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescGreaterThanOrEqualTo(String value) {
            addCriterion("rol_desc >=", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescLessThan(String value) {
            addCriterion("rol_desc <", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescLessThanOrEqualTo(String value) {
            addCriterion("rol_desc <=", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescLike(String value) {
            addCriterion("rol_desc like", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescNotLike(String value) {
            addCriterion("rol_desc not like", value, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescIn(List<String> values) {
            addCriterion("rol_desc in", values, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescNotIn(List<String> values) {
            addCriterion("rol_desc not in", values, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescBetween(String value1, String value2) {
            addCriterion("rol_desc between", value1, value2, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolDescNotBetween(String value1, String value2) {
            addCriterion("rol_desc not between", value1, value2, "rolDesc");
            return (Criteria) this;
        }

        public Criteria andRolEnabledIsNull() {
            addCriterion("rol_enabled is null");
            return (Criteria) this;
        }

        public Criteria andRolEnabledIsNotNull() {
            addCriterion("rol_enabled is not null");
            return (Criteria) this;
        }

        public Criteria andRolEnabledEqualTo(Boolean value) {
            addCriterion("rol_enabled =", value, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledNotEqualTo(Boolean value) {
            addCriterion("rol_enabled <>", value, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledGreaterThan(Boolean value) {
            addCriterion("rol_enabled >", value, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("rol_enabled >=", value, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledLessThan(Boolean value) {
            addCriterion("rol_enabled <", value, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledLessThanOrEqualTo(Boolean value) {
            addCriterion("rol_enabled <=", value, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledIn(List<Boolean> values) {
            addCriterion("rol_enabled in", values, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledNotIn(List<Boolean> values) {
            addCriterion("rol_enabled not in", values, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledBetween(Boolean value1, Boolean value2) {
            addCriterion("rol_enabled between", value1, value2, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolEnabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("rol_enabled not between", value1, value2, "rolEnabled");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeIsNull() {
            addCriterion("rol_add_time is null");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeIsNotNull() {
            addCriterion("rol_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeEqualTo(Date value) {
            addCriterion("rol_add_time =", value, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeNotEqualTo(Date value) {
            addCriterion("rol_add_time <>", value, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeGreaterThan(Date value) {
            addCriterion("rol_add_time >", value, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rol_add_time >=", value, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeLessThan(Date value) {
            addCriterion("rol_add_time <", value, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("rol_add_time <=", value, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeIn(List<Date> values) {
            addCriterion("rol_add_time in", values, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeNotIn(List<Date> values) {
            addCriterion("rol_add_time not in", values, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeBetween(Date value1, Date value2) {
            addCriterion("rol_add_time between", value1, value2, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("rol_add_time not between", value1, value2, "rolAddTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeIsNull() {
            addCriterion("rol_update_time is null");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeIsNotNull() {
            addCriterion("rol_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeEqualTo(Date value) {
            addCriterion("rol_update_time =", value, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeNotEqualTo(Date value) {
            addCriterion("rol_update_time <>", value, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeGreaterThan(Date value) {
            addCriterion("rol_update_time >", value, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rol_update_time >=", value, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeLessThan(Date value) {
            addCriterion("rol_update_time <", value, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("rol_update_time <=", value, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeIn(List<Date> values) {
            addCriterion("rol_update_time in", values, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeNotIn(List<Date> values) {
            addCriterion("rol_update_time not in", values, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("rol_update_time between", value1, value2, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("rol_update_time not between", value1, value2, "rolUpdateTime");
            return (Criteria) this;
        }

        public Criteria andRolDeletedIsNull() {
            addCriterion("rol_deleted is null");
            return (Criteria) this;
        }

        public Criteria andRolDeletedIsNotNull() {
            addCriterion("rol_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andRolDeletedEqualTo(Boolean value) {
            addCriterion("rol_deleted =", value, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedNotEqualTo(Boolean value) {
            addCriterion("rol_deleted <>", value, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedGreaterThan(Boolean value) {
            addCriterion("rol_deleted >", value, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("rol_deleted >=", value, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedLessThan(Boolean value) {
            addCriterion("rol_deleted <", value, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("rol_deleted <=", value, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedIn(List<Boolean> values) {
            addCriterion("rol_deleted in", values, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedNotIn(List<Boolean> values) {
            addCriterion("rol_deleted not in", values, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("rol_deleted between", value1, value2, "rolDeleted");
            return (Criteria) this;
        }

        public Criteria andRolDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("rol_deleted not between", value1, value2, "rolDeleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_role
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
     * This class corresponds to the database table tic_role
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