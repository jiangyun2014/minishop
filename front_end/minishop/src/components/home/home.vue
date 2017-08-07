<template>
  <div id="home">
    <v-banner></v-banner>
    <v-goods></v-goods>
  </div>
</template>

<script>
import banner from '@/components/banner/banner';
import goods from '@/components/goods/goods';

export default {
  name: 'home',
  data () {
    return {

    }
  },
  components: {
    'v-banner': banner,
    'v-goods': goods
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
