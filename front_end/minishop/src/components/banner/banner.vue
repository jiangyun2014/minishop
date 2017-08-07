<template>
  <div id="banner">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
      </ol>
      <!-- Wrapper for slides -->
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img src="./banner_01.png" alt="This is a picture!" style="height: 300px;width: 100%;">
          <div class="carousel-caption">
            <h2></h2>
          </div>
        </div>
        <div class="item">
          <img src="./banner_02.png" alt="This is a picture!" style="height: 300px;width: 100%;">
          <div class="carousel-caption">
            <h2></h2>
          </div>
        </div>
        <div class="item">
          <img src="./banner_03.png" alt="This is a picture!" style="height: 300px;width: 100%;">
          <div class="carousel-caption">
            <h2></h2>
          </div>
        </div>
        <div class="item">
          <img src="./banner_04.png" alt="This is a picture!" style="height: 300px;width: 100%;">
          <div class="carousel-caption">
            <h2></h2>
          </div>
        </div>
        <div class="item">
          <img src="./banner_05.png" alt="This is a picture!" style="height: 300px;width: 100%;">
          <div class="carousel-caption">
            <h2></h2>
          </div>
        </div>
      </div>
      <!-- Controls -->
      <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'banner',
  data () {
    return {

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#banner{
  margin-bottom: 10px;
  border-bottom: 1px solid #222;
}
</style>
