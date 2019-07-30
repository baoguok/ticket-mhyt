<template>
  <div class="sy-photo">
    <!-- 头部导航 -->
    <div class="test">
      <div class="testNav">
        <div :class="{'selected':showType === 0}" @click="changTab(0)">全部({{allCount}})</div>
        <div :class="{'selected':showType === 1}" @click="changTab(1)">有图({{hasPicCount}})</div>
      </div>
      <!-- 商家图片 -->
      <div class="photo">
          <!-- 评论内容 -->
          <div class="retext-wrap">
            <div class="retext" v-for="(item , key) in comment" :key="key">
              <div class="retext-main">
                <div class="retext-main-first">
                  <img :src="item.userInfo.avatarUrl" class="retext-username-img"/>
                  <div class="retext-main-first-right">
                    <p class="retext-username number">{{item.userInfo.nickName}}</p>
                    <div class="thumbUpTime">
                      <p class="retext-time number">{{item.addTime}}</p>
                      <div class="like"><img class="like-img" src="/static/images/like.svg"/>{{item.like}}</div>
                    </div>
                  </div>
                </div>
                <div class="retext-main-second">
                  <p>{{item.content}}</p>
                </div>
                <div class="retext-main-thirdly" v-for="(item,index) in item.picList" :key="index">
                  <img class="retext-main-thirdly-img" :src="item"/>
                  <!-- <div class="retext-main-thirdly-div">共9张</div> -->
                </div>
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
      showType: 0,
      allCount: 0,
      hasPicCount: 0,
      totalPages: 0,
      page: 1,
      size: 5,
      comment: []
    }
  },
  methods: {
    changTab (index) {
      this.showType = index
      this.page = 1
      this.comment = []
      this.getCommentList()
    },
    getCommentCount: function () {
      let that = this
      this.$util.request(this.$api.CommentCount, {
      }).then(function (res) {
        if (res.errno === 0) {
          that.allCount = res.data.allCount
          that.hasPicCount = res.data.hasPicCount
        }
      })
    },
    getCommentList: function () {
      let that = this
      this.$util.request(this.$api.CommentList, {
        size: that.size,
        page: that.page,
        showType: that.showType
      }).then(function (res) {
        if (res.errno === 0) {
          if (that.page > 1) {
            that.comment = that.comment.concat(res.data.data)
          } else {
            that.comment = res.data.data
          }
          console.log(that.comment)
        }
      })
    }
  },
  component: {
  },
  created () {
  },
  onShow () {
    this.getCommentCount()
    this.getCommentList()
  },
  onReachBottom () {
    if (this.showType === 0) {
      this.totalPages = this.allCount / this.size
    } else {
      this.totalPages = this.hasPicCount / this.size
    }
    if (this.totalPages > this.page) {
      wx.showLoading({
        title: '加载中...'
      })
      this.page = this.page + 1
      this.getCommentList()
      wx.hideLoading()
    } else {
      wx.showToast({
        title: '没有更多图片了',
        icon: 'none',
        duration: 2000
      })
      return false
    }
  }
}
</script>
<style>
.sy-photo{
  width: 100%;
  height: auto;
  /* position: fixed;
  top: 0;
  left: 0; */
  background: #f5f5f5;
  overflow-y: scroll;
}
.sy-photo::-webkit-scrollbar{
  width: 0px;
  height: 0px;
}
/* 头部导航 */
.test {
  width: 100%;
 color: #888;
 padding-top: 80rpx;
}
.selected {
 color: #00cc66;
 border-bottom: 2rpx solid #00cc66;
}
.testNav {
  width: 100%;
  color: #888;
  flex: 1;
  text-align: center;
  font-size: 26rpx;
  padding: 0 0rpx;
  height: 80rpx;
  line-height: 80rpx;
  display: flex;
  background: #fff;
  justify-content: space-around;
  align-content: center;
  position: fixed;
  top: 0;
  left: 0;
}
/* 评论的具体内容 */
.retext-wrap{
  margin-top: 30rpx;
  background: #fff;
  padding:10rpx 0  30rpx;
}
.retext{
  width: 94%;
  padding: 4% 3% 0 3%;

  border-bottom: 2rpx solid rgb(226, 226, 226);
}
.retext-main{
  padding-bottom: 4%;
}
.retext-main-first{
  display: flex;
  color: #888;
}
.retext-main-first-right{
  width:85%
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
.thumbUpTime{
  display: flex;
}
.retext-time{
  font-size: 11px;
  margin-right: 300rpx;
}
.like{
  font-size: 11px;
  display: flex;
}
.like-img{
  width: 30rpx;
  height: 30rpx;
  transform: translateY(-6rpx);
  margin-right: 8rpx;
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
</style>
