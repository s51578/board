$(function(){
	$("#qnaTable").DataTable({
		// 검색시 데이터 없을경우 빈 항목 추가
		drawCallback: function() {
			var api = this.api();
			var rowCount = api.rows({page: 'current'}).count();
			
			for(var i = 0; i< api.page.len() - (rowCount === 0 ? 1 : rowCount); i++) {
				$("#tbodys").append($("<tr><td>&nbsp;</td><td></td><td></td><td></td></tr>")); // td 개수 맞추어서 수정
			}
		},
		/*dom default : lfrtip
			l : 항목 길이 컨트롤러
			f : 검색
			t : 테이블
			i : 테이블 정보
			p : 페이징 컨트롤러
			r : 요소
		*/
		"dom": '<"top"l>rt<"col-sm-12 d-flex justify-content-center col-md-6 mx-auto p-0 bottom"p><"col-sm-12 col-md-6 mx-auto p-0 bottom"f><"clear">',
		// 스크롤 활성화 X : 가로 Y : 세로
		//scrollX: "100%",
		//scrollXInner: "1100px",
		//scrollY: 500,
		//"scrollCollapse": false,
		// 표시 건수기능 숨기기
		lengthChange: false,
		// 검색 기능 숨기기
		searching: true,
		// 정렬 기능 숨기기
		ordering: false,
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
		//페이징 갯수
		
		//언어 변경
		"language": {
			// 데이터가 없을 때
			"zeroRecords": "게시글이 존재하지 않습니다.",
			"search": "",
			// 길이 메뉴 커스텀
			"lengthMenu":  '<select>'+
						    '<option value="10">10줄 보기</option>'+
						    '<option value="25">25줄 보기</option>'+
						    '<option value="50">50줄 보기</option>'+
						    '<option value="-1">전체 보기</option>'+
						    '</select>',
		"paginate" : {
			// 페이징네비 표기 변경시 css padding 조절 필요
            "first" : "첫 페이지",
            "last" : "마지막 페이지",
            "previous" : "<",
            "next" : ">"
       	 	}
		},
		
		// 여기서 크기 지정시 모바일 환경에서 grid가 미적용 하므로 th에 inline으로 
		/*"columns": [
			{ "width": "10%" },
			{ "width": "15%" },
			{ "width": "45%" },
			{ "width": "20%" },
			{ "width": "10%" },
		]*/

	});
	
});