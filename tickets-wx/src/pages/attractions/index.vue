<template>
  <div class="attractions-wrap">
    <!-- 头部导航 -->
    <div class="header">
      <p class="header-left">景区信息</p>
      <!-- <p class="headder-right">景点简介</p> -->
    </div>
    <div class="blankdiv"></div>
    <!-- 优待政策 -->
    <div class="preferentialTreatment">
      <div class="preferentialTreatment-main">
        <div class="prefer-ticket-name">优待政策</div>
        <div class="prefer-text">{{messageList.mesOffer}}</div>
      </div>
    </div>
    <!-- 开放时间 -->
    <div class="preferentialTreatment">
      <div class="preferentialTreatment-main">
        <div class="prefer-ticket-name">开放时间</div>
        <div class="prefer-text">{{messageList.mesOpen}}</div>
      </div>
    </div>
    <!-- 预定须知 -->
    <div class="preferentialTreatment">
      <div class="preferentialTreatment-main">
        <div class="prefer-ticket-name">预定须知</div>
        <div class="prefer-text">{{messageList.mesNotice}}</div>
      </div>
    </div>
    <!-- 特别提示 -->
    <div class="preferentialTreatment">
      <div class="preferentialTreatment-main">
        <div class="prefer-ticket-name">特别提示</div>
        <div class="prefer-text">{{messageList.mesTitle}}</div>
      </div>
    </div>
    <!-- 建议时长 -->
    <div class="preferentialTreatment">
      <div class="preferentialTreatment-main">
        <div class="prefer-ticket-name">建议时长</div>
        <div class="prefer-text">{{messageList.mesSugTime}}</div>
      </div>
    </div>
    <!-- 景区设施 -->
    <div class="preferentialTreatment">
      <div class="preferentialTreatment-main">
        <div class="prefer-ticket-name">景区设施</div>
        <div class="prefer-text">{{messageList.mesFacility}}</div>
      </div>
    </div>
    <!-- 景点信息 -->
    <div class="preferentialTreatment">
      <div class="preferentialTreatment-main" @click="viewList()">
        <div class="prefer-ticket-name">景点信息</div>
        <div class="prefer-text">景点详细信息</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      messageList: []
    }
  },
  components: {
  },
  methods: {
    getMessage () {
      let that = this
      this.$util.request(this.$api.MessageList, {
      }).then(function (res) {
        if (res.errno === 0) {
          that.messageList = res.data.data[0]
          console.log(that.messageList)
        }
      })
    },
    viewList () {
      wx.navigateTo({
        url: '/pages/viewList/main'
      })
    }
  },
  created () {
    // let app = getApp()
  },
  onShow () {
    this.getMessage()
  }
}
</script>

<style>
.attractions-wrap{
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color:#F5F5F5;
  font-size: 27rpx;
  overflow-y:scroll;
  letter-spacing: 1px;
  color: #888;
}
.attractions-wrap::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
/* 头部导航 */
.header{
  width:100%;
  background: #fff;
  padding: 3% 0;
  display: flex;
  justify-content: space-around;
  margin-bottom: 15px;
  left: 0;
  top: 0;
  position: fixed;
  color: #000;
}
.blankdiv{
  width: 100%;
  height: 55px;
}
/* 提示部分 */
.preferentialTreatment{
  width:100%;
  background: #fff;
  margin-bottom: 15px;
}
.preferentialTreatment-main{
  padding: 3% 3% 0 3%;
}
.prefer-ticket-name{
  border-left: 2px solid #00cc66;
  line-height: 22px;
  font-size:15px;
  padding-left: 10px;
  margin-bottom: 15px;
  color: #000;
}
.prefer-text{
  font-size: 12px;
  padding-bottom: 15px;
}
.facility{
  display: flex;
  padding-bottom: 15px;
}
.facility-img{
  width: 20px;
  height: 20px;
}
.facility-part{
  width: 25%;
}
</style>
