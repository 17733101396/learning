$(function($) {
	var username = $("#paramUsername").val();
	var password = $("#paramPassword").val();
	var paramPower = $("#paramPower").val();
	$("#userManage").click(function(){$("#rightBody").load("userManage.jsp");});
	$("#android").click(function() {
		$("#rightBody").load("deviceManage.jsp?paramDeviceType=0");
	});
	$("#linux").click(function() {
		$("#rightBody").load("deviceManage.jsp?paramDeviceType=1");
	});
	$("#songManage").click(function() {
		$("#rightBody").load("songManage.jsp");
	});
	$("#ADManage").click(function() {
		$("#rightBody").load("ADManage.jsp");
	});
});