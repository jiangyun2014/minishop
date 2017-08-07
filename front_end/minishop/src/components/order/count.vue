<template>
  <div id="count">
    <header>
      <h2>填写收货信息</h2>
    </header>
    <main>
      <div class="recvArea" v-for="(receiver,index) in receiverList">
        <div class="itemCheck">
          <div class="radio" style="margin:0px;">
            <label><input class="chkItem" name="receiv" type="radio" :value="receiver.id" v-model="selectReceiver"></label>
          </div>
        </div>
        <div>
          <h4 v-text="receiver.receiver"></h4>
          <h5 v-text="'联系电话：'+receiver.telnum"></h5>
        </div>
        <div class="clearfix"></div>
        <p v-text="'收货地址：'+receiver.address"></p>
      </div>
      <div class="clearfix"></div>
      <div class="btnAreaFoot">
        <button class="btn btn-primary" @click="submitOrder()">提交订单</button>
      </div>
    </main>
  </div>
</template>

<script>
import router from '../../router';
import orderSrv from '../../api/orderSrv';
import receiverSrv from '../../api/receiverSrv';
export default {
  name: 'count',
  data () {
    return {
      carts: [],
      receiverList: [],
      selectReceiver: -1
    }
  },
  created(){
    this.getReceiverList();

  },
  methods: {
    submitOrder(){
      var ctx = this;
      if(!(this.selectReceiver>0)){
        alert('请选择收货人信息！');
        return false;
      }
      var cartsList = this.$route.params.carts;
      for(var i=0; i<cartsList.length; i++){
        var cart = cartsList[i];
        var totalPrice = cart.quantity * cart.price;
        var order = {
          userid: cart.userid,
          goodsid: cart.goodsid,
          receiver: ctx.selectReceiver,
          totalprice: totalPrice.toFixed(2)
        };
        orderSrv.addOrder(order,function(data){

        },function(data){
          console.log('error!');
        });
      }
      alert('订单提交成功！');
    },
    getReceiverList(){
      var ctx = this;
      receiverSrv.getReceiverByIdentity(function(data){
        ctx.receiverList = data;
      },function(data){
        console.log('error!');
      });
    },
    chooseReceiver(receiverId){
      this.selectReceiver = receiverId;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header h2{
  margin-left: 100px;
}

.recvArea{
  width: 80%;
  display: inline-block;
  float: left;
  margin-left: 100px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
}
.recvArea:hover{
  cursor: pointer;
  background: #eee;
}
.recvArea h4{
  display: inline-block;
  float: left;
}
.recvArea h5{
  display: inline-block;
  float: right;
  margin-right: 10px;
}

.btnAreaFoot{
  text-align: center;
}

</style>
