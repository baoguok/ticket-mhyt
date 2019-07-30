<template>
  <div class="ticketObligation-wrap">
    <!-- 待付款及计时 -->
    <div class="header-text">
      <div class="header-left">
       <p><span class="header-left-txt">{{statustitle}}</span><span class="header-date">{{datetime}}</span></p>
        <!-- <p class="header-left-txt">已取消</p>
        <p class="header-exceed">订单已超过可支付时间，请重新购买。</p> -->
      </div>
      <div class="header-right">
        <span>￥{{order.ordGoodsPrice}}</span>
        <!-- <img class="go-details" src="/static/images/many.svg" /> -->
      </div>
    </div>
    <!--使用日期、入园时间、购买数量 -->
    <div class="ticket-content">
      <div class="content-main">
        <div class="content-FIRST">
          <p>{{order.cateName}}</p>
          <!-- <img class="content-FIRSTimg" src="/static/images/go.svg"> -->
        </div>
        <div class="content-partTwo">
          <div class="partTwo-div">
            <span class="partTwo-left">使用日期</span>
            <span class="partTwo-right-date">{{ order.ordConfirmTime }}  <span class="partTwo-week">{{weekday}}</span></span>
          </div
          >
          <div class="partTwo-div">
            <span class="partTwo-left">使用方法</span>
            <span class="partTwo-right">可使用取票码、取票二维码、手机号、身份证至自助取票机或人工窗口取票</span>
          </div>
          <div class="partTwo-div">
            <span class="partTwo-left">入园地址</span>
            <span class="partTwo-right">景区大门售票处</span>
          </div>
          <div class="partTwo-div">
            <span class="partTwo-left">购买数量</span>
            <span class="partTwo-right">{{order.ordGoodsNum}}张</span>
          </div>
        <div class="partThree">
          <span class="partTwo-left">门票介绍</span>
          <span class="partTwo-right">{{order.catDesc}}</span>
        </div>
        </div>
        <div class="partFour">
          <div class="partFour-left">退改规则</div>
          <div class="partFour-right">
            <p>未消费可随时退款，</p>
            <p>3个工作日内完成退款审核</p>
            <p>过期后未消费门票将自动作废,不可退还</p>
          </div>
        </div>
      </div>
      <div class="yuan1"></div>
	    <div class="yuan2"></div>
    </div>
    <div class="WDZN-BYJ">
      <div v-for="(item, index) in ordergoods" :key="index" style="text-align: center;">
        门票: {{ item.gooName }} 单价: {{ item.gooRetailPrice }} 数量: {{ item.orgNumber }}
      </div>
      </div>
    <!-- 联系客服 -->
    <div class="WDZN-BYJ">
      <div class="WDZN-BYJ-main">
        <div class="supplier">
          <div class="supplier-wrap">
            <img class="supplier-img" src="/static/images/supplier-phone.svg">
          </div>
          <div class="supplier-text">
            <p class="text-p" @click="callphone">供应商电话</p>
            <p>如果遇到商品信息相关问题，请联系商家客服</p>
          </div>
        </div>
        <div class="service">
          <div class="service-wrap">
            <img class="service-img" src="/static/images/service.svg"/>
          </div>
          <div class="service-text">
            <p class="text-p"><button open-type='contact'>在线客服</button></p>
            <p>问题咨询/在线帮助</p>
          </div>
        </div>
      </div>
    </div>
    <!-- 订单信息 -->
    <div class="orderInformation">
      <div class="orderInformation-main">
        <div class="orderInformation-one">订单信息</div>
        <div class="orderInformation-two">
          <div class="order-sty">
            <p class="order-sty-left">游玩人</p>
            <p class="order-sty-right">{{order.ordTourist}}</p>
          </div>
          <div class="order-sty">
            <p class="order-sty-left">电话</p>
            <p class="order-sty-right-phone">{{order.ordMobile}}</p>
          </div>
          <div class="order-sty">
            <p class="order-sty-left">身份信息</p>
            <p class="order-sty-right">{{order.ordUcardId}}</p>
          </div>
        </div>
        <div class="orderInformation-three">
          <div class="inform">
            <span class="inform-left">订单编号</span>
            <span class="inform-right">{{order.ordOrderSn}}</span>
            <span class="inform-copy" @click="copyTBL">复制</span>
          </div>
          <div class="inform">
            <span class="inform-left">支付时间</span>
            <span class="inform-right">{{order.ordAddTime}}</span>
          </div>
          <div class="inform-pay">
            <span class="inform-left">支付方式</span>
            <span class="inform-right-text">在线支付</span>
          </div>
        </div>
      </div>
    </div>
    <div class="below-div"></div>
    <!-- 待支付 -->
    <div class="footer">
      <div class="footer-substratum" v-if="order.ordOrderStatus === 101">
        <div class="btn-delete" @click="gotohome">取消</div>
        <div class="btn-agreeAndContinue" @click="wxPay()">{{paytext}}</div>
      </div>
      <!-- 待使用 -->
      <div class="footer-substratum" v-else-if="order.ordOrderStatus === 201">
        <div class="btn-delete" @click="gotohome">取消</div>
        <div class="btn-delete"  @click="wxRefund()">退票</div>
      </div>
      <!-- 已取消 -->
      <div class="footer-substratum" v-else-if="order.ordOrderStatus === 301">
        <div class="btn-delete" @click="gotohome">取消</div>
        <div class="btn-delete">立即评价</div>
      </div>
      <!-- 已使用 -->
      <div class="footer-substratum" v-else>
        <div class="btn-delete" @click="gotohome">取消</div>
        <div class="btn-delete" @click="gotohome">再来一单</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      order: {},
      scenic: {}, // 景区信息
      statustitle: '',
      datetime: '30:00',
      weekday: '',
      ordergoods: [],
      orderId: '',
      paytext: '立即支付',
      status: true// ture 支付,false:退款
    }
  },
  components: {
  },
  methods: {
    callphone () {
      console.log('拨号')
      wx.makePhoneCall({
        phoneNumber: this.scenic.mesField // 仅为示例，并非真实的电话号码
      })
    },
    // 返回首页
    gotohome () {
      wx.switchTab({
        url: '/pages/ticketPurchase/main'
      })
    },
    countdown (time) {
      var interval = setInterval(() => {
        console.log(this.order.ordOrderStatus)
        if (this.order.ordOrderStatus !== 101) {
          clearInterval(interval)
        }
        var stime = Date.parse(new Date(time)) + 1000 * 60 * 30
        var etime = Date.parse(new Date())
        var usedTime = stime - etime // 两个时间戳相差的毫秒数
        var level1 = usedTime / 1000
        var minutes = Math.floor(level1 / 60)
        var second = level1 % 60
        if (second < 10) {
          second = '0' + second
        }
        if (minutes >= 0) {
          this.datetime = minutes + ':' + second
        } else {
          clearInterval(interval)
          this.datetime = '已过期'
          this.paytext = '再来一单'
        }
      }, 1000)
    },
    copyTBL () {
      wx.setClipboardData({data: this.order.ordOrderSn})
    },
    // 微信支付
    wxPay () {
      if (this.paytext === '再来一单') {
        wx.switchTab({
          url: '/pages/ticketPurchase/main'
        })
      } else {
        var that = this
        this.$util.request(this.$api.Wxpay, {
          orderId: this.order.ordId
        }, 'post').then(res => {
          if (res.errno === 0) {
            const payParam = res.data
            wx.requestPayment({
              'timeStamp': payParam.timeStamp,
              'nonceStr': payParam.nonceStr,
              'package': payParam.packageValue,
              'signType': 'MD5',
              'paySign': payParam.paySign,
              'success': function (res) {
                console.log('支付成功:')
                console.log(res)
                that.status = true
                that.updateOrdCou()
              },
              'fail': function (res) {
                console.log('支付失败')
                console.log(res)
              }
            })
          }
        })
      }
    },
    // 微信退款
    wxRefund () {
      wx.showLoading({title: '退款中'})
      this.$util.request(this.$api.Wxrefund, {
        orderId: this.order.ordId,
        catId: this.order.ordCatId
      }, 'get').then(res => {
        console.log(res)
        if (res.errno === 0) {
          wx.hideLoading()
          console.log('退款成功')
          this.status = false
          this.updateOrdCou()
        } else if (res.errno === 101) {
          wx.hideLoading()
          wx.showToast({title: '该门票退票时间过期', icon: 'none', duration: 2000})
        } else {
          wx.hideLoading()
          wx.showToast({title: '退款失败!', icon: 'none', duration: 2000})
        }
      })
    },
    // 取消订单
    cancelOrder () {
    },
    // 修改订单状态和优惠券状态
    updateOrdCou () {
      this.$util.request(this.$api.OrderUpdate, {
        orderId: this.order.ordId,
        cosId: this.order.ordCouponId,
        status: this.status
      }).then(res => {
        if (res.errno === 0) {
          wx.hideLoading()
          wx.showToast({title: '退款成功', icon: 'none', duration: 2000})
          setTimeout(function () {
            wx.switchTab({
              url: '/pages/ticketPurchase/main'
            })
          }, 2001)
        }
      })
    }
  },
  created () {
    // let app = getApp()
  },
  onLoad (options) {
    wx.showLoading('加载中...')
    this.$util.request(this.$api.OrderDetail, {
      ordId: options.ordId
    }, 'get').then(res => {
      wx.hideLoading()
      console.log(res)
      this.order = res.data.order
      this.ordergoods = res.data.ordergoods
      if (this.order.ordOrderStatus === 101) {
        this.statustitle = '待付款'
        this.countdown(this.order.ordAddTime)
      } else if (this.order.ordOrderStatus === 201) {
        this.statustitle = '待使用'
        this.datetime = ''
      } else if (this.order.ordOrderStatus === 301) {
        this.statustitle = '已使用'
        this.datetime = ''
      } else {
        this.statustitle = '已取消'
        this.datetime = ''
      }
      var myddy = new Date(this.order.ordConfirmTime).getDay()
      this.order.ordConfirmTime = this.order.ordConfirmTime.split(' ')[0]
      var date = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
      this.weekday = date[myddy]
    }).catch(() => {
      this.order = {}
    })
    this.scenic = wx.getStorageSync('scenic')
  }
}
</script>

