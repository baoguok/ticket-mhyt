<template>
  <div class="ticketPurchase-sy">
    <!-- 顶部图片 -->
    <div class="scenicArea-header" @click="goPhotos()">
      <swiper class="scenicArea-swiper" indicator-dots="true" autoplay="true" interval="5000" duration="500">
        <block v-for="(item, index) in viewList" :key="index">
          <swiper-item>
            <image class="scenicArea-img" :src="item.viePicUrl[0]"/>
          </swiper-item>
        </block>
      </swiper>
      <div class="img-text" >查看景点列表</div>
      <div class="img-number number">{{viewNum}}</div>
    </div>
    <!-- 门票的主题部分 -->
    <!-- 景区名字 -->
    <div class="tickets-content">
      <div class="tickets-content-main">
        <div class="content-main-left">
          <p class="content-name">{{mesList.mesName}} </p>
          <p class="content-describe">让你的假期，更加精彩</p>
        </div>
      </div>
    </div>
    <div class="blank-div"></div>
    <!-- 开放时间 -->
    <div class="openingHours">
      <div class="openingHours-main">
        <div class="openingHours-left">
          <img class="openingHours-left-img" src="/static/images/indent.svg" />
          <p><span>开放时间: {{mesList.mesOpen}}</span></p>
        </div>
      </div>
    </div>
    <!-- 地址 -->
    <div class="address" @click="oppenmap()">
      <div class="address-main">
        <div class="address-left">
          <img class="openingHours-left-img" src="/static/images/address.svg" />
          <p>{{mesList.mesAddress}}</p >
        </div>
        <div class="address-right">
          <p>导航</p>
          <img class="address-right-img" src="/static/images/go.svg"/>
        </div>
      </div>
    </div>

    <!-- 优惠券领取 -->
    <div class="couponing">
      <div class="couponing-up"> <!--   @click="gocouponCentre()" 这个是查询更多优惠券的方法 -->
        <div class="slash1"></div>
        <div class="slash2"></div>
        <p class="couponing-fine">优</p>
        <div class="dot"></div>
        <p>惠</p>
        <div class="dot"></div>
        <p class="couponing-ticket">券</p>
        <div class="slash2"></div>
        <div class="slash1"></div>
      </div>
      <div class="couponing-below" v-for="(item,index) in couponList" :key="index">
        <div class="couponing-below-left">
          <div class="couponing-below-left-money number"><span class="couponing-below-left-symbol ">￥ </span>{{item.couDiscount}}</div>
          <div class="coupoing-below-left-condition">满{{item.couMin}}元可用</div>
        </div>
        <div class="coupoing-below-right">
          <div class="use-toponymy">
            <p class="use-toponymy-name">{{item.couName}}</p>
            <p class="use-toponymy-text">{{item.couTag}}</p>
          </div>
          <div class="toponymy-draw">
            <div :class="isActive === item.couId?'toponymy-draw-alter':'toponymy-draw-btn'" :data-id="item.couId" :data-index="index" @click="getCoupon" >{{item.menu}}</div>
            <!-- <div class="number">{{item.couGetCount}}人已领</div> -->
          </div>
        </div>
      </div>
    </div>
    <!-- 门票类别 -->
    <div class="tickets-text">
      <div class="tickets-text-main">
        <img class="tickets-text-main-img" src="/static/images/tickets.svg"/>
        <p>门票</p>
      </div>
    </div>
    <!-- 门票 -->
    <div class="ticket-details" v-for="(tickets,index) in ticCategoryList" :key="index">
      <div class="ticket-details-main">
        <div class="ticket-details-main-left">
          <p class="ticket-scenic">{{tickets.catName}}</p>
          <div class="ticket-reserve">
            <img class="hint-img" src="/static/images/reserve.svg"/>
            <span>{{tickets.catKeywords}}</span>
          </div>
          <div class="ticket-supplier">
            <img class="shop-img" src="/static/images/supplier.svg">
            <span>{{tickets.catDesc}}</span>
          </div>
          </div>
        <div class="ticket-details-main-right">
          <p class="ticket-money number"><span class="money-symbol">￥</span>{{tickets.catPrice}}<span class="rise-text">起</span></p>
          <div class="ticket-btn" @click="gobooking(tickets)">
            立即预订
          </div>
        </div>
    </div>
    </div>

    <!-- 景点信息 -->
    <div class="attractions">
      <div class="attractions-main">
        <div class="attractions-main-head">景区信息</div>
        <div class="attractions-main-content" >
          <div class="attractions-content-left">预定须知:</div>
          <div class="attractions-content-right">{{mesList.mesNotice}}</div>
        </div>
        <div class="attractions-main-content" >
          <div class="attractions-content-left">优惠政策:</div>
          <div class="attractions-content-right">{{mesList.mesOffer}}</div>
        </div>
        <div class="attractions-more" @click="goattractions()">
          <p>查看全部景区信息</p>
          <img class="attractions-img"  src="/static/images/go.svg"/>
        </div>
      </div>
    </div>
    <!-- 用户评论 -->
    <div class="user-comment">
      <div class="user-comment-main" @click="goCommentList()">
        <img class="user-comment-img" src="/static/images/discuss.svg"/>
        <p>用户评论</p>
        <p class="user-comment-number">（<span class="number">{{comUserCount}}</span>人点评）</p>
      </div>
    </div>
    <!-- 评论内容 -->
    <div class="retext"  v-for="(comment,ids) in comList" :key="ids">
      <div class="retext-main">
        <div class="retext-main-first">
          <img :src="comment.avatar" class="retext-username-img"/>
          <div class="retext-main-first-right">
            <p class="retext-username number">{{comment.nickname}}</p>
            <p  class="retext-time number">{{comment.addTime}}</p>
          </div>
        </div>
        <div class="retext-main-second">
          <p>{{comment.content}}</p>
        </div>
        <div class="retext-main-thirdly">
          <img class="retext-main-thirdly-img" :src="item"  v-for="(item,index) in comment.picList" :key="index" />
          <!-- <div class="retext-main-thirdly-div">共9张</div> -->
        </div>
      </div>
    </div>
    <div class="attractions-more more" @click="goCommentList()">
      <p>查看全部评论</p>
      <img class="attractions-img"  src="/static/images/go.svg"/>
    </div>
  <!-- 自定义弹框开始 -->
  <div class="container" v-if="showModel">
    <div class="contents">
      <div class='title'>
        需要您的授权
      </div>
    <div class='tips'>
      为了提供更加全面的服务<br/>请在稍后的提示框中点击"允许"
    </div>
    <div class='wx_tips'>
      <div class='wx_tips_title'>智慧景区官网   申请</div>
      <div class='wx_tips_content'>获取您的昵称、头像、地区及性别</div>
      <div class='wx_tips_information'>
        <image class='wx_tips_image' src='/static/images/ticket.svg'/>
        <div class='wx_tips_text'>
          <div class='wx_tips_text_title'>微信个人信息</div>
          <div class='wx_tips_text_content'>尊敬的用户</div>
        </div>
      </div>
        <div class="wx_tips_div">
          <div class='wx_tips_cancel'>取消</div>
          <div class='wx_tips_define'>允许</div>
          <div class='wx_tips_circle'>允许</div>
        </div>
      </div>
        <div class='wx_button'>
          <button class='getuser' open-type="getUserInfo" @getuserinfo="agreeGetUser">我知道了 </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
