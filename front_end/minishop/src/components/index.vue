<template>
  <div id="index" class="container">
    <v-header></v-header>
    <div id="wrapper">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import header from '@/components/header/header';

export default {
  name: 'index',
  data () {
    return {

    }
  },
  components: {
    'v-header': header
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#wrapper{
  margin-top: 82px;
  margin-bottom: 30px;
  border: 1px solid #ccc;
  min-height: 600px;
}
</style>
