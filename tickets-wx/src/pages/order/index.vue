<template>
    <div class="bookingOrder-wrap">
      <!-- 票名加类型 -->
      <div class="header">
        <div class="header-main">
          {{catName}}
          <p>可选门票（{{ticketType}}）</p>
          <div class="notice" @click="gopurchase()">
            <div class="noticeMain" @click="refund">
              <img class="hintImg" src="/static/images/hintImg.svg" />
              <p>退票须知</p>
            </div>
          </div>
        </div>
      </div>
      <!-- 使用日期 -->
      <div class="orderDate">
        <div class="date-main" @click="calendar">
          <div>
            使用日期<span class="time">{{timeday}}</span><span class="week">星期{{week}}</span>
          </div>
          <img class="go-img" src="/static/images/go.svg" />
        </div>
      </div>
      <!-- 票的类别  -->
      <div class="ticketType">
        <div class="typeMain">
          <div class="type" v-for="(item, index) in ticgoods" :key="index">
            <div class="typeLeft">
              <p> {{item.gooName}} </p>
              <div :style="{'color':item.stock == 0?'#999':'#00B75C'}">(库存:{{item.stock}})</div>
            </div>
            <div class="typeRight">
              <div class="money">￥{{item.gooRetailPrice}}</div>
              <div :class="[item.num == 0 ?  'subtractNo':'subtract']" @click="subtractClick(index)">—</div>
              <div class="num">{{item.num}}</div>
              <div class="add" @click="addClick(index)">+</div>
            </div>
          </div>
          <div class="hint">
            <img class="hintImg" src="/static/images/hintImg.svg" />
            <p>{{ticSelfName}}可自助取票</p>
          </div>
        </div>
      </div>
      <!-- 用户信息 -->
      <div class="message">
        <div class="messageOne">游客信息<span class="oneRight">需<span class="number">1位</span>出行游客信息</span></div>
        <div class="messageTwo">
          <div class="ticketName">成人票<span>(填写一位成人信息)</span></div>
          <div class="messageUser">
            <p>姓名</p>
            <input class="username" placeholder="请输入证件上的实际姓名" v-model="username">
          </div>
          <div class="messageIDcard">
            <p>身份证</p>
            <input class="IDcard" maxlength="18" placeholder="请输入身份证号，可直接验票入园" v-model="Idcard">
          </div>
          <div class="phoneText">
            <p>联系电话</p>
            <input class="phone" maxlength="11" placeholder="接收确认短信及使用提醒" v-model="phone">
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
              <img class="coupon-img" src="/static/images/arch1.svg" />
              <p>优惠券</p>
            </div>
            <div class="coupon-first-right" @click="selectcou">
              <p>{{ couTips }}</p>
            </div>
          </div>
        </div>
      </div>
      <!-- 支付 -->
      <div class="footer">
        <div class="footer-main">
          <div class="footer-left">
            <p>订单金额：<span class="footer-money">￥{{showPrice}}</span></p>
          </div>
          <div class="footer-right" @click="payment">
            <p>在线支付</p>
          </div>
        </div>
      </div>
      <!-- 退票须知弹出提示 -->
      <div class="refundTic" v-if="showModel">
        <div class="tips-back">
          <div class="contents">
            <div class="title">
              退票须知
            </div>
            <div class="tips" v-if="catTicket.catIsretreat === 1">
              <p>退票规则:</p>
              <p :style="{color:'#ee4031'}">该类型门票，不能进行退票操作</p>
            </div>
            <div class="tips" v-else>
              <p>退票规则:</p>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;1、小程序预定非当日门票，订票两小时内可进行退票(不受第二条规则影响)</p>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;2、入园<span v-if="catTicket.catRetreDay === 0">当</span><span v-else>前{{catTicket.catRetreDay}}</span>天不可进行退票;预约当日门票，在12:00后不可进行退票</p>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;3、单笔订单退票，该订单每张门票<span v-if="catTicket.catRetreCost === 0">不收取退票手续费</span><span v-else>收取退票手续费{{catTicket.catRetreCost}}元/张</span></p>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;4、门票未打印情况下，可通过小程序-我的-订单，进行线上退票，若门票已打印，则门票不可进行退票</p>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;5、若预约当日未入园，且未在预约当日12:00前进行退票，则视为已验票，无法进行退票操作</p>
              <p>&nbsp;&nbsp;&nbsp;&nbsp;6、单笔订单含多张门票，进行退票操作时，需对整个订单退票操作，不支持对订单内的部分门票进行退票</p>
            </div>
            <div class="foot">
              <div class="agreement">
                <div class="checkbox" @click="checkdiv" :style="{backgroundColor:checknext?'#33CC66':'#fff',border:checknext?'1px solid #33CC66':'1px solid #E4E4E4'}">
                  <img src="/static/images/select.svg" class="img_checkbox"></div>
                下次不再自动弹出
              </div>
            </div>
          </div>
          <div class="cross" @click="cross" >
            <img class="cross-img" :style="{width:imageWidth + 'rpx', height:imageWidth + 'rpx'}" src="/static/images/cross.svg">
          </div>
        </div>
      </div>
    </div>
