console.log(apts);
console.log(estimateTrue)
console.log(estimateFalse);
/* 시작 함수 모음 */
$(document).ready(function () {
	//addSpace(location.href);
	cost(room);
});
/* 시작 함수 모음 */

function comma(num){
    var len, point, str; 
       
    num = num + ""; 
    point = num.length % 3 ;
    len = num.length; 
   
    str = num.substring(0, point); 
    while (point < len) { 
        if (str != "") str += ","; 
        str += num.substring(point, point + 3); 
        point += 3; 
    } 
     
    return str;
 
}

function cost (r) {

	var ldkrCost =  estimateTrue.totalFinalCost;
	var roomCost = estimateTrue.totalFinalCost - estimateFalse.totalFinalCost;
	var ldkCost = estimateFalse.totalFinalCost;
		
	$('.room').html("+"+comma(roomCost)+"원");
	
	if (r) {
		
		var finalCost = ldkrCost;
		var destroyCost = estimateTrue.totalDestroyCost;
		
		if(!destroy) {
			finalCost = finalCost - estimateTrue.totalDestroyCost;
			destroyCost = 0;
		}
		
		//var consCost = estimateTrue.totalConsCost + estimateTrue.totalManageCost;
		var consAllCost = estimateTrue.totalConsAllCost;
		
		$('.tileCost').html(comma(estimateTrue.totalTileCost)+"원");
		$('#totalPrice, #totalPriceBottom').html(comma(finalCost)+"원");
		$('.roomKichen').html(comma(finalCost-roomCost)+"원");
		$('.subMaterialCost').html(comma(estimateTrue.totalSubsCost)+"원");
		$('.consCost').html(comma(consAllCost)+"원");
		$('.wasteCost').html(comma(estimateTrue.totalWasteCost)+"원");
		$('.transCost').html(comma(estimateTrue.totalTransCost)+"원");
		$('.destroyCost').html(comma(estimateTrue.totalDestroyCost)+"원");
		$('.destroyCostDetail').html(comma(destroyCost)+"원");
	} else {
		
		var finalCost = ldkCost;
		var destroyCost = estimateFalse.totalDestroyCost;
		
		if(!destroy) {
			finalCost = finalCost - estimateFalse.totalDestroyCost;
			destroyCost = 0;
		}
		
		//var consCost = estimateFalse.totalConsCost + estimateFalse.totalManageCost;
		var consAllCost = estimateFalse.totalConsAllCost;
		
		$('.tileCost').html(comma(estimateFalse.totalTileCost)+"원");
		$('#totalPrice, #totalPriceBottom').html(comma(finalCost)+"원");
		$('.roomKichen').html(comma(finalCost)+"원");
		$('.subMaterialCost').html(comma(estimateFalse.totalSubsCost)+"원");
		$('.consCost').html(comma(consAllCost)+"원");
		$('.wasteCost').html(comma(estimateFalse.totalWasteCost)+"원");
		$('.transCost').html(comma(estimateFalse.totalTransCost)+"원");
		$('.destroyCost').html(comma(estimateFalse.totalDestroyCost)+"원");
		$('.destroyCostDetail').html(comma(destroyCost)+"원");
	}
}

function makeHrefFunc () {
	location.href = "quickSearchResultDetail?region="+region.rcode+"&aptNo="+apts.no+"&pNo="+material+"&isRoom="+room + "&isAddSpace=" + addSpace + "&isDestroy=" + destroy;
}

function btnInit(e) {
	switch(e.name) {
		case "roomKichen":
			$(".alertText").fadeTo(100,1).fadeTo(1000,0);
			break;
		case "room":
			if (room) {
				room = false;
				makeHrefFunc()
			} else {
				room = true;
				makeHrefFunc()
			}
			break;
		case "veranda": 
			if (addSpace) {
				addSpace = false;
				makeHrefFunc()
			} else {
				addSpace = true;
				makeHrefFunc()
			}
			break;
		case "destroy": 
			if (destroy) {
				destroy = false;
				makeHrefFunc()
			} else {
				destroy = true;
				makeHrefFunc()
			}
			break;
		default:
			break;
	}
}

function tileChange () {
	//location.href = "quickSearch/tile?region="+region.rcode+"&aptNo="+apts.no+"&pNo="+material;
	location.href = "quickSearch/tile?region="+region.rcode+"&aptNo="+apts.no+"&pNo="+material+"&isRoom="+room + "&isAddSpace=" + addSpace  + "&isDestroy=" + destroy;
}

function resultGo () {
	if ($('.detailHeaderText').html() == "견적서 상세보기") {
		location.href="quickSearchResult"+location.search;
	} else {
		history.go(-1);
	}
}
function typeChoice() {
	location.href = "quickSearch/apt/type?region="+region.rcode+"&aptName="+apts.nameApart+"&aptEmd="+apts.addrEmd+"&aptJb="+apts.addrJb+"&typeAcreage="+apts.typeAcreage;
}

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
//			var $temp = $('<input>');
//			$('body').append($temp);
//			$temp.val(location.protocol+"//"+location.host+"/view/"+data).select();
//			document.execCommand('copy');
//			$temp.remove();
			
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