$(function($) {
	$("#submit").on("click",function(e){
		var username = $('#username').val();
		var password = $('#password').val();

		//下面为ajax调用
		if(username.length<5){alert("用户名长度在5-15位之间");return 0;}
		if(password.length<6){alert("密码长度在6-16位之间");return 0;}
		$.ajax({
			url: 'login.do',
			type: 'post',
			timeout: 10000,
			data: {
				userName : username,
				password : password
			},
			
			error: function (){
				alert("登录连接超时...");
				return false;
			},
			success: function (res){
				if(1==res||2==res){
					var params = {paramUserName:username,paramPassword:password,paramPower:res};
					new submitform("toManage.do","post",params);
					}//验证通过，跳到用户管理页面
				else{alert("用户名或密码错误");}
			}
		});
		
		
	});
	
	
});