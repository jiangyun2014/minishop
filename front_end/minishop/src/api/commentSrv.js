import Vue from 'vue';

export default {
  addComment: (comment, success, error) => {
    Vue.resource('/api/auth/comments/add').save({},comment).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  deleteComment: (id, success, error) => {
    Vue.resource('/api/auth/comments/delete/{id}').delete({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  updateComment: (comment, success, error) => {
    Vue.resource('/api/auth/comments/update/{id}').update({
      id: comment.id
    },comment).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getCommentById: (id, success, error) => {
    Vue.resource('/api/comments/{id}').get({
      id: id
    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getCommentByList: (ctx, success, error) => {
    Vue.resource('/api/comments/list?&goodsid='+ctx.params.goodsid).get({

    }).then(function(response){
      if(response.status==200){
        success(response.body);
      }
    },function(response){
      error();
    });
  },

  getCommentByPage: (ctx, success, error) => {
    Vue.resource('/api/comments/page/{pageNum}/{pageSize}?&goodsid='+ctx.params.goodsid).get({
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
