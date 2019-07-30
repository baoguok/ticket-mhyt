<template>
  <div class="specific-sy">
    <div class="activity">
      <p class="activity_name">{{viewPoint.vieTitle}}</p>
    <div class="publish-time">
      <p class="date" >{{vieUpdateTime}}</p>
      <div class="release-browse">
        <img class="browse" :src="browse_url" :style="{width:imgwidth + 'rpx', height:imgwidth + 'rpx'}" />
        <p class="browse-number">{{viewPoint.vieRead}}</p>
      </div>
    </div>
  </div>
  <div class="detail">
    <wxParse :content="viewPoint.vieContent" />
  </div>
  <div class="blank"></div>
  </div>
</template>

<script>
import wxParse from 'mpvue-wxparse'

export default {
  data () {
    return {
      id: '',
      vieUpdateTime: '',
      viewPoint: {},
      browse_url: '/static/images/browse.svg',
      imgwidth: (wx.getSystemInfoSync().windowWidth * (750 / wx.getSystemInfoSync().windowWidth) * 0.045)
    }
  },
  components: {
    wxParse
  },
  methods: {
    // 添加阅读量
    addRead () {
      this.$util.request(this.$api.ViewPointRead, {
        vieId: this.id
      }, 'get').then(res => {
        wx.hideLoading()
      })
    }
  },
  created () {
    // let app = getApp()
  },
  onShow () {
    wx.showShareMenu({
      withShareTicket: true
    })
  },
  onLoad (option) {
    wx.showLoading({title: '加载中！'})
    this.id = option.id
    this.viewPoint = []
    var that = this
    this.$util.request(this.$api.ViewPoint, {
      vieId: this.id
    }).then(res => {
      console.log(res)
      if (res.errno === 0) {
        that.viewPoint = res.data.items
        that.vieUpdateTime = that.viewPoint.vieUpdateTime.substring(0, 10)
        that.addRead()
      }
    })
  }
}
</script>

<style>
@import url("~mpvue-wxparse/src/wxParse.css");
.specific-sy{
  text-align:center;
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color:#F5F5F5;
  font-size: 25rpx;
  overflow-y:scroll;
  -webkit-overflow-scrolling:touch;
}
.specific-sy::-webkit-scrollbar{
  width: 0px;
  height: 0px;
  }
  .activity{
   width: 100%;
   background-color:#f5f5f5;
   padding: 50rpx 0 25rpx 0;
   border-bottom: 2rpx solid rgb(204, 204, 204);
  }
  .publish-time{
      display: flex;
      font-size: 12px;
      color: #666666;
      margin: 2%  0 0 3%;
  }
  .activity_name{
      text-align: left;
      margin-left: 3%;
      font-size: 16px;
      color: #2b2b2b;
      font-weight: bold;
  }
  .date{
      margin-right: 1%;
  }
  .detail{
      width: 96%;
      background-color:#f5f5f5;
      margin: 1% 2%;
  }
  .wxParse .p {
    margin:5px;
  }
  .ideography{
    text-align: left;
    color: #666666;
    font-size: 13px;
  }
  .correlatograph{
     width: 100%;
     margin: 1% 0 0 0;
  }

  .date-text{
      font-size: 20px;
      color: #2b2b2b;
      font-weight:bold;
      text-align: left;
      margin: 0 0 6% 2%;
  }
  .activityContent-one{
    color: #666666;
    font-size: 13px;
    text-align: left;
    margin: 0 0 2% 2%;
  }

 .remark{
   color: #666666;
   text-align: left;
   font-size: 13px;
   margin: 1% 0% 5% 2%;
 }
 .div{
   width: 100%;
   margin-top: 10%;
 }
 .centre {
   height: 38px;
   text-align:center;
   display: grid;
   align-items: center;
 }
 .blank{
    width: 100%;
    height: 30rpx;
  }
  .release-browse{
    display: flex;
    margin-left: 30rpx;
  }
  .browse{
    margin-right:10rpx;
  }
</style>
