var whaple_map = null; //네이버 지도
var correctAddress = ""; //네이버 api 이용한 지번 주소값 저장
var address = apts.addrSdResult+apts.addrSg+apts.addrGu+apts.addrEmd+apts.addrRi+apts.addrJb; // get파라미터로 넘어온 주소 -> 네이버 api로 전송
console.log(estimateTrue);
console.log(estimateFalse);
/***************************************geoJson config*********************************************/
/*
전자지도 업데이트 이력
2021/10 : 최초 등록 (21/08월 버전 지도)
2021/11/16 : 지도 용량 60프로에서 5프로 단순화로 수정
*/
const jsonUpdateYearMonth = "2108"; //json파일 업데이트 날짜, 업데이트후 이부분 수정
const jsonRegion = [{"서울특별시":"seoul",
								"부산광역시":"busan",
								"대구광역시":"daegu",
								"부산광역시":"busan",
								"인천광역시":"incheon",
								"광주광역시":"gwangju",
								"대전광역시":"daejeon",
								"울산광역시":"ulsan",
								"세종특별자치시":"sejong",
								"경기도":"gyeonggi-do",
								"강원도":"gangwon-do",
								"충청북도":"chungcheongbuk-do",
								"충청남도":"chungcheongnam-do",
								"전라북도":"jeollabuk-do",
								"전라남도":"jeollanam-do",
								"경상북도":"gyeongsangbuk-do",
								"경상남도":"gyeongsangnam-do",
								"제주특별자치도":"jeju-do"}]; //json 시,도별 json실제파일명 매칭 배열 (행정구역명 변경이나 json파일명 변경시 수정)
								
/* 지도 업데이트 방법
	1. 도로명주소 개발자센터 -> 전자지도 다운로드 -> 신청 후 당일이나 익일 전국지도 개별 다운로드
	2. XrProjection 프로그램 사용 좌표계 변환 UTM-K(GRS80 타원체) -> WGS84 타원체의 경위도
	3. 변환된 파일 QGIS로 geoJson 변환 (bd파일 필수 칼럼 - BD_MGT_SN , EQB_MAN_SN & eqb파일 필수 칼럼 - SIG_CD , EQB_MAN_SN)
	4. mapshaper 사이트에서 5%로 simplify 후 export
	5. 업데이트 및 파일명 확인
	꼭 shp파일에서 필터 걸고 아파트만 남겨서 geoJson으로 저장하자 (이거 안하면 용량이 매우 커져서 감당안됨)
	*****BDTYP_CD = 02001 (아파트)*****
	testUrl = http://localhost:8080/service/form/quickSearchResult?no=151770&nameApart=판교원마을1단지&address=경기도성남시분당구판교동622
*/
/*****************************************************************************************************/
searchJuso(); //도로명주소 api 호출용 validation (필수)
getPoint(); //네이버 지도 api 사용 좌표 얻고 지도세팅 -> 폴리곤 까지 연계된 함수 (사실상 메인함수)

function tileChange() {
	//location.href = "quickSearch/tile?region="+region.rcode+"&aptNo="+apts.no+"&pNo="+material;
	location.href = "quickSearch/tile?region="+region.rcode+"&aptNo="+apts.no+"&pNo="+material+"&isRoom="+room + "&isAddSpace=" + addSpace  + "&isDestroy=" + destroy;
}


function getParam(name) { //get 파라미터 받아오기
    var splitUrl = location.search.substr(location.search.indexOf("?") + 1);
    var param = "";
    splitUrl = splitUrl.split("&");
    for (var i = 0; i < splitUrl.length; i++)
    {
        var splitBucket = splitUrl[i].split("=");
        if ([splitBucket[0]] == name) { 
            param = splitBucket[1];
        }
    }
    return decodeURI(param);
}
        