var QQMapWX = require('../../../static/js/qqmap-wx-jssdk')
export default {
  data () {
    return {
      key: 'CGHBZ-BCZKP-OTRDM-VFSC7-5TQHK-BEB4K',
      adCount: 0,
      adList: {},
      mesList: {},
      comList: '',
      comUserCount: 0,
      comCount: 0,
      couponList: [],
      viewList: [],
      ticCategoryList: '',
      num: 0,
      viewNum: '', // 文章数量
      isShow: false,
      showModel: false,
      txt: '查看更多（2）',
      url: '/static/images/more.svg',
      isActive: 0,
      menu: '免费领取'
    }
  },
  components: {
  },
  methods: {
    // 截取字符串
    subStr (str) {
      console.log(str)
      var viewurl = str.substring(1, str.length)
      console.log(viewurl)
      return viewurl
    },
    // 导航
    oppenmap () {
      wx.showLoading({
        title: '导航加载中'
      })
      var that = this
      that.mapCtx = wx.createMapContext('myMap')
      that.qqmapsdk = new QQMapWX({ key: this.key })
      that.qqmapsdk.geocoder({
        address: that.mesList.mesAddress,
        reliability: 10,
        success: function (res) {
          wx.hideLoading()
          wx.openLocation({
            latitude: res.result.location.lat,
            longitude: res.result.location.lng,
            scale: 16
          })
        },
        fail: function (error) {
          console.error(error)
        }
      })
    },
    // 商家图片
    goPhotos () {
      wx.navigateTo({
        url: '/pages/viewList/main'
      })
    },
    // 首页信息
    getIndexData () {
      this.$util.request(this.$api.IndexUrl, {
      }).then(res => {
        if (res.errno === 0) {
          this.viewList = res.data.viewList.slice(0, 5)
          this.viewNum = res.data.viewList.length
          this.mesList = res.data.mesList[0]
          this.comCount = res.data.comList.count
          this.comUserCount = res.data.comList.userCount
          this.comList = res.data.comList.data
          this.couponList = res.data.couponList
          wx.setStorageSync('scenic', this.mesList)
          for (const key in this.couponList) {
            if (this.couponList.hasOwnProperty(key)) {
              const element = this.couponList[key]
              element.menu = this.menu
            }
          }
          this.ticCategoryList = res.data.ticCategoryList
        }
      })
    },
    show (index) {
      this.num = index
      this.isShow = !this.isShow
    },
    getCoupon (e) {
      let that = this
      let couponId = e.mp.currentTarget.dataset.id
      let index = e.mp.currentTarget.dataset.index
      this.$util.request(this.$api.CouponReceive, {
        couponId: couponId
      }, 'POST').then(res => {
        if (res.errno === 0) {
          that.couponList[index].menu = '已领取'
          that.isActive = couponId
          if (that.isActive === couponId) {
            that.activeDisplay = 'none'
          } else {
            that.activeDisplay = 'block'
          }
          that.getIndexData()
        } else {
          that.$util.showErrorToast(res.errmsg)
        }
      })
    },
    gocouponCentre () {
      wx.navigateTo({
        url: '/pages/couponCentre/main'
      })
    },
    gobooking (tickets) {
      var catTic = JSON.stringify(tickets)
      this.$util.redirect('/pages/order/main?tickets=' + catTic)
    },
    goattractions () {
      wx.navigateTo({
        url: '/pages/attractions/main'
      })
    },
    goCommentList () {
      wx.navigateTo({
        url: '/pages/userEvaluation/main'
      })
    },
    agreeGetUser: function (e) {
      if (e.mp.detail.userInfo === undefined) {
        // this.globalData.hasLogin = false
        return
      }
      this.$user.checkLogin().catch(() => {
        this.$user.loginByWeixin(e.mp.detail.userInfo).then(res => {
          // this.globalData.hasLogin = true
          this.showModel = false
          this.getIndexData()
          wx.showTabBar()
        }).catch(() => {
          // this.globalData.hasLogin = false
          this.$util.showErrorToast('微信登录失败')
        })
      })
    }
  },
  created () {
    // let app = getApp()
  },
  onLoad () {
    this.getIndexData()
  },
  onShow () {
    wx.showShareMenu({
      withShareTicket: true
    })
    this.getIndexData()
    let that = this
    wx.getSetting({
      success: function (res) {
        if (!res.authSetting['scope.userInfo']) {
          that.showModel = true
          wx.hideTabBar()
        } else if (wx.getStorageSync('userInfo') === undefined || wx.getStorageSync('userInfo') === '') {
          that.showModel = true
          wx.hideTabBar()
        }
      }
    })
  }
}
</script>

