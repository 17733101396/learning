<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <script src="js/ADManage.js?version=11"></script>
<title>ADManage</title>
</head>
<body>
	<div class="panel pannel-default" style="background-color:transparent;">
		<div id="toolbar">
			<button id="add" type="button" class="glyphicon glyphicon-ok"
				data-toggle="modal" data-target="#myModal"
				style="color: rgb(167, 135, 134);font-size: 20px;">添加</button>
			<button id="remove" type="button" class="glyphicon glyphicon-remove"
				style="color: rgb(255, 0, 0);font-size: 20px;margin-left:10px">删除
			</button>
		</div>
		<table id="ADTable" data-toolbar="#toolbar" data-show-refresh="true"
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
					<h4 class="modal-title" id="myModalLabel">广告信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-2">
							<lable for="id">广告内容：</lable>
						</div>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="context"
								placeholder="请输入广告内容" />
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