<template>
<div class="container">
  <div class="post-comment">
    <div class="goods">
      <div class="img">
        <image :src="order.catPicUrl"></image>
      </div>
      <div class="info">
        <div class="t">
          <text class="name">{{order.cateName}} x{{order.ordGoodsNum}}</text>
        </div>
        <div class="attr">{{order.catDesc}}</div>
      </div>
    </div>
    <div class="rater">
      <text class="rater-title">评分</text>
      <block v-for="(item,index) in stars" :key="index">
        <image src="/static/images/icon_collect_checked.png" @click="selectRater" :data-star="item" v-if="item < star"></image>
        <image src="/static/images/icon_collect.png" @click="selectRater" :data-star="item" v-else></image>
      </block>
      <text class="rater-desc">{{starText}}</text>
    </div>
    <div class="input-box">
      <textarea class="content" focus="true" @input="bindInputValue" maxlength="200" placeholder="留言经过筛选后，对所有人可见" />
      <text class="count">{{200 - content.length}}</text>
    </div>
    <div class="weui-uploader">
      <div class="weui-uploader__hd">
        <div class="weui-uploader__title">图片上传</div>
        <div class="weui-uploader__info">{{picUrls.length}}/{{files.length}}</div>
      </div>
      <div class="weui-uploader__bd">
        <div class="weui-uploader__files" id="uploaderFiles">
          <block v-for="(item,index) in files" :key="index">
            <div class="weui-uploader__file" @click="predivImage" id="item">
              <image class="weui-uploader__img" :src="item" mode="aspectFill" />
            </div>
          </block>
          <div class="weui-uploader__input-box">
            <div class="weui-uploader__input" @click="chooseImage"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="btns">
      <div class="close" @click="onClose">取消</div>
      <div class="post" @click="onPost">发表</div>
    </div>
  </div>
</div>
</template>
<script>
export default {
  data () {
    return {
      type: 0,
      order: {},
      content: '',
      stars: [0, 1, 2, 3, 4],
      star: 5,
      starText: '十分满意',
      hasPicture: false,
      picUrls: [],
      files: []
    }
  },
  components: {
  },
  methods: {
    chooseImage: function (e) {
      if (this.files.length >= 5) {
        this.$util.showErrorToast('只能上传五张图片')
        return false
      }
      var that = this
      wx.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'],
        success: function (res) {
          that.files = that.files.concat(res.tempFilePaths)
          that.upload(res)
        }
      })
    },
    upload: function (res) {
      var that = this
      const uploadTask = wx.uploadFile({
        url: that.$api.StorageUpload,
        filePath: res.tempFilePaths[0],
        name: 'file',
        success: function (res) {
          var _res = JSON.parse(res.data)
          if (_res.errno === 0) {
            var url = _res.data.url
            that.picUrls.push(url)
            that.hasPicture = true
            that.picUrls = that.picUrls
          }
        },
        fail: function (e) {
          wx.showModal({
            title: '错误',
            content: '上传失败',
            showCancel: false
          })
        }
      })
      uploadTask.onProgressUpdate((res) => {
        console.log('上传进度', res.progress)
        console.log('已经上传的数据长度', res.totalBytesSent)
        console.log('预期需要上传的数据总长度', res.totalBytesExpectedToSend)
      })
    },
    predivImage: function (e) {
      wx.previewImage({
        current: e.mp.currentTarget.id, // 当前显示图片的http链接
        urls: this.files // 需要预览的图片http链接列表
      })
    },
    selectRater: function (e) {
      var star = e.mp.currentTarget.dataset.star + 1
      var starText
      if (star === 1) {
        starText = '很差'
      } else if (star === 2) {
        starText = '不太满意'
      } else if (star === 3) {
        starText = '满意'
      } else if (star === 4) {
        starText = '比较满意'
      } else {
        starText = '十分满意'
      }
      this.star = star
      this.starText = starText
    },
    getOrderGoods: function (options) {
      let that = this
      wx.showLoading('加载中...')
      this.$util.request(this.$api.OrderDetail, {
        ordId: options.ordId
      }, 'get').then(res => {
        wx.hideLoading()
        that.order = res.data.order
      }).catch(() => {
        that.order = {}
      })
    },
    onClose: function () {
      wx.navigateBack()
    },
    onPost: function () {
      let that = this
      if (!this.content) {
        that.$util.showErrorToast('请填写评论')
        return false
      }
      wx.showLoading('提交中...')
      that.$util.request(that.$api.OrderComment, {
        comOrderId: that.order.ordId,
        comContent: that.content,
        comStar: that.star,
        comHasPicture: that.hasPicture,
        comPicUrls: that.picUrls
      }, 'POST').then(function (res) {
        wx.hideLoading()
        console.log(res)
        if (res.errno === 0) {
          wx.showToast({
            title: '评论成功',
            complete: function () {
              that.order = {}
              that.content = ''
              that.picUrls = []
              that.files = []
            }
          })
        }
      })
    },
    bindInputValue (event) {
      let value = event.mp.detail.value
      // 判断是否超过140个字符
      if (value && value.length > 140) {
        return false
      }
      this.content = event.mp.detail.value
    }
  },
  created () {
    // let app = getApp()
  },
  onLoad: function (options) {
    this.orderId = options.orderId
    this.type = options.type
    this.valueId = options.valueId
    this.getOrderGoods(options)
  }
}
</script>