function init(lat, lng, naverAddr) { //맵그리는 초기화 함수
    whaple_map = new naver.maps.Map('map', {
		center: new naver.maps.LatLng(lat, lng),
        zoom: 17,
        minZoom: 14,
        maxZoom: 20,
        detectCoveredMarker: false,
		zoomControl: true,
		zoomControlOptions: {
			style: naver.maps.ZoomControlStyle.SMALL,
			position: naver.maps.Position.RIGHT_CENTER
		}
    });

	var marker = new naver.maps.Marker({
	    position: new naver.maps.LatLng(lat, lng),
	    map: whaple_map,
	    icon: {
			content:'<div><div class="markerIcon"><img src="/assets/resources/logo/ic_map_pin.svg"/></div><div class="markerPrice"><span class="markerPriceText"></span></div></div>',
	        size: new naver.maps.Size(72, 59),
	        origin: new naver.maps.Point(0, 0),
	        anchor: new naver.maps.Point(35, 30)
	    }
	});
	
	setPrice('.markerPriceText',"");
	
	var contentString = [
        '<div class="aptBox">',
        '   <span class="aptBoxName">'+apts.nameApart+'아파트</span>',
        '   <span class="aptBoxAddress">('+apts.addrEmd+" "+apts.addrRi+" "+apts.addrJb+')</span>',
        '</div>'
    ].join('');
    
	var infowindow = new naver.maps.InfoWindow({
	    content: contentString,
	    borderColor: '#a8a8a8',
	    disableAnchor:true
	});
	
	naver.maps.Event.addListener(marker, 'click', function(e) {
	    if (infowindow.getMap()) {
	        infowindow.close();
	    } else {
	        infowindow.open(whaple_map, marker);
	    }
	});
	
	infowindow.open(whaple_map, marker);
}

//특수문자, 특정문자열(sql예약어의 앞뒤공백포함) 제거
function checkSearchedWord(obj){
	if(obj.length >0){
		//특수문자 제거
		var expText = /[%=><]/ ;
		if(expText.test(obj) == true){
			alert("특수문자를 입력 할수 없습니다.") ;
			obj = obj.split(expText).join(""); 
			return false;
		}
		
		//특정문자열(sql예약어의 앞뒤공백포함) 제거
		var sqlArray = new Array(
			//sql 예약어
			"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
             		 "UNION",  "FETCH", "DECLARE", "TRUNCATE" 
		);
		
		var regex;
		for(var i=0; i<sqlArray.length; i++){
			regex = new RegExp( sqlArray[i] ,"gi") ;
			
			if (regex.test(obj) ) {
			    alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
				obj =obj.replace(regex, "");
				return false;
			}
		}
	}
	return true ;
}

// 적용예 (api 호출 전에 검색어 체크) 
function searchJuso(){
	if (!checkSearchedWord(address)) {
		return ;
	}
}

function isbdMgt_mached(element, bdMgtsn)  {
  if(element.bdMgtSn === bdMgtsn)  {
    return true;
  }
}

function drawPolygon_bd (polygonData, bdMgtsnData) { //건물 폴리곤
	var arrPolygon = polygonData.features;
	var pathNaver = [];
	var multiPolygon = [];
	var multiPolygonCnt = 0;
	var eqb = [];
	var polygonFlag = true;
	//console.log(bdMgtsnData);
	for (var x = 0; x < arrPolygon.length; x++) {
		if (arrPolygon[x].properties.BD_MGT_SN == bdMgtsnData) {
			for (y=0; y < arrPolygon[x].geometry.coordinates[0].length; y++) { //일치하는건물
					multiPolygon.push(new naver.maps.LatLng(arrPolygon[x].geometry.coordinates[0][y][1], arrPolygon[x].geometry.coordinates[0][y][0]));
					multiPolygonCnt = 1;
				}
			eqb.push(arrPolygon[x].properties.EQB_MAN_SN);
			eqb.push(arrPolygon[x].properties.BD_MGT_SN.substr(0,10));
		}
	}
	
	for (var x = 0; x < arrPolygon.length; x++) {
		if (arrPolygon[x].properties.EQB_MAN_SN == eqb[0] && arrPolygon[x].properties.BD_MGT_SN.indexOf(eqb[1]) != -1 && arrPolygon[x].properties.BD_MGT_SN != bdMgtsnData ) {
			if (multiPolygonCnt > 0) { //건물수 여러개
				if (eqb[0] != 0) { //건물군이 0인애들을 같은 건물군으로 오해하는것을 방지
					if (polygonFlag) { //첫번째 건물 폴리곤 초기화
						pathNaver.push(multiPolygon);
						multiPolygon = [];
						polygonFlag = false;
					}
					for (y=0; y < arrPolygon[x].geometry.coordinates[0].length; y++) {
						multiPolygon.push(new naver.maps.LatLng(arrPolygon[x].geometry.coordinates[0][y][1], arrPolygon[x].geometry.coordinates[0][y][0]));
						if (y == arrPolygon[x].geometry.coordinates[0].length-1) {
							pathNaver.push(multiPolygon);
							multiPolygon = [];
						}
					}
				}
			} else { //건물수 한개 (위쪽으로 코드 이동됨)
				
			}
			multiPolygonCnt++;
		}
	}
	if (multiPolygonCnt == 1 || eqb[0] == 0) {
		pathNaver = [multiPolygon];
	}
	//console.log(pathNaver);	
	if (eqb[0] == 0) {
		var polygon = new naver.maps.Polygon({
		    map: whaple_map,
		    paths: 
		        pathNaver
		    ,
		    fillColor: '#5eaea0',
	    	strokeColor: '#5eaea0',
	    	fillOpacity: 0.1,
	    	strokeOpacity: 1,	
	    	strokeWeight: 3
		});
	}
	return eqb;
}


