<template>
  <div class="couponCentre-sy">
      <!-- 优惠券列表 -->
    <div class="coupon" v-for="(item ,index) in couponList" :key="index">
      <div class="coupon-up">
          <div class="coupon-up-left">
            <p class="coupon-money"><span class="money-symbol">￥</span>{{item.money}}</p>
            <p class="coupon-service-conditions">{{item.conditions}}</p>
          </div>
          <div class="coupon-up-right">
              <div class="coupon-up-right-text">
                <div class="shopname">{{item.shopname}}</div>
                <div class="service-time">{{item.time}}</div>
              </div>
              <div class="up-right-obtain-div">
                <div class="coupon-up-right-obtain">
                   <!--环形进度条-->
                  <div v-show="item.circleText" class="circle">
                    <div class="pie-right">
                      <div class="right"></div>
                     </div>
                    <div class="pie-left">
                      <div class="left"></div>
                    </div>
                      <div class="mask">
                        <p class="mask-p">已领</p>
                        <span>30%</span>
                      </div>
                    </div>
                    <img v-show="item.flag" class="stamp-img" :src="item.stamp_url" />
                </div>
                <div :class="item.isActive?'immediately-to-receive-btn':'immediately-to-alter-btn'"  @click="submit(index)">{{item.menu}}</div>
              </div>
          </div>
      </div>
      <div class="coupon-below">
          <div class="coupon-below-text">
            {{ content }}
            <span @click='handleFold(false)' v-show="maxLen  != haystack.length">
                <img class="unfold-img " src="/static/images/unfold.svg"/>
            </span>
            <span @click='handleFold(true)' v-show="maxLen  == haystack.length">
                <img class="shrink-img" src="/static/images/shrink.svg"/>
            </span>
          </div>
      </div>
      <div class="yuan1"></div>
      <div class="yuan2"></div>
    </div>
    <!-- 尾部提示 -->
    <div class="footer">~ ~ ~ 没有更多优惠券了，请您留心最新活动呦 ~ ~ ~</div> 
  </div>
</template>

<script>
const maxLen = 23
export default {
  data () {
    return {
      haystack: '',
      maxLen: maxLen,
      couponList: [{
        id: 1,
        money: '50',
        conditions: '满190元可用',
        shopname: '长隆度假区（广州长隆水上乐园）',
        time: '还剩5天到期',
        stamp_url: '/static/images/stamp.svg',
        isActive: true,
        flag: false,
        circleText: true,
        menu: '立即领取'
      },
      {
        id: 2,
        money: '50',
        conditions: '满190元可用',
        shopname: '长隆度假区（广州长隆水上乐园）',
        time: '还剩5天到期',
        stamp_url: '/static/images/stamp.svg',
        isActive: true,
        flag: false,
        circleText: true,
        menu: '立即领取'
      }]
    }
  },
  methods: {
    // 折叠面板
    handleFold (fold) {
      this.maxLen = fold ? maxLen : this.haystack.length
    },
    getData: function () {
      setTimeout(() => {
        this.haystack =
          '此优惠不可与满减、折扣商品、第二份半价活动同时享受；次优惠须在线支付方式才可享受。'
      }, 1000)
    },
    // 优惠券领取
    submit: function (index) {
      this.couponList[index].menu = '去使用'
      this.couponList[index].flag = !this.couponList[index].flag
      this.couponList[index].circleText = !this.couponList[index].circleText
      this.couponList[index].isActive = !this.couponList[index].isActive
      if (this.couponList[index].isActive === true) {
        this.activeDisplay = 'block'
        this.couponList[index].isActive = !this.couponList[index].isActive
        this.couponList[index].flag = !this.couponList[index].flag
        this.couponList[index].circleText = !this.couponList[index].circleText
      } else {
        this.activeDisplay = 'none'
      }
    }
  },
  components: {
  },
  created () {
  },
  computed: {
    content () {
      const maxLen = this.maxLen
      const haystack = this.haystack.toString()
      return haystack.length > maxLen ? haystack.slice(0, maxLen) + '...' : haystack
    }
  },
  mounted () {
    this.getData()
  }
}
</script>

