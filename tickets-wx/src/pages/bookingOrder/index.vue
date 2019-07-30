<template>
    <div class="bookingOrder-wrap">
      <!-- 景点名称：套餐名 -->
      <div class="box">
        <div class="home_top">
          <p class="header-name">长隆度假区：生日套票（买一送一）</p>
        </div>
      </div>
      <!-- 游玩日期选择及订票数量 -->
      <div class="bookingOrder-content">
        <div class="bookingOrder-content-main">
          <div class="play-date">
            <div class="play-date-text">
              <p>出游日期</p>
            </div>
            <div :class="{'date-selection':tab === 1,'date-selection-queen':true }" @click="submit(1)">
              <p>04-23</p>
              <p class="ticket-money">￥108</p>
              <img  v-show="isPitch&&tab === 1" class="pitch-on" src="/static/images/pitch-on.svg"/>
            </div>
            <div :class="{'date-selection':tab === 2,'date-selection-queen':true }" @click="submit(2)">
              <p>04-24</p>
              <p class="ticket-money">￥108</p>
              <img  v-show="isPitch&&tab === 2" class="pitch-on" src="/static/images/pitch-on.svg"/>
            </div>
            <div :class="{'date-selection':tab === 3,'date-selection-queen':true }" @click="submit(3)">
              <p>04-25</p>
              <p class="ticket-money">￥108</p>
              <img  v-show="isPitch&&tab === 3" class="pitch-on" src="/static/images/pitch-on.svg"/>
            </div>
            <div class="more-date">
              <div class="more-date-text">
                <p>更多</p>
                <p>日期</p>
              </div>
              <div class="more-date-img">
                <img class="go-img" src="/static/images/go.svg" />
              </div>
            </div>
          </div>
          <!-- 是否能退订及预订须知 -->
          <div class="hint">
            <div class="hint-left">
              <img class="hint-left-img" src="/static/images/hint.svg"/>
              <span class="hint-left-text">换票入园 |</span>
              <span>不可退</span>
            </div>
            <div class="hint-right">
              <span>预订须知</span>
              <img class="hint-right-img" src="/static/images/go.svg"/>
            </div>
          </div>
          <!-- 购买数量 -->
          <div class="purchase-number">
            <div class="purchase-number-left"> 
              <span class="purchase-number-text">购买数量</span>
              <span>（单次最多订100张）</span>
            </div>
            <div class="purchase-number-right">
              <div :class="flag? 'subtract-front':'subtract'" @click="subtractClick()">—</div>
              <div class="num">{{num}}</div>
              <div class="add" @click="addClick()">+</div>
            </div>
          </div>
          <!-- 有效期 -->
          <div class="validity-date">
            <p>2019-04-23当天有效</p>
            <p class="refund-text">当前购买不可退</p>
          </div>
          <!-- 费用包含内容 -->
          <div class="payment-covers">
            <p class="symbol">*</p>
            <p class="payment-text">费用包含：长隆度假区生日票1张（客人当天生日买一送一)1张</p>
            <img class="down-img" src="/static/images/down.svg" />
          </div>
        </div>
      </div>
      <div class="below-div"></div>
      <!-- 游客信息 -->
      <div class="tourist-information">
        <div class="tourist-information-main">
          <div class="message-text">
            <p><span class="span-messageText">游客信息</span>需提供其中<span class="span-singleBit">1位</span>出游人信息</p>
          </div>
          <div class="change">
            <span>新增/更换</span>
            <img src="/static/images/change.svg" class="change-img"/>
          </div>
          <div class="addressName">
            <p class="distance">出游人</p>
            <input placeholder="姓名" />
          </div>
          <div class="userPhone">
            <p class="distance">手机号</p>
            <input placeholder="接收确认短信" maxlength="11"/>
          </div>
          <div class="userIDcard">
            <p class="distance">身份证</p>
            <input placeholder="请输入您的身份证号" maxlength="18"/>
          </div>
        </div>
      </div>
      <!-- 优惠券 -->
      <div class="coupon">
        <div class="coupon-main">
          <div class="coupon-text">
            <p>优惠</p>
          </div>
          <div class="coupon-first">
            <div class="coupon-first-left">
              <img class="coupon-img" src="/static/images/arch.svg" />
              <p>抵用券</p>
            </div>
            <div class="coupon-first-right">
              <p>暂无可用优惠券</p>
            </div>
          </div>
          <div class="coupon-second">
            <img class="coupon-img" src="/static/images/rebate.svg" />
            <span>支付成功后反15元酒店券</span>
          </div>
        </div>
      </div>
      <!-- 支付 -->
      <div class="footer">
        <div class="footer-main">
          <div class="footer-left">
            <p>订单金额：<span class="footer-money">￥108</span></p>
            <img class="footer-up-img" src="/static/images/money-up.svg" />
          </div>
          <div class="footer-right">
            <p>在线支付</p>
          </div>
        </div>
      </div>
      <div class="below1-div"></div>
    </div>
