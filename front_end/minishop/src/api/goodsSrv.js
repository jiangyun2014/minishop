import Vue from 'vue';

export default {
  addGoods: (goods, success, error) => {
    Vue.resource('/api/goods/add').save({},goods).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  deleteGoods: (id, success, error) => {
    Vue.resource('/api/goods/delete/{id}').delete({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  updateGoods: (goods, success, error) => {
    Vue.resource('/api/goods/update/{id}').update({
      id: goods.id
    },goods).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getGoodsById: (id, success, error) => {
    Vue.resource('/api/goods/{id}').get({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getGoodsByList: (ctx, success, error) => {
    Vue.resource('/api/goods/list?&searchKey='+ctx.params.searchKey).get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getGoodsByPage: (ctx, success, error) => {
    Vue.resource('/api/goods/page/{pageNum}/{pageSize}?&searchKey='+ctx.params.searchKey).get({
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
