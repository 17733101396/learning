<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>songManage</title>
<!-- 
    <script src="js/songManage.js?version=1"></script> 
-->
<script src="js/songManage.js?version=1"></script> 
</head>
<body>
	<div class="panel pannel-default">
		<div id="toolbar">
			<button id="add" type="button" class="glyphicon glyphicon-ok"
				data-toggle="modal" data-target="#myModal"
				style="color: rgb(167, 135, 134);font-size: 20px;">添加</button>
			<button id="remove" type="button" class="glyphicon glyphicon-remove"
				style="color: rgb(255, 0, 0);font-size: 20px;margin-left:10px">删除
			</button>
		</div>
		<table id="songTable" data-toolbar="#toolbar" data-show-refresh="true"
			data-show-toggle="true" data-show-columns="true" data-search=“true”
			data-id-field="id">
		</table>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">歌曲信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-2">
							<lable for="songName">歌曲名称：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="songName"
								placeholder="请输入歌曲名称" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="introduction">歌曲介绍：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="introduction"
								placeholder="请输入歌曲介绍" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="singer">歌手信息：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="singer"
								placeholder="请输入歌手名字" />
						</div>
					</div>
					<br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="submit">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>