</template>
<script>
export default {
  data () {
    return {
      tab: 1,
      num: 1,
      flag: true,
      isPitch: true
    }
  },
  components: {
  },
  methods: {
    submit (index) {
      this.tab = index
      this.isPitch = true
    },
    addClick () {
      if (this.num < 100) {
        this.num += 1
        this.flag = false
      }
    },
    subtractClick () {
      if (this.num > 1) {
        this.num -= 1
      }
      if (this.num === 1) {
        this.flag = !this.flag
        this.flag = true
      }
    }
  },
  created () {
    // let app = getApp()
  }
}
</script>
    
<style>
.bookingOrder-wrap{
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
.bookingOrder-wrap::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
/* 景点名称：套餐票名 */
.box{
	width:100%;
	margin:auto;
	overflow:hidden;
}
.home_top{
	position: relative;
	width:100%;
	height:100px;
	text-align:center;
	line-height:50px;
}
.home_top:after {
	width: 140%;
	height: 100px;
	position: absolute;
	left: -20%;
	top:0;
	z-index: -1;
	content: '';
	border-radius: 0 0 50% 50%;
	background:#00CC66;
}
.header-name{
  color: #fff;
  text-align: left;
  margin-left:2%;
  font-size:20px;
}
/* 游玩日期选择及订票数量 */
.bookingOrder-content{
  width: 90%;
  background: #fff;
  position: absolute;
  padding: 3%;
  top: 10%;
  margin-left: 2%;
  border-radius: 10px;
}
.bookingOrder-content-main{
  padding-top: 2%;
}
.play-date{
  display: flex;
  justify-content: space-around;
  margin-bottom: 10px;
}
.play-date-text{
  font-size: 16px;
  line-height: 44px;
  text-align: center;
}
.date-selection-queen{
  width: 18%;
  background: #f5f5f5;
  text-align: center;
  line-height: 20px;
  border-radius: 5px;
}
.date-selection{
  width: 18%;
  text-align: center;
  background: #fff;
  line-height: 22px;
  border-radius: 5px;
  padding-bottom: 1px;
  border: 1px solid #00cc66;
  letter-spacing: 0;
  position: relative;
}
.pitch-on{
  width: 14px;
  height: 14px;
  position: absolute;
  transform: translate(16px,-12px);
  
}
.ticket-money{
  color: #ee4031;
  letter-spacing: 0;
}
.more-date{
  width: 16%;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #F5F5F5;
  line-height: 22px;
  border-radius: 5px;
  padding-bottom: 2px;
}
.go-img{
  width: 15px;
  height: 15px;
  transform:translate(3px,3px);
}
/* 是否能退订及预订须知 */
.hint{
  display: flex;
  background: #F6F3EE;
  justify-content: space-between;
  padding: 3%;
  margin-bottom: 10px;
}
.hint-left{
  color: #A09148;
  display: flex;
  justify-content: left;
  align-items: center;
}
.hint-left-img{
  width: 15px;
  height: 15px;
  margin-right: 5px;
}
.hint-left-text{
  margin-right: 4px;
}
.hint-right{
  width: 25%;
  color: #ee4031;
  display: flex;
  justify-content: space-between;
}
.hint-right-img{
  width: 15px;
  height: 15px;
  transform: translateY(1px);
}
/* 购买数量 */
.purchase-number{
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
.purchase-number-left{
  color: #888;
  font-size: 10px;
  display: flex;
  align-items: flex-end;
}
.purchase-number-text{
  color: #000;
  font-size: 16px;
}
.purchase-number-right{
  display: flex;
}
.subtract-front{
   width: 40px;
  line-height: 40px;
  text-align: center;
  font-size: 10px;
  background: #f5f5f5;
  color: #dbdbdb;
}
.subtract,.num,.add{
  width: 40px;
  line-height: 40px;
  text-align: center;
}
.subtract{
  font-size: 10px;
  background: #f5f5f5;
  color: #00CC66;
}
.add{
  font-size: 20px;
  background: #f5f5f5;
  color: #00CC66;
}
/* 有效期 */
.validity-date{
  line-height: 20px;
}
.refund-text{
  color: #ee4031;
}
/* 费用包含 */
.payment-covers{
  margin-top: 10px;
  padding: 3%;
  background: #f5f5f5;
  display: flex;
}
.down-img{
  width: 15px;
  height: 15px;
  transform: translate(5px,1px);
}
.payment-text{
  width: 90%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space:nowrap;
  font-size: 11px;
  color: #888;
}
.symbol{
  color: #ee4031;
  margin-right: 3px;
}
/* 游客信息 */
.tourist-information{
  background: #fff;
  width: 90%;
  padding: 3%;
  margin-left: 2%;
  border-radius: 10px;
}
.tourist-information-main{
  padding-top: 1%;
}
.message-text{
  color: #888;
}
.span-messageText{
  color: #000;
  font-size: 22px;
  margin-right: 5px;
}
.span-singleBit{
  color: #ee4031;
}
.change{
  color: #00cc66;
  width: 80px;
  background: #def5e3;
  border-radius: 5px;
  margin: 15px 0;
  padding: 2%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.change-img{
  width: 10px;
  height: 10px;
}
.addressName{
  display: flex;
  border-bottom: 1px dashed #E8E8E8;
  justify-content: left;
  align-items: center;
  padding-bottom: 10px;
}
.userPhone{
  display: flex;
  justify-content: left;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px dashed #E8E8E8;
}
.userIDcard{
   display: flex;
  justify-content: left;
  align-items: center;
  padding-top: 10px ;
}
.distance{
  margin-right: 80px;
}
.below-div{
  width: 100%;
  height: 240px;
}
.below1-div{
  width: 100%;
  height: 60px;
}
/* 优惠券 */
.coupon{
  width: 90%;
  padding: 3%;
  background: #fff;
  border-radius: 10px;
  margin: 10px auto;
}
.coupon-main{
  background: #fff;
}
.coupon-text{
  font-size: 15px;
  border-bottom: 1px dashed #E8E8E8;
  padding-bottom: 10px;
}
.coupon-img{
  width: 20px;
  height: 20px;
  margin-right: 10px;
}

.coupon-first{
  display: flex;
  justify-content: space-between;
  border-bottom: 1px dashed #E8E8E8;
  padding: 10px 0;
}
.coupon-first-left{
  display: flex;
}
.coupon-first-right{
  color: #888;
}
.coupon-second{
  display: flex;
  justify-content: left;
  align-items: center;
   padding-top: 10px ;
}
/* 支付 */
.footer{
  position: fixed;
  bottom: 0;
  left: 0;
  width: 97%;
  padding: 0 0 0 3%;
  background: #fff;
}
.footer-main{
 display: flex;
}
.footer-left{
  width: 75%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 15px;
}
.footer-up-img{
  width: 12px;
  height: 12px;
  transform: translateX(-10px);
}
.footer-right{
  width: 25%;
  height: 49px;
  text-align: center;
  line-height: 49px;
  background: #00cc66;
  color: #fff;
  font-size: 17px;
}
.footer-money{
  color: #ee4031;
  letter-spacing: 0;
}
</style>
