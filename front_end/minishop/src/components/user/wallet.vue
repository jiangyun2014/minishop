<template>
  <div id="wallet">
    <main>
      <h3>当前余额：<span id="tip" v-text="wallet.currency"></span>&nbsp;元</h3>
      <div class="area" v-if="flag">
        <button class="btn btn-primary btn-lg" @click="add()">充值</button>
      </div>
      <div class="area" v-else>
        <input class="input-lg" type="text" v-model="wallet.addVal">
        <button class="btn btn-primary btn-lg" @click="addConfirm()">确认充值</button>
      </div>
    </main>
  </div>
</template>

<script>
import walletSrv from '../../api/walletSrv';
import router from '../../router';
export default {
  name: 'wallet',
  data () {
    return {
      flag: true,
      wallet: {
        id: '',
        userid: '',
        currency: 0,
        addVal: ''
      }
    }
  },
  created(){
    var ctx = this;
    this.flag = true;
    walletSrv.getWalletByIdentity(function(data){
      ctx.wallet.id = data.id;
      ctx.wallet.userid = data.userid;
      ctx.wallet.currency = data.currency;
    },function(data){
      console.log('error!');
    });
  },
  methods: {
    add(){
      this.flag = false;
    },
    addConfirm(){
      var ctx = this;
      this.wallet.currency = parseFloat(this.wallet.currency) + parseFloat(this.wallet.addVal);
      walletSrv.updateWallet(this.wallet,function(data){
        alert('充值成功！');
        ctx.wallet.addVal = '';
        ctx.flag = true;
        router.push({name: 'user_wallet'});
      },function(data){
        alert('充值失败！');
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
main{
  width: 80%;
  margin-top: 20px;
  margin-left: 50px;
}
.area{
  margin-top: 10px;
}
#tip{
  color: #f00;
  font-size: 38px;
  font-weight: bold;
}

</style>
