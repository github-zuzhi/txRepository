define(
[],
function() {
var parkBillingEdit = {
    appPath : getPath("app"),
    imgPath:getPath("img"),

	init : function() {
		 $('input').attr('autocomplete','off');//input框清楚缓存
		//添加提交
		$("#EditparkBilling").click(function(){
            parkBillingEdit.Editmatching();
		});
		//返回
		$("#EditcloseparkBilling").click(function(){
			closeTab("编辑配套服务");
			$("#parkBillingList").DataTable().ajax.reload(function(){});
		});
	},
	Editmatching:function() {
		var form = $("form[name=parkBillingEditForm]");
		form.ajaxSubmit({
			url : parkBillingEdit.appPath + "/parkBilling/toEditParkBilling.do",
			type : "post",
			success : function(res) {
				var msg = res.msg;
				var code = res.code;
				if (code == "1") {
					bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "编辑成功", function() {
						closeTab("编辑配套服务");
						$("#parkBillingList").DataTable().ajax.reload(function(){});
					});
				} else {
					bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "编辑失败，"+msg+"！");
				}
			},
			beforeSubmit : function(formData, jqForm, options) {// 提交表单前数据验证
                var form = $("form[name=parkBillingEditForm]");
                $("span[id$='add_matching_mlcx_verify']").html("");//清空所有span提示
                form.find("input[name='parkBillingName']").val();
                form.find("input[name='billingVersion']").val();
                form.find("select[name='parkType']").val();
                form.find("input[name='freeTime']").val();
                form.find("input[name='overtimePrice']").val();
                form.find("input[name='unitTime']").val();
                if(form.find("input[name='parkBillingName']").val()==''){
                    spanWarning("parkBillingName_add_matching","请输入计费方案名称!")
                }
                if(form.find("input[name='billingVersion']").val()==''){
                    spanWarning("billingVersion_add_matching","请输入计费方案版本!")
                }

                if(form.find("select[name='parkType']").val()==''){
                    spanWarning("parkType_add_matching","请选择停车类型!")
                }
                if(form.find("input[name='freeTime']").val()==''){
                    spanWarning("freeTime_add_matching","请输入预约免费时长!")
                }

                if(form.find("input[name='overtimePrice']").val()==''){
                    spanWarning("overtimePrice_add_matching","请输入金额!")
                }
                if(form.find("input[name='unitTime']").val()==''){
                    spanWarning("unitTime_add_matching","请输入计费时间单位!")
                }
                //判断提交验证
                var add_matching_mlcx_verify=$("span[id$='add_matching_mlcx_verify']")
                for(var i=0;i<add_matching_mlcx_verify.length;i++){
                    if(add_matching_mlcx_verify[i].innerHTML!=''){
                        return false;
                    }
                }
                return true;
			}
		});
	 },
	}
	return parkBillingEdit;
})
