import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/Layout'

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    perms: ['GET /aaa','POST /bbb']     will control the page perms (you can set multiple perms)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRouterMap = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/authredirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
      }
    ]
  }
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/scenic',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'scenic',
    meta: {
      title: '景区管理',
      icon: 'scenicImg'
    },
    children: [
      {
        path: 'ticmes',
        component: () => import('@/views/scenic/ticmes'),
        name: 'ticmes',
        meta: {
          perms: ['GET /admin/scenic/ticmes', 'POST /admin/scenic/ticmes', 'POST /admin/scenic/ticmes'],
          title: '景区信息管理',
          noCache: true
        }
      },
      {
        path: 'viewpoint',
        component: () => import('@/views/scenic/viewpoint'),
        name: 'viewpoint',
        meta: {
          perms: ['GET /admin/scenic/viewpoint', 'POST /admin/scenic/viewpoint', 'POST /admin/scenic/viewpoint'],
          title: '景点文章管理',
          noCache: true
        }
      },
      {
        path: 'gateview',
        component: () => import('@/views/scenic/gateview'),
        name: 'gateview',
        meta: {
          perms: ['GET /admin/scenic/gateview', 'POST /admin/scenic/gateview', 'POST /admin/scenic/gateview'],
          title: '景点闸机管理',
          noCache: true
        }
      },
      {
        path: 'order',
        component: () => import('@/views/scenic/order'),
        name: 'order',
        meta: {
          perms: ['GET /admin/scenic/order', 'POST /admin/scenic/order', 'POST /admin/scenic/order'],
          title: '订单管理',
          noCache: true
        }
      },
      {
        path: 'comment',
        component: () => import('@/views/scenic/comment'),
        name: 'comment',
        meta: {
          perms: ['GET /admin/scenic/comment', 'POST /admin/scenic/comment', 'POST /admin/scenic/comment'],
          title: '评论管理',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/ticket',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'ticket',
    meta: {
      title: '门票管理',
      icon: 'ticketImg'
    },
    children: [
      {
        path: 'category',
        component: () => import('@/views/ticket/category'),
        name: 'category',
        meta: {
          perms: ['GET /admin/ticket/category', 'POST /admin/ticket/category', 'POST /admin/ticket/category'],
          title: '门票类目',
          noCache: true
        }
      },
      {
        path: 'good',
        component: () => import('@/views/ticket/good'),
        name: 'good',
        meta: {
          perms: ['GET /admin/ticket/good', 'POST /admin/ticket/good', 'POST /admin/ticket/good'],
          title: '门票列表',
          noCache: true
        }
      }

    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'userManage',
    meta: {
      title: '用户管理',
      icon: 'userManageImg'
    },
    children: [
      {
        path: 'user',
        component: () => import('@/views/user/user'),
        name: 'user',
        meta: {
          perms: ['GET /admin/user/list', 'POST /admin/user/create', 'POST /admin/user/update'],
          title: '会员管理',
          noCache: true
        }
      },
      {
        path: 'tourist',
        component: () => import('@/views/user/tourist'),
        name: 'feedback',
        meta: {
          perms: ['POST /admin/tourist/list'],
          title: '游客管理',
          noCache: true
        }
      },
      {
        path: 'feedback',
        component: () => import('@/views/user/feedback'),
        name: 'feedback',
        meta: {
          perms: ['GET /admin/feedback/list'],
          title: '意见反馈',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/promotion',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'promotionManage',
    meta: {
      title: '推广管理',
      icon: 'promotionManageImg'
    },
    children: [
      // {
      //   path: 'adCate',
      //   component: () => import('@/views/promotion/adcate'),
      //   name: 'adCate',
      //   meta: {
      //     perms: ['GET /admin/ad/list', 'POST /admin/ad/create', 'GET /admin/ad/read', 'POST /admin/ad/update', 'POST /admin/ad/delete'],
      //     title: '景点图片类目',
      //     noCache: true
      //   }
      // },
      // {
      //   path: 'ad',
      //   component: () => import('@/views/promotion/ticad'),
      //   name: 'ad',
      //   meta: {
      //     perms: ['GET /admin/ticad/list', 'POST /admin/ticad/create', 'GET /admin/ad/read', 'POST /admin/ticad/delete'],
      //     title: '景点图片',
      //     noCache: true
      //   }
      // },
      {
        path: 'ticcoupon',
        component: () => import('@/views/promotion/ticcoupon'),
        name: 'ticcoupon',
        meta: {
          perms: ['POST /admin/ticcoupon/list', 'POST /admin/ticcoupon/create', 'POST /admin/ticcoupon/update', 'POST /admin/ticcoupon/delete'],
          title: '优惠券管理',
          noCache: true
        }
      },
      {
        path: 'couponDetail',
        component: () => import('@/views/promotion/couponDetail'),
        name: 'couponDetail',
        meta: {
          perms: ['GET /admin/coupon/list', 'GET /admin/coupon/listuser'],
          title: '优惠券详情',
          noCache: true
        },
        hidden: true
      }
      // {
      //   path: 'topic',
      //   component: () => import('@/views/promotion/topic'),
      //   name: 'topic',
      //   meta: {
      //     perms: ['GET /admin/topic/list', 'POST /admin/topic/create', 'GET /admin/topic/read', 'POST /admin/topic/update', 'POST /admin/topic/delete'],
      //     title: '专题管理',
      //     noCache: true
      //   }
      // },
      // {
      //   path: 'groupon-rule',
      //   component: () => import('@/views/promotion/grouponRule'),
      //   name: 'grouponRule',
      //   meta: {
      //     perms: ['GET /admin/groupon/list', 'POST /admin/groupon/create', 'POST /admin/groupon/update', 'POST /admin/groupon/delete'],
      //     title: '团购规则',
      //     noCache: true
      //   }
      // },
      // {
      //   path: 'groupon-activity',
      //   component: () => import('@/views/promotion/grouponActivity'),
      //   name: 'grouponActivity',
      //   meta: {
      //     perms: ['GET /admin/groupon/listRecord'],
      //     title: '团购活动',
      //     noCache: true
      //   }
      // }
    ]
  },

  {
    path: '/sys',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'sysManage',
    meta: {
      title: '系统管理',
      icon: 'sysManageImg'
    },
    children: [
      {
        path: 'admin',
        component: () => import('@/views/sys/admin'),
        name: 'admin',
        meta: {
          perms: ['GET /admin/admin/list', 'POST /admin/admin/create', 'POST /admin/admin/update', 'POST /admin/admin/delete'],
          title: '管理员',
          noCache: true
        }
      }
      // {
      //   path: 'role',
      //   component: () => import('@/views/sys/role'),
      //   name: 'role',
      //   meta: {
      //     perms: ['GET /admin/role/list', 'POST /admin/role/create', 'POST /admin/role/update', 'POST /admin/role/delete', 'GET /admin/role/permissions', 'POST /admin/role/permissions'],
      //     title: '角色管理',
      //     noCache: true
      //   }
      // },
      // {
      //   path: 'os',
      //   component: () => import('@/views/sys/os'),
      //   name: 'os',
      //   meta: {
      //     perms: ['GET /admin/os/list', 'POST /admin/os/create', 'POST /admin/os/update', 'POST /admin/os/delete'],
      //     title: '对象存储',
      //     noCache: true
      //   }
      // }
    ]
  },

  {
    path: '/stat',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    name: 'statManage',
    meta: {
      title: '统计',
      icon: 'chart'
    },
    children: [
      // {
      //   path: 'money',
      //   component: () => import('@/views/stat/money'),
      //   name: 'statMoney',
      //   meta: {
      //     perms: ['GET /admin/stat/money'],
      //     title: '资金流水统计',
      //     noCache: true
      //   }
      // },
      {
        path: 'user',
        component: () => import('@/views/stat/user'),
        name: 'statUser',
        meta: {
          perms: ['GET /admin/stat/user'],
          title: '用户统计',
          noCache: true
        }
      },
      {
        path: 'ota',
        component: () => import('@/views/stat/ota'),
        name: 'OTA',
        meta: {
          perms: ['GET /admin/stat/ota'],
          title: 'OTA销售表',
          noCache: true
        }
      }
      // {
      //   path: 'order',
      //   component: () => import('@/views/stat/order'),
      //   name: 'statOrder',
      //   meta: {
      //     perms: ['GET /admin/stat/order'],
      //     title: '订单统计',
      //     noCache: true
      //   }
      // },
      // {
      //   path: 'goods',
      //   component: () => import('@/views/stat/goods'),
      //   name: 'statGoods',
      //   meta: {
      //     perms: ['GET /admin/stat/goods'],
      //     title: '商品统计',
      //     noCache: true
      //   }
      // }
    ]
  },
  // {
  //   path: 'external-link',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   alwaysShow: true,
  //   name: 'externalLink',
  //   meta: {
  //     title: '外链',
  //     icon: 'link'
  //   },
  //   children: [
  //     {
  //       path: 'https://cloud.tencent.com/product/cos',
  //       meta: { title: '腾讯云存储', icon: 'link' }
  //     },
  //     {
  //       path: 'https://cloud.tencent.com/product/sms',
  //       meta: { title: '腾讯云短信', icon: 'link' }
  //     },
  //     {
  //       path: 'https://pay.weixin.qq.com/index.php/core/home/login',
  //       meta: { title: '微信支付', icon: 'link' }
  //     },
  //     {
  //       path: 'https://mpkf.weixin.qq.com/',
  //       meta: { title: '小程序客服', icon: 'link' }
  //     },
  //     {
  //       path: 'https://www.alibabacloud.com/zh/product/oss',
  //       meta: { title: '阿里云存储', icon: 'link' }
  //     },
  //     {
  //       path: 'https://www.qiniu.com/products/kodo',
  //       meta: { title: '七牛云存储', icon: 'link' }
  //     },
  //     {
  //       path: 'http://www.kdniao.com/api-track',
  //       meta: { title: '快递鸟', icon: 'link' }
  //     }
  //   ]
  // },
  {
    path: '/profile',
    component: Layout,
    redirect: 'noredirect',
    alwaysShow: true,
    children: [
      {
        path: 'password',
        component: () => import('@/views/profile/password'),
        name: 'password',
        meta: { title: '修改密码', noCache: true }
      }
    ],
    hidden: true
  },

  { path: '*', redirect: '/404', hidden: true }
]
