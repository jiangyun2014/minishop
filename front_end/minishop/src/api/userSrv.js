import Vue from 'vue';
import router from '../router';

export default {
  addUser: (user, success, error) => {
    Vue.resource('/api/user/add').save({},user).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  deleteUser: (id, success, error) => {
    Vue.resource('/api/auth/user/delete/{id}').delete({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  updateUser: (user, success, error) => {
    Vue.resource('/api/auth/user/update/{id}').update({
      id: goods.id
    },user).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getUserById: (id, success, error) => {
    Vue.resource('/api/auth/user/{id}').get({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getUserByList: (ctx, success, error) => {
    Vue.resource('/api/auth/user/list').get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getUserByPage: (ctx, success, error) => {
    Vue.resource('/api/auth/user/page/{pageNum}/{pageSize}').get({
      pageNum: ctx.pageInfo.pageNum,
      pageSize: ctx.pageInfo.pageSize
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getUserByIdentity: (success, error) => {
    Vue.resource('/api/auth/user/identity').get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  login: (user, success, error) => {
    Vue.resource('/api/user/login').save({},user).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  logout: () => {
    EasyStorage.removeItem('token');
    setTimeout(function(){
      //到首页
      router.push({name: 'home'});
    },3000);
  }
}
