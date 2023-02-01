//사파리 때매 let 쓰지말기 
//파라미터 처리
function goFilter(category,value,html="") { 
	var goUrl = location.pathname+"?";
	var splitUrl = location.search.substr(location.search.indexOf("?") + 1);
    var param = [];
    var newSelectedFilter = true;
    var sortOrder = "";
    var sortPrev = false;
    splitUrl = splitUrl.split("&");
    for (var i = 0; i < splitUrl.length; i++)	{
        var splitBucket = splitUrl[i].split("=");
        if (splitBucket[0] == category) {
			if (splitBucket[1] == value || splitBucket[1] == "") {
				newSelectedFilter = false;
				if (category == "sort") {
					sortPrev = true;	
				}
			} else {
				if (category == 'color') { //색상 다중 처리
					param.push([splitBucket[0],splitBucket[1]]);
				}
			}
		} else {
			if (splitBucket[0] == "type") {
				sortOrder = splitBucket[1];
			}
			if (value != "") {
				param.push([splitBucket[0],splitBucket[1]]);
			} else {
				if (splitBucket[0] != "search") {
					param.push([splitBucket[0],splitBucket[1]]);
				}
			}
		}
    }
    if (newSelectedFilter || category == "sort") {
		if (value != "") {
			param.push([category,value]);	
		}
	}
	for (var i = 0; i < param.length; i++)	{
		if (i == 0) {
			goUrl += param[i][0]+"="+param[i][1];
		} else {
			goUrl += "&"+param[i][0]+"="+param[i][1];
		}
    }
    switch (html) {
		case "낮은가격순" :
			goUrl = goUrl.replace('desc','asc');
		break;
		case "높은가격순" :
			goUrl = goUrl.replace('asc','desc');
		break;
		default :
			if (category == "sort" && sortPrev) {
				if (sortOrder == 'asc') {
					goUrl = goUrl.replace('asc','desc');	
				} else {
					goUrl = goUrl.replace('desc','asc');
				}
			} else {
				goUrl = goUrl.replace('desc','asc');	
			}
		break;
	}
    if (category == "reset") {
		location.href="/service/details/store/main";
	} else {
		location.href=goUrl;
	}
}

//선택한 필터 아이콘 생성 처리, 텍스트 강조 처리
function filterIconCreate () {
	var splitUrl = location.search.substr(location.search.indexOf("?") + 1);
	var filterIconHtml = "";
	var sortText = "";
	var sortSelected = "";
	var priceFlag = splitUrl.indexOf("asc");
	splitUrl = splitUrl.split("&");
    for (var i = 0; i < splitUrl.length; i++)	{
        var splitBucket = splitUrl[i].split("=");
        switch (splitBucket[0]) {
			case 'type' :
				break;
			case 'sort' :
				switch (splitBucket[1]) {
					case 'popular' :
						sortText = "인기순";
						break;
					case 'name' :
						sortText = "이름순";
						break;
					case 'newest' :
						sortText = "최신순";
						break;
					case 'price' :
						if (priceFlag != -1) {
							sortText = "낮은가격순";	
						} else {
							sortText = "높은가격순";	
						}						
						break;
				}
				break;
			case 'color' :
				$("a[data-category=color][data-value="+splitBucket[1]+"]").css("color","#ff5d4f").find('div:eq(1)').css("background-image","url(/assets/resources/icon/contents/main/icon_filter_check.svg)");
				filterIconHtml += '<li class="filterIcon" onclick="goFilter(\''+splitBucket[0]+'\',\''+splitBucket[1]+'\')"><span class="filterIconText">'+$("a[data-category=color][data-value="+splitBucket[1]+"]").text()+'</span><img src="/assets/resources/icon/contents/main/bt_filter_delete.svg"/></li>';
				break;
			case 'surface' :
				$("a[data-category=surface][data-value="+splitBucket[1]+"]").css("color","#ff5d4f").find('div:eq(1)').css("background-image","url(/assets/resources/icon/contents/main/bt_filter_surface_s.svg)");
				filterIconHtml += '<li class="filterIcon" onclick="goFilter(\''+splitBucket[0]+'\',\''+splitBucket[1]+'\')"><span class="filterIconText">'+$("a[data-category=surface][data-value="+splitBucket[1]+"]").text()+'</span><img src="/assets/resources/icon/contents/main/bt_filter_delete.svg"/></li>';
				break;
			case 'search' :
				$('.tileSearchKeyword').val(decodeURI(splitBucket[1]));
				break;
		}
    }
    var searchKeyword = $('.tileSearchKeyword').val();
    if (filterIconHtml != "" || searchKeyword != "") {
		filterIconHtml += '<li class="filterIconReset" onclick="goFilter(\'reset\',\'true\')"><img src="/assets/resources/icon/contents/main/bt_st_reset.svg"/><span class="filterIconResetText">초기화</span></li>';
		$('.filterIconBox , .lineBottom').css('margin-top','16px');
	}
    if (sortText.length >3) {
		$('.sortBtn').css("width","120px");
	}
	if ($.trim($('._product_list_box').html()) == "") {
		$('._product_list_box').html('<span class="noItemText">검색 결과가 없습니다.</span>');
		$('._product_list_box').css("height","300px").css("display","flex").css("align-items","center").css("justify-content","center");
	}
	$("a[data-category=sort]").each(function () {
		if ($(this).text() == sortText) {
			$(this).css("color","#ff5d4f");
		}
	});
	$('.filterIconArea').html(filterIconHtml);
	$('.sortText').html(sortText);
}

//아이폰 클릭 에러 대응 (안먹혀서 더 연구해야함)
function iosError () {
	$(".filterDrop").removeClass("open");
}


$(document).ready(function() {
		//필터 아이콘 처리 및 선택 필터 옵션 강조처리
		filterIconCreate();
		
		//필터버튼 클릭 -> 드롭다운 메뉴 오픈
		$('.filterBtn').on("click", function (event) {
			event.stopPropagation();
			const dropMenu = $(this).parent('.filterDrop');
			$('.filterDrop').not(dropMenu).removeClass("open");
			dropMenu.toggleClass("open");
		});
		
		//윈도우 다른부분 클릭했을때 드롭다운 메뉴 사라지기
		$(window).on('click',function() {
			$(".filterDrop").removeClass("open");
		});
		
		// 필터 드롭다운 메뉴 버튼 클릭 시 처리
		$('.filterA').on("click", function () {
			const category = this.dataset.category;
			const value = this.dataset.value;
			const html = $(this).html();
			goFilter(category,value,html);
		});
		
		//검색어
		$('form[name=tileSearch]').on('submit',function () {
			const category = "search";
			const value = $('.tileSearchKeyword').val();
			goFilter(category,value);
			return false;
		});
		
		$('[data-toggle="tooltip"]').tooltip();	
});