//JavaScript Document
$(function ($) {
	var $table = $("#usertab");
	var $add = $("#add");
	var $remove = $("#remove");
	var $submit = $("#submit");
	var paramPower=$("#paramPower").val();
	if(paramPower==1){
		$table.bootstrapTable({	
			url:'listAllUser.do',
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
			    field: 'userName',
			    title: '用户名',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '用户名',
	                validate: function (username) {
	                	if(username.length<5||username.length>15){return "用户名长度在5-15位之间";}
	                }
	            }
		    },
		    {
			    field: 'sex',
			    title: '性别',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'select',
	                title: '性别',
	                source:[{value:"男",text:"男"},{value:"女",text:"女"}],
	                
	            }
		    },
		    {
			    field: 'age',
			    title: '年龄',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '年龄',
	                validate: function (age) {
	                	if(!(/^[0-9]+$/.test(age)&&age>=0)){return "请输入自然数";}
	                }
	            }
		    },
		    {
			    field: 'phone',
			    title: '电话',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '电话',
	            }
		    },
		    {
			    field: 'address',
			    title: '住址',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '住址',
	            }
		    },
		    {
			    field: 'password',
			    title: '密码',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'text',
	                title: '密码',
	                validate: function (password) {
	                	if(password.length<6||password.length>16){return "密码长度在6-16位之间";}
	                }
	            }
		    },
		    {
			    field: 'power',
			    title: '权限',
			    align: 'center',
			    valign: 'middle',
			    editable: {
	                type: 'select',
	                title: '权限',
	                source:[{value:"超级管理员",text:"超级管理员"},{value:"一般管理员",text:"一般管理员"}]
	            }
		    }
		    ],
		    
		    onEditableSave: function (field, row, oldValue, $el) { 
	            updateUser(row);
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
			url:'listAllUser.do',
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
			    field: 'userName',
			    title: '用户名',
			    align: 'center',
			    valign: 'middle',
		    },
		    {
			    field: 'sex',
			    title: '性别',
			    align: 'center',
			    valign: 'middle',
		    },
		    {
			    field: 'age',
			    title: '年龄',
			    align: 'center',
			    valign: 'middle',
		    },
		    {
			    field: 'phone',
			    title: '电话',
			    align: 'center',
			    valign: 'middle',
		    },
		    {
			    field: 'address',
			    title: '住址',
			    align: 'center',
			    valign: 'middle',
		    },
		    {
			    field: 'password',
			    title: '密码',
			    align: 'center',
			    valign: 'middle',
		    },
		    {
			    field: 'power',
			    title: '权限',
			    align: 'center',
			    valign: 'middle',
		    }
		    ],
	    });
	}else{
		$add.hide();
		$remove.hide();
		alert("您不是管理员");
		return;
	}
	
	function updateUser(param){
		$.ajax({
			url: 'updateUser.do',
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
		var userName,passsword,power,sex,age,phone,address;
        userName= $("#name").val();
        password= $("#password").val();
        sex= $("#sex").val();
        power=$("#power").val();
	    age= $("#age").val();
		phone= $("#phone").val();
		address= $("#address").val();
		if(userName.length<5||userName.length>15){alert("用户名长度在5-15位之间");return 0;}
		if(password.length<6||password.length>16){alert("密码长度在6-16位之间");return 0;}
		var param= {
	            userName: userName,
	            password: password,
	            power : power,
	            sex: sex,
			    age: age,
				phone: phone,
				address: address};
	    $.ajax({
			url: 'addUser.do',
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
				$("#myModal").modal('hide');
				//$('.modal-backdrop').remove();//去掉遮罩层
				$table.bootstrapTable('refresh');
			}
		});
	});
		
	
	//删除功能
	$remove.click(function (){
		var con = confirm("是否删除?");
		if(!con){
			return;
		}
		var length=$table.bootstrapTable('getSelections').length;//获取选中的行数
		if(length==0){return;}
		var i,id;
		var ids=[];
		for(i=0;i<length;i++){//遍历选中行
			id=$table.bootstrapTable('getSelections')[i].id;
			ids[i]=id;
		}	
		$.ajax({
			url: 'deleteUsers.do',
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
	});
	
});



