<template>
  <div class="indent-wrap">
    <!-- 头部导航 -->
     <div class="testNav">
      <div :class="{'selected':tab === 'tobepaid','testTitle':true}" @click="changTab('tobepaid')">待支付</div>
      <div :class="{'selected':tab === 'tobeused','testTitle':true}" @click="changTab('tobeused')">待使用</div>
      <div :class="{'selected':tab === 'cancel','testTitle':true}" @click="changTab('cancel')">已取消</div>
      <div :class="{'selected':tab === 'alreadyused','testTitle':true}" @click="changTab('alreadyused')">已使用</div>
    </div>
    <div v-if="tab==='tobepaid'">
      <!-- 待支付 -->
      <div class="unpaid" v-for="(order,index) in listOrder" :key="index">
        <div class="unpaid-main">
          <div class="unpaid-left">
            <img class="unpaid-img" src="/static/images/cat.svg" />
          </div>
          <div class="unpaid-right">
            <div class="unpaid-text">
              <p class="writ-name">{{ order.cateName }}</p>
              <p class="writ-number">张数：{{ order.ordGoodsNum }}</p>
              <p class="writ-number">日期：{{ order.time }}</p>
              <p class="writ-money">总价：<span>￥{{ order.ordActualPrice }}</span></p>
            </div>
            <div class="pay">
              <div class="btn-pay" @click="goObligation(index)">立即支付</div>
              <div class="btn-details" @click="goObligation(index)">查看详情</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  <div v-else-if="tab==='tobeused'">
      <!-- 待使用 -->
      <div class="unpaid" v-for="(order,index) in listOrder" :key="index">
        <div class="unpaid-main">
          <div class="unpaid-left">
            <img class="unpaid-img" src="/static/images/cat.svg" />
          </div>
          <div class="unpaid-right">
            <div class="unpaid-text">
              <p class="writ-name">{{ order.cateName }}</p>
              <p class="writ-number">张数：{{ order.ordGoodsNum }}</p>
              <p class="writ-number">日期：{{ order.time }}</p>
              <p class="writ-money">总价： <span>￥{{ order.ordActualPrice }}</span></p>
            </div>
            <div class="pay">
              <div class="btn-again" @click="gotohome">再来一单</div>
              <div class="btn-details" @click="goObligation(index)">查看详情</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  <div v-else-if="tab==='cancel'">
      <!-- 已取消 -->
      <div class="unpaid" v-for="(order,index) in listOrder" :key="index">
        <div class="unpaid-main">
          <div class="unpaid-left">
            <img class="unpaid-img" src="/static/images/cat.svg" />
          </div>
          <div class="unpaid-right">
            <div class="unpaid-text">
              <p class="writ-name">{{ order.cateName }}</p>
              <p class="writ-number">张数：{{ order.ordGoodsNum }}</p>
              <p class="writ-number">日期：{{ order.time }}</p>
              <p class="writ-money">总价：<span>￥{{ order.ordActualPrice }}</span></p>
            </div>
            <div class="pay">
              <div class="btn-again" @click="gotohome">再来一单</div>
              <div class="btn-details" @click="goObligation(index)">查看详情</div>
            </div>
          </div>
        </div>
      </div>
    </div>
     <div v-else-if="tab==='alreadyused'">
      <!-- 已使用 -->
      <div class="unpaid" v-for="(order,index) in listOrder" :key="index">
        <div class="unpaid-main">
          <div class="unpaid-left">
            <img class="unpaid-img" src="/static/images/cat.svg" />
          </div>
          <div class="unpaid-right">
            <div class="unpaid-text">
              <p class="writ-name">{{ order.cateName }}</p>
              <p class="writ-number">张数：{{ order.ordGoodsNum }}</p>
              <p class="writ-number">日期：{{ order.time }}</p>
              <p class="writ-money">总价：<span>￥{{ order.ordActualPrice }}</span></p>
            </div>
            <div class="pay">
              <div class="btn-pay" @click="comment(index)" v-if="order.ordComment === 0">立即评价</div>
              <div class="btn-pay" v-else-if="order.ordComment === -1">已过期</div>
              <div class="btn-pay" v-else>已评价</div>
              <div class="btn-details" @click="goObligation(index)">查看详情</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      tab: 'tobepaid',
      listOrder: [],
      order: {},
      total: 0,
      page: 1,
      limit: 7,
      tabtwo: 'tobepaid'
    }
  },
  components: {
  },
  methods: {
    comment (index) { // 评论
      wx.navigateTo({
        url: '/pages/comment/main?ordId=' + this.listOrder[index].ordId
      })
    },
    goObligation (index) {
      this.order = this.listOrder[index]
      wx.navigateTo({
        url: '/pages/ticketObligation/main?ordId=' + this.listOrder[index].ordId
      })
    },
    // 选择状态
    changTab (index) {
      this.tab = index
      this.queryOrder()
    },
    // 返回首页
    gotohome () {
      wx.switchTab({
        url: '/pages/ticketPurchase/main'
      })
    },
    onReachBottom: function () {
      this.page = this.page + 1
      this.queryOrder()
      if (this.limit > this.total) {
        wx.showToast({title: '暂无更多数据!', icon: 'none', duration: 1000})
      }
    },
    // 查询订单列表
    queryOrder () {
      if (this.tabtwo !== this.tab) {
        this.listOrder = []
        this.tabtwo = this.tab
        this.page = 1
        this.limit = 7
      }
      wx.showLoading('加载中...')
      this.$util.request(this.$api.OrderList, {
        type: this.tab,
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
  created () {
    // let app = getApp()
  },
  onShow () {
    this.listOrder = []
    this.tabtwo = this.tab
    this.page = 1
    this.limit = 7
    this.queryOrder()
  }
}
</script>

<style>
.indent-wrap{
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color:#F5F5F5;
  font-size: 27rpx;
  overflow-y:scroll;
  letter-spacing: 1px;
}
.indent-wrap::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
/* 待支付 */
.unpaid{
  width:92%;
  padding:4%;
  background-color:#fff;
  margin:15px 0;
}
.unpaid-main{
  display: flex;
}
.unpaid-left{
  margin-right:10px;
}
.unpaid-img{
  width:76px;
  height:76px;
}
.unpaid-right{
  display:flex;
  justify-content: space-between;
  width:75%;
}
.unpaid-text{
  line-height:20px;
}
.writ-name{
  font-size:17px;
}
.writ-money span{
  letter-spacing:0;
  color:#ee4031;
}
.pay{
  justify-content:center;
  align-items:Center
}
.pay p{
  font-size:12px;
  color:#888;
  transform: translateY(2px);
}
.btn-pay{
  padding: 5px 20px;
  margin-top: 20px;
  color: #fff;
  font-size: 12px;
  border-radius: 15px;
  background: -webkit-linear-gradient(left, #FD6F18 , #FE4627);
  text-align: center;
 transform: translateY(-10px);
}
.btn-again{
  padding: 5px 20px;
  margin-top: 20px;
  color: white;
  font-size: 12px;
  border-radius: 15px;
  background: -webkit-linear-gradient(left, #1FCF6D,#1AAF5D);
  text-align: center;
  transform: translateY(-10px);
}
.btn-details{
  padding: 5px 20px;
  margin-top: 10px;
  color: white;
  font-size: 12px;
  border-radius: 15px;
  background: -webkit-linear-gradient(left, #0498FC,#007ACC);
  text-align: center;
  transform: translateY(-10px);
}
.testNav {
	line-height: 40px;
	display: flex;
	border-bottom: 1px solid #DDDDDD;
	background: #fff;
  justify-content: space-around;
  align-items: center;
  font-size: 30rpx;
	}
.selected {
  color: #00cc66;
  border-bottom: 1px solid #00cc66;
  text-align: center;
}
</style>
