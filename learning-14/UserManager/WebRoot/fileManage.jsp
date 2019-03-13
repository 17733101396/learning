<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/fileinput.min.css">
<!--bootstrap核心CSS文件-->
<link rel="stylesheet" href="css/manage.css?version=201903"
	type="text/css">

<meta charset="utf-8" />

<script src="js/format.js"></script>
<script src="js/polyfill.min.js"></script>
<!--jquery文件，务必在bootstrap.min.js之前引入-->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js?version=201903010"></script>
<script src="js/fileinput.js?version=201903010"></script>
<script src="js/fileinput.min.js?version=201903010"></script>
<script src="js/sortable.min.js?version=201903010"></script>
<script src="js/piexif.js"></script>
<script src="js/piexif.min.js"></script>
<script src="js/purify.min.js"></script>
<script src="js/zh.js?version=201903010"></script>
<title></title>
</head>

<body>
	<form enctype="multipart/form-data">
		<div class="file-loading">
			<input id="uploadFile" name="uploadFile" type="file" class="file"
				data-browse-on-zone-click="true" multiple>
		</div>
	</form>	
</body>
<script src="js/fileManage.js"></script>
</html>
