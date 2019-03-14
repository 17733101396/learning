$(function ($) {
	var $table = $("#ADTable");
	var $add = $("#add");
	var $remove = $("#remove");
	var $submit = $("#submit");
	var paramPower=$("#paramPower").val();
	if(paramPower==1){
		$table.bootstrapTable({	
			url:'listAllAD',
			method:'get',
			cache:false,
			pagination:true,
			pageSize:10,
			pageList:[10,15,20],
		    columns: [
		    {
			    field: 'select',
			    checkbox: true,
			    align: 'center',
			    valign: 'middle',
			    
		    },
		    {
		    	field:'id',
		    	title: '广告编号',
			    align: 'center',
			    valign: 'middle',
			    visible:false
		    },
		    {
		    	field:'context',
		    	title: '广告内容',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '广告内容',    
	            }
		    },
		    ],
		    
		    onEditableSave: function (field, row, oldValue, $el) { 
	            updateSong(row);
		    },
		    
		    onLoadSuccess: function (data){
		    	$remove.attr("disabled","disabled");
		    },
		    
		    onCheck: function (row){
		    	$remove.removeAttr("disabled");
		    },
		    
		    onCheckAll: function (row){
		    	$remove.removeAttr("disabled");
		    },
		    
		    onUncheck: function (row){
		    	var length=$table.bootstrapTable('getSelections').length;
		    	if(length==0){
		    		$remove.attr("disabled","disabled");
		    	}
		    },
		    
		    onUncheckAll: function (row){
		    	$remove.attr("disabled","disabled");
		    }
	    });
	}else if(paramPower==2){
		$add.hide();
		$remove.hide();
		$table.bootstrapTable({	
			url:'listAllAD',
			method:'get',
			cache:false,
			pagination:true,
			pageSize:10,
			pageList:[10,15,20],
		    columns: [
		    {
			    field: 'select',
			    checkbox: true,
			    align: 'center',
			    valign: 'middle',
			    visible:false
			    
		    },
		    {
		    	field:'id',
		    	title: '广告编号',
			    align: 'center',
			    valign: 'middle',
			    visible:false
		    },
		    {
		    	field:'context',
		    	title: '广告内容',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '广告内容',  
	                disabled:true
	            }
		    }
		    ],
	    });
	}else{
		$add.hide();
		$remove.hide();
		alert("您不是管理员");
		return;
	}
	
	function updateSong(param){
		$.ajax({
			url: 'updateAD',
			data:param,
			cache:false,
			error: function (){
				console.log("连接超时...");
				return false;
			},
			success: function (res){
				if(1==res){$table.bootstrapTable('refresh');console.log("更新成功");}
				else{$table.bootstrapTable('refresh');console.log("更新失败");}
			}
		});
	}
	
	//添加新行
	$submit.click(function (){
		var context;
		context=$("#context").val();
		var param= {
				context:context
		};
	    $.ajax({
			url: 'addAD',
			data:param,
			cache:false,
			error: function (){
				console.log("连接超时...");
				return false;
			},
			success: function (res){
				if(1==res){console.log("添加成功");}
				else{console.log("添加失败");}
			},
			complete: function (){
				$("#myModal").modal('hide');//隐藏modal
				//$('.modal-backdrop').remove();//去掉遮罩层
				$table.bootstrapTable('refresh');
			}
		});
	});
	
	//删除功能
	$remove.click(
	  function (){
		var con = confirm("是否删除?");
		if(!con){
			return;
		}
		var length=$table.bootstrapTable('getSelections').length;//获取选中的行数
		if(length==0){return ;}
		var i,id;
		var ids=[];
		for(i=0;i<length;i++){//遍历选中行
			id=$table.bootstrapTable('getSelections')[i].id;
			ids[i]=id;
		}	
		$.ajax({
			url: 'deleteAD',
			data:{ids:ids},
			cache:false,
			error: function (){
				console.log("连接超时...");
				return false;
			},
			success: function (res){
				if(1==res){$table.bootstrapTable('refresh');console.log("删除成功");}
				else{$table.bootstrapTable('refresh');console.log("删除失败");}
			}
		});
	  }
	);
});