</template>
<script>
export default {
  data () {
    return {
      goodsId: '', // 当前商品ID
      type: '', //  缓存状态
      btntype: true,
      imageWidth: (wx.getSystemInfoSync().windowWidth * (750 / wx.getSystemInfoSync().windowWidth) * 0.1),
      ticgoods: [],
      ticGood: {},
      catTicket: {}, // 门票类别
      couTips: '',
      TotalPrice: 0,
      gooIds: '',
      catId: 0,
      showPrice: 0,
      couDiscount: 0,
      cosId: -1,
      timeday: '',
      timeweek: '',
      timeMonth: '',
      week: '',
      day: '',
      timedate: 0,
      takenum: '',
      Idcard: '',
      username: '',
      phone: '',
      orderId: '',
      couponId: '',
      addStatus: true, // 添加按钮状态
      cutStatus: false, // 减少按钮状态
      showModel: true, // 退票规则弹窗
      checknext: true, // 多选框
      catRetreCost: '', // 退票手续费
      userStatus: true, // 出游人信息状态(false:未填写完整)
      gooId: '', // 门票Id
      gooNUm: '',
      userDao: '', // 用户访问记录
      userGood: {}, // 用户门票信息
      scenicName: '', // 景区名称
      ticketType: '', // 门票类型
      catName: '', // 门票名称
      ticSelfName: '', // 自助取票门票名称
      staNum: '', // 售票数量
      catViewId: '' // 景点集合
    }
  },
  components: {},
  methods: {
    // 星期格式化
    weekStr (value) {
      console.log(value)
      if (value === 2) {
        this.week = '一'
      } else if (value === 3) {
        this.week = '二'
      } else if (value === 4) {
        this.week = '三'
      } else if (value === 5) {
        this.week = '四'
      } else if (value === 6) {
        this.week = '五'
      } else if (value === 7) {
        this.week = '六'
      } else {
        this.week = '日'
      }
    },
    // 退票须知
    refund () {
      this.showModel = true
    },
    goTicket () {
      this.showModel = false
    },
    // 叉掉提示
    cross () {
      this.showModel = false
      wx.setStorageSync('check', this.checknext)
    },
    // 退票弹窗复选框
    checkdiv () {
      this.checknext = !this.checknext
      wx.setStorageSync('check', this.checknext)
    },
    // 添加购买数量(限购判断)
    addClick (index) {
      console.log(this.ticgoods[index].stock)
      if (this.btntype) {
        if ((this.ticgoods[index].num + 1) > this.ticgoods[index].stock) {
          wx.showToast({title: '库存不足!', icon: 'none', duration: 1000})
        } else {
          if ((this.ticgoods[index].num + 1) > this.ticgoods[index].gooNumLimit) {
            wx.showToast({title: '超出购买数量!', icon: 'none', duration: 1000})
          } else {
            this.test('add', this.ticgoods[index].gooId, this.ticgoods[index].stock)
          }
        }
      }
    },
    // 修改购票数量(减少)
    subtractClick (index) {
      console.log(this.ticgoods[index].stock)
      if (this.btntype) {
        if (this.ticgoods[index].num !== 0) {
          this.test('reduce', this.ticgoods[index].gooId, this.ticgoods[index].stock)
        }
      }
    },
    test (type, goodsId, stock) {
      console.log(goodsId + '-' + type + '-' + this.timedate + '-' + stock)
      wx.showLoading({title: '加载中'})
      var that = this
      if (this.btntype) { // 当前按钮可不可按
        that.btntype = false
        this.$util.request(this.$api.TicGoodCache, {
          goodsId: goodsId,
          type: type,
          time: that.timedate,
          surplus: stock
        }).then(res => {
          that.btntype = true
          var ticGoods = that.ticgoods
          that.ticgoods = []
          for (var i = 0; i < ticGoods.length; i++) {
            if (res.data.goodsId === ticGoods[i].gooId) {
              ticGoods[i].num = res.data.number
              ticGoods[i].stock = res.data.surplus
              that.ticgoods.push(ticGoods[i])
            } else {
              that.ticgoods.push(ticGoods[i])
            }
          }
          this.operation()
          wx.hideLoading()
        })
      }
    },
    // 计算支付金额
    operation () {
      this.TotalPrice = 0
      this.showPrice = 0
      for (var item of this.ticgoods) {
        this.TotalPrice += item.gooRetailPrice * item.num
        if (this.TotalPrice === 0) {
          this.showPrice = 0
        } else {
          this.showPrice = this.TotalPrice - this.couDiscount
        }
      }
      // this.queryRedis()
    },
    // 查询库存缓存
    queryRedis () {
      var that = this
      var params = this.ticgoods
      this.$util.request(this.$api.TicGoodRedis, {
        catId: this.catId,
        Time: this.timedate,
        gooId: this.gooId,
        params: params,
        gooNum: this.gooNum,
        count: this.count,
        price: this.showPrice
      }).then(res => {
        if (res.data.returnCode === -1) {
          wx.showToast({title: '库存不足!', icon: 'none', duration: 1000})
        }
        that.ticgoods = []
        // 用户记录
        that.userGood = JSON.parse(res.data.user.params)
        for (var item of res.data.items) {
          for (var userGoo of that.userGood) {
            if (item.gooId === userGoo.gooId) {
              that.gooIds += item.gooId + ','
              item.num = userGoo.num
              that.ticgoods.push(item)
            }
          }
        }
        that.addStatus = true
        that.cutStatus = true
        wx.hideLoading()
      })
    },
    payment () {
      // 判断出游人信息是否填写
      if (Object.keys(this.Idcard).length !== 18) {
        this.userStatus = false
      }
      if (Object.keys(this.username).length === 0) {
        this.userStatus = false
      }
      if (Object.keys(this.phone).length !== 11) {
        this.userStatus = false
      }
      if (this.userStatus) {
        this.pay()
      } else {
        wx.showToast({title: '出游信息不正确或未填写', icon: 'none', duration: 1000})
        this.userStatus = true
      }
    },
    // 提交订单
    pay () {
      wx.setStorageSync('agree', this.checknext)
      console.log(this.ticgoods)
      var params = this.ticgoods
      // var viewId = JSON.parse(this.catViewId)
      this.$util.request(this.$api.OrderSubmit, {
        cosId: this.cosId,
        timedate: this.timedate,
        TotalPrice: this.showPrice,
        couDiscount: this.couDiscount,
        Idcard: this.Idcard,
        username: this.username,
        phone: this.phone,
        ordCatId: this.catId,
        params
      }, 'post').then(res => {
        wx.hideLoading()
        if (res.errno === 0) {
          this.orderId = res.data.orderId
          this.wxPay() // 调用微信支付
        }
      })
    },
    // 微信支付
    wxPay () {
      console.log(this.orderId)
      const that = this
      this.$util.request(this.$api.Wxpay, {
        orderId: this.orderId
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
              that.updateOrdCou()
            },
            'fail': function (res) {
              console.log('支付失败')
              console.log(res)
            }
          })
        }
      })
    },
    // 修改订单状态和优惠券状态
    updateOrdCou () {
      var that = this
      this.$util.request(this.$api.OrderUpdate, {
        orderId: this.orderId,
        cosId: this.cosId,
        status: true
      }).then(res => {
        if (res.errno === 0) {
          console.log('修改成功')
          that.updateStatis()
        }
      })
    },
    // 修改售票数据记录
    updateStatis () {
      this.staNum = ''
      // 售票数量
      for (var goo of this.ticgoods) {
        if (goo.num !== 0) {
          this.staNum += goo.num
        }
      }
      this.$util.request(this.$api.StatisUpdate, {
        num: this.staNum,
        staSelId: -1,
        bool: true
      }, 'post').then(res => {
        this.refreshCache()
        console.log(res)
      })
    },
    // 添加游客信息
    // addTou () {
    //   this.$util.request(this.$api.TouQuery, {
    //     touUserId: wx.getStorageSync('userInfo').useId
    //   }, 'post').then(res => {

    //   })
    // },
    // 更新缓存
    refreshCache () {
      // const keyUser = this.timedate + '-' + this.catId + '-user'
      console.log('ssssssss')
      this.$util.request(this.$api.TicGoodRedu, {
        time: this.timedate,
        catId: this.catId
      }, 'get').then(res => {
        console.log(res)
        if (res.errno === 0) {
          setTimeout(function () {
            wx.switchTab({
              url: '/pages/ticketPurchase/main'
            })
          }, 2001)
          wx.showToast({title: '购买成功', icon: 'success', duration: 2000})
        }
      })
    },
    // 查询游客信息
    queryTou () {
      this.$util.request(this.$api.TouQuery, {
        touUserId: wx.getStorageSync('userInfo').useId
      }, 'get').then(res => {
        if (res.data.total !== 0) {
          this.Idcard = res.data.items.touUcardId
          this.username = res.data.items.touTourist
          this.phone = res.data.items.touMobile
        }
      })
    },
    calendar () {
      wx.navigateTo({
        url: '/pages/calendar/main?price=' + this.ticgoods[0].gooRetailPrice
      })
    },
    gopurchase () {
      // wx.navigateTo({
      //   url: '/pages/purchase/main'
      // })
    },
    selectcou () {
      if (this.couTips !== '暂无可用优惠券') {
        if (this.TotalPrice !== 0) {
          this.$util.redirect('/pages/couponSelect/main?catId=' + this.catId + '&gooIds=' + this.gooIds + '&TotalPrice=' + this.TotalPrice)
        } else {
          wx.showToast({
            title: '请选择购买的门票',
            icon: 'none'
          })
        }
      }
    },
    // 查询用户访问记录
    userAccess () {
      this.$util.request(this.$api.TicGoodList, {
        CatId: this.catId,
        Time: this.timedate
      }).then(res => {
        // console.log(res)
        if (res.errno === 0) {
          if (res.data.coupon) {
            this.couTips = '点击使用优惠券'
          } else {
            this.couTips = '暂无可用优惠券'
          }
          this.ticSelfName = ''
          this.ticgoods = res.data.ticgoods
          for (var i = 0; i < this.ticgoods.length; i++) {
            if (this.ticgoods[i].stock > 0) {
              if (this.ticketType === '') {
                this.ticketType = this.ticgoods[i].gooName
              } else {
                this.ticketType = this.ticketType + ' | ' + this.ticgoods[i].gooName
              }
            }
          }
          this.operation()
          this.ticSelfName = this.ticSelfName.substring(0, this.ticSelfName.length - 1)
          this.ticketType = this.ticketType.substring(0, this.ticketType.length - 1)
          wx.hideLoading()
        }
      })
    }
  },
  onLoad (options) {
    this.catTicket = JSON.parse(options.tickets)
    this.catId = this.catTicket.catId
    this.catName = this.catTicket.catName
    this.catViewId = this.catTicket.catViewId
    this.catRetreCost = this.catTicket.tickets
    this.TotalPrice = 0
    this.showPrice = 0
    this.couDiscount = 0
    this.cosId = -1
    this.ticgoods = []
    this.gooIds = ''
    this.Idcard = ''
    this.username = ''
    this.phone = ''
  },
  created () {
    // let app = getApp()
  },
  onShow () {
    if (wx.getStorageSync('check')) {
      this.showModel = false
    }
    this.scenicName = wx.getStorageSync('scenic').mesName
    wx.showLoading({title: '加载中'})
    // 是否使用优惠券
    if (wx.getStorageSync('coupon') !== '') {
      if (wx.getStorageSync('coupon') === '-1') {
        this.couTips = '不使用优惠券'
      } else {
        this.couponId = wx.getStorageSync('coupon').cou.couId
        this.couDiscount = wx.getStorageSync('coupon').cou.couDiscount
        this.cosId = wx.getStorageSync('coupon').cos.cosId
        this.showPrice = this.TotalPrice - this.couDiscount
        this.couTips = '优惠￥' + this.couDiscount + ''
      }
      wx.setStorageSync('coupon', '')
    }
    // 购票时间
    if (wx.getStorageSync('datetime') === '') {
      if (this.timeday === '') {
        var curDate = new Date()
        var nextDate = curDate.getHours() < 14 ? new Date(curDate.getTime()) : new Date(curDate.getTime() + 24 * 60 * 60 * 1000)
        this.day = nextDate.getDate() < 10 ? '0' + nextDate.getDate() : nextDate.getDate() // 号
        this.timeweek = (nextDate.getDay() + 1) // 星期
        this.timeMonth = nextDate.getMonth() + 1 < 10 ? '0' + (nextDate.getMonth() + 1) : nextDate.getMonth() + 1 // 月份
        this.timeday = (nextDate.getMonth() + 1) + '月' + this.day + '日' // 日期
        this.timedate = nextDate.getFullYear() + '-' + this.timeMonth + '-' + this.day + ' 00:00:00'
      }
    } else {
      var curDates = new Date(wx.getStorageSync('datetime'))
      this.day = curDates.getDate() < 10 ? '0' + curDates.getDate() : curDates.getDate()// 号
      this.timeweek = (curDates.getDay() + 1)// 星期
      this.timeMonth = curDates.getMonth() + 1 < 10 ? '0' + (curDates.getMonth() + 1) : curDates.getMonth() + 1 // 月份
      this.timeday = (curDates.getMonth() + 1) + '月' + curDates.getDate() + '日' // 日期
      this.timedate = curDates.getFullYear() + '-' + this.timeMonth + '-' + this.day + ' 00:00:00'
      wx.removeStorageSync('datetime')
    }
    this.weekStr(this.timeweek)
    this.ticgoods = []
    this.ticketType = ''
    this.userAccess()
    this.queryTou()
  }
}
</script>

