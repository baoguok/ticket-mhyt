<template>
  <div class="index">
    <div class="couponStyle">
      <div class="couponStyle-main">
        <div class="coupon-left">
          <p class="coupon-firstLine">￥<span class="coupon-number">10.00</span></p>
          <p class="coupon-Tasks">满<span class="conpon-money">99.00</span>元可用</p>
        </div>
        <div class="coupon-right">
          <p class="coupon-name">限时满减券</p>
          <p class="coupon-universal">全场通用</p>
          <div class="coupon-time">
            <div><span class="valiaity">有效期</span>：</div>
            <div class="coupon-date">
              <p>2019-04-26 18:51:44<span class="to">至</span></p>
              <p>2019-05-26 18:51:44</p>
            </div>
          </div>
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
    }
  },
  components: {
  },
  methods: {
  },
  created () {
    // let app = getApp()
  }
}
</script>
<style>
.index{
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color:#F5F5F5;
  font-size: 25rpx;
  overflow-y:scroll;
  color: #888;
}
.couponStyle{
  background: #fff;
  width: 94%;
  font-size: 12px;
  margin: 15px auto;
  border-radius: 5px;
  position: relative;
}
.couponStyle-main{
  display: flex;
}
.coupon-left{
  background: -webkit-linear-gradient(left top, rgb(247, 148, 62)  , rgb(255, 101, 74)); 
  background: -o-linear-gradient(bottom right, rgb(247, 148, 62)  , rgb(255, 101, 74)); 
  background: -moz-linear-gradient(bottom right, rgb(247, 148, 62) , rgb(255, 101, 74));
  background: linear-gradient(to bottom right, rgb(247, 148, 62)   , rgb(255, 101, 74));
  color: #fff;
  padding: 10px;
  border-radius: 5px 0 0 5px;
}
.coupon-firstLine{
 padding: 12px 0 22px 0; 
}
.coupon-number{
  font-size: 30px;
}
.coupon-Tasks{
  letter-spacing: 1px;
  font-size: 13px;
  transform: translateY(-12px);
}
.conpon-money{
  letter-spacing: 0px;
  margin-right: 1px;
}
.coupon-right{
  border-left: 1px dashed rgb(255, 101, 74);
  padding: 13px 0 10px  13px;
}
.coupon-name{
  color: #000;
  font-size: 18px;
  letter-spacing: 1px;
}
.coupon-universal{
  font-size: 13px;
  padding: 9px 0;
}
.coupon-time{
  display: flex;
}
.valiaity{
  letter-spacing: 1px;
}
.to{
  margin-left: 2px;
}
.yuan1{
  position: absolute;
  width: 12px;
  height: 6px;
  border-radius:0 0  20px 20px;
  background-color: #f5f5f5;
  left: 105px;
  top:0px;
  }
.yuan2{
  position: absolute;
  width: 12px;
  height: 6px;
  border-radius:20px 20px 0 0;
  background-color: #f5f5f5;
  right: 235px;
  top:108px;
  }
 
</style>