<style scoped>
  .ticketPurchase-sy{
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background-color:#F5F5F5;
    font-size: 25rpx;
    overflow-y:scroll;
    /* letter-spacing: 1px; */
  }
  .ticketPurchase-sy::-webkit-scrollbar{
    width: 0px;
    height: 0px;
  }
  /* 顶部部分 */
  .scenicArea-header{
    background: #F5F5F5;
  }
  /* 顶部轮播图 */
  .scenicArea-swiper{
    width: 100%;
    height: 195px;
  }
  /* 轮播图片 */
  .scenicArea-img{
    width: 100%;
    height: 205px;
    position: relative;
  }
  .img-text {
    position: absolute;
    color: #eeeeee;
    top: 170rpx;
    left: 260rpx;
    background:rgba(0,0,0,0.4);
    width: 120px;
    font-size: 26rpx;
    line-height: 50rpx;
    text-align: center;
    border-radius: 20px;
  }
  /* 景点图片的张数 */
  .img-number{
    position: absolute;
    top: 155px;
    left: 310px;
    width: 44px;
    line-height: 22px;
    background: #000;
    color: #fff;
    border-radius: 15px;
    background:rgba(0,0,0,0.4);
    font-size: 10px;
    text-align: center;
  }
  /*门票的主题部分 */
  /* 景区名字 */
  .tickets-content{
    width: 92%;
    padding: 4% 4% 0 4%;
    background: #fff;
     position: absolute;
     top: 190px;
     border-radius: 10px 10px 0 0;
  }
  .content-name{
    font-size: 20px;
    color: #060606;
  }
  .content-describe{
    color: #888;
    font-size: 11px;
    margin-top: 5px;
  }
  .content-main-left{
    width: 65%;
    /* border-right: 1px solid #F6F6F6; */
  }
  .tickets-content-main{
    border-bottom: 1px solid #F6F6F6;
    padding-bottom: 4%;
    display: flex;
  }
  .content-main-right{
    width: 35%;
    color: #888;
    display: grid;
    justify-content: center;
    align-items: center;
  }
  .content-username-discuss{
    display: flex;
  }
  .content-main-right-img{
    width: 17px;
    height: 17px;
    margin-right: 5px;
  }
  .blank-div{
    width: 100%;
    height: 59px;
    background: #fff;
  }
  /* 开放时间 */
  .openingHours{
    width: 92%;
    padding: 4% 4% 0 4%;
    background: #fff;
  }
