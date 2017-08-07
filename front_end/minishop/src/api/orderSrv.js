import Vue from 'vue';

export default {
  addOrder: (order, success, error) => {
    Vue.resource('/api/auth/order/add').save({},order).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  deleteOrder: (id, success, error) => {
    Vue.resource('/api/auth/order/delete/{id}').delete({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  updateOrder: (order, success, error) => {
    Vue.resource('/api/auth/order/update/{id}').update({
      id: order.id
    },order).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getOrderById: (id, success, error) => {
    Vue.resource('/api/auth/order/{id}').get({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getOrderByList: (status, success, error) => {
    Vue.resource('/api/auth/order/list?&status='+status).get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getOrderByPage: (ctx, success, error) => {
    Vue.resource('/api/auth/order/page/{pageNum}/{pageSize}?&status='+status).get({
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
