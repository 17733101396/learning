$(function($){
	$("#submit").click(function(){
		var deviceID = $("#deviceID").val();
		$.ajax({
			url:'clientLogin',
			type: 'post',
			data:{
				deviceID:deviceID,
			},
			error: function (){
				alert("登录连接超时...");
				return false;
			},
			success: function (res){
				if(1==res){
					window.location.href="toClient";			
				}
				else{alert("...错误");}
			}
		});
	});
});