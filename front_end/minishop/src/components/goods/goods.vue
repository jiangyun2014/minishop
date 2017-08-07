<template>
  <div id="goods">
    <header>
      <input id="searchKey" class="input-lg" type="text" placeholder="请输入商品关键字..." v-model="params.searchKey">
      <input id="searchBtn" class="btn btn-primary input-lg" type="button" value="搜索" @click="searchGoods()"/>
    </header>
    <main>
      <div class="goodsArea" v-for='(goods,index) in goodsList'>
        <img :src='goods.picture' :title="goods.name" @click="goodsDet(goods.id)"/>
        <h3 v-text="goods.name"></h3>
        <p v-text="goods.description"></p>
        <div class="bBar">
          <h4 v-text="'￥'+goods.price"></h4>
          <button class="btn btn-primary btn-sm" @click="addInCart(goods.id)">加入购物车</button>
        </div>
      </div>
    </main>
    <div class="clearfix"></div>
    <nav aria-label="">
      <ul class="pager">
        <li :class="pageInfo.hasPre"><a href="#" @click="prePage()">上一页</a></li>
        <li :class="pageInfo.hasNext"><a href="#" @click="nextPage()">下一页</a></li>
      </ul>
    </nav>
  </div>
</template>

<script>
import router from '../../router';
import goodsSrv from '../../api/goodsSrv';
import cartSrv from '../../api/cartSrv';
export default {
  name: 'goods',
  data () {
    return {
      pageInfo: {
        pageNum: 1,
        pageSize: 8,
        pages: 0,
        hasNext: 'disabled',
        hasPre: 'disabled'
      },
      params: {
        searchKey: ''
      },
      'goodsList': []
    }
  },
  created() {
    this.renderPage();

    //this.testAdd();
    //this.testUpdate();
    //this.testDelete();
    //this.testGetById();
    //this.testGetByList();
    //this.testGetByPage();

  },
  methods: {
    renderPage() {
      var ctx = this;
      goodsSrv.getGoodsByPage(ctx,function(data){
        ctx.goodsList = data.list;
        ctx.pageInfo.pages = data.pages;
        ctx.pageInfo.hasNext = data.hasNextPage?'active':'disabled';
        ctx.pageInfo.hasPre = data.hasPreviousPage?'active':'disabled';
      },function(){
        console.log("error");
      });
    },
    prePage() {
      if(this.pageInfo.pageNum>1){
        this.pageInfo.pageNum--;
        this.renderPage();
      }
    },
    nextPage(){
      if(this.pageInfo.pageNum<this.pageInfo.pages){
        this.pageInfo.pageNum++;
        this.renderPage();
      }
    },
    searchGoods(){
      this.renderPage();
    },
    goodsDet(goodsId){
      router.push({
        name: 'goodsDet',
        params: {
          goodsId: goodsId
        }
      });
    },
    addInCart(goodsId){
      var cart = {
        goodsid: goodsId
      };
      cartSrv.addCart(cart,function(data){
        alert('商品已加入购物车,可到购物车中查看！');
      },function(data){
        console.log('error!');
      });
    },

    testAdd(){
      goodsSrv.addGoods({
        id: parseInt(Math.random()*10000),
        name: '测试商品',
        price: 999.88,
        picture: 'http://localhost:8080/images/sample.png',
        description: '真品测试'
      },function(data){
        console.log("successOK");
      },function(){
        console.log("error");
      });
    },
    testUpdate(){
      goodsSrv.updateGoods({
        id: 6590,
        name: '测试商品'+parseInt(Math.random()*100),
        price: 999.88,
        picture: 'http://localhost:8080/images/sample.png',
        description: '真品测试'
      },function(data){
        console.log("successOK");
      },function(){
        console.log("error");
      });
    },
    testDelete(){
      goodsSrv.deleteGoods(9576,function(data){
        console.log("successOK");
      },function(){
        console.log("error");
      });
    },
    testGetById(){
      goodsSrv.getGoodsById(6590,function(data){
        console.log("testGetById==="+data);
      },function(){
        console.log("error");
      });
    },
    testGetByList(){
      var ctx = this;
      goodsSrv.getGoodsByList(ctx,function(data){
        console.log("testGetByList==="+data);
      },function(){
        console.log("error");
      });
    },
    testGetByPage(){
      var ctx = this;
      goodsSrv.getGoodsByPage(ctx,function(data){
        console.log("testGetByPage==="+data);
      },function(){
        console.log("error");
      });
    }

 }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header{
  height: 100px;
  text-align: center;
  line-height: 100px;
}
#searchKey{
  width: 50%;
}
#searchBtn{
  width: 150px;
  font-size: 18px;
}

main{
  padding: 10px;
}
.goodsArea{
  width: 22%;
  margin: 10px 15px;
  padding: 10px;
  border: 1px solid #aaa;
  border-radius: 5px;
  display: block;
  float: left;
}
.goodsArea img{
  width: 100%;
  border: none;
}
.goodsArea h3{
  font-size: 20px;
  font-weight: bold;
}
.goodsArea .bBar h4{
  color: #f00;
  display: inline-block;
}
.goodsArea .bBar button{
  display: inline-block;
  float: right;
  margin-right: 2px;
}

</style>
