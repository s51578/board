
function getUrl(){
	
	var url = "/dropzone/image/upload/";
	
	return url;
}

function getTagTemplate(materail_count){
	
	var icon = "";
	
	if(materail_count!=0) {
		icon = "<span class='fa fa-cube text-warning mr-1'></span>";
		//icon = "<span class='badge badge-warning mr-1'>"+materail_count+"</span>";
	}else {
		icon = "<span class='pr-1'>#</span>";
	}
	
	return icon;
}

$(document).ready(function () {
	 	
		var previewNode = document.querySelector("#template");
		previewNode.id = "";
		var previewTemplate = previewNode.parentNode.innerHTML;
		previewNode.parentNode.removeChild(previewNode);
		
		Dropzone.autoDiscover = false;
		
		var myDropzone = $("div#fileDropzone").dropzone({
			
			url : getUrl(), // 드롭다운 시 업로드 되는 URL
			method : "POST",
			maxFilesize : 10, // 드롭다운 시 파일 크기
			//maxFiles : 5, //최대 허용 파일 개수
			parallelUploads : 1,
			paramName : "uploadImage",
			//acceptedFiles: ".jpg",
			/*addRemoveLinks : true,
			dictRemoveFile : "delete",*/
			thumbnailWidth : null,
			thumbnailHeight : null,
			previewTemplate : previewTemplate,
			previewsContainer : "#edit_window",
			sending : function(files, xhr, data) {
				data.append("uuid", files.upload.uuid);
			},
			/*sendingmultiple : function(files, xhr, data) {
				
				var filesInfo = new Array();
				
				for (var index = 0; index < files.length; index++) {
					var file = new Object();
					file["no"] = files[index].no;
					file["name"] = files[index].name;
					file["type"] = files[index].type;
					file["tags"] =  JSON.stringify(files[index].upload.tags);
					filesInfo.push(file);
				}
				
				var type= $("#type").val();
				var where= $("#where").val();
				var title= $("#collection_title").val();
				var content = $("#collection_content").val();
				
				data.append("filesInfo", JSON.stringify(filesInfo));
                data.append("type", type);
                data.append("where", where);
                data.append("title", title);
                data.append("content", content);
                
            },*/
			//TODO : 에러 시 이미지 업로드 할 수 없다는 표시
			error : function(file, errormessage, xhr) {

				alert(xhr.responseText);

				this.removeFile(file);
			},
			success : function(file) {
				var aaa  = file;
			},
			addedfile : function(file) {
				
				var dropzone = this;
				
				file.previewElement = Dropzone.createElement(dropzone.options.previewTemplate.trim());
				//var count = dropzone.getFilesWithStatus(Dropzone.SUCCESS).length;
				//var count = dropzone.getQueuedFiles().length;
				var count = dropzone.files.indexOf(file);
				var all = dropzone.files.length;
				
				if(all>5)
					return false;
				
				//list-view / main-view 추가
				editList(file, count);
				
				//드랍존 축소
				simpleDropzone(dropzone.element);
				
				file["main"] = false;
				
				//tag 리스트 공간 확보
				var array = new Array();
				
				if(typeof file.upload.tags == 'undefined')
					file.upload["tags"] = array;
				
				file.previewElement.addEventListener("click", function(e) {
					
					//1. 리스트뷰 선택 -> 메인 뷰
					if($(e.target).parents().hasClass("list-preview"))
					{
						var th_files = dropzone.files;
						
						editList(file, 0);
						var index = 1;
						for (var i = 0; i < th_files.length; i++) {
							
							var th_file = th_files[i];
							
							if(file!=th_file) {
								editList(th_file, index);
								index++;
							}
						}

						//****진짜 삭제 후 리스팅 방법
						/*dropzone.removeAllFiles();
						dropzone.addFile(file);
						
						for (var i = 0; i < th_files.length; i++) {
							
							var th_file = th_files[i];
							if(file!=th_file)
								//dropzone.emit("addedfile", th_file);
								dropzone.addFile(th_file);
						}*/
						
					}
					//2. 삭제 버튼
					else if($(e.target).hasClass("remove-btn")){
						
						//remove
						dropzone.removeFile(file);
						
						var th_files = dropzone.files;
						
						for (var i = 0; i < th_files.length; i++)
							editList(th_files[i], i);
						
					}//2-1. 대표 이미지 설정
					else if($(e.target).hasClass("main-image-btn")) {
						
						onMainImage(e.target);
						
						var files = Dropzone.forElement("div#fileDropzone").files;
						
						for (var index = 0; index < files.length; index++) {
							files[index].main = false;
						}
						
						file.main = true;
						
					}
					//3. 새로고침
					else if($(e.target).hasClass("refresh-btn")){
						
						file.upload.tags.length = 0;
						$(e.target).parent().find("#tags-list").html("");
						//printTagArray(file);
					}
					//4. 제품 등록-태그
					else if($(e.target).hasClass("tag-material")){
						
						var tagForm = $(e.target).parents(".tag-form");
						var input = $(tagForm).find("input[type=text]");
						var value = $(e.target).text();
						var material_id = $(e.target).attr("no");
						var materail_count = $('.tag-material-add').length + 1;
						var icon = getTagTemplate(materail_count);
						var cube_icon = "<i class='fa fa-cube pr-1'></i>";
						
						var tag = new Object();
						tag["offsetY"] = $(tagForm).attr("offsetY");
						tag["offsetX"] = $(tagForm).attr("offsetX");
						tag["height"] = $(tagForm).attr("figureY");
						tag["width"] = $(tagForm).attr("figureX");
						tag["value"] = value;
						tag["materialId"] = material_id;
						tag["materialCount"] = 0;
						
						file.upload.tags.push(tag);
						
						var num = 1;
						for (var count = 0; count < file.upload.tags.length; count++) {
							
							if(file.upload.tags[count].materialId!=null) {
								file.upload.tags[count].materialCount = num;
								num++;
							}
						}
						
						$(input).replaceWith("<small class='m-2'>"+icon+value+"</small>");
						$(tagForm).find(".dropdown").remove();
						$(tagForm).find(".tag-success").remove();
						$(e.target).remove();
						
						$(tagForm).addClass('tag-material-add');
						//$(tagForm).toggleClass('bg-dark bg-warning');
						
					}
					//5. 태그 확정
					else if($(e.target).hasClass("tag-success")){
						
						var tagForm = $(e.target).parents(".tag-form");
						var input = $(tagForm).find("input[type=text]");
						var value = $(input).val();
						var icon = getTagTemplate(0);
						
						var tag = new Object();
						tag["offsetY"] = $(tagForm).attr("offsetY");
						tag["offsetX"] = $(tagForm).attr("offsetX");
						tag["height"] = $(tagForm).attr("figureY");
						tag["width"] = $(tagForm).attr("figureX");
						tag["value"] = value;
							
						file.upload.tags.push(tag);
						
						$(input).replaceWith("<small class='m-2'>"+icon+value+"</small>");
						$(tagForm).find(".dropdown").remove();
						$(e.target).remove();
						
						//printTagArray(file);
						
						/*var form = $(click_btn_e.target).parents(".tag-inner");
						var input = $(form).find("input[type=text]");
						var value = $(input).val();
						
						$(newTag).html("");
						$(newTag).attr("data-title", value);
						$(newTag).tooltip('show');*/
						
					}
					//6. 태그 삭제
					else if($(e.target).hasClass("tag-remove")){
						
						var tagForm = $(e.target).parents(".tag-form");
						var curr_offsetY = $(tagForm).attr("offsetY");
						var curr_offsetX = $(tagForm).attr("offsetX");
						 
						var tag_array = file.upload.tags;
						
						for (var index = 0; index < tag_array.length; index++) {
							var arr_y = tag_array[index].offsetY;
							var arr_x = tag_array[index].offsetX;
							
							if(arr_y==curr_offsetY && arr_x==curr_offsetX) {
								tag_array.splice(index, 1);
							}
						}

						var form = $(e.target).parents(".tag-form");
						$(form).remove();
						
						//printTagArray(file);
						
					}
					//7. 사진 태그
					else if($(e.target).parents().hasClass("main-preview") 
							&& !$(e.target).parents().hasClass("tag-form") ) {
						
						var figure_len_y = $(e.target).parents().height();
						var figure_len_x = $(e.target).parents().width();
						
						var per_y = (e.offsetY / figure_len_y) * 100;
						var per_x = (e.offsetX / figure_len_x) * 100;
						
						console.log( e.offsetY + " / " + e.offsetX);
						
						var tagsTemplate = $("#tooltip-template").html();
						$(e.target).find("#tags-list").append(tagsTemplate);
						var newTag = $(e.target).find("#tags-list").children().last();
						
						//$("#img-tooltip").css({top: e.offsetY, left: e.offsetX });
						$(newTag).css({position: "absolute"});
						$(newTag).css({top: per_y+"%", left: per_x+"%" });
						$(newTag).attr("offsetY", e.offsetY);
						$(newTag).attr("offsetX", e.offsetX);
						$(newTag).attr("figureY", figure_len_y);
						$(newTag).attr("figureX", figure_len_x);
						
						//$(newTag).tooltip('show');
						
						return;
					}
					
					//드랍존 축소
					simpleDropzone(dropzone.element);
					
					$('[data-toggle="tooltip"]').tooltip();
				});
				
				return true;
			}
	});
	
	function onMainImage(target){
		
		$(".main-image-btn").removeClass('fa-check-square fa-square-o');
		$(".main-image-btn").addClass('fa-square-o');
		
		$(target).toggleClass('fa-square-o fa-check-square');
		
	}
		
	function simpleDropzone(element){

		var last = $("#edit_list").children().last();
		
		$(element).css("width", "150px;").css("height", "150px;");
		$(element).addClass("m-1");
		
		$(element).find("#formLayout").removeClass("p-5");
		$(element).find("#formLayout").css("padding", "32px");
		$(element).find("#formLayout .dz-message").css("height", "");
		
		var sicon = "&nbsp;<i class='fa fa-plus-circle fa-2x text-info'></i>&nbsp;";
		$(element).find(".dz-message").html(sicon);

		$(element).insertAfter(last);
		
	}
	
	function editList(file, count){
		
		//$(file.previewElement).removeClass("preview-*").addClass("preview-"+count);
		
		if(count==0) {
			$("#edit_list .list-preview").remove();
			
			$(file.previewElement).addClass("main-preview").removeClass("list-preview");
			$(file.previewElement).find(".overlay").children().show();//find("i").show(); 
			
		}else {
			$(file.previewElement).addClass("list-preview").removeClass("main-preview");
			$(file.previewElement).find(".overlay").children().hide();//find("i").hide();
		}
		
		$("#edit_list").append(file.previewElement);
		
		//태그 뿌리기
		
	}
	
	function printTagArray(file){
		
		var resultAll = "";
		for (var index = 0; index < file.upload.tags.length; index++) {
			var result = file.upload.tags[index];
			resultAll += index + " / ";
			resultAll += result.offsetY + " / ";
			resultAll += result.offsetX + " / ";
			resultAll += result.value + " / ";
			resultAll += "<br />";
		}
		
		$("#log").html(resultAll);
		
	}

	$("#catalog").on('change', function() {
		
		if (this.files && this.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#preview").attr("src", e.target.result);
			}
			reader.readAsDataURL(this.files[0]);
		}else
			$("#preview").attr("src", "");
		
	});

	$("#remove_catalog").click(function(){
		document.getElementById("catalog").value = "";
		$("#catalog").change();
	})
	
	document.onpaste = function(event) {
		
		var myDropzone = Dropzone.forElement("div#fileDropzone");
		
		var items = (event.clipboardData || event.originalEvent.clipboardData).items;
		for (index in items) {
			var item = items[index];
			if (item.kind === 'file') {
				// adds the file to your dropzone instance
				myDropzone.addFile(item.getAsFile())
			}
		}
	}
	

	$("form").submit(function() {
		
		var result = confirm('등록하시겠습니까?');
		
		if(result) {
			
			var filesInfo = new Array();
			var files = Dropzone.forElement("div#fileDropzone").files;
			var check = false;
			
			for (var index = 0; index < files.length; index++) {
				var file = new Object();
				file["no"] = files[index].no;
				file["name"] = files[index].name;
				file["type"] = files[index].type;
				file["uuid"] = files[index].upload.uuid;
				file["main"] = files[index].main;
				file["tags"] =  JSON.stringify(files[index].upload.tags);
				filesInfo.push(file);
				
				if(files[index].main)
					check = true;
				
			}
			
			if(filesInfo[0]==null) {
				alert("대표 이미지가 존재하지 않습니다");
				return false;
			}
				

			if(!check)
				filesInfo[0].main = true;
			
			$("#filesInfo").val(JSON.stringify(filesInfo));
			
			return true;
			
		} else {
			return false;
		}
	});
	
});

