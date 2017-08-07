<template>
  <div id="cartGoods">
    <div class="cartGoodsWrapper">
      <img class="itemPic" :src="goods.picture" :title="goods.name"/>
      <div class="itemCon">
        <h3 v-text="goods.name"></h3>
        <h4 v-text="'￥'+goods.price" style="color:#f00;"></h4>
        <p v-text="goods.description"></p>
      </div>
    </div>
  </div>
</template>

<script>
import goodsSrv from '../../api/goodsSrv';
export default {
  name: 'cartGoods',
  props: ['goodsId'],
  data () {
    return {
      goods: {}
    }
  },
  created(){
    this.renderPage();

  },
  methods: {
    renderPage(){
      var ctx = this;
      goodsSrv.getGoodsById(this.goodsId, function(data){
        ctx.goods = data;
      },function(){
        console.log("error");
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cartGoodsWrapper{

}
.itemPic{
  height: 120px;
  width: 120px;
  border: 1px solid #ccc;
  border-radius: 5px;
  float: left;
  margin-left: 30px;
  margin-top: 5px;
  margin-bottom: 5px;
}
.itemCon{
  height: 120px;
  width: 600px;
  float: left;
  margin-left: 30px;
}
</style>