<style>
page, .container {
  height: 100%;
  background: #f4f4f4;
  padding: 0;
}

.post-comment {
  width: 700rpx;
  height: auto;
  overflow: hidden;
  padding: 30rpx;
  background: #fff;
}

.post-comment .goods {
  display: flex;
  align-items: center;
  height: 199rpx;
  margin-left: 31.25rpx;
}

.post-comment .goods .img {
  height: 145.83rpx;
  width: 145.83rpx;
  background: #f4f4f4;
}

.post-comment .goods .img image {
  height: 145.83rpx;
  width: 145.83rpx;
}

.post-comment .goods .info {
  height: 145.83rpx;
  flex: 1;
  padding-left: 20rpx;
}

.post-comment .goods .name {
  margin-top: 30rpx;
  display: block;
  height: 44rpx;
  line-height: 44rpx;
  color: #333;
  font-size: 30rpx;
}

.post-comment .goods .number {
  display: block;
  height: 37rpx;
  line-height: 37rpx;
  color: #666;
  font-size: 25rpx;
}

.post-comment .goods .status {
  width: 105rpx;
  color: #b4282d;
  font-size: 25rpx;
}

.post-comment .rater {
  display: flex;
  flex-direction: row;
  height: 55rpx;
}

.post-comment .rater .rater-title {
  font-size: 29rpx;
  padding-right: 10rpx;
}

.post-comment .rater image {
  padding-left: 5rpx;
  height: 50rpx;
  width: 50rpx;
}

.post-comment .rater .rater-desc {
  font-size: 29rpx;
  padding-left: 10rpx;
}

.post-comment .input-box {
  height: 337.5rpx;
  width: 690rpx;
  position: relative;
  background: #fff;
}

.post-comment .input-box .content {
  position: absolute;
  top: 0;
  left: 0;
  display: block;
  background: #fff;
  font-size: 29rpx;
  border: 5px solid #f4f4f4;
  height: 300rpx;
  width: 650rpx;
  padding: 20rpx;
}

.post-comment .input-box .count {
  position: absolute;
  bottom: 20rpx;
  right: 20rpx;
  display: block;
  height: 30rpx;
  width: 50rpx;
  font-size: 29rpx;
  color: #999;
}

.post-comment .btns {
  height: 108rpx;
}

.post-comment .close {
  float: left;
  height: 108rpx;
  line-height: 108rpx;
  text-align: left;
  color: #666;
  padding: 0 30rpx;
  font-family:PingFangSC-Light, helvetica, 'Heiti SC';
  font-size:29rpx;
}

.post-comment .post {
  float: right;
  height: 108rpx;
  line-height: 108rpx;
  text-align: right;
  padding: 0 30rpx;
  font-family:PingFangSC-Light, helvetica, 'Heiti SC';
  font-size:29rpx;
  color:#333;
}

.weui-uploader {
  margin-top: 50rpx;
}

.weui-uploader__hd {
  display: -webkit-box;
  display: -webkit-flex;
  display: flex;
  padding-bottom: 10px;
  -webkit-box-align: center;
  -webkit-align-items: center;
  align-items: center;
}

.weui-uploader__title {
  -webkit-box-flex: 1;
  -webkit-flex: 1;
  flex: 1;
  font-family:PingFangSC-Light, helvetica, 'Heiti SC';
  font-size:29rpx;
  color:#333;
}

.weui-uploader__info {
  color: #b2b2b2;
  font-family:PingFangSC-Light, helvetica, 'Heiti SC';
  font-size:29rpx;
}

.weui-uploader__bd {
  margin-bottom: -4px;
  margin-right: -9px;
  overflow: hidden;
}

.weui-uploader__file {
  float: left;
  margin-right: 9px;
  margin-bottom: 9px;
}

.weui-uploader__img {
  display: block;
  width: 79px;
  height: 79px;
}

.weui-uploader__file_status {
  position: relative;
}

.weui-uploader__file_status:before {
  content: " ";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
}

.weui-uploader__file-content {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  color: #fff;
}

.weui-uploader__input-box {
  float: left;
  position: relative;
  margin-right: 9px;
  margin-bottom: 9px;
  width: 77px;
  height: 77px;
  border: 1px solid #d9d9d9;
}

.weui-uploader__input-box:after, .weui-uploader__input-box:before {
  content: " ";
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  background-color: #d9d9d9;
}

.weui-uploader__input-box:before {
  width: 2px;
  height: 39.5px;
}

.weui-uploader__input-box:after {
  width: 39.5px;
  height: 2px;
}

.weui-uploader__input-box:active {
  border-color: #999;
}

.weui-uploader__input-box:active:after, .weui-uploader__input-box:active:before {
  background-color: #999;
}

.weui-uploader__input {
  position: absolute;
  z-index: 1;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
}

</style>
