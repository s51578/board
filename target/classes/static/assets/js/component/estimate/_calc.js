// 3자리수마다 , 찍어주는 함수
function thousands_separators(num)
{
	var num_parts = num.toString().split(".");
	num_parts[0] = num_parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return num_parts.join(".");
}

//dedArea : input
//roomCount : 3
//bathCount : 2
//isExpand : false
//expandRoomCount : 0
//isCons : input
function getEstimate(dedArea, roomCount, bathCount, isExpand, expandRoomCount, isCons) {
	
	var contentsNo = $("#contentsNo").val();
	
	var formData = new FormData();
	
	formData.append("contentsNo", contentsNo);
	formData.append("dedArea", dedArea);
	formData.append("roomCount", roomCount);
	formData.append("bathCount", bathCount);
	formData.append("isExpand", isExpand);
	formData.append("expandRoomCount", expandRoomCount);
	formData.append("isCons", isCons);
	
	$.ajax({
		url : "/service/ajax/estimate",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			
			var tilePrice = data.estTilePrice;
			var estimateSubs = data.estimateSubs;
			var dedArea = data.dedArea;
			var acreage = data.acreage;
			
			$("#pyValue").val(acreage+"평");
			
			var roomCount = data.roomCount;
			var bathCount = data.bathCount;
			var isExpand = data.isExpand;
			var expandRoomCount = data.expandRoomCount;
			var isCons = data.isCons;
			
			var finalArea = data.finalArea;
			var tileQtt = data.tileQtt;
			
			$("#tileTable").find("td:eq(1)").text(tileQtt+"박스");
			$("#constructionTable").find("td:eq(1)").text(tileQtt+"박스");
			
			var estimateSubs = data.estimateSubs;
			
			for (let esub of estimateSubs) {
				
				var no = esub.subMaterial.no;
				let idConst = "subsNo_"+ no;
				
				$("#dryFixTable #"+idConst).find("td:eq(1) > span").text(esub.subBoxAmount+"포");
				$("#dryFixTable #"+idConst).find("td:eq(2) > span").text(thousands_separators(esub.subBoxCost));
				$("#dryFixTable #"+idConst).find("td:eq(3) > span").text(thousands_separators(esub.subBoxAmountCost)+"원");
				
				$("#dryFixTable #"+idConst).find("input[name^=subs][name$=subBoxAmount]").val(esub.subBoxAmount);
				$("#dryFixTable #"+idConst).find("input[name^=subs][name$=subBoxCost]").val(esub.subBoxCost);
				$("#dryFixTable #"+idConst).find("input[name^=subs][name$=subBoxAmountCost]").val(esub.subBoxAmountCost);
				
			}
			
			var totalTileKg = data.totalTileKg;
			var totalSubsKg = data.totalSubsKg;
			
			var totalTileCost = data.totalTileCost;
			$("#tileTable").find("td:eq(3)").text(thousands_separators(totalTileCost)+"원");
			
			var totalSubsCost = data.totalSubsCost;
			var consCost = data.consCost;
			var totalConsCost = data.totalConsCost;
			$("#constructionTable").find("td:eq(3)").text(thousands_separators(totalConsCost)+"원");
			
			var transCost = data.transCost;
			var totalTransCost = data.totalTransCost;
			$("#transTable").find("td:eq(2)").text(thousands_separators(transCost)+"원");
			$("#transTable").find("td:eq(3)").text(thousands_separators(totalTransCost)+"원");
			
			var totalFinalCost = data.totalFinalCost;
			
//			var constNoService = $("#sp_help:checked").val(); // 시공 여부
//			if(!constNoService) // checked 되어있지 않으면
//				$('#constructionTable').hide();
			
			$("#minPrice").text(thousands_separators(totalFinalCost)+"원");
			$("#minPrice_title").text(thousands_separators(totalFinalCost)+"원");
			
			//console.log(data.status);
			
			$("input[name=boxCost]").val(tilePrice);
			$("input[name=boxAmount]").val(tileQtt);
			$("input[name=boxAmountCost]").val(totalTileCost);
			$("input[name=transCost]").val(transCost);
			$("input[name=transTotalCost]").val(totalTransCost);
			$("input[name=constCost]").val(consCost);
			$("input[name=constTotalCost]").val(totalConsCost);
			$("input[name=totalCost]").val(totalFinalCost);
			
		},
		error : function(data, textStatus, xhr) {

			if (data.status == 200)
				alert("로그인이 필요합니다.");
			else if (data.status == 503)
				alert("이미 등록되었습니다." + textStatus);
			else if (data.status == 400)
				alert("잘못된 요청입니다." + textStatus);
			else
				alert("서버 오류가 발생하였습니다." + textStatus);
		}
	});
	
}