function getSubmitUrl(){
	var type = $("#type").val();
	var where = $("#where").val();
		
	var url = "";
	if(type=='business')
		url = "/service/company/collection/register";
	else if(type=='private' && where=='1')
		url = "/service/master/collection/register";
	else if(type=='private' && where=='2')
		url = "/service/master/collection/material/register";
	
	return url;
}

function yetsubmit() {
	
	var formData = new FormData();
	
	var filesInfo = new Array();
	var files = Dropzone.forElement("div#fileDropzone").files;
	
	for (var index = 0; index < files.length; index++) {
		var file = new Object();
		file["no"] = files[index].no;
		file["name"] = files[index].name;
		file["type"] = files[index].type;
		file["uuid"] = files[index].upload.uuid;
		file["main"] = files[index].main;
		file["tags"] =  JSON.stringify(files[index].upload.tags);
		filesInfo.push(file);
		
		if(files[index].main)
			$("#mainImageUuid").val(files[index].upload.uuid);
		
	}
	
	var mainUuid = $("#mainImageUuid").val();
	
	if(mainUuid==null || mainUuid=="")
		$("#mainImageUuid").val(files[0].upload.uuid);
	
	formData.append("filesInfo", JSON.stringify(filesInfo));
	
	var myform = $("#masterform").serializeArray();
	for (var i = 0; i < myform.length; i++)
		formData.append(myform[i].name, myform[i].value);
	
	formData.append("catalog", $("#catalog")[0].files[0]);
	
	$.ajax({
		url : getSubmitUrl(),
		type : "POST",
		cache : false,
		async : false,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			
			if(data.result=="200"){
				var collectionId = data.cid;
				var redirect = data.redirect;
				
				location.href = redirect +"?col=" + collectionId;
			} else {
				alert("로그인이 필요합니다.");
			}
			
		},
		error : function(data, textStatus, xhr) {

			if (data.status == 200)
				alert("로그인이 필요합니다.");
			else if (data.status == 503)
				alert("이미 등록되었습니다." + textStatus);
			else if (data.status == 400)
				alert("잘못된 요청입니다." + textStatus);
			else if (data.status == 500)
				alert("파일을 업로드하는데 실패했습니다." + textStatus);
			else
				alert("오류 : " + textStatus);
		}
	});
	
}
