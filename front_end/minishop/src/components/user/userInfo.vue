<template>
  <div id="userInfo">
    <main>
      <img id="userPic" src="./photo.jpg"/>
      <hr/>
      <div id="con">
        <h4>性别：<span v-text="user.gender"></span></h4>
        <h4>名称：<span v-text="user.username"></span></h4>
        <h4>账号：<span v-text="user.account"></span></h4>
      </div>
    </main>
  </div>
</template>

<script>
import userSrv from '../../api/userSrv';
export default {
  name: 'userInfo',
  data () {
    return {
      user: {}
    }
  },
  created() {
    var ctx = this;
     userSrv.getUserByIdentity(function(data){
      ctx.user = data;
    },function(data){
      console.log('error!')
    });
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
main{
  text-align: center;
}
#userPic{
  width:160px;
  height: 160px;
  border: 1px solid #ccc;
  border-radius: 80px;
}
#con{
  width: 30%;
  margin: 0 auto;
  text-align: left;
}

</style>
