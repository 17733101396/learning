<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<script src="js/userManage.js?version=51"></script>
<title>用户信息</title>
</head>

<body>
	<div class="panel pannel-default" style="background-color:transparent;" >
		<div id="toolbar">
    <button id="add" type="button" class="glyphicon glyphicon-ok" data-toggle
	="modal" data-target="#myModal"  style="color: rgb(167, 135, 134);font-size: 20px;">添加
	</button>
	<button id="remove" type="button" class="glyphicon glyphicon-remove" style="color: rgb(255, 0, 0);font-size: 20px;margin-left:10px">删除
	</button>
  </div>
		<table id="usertab" data-toolbar="#toolbar" data-show-refresh="true"
			data-show-toggle="true" data-show-columns="true" data-search=“true”
			data-id-field="id" >
		</table>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">用户信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-2">
							<lable for="name">用户名：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name"
								placeholder="请输入用户名" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="password">密码：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="password"
								placeholder="请输入密码" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="power">权限：</lable>
						</div>
						<div class="col-sm-10">
							<select class="form-control" id="power">
								<option>超级管理员</option>
								<option>一般管理员</option>
							</select>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="sex">性别：</lable>
						</div>
						<div class="col-sm-10">
							<select class="form-control" id="sex">
								<option>男</option>
								<option>女</option>
							</select>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="age">年龄：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="age"
								placeholder="请输入年龄" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="phone">电话：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="phone"
								placeholder="请输入电话" />
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2">
							<lable for="address">住址：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="address"
								placeholder="请输入住址" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"  id="submit">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>
