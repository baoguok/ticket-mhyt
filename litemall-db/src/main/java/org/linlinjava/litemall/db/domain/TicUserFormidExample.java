package org.linlinjava.litemall.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicUserFormidExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    public TicUserFormidExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
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
     * This method corresponds to the database table tic_user_formid
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
     * This method corresponds to the database table tic_user_formid
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_user_formid
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
     * This class corresponds to the database table tic_user_formid
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

        public Criteria andForIdIsNull() {
            addCriterion("for_id is null");
            return (Criteria) this;
        }

        public Criteria andForIdIsNotNull() {
            addCriterion("for_id is not null");
            return (Criteria) this;
        }

        public Criteria andForIdEqualTo(Integer value) {
            addCriterion("for_id =", value, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdNotEqualTo(Integer value) {
            addCriterion("for_id <>", value, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdGreaterThan(Integer value) {
            addCriterion("for_id >", value, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("for_id >=", value, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdLessThan(Integer value) {
            addCriterion("for_id <", value, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdLessThanOrEqualTo(Integer value) {
            addCriterion("for_id <=", value, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdIn(List<Integer> values) {
            addCriterion("for_id in", values, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdNotIn(List<Integer> values) {
            addCriterion("for_id not in", values, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdBetween(Integer value1, Integer value2) {
            addCriterion("for_id between", value1, value2, "forId");
            return (Criteria) this;
        }

        public Criteria andForIdNotBetween(Integer value1, Integer value2) {
            addCriterion("for_id not between", value1, value2, "forId");
            return (Criteria) this;
        }

        public Criteria andForFormidIsNull() {
            addCriterion("for_formId is null");
            return (Criteria) this;
        }

        public Criteria andForFormidIsNotNull() {
            addCriterion("for_formId is not null");
            return (Criteria) this;
        }

        public Criteria andForFormidEqualTo(String value) {
            addCriterion("for_formId =", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidNotEqualTo(String value) {
            addCriterion("for_formId <>", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidGreaterThan(String value) {
            addCriterion("for_formId >", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidGreaterThanOrEqualTo(String value) {
            addCriterion("for_formId >=", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidLessThan(String value) {
            addCriterion("for_formId <", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidLessThanOrEqualTo(String value) {
            addCriterion("for_formId <=", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidLike(String value) {
            addCriterion("for_formId like", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidNotLike(String value) {
            addCriterion("for_formId not like", value, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidIn(List<String> values) {
            addCriterion("for_formId in", values, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidNotIn(List<String> values) {
            addCriterion("for_formId not in", values, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidBetween(String value1, String value2) {
            addCriterion("for_formId between", value1, value2, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForFormidNotBetween(String value1, String value2) {
            addCriterion("for_formId not between", value1, value2, "forFormid");
            return (Criteria) this;
        }

        public Criteria andForIsprepayIsNull() {
            addCriterion("for_isprepay is null");
            return (Criteria) this;
        }

        public Criteria andForIsprepayIsNotNull() {
            addCriterion("for_isprepay is not null");
            return (Criteria) this;
        }

        public Criteria andForIsprepayEqualTo(Boolean value) {
            addCriterion("for_isprepay =", value, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayNotEqualTo(Boolean value) {
            addCriterion("for_isprepay <>", value, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayGreaterThan(Boolean value) {
            addCriterion("for_isprepay >", value, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayGreaterThanOrEqualTo(Boolean value) {
            addCriterion("for_isprepay >=", value, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayLessThan(Boolean value) {
            addCriterion("for_isprepay <", value, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayLessThanOrEqualTo(Boolean value) {
            addCriterion("for_isprepay <=", value, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayIn(List<Boolean> values) {
            addCriterion("for_isprepay in", values, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayNotIn(List<Boolean> values) {
            addCriterion("for_isprepay not in", values, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayBetween(Boolean value1, Boolean value2) {
            addCriterion("for_isprepay between", value1, value2, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForIsprepayNotBetween(Boolean value1, Boolean value2) {
            addCriterion("for_isprepay not between", value1, value2, "forIsprepay");
            return (Criteria) this;
        }

        public Criteria andForUseamountIsNull() {
            addCriterion("for_useAmount is null");
            return (Criteria) this;
        }

        public Criteria andForUseamountIsNotNull() {
            addCriterion("for_useAmount is not null");
            return (Criteria) this;
        }

        public Criteria andForUseamountEqualTo(Integer value) {
            addCriterion("for_useAmount =", value, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountNotEqualTo(Integer value) {
            addCriterion("for_useAmount <>", value, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountGreaterThan(Integer value) {
            addCriterion("for_useAmount >", value, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountGreaterThanOrEqualTo(Integer value) {
            addCriterion("for_useAmount >=", value, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountLessThan(Integer value) {
            addCriterion("for_useAmount <", value, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountLessThanOrEqualTo(Integer value) {
            addCriterion("for_useAmount <=", value, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountIn(List<Integer> values) {
            addCriterion("for_useAmount in", values, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountNotIn(List<Integer> values) {
            addCriterion("for_useAmount not in", values, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountBetween(Integer value1, Integer value2) {
            addCriterion("for_useAmount between", value1, value2, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForUseamountNotBetween(Integer value1, Integer value2) {
            addCriterion("for_useAmount not between", value1, value2, "forUseamount");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeIsNull() {
            addCriterion("for_expire_time is null");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeIsNotNull() {
            addCriterion("for_expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeEqualTo(Date value) {
            addCriterion("for_expire_time =", value, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeNotEqualTo(Date value) {
            addCriterion("for_expire_time <>", value, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeGreaterThan(Date value) {
            addCriterion("for_expire_time >", value, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("for_expire_time >=", value, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeLessThan(Date value) {
            addCriterion("for_expire_time <", value, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("for_expire_time <=", value, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeIn(List<Date> values) {
            addCriterion("for_expire_time in", values, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeNotIn(List<Date> values) {
            addCriterion("for_expire_time not in", values, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeBetween(Date value1, Date value2) {
            addCriterion("for_expire_time between", value1, value2, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("for_expire_time not between", value1, value2, "forExpireTime");
            return (Criteria) this;
        }

        public Criteria andForOpenidIsNull() {
            addCriterion("for_openId is null");
            return (Criteria) this;
        }

        public Criteria andForOpenidIsNotNull() {
            addCriterion("for_openId is not null");
            return (Criteria) this;
        }

        public Criteria andForOpenidEqualTo(String value) {
            addCriterion("for_openId =", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidNotEqualTo(String value) {
            addCriterion("for_openId <>", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidGreaterThan(String value) {
            addCriterion("for_openId >", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("for_openId >=", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidLessThan(String value) {
            addCriterion("for_openId <", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidLessThanOrEqualTo(String value) {
            addCriterion("for_openId <=", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidLike(String value) {
            addCriterion("for_openId like", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidNotLike(String value) {
            addCriterion("for_openId not like", value, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidIn(List<String> values) {
            addCriterion("for_openId in", values, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidNotIn(List<String> values) {
            addCriterion("for_openId not in", values, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidBetween(String value1, String value2) {
            addCriterion("for_openId between", value1, value2, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForOpenidNotBetween(String value1, String value2) {
            addCriterion("for_openId not between", value1, value2, "forOpenid");
            return (Criteria) this;
        }

        public Criteria andForAddTimeIsNull() {
            addCriterion("for_add_time is null");
            return (Criteria) this;
        }

        public Criteria andForAddTimeIsNotNull() {
            addCriterion("for_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andForAddTimeEqualTo(Date value) {
            addCriterion("for_add_time =", value, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeNotEqualTo(Date value) {
            addCriterion("for_add_time <>", value, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeGreaterThan(Date value) {
            addCriterion("for_add_time >", value, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("for_add_time >=", value, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeLessThan(Date value) {
            addCriterion("for_add_time <", value, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("for_add_time <=", value, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeIn(List<Date> values) {
            addCriterion("for_add_time in", values, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeNotIn(List<Date> values) {
            addCriterion("for_add_time not in", values, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeBetween(Date value1, Date value2) {
            addCriterion("for_add_time between", value1, value2, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("for_add_time not between", value1, value2, "forAddTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeIsNull() {
            addCriterion("for_update_time is null");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeIsNotNull() {
            addCriterion("for_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeEqualTo(Date value) {
            addCriterion("for_update_time =", value, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeNotEqualTo(Date value) {
            addCriterion("for_update_time <>", value, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeGreaterThan(Date value) {
            addCriterion("for_update_time >", value, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("for_update_time >=", value, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeLessThan(Date value) {
            addCriterion("for_update_time <", value, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("for_update_time <=", value, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeIn(List<Date> values) {
            addCriterion("for_update_time in", values, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeNotIn(List<Date> values) {
            addCriterion("for_update_time not in", values, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("for_update_time between", value1, value2, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("for_update_time not between", value1, value2, "forUpdateTime");
            return (Criteria) this;
        }

        public Criteria andForDeletedIsNull() {
            addCriterion("for_deleted is null");
            return (Criteria) this;
        }

        public Criteria andForDeletedIsNotNull() {
            addCriterion("for_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andForDeletedEqualTo(Boolean value) {
            addCriterion("for_deleted =", value, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedNotEqualTo(Boolean value) {
            addCriterion("for_deleted <>", value, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedGreaterThan(Boolean value) {
            addCriterion("for_deleted >", value, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("for_deleted >=", value, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedLessThan(Boolean value) {
            addCriterion("for_deleted <", value, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("for_deleted <=", value, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedIn(List<Boolean> values) {
            addCriterion("for_deleted in", values, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedNotIn(List<Boolean> values) {
            addCriterion("for_deleted not in", values, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("for_deleted between", value1, value2, "forDeleted");
            return (Criteria) this;
        }

        public Criteria andForDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("for_deleted not between", value1, value2, "forDeleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_user_formid
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
     * This class corresponds to the database table tic_user_formid
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