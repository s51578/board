// 3자리수마다 , 찍어주는 함수
function thousands_separators(num)
  {
    var num_parts = num.toString().split(".");
    num_parts[0] = num_parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return num_parts.join(".");
  }

// 절대값
const __calSquareMeter = 3.305785; // 1평당 제곱미터 크기

const __finalPrice_tile_loss = 1.03; // 타일 로스율 3프로

//공간크기 input 변수 사용자 입력 (테스트를 위해 임의 값 넣어둠)
//let pyValue = 0; //평수
//let smValue = 0; // 제곱미터

//DB에서 받아올 값들 (테스트를 위해 임의 값 넣어둠)
//let tilePackageWeight_kg = 32; // 타일 패키지 무게
//let dryfixWeight_kg = 10; // 부자재 무게
//let No_tileInPackage = 4; // 패키지에 있는 타일 개수
//let tilePrice = 0; // 타일가격
//let dryfixPrice = subscost;//15000; // 부자재 가격

let _subs = subs;
//패키지당 회베수 (ex. 600x600 = 1.44)
let _basicTileSquareMeter_600sq = meter2Box; // 기본 타일당 -> meter2Box 값 넣기

let _tilePackageWeight_kg = kgBox;

let _tilePrice = cost;

// 평수 -> 제곱미터 계산기
function pyCalc(pyValue) {
	
	let convertToSquareMeter = 0; // 평 -> 제곱미터 변환 변수	
	//User Data Input으로 전환 필요
/*	console.log("=========================================================");
	console.log("입력된 평수 (바닥면적 기준) : "+pyValue+"평");*/
	
	//convertToSquareMeter = Math.round(pyValue * __calSquareMeter); // 소수점자리 올림
	convertToSquareMeter = pyValue * __calSquareMeter;
	convertToSquareMeter = convertToSquareMeter.toFixed(2);
/*	console.log("평수를 m2로 재계산한 값 : "+convertToSquareMeter+"회베");
	console.log("=========================================================");*/
	return convertToSquareMeter;
}

// 제곱미터 -> 평수 계산기
function squareMeterCalc(smValue) {
	
	let convertToPy = 0; // 제곱미터 -> 평 변환 변수
/*	console.log("=========================================================");
	console.log("입력된 제곱미터 (바닥면적 기준) : "+smValue+"m2");*/
	
	convertToPy = smValue / __calSquareMeter; // 소수점자리 반올림
	convertToPy = convertToPy.toFixed(2);
	
/*	console.log("제곱미터를 평수로 재계산한 값 : "+convertToPy+"평");
	console.log("=========================================================");*/
	return convertToPy;
}

//타일 수량 계산기 (평수)
/*function tileQuantityCalcInPy(convertToPy) {
	
	let exactTileQuantityInPy = (convertToPy / _basicTileSquareMeter_600sq) * __calSquareMeter;
	
	console.log("=========================================================");
	console.log("타일수량 : "+exactTileQuantity+"개");
	console.log("=========================================================");
	
	return exactTileQuantityInPy;
}*/


//*시공에 필요한 최소 타일수량 계산기 (600sq Tile 전용 계산기) - 재곱미터 계산
//패키지 수
function boxQuantityCalc(convertToSquareMeter) {
	
	//const basicTileSquareMeter_600sq = 1.44; 
	//let No_tileInPackage = 4;//DB넘어오는 값 추후 사용 필요
	let exactTileQuantity = convertToSquareMeter / _basicTileSquareMeter_600sq * __finalPrice_tile_loss; // 타일 수량
	let minTileQuantityInPackage = Math.ceil(exactTileQuantity); // 소수점자리 무조건 올림 (ceil)
	//반올림 아닌 올림으로 계산해야함 round()
	
/*	console.log("계산된 최소 필요 타일(한 패키지(4장 묶음)) : "+minTileQuantityInPackage+"묶음");
	console.log("계산된 최소 필요 타일(총 장수) : "+minTileQuantityInPackage*No_tileInPackage+"장");*/
	
	return minTileQuantityInPackage;
	
}

