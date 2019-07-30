<template>
  <view class="container">
  <scroll-view class="coupon-list" scroll-y="true" :scroll-top="scrollTop">
    <view class="unselect" @click='unselectCoupon'>不选择优惠券</view>
    <div class="couponing-below" v-for="(item,index) in couponList" :key="index" @click="selectCoupon(index)" :data-id="item.id">
      <div class="couponing-below-left">
        <div class="couponing-below-left-money"><span class="couponing-below-left-symbol ">￥ </span>{{item.cou.couDiscount}}</div>
        <div class="coupoing-below-left-condition">满{{item.cou.couMin}}元可用</div>
      </div>
      <div class="coupoing-below-right">
        <div class="use-toponymy">
          <p class="use-toponymy-name">{{item.cou.couName}}</p>
          <p class="use-toponymy-text">有效期：{{item.cos.cosStartTime}} &nbsp;至&nbsp; {{item.cos.cosEndTime}}</p>
        </div>
      </div>
    </div>
  </scroll-view>
</view>
</template>
<script>
export default {
  data () {
    return {
      couponList: [],
      catId: 0,
      gooIds: '',
      grouponLinkId: 0,
      scrollTop: 0,
      TotalPrice: 0
    }
  },
  components: {
  },
  methods: {
    // 查询优惠券信息
    getCouponList: function () {
      let that = this
      this.couponList = []
      // 页面渲染完成
      wx.showToast({
        title: '加载中...',
        icon: 'loading',
        duration: 2000
      })
      this.$util.request(this.$api.CouponSelectList, {
        catId: this.catId,
        gooIds: this.gooIds
      }).then(function (res) {
        if (res.errno === 0) {
          that.couponList = res.data
        }
        wx.hideToast()
      })
    },
    // 选择优惠券
    selectCoupon: function (index) {
      //  判断是不是满足减免金额
      if (this.TotalPrice < this.couponList[index].cou.couMin) {
        wx.showToast({
          title: '不足减免金额',
          icon: 'none'
        })
      } else {
        wx.setStorageSync('coupon', this.couponList[index])
        wx.navigateBack()
      }
    },
    unselectCoupon: function () {
      // 如果优惠券ID设置-1，则表示订单不使用优惠券
      wx.setStorageSync('coupon', '-1')
      wx.navigateBack()
    }
  },
  created () {
    // let app = getApp()
  },
  onLoad: function (options) {
    this.gooIds = options.gooIds
    this.catId = options.catId
    this.TotalPrice = options.TotalPrice
  },
  onShow: function () {
    // 页面显示
    wx.showLoading({
      title: '加载中...'
    })
    try {
      var cartId = wx.getStorageSync('cartId')
      if (!cartId) {
        cartId = 0
      }
      var couponId = wx.getStorageSync('couponId')
      if (!couponId) {
        couponId = 0
      }
      var grouponRulesId = wx.getStorageSync('grouponRulesId')
      if (!grouponRulesId) {
        grouponRulesId = 0
      }
      this.cartId = cartId
      this.couponId = couponId
      this.grouponRulesId = grouponRulesId
    } catch (e) {
      // Do something when catch error
      console.log(e)
    }
    this.getCouponList()
  }
}
</script>

<style>
page {
  background: #f4f4f4;
  min-height: 100%;
}

.container {
  background: #f4f4f4;
  min-height: 100%;
  padding-top: 30rpx;
}

.coupon-list {
  width: 750rpx;
  height: 100%;
  overflow: hidden;
}

.unselect {
  height: 80rpx;
  border: none;
  width: 700rpx;
  background: #28b43b;
  color: #fff;
  font-size: 35rpx;
  text-align: center;
  margin-bottom: 30rpx;
  margin-left: 30rpx;
  margin-right: 30rpx;
  line-height:80rpx;
}

.couponing-below{
  display: flex;
  margin:10rpx 28rpx;
  background:white;
}
.couponing-below-left{
  width: 80px;
  background: -webkit-linear-gradient(left, #FD6F18 , #FE4627);
  color: #fff;
  border-radius: 5px;
  margin-right: 10px;
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
</style>
