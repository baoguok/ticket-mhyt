<template>
  <div class="order-sy">
    <div class="testNav">
      <div :class="[status == 0 ? 'active' : '']"  @click="changTab(0)">未使用</div>
      <div :class="[status == 1 ? 'active' : '']"  @click="changTab(1)">已使用</div>
      <div :class="[status == 2 ? 'active' : '']"  @click="changTab(2)">已过期</div>
    </div>
	  <div class="rests">
      <div class="b">
        <div class="coupon-form" v-if="status===0">
          <div class="input-box">
            <input class="coupon-sn" placeholder="请输入优惠码" :value="code"  @input="bindExchange"/>
            <image class="clear-icon" v-if="code.length > 0" src="/static/images/clear_input.png" @click="clearExchange"></image>
          </div>
          <div class="add-btn" @click="goExchange">兑换</div>
        </div>
        <div class="help" v-if="status===0">
          <img class="help-img" src="/static/images/help.svg" />
          <span class="help-text">使用说明</span>
        </div>
        <scroll-view class="coupon-list" scroll-y="true" :scroll-top="scrollTop">
          <div class="couponing-below" v-for="(item,index) in couponList" :key="index">
            <div class="couponing-below-left" :class="[status == 0 ? 'active' : '']">
              <div class="couponing-below-left-money"><span class="couponing-below-left-symbol ">￥ </span>{{item.discount}}</div>
              <div class="coupoing-below-left-condition">满{{item.min}}元可用</div>
            </div>
            <div class="coupoing-below-right">
              <div class="use-toponymy">
              <p class="use-toponymy-name">{{item.name}}</p>
              <p class="use-toponymy-text">有效期：{{item.startTime}} - {{item.endTime}}</p>
              </div>
            </div>
          </div>
          <view class="page" v-if="showPage">
            <view class="prev" :class="[page <= 1 ? 'disabled' : '']" @click="prevPage">上一页</view>
            <view class="next" :class="[(count / size) < page ? 'disabled' : '']" @click="nextPage">下一页</view>
          </view>
        </scroll-view>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      couponList: [],
      code: '',
      status: 0,
      page: 1,
      size: 10,
      count: 0,
      scrollTop: 0,
      showPage: false
    }
  },
  components: {
  },
  methods: {
    getCouponList: function () {
      let that = this
      this.scrollTop = 0
      this.showPage = false
      this.couponList = []
      this.$util.request(this.$api.CouponMyList, {
        status: that.status,
        page: that.page,
        size: that.size
      }).then(function (res) {
        if (res.errno === 0) {
          that.scrollTop = 0
          that.couponList = res.data.data
          that.showPage = true
          that.count = res.data.count
        }
      })
    },
    changTab (index) {
      this.status = index
      this.couponList = []
      this.page = 1
      this.size = 10
      this.count = 0
      this.scrollTop = 0
      this.showPage = false
      this.getCouponList()
    },
    bindExchange: function (e) {
      this.code = e.mp.detail.value
    },
    clearExchange: function () {
      this.code = ''
    },
    goExchange: function () {
      if (this.code.length === 0) {
        this.$util.showErrorToast('请输入兑换码')
        return
      }
      let that = this
      that.$util.request(that.$api.CouponExchange, {
        code: that.code
      }, 'POST').then(function (res) {
        if (res.errno === 0) {
          that.getCouponList()
          that.clearExchange()
          wx.showToast({
            title: '领取成功',
            duration: 2000
          })
        } else {
          that.$util.showErrorToast(res.errmsg)
        }
      })
    },
    nextPage: function (event) {
      var that = this
      if (that.page > that.count / that.size) {
        return true
      }
      that.page = that.page + 1
      that.getCouponList()
    },
    prevPage: function (event) {
      if (this.page <= 1) {
        return false
      }
      this.page = this.page - 1
      this.getCouponList()
    }
  },
  created () {
    // let app = getApp()
  },
  onLoad () {
    this.getCouponList()
  }
}
</script>
    
<style>
.order-sy{
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color:#F5F5F5;
  font-size: 25rpx;
  overflow-y:scroll;
  color: #666666;
}
.order-sy::-webkit-scrollbar{
  width: 2px;
  height: 2px;
}
/* 导航 */
.testNav {
	line-height: 40px;
	display: flex;
	border-bottom: 1px solid #DDDDDD;
	background: #fff;
  justify-content: space-around;
  align-items: center;
  font-size: 30rpx;
  margin-bottom: 20rpx;
	}
.testNav .active {
  color: #FF7B06;
  border-bottom: 1px solid #FF7B06; 
  text-align: center;
}
.clear-icon {
  position: absolute;
  top: 140rpx;
  right: 230rpx;
  z-index: 2;
  display: block;
  background: #f5f5f5;
  height: 44rpx;
  width: 44rpx;
}
/* 分页 */
.paging{
  width: 100%;
  color: #666666;
  line-height: 54px;
  text-align: center;
  font-size: 29rpx;
  margin-top: 5%;
}
/* 兑换码 */
.coupon-form{
  width: 100%;
  background: #fff;
  display: flex;
  justify-content: space-around;
  padding: 3% 0;
  
}
.add-btn{
  color: #fff;
  background: #FF7B06;
  line-height: 35px;
  width: 84px;
  text-align: center;
}
.input-box{
  border:2rpx solid rgba(0, 0, 0, 0.15);
  border-radius: 3px;
  line-height: 35px;
  width: 246px;
  padding-left: 3%;
  display: flex;
  align-items: center;
  background: #fff;
}
.coupon-sn{
  background: #fff;
  width:440rpx;
}
/* 使用说明 */
.help{
  width: 97%;
  line-height: 36px;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.help-img{
  width: 15px;
  height: 15px;
  text-align: right;
}
.help-text{
 margin-left: 1%;
}
/* 代金券 */
.b .coupon-list {
  width: 750rpx;
  height: 100%;
  overflow: hidden;
}

.couponing-below{
  display: flex;
  margin:10rpx 28rpx;
  background:white;
}
.couponing-below-left{
  width: 80px;
  background: gray;
  color: #fff;
  border-radius: 5px;
  margin-right: 10px;
}
.couponing-below-left.active{
  background: -webkit-linear-gradient(left, #FD6F18 , #FE4627);
}
.couponing-below-left-money{
  line-height: 30px;
  font-size: 17px;
  border-bottom: 1px dashed #fff;
}
.couponing-below-left-symbol{
  font-size: 12px;
}
.coupoing-below-left-condition{
  font-size: 11px;
  line-height: 22px;
}
.coupoing-below-right{
  width: 80%;
  display: flex;
  justify-content: space-between;
}
.use-toponymy-name{
  color: #2b2b2b;
  font-size:35rpx;
}
.use-toponymy-text{
  color: #888;
  font-size:25rpx;
}

.b .page {
  width: 750rpx;
  height: 108rpx;
  background: #fff;
  margin-bottom: 20rpx;
  margin-top: 20rpx;
}

.b .page view {
  height: 108rpx;
  width: 49%;
  float: left;
  font-size: 29rpx;
  color: #333;
  text-align: center;
  line-height: 108rpx;
}

.b .page .prev {
  border-right: 1px solid #d9d9d9;
}

.b .page .disabled {
  color: #ccc;
}
</style>