<style>
/* 弹窗底层背景 */
.refundTic{
  position: fixed;
  background-color: rgba(0, 0, 0,0.3);
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  z-index: 5;
}
.tips-back{
  position: relative;
  width: 100%;
  display: flex;
  flex-direction:column;
  top: 15%;
}
/* 退票须知弹窗 */
.contents{
  width: 80%;
  height: 100%;
  position: relative;
  color: #2B2B2B;
  background-color: #F9F7FA;
  margin: auto;
	border-radius: 10px;
  box-shadow: 2px 2px 5px rgba(0, 0, 0,0.3);
  z-index: 10;
  top: 15%;
}
/* 退票：标题 */
.title{
  height: 27px;
  font-size: 25px;
  text-align: center;
  margin-top: 10rpx;
  margin-bottom: 20rpx;
}
/* 退票规则文字 */
.tips{
  /* position: fixed; */
  height: 580rpx;
  overflow: auto;
  margin-left: 5%;
  margin-right: 3%;
  margin-bottom: 65rpx;
  line-height: 40rpx;
  font-size: 28rpx;
}
.tips::-webkit-scrollbar{
  width: 2px;
  height: 2px;
}
.foot{
  width: 100%;
  height: 60rpx;
  position: absolute;
  vertical-align:middle;
  display: flex;
  justify-content:center;
  align-items:Center;
  bottom: 0;
}
/* 同意复选框 */
.agreement{
  width: 95%;
  height: 60rpx;
  text-align: center;
  color:#5E5D5D;
  font-size: 25rpx;
  vertical-align:middle;
  display: flex;
  justify-content:center;
  align-items:Center;
  margin: 10rpx auto;
}
  /* 退票--复选框 */
