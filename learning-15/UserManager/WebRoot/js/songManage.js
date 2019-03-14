$(function ($) {
	var $table = $("#songTable");
	var $add = $("#add");
	var $remove = $("#remove");
	var $submit = $("#submit");
	var paramPower=$("#paramPower").val();
	if(paramPower==1){
		$table.bootstrapTable({	
			url:'listAllSong',
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
		    	field:'songID',
		    	title: '歌曲编号',
			    align: 'center',
			    valign: 'middle',
			    visible:false
		    },
		    {
		    	field:'songName',
		    	title: '歌曲名称',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '歌曲名称',    
	            }
		    },
		    {
		    	field:'introduction',
		    	title: '歌曲介绍',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '歌曲介绍',    
	            }
		    },
		    {
		    	field:'singer',
		    	title: '歌手',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '歌手名字',    
	            }
		    },
		    {
		    	field:'download',
		    	title: '下载',
			    align: 'center',
			    valign: 'middle',
			    visible:false,
			    formatter:function(value, row, index){
			    	return '<a class="downloadFile" href="downloadFile">下载</a>';
			    }, 
			    events:{'click .downloadFile':function(event,value,row,index){
			    	console.log(row.songName);
			    	$(".downloadFile").attr("href","downloadFile?filename="+encodeURI(row.songName));
			    	}
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
			url:'listAllSong',
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
		    	field:'songID',
		    	title: '歌曲编号',
			    align: 'center',
			    valign: 'middle',
			    visible:false
		    },
		    {
		    	field:'songName',
		    	title: '歌曲名称',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '歌曲名称',  
	                disabled:true
	            }
		    },
		    {
		    	field:'introduction',
		    	title: '歌曲介绍',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '歌曲介绍',  
	                disabled:true
	            }
		    },
		    {
		    	field:'singer',
		    	title: '歌手',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '歌手名字',  
	                disabled:true
	            }
		    },
		    {
		    	field:'download',
		    	title: '下载',
			    align: 'center',
			    valign: 'middle',
			    visible:false,
			    formatter:function(value, row, index){
			    	return '<a class="downloadFile" href="downloadFile">下载</a>';
			    }, 
			    events:{'click .downloadFile':function(event,value,row,index){
			    	console.log(row.songName);
			    	$(".downloadFile").attr("href","downloadFile?filename="+encodeURI(row.songName));
			    	}
			    }
		    },
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
			url: 'updateSong',
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
		var songName,introduction,singer;
		songName=$("#songName").val();
		introduction=$("#introduction").val();
		singer=$("#singer").val();
		var param= {
				songName:songName,
				introduction:introduction,
				singer:singer
		};
	    $.ajax({
			url: 'addSong',
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
			id=$table.bootstrapTable('getSelections')[i].songID;
			ids[i]=id;
		}	
		$.ajax({
			url: 'deleteSong',
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
	
	$("#download").click(function(){
		console.log("55");
	});
	
});