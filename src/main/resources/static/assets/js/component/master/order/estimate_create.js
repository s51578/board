const __calSquareMeter = 3.305785; 

//평수 변환
function converToPy(target) {
	var smValue = $(target).val();
	var convertToPy = Math.round(smValue / __calSquareMeter); // 소수점자리 반올림
	/*convertToPy = convertToPy.toFixed(2);*/

	$("#acreage").val(convertToPy);
}

//면적 변환
function convertToSm(target) {
	var value = $(target).val();
	var convertToSm = value * __calSquareMeter; // 소수점자리 반올림
	convertToSm = convertToSm.toFixed(4);
	
	$("#areaCons").val(convertToSm);
}

function inputNumberFormat(obj) {
	obj.value = comma(uncomma(obj.value));
}
	
function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1');
}

function uncomma(str) {
	str = String(str);
	return str.replace(/[^\d]+/g, '');
}

// 3자리수마다 , 찍어주는 함수
function thousands_separators(num)
{
	var num_parts = num.toString().split(".");
	num_parts[0] = num_parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return num_parts.join(".");
}

function validation(value){
	
	if (value == null || value == "" || typeof value == 'undefined')
		return false;
	else
		return true;
}

function getEstimateCalc() {
	
	var orderNo = $("#orderNo").val();
	var costSheetNo = $("#costSheetNo").val();
	var mtContentsNo = $("#mtContentsNo").val();
	var areaCons = $("#areaCons").val();
	
	if(!validation(orderNo) || !validation(costSheetNo) 
			|| !validation(mtContentsNo) || !validation(areaCons)) {
		alert("필수항목을 채우세요.");
		return;
	}
	
	var formData = new FormData();
	
	formData.append("orderNo", orderNo);
	formData.append("costSheetNo", costSheetNo);
	formData.append("mtContentsNo", mtContentsNo);
	formData.append("areaCons", areaCons);
	
	$.ajax({
		url : "/service/ajax/estimate/order/calc",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			
			initAddParam();
			
			var mtName = data.mtName;
			var tileQtt = data.tileQtt;
			var estTilePrice = data.estTilePrice;
			var totalTileCost = data.totalTileCost;
			
			/*$("#mtName").val(mtName);
			$("#tileQtt").val(tileQtt);
			$("#estTilePrice").val(estTilePrice);
			$("#totalTileCost").val(totalTileCost);*/
			
			addParam("tile", mtName, tileQtt, estTilePrice, totalTileCost)
			
			var estimateSubs = data.estimateSubs;
			
			for (let esub of estimateSubs) {
				
				/* var no = esub.subMaterial.no; */
				var subName = esub.subMaterial.subName;
				var subBoxAmount = esub.subBoxAmount;
				var subBoxCost = esub.subBoxCost;
				var subBoxAmountCost = esub.subBoxAmountCost;
				
				addParam("sub", subName, subBoxAmount, subBoxCost, subBoxAmountCost);
				
			}
			
			/*$(".subs-info").remove();
			$("#subsTr").after(subs_html);
			$("#subsTr").css("display", "none");*/
			
			var consCost = data.consCost + data.manageCost;
			var totalConsAllCost = data.totalConsAllCost;
			/*var totalConsAllCost = data.totalConsCost + '/' + data.totalManageCost;*/
			addParam("cons", "시공비", "1", consCost, totalConsAllCost);
			
			var transCost = data.transCost;
			var totalTransCost = data.totalTransCost;
			
			addParam("trans", "운송비", "1", transCost, totalTransCost);
			
			var totalDestroyCost = data.totalDestroyCost;
			
			addParam("destroy", "철거비", "1", totalDestroyCost, totalDestroyCost);
			
			devMode(data);
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

$(document).ready(function(){
	
	converToPy("#areaCons");
	
	$(".plusOpt button").click(function(){
		
		addParam("add", "", "", "", "");
		changeAllParamName();
		
	})
	
	$(document).on("click", ".minusOpt button", function(e){
		$(e.target).parents("tr").remove();
		changeAllParamName();
	})
	
	$(document).on("change keyup", "._calc_auto", function(e){
		
		var itemAmt = $(e.target).parents("tr").find(".itemAmt").val();
		var itemCost = $(e.target).parents("tr").find(".itemCost").val();
		
		$(e.target).parents("tr").find(".itemAmtCost").val(itemAmt*itemCost);
		
		var total = 0;
		$(".itemAmtCost").each(function(i, e){
			var itemAmtCost = $(e).val();
			total += itemAmtCost*1;
		});
		
		$("#totalEstimateCost").val(total);
	})
	
	$(document).on("change keyup", "#areaCons", function(e){
		converToPy(e.target);
	});
	
	$(document).on("change keyup", "#acreage", function(e){
		convertToSm(e.target);
	});
	
});

function initAddParam() {
	$(".tr_info").not(".tr_info.d-none").remove();	
}

function addParam(itemType, itemName, itemAmt, itemCost, itemAmtCost) {
	
	var element = $("#tr_info_template").clone();
	$(element).removeClass("d-none");
	$(element).attr("id", "");
	
	$(element).find(".itemType").val(itemType);
	$(element).find(".itemName").val(itemName);
	$(element).find(".itemAmt").val(itemAmt);
	$(element).find(".itemCost").val(itemCost);
	$(element).find(".itemAmtCost").val(itemAmtCost);
	/* $(element).find(".plusOpt").remove(); */
	/* $(element).find(".minusOpt").removeClass("d-none"); */
	/* $(element).find("input").attr("type", "hidden"); */
	
	$("#tr_info_template").find("input").val("");
	$("#tr_info_template").before(element);
	
	changeAllParamName();
	
}

function changeParamName(element, className, value) {
	$(element).find(className).atter("name", value);
}

function changeAllParamName() {
	
	var total = 0;
	$(".tr_info").not(".tr_info.d-none").each(function(i, e){

		$(e).find(".itemType").attr("name", "estimateDoc.items["+ i +"].itemType");
		$(e).find(".itemName").attr("name", "estimateDoc.items["+ i +"].itemName");
		$(e).find(".itemAmt").attr("name", "estimateDoc.items["+ i +"].itemAmt");
		$(e).find(".itemCost").attr("name", "estimateDoc.items["+ i +"].itemCost");
		$(e).find(".itemAmtCost").attr("name", "estimateDoc.items["+ i +"].itemAmtCost");
		
		var itemAmtCost = $(e).find(".itemAmtCost").val();
		
		total += itemAmtCost*1;
	})
	
	$("#totalEstimateCost").val(total);
}

function devMode(data) {
	
	var html = "<table>";
	
	for(key in data) {
		
		html += "<tr>";	
		html += "<td>" + key + "</td>";
		html += "<td>" + data[key] + "</td>";
		html += "</tr>";

		if(key=='estimateSubs') {
			
			var obs = data[key];
			
			for (var index in obs) {
				html += "<tr>";
				html += "<td>" + obs[index].subMaterial.subName + "</td>";
				html += "<td> / subBoxAmount : " + obs[index].subBoxAmount + "</td>";
				html += "<td> / cost : " + obs[index].subMaterial.cost + "</td>";
				html += "<td> / usagePerm2 : " + obs[index].subMaterial.usagePerm2 + "</td>";
				html += "</tr>";
			}
		
		}
			
	}
	html += "</table>";
	$("#_estimate_result").html(html);
}