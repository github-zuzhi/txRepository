<meta charset="utf-8">
<style>
.seach-input-item pull-left .form-control{
	width:50% !important;
}
@media only screen and (max-width:992px) {
.pull-down-menu input, .pull-down-menu select {
    width: 30%;
}
.seach-input-item pull-left .form-control{
	width:45% !important;
	margin-right:20%;
}
.pull-down-menu span {
    width: 20%;
}
}
@media only screen and (max-width:768px) {
.row .sorting_disabled{
font-size:1.1rem !important;
}
}
@media only screen and (min-width:768px) and (max-width:1024px) {
.row .sorting_disabled{
font-size:1.2rem !important;
}
}
@media only screen and (min-width:1024px) and (max-width:1366px) {
.row .sorting_disabled{
font-size:1.3rem !important;
}
}
table{
border:1px solid rgba(127,127,127,0.05);
}
</style>
   <div class="container-fluid">
     <div class="search-input-wrapper">
		<div class="clearfix search-input-header">
			<img class="pull-left" src="res/img/search.png">
			<!--<span class="pull-right moreButton" id="moreCarList">更多</span>-->
		</div>
		<div class="search-input-content">
         <form class="form-horizontal" name="redeemCodeSearchForm">
        <div class="seach-input-border-box">
					<!-- /.box-tools -->
					<div class="seach-input-details close-content clearfix">
                <div class="seach-input-item pull-left">
                    <span>名称</span>
                    <input type="text" class="form-control" name="redName" placeholder="请输入名称">
                </div>
                <div class="seach-input-item pull-left">
                    <span>审核状态</span>
                    <select class="form-control" name="censorStatus">
                    	<option value="">全部</option>
                        <option value="0" >未审核</option>
                        <option value="1" >已审核</option>
                        <option value="2" >未通过</option>
                   </select>
                </div>
                <div class="seach-input-item pull-left">
                    <span>启用状态</span>
                    <select class="form-control" name="isAvailable">
	                     <option value="">全部</option>
	                     <option value="0">停用</option>
	                     <option value="1">启用</option>
                    </select>
                </div>
                 <div class="seach-input-item pull-left">
                      <span>有效日期</span>
                      <input class="datepicker form-control" name="availableTime1" placeholder="请选择有效日期开始时间"/>
                  </div>
                 <div class="seach-input-item pull-left">
                     <span>至</span>
                     <input class="datepicker form-control" name="availableTime2" placeholder="请选择有效日期结束时间"/>
                 </div>
         </div>
         			<div class="seacher-button-content">
						<button type="reset" class="btn-new-type">清空</button>
						<button type="button" class="btn-new-type" id="redeemCodeSearchafter">确定</button>
					</div>
        </div><!-- /.box-body -->
         </form>
         <!-- /.box-footer -->
       </div><!-- /.box -->
	  </div><!-- /.col -->	
     <div class="row show-table-content">
      <div class="col-xs-12">
       <!--定义操作列按钮模板-->
       <script id="redeemCodeTpl" type="text/x-handlebars-template">
        {{#each func}}
        <button type="button" class="btn-new-type btn-{{this.type}}" onclick="{{this.fn}}">{{this.name}}</button>
        {{/each}}
       </script>
       <div class="box">
        <div class="box-body">
         <table id="redeemCodeList" class="table table-hover table-bettween-far" width="100%" border="1">
         </table>
        </div><!-- /.box-body -->
       </div><!-- /.box -->
      </div>
     </div>
    </div><!-- /.container-fluid -->
    <script type="text/javascript">
    $(function(){
    require.config({paths: {"redeemCode":"res/js/marketing/redeemCode_list"}});
		require(["redeemCode"], function (redeemCode){
			redeemCode.init();
		});
		$(".datepicker").datepicker({
            language: "zh-CN",
            autoclose: true,//选中之后自动隐藏日期选择框
            clearBtn: true,//清除按钮
            todayBtn: 'linked',//今日按钮
            format: "yyyy-mm-dd"//日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
        });
	   }); 
    </script>
