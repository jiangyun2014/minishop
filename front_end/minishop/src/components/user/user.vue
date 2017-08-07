<template>
  <div id="user">
    <header>
      <h2>个人中心</h2>
    </header>
    <div id="userWrapper" class="container">
      <aside class="col-md-3">
        <ul id="sideLink">
          <li>
            <router-link to="/user/userInfo">基本信息</router-link>
          </li>
          <li>
            <router-link to="/user/wallet">我的钱包</router-link>
          </li>
          <li>
            <router-link to="/user/address">收货管理</router-link>
          </li>
          <li>
            <router-link to="/user/otherSettings">其他设置</router-link>
          </li>
        </ul>
      </aside>
      <main class="col-md-9">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script>
import router from '../../router';
export default {
  name: 'user',
  data () {
    return {

    }
  },
  created(){
    setTimeout(function(){
      router.push({name: 'user_userInfo'});
    },2000);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header h2{
  font-weight: bold;
  margin-left: 100px;
}

#userWrapper aside{
  background: #eee;
}
#sideLink{
  margin: 0;
  padding: 0;
  text-align: center;
}
#sideLink li{
  display: block;
  height: 60px;
  line-height: 60px;
}
#sideLink li a{
  font-size: 20px;
  color: #888;
}
#sideLink li a:hover{
  color: #222;
  text-decoration: none;
}

#userWrapper main{
  min-height: 400px;
}
</style>