function drawPolyLine_eqb (polyLineData, eqb) {
	var arrPolyLine = polyLineData.features;
	var pathNaver = [];
	var multiPolygon = [];
	var bdMgtsn = eqb[1].substring(0,5);
	console.log("건물군 : "+eqb[0]);
	for (x = 0; x < arrPolyLine.length; x++) {
		if (arrPolyLine[x].properties.EQB_MAN_SN == eqb[0] && arrPolyLine[x].properties.SIG_CD == bdMgtsn) {
			if (arrPolyLine[x].geometry.type == "Polygon") { //폴리곤
				for (y=0; y < arrPolyLine[x].geometry.coordinates[0].length; y++) {
					pathNaver.push(new naver.maps.LatLng(arrPolyLine[x].geometry.coordinates[0][y][1], arrPolyLine[x].geometry.coordinates[0][y][0]));
				}
				pathNaver = [pathNaver];
			} else { // 멀티 폴리곤 
				for (y=0; y < arrPolyLine[x].geometry.coordinates.length; y++) {
					for (z=0; z <arrPolyLine[x].geometry.coordinates[y].length; z++) {
						for (a=0; a <arrPolyLine[x].geometry.coordinates[y][z].length; a++) {	
							multiPolygon.push(new naver.maps.LatLng(arrPolyLine[x].geometry.coordinates[y][z][a][1],arrPolyLine[x].geometry.coordinates[y][z][a][0]));
							if (a == arrPolyLine[x].geometry.coordinates[y][z].length-1) {
								pathNaver.push(multiPolygon);
								multiPolygon = [];
							}
						}
					}
				}
			}
		}
	}
	var polyLine_eqb = new naver.maps.Polygon({
	    map: whaple_map,
	    paths: 
			pathNaver
	    ,
	    zIndex : 3,
	    fillColor: '#5eaea0',
	    strokeColor: '#5eaea0',
	    fillOpacity: 0.1,
	    strokeOpacity: 1,	
	    strokeWeight: 3
	});
}

