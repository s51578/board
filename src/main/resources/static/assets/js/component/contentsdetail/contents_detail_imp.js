// 계산기에 적용할 값
let contentsTileCost = tileCost;
let m2Box = meter2Box;

// 3자리수마다 , 찍어주는 함수
function thousands_separators(num)
  {
    var num_parts = num.toString().split(".");
    num_parts[0] = num_parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return num_parts.join(".");
  }

//URL 암호화 및 복사
let url = location.href;
function getEncodeUrl(urlstring) {
	
	var textarea = document.createElement('textarea');
	textarea.value = urlstring;
	document.body.appendChild(textarea);
	textarea.select();
	textarea.setSelectionRange(0, 9999); // For IOS
	document.execCommand('copy');
	document.body.removeChild(textarea);
	
	var formData = new FormData();
	
	formData.append("urlstring", urlstring);
	
	$.ajax({
		url : "/view/get/encode",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			console.log(data);

			var textarea = document.createElement('textarea');
			textarea.value = location.protocol+"//"+location.host+"/view/"+data;
			document.body.appendChild(textarea);
			textarea.select();
			textarea.setSelectionRange(0, 9999); // For IOS
			document.execCommand('copy');
			document.body.removeChild(textarea);
			
			alert("복사되었습니다");
		},
		error : function(data, textStatus, xhr) {
	
			if(data.status > 400)
				alert("관리자에게 문의하십시오." + textStatus);
	
		}
	});
}

//아이콘 뒤집기
function togChange(parent) {
	var child = $(parent).find('img');
	if (child.css('transform') == 'none') {
		child.css('transform','rotate(180deg)');
	} else {
		child.css('transform','');
	}
}

//요소 보임 체크
function Utils() {

}

Utils.prototype = {
    constructor: Utils,
    isElementInView: function (element, fullyInView) {
        var pageTop = $(window).scrollTop()+80;
        var pageBottom = pageTop + $(window).height();
        var elementTop = $(element).offset().top;
        var elementBottom = elementTop + $(element).height();

        if (fullyInView === true) {
            return ((pageTop < elementTop) && (pageBottom > elementBottom));
        } else {
            return ((elementTop <= pageBottom) && (elementBottom >= pageTop));
        }
    }
};

var Utils = new Utils();
var offset_value = '80';

$(document).ready(function(){
	
	$('[data-toggle="tooltip"]').tooltip();
	
	//주소복사
	$(".share_btn").on("click", function(){
		getEncodeUrl(url);
	});
	
	//네비게이션바 클릭 이동
	$('ul.tabs li').on('click',function () {
		$('ul.tabs li').removeClass('active');
		$(this).addClass('active');
		var offset = '';
		switch ($(this).text()) {
			case "상품설명" :
				offset ="#product";
			break;
			case "시공안내" :
				offset ="#cons";
			break;
			case "정책안내" :
				offset ="#policy";
			break;
			case "추천상품" :
				offset ="#mdPick";
			break;	
		}
		offset = $(offset).offset();
		$('html').scrollTop(offset.top - offset_value - 73);
	});
	
	//윈도우 스크롤 이벤트
	$(window).on("resize scroll",function () {
		$('.scroll').each(function () {
			if($(this).offset().top - 200 < $(window).scrollTop()) {
			  var name = $(this).attr('id');
		      $('ul.tabs li').removeClass('active');
		      $('li[name="'+name+'"]').addClass('active');
		    }
		});
		var offset = $('#pc_header').css("display");
		var offset_val = '';
		if (offset == "flex") {
			offset_val = '80px';
			offset_value = '80';
		} else {
			offset_val = '56px';
			offset_value = '56';
		}
		var isElementInView = Utils.isElementInView($('._title_img'), false);
		if (isElementInView) {
		    $('.navBar_box').removeClass("navReact").css("top","");
		} else {
			$('.navBar_box').addClass("navReact").css("top",offset_val);
		}
	});
});
