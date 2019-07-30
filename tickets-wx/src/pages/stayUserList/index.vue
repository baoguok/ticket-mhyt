<template>
  <div class="stayUserList-wrap">
    <div class="help">
      <img class="help-img" src="/static/images/help.svg" />
      <span class="help-text">使用说明</span>
    </div>

    <div class="couponStyle" @click="qrcode(index)" v-for="(item, index) in listOrder" :key="index">
      <div class="split-line"></div>
      <div class="coupon-left">
        <img src="/static/images/decorateImg.svg" class="decorateImg" />
      </div>
      <div class="coupon-right">
        <div>
          <p class="coupon-name">{{item.cateName}}</p>
          <p class="coupon-universal">张数：<span>{{item.ordGoodsNum}}</span></p>
          <p class="coupon-time"><span class="valiaity">使用日期</span>：<span>{{item.time}}</span></p>
        </div>
        <div><p class="coupon-firstLine">￥<span class="coupon-number">{{item.ordActualPrice}}</span></p ></div>
      </div>
    </div>



  </div>
</template>
<script>
export default {
  data () {
    return {
      listOrder: [],
      total: 0,
      page: 1,
      limit: 7
    }
  },
  components: {
  },
  methods: {
    qrcode (index) {
      wx.navigateTo({
        url: '/pages/qRcode/main?ordId=' + this.listOrder[index].ordId
      })
    },
    // 查询订单列表
    queryOrder () {
      wx.showLoading('加载中...')
      this.$util.request(this.$api.OrderList, {
        type: 'tobeused',
        page: this.page,
        limit: this.limit
      }, 'get').then(res => {
        wx.hideLoading()
        for (var i = 0; i < res.data.item.length; i++) {
          res.data.item[i]['time'] = this.screenTime(res.data.item[i].ordConfirmTime)
          this.listOrder.push(res.data.item[i])
        }
        this.total = res.data.total
      }).catch(() => {
        this.listOrder = []
        this.total = 0
      })
    },
    screenTime (time) {
      var newtime = time.split('-')
      var date = new Date()
      var y = date.getFullYear()
      if (y === newtime[0]) {
        return time.split(' ')[0]
      } else {
        return newtime[1] + '-' + newtime[2].split(' ')[0]
      }
    }
  },
  onReachBottom: function () {
    this.page = this.page + 1
    this.queryOrder()
    if (this.limit > this.total) {
      wx.showToast({title: '暂无更多数据!', icon: 'none', duration: 1000})
    }
  },
  created () {
    // let app = getApp()
  },
  onLoad () {
    this.page = 1
    this.listOrder = []
    this.queryOrder()
  }
}
</script>

<style>
.stayUserList-wrap{
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color:#F5F5F5;
  font-size: 27rpx;
  overflow-y:scroll;
  color: #666;
  position: absolute;
}
.stayUserList-wrap::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
.couponStyle{
  width: 704rpx;
  background: #fff;
  font-size: 12px;
  margin: 0 auto 30rpx;
  border-radius: 8rpx;
  position: relative;
  display: flex;
}
.coupon-left{
  background: -webkit-linear-gradient(left top, rgb(247, 148, 62)  , rgb(255, 101, 74));
  background: -o-linear-gradient(bottom right, rgb(247, 148, 62)  , rgb(255, 101, 74));
  background: -moz-linear-gradient(bottom right, rgb(247, 148, 62) , rgb(255, 101, 74));
  background: linear-gradient(to bottom right, rgb(247, 148, 62)   , rgb(255, 101, 74));
  color: #fff;
  padding: 10px;
  border-radius: 8rpx 0 0 8rpx;
  position: relative;
  flex: 1;
  display:flex;
  align-items: center;
  justify-content: center;
}
.decorateImg {
  width: 100rpx;
  height: 100rpx;
}
.coupon-firstLine{
 padding: 24rpx 22rpx 22rpx 0;
 color: #ee4031;
}
.coupon-number{
  font-size: 40rpx;
}
.coupon-Tasks{
  letter-spacing: 1px;
  font-size: 24rpx;
  line-height: 24rpx;
}
.conpon-money{
  letter-spacing: 0px;
  margin-right: 1px;
}
.coupon-right{
  border-left: 1px dashed rgb(255, 101, 74);
  padding: 12px 0 10px  12px;
  flex: 0 0 490rpx;
  display: flex;
  justify-content: space-between;
}
.coupon-name{
  color: #2b2b2b;
  font-size: 16px;
  letter-spacing: 1px;
}
.coupon-universal{
  font-size: 13px;
  padding: 14rpx 0;
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
.split-line {
    position: relative;
    flex: 0 0 0;
    background: #f5f5f5;
}
.split-line:before, .split-line:after {
    content: '';
    position: absolute;
    width: 20rpx;
    height: 10rpx;
    background: #f5f5f5;
    left:  180rpx;
    z-index: 1;
}
.split-line:before {
    border-radius: 0 0 8px 8px;
    top: 0;
}
.split-line:after {
    border-radius: 8px 8px 0 0;
    bottom: 0;
}
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
</style>
