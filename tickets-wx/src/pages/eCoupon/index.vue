<template>
  <div class="mycoupon-sy">
    <div class="header">可用抵用券（{{count}}张）</div>
    <!-- 优惠券 -->
    <div class="coupon" v-for="item in coupon" :key="item.id">
      <div class="coupon-main">
        <div class="coupon-up">
          <div class="coupon-left">
            <p class="coupon-name">{{item.name}}</p>
            <p class="coupon-date">截止日期{{item.endTime}}</p>
          </div>
          <div class="coupon-right">
            <p class="coupon-money"><span>￥</span>{{item.discount}}</p>
            <p class="coupon-text">满{{item.min}}</p>
          </div>
        </div>
        <div class="coupon-below">
          <p>{{item.desc}}</p>
        </div>
      </div>
      <div class="yuan1"></div>
	    <div class="yuan2"></div>
    </div>
    <div class="couponOut" v-for="item in couponOut" :key="item.id">
      <div class="coupon-main">
        <div class="coupon-up">
          <div class="coupon-left">
            <p class="coupon-name">{{item.name}}</p>
            <p class="coupon-date">截止日期{{item.endTime}}</p>
          </div>
          <div class="coupon-right">
            <p class="coupon-money"><span>￥</span>{{item.discount}}</p>
            <p class="coupon-text">满{{item.min}}</p>
          </div>
        </div>
        <div class="coupon-below">
          <p>{{item.desc}}</p>
        </div>
      </div>
      <div class="yuan1"></div>
	    <div class="yuan2"></div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      coupon: [],
      couponOut: [],
      count: 0
    }
  },
  components: {
  },
  methods: {
    getCouponList: function () {
      let that = this
      this.$util.request(this.$api.CouponMyList, {
      }).then(function (res) {
        if (res.errno === 0) {
          console.log(res)
          that.coupon = res.data.couponVoList
          // that.couponOut = res.data.couponVoOutList
          that.count = res.data.count
        }
      })
    }
  },
  created () {
  },
  onLoad () {
    this.getCouponList()
  }
}
</script>

<style scoped>
  .mycoupon-sy{
    text-align:center;
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background-color:#F5F5F5;
    font-size: 25rpx;
    overflow-y:scroll;
    letter-spacing: 1px;
  }
  .mycoupon-sy::-webkit-scrollbar{
    width: 2px;
    height: 2px;
  }
  .header{
    text-align: left;
    width: 96%;
    padding-left: 4%;
    margin-top: 15px;
  }
  /* 优惠券 */
.coupon{
  width: 84%;
  padding:2% 4% 0 4%;
  background: #fff;
  margin: 15px auto;
  position: relative;
  border-radius: 5px;
}
.coupon-main{
  background: #fff;
}
.couponOut{
  width: 84%;
  padding:2% 4% 0 4%;
  background: rgb(238, 236, 236);
  margin: 15px auto;
  position: relative;
  border-radius: 5px;
}
.couponOut .coupon-main{
  background: rgb(238, 236, 236);
}

.coupon-up{
  display: flex;
  justify-content: space-between;
  padding-bottom: 12px;
  border-bottom: 1px dashed #E8E8E8;
}
.coupon-left{
  text-align: left;
  line-height: 22px;
  margin-top: 9px;
}
.coupon-name{
  font-size: 16px;
}
.coupon-date{
  color: #888;
}
.coupon-money{
  font-size: 25px;
  letter-spacing: 0;
  color: #ee4031;
}
.coupon-money span{
  font-size: 13px;
}
.coupon-text{
  color: #888;
  font-size: 12px;
}
.coupon-below{
  padding: 3% 0;
  font-size: 10px;
  text-align: left;
  color: #888;
}
.phone{
  letter-spacing: 0;
}
.yuan1{
  position: absolute;
  width: 5px;
  height: 10px;
  border-radius:0  20px 20px 0 ;
  background-color: #f5f5f5;
  left: 0px;
  top:56.5%;
  }
.yuan2{
  position: absolute;
  width: 5px;
  height: 10px;
  border-radius:20px  0 0 20px;
  background-color: #f5f5f5;
  right: 0px;
  top:56.5%;
  }
</style>