<template>
  <div class="centre-wrap">
    <!-- 景点名称：套餐名 -->
    <div class="header">
      <div class="header_top">
        <img :src="userInfo.avatarUrl" class="username-img"/>
        <p class="username">{{userInfo.nickName}}</p>
      </div>
    </div>
    <!-- 订单 -->
    <div class="content" @click="goindent()">
      <div class="content-left">
        <img class="content-img" src="/static/images/content-img.svg"/>
        <p>订单</p>
      </div>
      <div class="content-right">
        <img class="go-img" src="/static/images/go.svg" />
      </div>
    </div>
    <!-- 优惠券 -->
    <div class="content" @click="gocoupon()">
      <div class="content-left">
        <img class="content-img-below" src="/static/images/c-ticket.svg"/>
        <p>优惠券</p>
      </div>
      <div class="content-right">
        <img class="go-img" src="/static/images/go.svg" />
      </div>
    </div>
    <div class="content" @click="stay">
      <div class="content-left">
        <img class="content-img-below" src="/static/images/payment.svg"/>
        <p>待入园</p>
      </div>
      <div class="content-right">
        <img class="go-img" src="/static/images/go.svg" />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      userInfo: {
        nickName: '立刻登录还有更多优惠哟',
        avatarUrl: 'http://yanxuan.nosdn.127.net/8945ae63d940cc42406c3f67019c5cb6.png'
      },
      hasLogin: false
    }
  },
  components: {
  },
  methods: {
    goindent () {
      wx.navigateTo({
        url: '/pages/indent/main'
      })
    },
    gocoupon () {
      wx.navigateTo({
        url: '/pages/eCoupon/main'
      })
    },
    stay () {
      wx.navigateTo({
        url: '/pages/stayUserList/main'
      })
    }
  },
  created () {
    // let app = getApp()
  },
  onShow () {
    console.log(this.globalData.hasLogin)
    // 获取用户的登录信息
    let userInfo = wx.getStorageSync('userInfo')
    if (userInfo !== undefined) {
      this.userInfo = userInfo
      this.hasLogin = true
    }
  }
}
</script>

<style>
.centre-wrap{
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
.centre-wrap::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
/* 用户名和头像 */
.header{
  width:100%;
	background:#fff;
   text-align: center;
   padding:15px 0;
}
.username-img{
   width: 67px;
  height: 67px;
   border-radius:50%;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
}
.username{
  font-size:15px;
}
/* 订单 */
.content{
  width:95%;
  background:#fff;
  padding:2.5%;
  display: flex;
  margin: 15px 0;
  justify-content:space-between;
  align-items:center;
}
.content-left{
  display:flex;
  font-size:15px;
}
.content-img{
  width:18px;
  height:18px;
  margin-right:10px;
  transform:translateY(2px);
}
.content-img-below{
  width:22px;
  height:25px;
  margin-right:10px;
  transform:translateY(-2px);
}
.go-img{
  width:17px;
  height:17px;
  transform:translateY(2px);
}
</style>
