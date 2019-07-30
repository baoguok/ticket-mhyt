<template>
  <div class="lineitem-sy">
    <div class="goal" >
      <div class="goal-text">{{gooName}}</div>
    </div>
    <div class="content">
      <div class="content-main">
        <div class="content-left">
          <p  class="date-p">游玩日期：{{order.ordConfirmTime}}</p>
          <p class="date-text">产品包含：<span v-for="(item, index) in ordergoods" :key="index">{{item.orgGoodsName}}、</span>{{describe}}</p>
          <p class="date-text">总价：{{order.ordActualPrice}}元</p>
        </div>
        <div class="content-right">
          <div class="day">
            <span>距离入园{{day}}天</span>
          </div>
        </div>
      </div>
      <div class="yuan1"></div>
      <div class="yuan2"></div>
    </div>

    <div class="QR-code">

      <div class="RQ">
        <div class="RQ-img-div">
          <img src="/static/images/RQ.svg" :style="{width:imgwidth + 'rpx', height:imgwidth + 'rpx'}"/>
        </div>
        <div class="verify">
          <p class="verify-text">取票码：</p>
          <p class="verify-number">{{order.ordColCode}}</p>
        </div>
        <div class="content-div">
          <p class="title">请到景区附近的取票机或景区前台取</p>
        </div>
      </div>
      <div class="share">
        <div class="share-picture">
          <img class="share-img" :src="order.ordQrCode" :style="{width:shareimgwidth + 'rpx', height:shareimgwidth + 'rpx'}"/>
          <div class="share-text picture" @click="svsaimg">保存相册</div>
        </div>
        <div class="share-picture">
          <img class="share-img" src="/static/images/friend.svg" :style="{width:shareimgwidth + 'rpx', height:shareimgwidth + 'rpx'}"/>
          <button open-type="share" class="share-text">分享好友</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      imgwidth: (wx.getSystemInfoSync().windowWidth * (750 / wx.getSystemInfoSync().windowWidth) * 0.5),
      shareimgwidth: (wx.getSystemInfoSync().windowWidth * (750 / wx.getSystemInfoSync().windowWidth) * 0.07),
      order: {},
      ordergoods: {},
      weekday: '',
      gooName: '',
      day: '',
      ordId: 0
    }
  },
  components: {
  },
  methods: {
    datedifference (sDate1, sDate2) {
      var dateSpan
      var iDays
      sDate1 = Date.parse(sDate1)
      sDate2 = Date.parse(sDate2)
      dateSpan = sDate2 - sDate1
      dateSpan = Math.abs(dateSpan)
      iDays = Math.floor(dateSpan / (24 * 3600 * 1000))
      return iDays
    },
    onShareAppMessage: function () {
      return {
        title: this.gooName,
        imageUrl: this.order.ordQrCode,
        path: '/pages/qRcode/main?ordId=' + this.ordId
      }
    },
    svsaimg () {
      wx.downloadFile({
        url: this.order.ordQrCode,
        success: function (res) {
          if (res.statusCode === 200) {
            wx.saveImageToPhotosAlbum({
              filePath: res.tempFilePath,
              success (res) {
                wx.showToast({
                  title: '保存图片成功！'
                })
              },
              fail (res) {
                wx.showToast({
                  title: '保存图片失败！'
                })
              }
            })
          }
        }
      })
    }
  },
  created () {
    // let app = getApp()
  },
  onLoad (options) {
    this.ordId = options.ordId
    wx.showLoading('加载中...')
    this.$util.request(this.$api.OrderDetail, {
      ordId: this.ordId
    }, 'get').then(res => {
      wx.hideLoading()
      console.log(res)
      this.order = res.data.order
      this.ordergoods = res.data.ordergoods
      this.gooName = this.ordergoods[0].gooName
      this.day = this.datedifference(new Date(), new Date(this.order.ordConfirmTime))
      this.order.ordConfirmTime = this.order.ordConfirmTime.split(' ')[0]
    }).catch(() => {
      this.order = {}
    })
  }
}
</script>

<style scoped>
 .lineitem-sy{
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background-color:#F5F5F5;
    font-size: 24rpx;
    overflow-y:scroll;
 }
 .lineitem-sy::-webkit-scrollbar{
    width: 2px;
    height: 2px;
  }
  .goal{
    width: 92%;
    background: #fff;
    font-size: 15px;
    color: #2b2b2b;
    margin: 0  auto;
    text-align: left;
    margin-top:4%;
    border-radius: 3px 3px 0 0;
    display: flex;
     justify-content: space-between;
  }

  .goal-text{
    width: 94%;
    margin: 4% 3% 0;
    line-height: 15px;
  }

  .content{
    width: 92%;
    margin: 0 auto;
    color: #555;
    font-size: 12px;
    background: #fff;
    position: relative;
  }
  .content-main {
    padding: 2% 0 5% 3%;
    display: flex;
    justify-content: space-between;
     border-bottom: 1px dashed rgb(223, 223, 223);
  }
  .date-p{
    color: #FF3300;
  }
  .date-text{
    line-height: 22px;
  }
  .content-left{
    width:83%;
  }
  .content-right{
    width: 14%;
    color: #fff;
  }
  .day{
    background: #FF3300;
    border-radius: 5px 0 0 5px;
    text-align: center;
    font-size: 10px;
    padding: 8%;
  }
  .content-div{
    width:89%;
    margin: 0 auto;
}
 .title{
    font-size: 11px;
    color: #888;
}
  .QR-code{
    text-align: center;
    overflow: hidden;
    width:92%;
    background: #fff;
    margin: 0 auto;
    border-radius: 0 0 3px 3px ;
  }
  .verify{
    width: 100%;
    display: flex;
    color: #F7F7F7;
    justify-content: center;
    align-items: center;
    margin: 3% auto;
    border-radius: 5px;
  }
  .verify-text{
    font-size: 13px;
    color: #2b2b2b;
    margin-left: 5%;
  }
  .verify-number{
     margin-left: 20rpx;
    font-size: 13px;
    color: #2b2b2b;
  }
  .RQ{
    width: 100%;
  }
  .RQ-img-div{
    width: 55%;
    margin: 30rpx auto 0;
  }
  .share{
    width: 80%;
    display: flex;
    font-size: 16px;
    color: #999999;
    margin: 5% auto;
    justify-content: space-between;
  }
  .share-text{
    text-align: center;
    align-items: center;
    display: grid;
    padding-left:3%;
    border: 0px;
    color: #999999;
    font-size: 16px;
    width: auto;
    height: auto;
    background-color: white;
  }
  .share-text::after{
    border: 0px;
  }
 .share-picture{
   display: flex;
 }
  .yuan1{
    position: absolute;
    width: 8px;
    height: 16px;
    border-radius:0 20px  20px 0;
    background-color: #f5f5f5;
    left: 0px;
    top:93%;
  }
  .yuan2{
    position: absolute;
    width: 8px;
    height: 16px;
    border-radius:  20px 0 0 20px ;
    background-color: #f5f5f5;
    right: 0px;
    top:93%;
  }

</style>
