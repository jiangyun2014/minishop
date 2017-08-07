<template>
  <div id="logout">
    <header>
      <button class="btn btn-primary col-xs-3 col-xs-offset-4" @click="logout()">注销</button>
    </header>
  </div>
</template>

<script>
import router from '../../router';
export default {
  name: 'logout',
  data () {
    return {

    }
  },
  methods: {
    logout(){
      if(!window.confirm('你确认要退出吗？')){
        return false;
      }
      EasyStorage.removeItem('token');
      setTimeout(function(){
        //到首页
        router.push({name: 'home'});
      },1000);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header{
  margin-top: 30px;
  margin-left: 100px;
}
</style>
