<template>
  <div id="header">
    <nav id="nav" class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <!-- Logo和标题 -->
          <a id="logo-title" class="navbar-brand" href="#">
            <img id="logo" src="./logo.png">
            <img id="title" src="./title.png">
          </a>
        </div><!-- /.navbar-header -->
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li>
              <router-link to="/home" class="icon btn-lg nav_btn iconTag" title="首页">
                <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
              </router-link>
            </li>
            <li>
              <router-link to="/cart" class="icon btn-lg nav_btn iconTag" title="购物车">
                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
              </router-link>
            </li>
            <li>
              <router-link to="/order" class="icon btn-lg nav_btn iconTag" title="我的订单">
                <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
              </router-link>
            </li>
            <li>
              <router-link to="/user" class="icon btn-lg nav_btn iconTag" title="个人中心">
                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
              </router-link>
            </li>
            <li>
              <router-link to="/notice" class="icon btn-lg nav_btn iconTag" title="消息通知">
                <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
              </router-link>
            </li>
            <li>
              <router-link to="/login" v-if="!isLogin" class="textTag" title="登录">登录</router-link>
              <router-link to="/register" v-if="!isLogin" class="textTag" title="注册">注册</router-link>
              <span v-text="now" v-if="isLogin" class="timeTip"></span>
              <router-link to="/logout" v-if="isLogin" class="textTag" title="注销">注销</router-link>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
  </div>
</template>

<script>
export default {
  name: 'header',
  data () {
    return {
      now: '',
      isLogin: EasyStorage.getItem('token') ? true : false
    }
  },
  created(){
    this.updateTime();
  },
  methods: {
    updateTime(){
      var ctx = this;
      setInterval(function(){
        ctx.now = new Date().toLocaleString();
        ctx.isLogin = EasyStorage.getItem('token') ? true : false;
      },1000);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/**reset navbar style*/
.navbar{
  height: 80px;
}
.navbar-default{
  background-color: #222;
  color: #fff;
}
.navbar-brand{
  padding: 0px;
}
.navbar-right{
  height: 80px;
}
.nav > li{
  height: 80px;
  margin-top: 12px;
}

#logo-title img{
  display: inline-block;
  padding: 0px;
  margin-left: 20px;
}
#logo{
  width: 50px;
  height: 50px;
  margin-top: 12px;
}
#title{
  width: 200px;
  height: 50px;
  margin-top: 15px;
  margin-left: 5px;
}

.navbar-right li a.iconTag:visited,.navbar-right li a.iconTag:link{
  color: #eee;
  font-size: 22px;
}
.navbar-right li a.iconTag:hover{
  color: #fff;
  font-size: 24px;
}

.navbar-right li a.textTag:visited,.navbar-right li a.textTag:link{
  color: #fff;
  background: #222;
  font-size: 16px;
  padding: 3px 8px;
  display: inline-block;
  border-radius: 3px;
  margin-top: 15px;
  margin-right: 3px;
}
.navbar-right li a.textTag:hover{
  color: #222;
  background: #fff;
  font-size: 16px;
  padding: 3px 8px;
  display: inline-block;
  border-radius: 3px;
  margin-top: 15px;
  margin-right: 3px;
}
.timeTip{

}

</style>
