<template>
  <div id="goodsDet">
    <header>
      <h2>商品详情</h2>
      <button class="btn btn-primary btn-xs pull-right" style="margin-right: 10px;" type="submit" @click="goback()">返回</button>
    </header>
    <main>
      <div class="cartitem">
        <img class="itemPic" :src="goods.picture" :title="goods.name"/>
        <div class="itemCon">
          <h3 v-text="goods.name"></h3>
          <h4 v-text="'￥'+goods.price"></h4>
          <p v-text="goods.description"></p>
        </div>
        <div class="clearfix"></div>
      </div>
    </main>
    <div id="commentArea">
      <h3>评论</h3>
      <hr/>
      <div id="commentCon">
        <v-comment v-if="flag" :goodsId='goods.id'></v-comment>
      </div>
    </div>
  </div>
</template>

<script>
import goodsSrv from '../../api/goodsSrv';
import router from '../../router';
import comment from '@/components/comment/comment';
export default {
  name: 'goodsDet',
  data () {
    return {
      flag: false,//说明：给v-comment组件加判断（v-if），默认false，即先不要渲染；
      //等到异步加载完数据后(即加载goods)，再将flag改为true，此时才开始渲染。此方法可以避免传空值到子组件
      goods: {}
    }
  },
  components: {
    'v-comment': comment
  },
  created(){
    this.renderPage();

  },
  methods: {
    renderPage(){
      var ctx = this;
      goodsSrv.getGoodsById(this.$route.params.goodsId, function(data){
        ctx.goods = data;
        ctx.flag = true;
      },function(){
        console.log("error");
      });
    },
    goback(){
      router.push({
        name: 'home',
        params: {}
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header h2{
  font-weight: bold;
  margin-left: 100px;
}

.cartitem .itemPic{
  height: 150px;
  width: 150px;
  border: 1px solid #ccc;
  border-radius: 5px;
  float: left;
  margin-left: 100px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.cartitem .itemCon{
  height: 150px;
  float: left;
  margin-left: 30px;
}
.itemCon h4{
  color: #f00;
}

#commentArea{
  margin-left: 50px;
}
#commentCon{
  height: 300px;
  overflow-y: auto;
}

</style>
