// 커스텀 이미지 동적 적용
function chatChannel() {
	Kakao.Channel.chat({
		//channelPublicId: '_BeAxjs' // 카카오톡 채널 홈 URL에 명시된 id로 설정합니다.
		channelPublicId: '_YueLs'
	});
}
window.onload = function(){
        if (window.navigator.userAgent.match(/MSIE|Internet Explorer|Trident/i)){
            if (window.confirm("본 페이지는 IE에 최적화되어 있지 않습니다. Edge로 이동하시겠습니까?")){
                window.location = "microsoft-edge:" + window.location.href;
            }
        }
    };

$(function() {
	
	$("#scroll-top").on("click", function() {
		$("html,body").animate({ scrollTop: 0 }, 600);
	});

	$(window).scroll(function() {
		
		if ($(document).scrollTop() > 100) {
			$("#scroll-top").addClass("show");
		} else {
			$("#scroll-top").removeClass("show");
		}
		
	});

});

