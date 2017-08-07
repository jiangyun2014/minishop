<template>
  <div id="comment">
    <ul class="list-group">
      <li class="list-group-item" v-for="(comment,index) in commentList">
        <h5 v-text="'用户'+comment.account+'：'"></h5>
        <p v-text="comment.content" class="comcon"></p>
        <div class="text-right" style="width: 100%;">
          <h5 v-text="comment.pubtime"></h5>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import commentSrv from '../../api/commentSrv';
export default {
  name: 'comment',
  props: ['goodsId'],
  data () {
    return {
      params: {
        goodsid: this.goodsId
      },
      commentList: []
    }
  },
  created(){
    this.renderPage();

  },
  methods: {
    renderPage(){
      var ctx = this;
      commentSrv.getCommentByList(ctx,function(data){
        ctx.commentList = data;
      },function(){
        console.log("error");
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.comcon{
  margin-left: 50px;
}
</style>
