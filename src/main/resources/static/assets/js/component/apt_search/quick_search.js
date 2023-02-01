$(document).ready(function() {

	$("#searchInput").on("keyup", function(key) {
		if (key.keyCode == 13) {
			const selectedRegionRcode = $("#selectedRegionRcode").val();
			const str = $("#searchInput").val();
	
			if (selectedRegionRcode == "" || typeof selectedRegionRcode == 'undefined' || selectedRegionRcode == null) {
				/*
				 * alert("지역코드가 없습니다.");
				 * form.keyword.focus();
				 */
				return false;
			}
	
			if (str == "" || typeof str == 'undefined' || str == null || str.length < 2) {
				/*
				 * alert("검색어를 입력해주세요.");
				 * form.keyword.focus();
				 */
				return false;
			}
	
			getAptName(selectedRegionRcode, str);
		}
	});
	
	/*$("#searchInput").on("focusout", function(key) {

			const selectedRegionRcode = $("#selectedRegionRcode").val();
			const str = $("#searchInput").val();

			if (selectedRegionRcode == "" || typeof selectedRegionRcode == 'undefined' || selectedRegionRcode == null) {
				
				 * alert("지역코드가 없습니다.");
				 * form.keyword.focus();
				 
				return false;
			}

			if (str == "" || typeof str == 'undefined' || str == null || str.length < 2) {
				
				 * alert("검색어를 입력해주세요.");
				 * form.keyword.focus();
				 
				return false;
			}

			getAptName(selectedRegionRcode, str);

	});*/

	$(".aptSearchContainer__menu__search--img").click(function(){
		var ele = $(".aptSearchContainer__menu__search--input")
		$(ele).val("");
		$(ele).focus();
	})
});

function getAptName(region, search) {
	
	const formData = new FormData();

	formData.append("region", region);
	formData.append("search", search);
	
	$.ajax({
		url : "/service/ajax/search",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType : false,
		processData : false,
		beforeSend : function(xhr, opts) {
			
			$(".aptSearchContainer__result_list").html("");
			
			changeDisplayFlexByClassName("aptSearchContainer__loading");
			changeDisplayNoneByClassName("aptSearchContainer__result");
			changeDisplayNoneByClassName("aptSearchContainer__empty");
			
		},
		success : function(data, textStatus, xhr) {
			var apts = data.apts;
			var words = data.words;

			var url = "/service/form/quickSearch/apt/type?region=";
			var param = "aptName=";
			var param2 = "aptEmd=";
			var param3 = "aptJb=";
			
			if (apts.length > 0) {
				
				var template = $("#list_template");
				
				//for (var apt of apts) {
				for (var i = 0; i < apts.length; i++) {
					var apt = apts[i];
				
					var href = "location.href='" + url + region + "&" + param + apt.nameApart + "&" + param2 + apt.addrEmd + "&" + param3 + apt.addrJb + "'";
					$(template).find(".aptSearchContainer__result_list--div").attr("onclick", href);
					
					var replace_nameApart = "";
					var changeList = new Array();
					
					//for (var word of words) {
						//replace_nameApart = apt.nameApart.replace(word, "<span class='apt__word--highlight'>"+ word +"</span>");
					//}
					
//					for (var w = 0; w < changeList.length; w++) {
//						replace_nameApart = apt.nameApart.replace(changeList[w].which, "<span class='apt__word--highlight'>"+ changeList[w].word +"</span>")
//					}
					
					$(template).find(".aptSearchContainer__result_list__title--text").text(apt.nameApart);
					
					var addr = apt.addrSdResult + " " + apt.addrSg + " " + apt.addrGu + " " + apt.addrEmd + " " + apt.addrRi + " " + apt.addrJb;
					
					$(template).find(".aptSearchContainer__result_list__addr--text").text(addr);
					
					
					var html = $(template).html();
					
					$(".aptSearchContainer__result_list").append(html);
				}
				
				changeDisplayFlexByClassName("aptSearchContainer__result");
				changeDisplayNoneByClassName("aptSearchContainer__loading");
				changeDisplayNoneByClassName("aptSearchContainer__empty");
				
			} else {
				changeDisplayFlexByClassName("aptSearchContainer__empty");
				changeDisplayNoneByClassName("aptSearchContainer__loading");
				changeDisplayNoneByClassName("aptSearchContainer__result");
			}
			
		},
		error : function(data, textStatus, xhr) {
			if (data.status == 200)
				alert("로그인이 필요합니다.");
			else if (data.status == 503)
				alert("이미 등록되었습니다." + textStatus);
			else if (data.status == 400)
				alert("잘못된 요청입니다." + textStatus);
			else {
				location.reload();
			}
				//alert("서버 오류가 발생하였습니다." + textStatus);
		}/*,
		complete: function(data, status) {
			changeDisplayNoneByClassName("aptSearchContainer__loading");
			changeDisplayFlexByClassName("aptSearchContainer__result");
			
		}*/
	});

}

function changeDisplayFlexByClassName(className) {
	$("."+className).removeClass('d-none');
	$("."+className).addClass('d-block');
//	var element = document.getElementsByClassName(className);
//	//element[0].classList.replace('d-none', 'd-flex');
//	element[0].classList.add('d-flex');
//	element[0].classList.remove('d-none');
}

function changeDisplayNoneByClassName(className) {
	$("."+className).addClass('d-none');
	$("."+className).removeClass('d-block');
}

function searchApart(thisBtn) {
	location.href = "/service/form/quickSearchResult?no=" + thisBtn.dataset.no
			+ "&nameApart=" + thisBtn.dataset.nameapart + "&address="
			+ thisBtn.dataset.address;
}