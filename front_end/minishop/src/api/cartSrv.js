import Vue from 'vue';

export default {
  addCart: (cart, success, error) => {
    Vue.resource('/api/auth/cart/add').save({},cart).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  deleteCart: (id, success, error) => {
    Vue.resource('/api/auth/cart/delete/{id}').delete({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  updateCart: (cart, success, error) => {
    Vue.resource('/api/auth/cart/update/{id}').update({
      id: cart.id
    },cart).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getCartById: (id, success, error) => {
    Vue.resource('/api/auth/cart/{id}').get({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getCartByList: (ctx, success, error) => {
    Vue.resource('/api/auth/cart/list').get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getCartByPage: (ctx, success, error) => {
    Vue.resource('/api/auth/cart/page/{pageNum}/{pageSize}').get({
      pageNum: ctx.pageInfo.pageNum,
      pageSize: ctx.pageInfo.pageSize
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  }
}
