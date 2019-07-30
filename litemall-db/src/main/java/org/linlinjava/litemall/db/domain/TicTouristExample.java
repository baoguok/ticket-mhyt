package org.linlinjava.litemall.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicTouristExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    public TicTouristExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
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
     * This method corresponds to the database table tic_tourist
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
     * This method corresponds to the database table tic_tourist
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tic_tourist
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
     * This class corresponds to the database table tic_tourist
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

        public Criteria andTouIdIsNull() {
            addCriterion("tou_id is null");
            return (Criteria) this;
        }

        public Criteria andTouIdIsNotNull() {
            addCriterion("tou_id is not null");
            return (Criteria) this;
        }

        public Criteria andTouIdEqualTo(Integer value) {
            addCriterion("tou_id =", value, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdNotEqualTo(Integer value) {
            addCriterion("tou_id <>", value, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdGreaterThan(Integer value) {
            addCriterion("tou_id >", value, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tou_id >=", value, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdLessThan(Integer value) {
            addCriterion("tou_id <", value, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdLessThanOrEqualTo(Integer value) {
            addCriterion("tou_id <=", value, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdIn(List<Integer> values) {
            addCriterion("tou_id in", values, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdNotIn(List<Integer> values) {
            addCriterion("tou_id not in", values, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdBetween(Integer value1, Integer value2) {
            addCriterion("tou_id between", value1, value2, "touId");
            return (Criteria) this;
        }

        public Criteria andTouIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tou_id not between", value1, value2, "touId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdIsNull() {
            addCriterion("tou_user_id is null");
            return (Criteria) this;
        }

        public Criteria andTouUserIdIsNotNull() {
            addCriterion("tou_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andTouUserIdEqualTo(Integer value) {
            addCriterion("tou_user_id =", value, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdNotEqualTo(Integer value) {
            addCriterion("tou_user_id <>", value, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdGreaterThan(Integer value) {
            addCriterion("tou_user_id >", value, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tou_user_id >=", value, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdLessThan(Integer value) {
            addCriterion("tou_user_id <", value, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("tou_user_id <=", value, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdIn(List<Integer> values) {
            addCriterion("tou_user_id in", values, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdNotIn(List<Integer> values) {
            addCriterion("tou_user_id not in", values, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdBetween(Integer value1, Integer value2) {
            addCriterion("tou_user_id between", value1, value2, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tou_user_id not between", value1, value2, "touUserId");
            return (Criteria) this;
        }

        public Criteria andTouTouristIsNull() {
            addCriterion("tou_tourist is null");
            return (Criteria) this;
        }

        public Criteria andTouTouristIsNotNull() {
            addCriterion("tou_tourist is not null");
            return (Criteria) this;
        }

        public Criteria andTouTouristEqualTo(String value) {
            addCriterion("tou_tourist =", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristNotEqualTo(String value) {
            addCriterion("tou_tourist <>", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristGreaterThan(String value) {
            addCriterion("tou_tourist >", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristGreaterThanOrEqualTo(String value) {
            addCriterion("tou_tourist >=", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristLessThan(String value) {
            addCriterion("tou_tourist <", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristLessThanOrEqualTo(String value) {
            addCriterion("tou_tourist <=", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristLike(String value) {
            addCriterion("tou_tourist like", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristNotLike(String value) {
            addCriterion("tou_tourist not like", value, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristIn(List<String> values) {
            addCriterion("tou_tourist in", values, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristNotIn(List<String> values) {
            addCriterion("tou_tourist not in", values, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristBetween(String value1, String value2) {
            addCriterion("tou_tourist between", value1, value2, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouTouristNotBetween(String value1, String value2) {
            addCriterion("tou_tourist not between", value1, value2, "touTourist");
            return (Criteria) this;
        }

        public Criteria andTouMobileIsNull() {
            addCriterion("tou_mobile is null");
            return (Criteria) this;
        }

        public Criteria andTouMobileIsNotNull() {
            addCriterion("tou_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andTouMobileEqualTo(String value) {
            addCriterion("tou_mobile =", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileNotEqualTo(String value) {
            addCriterion("tou_mobile <>", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileGreaterThan(String value) {
            addCriterion("tou_mobile >", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileGreaterThanOrEqualTo(String value) {
            addCriterion("tou_mobile >=", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileLessThan(String value) {
            addCriterion("tou_mobile <", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileLessThanOrEqualTo(String value) {
            addCriterion("tou_mobile <=", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileLike(String value) {
            addCriterion("tou_mobile like", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileNotLike(String value) {
            addCriterion("tou_mobile not like", value, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileIn(List<String> values) {
            addCriterion("tou_mobile in", values, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileNotIn(List<String> values) {
            addCriterion("tou_mobile not in", values, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileBetween(String value1, String value2) {
            addCriterion("tou_mobile between", value1, value2, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouMobileNotBetween(String value1, String value2) {
            addCriterion("tou_mobile not between", value1, value2, "touMobile");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdIsNull() {
            addCriterion("tou_ucard_id is null");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdIsNotNull() {
            addCriterion("tou_ucard_id is not null");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdEqualTo(String value) {
            addCriterion("tou_ucard_id =", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdNotEqualTo(String value) {
            addCriterion("tou_ucard_id <>", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdGreaterThan(String value) {
            addCriterion("tou_ucard_id >", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdGreaterThanOrEqualTo(String value) {
            addCriterion("tou_ucard_id >=", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdLessThan(String value) {
            addCriterion("tou_ucard_id <", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdLessThanOrEqualTo(String value) {
            addCriterion("tou_ucard_id <=", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdLike(String value) {
            addCriterion("tou_ucard_id like", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdNotLike(String value) {
            addCriterion("tou_ucard_id not like", value, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdIn(List<String> values) {
            addCriterion("tou_ucard_id in", values, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdNotIn(List<String> values) {
            addCriterion("tou_ucard_id not in", values, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdBetween(String value1, String value2) {
            addCriterion("tou_ucard_id between", value1, value2, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouUcardIdNotBetween(String value1, String value2) {
            addCriterion("tou_ucard_id not between", value1, value2, "touUcardId");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeIsNull() {
            addCriterion("tou_add_time is null");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeIsNotNull() {
            addCriterion("tou_add_time is not null");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeEqualTo(Date value) {
            addCriterion("tou_add_time =", value, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeNotEqualTo(Date value) {
            addCriterion("tou_add_time <>", value, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeGreaterThan(Date value) {
            addCriterion("tou_add_time >", value, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tou_add_time >=", value, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeLessThan(Date value) {
            addCriterion("tou_add_time <", value, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("tou_add_time <=", value, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeIn(List<Date> values) {
            addCriterion("tou_add_time in", values, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeNotIn(List<Date> values) {
            addCriterion("tou_add_time not in", values, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeBetween(Date value1, Date value2) {
            addCriterion("tou_add_time between", value1, value2, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("tou_add_time not between", value1, value2, "touAddTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeIsNull() {
            addCriterion("tou_update_time is null");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeIsNotNull() {
            addCriterion("tou_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeEqualTo(Date value) {
            addCriterion("tou_update_time =", value, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeNotEqualTo(Date value) {
            addCriterion("tou_update_time <>", value, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeGreaterThan(Date value) {
            addCriterion("tou_update_time >", value, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tou_update_time >=", value, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeLessThan(Date value) {
            addCriterion("tou_update_time <", value, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("tou_update_time <=", value, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeIn(List<Date> values) {
            addCriterion("tou_update_time in", values, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeNotIn(List<Date> values) {
            addCriterion("tou_update_time not in", values, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("tou_update_time between", value1, value2, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("tou_update_time not between", value1, value2, "touUpdateTime");
            return (Criteria) this;
        }

        public Criteria andTouDeletedIsNull() {
            addCriterion("tou_deleted is null");
            return (Criteria) this;
        }

        public Criteria andTouDeletedIsNotNull() {
            addCriterion("tou_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andTouDeletedEqualTo(Boolean value) {
            addCriterion("tou_deleted =", value, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedNotEqualTo(Boolean value) {
            addCriterion("tou_deleted <>", value, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedGreaterThan(Boolean value) {
            addCriterion("tou_deleted >", value, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("tou_deleted >=", value, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedLessThan(Boolean value) {
            addCriterion("tou_deleted <", value, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("tou_deleted <=", value, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedIn(List<Boolean> values) {
            addCriterion("tou_deleted in", values, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedNotIn(List<Boolean> values) {
            addCriterion("tou_deleted not in", values, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("tou_deleted between", value1, value2, "touDeleted");
            return (Criteria) this;
        }

        public Criteria andTouDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("tou_deleted not between", value1, value2, "touDeleted");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tic_tourist
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
     * This class corresponds to the database table tic_tourist
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