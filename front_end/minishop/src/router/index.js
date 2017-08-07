import Vue from 'vue';
import Router from 'vue-router';
import index from '@/components/index';
import home from '@/components/home/home';
import cart from '@/components/cart/cart';
import order from '@/components/order/order';
import user from '@/components/user/user';
import notice from '@/components/notice/notice';
import login from '@/components/user/login';
import register from '@/components/user/register';
import logout from '@/components/user/logout';

import userInfo from '@/components/user/userInfo';
import wallet from '@/components/user/wallet';
import address from '@/components/user/address';
import otherSettings from '@/components/user/otherSettings';

import goodsDet from '@/components/goods/goodsDet';
import count from '@/components/order/count';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: home
    },
    {
      path: '/index',
      name: 'index',
      component: home
    },
    {
      path: '/home',
      name: 'home',
      component: home
    },
    {
      path: '/cart',
      name: 'cart',
      component: cart
    },
    {
      path: '/order',
      name: 'order',
      component: order
    },
    {
      path: '/user',
      name: 'user',
      component: user,
      children: [
        {
          path: 'userInfo',
          name: 'user_userInfo',
          component: userInfo
        },
        {
          path: 'wallet',
          name: 'user_wallet',
          component: wallet
        },
        {
          path: 'address',
          name: 'user_address',
          component: address
        },
        {
          path: 'otherSettings',
          name: 'user_otherSettings',
          component: otherSettings
        }
      ]
    },
    {
      path: '/notice',
      name: 'notice',
      component: notice
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/logout',
      name: 'logout',
      component: logout
    },
    {
      path: '/goodsDet',
      name: 'goodsDet',
      component: goodsDet
    },
    {
      path: '/count',
      name: 'count',
      component: count
    }
  ]
});
