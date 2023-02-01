$(function () {
	//submit validation
	$("form[name='loginInfo']").on("submit", function (event) {
		const pwd = $("input[name='pwd']"); 
		const pwdFormat = /^\d{4}$/;
		if (pwd.val() == "") {
			pwd.css("border-color","#ff5d4f");
			$(".errorText").html("휴대폰 번호의 뒤4자리를 입력해주세요.");
			event.preventDefault();
		} else if (!pwdFormat.test(pwd.val())) {
			pwd.css("border-color","#ff5d4f");
			$(".errorText").html("잘못된 번호입니다.");
			event.preventDefault();
		}
	});
	
	if (location.pathname == "/service/estimate/doc") {
		var sub = 0;
		//발행일 처리
		$('.regDate').html(estimateDate($('.regDate').html()));
		
		//고객명
		var customerName = personalInfo($('.customerName').html(),"name");
		$('.customerName').html(customerName);
		$('.customerName.top').html(customerName + "님의 견적가");
		
		//핸드폰번호
		$('.customerPhone').html(personalInfo($('.customerPhone').html(),"phone"));
		
		//부자재 가격
		$("dc").each( function (index, dc) {
			sub += parseInt($(dc).text());
			$(dc).remove();
		});
		$(".subPrice").html(sub.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원");
	}
	
	$(document).on('click', '.estimateLogin', function () {
		$("form[name='loginInfo']").submit();
	});
	
});

//개인정보
function personalInfo (info,flag) {
	switch(flag) {
		case "name" : 
			if (info.length > 2) {
				return info.substr(0,1) + "O".repeat(info.length-2)+info.substr(info.length-1);
			} else {
				return info.substr(0,1) + "O";
			}			
			break;
		case "phone" : 
			var splitPhone = info.split("-");
			return splitPhone[0] + "-" + "X".repeat(splitPhone[1].length) + "-" + splitPhone[2];
			break;
	}
}

//발행일 처리 함수
function estimateDate (regDate) {
	var splitDate = regDate.split("-");
	var estimateYear = splitDate[0].substr(2,2);
	var estimateDay = splitDate[2].split("T");
	return "발행일 : "+estimateYear+"."+splitDate[1]+"."+estimateDay[0];
		
}

//collapse 아이콘, padding 처리
function togChange (parent) {
	var child = $(parent).find('img');
	var appDetail = $('.appDetail__estimateDoc');
	if (child.css('transform') == 'none') {
		child.css('transform','rotate(180deg)');
		appDetail.css('padding-bottom','24px');
	} else {
		child.css('transform','');
		appDetail.css('padding-bottom','');
	}
}