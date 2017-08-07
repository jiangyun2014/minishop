import Vue from 'vue';

export default {
  addReceiver: (receiver, success, error) => {
    Vue.resource('/api/auth/receiver/add').save({},receiver).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  deleteReceiver: (id, success, error) => {
    Vue.resource('/api/auth/receiver/delete/{id}').delete({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  updateReceiver: (receiver, success, error) => {
    Vue.resource('/api/auth/receiver/update/{id}').update({
      id: receiver.id
    },receiver).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getReceiverById: (id, success, error) => {
    Vue.resource('/api/auth/receiver/{id}').get({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getReceiverByList: (ctx, success, error) => {
    Vue.resource('/api/auth/receiver/list').get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getReceiverByPage: (ctx, success, error) => {
    Vue.resource('/api/auth/receiver/page/{pageNum}/{pageSize}').get({
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

  getReceiverByIdentity: (success, error) => {
    Vue.resource('/api/auth/receiver/list/identity').get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  }
}
