<template>
  <div id="address">
    <header>
      <button class="btn btn-sm btn-primary" type="button" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span> 添加收货人</button>
    </header>
    <hr/>
    <main>
      <div class="mainArea" v-for="(receiver,index) in receiverList">
        <div class="recvArea">
          <div>
            <h4 v-text="receiver.receiver"></h4>
            <h5 v-text="'联系电话：'+receiver.telnum"></h5>
          </div>
          <div class="clearfix"></div>
          <p v-text="'收货地址：'+receiver.address"></p>
        </div>
        <div class="checkArea">
          <button class="btn btn-danger btn-sm" @click="deleteReceiver(receiver.id)">删除</button>
        </div>
        <div class="clearfix"></div>
      </div>
    </main>

    <!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h5 class="modal-title" id="myModalLabel">添加收货人</h5>
					</div>
					<div class="modal-body">
						<form class="container form-horizontal">
							<div class="form-group">
								<label class="col-sm-1 control-label">收货人</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" v-model="addReceiver.receiver"/>
								</div>
							</div>
              <div class="form-group">
								<label class="col-sm-1 control-label">联系电话</label>
								<div class="col-sm-4">
									<input class="form-control" type="tel" v-model="addReceiver.telnum"/>
								</div>
							</div>
              <div class="form-group">
								<label class="col-sm-1 control-label">收货地址</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" v-model="addReceiver.address"/>
								</div>
							</div>
						</form>
						<div class="text-center" style="color:#f00;" v-text="formTip"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"  @click="addReceiverF()">保存</button>
					</div>
				</div>
			</div>
		</div>

  </div>
</template>

<script>
import receiverSrv from '../../api/receiverSrv';
import router from '../../router';
export default {
  name: 'address',
  data () {
    return {
      formTip: '',
      addReceiver: {
        receiver: '',
        telnum: '',
        address: ''
      },
      receiverList: []
    }
  },
  created(){
    this.renderPage();

  },
  methods: {
    renderPage(){
      var ctx = this;
      receiverSrv.getReceiverByIdentity(function(data){
        ctx.receiverList = data;
      },function(data){
        console.log('error!');
      });
    },
    addReceiverF(){
      if(this.addReceiver.receiver!='' && this.addReceiver.telnum!='' && this.addReceiver.address!=''){
        receiverSrv.addReceiver(this.addReceiver,function(data){
          alert(data.msg);
          router.push({name: 'user_address'});
        },function(data){
          console.log('error!');
        });
      }else{
        this.formTip = '请完整输入所有信息!';
      }
    },
    deleteReceiver(id){
      if(!window.confirm('确认删除？')){
        return false;
      }
      receiverSrv.deleteReceiver(id,function(data){
        alert('删除成功！');
        router.push({name: 'user_address'});
      },function(data){
        console.log('error!');
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
header{
  padding-left: 30px;
  padding-top: 20px;
}

.mainArea{
  margin-left: 30px;
  margin-top: 10px;
  border-bottom: 1px solid #ccc;
}
.checkArea{
  display: inline-block;
  float: right;
  padding-right: 30px;
}
.recvArea{
  width: 80%;
  display: inline-block;
  float: left;
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

</style>