.openingHours-main{
  border-bottom: 1px solid #F6F6F6;
  padding: 4% 0;
  display: flex;
  justify-content: space-between;
}
  .openingHours-left{
    display: flex;
    font-size: 13px;
  }
  .openingHours-left-img{
    width: 17px;
    height: 17px;
    margin-right: 7px;
  }
  .openingHours-right{
    display: flex;
    color: #888;
    font-size: 13px;
  }
  .openingHours-right-img{
    width:17px;
    height: 17px;
    margin: 1px 0 0 12px;
  }
/* 地址 */
.address{
    width: 92%;
    padding: 4% 4% 0 4%;
    background: #fff;
  }
  .address-main{
    padding-bottom: 4%;
    display: flex;
    justify-content: space-between;
  }
  .address-left{
    display: flex;
    font-size: 13px;
  }
  .address-left-img{
    width: 17px;
    height: 17px;
    margin-right: 7px;
  }
  .address-right{
    display: flex;
    color: #888;
    font-size: 13px;
  }
  .address-right p{
    width: 60rpx;
  }
  .address-right-img{
    width: 17px;
    height: 17px;
    margin: 1px 0 0 2rpx;
  }
  /* 优惠券领取 */
  .couponing{
  width: 90%;
  padding: 3% 5% 3% 5%;
  background: #fff;
  margin: 3% 0 0 0;
  color: #555;
  text-align: center;
}
.couponing-up{
  display: flex;
  justify-content: center;
  align-items: center;
  color: #000;
  font-size: 15px;
}
.slash1{
  width: 2px;
  height: 10px;
  background-color: #9847fc;
  transform: rotate(30deg);
  margin: 0 2.5px;
}
.slash2{
  width: 2px;
  height: 15px;
  background-color: #e53d88;
  transform: rotate(30deg);
  margin: 0 2.5px;
}
.dot{
  width: 3px;
  height: 3px;
  background-color: #ff7590;
  border-radius: 50%;
  margin: 0 2.5px;
}
.couponing-fine{
  margin-left: 12px;
}
.couponing-ticket{
  margin-right: 12px;
}
.couponing-below{
  display: flex;
  margin-top:50rpx;
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
.use-toponymy{
  padding: 3px 0;
  text-align: left;
  line-height: 22px;
  width: 80px;
}
.use-toponymy-name{
  font-size: 15px;
  color: #2b2b2b;
}
.use-toponymy-text{
  color: #888;
}
.toponymy-draw{
  line-height: 22px;
  font-size: 11px;
  color: #888;
}
/* 点击前的样式 */
.toponymy-draw-btn{
  padding: 4px 10px;
  color: #fff;
  font-size: 12px;
  border-radius: 15px;
  background: -webkit-linear-gradient(left, #FD6F18 , #FE4627);
  line-height: 20px;
}
/* 点击后的样式 */
.toponymy-draw-alter{
  width: 70px;
  font-size: 12px;
  line-height: 26px;
  color:#888;
  border-radius: 15px;
  border:  1px solid #888;
}
/*门票类别 */
.tickets-text{
  width: 94%;
  padding: 3% 3% 0 3%;
  background: #fff;
  margin-top: 3%;
}
.tickets-text-main{
  display: flex;
  padding-bottom: 4%;
  border-bottom: 1px solid #F6F6F6;
  font-size: 18px;
}
.tickets-text-main-img{
  width: 30px;
  height: 30px;
  margin-right: 3%;
}
/* 票的样式*/
.adult-ticket{
  width: 94%;
  padding: 3% 3% 0 3%;
  background: #fff;
}
.adult-ticket-main{
   border-bottom: 1px solid #F6F6F6;
   display: flex;
   padding-bottom: 3%;
   font-size: 16px;
   justify-content: space-between;
}
.adult-ticket-main-left{
  color: #000;
}
.adult-ticket-main-right{
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
}
.adult-ticket-main-right-img{
  width: 15px;
  height: 15px;
  margin: 8px 0 0 5px;
}
.ticket-money{
  color: #ee4031;
}
.money-symbol{
  font-size: 12px;
}
.rise-text{
  font-size: 10px;
  color: #888;
  margin-left: 3px;
}
/* 票价详情 */
.ticket-details{
  width: 92%;
  padding: 4% 4% 0 4%;
  background:#fff;
}
.ticket-details-main{
  border-bottom: 1px solid #EFEFEF;
  display: flex;
  justify-content: space-between;
  padding-bottom: 4%;
}
.ticket-details-main-left{
  width: 80%;
  color: #888;
}
.ticket-scenic{
  color: #000;
  font-size: 14px;
  width: 240px;
}
.ticket-reserve{
  color: #00CC66;
  display: flex;
  align-items: center;
}
.ticket-supplier{
  width: 90%;
  display: flex;
  justify-content:left;
  align-items: center;
}
.hint-img,.shop-img{
  width: 15px;
  height: 15px;
  margin-right: 5px;
}
.ticket-details-main-right{
  font-size: 20px;
  padding-top: 3%;
}
.ticket-btn{
  padding: 4px 10px;
  color: #fff;
  font-size: 12px;
  border-radius: 15px;
  background: -webkit-linear-gradient(left, #FD6F18 , #FE4627);
  line-height: 20px;
  text-align: center;

}
/* 查看更多
.more-text{
  width: 100%;
  padding: 3% 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.more-img{
  width: 15px;
  height: 15px;
  transform:translateY(5px);
} */
/* 景点信息 */
.attractions{
  width: 92%;
  padding: 4% 4% 0 4%;
  background: #ffffff;
  margin-top: 3%;
}
.attractions-main{
  padding-bottom: 4%;
}
.attractions-main-head{
  font-size: 16px;
  font-weight: bolder;
  padding-bottom: 3%;
}
.attractions-main-content{
  display: flex;
  font-size: 14px;
  padding: 3% 0;
}
.attractions-content-left{
  width: 38%;
  color: #888;
}
.attractions-content-right{
  color: #2b2b2b;
  overflow: hidden;
  -webkit-line-clamp: 2;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
}
.attractions-date{
   width: 38%;
  color: #888;
}
.attractions-time{
  color: #2b2b2b;
}
.attractions-content-time{
  display: flex;
  padding-bottom: 3%;
}
.attractions-content-time-left{
  color: #888;
  width: 23%;

}
.attractions-content-time-right{
  color: #2b2b2b;
}
.attractions-more{
  color: #888;
  padding-top: 3%;
  display:flex;
  border-top:2rpx solid #f6f6f6;
  justify-content: center;
}
.attractions-img{
  width: 15px;
  height: 15px;
  margin-left: 2px;
  transform:translateY(1px);
}
/* 用户评论 */
.user-comment{
  width: 92%;
  padding: 4% 4% 0 4%;
  background: #ffffff;
  margin-top: 3%;
}
.user-comment-main{
  padding-bottom: 4%;
  border-bottom: 1px solid #F6F6F6;
  display: flex;
  align-items: center;
  justify-content: left;
  font-size: 13px;
}
.user-comment-img{
  width: 18px;
  height: 18px;
  margin-right: 10px;
}
.user-comment-number{
  font-size: 11px;
  color: #888;
  margin-left: 10px;
}
.more{
  background:#ffffff;
  padding-bottom: 3%;
  margin-bottom:3%;
}
/* 评论的具体内容 */
.retext{
  width: 94%;
  padding: 3% 3% 0 3%;
  background: #fff;
}
.retext-main{
  padding-bottom: 3%;
  border-bottom: 1px solid #F6F6F6;
}
.retext-main-first{
  display: flex;
  margin-bottom: 20px;
}
.retext-username-img{
  width: 40px;
  height: 40px;
  border-radius:50%;
  margin-right: 5px;
}
.retext-username{
  font-size: 13px;
}
.retext-time{
  font-size: 11px;
  color: #888;
}
.retext-main-second{
  font-size: 13px;
  padding: 0 0 0 45px;
  margin-bottom: 10px;
}
.retext-main-thirdly{
  display: flex;
  padding: 0 0 0 45px;
}
.retext-main-thirdly-img{
  width: 70px;
  height: 70px;
  margin-right: 5px;
}
.retext-main-thirdly-div{
  width: 70px;
  height: 70px;
  background: #EAEAEA;
  color: #555;
  font-size: 13px;
  line-height: 70px;
  text-align: center;
}
.number{
  letter-spacing: 0px;
}
/* 自定义弹框 */
.ss{
  position: fixed;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
}
.wx_tips{
  /* margin-top: 20px; */
  /* width: 80%;
	margin-left: 10%;
  margin-bottom: 20px; */
  background: #fff;
  padding: 26rpx ;
  margin: 40rpx 80rpx;
  border-radius: 8rpx;

}
.wx_tips_title{
  text-align: left;
  font-size: 26rpx;
}
.wx_tips_content{
  margin-top: 10px;
  text-align: left;
  font-size: 24rpx;
  /* font-weight: 700; */
}
.wx_tips_image{
  width: 100rpx;
  height: 100rpx;
  border-radius: 5px;
  float: left;
}
.wx_tips_text{
  float: left;
  margin-left: 15px;
  height: 100rpx;
}
.wx_tips_text_title{
  font-size: 26rpx;
  height: 50rpx;
  line-height: 50rpx;
  color: #2B2B2B;
}
.wx_tips_text_content{
   font-size: 24rpx;
  height: 50rpx;
  line-height: 50rpx;
  color: #666;
}
.wx_tips_information{
  padding: 10px 0px;
  border-top: 1rpx solid #dddddd;
  /* border-bottom: 1rpx solid #dddddd; */
  text-align: left;
  height: 110rpx;
  margin: 10px 0px;
}
.wx_tips_div{
  display: flex;
}
.wx_tips_cancel{
  /* float: left; */
  width: 40%;
  height: 60rpx;
  font-size: 20rpx;
  line-height: 60rpx;
  border-radius: 5px;
  background-color: #EDEDED;

}
.wx_tips_define{
  font-size: 20rpx;
  line-height: 60rpx;
  /* float: left; */
  width: 40%;
  height: 60rpx;
  border-radius: 5px;
  background-color: #07C160;
  color: white;
  margin-left: 24%;
}
.wx_tips_circle{
  margin-top: -30rpx;
  position: absolute;
  z-index: 1;
  border-radius: 50%;
  width: 120rpx;
  height: 120rpx;
  border: 10rpx solid #fff;
  text-align: center;
  line-height: 120rpx;
  left: 70%;
  color: #F9F7FA;
  background:  #07C160;
  box-shadow:0px 0px 20rpx rgb(201, 201, 201);
}
.container{
  position: fixed;
  background-color: rgba(0, 0, 0,0.3);
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
}
.contents{
  color: #2B2B2B;
  background-color: #F9F7FA;
  width: 80%;
	border-radius: 10px;
	text-align: center;
	top: 18%;
  box-shadow: 2px 2px 5px rgba(0, 0, 0,0.3);
  z-index: 99;
}

.title{
  margin-top: 20px;
  text-align: center;
  font-size: 36rpx;
  /* font-weight: 700; */
}
.tips{
  width:80%;
  margin-left: 10%;
  font-size: 28rpx;
  margin-top: 10px;
  color: #666;
  letter-spacing: 2rpx;
}
.wx_button{
  margin-bottom: 20px;
  margin-top: 71rpx;
}
.getuser{
  width: 70%;
  height: 70rpx;
  border-radius:25px;
  box-shadow: 0px;
  background-color:#07C160;
  color: white;
  font-size: 24rpx;
  line-height: 70rpx;
}
.getuser:active{
  background-color:#06AA55;
}
.getuser::after{
  border: 0;
  border-radius: 0px;
  box-shadow: 0px;
}
</style>