//*부자재량 계산기 (드라이픽스)
function sub_RatioQuantityCalc(exactTileQuantity, per_m2) {
	
	//let dryfixRatioQuantity_tilePackage = 0.4;
	
	//가중치 변수 테스트 필요. 현재 없음
	//let dryfixPrice = 15000;
	
	let calculatedQuantity = exactTileQuantity * per_m2 * _basicTileSquareMeter_600sq;
	let minCalculatedQuantity = Math.ceil(calculatedQuantity);
	
	
/*	console.log("=========================================================");
	console.log("계산된 최소 드라이픽스 (한 패키지당 사용량) : "+minCalculatedQuantity+"통 / 가중치 반영 안된 상태");
	console.log("=========================================================");*/
	
	return minCalculatedQuantity;

}

//부자재 비용 계산기
function sub_PriceCalc(minCalculatedQuantity, sub_cost) {
	
	let totalSubPrice = sub_cost * minCalculatedQuantity;
	
	return totalSubPrice;
}

//*시공비 계산기
function constructionCostCalc(convertToSquareMeter) {
	
	let minAreaConstruction = 30;
	let dailyPaidPerWorker = 300000;
	let minDayToWork = 2;
	let minSqPaidPerWorker = 40000;
	let constructionCost = 0;
	
	if(minAreaConstruction > convertToSquareMeter){
		constructionCost =  minDayToWork * (2 * dailyPaidPerWorker);
	  } else {
		constructionCost = Math.ceil(convertToSquareMeter) * minSqPaidPerWorker;
	}
/*	console.log("계산된 최소 시공비 (한 패키지당 사용량) : "+thousands_separators(constructionCost)+"원");
	console.log("=========================================================");*/

	return constructionCost;
}

//*운송비 계산기
function transportationCostCalc(tileboxQuantity, totalSubskg) {
	
	//tilePackageWeight_kg;
	let totalTilePackage = _tilePackageWeight_kg * tileboxQuantity;
	
	let transportationPrice;
	let transportationUpload;
	let totalTransportationCost;
	
	let totalWeight = totalTilePackage + totalSubskg;
	
/*	console.log("전체 타일 무게 : "+thousands_separators(totalTilePackage)+"kg");
	console.log("전체 부자재(드라이픽스)무게 : "+thousands_separators(totalDryfixPackage)+"kg");
	console.log("전체 타일 무게와 부자재(드라이픽스)무게 : "+thousands_separators(totalWeight)+"kg");
	console.log("=========================================================");*/
	
	//1톤일 경우,
	if(totalWeight <= 1000){
		transportationPrice = 100000;
		transportationUpload = 100000;
		totalTransportationCost = transportationPrice + transportationUpload;
	  //console.log("1톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 혹은 이하일 경우) : "+thousands_separators(totalTransportationCost)+"원");
	
	} else if(totalWeight > 1000 && totalWeight < 1500){
		transportationPrice = 110000;
		transportationUpload = 100000;	
		totalTransportationCost = transportationPrice + transportationUpload;	
	  //console.log("1.5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 이상 혹은 1.5톤일 경우) : "+thousands_separators(totalTransportationCost)+"원");
	
	} else if(totalWeight > 1500 && totalWeight < 2500){
		transportationPrice = 130000;
		transportationUpload = 100000;	
		totalTransportationCost = transportationPrice + transportationUpload;	
	  //console.log("1.5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 이상 혹은 1.5톤일 경우) : "+thousands_separators(totalTransportationCost)+"원");
	
	} else if(totalWeight > 2500 && totalWeight < 3500){
		transportationPrice = 140000;
		transportationUpload = 200000;	
		totalTransportationCost = transportationPrice + transportationUpload;	
	  //console.log("1.5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1톤 이상 혹은 1.5톤일 경우) : "+thousands_separators(totalTransportationCost)+"원");
	
	} else {
		transportationPrice = 150000;
		transportationUpload = 200000;	
		totalTransportationCost = transportationPrice + transportationUpload;
	  //console.log("5톤 트럭 사용 : 계산된 최소 운송비 KG (전체 무게가 1.5톤보다 더 많을 경우) : "+thousands_separators(totalTransportationCost)+"원");
	}
	
/*	console.log("계산된 최소 운송비 KG (입력된 전체 패키지 타일 및 부자재 포함) : "+thousands_separators(totalTransportationCost)+"원");
	console.log("=========================================================");*/
	
	return totalTransportationCost;
}