.checkbox{
  width: 15px;
  height: 15px;
  position: inherit;
  margin-right: 5px;
  display: flex;
  justify-content:center;
  align-items:center;
}
  /* 叉号 */
  .cross{
    margin: 10% auto 0;
  }
/* 整个页面背景样式 */
.bookingOrder-wrap{
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color:#F5F5F5;
  font-size: 28rpx;
  overflow-y:scroll;
  /* letter-spacing: 1px; */
}
/* 下拉条宽高 */
.bookingOrder-wrap::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
/* 票名加类型外层样式 */
.header{
  width: 100%;
  background: #fff;
}
/* 内层样式 */
.header-main{
  padding: 30rpx 20rpx;
  font-size: 38rpx;
  color: #2b2b2b;
}
/* 头部标题旁边的提示 */
.header-main>p{
  font-size: 24rpx;
  color: #666;
  margin-top: 14rpx;
}
/* 购买须知提示div外层样式*/
.notice{
  width: 100%;
  color: #999;
  font-size: 22rpx;
  margin-top: 10rpx;
}
/* 购买须知内层样式 */
.noticeMain{
  padding-right: 20rpx;
  display: flex;
  align-items: center;
}
/* 购买须知提示字体样式 */
.noticeMain p{
  height: 22rpx;
  line-height: 22rpx;
}
/* 提示图片 */
.questionMarkImg{
  width: 24rpx;
  height: 24rpx;
  margin-right: 10rpx;
}
/* 使用时间 */
.orderDate{
  width: 100%;
  background: #fff;
  margin-top: 20rpx;
}
.date-main{
  padding: 26rpx 20rpx;
  font-size: 30rpx;
  color: #2b2b2b;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 30rpx;
  line-height: 30rpx;
}
.day,.week,.time{
  color: #666;
  font-size: 28rpx;
  height: 28rpx;
  line-height: 28rpx;
  margin-left: 30rpx;
  display:inline-block;
}
.time{
  letter-spacing: 0rpx;
}
.go-img{
  width: 30rpx;
  height: 30rpx;
}
/* 票的类别 */
.ticketType{
  width: 100%;
  background: #fff;
  margin-top: 20rpx;
}
.typeMain{
  padding: 26rpx 20rpx  0rpx;
}
.type{
  display: flex;
  justify-content: space-between;
  padding-bottom: 26rpx;
  align-items: center;
}
.typeStudent{
  display: flex;
  justify-content: space-between;
  padding-bottom: 18rpx;
  border-bottom: 2rpx solid #E9E9E9;
}
.typeLeft{
  display: flex;
}
.typeRight{
  display: flex;
}
.money{
  color: #ee4031;
  letter-spacing: 0rpx;
  display: flex;
  align-content: center;
  line-height: 46rpx;
}
/* 增加数量按钮 */
.add{
  width: 46rpx;
  height: 46rpx;
  /* display: flex;
  align-items: center;
  justify-content:center; */
  font-size: 35rpx;
  border-radius: 50%;
  background: #ee4031;
  color: #fff;
  line-height: 46rpx;
  text-align: center;
}
/* .addSymbol{
  font-size: 35rpx;
} */
/* 票的数量 */
.num{
  width: 54rpx;
  margin:0 auto;
  height: 46rpx;
  line-height: 46rpx;
  text-align:center;
}
/* 减少数量的按钮 */
.subtractNo{
  width: 46rpx;
  height: 46rpx;
  border-radius: 50%;
  background: #D4D2D3;
  color: #fff;
  line-height: 46rpx;
  text-align: center;
  margin-left: 20rpx;
  font-size: 16rpx;
}
.subtract{
  width: 46rpx;
  height: 46rpx;
  border-radius: 50%;
  background: #ee4031;
  color: #fff;
  line-height: 46rpx;
  text-align: center;
  margin-left: 20rpx;
  font-size: 16rpx;
}
/* 提示 */
.hint{
  color: #999;
  font-size: 22rpx;
  padding: 18rpx 0;
  display: flex;
}
.hintImg{
  width: 30rpx;
  height: 30rpx;
  margin-right: 10rpx;
}
/* 用户信息 */
.message{
  width: 100%;
  margin-top: 20rpx;
}
.messageOne{
  font-size: 34rpx;
  padding-left: 20rpx;
  margin-bottom: 20rpx;
}
.oneRight{
  font-size: 28rpx;
  margin-left: 20rpx;
  color: #666;
}
.number{
  color: #ee4031;
}
.messageTwo{
  margin-top: 20rpx;
  background: #fff;
  padding:32rpx 20rpx 20rpx;
}
/*票的类别：成人票 */
.ticketName{
  border-left: 4rpx solid #00cc66;
  margin-bottom: 24rpx;
  height: 28rpx;
  line-height: 28rpx;
  padding-left: 16rpx;
  font-size: 30rpx;
}
/* 成人票：旁边的提示信息 */
.ticketName span{
  color: #999;
  font-size: 22rpx;
  margin-left: 4rpx;
}
/* 游客信息 */
.messageUser{
  padding-top: 10rpx;
   border-top: 2rpx solid #E9E9E9;
}
.messageUser, .messageIDcard, .phoneText{
  display: flex;
  align-items:center;
  font-size: 30rpx;
}
.username{
  width: 200px;
  font-size: 28rpx;
  margin-left: 86rpx;
  height: 40px;
  z-index: 1;
}
.IDcard{
  width: 250px;
  margin-left: 58rpx;
  height: 40px;
  font-size: 28rpx;
  z-index: 1;
}
.phone{
  width: 200px;
  margin-left: 30rpx;
  height: 40px;
  font-size: 28rpx;
  z-index: 1;
}
.correct{
  width: 40rpx;
  height: 40rpx;
}
/* 优惠券 */
.coupon{
  width: 100%;
  background: #fff;
  margin: 10px auto 120rpx;
  color: #2b2b2b;
}
.coupon-main{
  background: #fff;
  padding: 20rpx;
}
.coupon-text{
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
  font-size: 22rpx;
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
  width: 35%;
  height: 49px;
  text-align: center;
  line-height: 49px;
  background: #ee4031;
  color: #fff;
  font-size: 17px;
}
.footer-money{
  color: #ee4031;
  letter-spacing: 0;
}


</style>
