<template>
  <div class="back">
    <div class="game-sy">
      <div class="strategy" v-for="(item,index) in viewList" :key="item.picUrl" @click="goTopic(index)">
        <div class="strategy-main">
          <img class="activity-img" :src="item.viePicUrl[0]" />
          <div class="releaseTime">
            <div class="releaseTime-main">
              <p class="activity-name">
                {{item.vieTitle}}
                <img class="browse" :src="browse_url" :style="{width:imgwidth + 'rpx', height:imgwidth + 'rpx'}" />
                {{item.vieRead}}
              </p>
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
      page: 1,
      size: 3,
      allTotal: '',
      totalPage: '',
      viewList: [],
      browse_url: '/static/images/browse.svg',
      praise_url: '/static/images/praise.svg',
      imgwidth: (wx.getSystemInfoSync().windowWidth * (750 / wx.getSystemInfoSync().windowWidth) * 0.045)
    }
  },
  components: {
  },
  methods: {
    goTopic (index) {
      let id = this.viewList[index].vieId
      wx.navigateTo({
        url: '/pages/viewpoint/main?id=' + id
      })
    },
    getViewList () {
      var that = this
      this.$util.request(this.$api.ViewList, {
        page: this.page,
        limit: this.size
      }).then(res => {
        if (res.errno === 0) {
          that.viewList = that.viewList.concat(res.data.items)
          that.allTotal = res.data.allTotal
          that.totalPage = that.allTotal / that.size
          wx.hideLoading()
        }
      })
    }
  },
  created () {
  },
  onShow () {
    wx.showLoading({title: '加载中！'})
    this.viewList = []
    this.page = 1
    this.getViewList()
  },
  // 上拉加载
  onReachBottom: function () {
    console.log('上拉加载')
    var that = this
    if (that.totalPage > that.page) {
      wx.showLoading({title: '加载中'})
      setTimeout(function () {
        wx.hideLoading()
        that.page = that.page + 1
        that.getViewList()
      }, 500)
    } else {
      wx.showToast({title: '没有更多景点信息', icon: 'none', duration: 1000})
    }
  }
}
</script>

<style>
  /* 底层背景 */
  .back{
    width: 100%;
    height: 100%;
    position: absolute;
    background-color:#F5F5F5;
  }
  .game-sy{
    /* width: 100%;
    height: 100%; */
    /* position: fixed; */
    top: 0;
    left: 0;
    background-color:#F5F5F5;
    font-size: 25rpx;
    overflow-y:scroll;
    -webkit-overflow-scrolling:touch;
  }
  .game-sy::-webkit-scrollbar{
    width: 0px;
    height: 0px;
  }
  .strategy{
    width: 94%;
    border-radius: 3px;
    background-color:#fff;
    margin: 3% auto;
  }
  .activity-img{
    width: 100%;
    border-radius: 3px 3px 0 0;
    height: 185px;
  }
  .red-img{
    width: 5%;
    height: 40rpx;
  }
  .releaseTime{
    width: 100%;
    font-size: 12px;
  }
  .releaseTime-main{
    padding: 3%;
    display: flex;
  }
  .activity-name{
    font-size: 16px;
    color: #2b2b2b;
    padding-bottom: 1%;
    display: flex;
    align-items: center;
  }
  .date{
   width: 50%;
  }
  .browse{
    margin: auto 6rpx auto 20rpx;
  }
  .div{
    width: 100%;
    margin: 20%;
  }
   .release-date{
    display: flex;
    color: #888;
  }
  .release-browse{
    display: flex;
  }
  .release-date{
    display: flex;
  }
</style>