// 타일 가격 계산기
function tilePriceCalc(exactTileQuantity){

	/*let total_tilePrice = Math.ceil((exactTileQuantity * (_tilePrice * _basicTileSquareMeter_600sq)* __finalPrice_tile_loss));*/
	
	let total_tilePrice = Math.ceil( exactTileQuantity * _tilePrice );
	return total_tilePrice;
}


//*최종 견적 계산기
function totalPrice(exactTileQuantity, allSubsPrice, constructionCost, totalTransportationCost) {
	
	let finalPrice_tile = tilePriceCalc(exactTileQuantity); // 최종 타일가격
	//let finalPrice_tile_loss = 1.03; // 타일 로스율
	//let tax = 1.1;
	let finalPrice_subs = allSubsPrice; //minCalculatedQuantity * dryfixPrice; // 최종 부자재비용
	let finalPrice_construction = constructionCost; // 최종 시공비용
	let finalPrice_transportation = totalTransportationCost; // 최종 운송비용
	
	let finalTotalPrice = 0; 
	
	var privacy_chk = $("#sp_help").is(":checked");
	
	if(!privacy_chk)
		finalTotalPrice =finalPrice_tile + finalPrice_subs + finalPrice_transportation; // 토탈가격 //+ finalPrice_construction (시공비 임시 제외) 
	else
		finalTotalPrice =finalPrice_tile + finalPrice_subs + finalPrice_transportation + finalPrice_construction; // 토탈가격 
	
/*	console.log("타일 가격 : "+thousands_separators(finalPrice_tile)+"원");
	console.log("드라이픽스 부자재 가격 : "+thousands_separators(finalPrice_subs)+"원");
	console.log("시공비 가격 : "+thousands_separators(finalPrice_construction)+"원");
	console.log("운송비 가격 (수도권 기준) : "+thousands_separators(finalPrice_transportation)+"원");
	console.log("총 비용 (수도권 기준) : "+thousands_separators(finalTotalPrice)+"원");
	
	console.log("=========================================================");*/
	
	return finalTotalPrice;
}


//각 부자재 수량 및 총 무게 리턴
function totalSubsKgByEach(tileboxQuantity) {
	
	let totalSubsQuantity = 0;
	let totalSubsKg = 0;
	
	for (let sub of _subs) {
		var no = sub.no;
		let idConst = "subsNo_"+ no;

		let eachQuantity = sub_RatioQuantityCalc(tileboxQuantity, sub.usagePerm2); // 부자재 수량
		
		$("#dryFixTable #"+idConst).find("td:eq(1)").text(eachQuantity+"포");
		//$("#dryFixTable").find("td:eq(1)").text(eachQuantity+"포");
		
		$("#dryFixTable #"+idConst).find("input[name^=subs][name$=subBoxAmount]").val(eachQuantity);
		
		totalSubsQuantity += eachQuantity;
		totalSubsKg += eachQuantity * sub.kgBox;
	}
	
	return totalSubsKg;
}

