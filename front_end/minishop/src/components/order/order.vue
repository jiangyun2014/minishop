<template>
  <div id="order">
    <header>
      <h2>我的订单</h2>
    </header>
    <main>
      <div class="typeBar">
        <span class="badge" @click="changeType(-1)">全部</span>
        <span class="badge" @click="changeType(1)">未付款</span>
        <span class="badge" @click="changeType(2)">已付款</span>
        <span class="badge" @click="changeType(3)">未发货</span>
        <span class="badge" @click="changeType(4)">已发货</span>
        <span class="badge" @click="changeType(5)">运输中</span>
        <span class="badge" @click="changeType(6)">已收货</span>
        <span class="badge" @click="changeType(7)">已评价</span>
      </div>
      <div class="orderWrapper">
        <div class="orderItem" v-for="(order,index) in orderList">
          <div>
            <v-cartGoods :goodsId="order.goodsid"></v-cartGoods>
          </div>
          <div class="tipArea">
            <h4>合计:<span v-text="'￥'+order.totalprice" style="color:#f00;font-size: 22px;"></span></h4>
            <h5 v-text="typeText[order.status]"></h5>
          </div>
          <div class="clearfix"></div>
          <div class="optArea">
            <p v-text="order.ordertime"></p>
            <span class="badge">去评价</span>
            <span class="badge">查看物流</span>
            <span class="badge">确认收货</span>
            <span class="badge">退货</span>
            <span class="badge">去付款</span>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import cartGoods from '@/components/cart/cartGoods';
import orderSrv from '../../api/orderSrv';
export default {
  name: 'order',
  data () {
    return {
      currentType: -1,
      typeText: ['','订单未付款','订单已付款','订单未发货','订单已发货','订单运输中','订单已收货','订单已评价'],
      orderList: []
    }
  },
  created(){
    this.renderPage();

  },
  computed: {

  },
  methods: {
    renderPage(){
      var ctx = this;
      orderSrv.getOrderByList(ctx.currentType,function(data){
        ctx.orderList = data;
      },function(data){
        console.log('error!');
      });
    },
    changeType(type){
      this.currentType = type;
      this.renderPage();
    }
  },
  components: {
    'v-cartGoods': cartGoods
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header h2{
  margin-left: 100px;
}

.typeBar{
  height: 50px;
  line-height: 50px;
  border-bottom: 1px solid #ccc;
  margin-left: 100px;
}
.typeBar span{
  display: inline-block;
  margin-left: 20px;
  background: #222;
  font-size: 18px;
  cursor: pointer;
}

.orderWrapper{
  height: 500px;
  margin-left: 100px;
  overflow-y: auto;
}
.orderItem{
  margin-bottom: 10px;
  border-bottom: 1px solid #ccc;
}
.tipArea{
  width: 200px;
  margin-top: 30px;
  display: inline-block;
  float: right;
  text-align: center;
}
.optArea{
  width: 100%;
  height: 50px;
  border-top: 1px solid #ccc;
}
.optArea span{
  display: inline-block;
  float: right;
  margin-right: 20px;
  margin-top: 10px;
  font-size: 12px;
  background: #333;
  padding: 5px;
  cursor: pointer;
}
.optArea p{
  display: inline-block;
  float: left;
  margin-top: 10px;
  margin-left: 30px;
  font-size: 14px;
}

</style>
