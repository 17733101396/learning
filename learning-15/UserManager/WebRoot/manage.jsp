<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'manage.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!--bootstrap核心CSS文件-->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">

<!--可选的bootstrap主题文件（一般不用引入）-->
<link rel="stylesheet" href="css/bootstrap-theme.min.css"
	type="text/css">
<link rel="stylesheet" href="css/bootstrap-table.min.css"
	type="text/css">

<link rel="stylesheet" href="css/bootstrap-editable.css" type="text/css">
<link rel="stylesheet" href="css/manage.css?version=201903" type="text/css">

<script src="js/format.js"></script>
<script src="js/polyfill.min.js"></script>
<!--jquery文件，务必在bootstrap.min.js之前引入-->
<script src="js/jquery.min.js"></script>

<!--bootstrap核心JavaScript文件-->
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-editable.min.js"></script>
<!--bootstrap table自动加载js文件-->
<script src="js/bootstrap-table.min.js"></script>

<!--bootstrap table 语言包-->
<script src="js/bootstrap-table-en-US.min.js"></script>
<script src="js/bootstrap-table-zh-CN.min.js"></script>

<script src="js/bootstrap-table-editable.js"></script>
<script src="js/moment.min.js?version=2"></script>
<script src="js/manage.js?version=2"></script>
</head>

<body style=" background-image: url(images/manage/back.png);height:568px;overflow:hidden;">
  <input id="paramPower" type="hidden" value= <%=session.getAttribute("paramPower") %> />
  <div id="manage-top" class="manage-top" style="font-size: 30pt;font-weight:bold;text-align: center;"><span class="glyphicon glyphicon-home" style="color: rgb(67, 113, 130); font-size: 30px;z-index:-1;"> 音乐管理系统</span></div>
  <div class="manage-contain" style="height:600px" >
      <div id="menu" class="menu" style="float:left;width:13%;margin-left:2%;height:600px">
      <button id="userManage" class="userManage"><span class="glyphicon glyphicon-user" style="color: rgb(67, 113, 255); font-size: 25px;"> 用户管理</span></button><br/>
      <button id="deviceManage" class="deviceManage" type="button" data-toggle="collapse" data-target="#demo" style="margin-top:20px;"><span class="glyphicon glyphicon-plus" style="color: rgb(67, 113, 255); font-size: 25px;"> 设备管理</span></button><br/>    
      <div id="demo" class="collapse">
          <button id="android" class="android" ><span class="glyphicon glyphicon-th-list" style="color: rgb(67, 113, 255); font-size: 22px;margin-top:20px;"> android端</span></button><br/>
          <button id="linux" class="linux"> <span class="glyphicon glyphicon-th-list" style="color: rgb(67, 113, 255); font-size: 22px;margin-top:20px;"> linux端</span></button><br/>
      </div>     
      <button id="songManage" class="songManage" style="margin-top:20px;"><span class="glyphicon glyphicon-music" style="color: rgb(67, 113, 255); font-size: 25px;"> 音乐管理</span></button><br/>
      <button id="ADManage" class="ADManage" style="margin-top:20px;"><span class="glyphicon glyphicon-envelope" style="color: rgb(67, 113, 255); font-size: 25px;"> 广告管理</span></button><br/>
      </div>
      <div id="rightPart" class="rightPart" style="float:right; width:83%; margin-left:2%;height:568px;overflow:hidden;">
          <div id="rightBody" class="rightBody" style="float:right; width:100%; margin-left:2%;height:568px;overflow:scroll;"></div>
      </div>
  </div>
</body>
</html>
