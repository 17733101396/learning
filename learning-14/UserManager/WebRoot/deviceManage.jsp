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
<meta charset="utf-8" />

<!-- 
    <script src="js/deviceManage.js?version=20190309"></script>
 -->
 <script src="js/deviceManage.js?version=201903010"></script>
<title>设备信息</title>
</head>

<body>
<input id="paramDeviceType" type="hidden" value="${param.paramDeviceType}">
<div class="panel pannel-default">
  <div id="toolbar">
    <button id="add" type="button" class="glyphicon glyphicon-ok" data-toggle
	="modal" data-target="#myModal"  style="color: rgb(167, 135, 134);font-size: 20px;">添加
	</button>
	<button id="remove" type="button" class="glyphicon glyphicon-remove" style="color: rgb(255, 0, 0);font-size: 20px;margin-left:10px">删除
	</button>
  </div>
  <table id="deviceTable" 
        data-toolbar="#toolbar" 
        data-show-refresh="true" 
		data-show-toggle="true"  
		data-show-columns="true" 
		data-search="true"
		data-id-field="id">    
  </table>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">设备信息</h4>
            </div>
            <div class="modal-body">
			    <div class="row">
			      <div class="col-sm-2"><lable for="deviceID">设备ID：</lable></div>
			      <div class="col-sm-10"><input type="text" class="form-control" id="deviceID" placeholder="请输入设备ID"/></div>   
			    </div><br>
			    <div class="row">
			      <div class="col-sm-2"><lable for="deviceName">设备名：</lable></div>
			      <div class="col-sm-10"><input type="text" class="form-control" id="deviceName" placeholder="请输入设备名"/></div>   
			    </div><br> 
			    <div class="row" style="height:125px;overflow:scroll">
			      <div class="col-sm-2"><lable for="adList">广告列表：</lable></div>
			      <div class="col-sm-10" id="adList"></div>   
			    </div><br> 
			    <div class="row" style="height:125px;overflow:scroll">
			      <div class="col-sm-2"><lable for="songList">歌曲列表：</lable></div>
			      <div class="col-sm-10" id="songList"></div>   
			    </div><br>
			</div>
            <div class="modal-footer">
			    <button type="button" class="btn btn-primary" id="submit" >确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
