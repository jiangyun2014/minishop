<template>
  <div id="cart">
    <header>
      <h2>购物车</h2>
    </header>
    <div id="optionbar">
      <div id="checkBtn" class="checkbox barcon">
        <label><input @click="toggleCheck()" type="checkbox" value="" v-model="selectAllCarts">全选</label>
      </div>
      <div id="countBtn" class="barcon" @click="doCount()">结算</div>
      <div id="totalPriceTip" class="barcon">合计：<span id="totalPriceText" v-text="'￥'+pageTotalPrice"></span></div>
      <div class="clearfix"></div>
    </div>
    <main>
      <div class="cartitem" v-for="(cart,index) in cartList">
        <div class="itemCheck">
          <div class="checkbox" style="margin:0px;">
            <label><input class="chkItem" type="checkbox" :value="cart" v-model="selectCarts"></label>
          </div>
        </div>
        <v-cartGoods :goodsId="cart.goodsid"></v-cartGoods>
        <div class="btnArea">
          <span class="glyphicon glyphicon-trash btnicon" @click="removeCart(cart.id)" title="从购物车移除"></span>
          <div>
            <span class="glyphicon glyphicon-minus btnicon" @click="minusCart(cart)"></span>
            <span class="countTip" v-text="cart.quantity"></span>
            <span class="glyphicon glyphicon-plus btnicon" @click="plusCart(cart)"></span>
          </div>
        </div>
        <div class="clearfix"></div>
      </div>
    </main>
  </div>
</template>

<script>
import cartGoods from '@/components/cart/cartGoods';
import router from '../../router';
import cartSrv from '../../api/cartSrv';
export default {
  name: 'cart',
  data () {
    return {
      selectAllCarts: false,
      selectCarts: [],
      cartList: []
    }
  },
  computed: {
    pageTotalPrice: function(){
      var totalPrice = 0;
      for(var i=0; i<this.selectCarts.length; i++){
        var cart = this.selectCarts[i];
        totalPrice += parseFloat(parseInt(cart.quantity) * cart.price);
      }
      return totalPrice.toFixed(2);
    }
  },
  created(){
    this.renderPage();

  },
  methods: {
    renderPage(){
      var ctx = this;
      cartSrv.getCartByList(ctx,function(data){
        ctx.cartList = data;
      },function(data){
        console.log('error!');
      });
    },
    removeCart(cartId){
      if(!window.confirm('确认从购物车移除该商品吗?')){
        return false;
      }
      cartSrv.deleteCart(cartId,function(data){
        alert('移除成功！');
        this.renderPage();
      },function(data){
        console.log('error!');
      });
    },
    plusCart(cart){
      cart.quantity++;
      cartSrv.updateCart(cart,function(data){

      },function(data){

      });
    },
    minusCart(cart){
      if(cart.quantity>1){
        cart.quantity--;
        cartSrv.updateCart(cart,function(data){

        },function(data){

        });
      }
    },
    doCount(){
      router.push({
        name: 'count',
        params: {
          carts: this.selectCarts
        }
      });
    },
    toggleCheck(){
      var cbs = $('.chkItem');
      for(var i=0; i<cbs.length; i++){
        if(this.selectAllCarts){alert('ADD:before==='+$(cbs[i]).attr('checked'))
          $(cbs[i]).attr({'checked':'checked'});alert('ADD:after==='+$(cbs[i]).attr('checked'))
        }else{alert('Remove:before==='+$(cbs[i]).attr('checked'))
          $(cbs[i]).removeAttr('checked');alert('Remove:after==='+$(cbs[i]).attr('checked'))
        }
      }
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
  font-weight: bold;
  margin-left: 100px;
}

#optionbar{
  width: 100%;
  height: 50px;
  border: 1px solid #aaa;
}
.barcon{
  display: inline-block;
}
#checkBtn{
  font-size: 18px;
  float: left;
  margin-left: 100px;
}
#totalPriceTip{
  width: 300px;
  height: 100%;
  font-size: 18px;
  text-align: center;
  line-height: 50px;
  float: right;
}
#totalPriceText{
  color: #f00;
}
#countBtn{
  width: 100px;
  height: 100%;
  border: 1px solid #aaa;
  color: #fff;
  background: #222;
  font-size: 18px;
  text-align: center;
  line-height: 50px;
  float: right;
  cursor: pointer;
}

main{
  min-height: 300px;
  margin-bottom: 80px;
  overflow-y: auto;
}
.cartitem{
  width: 100%;
  border-bottom: 1px solid #ccc;
}
.cartitem .itemCheck{
  width: 100px;
  height: 100%;
  line-height: 120px;
  float: left;
  margin-left: 100px;
}
.cartitem .btnArea{
  width: 120px;
  float: right;
  margin-right: 10px;
  margin-top: 30px;
  text-align: center;
}
.btnicon{
  font-size: 18px;
  padding: 10px;
  cursor: pointer;
  border: 1px solid #fff;
}
.btnicon:hover{
  border: 1px solid #ccc;
}
.countTip{
  font-size: 28px;
  font-weight: bold;
}

</style>
