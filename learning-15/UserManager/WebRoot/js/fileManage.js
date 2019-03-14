$(function($) {
	
	 $("#uploadFile").on("fileloaded", function (event, data, previewId, index) {
		 console.log(data);
	 });
	 
	 $("#uploadFile").on("fileuploaded", function (event, data, previewId, index) {
		 console.log("上传成功");
	 });
	 
	 $('#uploadFile').on('fileerror', function(event, data, msg) {
		 console.log("上传失败");
	 });
	 
	
});

