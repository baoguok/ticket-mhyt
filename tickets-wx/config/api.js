// 以下是业务服务器API地址
// 本机开发时使用
// var WxApiRoot = 'http://1d828193e3.imwork.net:39655/wx/';
// 局域网测试使用
// var WxApiRoot = 'https://ticket.fycld.com/wx/'
// 云平台部署时使用
const WxApiRoot = 'https://wh-ticket.fycld.com/wx/'
// 云平台上线时使用
// var WxApiRoot = 'https://www.menethil.com.cn/wx/';
// 本地开发时测试
// var WxApiRoot = 'http://127.0.0.1:8013/wx/'

export default {
  IndexUrl: WxApiRoot + 'home/index', // 首页数据接口

  TicGoodList: WxApiRoot + 'ticgo/list', //
  TicGoodRedis: WxApiRoot + 'ticgo/getStock', // 查询库存
  TicGoodCheck: WxApiRoot + 'ticgo/checkStock', // 检验库存
  TicGoodCache: WxApiRoot + 'ticgo/goodCache', // redis存放门票
  TicGoodRedu: WxApiRoot + 'ticgo/reduction', // 用户购买成功后清除redis中缓存

  AdList: WxApiRoot + 'ad/list', // 广告图片
  AdCate: WxApiRoot + 'ad/cateCount', // 所有广告类别及图片数量

  CatalogList: WxApiRoot + 'catalog/index', // 分类目录全部分类数据接口
  CatalogCurrent: WxApiRoot + 'catalog/current', // 分类目录当前分类数据接口

  AuthLoginByWeixin: WxApiRoot + 'auth/login_by_weixin', // 微信登录
  AuthLoginByAccount: WxApiRoot + 'auth/login', // 账号登录
  AuthLogout: WxApiRoot + 'auth/logout', // 账号登出
  AuthRegister: WxApiRoot + 'auth/register', // 账号注册
  AuthReset: WxApiRoot + 'auth/reset', // 账号密码重置
  AuthRegisterCaptcha: WxApiRoot + 'auth/regCaptcha', // 验证码
  AuthBindPhone: WxApiRoot + 'auth/bindPhone', // 绑定微信手机号

  GoodsCount: WxApiRoot + 'goods/count', // 统计商品总数
  GoodsList: WxApiRoot + 'goods/list', // 获得商品列表
  GoodsCategory: WxApiRoot + 'goods/category', // 获得分类数据
  GoodsDetail: WxApiRoot + 'goods/detail', // 获得商品的详情
  GoodsNew: WxApiRoot + 'goods/new', // 新品
  GoodsHot: WxApiRoot + 'goods/hot', // 热门
  GoodsHots: WxApiRoot + 'goods/hots', // 获得热门列表
  GoodsRelated: WxApiRoot + 'goods/related', // 商品详情页的关联商品（大家都在看）

  BrandList: WxApiRoot + 'brand/list', // 品牌列表
  BrandDetail: WxApiRoot + 'brand/detail', // 品牌详情

  CartList: WxApiRoot + 'cart/index', // 获取购物车的数据
  CartAdd: WxApiRoot + 'cart/add', // 添加商品到购物车
  CartFastAdd: WxApiRoot + 'cart/fastadd', // 立即购买商品
  CartUpdate: WxApiRoot + 'cart/update', // 更新购物车的商品
  CartDelete: WxApiRoot + 'cart/delete', // 删除购物车的商品
  CartChecked: WxApiRoot + 'cart/checked', // 选择或取消选择商品
  CartGoodsCount: WxApiRoot + 'cart/goodscount', // 获取购物车商品件数
  CartCheckout: WxApiRoot + 'cart/checkout', // 下单前信息确认

  CollectList: WxApiRoot + 'collect/list', // 收藏列表
  CollectAddOrDelete: WxApiRoot + 'collect/addordelete', // 添加或取消收藏

  PraiseAdd: WxApiRoot + 'praise/add', // 商品点赞
  PraiseCheck: WxApiRoot + 'praise/check', // 商品是否已点赞

  CommentList: WxApiRoot + 'comment/list', // 评论列表
  CommentCount: WxApiRoot + 'comment/count', // 评论总数
  CommentPost: WxApiRoot + 'comment/post', // 发表评论

  MessageList: WxApiRoot + 'message/list', // 景点信息列表
  HotactivityList: WxApiRoot + 'hotactivity/list', // 热门活动列表
  HotactivityDetail: WxApiRoot + 'hotactivity/detail', // 热门活动详情
  HotactivityRelated: WxApiRoot + 'hotactivity/related', // 相关热门活动

  StrategyList: WxApiRoot + 'strategy/list', // 活动攻略列表
  StrategyDetail: WxApiRoot + 'strategy/detail', // 活动攻略详情
  StrategyRelated: WxApiRoot + 'strategy/related', // 相关活动攻略

  SearchIndex: WxApiRoot + 'search/index', // 搜索关键字
  SearchResult: WxApiRoot + 'search/result', // 搜索结果
  SearchHelper: WxApiRoot + 'search/helper', // 搜索帮助
  SearchClearHistory: WxApiRoot + 'search/clearhistory', // 搜索历史清楚

  AddressList: WxApiRoot + 'address/list', // 收货地址列表
  AddressDetail: WxApiRoot + 'address/detail', // 收货地址详情
  AddressSave: WxApiRoot + 'address/save', // 保存收货地址
  AddressDelete: WxApiRoot + 'address/delete', // 保存收货地址

  ExpressQuery: WxApiRoot + 'express/query', // 物流查询

  RegionList: WxApiRoot + 'region/list', // 获取区域列表

  OrderSubmit: WxApiRoot + 'order/submit', // 提交订单
  OrderPrepay: WxApiRoot + 'order/prepay', // 订单的预支付会话
  OrderList: WxApiRoot + 'order/listOrder', // 订单列表
  OrderDetail: WxApiRoot + 'order/details', // 订单详情
  OrderCancel: WxApiRoot + 'order/cancel', // 取消订单
  OrderRefund: WxApiRoot + 'order/refund', // 退款取消订单
  OrderDelete: WxApiRoot + 'order/delete', // 删除订单
  OrderConfirm: WxApiRoot + 'order/confirm', // 确认收货
  OrderGoods: WxApiRoot + 'order/goods', // 代评价商品信息
  OrderComment: WxApiRoot + 'order/comment', // 评价订单商品信息
  OrderCheck: WxApiRoot + 'order/check', // 检查订单超时
  OrderUpdate: WxApiRoot + 'order/updateOrd', // 修改订单状态

  FeedbackAdd: WxApiRoot + 'feedback/submit', // 添加反馈
  FootprintList: WxApiRoot + 'footprint/list', // 足迹列表
  FootprintDelete: WxApiRoot + 'footprint/delete', // 删除足迹

  UserFormIdCreate: WxApiRoot + 'formid/create', // 用户FromId，用于发送模版消息

  GroupOnList: WxApiRoot + 'groupon/list', // 团购列表
  GroupOn: WxApiRoot + 'groupon/query', // 团购API-查询
  GroupOnMy: WxApiRoot + 'groupon/my', // 团购API-我的团购
  GroupOnDetail: WxApiRoot + 'groupon/detail', // 团购API-详情
  GroupOnJoin: WxApiRoot + 'groupon/join', // 团购API-详情

  CouponList: WxApiRoot + 'coupon/list', // 优惠券列表
  CouponMyList: WxApiRoot + 'coupon/mylist', // 我的优惠券列表
  CouponSelectList: WxApiRoot + 'coupon/selectlist', // 当前订单可用优惠券列表
  CouponReceive: WxApiRoot + 'coupon/receive', // 优惠券领取
  CouponExchange: WxApiRoot + 'coupon/exchange', // 优惠券兑换
  CouponCount: WxApiRoot + 'coupon/count', // 优惠券领取数量

  DelRedis: WxApiRoot + 'redis/delredis', // 删除缓存

  TouQuery: WxApiRoot + 'tourist/query', // 游客信息查询
  TouAdd: WxApiRoot + 'tourist/add', // 游客信息添加

  StorageUpload: WxApiRoot + 'storage/upload', // 图片上传,

  StatisUpdate: WxApiRoot + 'statis/update', // 售票记录修改

  UserIndex: WxApiRoot + 'user/index', // 个人页面用户相关信息

  Userinfo: WxApiRoot + 'auth/getuser', // 获取个人信息

  Wxpay: WxApiRoot + 'wxpay/pay', // 微信支付
  Wxrefund: WxApiRoot + 'wxpay/refund', // 微信退款

  ViewList: WxApiRoot + 'view/list', // 景点集合
  ViewPoint: WxApiRoot + 'view/query', // 景点信息
  ViewPointRead: WxApiRoot + 'view/updateRead', // 添加阅读量

  Gettoken: WxApiRoot + 'qrcode/gettoken', // 获取access_token
  Qrcode: WxApiRoot + 'qrcode/getqrcode', // 获取access_token
  Weather: 'http://v.juhe.cn/weather/index' // 获取天气
}