//각 부자재 가격 및 총 가격 리턴
function totalSubsCostByEach(tileboxQuantity) {
	
	let totalSubsCost = 0;
	
	for (let sub of _subs) {
		var no = sub.no;
		let idConst = "subsNo_"+ no;

		let eachQuantity = sub_RatioQuantityCalc(tileboxQuantity, sub.usagePerm2);
		let eachPrice = sub_PriceCalc(eachQuantity, sub.cost); // 부자재 총 가격
				
		$("#dryFixTable #"+idConst).find("td:eq(3)").text(thousands_separators(eachPrice)+"원");
		
		$("#dryFixTable #"+idConst).find("input[name^=subs][name$=subBoxCost]").val(sub.cost);
		$("#dryFixTable #"+idConst).find("input[name^=subs][name$=subBoxAmountCost]").val(eachPrice);
		//$("#dryFixTable").find("td:eq(3)").text(thousands_separators(eachPrice)+"원");
		totalSubsCost += eachPrice;
	}
	
	return totalSubsCost;
}

function estimate(returnCalcPy) {
	
	//console.log(pyValue);
	let tileboxQuantity = boxQuantityCalc(returnCalcPy); // 시공에 필요한 최소 타일 수량
	//let tileQuantity = tileQuantityCalc(returnCalcPy); // 타일 수량

	let totalSubsKg = totalSubsKgByEach(tileboxQuantity); // 부자재 수량
	//let subQuantity = sub_RatioQuantityCalc(tileboxQuantity); // 부자재 수량
	let totalSubsPrice = totalSubsCostByEach(tileboxQuantity); // 부자재 총 가격
	//let totalSubPrice = sub_PriceCalc(subQuantity); // 부자재 총 가격
	
	let transCost = transportationCostCalc(tileboxQuantity, totalSubsKg);
	//let transCost = transportationCostCalc(tileboxQuantity, subQuantity); //운송비
	
	let totalTilePrice = tilePriceCalc(tileboxQuantity); // 총 타일 가격
	let constructionPrice = Math.floor(constructionCostCalc(returnCalcPy)); 
	
	$("#tileTable").find("td:eq(1)").text(tileboxQuantity+"박스");
	$("#constructionTable").find("td:eq(1)").text(tileboxQuantity+"박스");
	
	// 유저 입력값이 존재하면 값을 넣어주고 없거나 0이면 0원을 표기해줌 (시공비)
	if(pyValue != null && pyValue != 0)
		$("#constructionTable").find("td:eq(3)").text(thousands_separators(constructionPrice)+"원");
	else
		$("#constructionTable").find("td:eq(3)").text("0원");
	
	// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (타일)
	if(typeof pyValue === "undefined" || pyValue === "" || pyValue === null || pyValue === 0) {
		$("#tileTable").find("td:eq(1)").text("0박스");
		$("#tileTable").find("td:eq(3)").text("0원");
		} else {
			$("#tileTable").find("td:eq(3)").text(thousands_separators(totalTilePrice)+"원");
		}
	
	// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (운송비)
	if(typeof pyValue === "undefined" || pyValue === "" || pyValue === null || pyValue === 0)
		$("#transTable").find("td:eq(3)").text("0원");
	else {
		$("#transTable").find("td:eq(2)").text(thousands_separators(transCost)+"원");
		$("#transTable").find("td:eq(3)").text(thousands_separators(transCost)+"원");
	}

	let final_value =  totalPrice(tileboxQuantity, totalSubsPrice, constructionPrice, transCost);
	
	if(pyValue != null && pyValue != 0) {
		$("#minPrice").text(thousands_separators(final_value)+"원");
	}
	
	if(typeof pyValue === "undefined" || pyValue === null || pyValue === "" || pyValue === 0) {
		$("input[name=boxCost]").val(0);
		$("input[name=boxAmount]").val(0);
		$("input[name=boxAmountCost]").val(0);
		//$("input[name=subBoxCost]").val(0);
		//$("input[name=subBoxAmount]").val(0);
		//$("input[name=subBoxAmountCost]").val(0);
		$("input[name=transCost]").val(0);
		$("input[name=transTotalCost]").val(0);
		$("input[name=constCost]").val(0);
		$("input[name=constTotalCost]").val(0);
		$("input[name=totalCost]").val(0);
	} else {
		$("input[name=boxCost]").val(_tilePrice);
		$("input[name=boxAmount]").val(tileboxQuantity);
		$("input[name=boxAmountCost]").val(totalTilePrice);
		//$("input[name=subBoxCost]").val(_dryfixPrice);
		//$("input[name=subBoxAmount]").val(dryFixQuantity);
		//$("input[name=subBoxAmountCost]").val(totalDryFixPrice);
		$("input[name=transCost]").val(100000);
		$("input[name=transTotalCost]").val(transCost);
		$("input[name=constCost]").val(40000);
		$("input[name=constTotalCost]").val(constructionPrice);
		$("input[name=totalCost]").val(final_value);
	}
	
}

