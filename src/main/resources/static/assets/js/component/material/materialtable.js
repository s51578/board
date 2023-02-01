$(function(){
	
	const mtTable = $("#mtTable").DataTable({
		// f : 검색
		// 스크롤 활성화 X : 가로 Y : 세로
		responsive: true,
		scrollX: true,
		//'<"#control_menu.row m-0 pt-2 pb-2 mt-3 mb-3 d-flex align-items-center justify-content-center"f>' +
		dom: 
			'<"row" <"#dt_top_left .col-6 d-flex justify-content-start"i>' +
			'<"#dt_top_right.col-6 d-flex justify-content-end align-items-end"l>>' +
			't<"col-12 p-5 d-flex justify-content-center"p>',
		scrollY: false,
		// 표시 건수기능 숨기기
		lengthChange: true,
		// 검색 기능 숨기기
		//searching: true,
		// 정렬 기능 숨기기
		//ordering: true,
		// 정보 표시 숨기기
		info: true,
		// 페이징 기능 숨기기
		paging: true,
		// 정렬순서
		order: [[0, "DESC"]], // 0번째 기준으로 내림차순
		// 페이지 새로고침시 페이징 유지
		stateSave: true,
		//페이징 타입
		"pagingType": "full_numbers",
		"language": {
			// 데이터가 없을 때
			"zeroRecords": "상품이 존재하지 않습니다.",
			"search": "검색",
			"lengthMenu":  '<select>'+
						    '<option value="10">10개 보기</option>'+
						    '<option value="30">30개 보기</option>'+
						    '<option value="50">50개 보기</option>'+
						    '<option value="100">100개 보기</option>'+
						    '<option value="-1">전체 보기</option>'+
						    '</select>',
			"info": "총 _TOTAL_ 개",
			"infoEmpty": "총 0개",
			"infoFiltered": "",
			"searchPlaceholder": "상품명/분류코드 입력",
		"paginate" : {
            "first" : "<<",
            "last" : ">>",
            "next" : ">",
            "previous" : "<"
       	 	}
		},
		
		/*"columnDefs": [
			{"className": "dt-body-right", "targets": -1}
		]*/
		
		/*"columns": [
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
			{ "width": "130px" },
		]*/
		
		
		// 수동 검색
		/*initComplete : function() {
	        var input = $('.dataTables_filter input').unbind(),
	            self = this.api(),
	            $searchButton = $('<button class="btn btn-secondary ml-2 mr-1">')
	                       .text('검색')
	                       .click(function() {
	                          self.search(input.val()).draw();
	                       }),
	            $clearButton = $('<button class="btn btn-secondary ml-2 mr-5">')
	                       .text('삭제')
	                       .click(function() {
	                          input.val('');
	                          $searchButton.click(); 
	                       }) 
	        $('.dataTables_filter').append($searchButton, $clearButton);
    	}*/

	});
	
	
	initDataTable();
	
	// DOM 조작
	$("div#dt_top_right")
			.prepend(
					'<a class="btn btn-primary mr-3" href="/master/material/create">상품등록</a>');

	/*$('#table_reset_btn').on('click', function() {
		initDataTable();
	});*/

	$(".searchElement").on("change", function() {

		var search_sts = '';

		$(".searchElement").each(function(i, e) {

			var val = $(e).val();
			search_sts += " " + val;

		});

		mtTable.search(search_sts).draw();
	})

	function initDataTable() {
		mtTable.search('').columns().search('').draw();
		$("#searchInput").val('');
	}
	
});