<style>
.couponCentre-sy{
    text-align:center;
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background-color:#F5F5F5;
    font-size: 25rpx;
    overflow-y:scroll;
}
.couponCentre-sy::-webkit-scrollbar{
    width: 2px;
    height: 2px;
}
/* 优惠券 */
.coupon{
    width: 90%;
    padding: 2% 0 0 0;
    margin: 3% auto;
    background: #fff;
    position: relative;
    overflow: hidden;
}
.coupon-up{
    display: flex;
    padding: 0 4% 2% 4%;
}
.coupon-up-left{
    width: 25%;
    color: #FF7B06; 
}
.coupon-money{
    font-size: 25px;
    line-height: 28px;
    margin-top: 18px;
}
.money-symbol{
    font-size: 14px;
}
.coupon-service-conditions{
    margin-top: 3px;
    font-size: 10px;
  
}
.coupon-up-right{
    display: flex;
}
.coupon-up-right-text{
    padding-top: 7px;
    text-align: left;
    margin-left: 5px;
    margin-top: 17px;
}
.shopname{
    width: 150px;
    overflow: hidden;
   text-overflow: ellipsis;
   white-space:nowrap;
   font-weight: bolder;
   font-size: 14px;
   
}
.service-time{
    font-size: 10px;
    color: #888;
    line-height: 20px;
    margin-top: 3px;
}
.up-right-obtain-div{
    margin-left: 10px;
}
.coupon-up-right-obtain{
    width: 75px;
    position: relative;
    height: 50px;
    font-size: 10px;
    color: #FF7B06;
}
/* 已领取图片 */
.stamp-img{
  width: 65px;
  height: 65px;
  position: absolute;
  bottom: 6px;
  left: 20px;
}
/* 圆环进度条 */
.circle {
    position: absolute;
    height: 46px;
    width: 46px;
    border-radius: 50%;
    background: #e3e3e3;
    left: 15px;
    text-align: center;
}
.pie-right, .pie-left { 
    position: absolute;
    top: 0;
    left: 0;
    height: 46px;
    width: 46px;
    border-radius: 50%;
}
.right, .left {
    position: absolute;
    top: 0;
    left:0;
    height: 46px;
    width: 46px;
    border-radius: 50%;
    background: -webkit-linear-gradient(top,#FF7B06  , #FE4627);
}
.pie-right, .right { 
    clip: rect(0, auto, auto, 23px);
}
.pie-left, .left { 
    clip: rect(0, 23px, auto, 0);
}
.mask {
    position: absolute;
    top: 3px;
    left: 3px;
    height: 40px;
    width: 40px;
    background: #fff;
    border-radius: 50%;
    text-align: center;
    align-items: center;
}
.mask-p{
    margin-top: 6px;
    letter-spacing: 2px;
}
.right{
    transform: rotate(108deg);
}
.left{
    transform: rotate(0deg);
}
.immediately-to-receive-btn{
    font-size: 12px;
    width: 67px;
    background: -webkit-linear-gradient(left,#FF7B06  , #FE4627);
    color:#fff;
    line-height: 28px;
    height: 28px;
    border-radius: 15px;
}
.immediately-to-alter-btn{
   font-size: 12px;
    width: 67px;
    border:  1px solid #FF7B06;
    color:#FF7B06;
    line-height: 26px;
    height: 26px;
    border-radius: 15px;
}
.coupon-below{
    padding: 2% 2% 2% 3%;
    background: -webkit-linear-gradient(left, #FF7B06, #FE4627);
    color: #fff;
    width: 95%;
    font-size: 12px;
    text-align: left;
}
.coupon-below-text{
    width: 320px;
    overflow: hidden;
    -webkit-line-clamp: 1;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.shrink-img{
    width: 14px;
    height: 14px;
    margin: 0 3px 11px 0;
  }
.unfold-img{
    margin: 2px 3px 0 0 ;
    width: 14px;
    height: 14px;

  }
/* 缺口圆 */
.yuan1{
    position: absolute;
    width: 5px;
    height: 10px;
    border-radius:0 20px 20px 0 ;
    background-color: #f5f5f5;
    left: 0px;
    top:87px;
  }
  .yuan2{
    position: absolute;
    width: 5px;
    height: 10px;
    border-radius: 20px 0 0 20px  ;
    background-color: #f5f5f5;
    right: 0px;
    top:87px;
  }
  /* 尾部提示 */
  .footer{
    color: #777;
    margin: 3% 0 ;
    font-size: 11px;
  }
</style>