//keyup delay
function delay(callback, ms) {
	
	var timer = 0;
	
	return function() {
		var context = this;
		var args = arguments;
		
		clearTimeout(timer);
		
		timer = setTimeout(function () {
			callback.apply(context, args);
		}, ms || 0);
		
	}
}

$(function(){
	
	//툴팁
	$('[data-toggle="tooltip"]').tooltip()
	
	// 제곱미터 입력 Input
	$("#smValue").keyup(delay(function(){
		
		let dedArea = $(this).val();

//		var sp_help = false;
//		if($("#sp_help").is(":checked"))
		var	sp_help = true;
		
		if(typeof dedArea != "undefined" && dedArea != "" && dedArea != null && dedArea != 0) {
			
			if(dedArea < 60)
				getEstimate(dedArea, 2, 1, false, 0, sp_help);
			else
				getEstimate(dedArea, 3, 2, false, 0, sp_help);
		}
		//estimate(smValue);
		
		// 유저 입력값이 존재하면 값을 넣어주고 없거나 0이면 0원을 표기해줌 (시공비)
		if(typeof dedArea === "undefined" || dedArea === "" || dedArea === null || dedArea === 0) {
			$("#constructionTable").find("td:eq(3)").text("0원");
			$("#constructionTable").find("td:eq(1)").text("0박스");
		}
	
		// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (타일)
		if(typeof dedArea === "undefined" || dedArea === "" || dedArea === null || dedArea === 0) {
			$("#tileTable").find("td:eq(1)").text("0박스");
			$("#tileTable").find("td:eq(3)").text("0원");
		}
	
		// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (운송비)
		if(typeof dedArea === "undefined" || dedArea === "" || dedArea === null || dedArea === 0) {
			$("#transTable").find("td:eq(3)").text("0원");
		}
		
		
	},1000));
	
	// 첫 화면 로딩시 시공견적 리스트 숨김
//	var constNoService = $("#sp_help:checked").val(); // 처음값 unchecked
//	if(!constNoService) // checked 되어있지 않으면
//		$('#constructionTable').hide();
//
// 	// 체크 박스 동작 여부에 따라 견적 리스트 화면상태 변경
//	$("#sp_help").change(function(){
//		
//		if(this.checked) {
//			$('#constructionTable').show();
//			$('#smValue').keyup();
//		}
//		else {
//			$('#constructionTable').hide();
//			$('#smValue').keyup();
//		}
//		
//	});
	
	//validation
	$("#submitData").click(function(){
		return modalValidation();
	});
	
	$(".valueCheck").change(function(){
		
		var check = true;
		
		$(".valueCheck").each(function(index, item){
			
			var val = $(item).val();
			if(typeof val =='undefined' || val == null || val == "") {
				check = false;
			}
			
		});
		
		//개인정보취급 동의
		var privacy_chk = $("#privacy").is(":checked");
		if(!privacy_chk)
			check = false;
		
		if(check)
			$("#submitData").removeAttr("disabled");
		else
			$('#submitData').attr('disabled',true);
			
	});
	
	// 가이드북 슬라이더
	$('.guide_slick_slider').slick({
			slidesToShow : 4,
			slidesToScroll : 1,
			autoplay: false,
			dots : false,
			draggable: false,
			prevArrow:"<img id='guide_prev_hover' class='slick-prev-arrow' src='/assets/resources/icon/contents/slide/bt_card_left_n.svg'>",
			nextArrow:"<img id='guide_next_hover' class='slick-next-arrow' src='/assets/resources/icon/contents/slide/bt_card_right_n.svg'>",
			infinite: true,
			
			responsive : [
				{
					breakpoint : 992,
					settings : {
						slidesToShow : 3,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1
					}
				}, 
				{
					breakpoint : 768,
					settings : {
						slidesToShow : 2,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1
					}
				}, 
				{
					breakpoint : 576,
					settings : {
						slidesToShow : 1,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1,
						dots: true,
						draggable: true,
						arrows: false
					}
				}
			],
		});
		
		
		/*$("#guide_prev_hover").hover(function(){
		    $("#guide_prev_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_left_s.svg");
		    }, function(){
		    $("#guide_prev_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_left_n.svg");
		  });
		  $("#guide_next_hover").hover(function(){
		    $("#guide_next_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_right_s.svg");
		    }, function(){
		    $("#guide_next_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_right_n.svg");
		  });*/

		// 동적 요소에 적용
		$(document).on("mouseover", "#guide_prev_hover", function(){
			$("#guide_prev_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_left_s.svg");
		});
		$(document).on("mouseout", "#guide_prev_hover", function(){
			$("#guide_prev_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_left_n.svg");
		});
		$(document).on("mouseover", "#guide_next_hover", function(){
			$("#guide_next_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_right_s.svg");
		});
		$(document).on("mouseout", "#guide_next_hover", function(){
			$("#guide_next_hover").attr("src", "/assets/resources/icon/contents/slide/bt_card_right_n.svg");
		});

		//가이드북 div 클릭 이동
		$(".guide_card").css("cursor", "pointer");
		$(".guidebook_1").on("click", function(){
			$(location).attr("href", "/service/guide/guidebook_1")
		});
		$(".guidebook_2").on("click", function(){
			$(location).attr("href", "/service/guide/guidebook_2")
		});
		$(".guidebook_3").on("click", function(){
			$(location).attr("href", "/service/guide/guidebook_3")
		});
		$(".guidebook_4").on("click", function(){
			$(location).attr("href", "/service/guide/guidebook_4")
		});
		$(".guidebook_5").on("click", function(){
			$(location).attr("href", "/service/guide/guidebook_5")
		});
		$(".guidebook_6").on("click", function(){
			$(location).attr("href", "/service/guide/guidebook_6")
		});
		$(".guidebook_7").on("click", function(){
			$(location).attr("href", "/service/guide/guidebook_7")
		});
		
});


// 값 넘길때 값 체크
function calcResult() {
	var form = document.calcForm;
	
	let str = form.smValue.value;
	
	if(typeof str ==='undefined' || str === null || str == 0 || str == "") {
		alert("평수를 입력해주세요.");
		form.pyValue.focus();
		return;
	}
	
	/*if(!/^(\d+)$/.test(str)) {
		alert("숫자만 가능합니다.");
		form.focus();
		return;
	}*/
	
	$("#orderModal").modal();
	
}

//값 넘길때 값 체크
function modalValidation() {
	
	var name = $("#_name").val();
	var phone = $("#_phone").val();
	var email = $("#_email").val();
	
	if(typeof name =='undefined' || name == null || name == "") {
		alert("이름은 필수 입력 정보입니다.");
		$("#_name").focus();
		return false;
	}
	
	if(typeof phone =='undefined' || phone == null || phone == 0 || phone == "") {
		alert("전화번호는 필수 입력 정보입니다.");
		$("#_phone").focus();
		return false;
	}
	
	if(typeof email =='undefined' || email == null || email == "") {
		alert("이메일은 필수 입력 정보입니다.");
		$("#_email").focus();
		return false;
	}
	
	return true;
}




