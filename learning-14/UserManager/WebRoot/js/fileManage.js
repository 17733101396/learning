$(function($) {
	
	$('#uploadFile').fileinput({
		language:'zh', 
	    uploadUrl: 'uploadFile.do',
	});
	 $("#uploadFile").on("fileloaded", function (event, data, previewId, index) {
		 console.log(data);
	 });
	 
	 $("#uploadFile").on("fileuploaded", function (event, data, previewId, index) {
		 console.log(data);
	 });
	 
	 $('#uploadFile').on('fileerror', function(event, data, msg) {
		 console.log("上传失败");
	 });

});
