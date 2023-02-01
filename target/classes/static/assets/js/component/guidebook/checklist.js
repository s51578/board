$(function () {
	
		$('[data-toggle="tooltip"]').tooltip();
		
		// 가이드북 읽기 호버 처리
		$("._img_checklist_Survey_guide_read").hover(function(){
		    $(".icon_book").attr("src", "/assets/resources/icon/contents/checklist/icon_guide_book_grey.svg");
		    }, function(){
		    $(".icon_book").attr("src", "/assets/resources/icon/contents/checklist/icon_book_n.svg");
		  });

		// 체크리스트 체크박스 범위 변경(div 클릭해도 활성화)
		$('._checklist_category_content_Box').on('click', function(){
			
   			var checkbox = $(this).children('input[type="checkbox"]');
   			checkbox.prop('checked', !checkbox.prop('checked'));
		});
		
});