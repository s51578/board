$(function(){
	 $("#contents_Table").DataTable({
		// 스크롤 활성화 X : 가로 Y : 세로
		//scrollX: false
		"dom": '<"top"f>rt<"col-sm-12 d-flex justify-content-center col-md-6 mx-auto p-0 bottom"p><"col-sm-12 col-md-6 mx-auto p-0 bottom"><"clear">',
		scrollY: false,
		// 표시 건수기능 숨기기
		lengthChange: true,
		// 검색 기능 숨기기
		searching: true,
		// 정렬 기능 숨기기
		ordering: true,
		// 정보 표시 숨기기
		info: false,
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
			"zeroRecords": "게시글이 존재하지 않습니다.",
			"search": "검색 : ",
			"lengthMenu":  '<select>'+
						    '<option value="10">10줄 보기</option>'+
						    '<option value="25">25줄 보기</option>'+
						    '<option value="50">50줄 보기</option>'+
						    '<option value="-1">전체 보기</option>'+
						    '</select>',
		"paginate" : {
            "first" : "<<",
            "last" : ">>",
            "next" : ">",
            "previous" : "<"
       	 	}
		},
		
		"columns": [
			{ "width": "200px" },
			{ "width": "200px" },
			{ "width": "200px" },
			{ "width": "200px" },
			{ "width": "200px" },
			{ "width": "200px" },
			{ "width": "300px" },
		]
	});
	
});