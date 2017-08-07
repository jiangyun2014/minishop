import Vue from 'vue';

export default {
  addWallet: (wallet, success, error) => {
    Vue.resource('/api/auth/wallet/add').save({},wallet).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  deleteWallet: (id, success, error) => {
    Vue.resource('/api/auth/wallet/delete/{id}').delete({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  updateWallet: (wallet, success, error) => {
    Vue.resource('/api/auth/wallet/update/{id}').update({
      id: wallet.id
    },wallet).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getWalletById: (id, success, error) => {
    Vue.resource('/api/auth/wallet/{id}').get({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getWalletByList: (ctx, success, error) => {
    Vue.resource('/api/auth/wallet/list').get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getWalletByPage: (ctx, success, error) => {
    Vue.resource('/api/auth/wallet/page/{pageNum}/{pageSize}').get({
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

  getWalletByIdentity: (success, error) => {
    Vue.resource('/api/auth/wallet/identity').get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  }
}
