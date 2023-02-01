
function getUrl(){
	
	var url = "/dropzone/image/upload/";
	
	return url;
}

$(document).ready(function () {

	$(document).on('change', '._reg_image', function(e){
		var element = e.target;
		var file = element.files[0];
		var iArea = $(element).closest("tr").find("._reg_image_preview");
		var paramInput = $(element).closest("tr").find("._reg_image_param");
		
		$(element).next('.custom-file-label').html(file.name);
		$(iArea).attr("src", URL.createObjectURL(file));
		
		upload(file, iArea, paramInput);
	});
	
});

function upload(file, area, paramArea) {
	
	var formData = new FormData();
	
	formData.append("uploadImage", file);
	
	$.ajax({
		url : "/service/ajax/image/upload",
		type : "POST",
		cache : false,
		async : false,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			
			var initpath= "/image_storage"
			var imageNo = data.no;
			var imageThPath = data.thumbnailPath;
			var imageThName = data.thumbnailName;
			
			var imagePath = initpath + "/" + imageThPath + "/" + imageThName;
			
			$(area).attr("src", imagePath);
			$(paramArea).val(imageNo);
			
		},
		error : function(data, textStatus, xhr) {

			if (data.status == 200)
				alert("로그인이 필요합니다.");
			else if (data.status == 503)
				alert("이미 등록되었습니다." + textStatus);
			else if (data.status > 400)
				alert("잘못된 요청입니다." + textStatus);
			else
				alert("관리자에게 문의하십시오." + textStatus);
		}
	});
	
}