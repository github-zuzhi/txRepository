<meta charset="utf-8">
<style>
	.seach-input-item pull-left .form-control {
		width: 50% !important;
	}
	
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
	
	table {
		border: 1px solid rgba(127, 127, 127, 0.05);
	}
	.matching-brand-img{
        background-size:100%;
        background-position:center;
        background-repeat:no-repeat;
        border:solid 1px #ddd;
        width:50%;
        height:80%;
        border-radius:20%;
        margin:0 auto;
    }
</style>
<div class="container-fluid">
	<div class="search-input-wrapper">
		<div class="clearfix search-input-header">
			<img class="pull-left" src="res/img/search.png">
		</div>
		<div class="pull-right moreButtonNew" id="morematching">
		</div>
		<div class="search-input-content">
			<form class="form-horizontal" name="parkBillingSearchForm">
				<div class="seach-input-border-box">
					<div class="seach-input-details close-content clearfix">
                        <div class="seach-input-item pull-left">
                            <span>计费方案编号</span>
                            <input type="text" class="form-control" name="parkBillingNo" placeholder="请输入计费方案编号">
                        </div>
                        <div class="seach-input-item pull-left">
                            <span>计费方案名称</span>
                            <input type="text" class="form-control" name="parkBillingName" placeholder="请输入计费方案名称">
                        </div>
                        <div class="seach-input-item pull-left">
                            <span>计费方案版本</span>
                            <input type="text" class="form-control" name="billingVersion" placeholder="请输入计费方案版本">
                        </div>

                        <div class="seach-input-item pull-left">
                            <span>停车类型</span>
                            <select class="form-control" name="parkType">
                                <option value="">--全部--</option>
                                <option value="0">闸</option>
                                <option value="1">地锁</option>
                            </select>
                        </div>

						<div class="seach-input-item pull-left">
							<span>启用状态</span>
							<select class="form-control" name="status">
								<option value="">--全部--</option>
								<option value="0">停用</option>
								<option value="1">启用</option>
							</select>
						</div>
					</div>
					<div class="seacher-button-content">
						<button type="reset" class="btn-new-type">清空</button>
						<button type="button" class="btn-new-type" id="parkBillingSearchafter">确定</button>
					</div>
				</div>
			</form>
		</div>
		<!-- /.box-footer -->
	</div>
	<!-- /.box -->
	<div class="row show-table-content">
		<div class="col-xs-12">
			<!--定义操作列按钮模板-->
			<script id="parkBillingTpl" type="text/x-handlebars-template">
				{{#each func}}
				<button type="button" class="btn-new-type btn-{{this.type}}" onclick="{{this.fn}}">{{this.name}}</button> {{/each}}
			</script>
			<div class="box">
				<div class="box-body">
					<table id="parkBillingList" class="table table-hover table-bettween-far" width="100%" border="1">
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
	</div>
</div>
<!-- /.container-fluid -->
<script type="text/javascript">
	$(function() {
		require.config({
			paths: {
				"parkBillingList": "res/js/mlpark/parkBilling_list"
			}
		});
		require(["parkBillingList"], function(parkBillingList) {
            parkBillingList.init();
		});
		$(".datepicker").datepicker({
			language: "zh-CN",
			autoclose: true, //选中之后自动隐藏日期选择框
			clearBtn: true, //清除按钮
			todayBtn: "linked", //今日按钮
			format: "yyyy-mm-dd" //日期格式，详见 http://bootstrap-datepicker.readthedocs.org/en/release/options.html#format
		});
	});
</script>