function get_polygon_json () { //도로명주소 개발자센터 api 호출
	const formData = new FormData();
	formData.append("confmKey", "U01TX0FVVEgyMDIxMTAxMjE2MDI1NDExMTc0ODY=");
	formData.append("currentPage", "1");
	formData.append("countPerPage", "10");
	formData.append("resultType", "json");
	formData.append("keyword", correctAddress);
	$.ajax({
		url : "https://www.juso.go.kr/addrlink/addrLinkApi.do",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
				var jsonJusoData = data.results;
				var jsonDataStatus = jsonJusoData.common;
				var bdMgtSn = "";
				var searchAptName = correctAddress.split(" ");
				var siNm = "";
				console.log(jsonJusoData);
				if ( jsonDataStatus.errorCode == "0") { //api 에러코드 발생하지 않았을때 (정상)
					if (jsonJusoData.juso.length > 0) { //api 검색 결과 정상이지만 주소값 리턴되지 않는 상황 제외
						if (jsonDataStatus.totalCount >1) { //api 검색결과 1개 이상
							for(x=0; x < jsonJusoData.juso.length; x++) {
								var bdNm = jsonJusoData.juso[x].bdNm.replace(/ /g,"");
								if (bdNm.indexOf(searchAptName[searchAptName.length-1]) != -1 && jsonJusoData.juso[x].bdKdcd == "1") { //아파트명으로 검색 및 공동주택여부 : 1 (공동주택) 우선순위로 데이터 수집
									bdMgtSn = jsonJusoData.juso[x].bdMgtSn;
									siNm = jsonJusoData.juso[x].siNm;
									break;
								} else {
									bdMgtSn = jsonJusoData.juso[0].bdMgtSn;
									siNm = jsonJusoData.juso[0].siNm;
								}
							}
						} else { // 검색 결과 1개
							bdMgtSn = jsonJusoData.juso[0].bdMgtSn;
							siNm = jsonJusoData.juso[0].siNm;
						}
						if (jsonRegion[0][siNm]) {
							siNm = jsonRegion[0][siNm];
						} else {
							console.log("시,도 정보 확인");
						}
						var bd_url = "/resources/map/geoJson/"+siNm+"_bd_"+jsonUpdateYearMonth+".json"; 
						var eqb_url = "/resources/map/geoJson/"+siNm+"_eqb_"+jsonUpdateYearMonth+".json";
						var eqb = "";
						$.get(bd_url //건물
							).done(function(data){
								 eqb = drawPolygon_bd(data,bdMgtSn);
								 $.get(eqb_url //건물군
										).done(function(data){
											if (eqb.length == 0) {
												console.log("건물 지형 정보 없음");
												return;
											}
											drawPolyLine_eqb(data,eqb);
										}).fail(function(data){
											console.log("건물군 로딩 실패\n에러 코드: "+ data);
									});
							}).fail(function(data){
								console.log(data);
								console.log("건물  로딩 실패\n에러 코드: "+ data);
						});
					} else { //api 통신 성공했으나 주소가 리턴되지 않을경우가 있다
						
					}
				} else { // api에러 에러메시지 출력
					console.log(jsonDataStatus.errorMessage);
				}
		},
		error : function(data, textStatus, xhr) {
			if (data.status == 200)
				console.log("로그인이 필요합니다.");
			else if (data.status == 503)
				console.log("이미 등록되었습니다." + textStatus);
			else if (data.status == 400)
				console.log("잘못된 요청입니다." + textStatus);
			else
				console.log("서버 오류가 발생하였습니다." + textStatus);
		}
	});
	
}

 function getPoint () { //네이버에서 좌표값 가져오기
    naver.maps.Service.geocode({
        address: address
    }, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
            return console.log('호출 에러 입니다.');
        } else {                    
            var result = response.result, // 검색 결과의 컨테이너
            items = result.items; // 검색 결과의 배열
            console.log(items);
            if (items.length != 0) {
                var pointX = items[0].point.x;
                var pointY = items[0].point.y;
                correctAddress = items[0].address;
                var naverAddress = items[0].address;
                init(pointY,pointX,naverAddress);
                resize();
                get_polygon_json(); //폴리곤
            } else {
                return console.log('검색결과가 없습니다.');
            }
        }
    });
}


//window.addEventListener('DOMContentLoaded', function() {
//	resize();
//	window.addEventListener('resize', resize);
//});

function resize() {
	var mapWidth = document.getElementById('map').offsetWidth
	var mapHeight = window.innerHeight
			- document.getElementById('footerBar').offsetHeight;
	var Size = new naver.maps.Size(mapWidth, mapHeight);
	whaple_map.setSize(Size);
}

function counselingDetail () {
	location.href="/service/form/quickSearchResultDetail"+location.search;
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
				console.log("관리자에게 문의하십시오." + textStatus);
	
		}
	});
}

function typeChoice() {
	location.href = "quickSearch/apt/type?region="+region.rcode+"&aptName="+apts.nameApart+"&aptEmd="+apts.addrEmd+"&aptJb="+apts.addrJb+"&typeAcreage="+apts.typeAcreage;
}

function aptSearch () {
	location.href = "quickSearch/apt?region="+region.rcode;
}


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

$(document).ready (function () {
	/* 자동 시작 함수 모음 */
	setPrice('.counselingPrice');
  /* 시공 범위  */
  var areaText = "시공 범위: 거실, 주방 바닥";
  	if (room) {
		areaText += " · 방 "+estimateTrue.roomCount+"개 바닥";
	}
	$('#areaRange').text(areaText);
    /* 자동 시작 함수 모음 */
});

function setPrice (target,won="원") {
		/* 예상 견적가 */
	if (room) {
		
		var finalCost = estimateTrue.totalFinalCost;
		
		if(!destroy) {
			finalCost = finalCost - estimateTrue.totalDestroyCost;
			destroyCost = 0;
		}
		
		$(target).html(comma(finalCost)+won);
	} else {
		
		var finalCost = estimateFalse.totalFinalCost;
		
		if(!destroy) {
			finalCost = finalCost - estimateFalse.totalDestroyCost;
			destroyCost = 0;
		}
		
		$(target).html(comma(finalCost)+won);
	}
}