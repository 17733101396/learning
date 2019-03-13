$(function($) {
	var deviceID=$("#deviceID").val();
	$("#songTable").bootstrapTable({	
		url:'clientSelectAllSong?deviceID='+deviceID,
		method:'post',
		cache:false,
		async: false,
		pagination:true,
		pageSize:10,
		pageList:[10,15,20],
	    columns: [
	    {
	    	field:'songID',
	    	title: '歌曲编号',
		    align: 'center',
		    valign: 'middle'
	    },
	    {
	    	field:'songName',
	    	title: '歌曲名称',
		    align: 'center',
		    valign: 'middle',
	    },
	    {
	    	field:'introduction',
	    	title: '歌曲介绍',
		    align: 'center',
		    valign: 'middle',
	    },
	    {
	    	field:'singer',
	    	title: '歌手',
		    align: 'center',
		    valign: 'middle',
	    },
	    {
	    	field:'collection',
	    	title: '收藏',
		    align: 'center',
		    valign: 'middle',
		    editable:{
		    	title:"收藏",
		    	type:"select",
		    	source:[{value:"1",text:"已收藏"},{value:"0",text:"未收藏"}],
		    }
	    },
	    ],
	    
	    onEditableSave: function (field, row, oldValue, $el) { 
	    	var param = {
	    			deviceID:deviceID,
	    			songID:row.songID,
	    			collection:row.collection
	    	};
	    	updateCol(param);
	    },
    });
	
	function updateCol(param){
		$.ajax({
			url: 'collectionManage',
			data:param,
			method:'post',
			cache:false,
			error: function (){
				console.log("连接超时...");
				return false;
			},
			success: function (res){
				if(1==res){$("#songTable").bootstrapTable('refresh');console.log("更新成功");}
				else{$("#songTable").bootstrapTable('refresh');console.log("更新失败");}
			}
		});
	}
	
	$("#songCollectionTable").bootstrapTable({	
		url:'clientSelectSong?deviceID='+deviceID,
		method:'post',
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
		    valign: 'middle'
	    },
	    {
	    	field:'songName',
	    	title: '歌曲名称',
		    align: 'center',
		    valign: 'middle',
	    },
	    {
	    	field:'introduction',
	    	title: '歌曲介绍',
		    align: 'center',
		    valign: 'middle',
	    },
	    {
	    	field:'singer',
	    	title: '歌手',
		    align: 'center',
		    valign: 'middle',
	    },
	    ],
	    
	    onLoadSuccess: function (data){
	    	$("#remove").attr("disabled","disabled");
	    },
	    
	    onCheck: function (row){
	    	$("#remove").removeAttr("disabled");
	    },
	    
	    onCheckAll: function (row){
	    	$("#remove").removeAttr("disabled");
	    },
	    
	    onUncheck: function (row){
	    	var length=$("#songCollectionTable").bootstrapTable('getSelections').length;
	    	if(length==0){
	    		$remove.attr("disabled","disabled");
	    	}
	    },
	    
	    onUncheckAll: function (row){
	    	$("#remove").attr("disabled","disabled");
	    }
	    
    });
	
	//删除功能
	$("#remove").click(
	  function (){
		var con = confirm("是否移出收藏列表?");
		if(!con){
			return;
		}
		var length=$("#songCollectionTable").bootstrapTable('getSelections').length;//获取选中的行数
		if(length==0){return ;}
		var i,id;
		var ids=[];
		for(i=0;i<length;i++){//遍历选中行
			id=$("#songCollectionTable").bootstrapTable('getSelections')[i].songID;
			ids[i]=id;
		}	
		$.ajax({
			url: 'cancleCollection',
			method:'post',
			data:{
				songList:ids,
				deviceID:deviceID	
			},
			cache:false,
			error: function (){
				console.log("连接超时...");
				return false;
			},
			success: function (res){
				if(1==res){$("#songCollectionTable").bootstrapTable('refresh');console.log("删除成功");}
				else{$("#songCollectionTable").bootstrapTable('refresh');console.log("删除失败");}
			}
		});
	  }
	);
	
	$("#ADTable").bootstrapTable({	
		url:'clientSelectAD?deviceID='+deviceID,
		method:'post',
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
		    valign: 'middle'
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
	 
});