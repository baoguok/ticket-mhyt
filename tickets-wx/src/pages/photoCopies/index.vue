<template>
  <div class="sy-photo">
    <!-- 头部导航 -->
    <div class="test">
      <div class="testNav">
        <div :class="{'selected':tab === 0,'testTitle':true}" @click="changTab(0)">全部({{cateCount}})</div>
        <div :class="{'selected':tab === 1,'testTitle':true}" @click="changTab(1)">{{cateList[0].cateName}}({{cateList[0].cateCount}})</div>
        <div :class="{'selected':tab === 2,'testTitle':true}" @click="changTab(2)">{{cateList[1].cateName}}({{cateList[1].cateCount}})</div>
      </div>
      <!-- 商家图片 -->
      <div class="photo">
          <div class="photo-wrap">
            <div class="photo-main">
                <div class="imgtext" v-for="(item,index) in photos" :key="index">  
                  <img :src="item.url" :class="[((index+1)%2) == 0 ?  'picture-imgtwo':'picture-imgone']" :data-src="item.url" @click="previewImage"/>
                  <div class="text">{{item.cateName}}</div>
                </div>
            </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import fancyBox from 'vue-fancybox'
export default {
  data () {
    return {
      tab: 0,
      photos: [],
      imageList: [],
      totalPages: 0,
      page: 1,
      size: 10,
      count: 0,
      cateCount: 0,
      cateList: [],
      cate: {}
    }
  },
  methods: {
    previewImage: function (e) {
      var current = e.mp.target.dataset.src
      wx.previewImage({
        current: current,
        urls: this.imageList // 需要预览的图片http链接列表
      })
    },
    changTab (index) {
      this.tab = index
      this.page = 1
      this.photos = []
      this.getPhotos()
    },
    getCate () {
      this.$util.request(this.$api.AdCate, {
      }, 'Get').then(res => {
        if (res.errno === 0) {
          this.cateList = res.data.cateList
          this.cateCount = res.data.all
        }
      })
    },
    getPhotos () {
      let that = this
      this.$util.request(this.$api.AdList, {
        showType: this.tab,
        page: this.page,
        size: this.size
      }, 'Get').then(res => {
        if (res.errno === 0) {
          console.log(res)
          if (that.page > 1) {
            that.photos = that.photos.concat(res.data.adList)
            for (var i = 0; i < that.photos.length; i++) {
              this.imageList[i] = that.photos[i].url
            }
          } else {
            that.photos = res.data.adList
            for (var j = 0; j < that.photos.length; j++) {
              this.imageList[j] = that.photos[j].url
            }
          }
          this.count = res.data.count
          this.totalPages = this.count / this.size
        }
      })
    }
  },
  component: {
  },
  created () {
  },
  onLoad () {
    this.getPhotos()
    this.getCate()
  },
  onReachBottom () {
    console.log(this.page)
    console.log(this.totalPages)
    if (this.totalPages > this.page) {
      wx.showLoading({
        title: '加载中...'
      })
      this.page = this.page + 1
      this.getPhotos()
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
  height: 100%;
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
.testTitle {
  width:25%;
  flex: 1;
  text-align: center;
  font-size: 26rpx;
  color: #888;
 
}
.selected {
 color: #00cc66;
 border-bottom: 2rpx solid #00cc66;
}
.testNav {
  padding: 0 0rpx;
  height: 80rpx;
  line-height: 80rpx;
  display: flex;
  background: #fff;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
   z-index: 99;
}
/* 商家图片 */
.photo{
  width: 100%;
  height: 750px;
}
.photo-wrap{
  width: 94%;
  background: #f5f5f5;
  margin: 13% auto ;
  border-radius: 12rpx;
}
.photo-main{
  padding: 20rpx;
  text-align: center;
  height: 242rpx;
  display: flex;
  flex-wrap:wrap;
}
.imgtext{
  height:242rpx ;
  margin-bottom: 20rpx;
}
.picture-imgone{
  width:323rpx;
  height:242rpx ;
  margin-right: 20rpx;
  border-radius: 6rpx;
}
.picture-imgtwo{
  width:323rpx ;
  height:242rpx ;
  border-radius: 6rpx;
}
.text{
  width:323rpx ;
  transform: translateY(-62rpx);
  text-align: center;
  background: rgba(0, 0, 0, 0.4);
  color: #fff;
  font-size: 24rpx;
  padding:10rpx 0;
  border-radius: 0 0 6rpx 6rpx;
  z-index: 2;
}
/* 图片预览 */
.container {
  box-sizing:border-box;
	padding:20px;
} 
.previewimg{
	float:left;
	width:45%;
	height:200px;
	margin:2%;
}
.previewimg image{
	width:100%;
	height:100%;
}
</style>
