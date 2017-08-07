// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';
import VueResource from 'vue-resource';

Vue.use(VueResource);

Vue.config.productionTip = false;

Vue.http.interceptors.push((request,next) => {
  const tokenVal = EasyStorage.getItem('token');
  if(tokenVal) {
    Vue.http.headers.common.Authorization = tokenVal;
  } else {
    delete Vue.http.headers.common.Authorization;
  }

  next((response) => {
    if (response.status === 400 || response.status === 401) {
      // 当Token已经失效时，清空所有保存在localStorage的数据
      EasyStorage.removeItem('token');
    }
    return response;
  });
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  template: '<App/>',
  components: { App }
});