<style>
.ticketObligation-wrap{
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background: -webkit-linear-gradient(top,#00cc66,#51ee9f);
  font-size: 25rpx;
  overflow-y:scroll;
  letter-spacing: 1px;
}
.ticketObligation-wrap::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
/* 待付款及计时 */
.header-text{
  color: #fff;
  font-size: 20px;
  display: flex;
  justify-content: space-between;
  width: 94%;
  padding: 3%;
}
.header-left{
  padding-left: 15px;
}
.header-left-txt{
  letter-spacing: 2px;
}
.header-exceed{
  font-size:12px;
  margin-top:10px;
}
.go-details{
  width: 21px;
  height: 21px;
  margin-left: 5px;
  transform: translateY(4px);
}
.header-date{
  margin-left: 10px;
}
/* 使用日期、入园时间、购买数量 */
.ticket-content,.orderInformation{
  width: 88%;
  padding: 4%;
  margin: 0 auto;
  background: #fff;
  border-radius: 10px;
  position: relative;
}
.content-main{
  background: #ffffff;
}
.content-FIRST{
  display: flex;
  border-bottom: 1px dashed #E8E8E8;
  padding-bottom: 10px;
}
.content-FIRST p{
  width: 320px;
  font-size: 13px;
}
.content-FIRSTimg{
  width: 15px;
  height: 15px;
}
.content-partTwo{
  font-size: 13px;
  border-bottom: 1px dashed #E8E8E8;
  padding-top: 10px;
}
.partTwo-div{
  padding-bottom: 10px ;
}
.partTwo-left{
  margin-right: 20px;
  color: #888;
}
.partTwo-right-date{
  color: #ee4031;
  margin-right: 3px;
}
.partThree{
  padding: 10px 0;
  font-size: 13px;
  border-bottom: 1px dashed #E8E8E8;
}
.partFour{
  display: flex;
  font-size: 13px;
  padding-top: 10px;
}
.partFour-left{
  margin-right: 20px;
  color: #888;
}
.partFour-right{
  color: #ee4031;
}
.yuan1{
  position: absolute;
  width: 6px;
  height: 12px;
  border-radius:0  20px 20px 0 ;
  background-color: #26DC80;
  left: 0px;
  top:60.3%;
  }
.yuan2{
  position: absolute;
  width: 6px;
  height: 12px;
  border-radius:20px  0 0 20px;
  background-color: #26DC80;
  right: 0px;
  top:60.4%;
  }
/* 联系客服 */
.WDZN-BYJ{
  width: 88%;
  padding: 4%;
  margin: 10px auto;
  background: #fff;
  border-radius: 10px;
}
.WDZN-BYJ-main{
  display: flex;
  color: #888;
}
.supplier-wrap{
  width: 48px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.supplier-text{
  width: 105px;
}
.supplier{
  display: flex;
  width: 50%;
  border-right: 1px dashed #E8E8E8;
}
.supplier-img{
  width: 30px;
  height: 30px;
}
.service{
  display: flex;
}
.service-wrap{
  width: 48px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.service-text{
  display: grid;
  justify-content: center;
  align-content: center;
}
.service-img{
  width: 30px;
  height: 30px;
}
.text-p{
  color: #000;
  margin-bottom: 3px;
}
/* 订单信息 */
.orderInformation{
  margin-top: 10px;
}
.orderInformation-one{
  font-size: 14px;
  border-bottom: 1px dashed #E8E8E8;
  padding-bottom: 10px;
}
.orderInformation-two{
  padding-top: 10px;
  border-bottom: 1px dashed #E8E8E8;
}
.order-sty{
  display: flex;
  padding-bottom: 10px;
}
.order-sty-left{
  width: 76px;
  color: #888;
}
.order-sty-right-phone{
  letter-spacing: 0px;
}
.orderInformation-three{
  padding-top: 10px;
}
.inform{
  padding-bottom: 10px;
}
.inform-left{
  color: #888;
  margin-right: 20px;
}
.inform-right{
  letter-spacing: 0;
}
.inform-copy{
  border: 1px solid #DFDFDF;
  padding: 2px;
  font-size: 11px;
  margin-left: 10px;
}
.below-div{
  width: 100%;
  height: 100px;
}
/*尾部的支付按钮 */
.footer{
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
}
.footer-img{
  width: 20px;
  height: 20px;
  margin-right: 10px;
}
.footer-up{
  background: rgb(211, 252, 231);
  color: #00cc66;
  display: flex;
  padding: 2%;
}
.footer-substratum{
  background: #fff;
  padding: 4% 3%;
  display: flex;
  justify-content: flex-end;
  font-size: 12px;
  text-align: center;
}
.btn-delete{
  border: 1px solid #ee4031;
  color: #ee4031;
  border-radius: 20px;
  margin-right: 10px;
  padding: 3px 10px;
  line-height: 20px;
}
.btn-invoice{
  border: 1px solid #ee4031;
  color: #ee4031;
  border-radius: 20px;
  margin-right: 10px;
  padding: 3px 17px;
  line-height: 20px;
}
.btn-agreeAndContinue{
  padding: 4px 11px;
  color: #fff;
  font-size: 12px;
  border-radius: 20px;
  background: -webkit-linear-gradient(left, #FD6F18 , #FE4627);
  line-height: 20px;

}
</style>
