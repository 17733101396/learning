$(function ($) {
	var $table = $("#deviceTable");
	var $add = $("#add");
	var $remove = $("#remove");
	var $submit = $("#submit");
	var paramDeviceType=$("#paramDeviceType").val();
	var paramPower=$("#paramPower").val();
	if(paramPower==1){
		$table.bootstrapTable({
			url:'listAllDevice.do?paramDeviceType='+paramDeviceType,
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
			    valign: 'middle'
		    },
		    {
			    field: 'id',
			    title: '编号',
			    align: 'center',
			    valign: 'middle'
		    },
		    {
			    field: 'deviceID',
			    title: '设备ID',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '设备ID',    
	            }
		    },
		    {
			    field: 'deviceName',
			    title: '设备名',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '设备名',
	            }
		    },
		    {
			    field: 'deviceType',
			    title: '设备类型',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'select',
	                source: [{value:0,text:"android端"},{value:1,text:"linux端"}],
	                title: '设备类型',
	            }
		    },
		    {
			    field: 'type',
			    title: '在线状况',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'select',
	                title: '在线状况',
	                source: [{value:0,text:"下线"},{value:1,text:"上线"}],
	            }
		    },
		    {
			    field: 'time',
			    title: '操作起始时间',
			    placement: 'left',
	            editable: {
	                type: 'combodate',   
	                title: '操作起始时间',
	                format: 'YYYY-MM-DD',    
	                viewformat: 'YYYY-MM-DD',    
	                template: 'YYYY / MM / DD',
	                combodate: {
	                    minYear: 1920,
	                    maxYear: 2020,
	                    minuteStep: 1
	               }
	            }
		    },
		    {
			    field: 'adList',
			    title: '广告列表',
	            editable: {
	                type: 'checklist',
	                title: '广告列表',
	                placement: 'left',
	                mode: "inline",
	                source: function (){
	                	var result = [];
                        $.ajax({
                            url: 'listAllAD.do',
                            async: false,
                            type: "get",
                            data: {},
                            success: function (data, status) {
                                $.each(data, function (key, value) {
                                    result.push({value: value.id, text: value.context});
                                });
                            }
                        });
                        return result;
	                }
	            }
		    },
		    {
			    field: 'songList',
			    title: '歌曲列表',
	            editable: {
	                type: 'checklist',
	                title: '歌曲列表',
	                placement: 'left',
	                mode: "inline",      
	                source: function (){
	                	var result = [];
                        $.ajax({
                            url: 'listAllSong.do',
                            type: "get",
                            data: {},
                            async: false,
                            success: function (data, status) {
                                $.each(data, function (key, value) {
                                    result.push({value: value.songID, text: value.songName});
                                });
                            }
                        });
                        return result;
	                }
	            }
		    },
		    ],
		    
		    onEditableSave: function (field, row, oldValue, $el) { 
		    	//检查提交的adList和songList是否为字符串,不是则转化为 字符串
		    	if(typeof row.adList != 'string'){
		    		row.adList = row.adList.join(",");
		    		console.log(row.adList);
		    	}
		    	if(typeof row.songList != 'string'){
		    		row.songList = row.songList.join(",");
		    		console.log(row.songList);
		    	}  	
	            updateUser(row);
	            console.log(row);
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
	} else if(paramPower==2){
		$add.hide();
		$remove.hide();
		$table.bootstrapTable({	
			url:'listAllDevice.do?paramDeviceType='+paramDeviceType,
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
			    field: 'id',
			    title: '编号',
			    align: 'center',
			    valign: 'middle'
		    },
		    {
			    field: 'deviceID',
			    title: '设备ID',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '设备ID',    
	                disabled:true
	            }
		    },
		    {
			    field: 'deviceName',
			    title: '设备名',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '设备名',
	                disabled:true
	            }
		    },
		    {
			    field: 'deviceType',
			    title: '设备类型',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'select',
	                source: [{value:0,text:"手机端"},{value:1,text:"linux端"}],
	                title: '设备类型',
	                disabled:true
	            }
		    },
		    {
			    field: 'type',
			    title: '在线状况',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'select',
	                title: '在线状况',
	                source: [{value:0,text:"下线"},{value:1,text:"上线"}],
	                disabled:true
	            }
		    },
		    {
			    field: 'time',
			    title: '操作起始时间',
			    placement: 'left',
			    editable: {
	                type: 'combodate',   
	                title: '操作起始时间',
	                format: 'YYYY-MM-DD',    
	                viewformat: 'YYYY-MM-DD',    
	                template: 'YYYY / MM / DD',
	                combodate: {
	                    minYear: 1920,
	                    maxYear: 2020,
	                    minuteStep: 1
	               }
	            }
		    },
		    {
			    field: 'adList',
			    title: '广告列表',
	            editable: {
	                type: 'checklist',
	                title: '广告列表',
	                placement: 'left',                
	                disabled:true,
	                source: function (){
	                	var result = [];
                        $.ajax({
                            url: 'listAllAD.do',
                            async: false,
                            type: "get",
                            data: {},
                            success: function (data, status) {
                                $.each(data, function (key, value) {
                                    result.push({value: value.id, text: value.context});
                                });
                            }
                        });
                        return result;
	                }
	            }
		    },
		    {
			    field: 'songList',
			    title: '歌曲列表',
	            editable: {
	                type: 'checklist',
	                title: '歌曲列表',
	                placement: 'left',   
	                disabled:true,
	                source: function (){
	                	var result = [];
                        $.ajax({
                            url: 'listAllSong.do',
                            async: false,
                            type: "get",
                            data: {},
                            success: function (data, status) {
                                $.each(data, function (key, value) {
                                    result.push({value: value.songID, text: value.songName});
                                });
                            }
                        });
                        return result;
	                }
	            }
		    },
		    ],
	    });
	} else{
		$add.hide();
		$remove.hide();
		alert("您不是管理员");
		return;
	}
	
	function updateUser(param){
		$.ajax({
			url: 'updateDevice.do',
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
		var deviceID,deviceName,deviceType,type,time;
		var adList=[];
		var songList=[];
		deviceID=$("#deviceID").val();
		deviceName=$("#deviceName").val();
		deviceType=paramDeviceType;
		type=0;
		time=(new Date()).Format("yyyy-MM-dd");
		$("input[name='adList']:checked").each(function(i){
			adList[i]=$(this).val();
		});
		$("input[name='songList']:checked").each(function(i){
			songList[i]=$(this).val();
		});
		//把adList和songList转化为字符串
		adList=adList.join(",");
		songList=songList.join(",");
		var param= {
				deviceID:deviceID,
				deviceName:deviceName,
				deviceType:deviceType,
				type:type,
				time:time,
				adList:adList,
				songList:songList
		};
	    $.ajax({
			url: 'addDevice.do',
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
			url: 'deleteDevice.do',
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
	
	$add.click(function(){
        $.ajax({
            url: 'listAllAD.do',
            type: "get",
            data: {},
            success: function (data, status) {
                $.each(data, function (key, value) {
                    $("#adList").append(" <input type='checkbox' name='adList' value="+value.id+">"+value.context+"</br> ");
                });
            }
        });
        $.ajax({
            url: 'listAllSong.do',
            type: "get",
            data: {},
            success: function (data, status) {
                $.each(data, function (key, value) {
                    $("#songList").append(" <input type='checkbox' name='songList' value="+value.songID+">"+value.songName+"</br> ");
                });
            }
        });
	});
});



