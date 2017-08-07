<template>
  <div id="login">
    <header>
      <h2>用户登录</h2>
    </header>
    <hr/>
    <form id="loginform" class="form-horizontal" method="post">
			<div class="form-group">
				<div class="col-xs-6 col-xs-offset-3">
					<input type="email" class="form-control" placeholder="账号" v-model="user.account">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-6 col-xs-offset-3">
					<input type="password" class="form-control" placeholder="密码" v-model="user.password">
				</div>
			</div>
      <div class="form-group">
        <div class="col-xs-6 col-xs-offset-3">
					<p id="resultTip" v-text="result"></p>
				</div>
      </div>
		</form>
    <button class="btn btn-primary col-xs-3 col-xs-offset-5" @click="login()">登录</button>
  </div>
</template>

<script>
import userSrv from '../../api/userSrv';
import router from '../../router';
export default {
  name: 'login',
  data () {
    return {
      user: {
        account: '',
        password: ''
      },
      result: ''
    }
  },
  methods: {
    validate(user){
      if(user.account!='' && user.password!=''){
        return true;
      }else{
        this.result = '请输入完整信息！';
        return false;
      }
    },
    login(){
      var ctx = this;
      if(ctx.validate(ctx.user)){
        userSrv.login(ctx.user,function(data){
          //提示信息
          ctx.result = data.msg;
          if(data.status_key==1){//登录成功
            EasyStorage.setItem('token',data.token);
            setTimeout(function(){
              //到首页
              router.push({name: 'home'});
            },1000);
          }
        },function(data){
          console.log("error!");
        });
      }
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

#resultTip{
  width: 100%;
  text-align: center;
  color: #f00;
}

</style>
