define([],function() {	
	var carInventory={
			appPath:getPath("app"),	
			init: function () {	
	            //数据列表
				carInventory.pageList();
				//查询
				$("#carInventorySearchafter").click(function(){
					carInventory.pageList();
	            });
				
			},
				 
					  
			//方法加载
	        operateColumEvent: function(){
				
					$(".carInventory-operate-edit").on("click",function(){
						addTab("库存编辑",carInventory.appPath+ "/carInventory/toCarInventoryEdit.do?carInventoryId="+$(this).data("id"));			
					});
					$(".carInventory-operate-get").on("click",function(){
						addTab("库存详情",carInventory.appPath+ "/carInventory/toCarInventoryView.do?carInventoryId="+$(this).data("id"));			
					});
					$(".carInventory-operate-on").on("click",function(){
						var carInventoryId = $(this).data("id");
						bootbox.confirm("<img src='res/img/wen.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "确定要启用当前车辆库存吗？", function(result) {
							if(result){
								$.post("carInventory/editCarInventoryNs.do",{carInventoryId:carInventoryId,isAvailable:1},function(res){	
									if(res.code==1){

										bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "启用成功！",function(){
											  $(".bootbox").modal("hide");
											  $("#carInventoryList").DataTable().ajax.reload(function(){
					    						}); 
										  });
										
									}else{
										bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + res.msg);
									}
									
								});
							}						
						});
					});
					$(".carInventory-operate-off").on("click",function(){
						var carInventoryId = $(this).data("id");
						bootbox.confirm("<img src='res/img/wen.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "确定要停用当前车辆库存吗？", function(result) {
							if(result){
								$.post("carInventory/editCarInventoryNs.do",{carInventoryId:carInventoryId,isAvailable:0},function(res){	
									if(res.code==1){

										bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "停用成功！",function(){
											  $(".bootbox").modal("hide");
											  $("#carInventoryList").DataTable().ajax.reload(function(){
					    						}); 
										  });
										
									}else{
										bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + res.msg);
									}
									
								});
							}						
						});
					});
//					$(".carInventory-operate-del").on("click",function(){
//						var carInventoryId = $(this).data("id");
//						bootbox.confirm("<img src='res/img/wen.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "确定要删除吗？", function(result) {
//							if(result){
//							
//								$.post("carInventory/delcarInventory.do",{carInventoryId:carInventoryId},function(res){	
//									if(res.code==1){
//
//										bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "删除成功！",function(){
//											  $(".bootbox").modal("hide");
//											  $("#carInventoryList").DataTable().ajax.reload(function(){
//					    						}); 
//										  });
//										
//									}else{
//										bootbox.alert("<img src='res/img/tan.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + res.msg);
//									}
//									
//								});
//							}						
//	       				});
//						
//					});
	        },
			pageList:function () {	
				var carInventoryTpl = $("#carInventoryTpl").html();
				// 预编译模板
				var template = Handlebars.compile(carInventoryTpl);
				$('#carInventoryList').dataTable( {
					searching:false,
					destroy: true,
					"ajax": {
						"type": "POST",
						"url": carInventory.appPath+'/carInventory/pageListCarInventory.do',
						"data": function ( d ) {
							var form = $("form[name=carInventorySearchForm]");
							return $.extend( {}, d, {
								"pageNo": (d.start/d.length)+1,
								"pageSize":d.length,
								"cityId":form.find("select[name=cityId]").val(),
								"parkNo":form.find("select[name=parkNo]").val(),
								"carBrandId":form.find("select[name=carBrandId]").val(),
								"carModelId":form.find("select[name=carModelId]").val(),
								
							} );
						},
						"dataSrc": function ( json ) {
							json.recordsTotal=json.rowCount;
							json.recordsFiltered=json.rowCount;
							json.data=json.data;
							return json.data;
						},
						error: function (xhr, error, thrown) {  
			            }
					},
					"columns": [
			            { "title":"品牌","data": "carBrandName" },
						{ "title":"车型","data": "carModelName" },
						//{ "title":"场站名称","data": "parkName" },
						{ "title":"城市名称","data": "cityName" },
						{ "title":"日期","data": "inventoryDate" },
						{ "title":"库存数","data": "inventoryTotal" },
						{ "title":"已租借数量","data": "leasedQuantity" },
						//{ "title":"已预定数量","data": "reserveQuantity" },
						{ "title":"实际可用库存","data": "available" },
						//{ "title":"状态","data": "isAvailable" },
						{ "title":"操作","orderable":false}
					],
				   ///"dom": "<'row'<'col-xs-2'l><'#carInventorytool.col-xs-4'><'col-xs-6'f>r>" +"t" +"<'row'<'col-xs-6'i><'col-xs-6'p>>",
				   "dom": "<'row'<'#carInventoryTools.col-xs-6'><'col-xs-6'f>r>" +"t" +"<'row'<'col-xs-3'l><'col-xs-3'i><'col-xs-6'p>>",
				   initComplete: function () {
					   $("#carInventoryTools").html("");
		       			$("#carInventoryTools").append('<button type="button" class="btn btn-default btn-sm carInventoryTools-operate-add btnDefault">新增</button>');
		       			
		       			$(".carInventoryTools-operate-add").on("click",function(){
		       				addTab("库存新增", carInventory.appPath+ "/carInventory/toCarInventoryAdd.do");
		       			});	 
		       			
		       			/**
		       			 * 导出
		       			 */
		       			/*$(".carInventoryTools-operate-export").on("click",function(){
		       				bootbox.confirm("<img src='res/img/wen.png' style='width: 29px;height: 29px;margin-top: -4px'>&nbsp;&nbsp;" + "确定要导出模板吗？", function(result) {
								if(result){
									window.location.href = carInventory.appPath+ "/carInventory/downloadExcelFile.do?filepath=carInventory&newFileName=carInventory.xls";;
								}						
		       				});
		       			});*/
		       			
					},
					"drawCallback": function( settings ) {
						carInventory.operateColumEvent();
	        	    },
	        	    "order": [[ 1, "desc" ]],
					"columnDefs": [
						{
						    "targets": [3],
						    "render": function(data, type, row, meta) {
						    	if(data){
						    		var now = moment(data); 
						        	return now.format('YYYY-MM-DD');
						    	}else{
						    		return "";
						    	}
						    	 
						    }
						},
						/*{
						    "targets": [7],
						    "render": function(data, type, row, meta) {
						        if(data==0){
						        	return "停用";
						        }else if(data==1){
						        	return "启用";
						        }else{
						        	return "";
						        }
						    }
						},*/
						
						{
							"targets": [7],
							"render": function (a, b, c, d) {
								//var edit='';
								var get='<span class="glyphicon  carInventory-operate-get" data-id="'+c.carInventoryId+'" data-toggle="tooltip" data-placement="top">查看</span>';
	        					//var on = "";
	        					//var off = "";
								var edit='<span class="glyphicon  carInventory-operate-edit" data-id="'+c.carInventoryId+'" data-toggle="tooltip" data-placement="top">编辑</span>';
								//if(c.isAvailable==0){
									
									//on='<span class="glyphicon  btn btn-default  carInventory-operate-on" data-id="'+c.carInventoryId+'" data-toggle="tooltip" data-placement="top">启用</span>';
								//}else if(c.isAvailable==1){
									//off='<span class="glyphicon  btn btn-default  carInventory-operate-off" data-id="'+c.carInventoryId+'" data-toggle="tooltip" data-placement="top">停用</span>';
								//}
								
								return get+edit;
	        					
							}
						}
					]
				});
			}
	    };
	return carInventory;
});


