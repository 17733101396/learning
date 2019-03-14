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
<script src="js/client.js?version=5"></script>
</head>

<body>
<input type="hidden" id="deviceID" value=<%=session.getAttribute("deviceID")%> />
用户: <%=session.getAttribute("deviceID")%>

<div class="panel pannel-default">
		<div id="toolbar">
			<button id="remove" type="button">取消收藏</button>
		</div>
		<table id="songCollectionTable" data-toolbar="#toolbar" data-show-refresh="true"
			data-show-toggle="true" data-show-columns="true" data-search=“true”
			data-id-field="id">
		</table>
	</div>

<table id="songTable" data-show-refresh="true"
			data-show-toggle="true" data-show-columns="true" data-search=“true”
			data-id-field="id">
</table>

<table id="ADTable"  data-show-refresh="true"
			data-show-toggle="true" data-show-columns="true" data-search=“true”
			data-id-field="id">
</table> 
  
</body>
</html>
