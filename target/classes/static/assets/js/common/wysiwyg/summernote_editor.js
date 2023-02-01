$(()=> {	
	/*
	** CSS **
	<link rel="stylesheet" href="/lib/summernote/summernote-bs4.css"></link>
	** JS **
	<script src="/lib/summernote/summernote-bs4.js"></script>
	<script src="/lib/summernote/summernote-ko-KR.js"></script>
	<script src="/assets/js/common/wysiwyg/summernote_editor.js"></script>
	** HTML **
	<textarea id="summernote"></textarea>
	or
	<div id="summernote"></div>
	*/
	
	//섬머노트
	$('#summernote').summernote({
		minHeight: 600,
        lang:"ko-KR",
        focus: false,
        fontNames: ['NanumSquareOTF'],
        fontNamesIgnoreCheck: ['NanumSquareOTF'],
        toolbar: [
          ['style', ['style']],
          ['fontname', ['fontname']],
          ['fontsize', ['fontsize']],
          ['font', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph', 'height']],
          ['table', ['table']],
          ['insert', ['link', 'picture', 'video']],
          ['view', ['undo', 'redo', 'fullscreen', 'codeview', 'help']]
        ],
    	callbacks: {	
			onImageUpload : files => {
				uploadImage(files[0]);
			},
			onPaste: event => {
				var clipboardData = event.originalEvent.clipboardData;
				if (clipboardData && clipboardData.items && clipboardData.items.length) {
					var item = clipboardData.items[0];
					if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
						event.preventDefault();
					}
				}
			}
		}
	});
	
	//이미지 업로드 ajax
	var uploadImage = file => {
		data = new FormData();
		data.append("uploadImage", file);
		$.ajax({
			data : data,
			type : "POST",
			url : "/service/ajax/image/upload",
			contentType : false,
			processData : false,
			success : data => {
				$("#summernote").summernote('insertImage', "/image_storage/"+data.filePath+"/"+data.fileName);
			}
		});
	}
	
	//드래그 앤 드랍 처리
	$("div.note-editable").on('drop' , event => {
         for(i=0; i< event.originalEvent.dataTransfer.files.length; i++){
         	uploadImage(event.originalEvent.dataTransfer.files[i] , $("#summernote")[0]);
         }
        event.preventDefault();
   });
   
});