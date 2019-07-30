// var app = getApp()
// const host = 'http://127.0.0.1:8080/wx/'
// import main from '../main'

function formatTime (date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber (n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 封封微信的的request
 */
function request (url, data = {}, method = 'GET') {
  return new Promise(function (resolve, reject) {
    wx.request({
      url: url,
      data: data,
      method: method,
      header: {
        'Content-Type': 'application/json',
        'X-Litemall-Token': wx.getStorageSync('token')
      },
      success: function (res) {
        if (res.statusCode === 200) {
          if (res.data.errno === 501) {
            // 清除登录相关内容
            try {
              wx.removeStorageSync('userInfo')
              wx.removeStorageSync('token')
            } catch (e) {
              // Do something when catch error
            }
            // 切换到登录页面
            wx.redirectTo({
              url: '/pages/ticketPurchase/main'
            })
          } else {
            resolve(res.data)
          }
        } else {
          reject(res.errMsg)
        }
      },
      fail: function (err) {
        reject(err)
      }
    })
  })
}

function redirect (url) {
  // console.log(main.globalData.hasLogin)
  wx.getSetting({
    success: function (res) {
      if (!res.authSetting['scope.userInfo']) {
        wx.redirectTo({
          url: '/pages/ticketPurchase/main'
        })
      } else if (wx.getStorageSync('userInfo') === undefined || wx.getStorageSync('userInfo') === '') {
        wx.redirectTo({
          url: '/pages/ticketPurchase/main'
        })
      } else {
        wx.navigateTo({
          url: url
        })
      }
    }
  })
}

function showErrorToast (msg) {
  wx.showToast({
    title: msg,
    image: '/static/images/icon_error.png'
  })
}

export default {
  formatTime,
  request,
  redirect,
  showErrorToast
}
