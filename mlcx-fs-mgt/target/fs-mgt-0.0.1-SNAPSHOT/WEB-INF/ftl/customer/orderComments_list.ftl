<meta charset="utf-8">
<style>
	@media only screen and (max-width:992px) {
		.pull-down-menu input,
		.pull-down-menu select {
			width: 30%;
		}
		.seach-input-item pull-left .form-control {
			width: 45% !important;
			margin-right: 20%;
		}
		.pull-down-menu span {
			width: 20%;
		}
	}
	
	@media only screen and (max-width:768px) {
		.row .sorting_disabled {
			font-size: 1.1rem !important;
		}
	}
	
	@media only screen and (min-width:768px) and (max-width:1024px) {
		.row .sorting_disabled {
			font-size: 1.2rem !important;
		}
	}
	
	@media only screen and (min-width:1024px) and (max-width:1366px) {
		.row .sorting_disabled {
			font-size: 1.3rem !important;
		}
	}
	
	.other {
		margin: 0 !important;
		padding: 0 !important;
	}
	
	table {
		border: 1px solid rgba(127, 127, 127, 0.05);
	}
</style>
<div class="container-fluid">
	<div class="search-input-wrapper">
		<div class="clearfix search-input-header">
			<img class="pull-left" src="res/img/search.png">
			<!--<span class="pull-right moreButton" id="moreCarList">更多</span>-->
		</div>
		<div class="search-input-content">
			<form name="orderCommentsSearchForm">
				<div class="seach-input-border-box">
					<div class="seach-input-details close-content clearfix">
						<div class="seach-input-item pull-left">
							<span>订单号</span>
							<input class="form-control" name="orderNo" placeholder="请输入订单号">
						</div>
						<div class="seach-input-item pull-left">
							<span>车牌</span>
							<input class="form-control" name="carPlateNo" placeholder="请输入车牌">
						</div>
						<div class="seach-input-item pull-left">
							<span>车型</span>
							<input class="form-control" name="carModelName" placeholder="请输入车牌">
						</div>
						<div class="seach-input-item pull-left">
							<span>手机</span>
							<input class="form-control" name="mobilePhone" placeholder="请输入手机号码">
						</div>
						<div class="seach-input-item pull-left">
							<span>用户姓名</span>
							<input class="form-control" name="memberName" placeholder="请输入用户姓名">
						</div>
					</div>
				    <div class="seacher-button-content">
						<button type="reset" class="btn-new-type">清空</button>
						<button type="button" class="btn-new-type" id="orderCommentsSearch">确定</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row show-table-content">
		<div class="col-xs-12">
			<!--定义操作列按钮模板-->
			<script id="orderCommentsBtnTpl" type="text/x-handlebars-template">
				{{#each func}}
				<button type="button" class="btn-new-type btn-{{this.type}}" onclick="{{this.fn}}">{{this.name}}</button> {{/each}}
			</script>
			<div class="box">
				<div class="box-body">
					<table id="orderCommentsList" class="table table-hover table-bettween-far" width="100%" border="1">
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<!-- /.container-fluid -->

<script type="text/javascript">
	$(function() {
		require.config({
			paths: {
				"orderComments": "res/js/customer/orderComments_list"
			}
		});
		require(["orderComments"], function(orderComments) {
			orderComments.init();
		});
	});
</script>