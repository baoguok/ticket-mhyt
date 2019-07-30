var util = require('../../utils/util.js');
var api = require('../../config/api.js');

var app = getApp();
Page({
  data: {
    status: false,
    orderId: 0,
    hotGoodsList:[],
    limit: 4,
    i:0,
    all:true
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.setData({
      orderId: options.orderId,
      status: options.status === '1' ? true : false
    })
    this.getGoodsData();
  },
  onReady: function() {

  },
  onShow: function() {
    // 页面显示

  },
  onHide: function() {
    // 页面隐藏

  },
  onUnload: function() {
    // 页面关闭

  },

  getGoodsData: function () {
    let that = this;
    let hotGoodsList = this.data.hotGoodsList;
    let i = this.data.i;
    let all = this.data.all;
    let limit = 0;
    if(i===0){
      limit = this.data.limit;
    }else{
      limit = wx.getStorageSync("limit");
    }
    util.request(api.GoodsHots, {
      limit: limit
    }
    ).then(function (res) {
      if (res.errno === 0) {
        that.data.i=1;
        console.log(res);
        that.setData({
          hotGoodsList: res.data.hotGoods,
          all: res.data.all
        });
        limit+=4;
        wx.setStorageSync("limit", limit);
      }
    });
  },
  
  getHotGoodsMore(){
    this.getGoodsData();
  },

  payOrder() {
    let that = this;
    // 模拟支付成功，同理，后台也仅仅是返回一个成功的消息而已
    // wx.showModal({
    //   title: '目前不能微信支付',
    //   content: '点击确定模拟支付成功，点击取消模拟未支付成功',
    //   success: function (res) {
    //     if (res.confirm) {
    //       util.request(api.OrderPrepay, { orderId: that.data.orderId }, 'POST').then(res => {
    //         if (res.errno === 0) {
    //           that.setData({
    //             status: true
    //           });
    //         }
    //         else {
    //           util.showErrorToast('支付失败');
    //         }
    //       });
    //     }
    //     else if (res.cancel) {
    //       util.showErrorToast('支付失败');
    //     }

    //   }
    // });

    util.request(api.OrderPrepay, {
      orderId: that.data.orderId
    }, 'POST').then(function(res) {
      if (res.errno === 0) {
        const payParam = res.data;
        console.log("支付过程开始")
        wx.requestPayment({
          'timeStamp': payParam.timeStamp,
          'nonceStr': payParam.nonceStr,
          'package': payParam.packageValue,
          'signType': payParam.signType,
          'paySign': payParam.paySign,
          'success': function(res) {
            console.log("支付过程成功")
            that.setData({
              status: true
            });            
          },
          'fail': function(res) {
            console.log("支付过程失败")
            util.showErrorToast('支付失败');
          },
          'complete': function(res) {
            console.log("支付过程结束")
          }
        });
      }
    });
  }
})