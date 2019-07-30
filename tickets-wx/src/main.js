import Vue from 'vue'
import App from './App'
import util from './utils/util'
import api from '../config/api'
import user from './utils/user'
import check from './utils/check'
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue(App)
Vue.prototype.$util = util
Vue.prototype.$api = api
Vue.prototype.$user = user
Vue.prototype.$check = check
var globalData = {hasLogin: false}
app.$mount()
Vue.prototype.globalData = globalData
export default{
  globalData
}