$(function(){
	
	//툴팁
	$('[data-toggle="tooltip"]').tooltip()
	
	var init = $("#smValue").val();
	
	if(typeof init!="undefined" && init != null && init != "")
		estimate(init);
	
	// 평수 입력 Input
	$("#pyValue").keyup(function(){
		
		var pyValue = $(this).val(); // 사용자 입력 값을 담을 변수
		let returnCalcPy =  pyCalc(pyValue); // 평 -> 재곱미터 변환값이 담긴 변수
		
		estimate(returnCalcPy);
		
		// 유저 입력값이 존재하면 값을 넣어주고 없거나 0이면 0원을 표기해줌 (시공비)
		if(typeof pyValue === "undefined" || pyValue === "" || pyValue === null || pyValue === 0) {
			$("#constructionTable").find("td:eq(3)").text("0원");
			$("#constructionTable").find("td:eq(1)").text("0박스");
		}
	
		// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (타일)
		if(typeof pyValue === "undefined" || pyValue === "" || pyValue === null || pyValue === 0) {
			$("#tileTable").find("td:eq(1)").text("0박스");
			$("#tileTable").find("td:eq(3)").text("0원");
		}
	
		// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (운송비)
		if(typeof pyValue === "undefined" || pyValue === "" || pyValue === null || pyValue === 0) {
			$("#transTable").find("td:eq(3)").text("0원");
		}
		
		$("#smValue").val(returnCalcPy);
		
	});
	
	// 제곱미터 입력 Input
	$("#smValue").keyup(function(){
		
		let smValue = $(this).val();
		let returnCalSm = squareMeterCalc(smValue); // 제곱미터 -> 평
		
		estimate(smValue);
		
		// 유저 입력값이 존재하면 값을 넣어주고 없거나 0이면 0원을 표기해줌 (시공비)
		if(typeof smValue === "undefined" || smValue === "" || smValue === null || smValue === 0) {
			$("#constructionTable").find("td:eq(3)").text("0원");
			$("#constructionTable").find("td:eq(1)").text("0박스");
		}
	
		// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (타일)
		if(typeof smValue === "undefined" || smValue === "" || smValue === null || smValue === 0) {
			$("#tileTable").find("td:eq(1)").text("0박스");
			$("#tileTable").find("td:eq(3)").text("0원");
		}
	
		// 유저 입력값이 존재하지 않으면 0원을 표기해주고 존재하면 값을 넣어줌 (운송비)
		if(typeof smValue === "undefined" || smValue === "" || smValue === null || smValue === 0) {
			$("#transTable").find("td:eq(3)").text("0원");
		}
		
		$("#pyValue").val(returnCalSm);
		
	});
	

	// 첫 화면 로딩시 시공견적 리스트 숨김
	var constNoService = $("#sp_help:checked").val(); // 처음값 unchecked
	if(!constNoService) // checked 되어있지 않으면
		$('#constructionTable').hide();

 	// 체크 박스 동작 여부에 따라 견적 리스트 화면상태 변경
	$("#sp_help").change(function(){
		
		if(this.checked) {
			$('#constructionTable').show();
			$('#smValue').keyup();
		}
		else {
			$('#constructionTable').hide();
			$('#smValue').keyup();
		}
		
	});
	
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
	
	let str = form.pyValue.value;
	
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




