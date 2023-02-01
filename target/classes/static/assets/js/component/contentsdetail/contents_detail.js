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

//값 넘길때 값 체크
function contact() {
	
	var cno = $("#_cno").val();
	var name = $("#_name").val();
	var phoneNum = $("#_phone").val();
	var email = $("#_email").val();
	var memo = $("#_memo").val();
	
	var privacy = false;
	
	if($("#privacy").is(':checked'))
		privacy = true;
	else {
		alert("개인정보수집이용에 동의해주세요");
		return false;
	}
	
	if(typeof name =='undefined' || name == null || name == "") {
		alert("이름은 필수 입력 정보입니다.");
		$("#_name").focus();
		return false;
	}
	
	if(typeof phoneNum =='undefined' || phoneNum == null || phoneNum == 0 || phoneNum == "") {
		alert("전화번호는 필수 입력 정보입니다.");
		$("#_phone").focus();
		return false;
	}
	
	var formData = new FormData();
	
	formData.append("cno", cno);
	formData.append("name", name);
	formData.append("phoneNum", phoneNum);
	formData.append("email", email);
	formData.append("memo", memo);
	formData.append("privacy", privacy);
	
	$.ajax({
		url : "/service/ajax/contact",
		type : "POST",
		cache : false,
		async : false,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			alert("신청되었습니다");
			$('.modal').modal('hide');
		},
		error : function(data, textStatus, xhr) {

			if(data.status > 300)
				alert("불편을 드려서 죄송합니다." + textStatus);
			
		}
	});
	
	return true;
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

// 요소의 보임 여부
function checkVisible(elm,eval) {
	eval=eval||"object visible";
	var viewportHeight=$(window).height(), // viewport의 높이
	    viewportWidth=$(window).width(),

	scrolltop=$(window).scrollTop(), // 화면 최상단의 높이
	y=$(elm).offset().top, // 요소의 절대높이
	elementHeight=$(elm).height(); // 각 요소의 높이
	
	if (eval == "object visible") {
		return ((y < (viewportHeight+scrolltop)) && ( y>(scrolltop-elementHeight+80))); // 80 헤더의 크기
	}
}	

// 절대좌표
// jQuery에서 높이를 구할 경우 문서가 load된 후에 코드를 실행해도
// load때마다 높이가 달라지는 현상이 발생함에 따라
// JS로 구현
window.addEventListener('load', function(event){
	// PC 요소 절대 높이
	/*const product_description = document.getElementById('product_description');
	const service_process = document.getElementById('service_process');
	const tile_detail_info = document.getElementById('tile_detail_info');
	const mdPick_slide = document.getElementById('mdPick_slide');
	const pd_absoluteOffset = window.pageYOffset + product_description.getBoundingClientRect().top;
	const sp_absoluteOffset = window.pageYOffset + service_process.getBoundingClientRect().top;
	const di_absoluteOffset = window.pageYOffset + tile_detail_info.getBoundingClientRect().top;
	const mdPick_absoluteOffset = window.pageYOffset + mdPick_slide.getBoundingClientRect().top;*/
	
	
	// PC Sticky Header 버튼
	const pd_nav_btn = document.getElementById('li_product_description');
	const di_nav_btn = document.getElementById('li_tile_detail_info');
	const sp_nav_btn = document.getElementById('li_service_process');
	const mdPick_nav_btn = document.getElementById('li_mdPick');
	
	// PC Fixed Header 버튼
	const fixed_pd_nav_btn = document.getElementById('fixed_li_product_description');
	const fixed_di_nav_btn = document.getElementById('fixed_li_tile_detail_info');
	const fixed_sp_nav_btn = document.getElementById('fixed_li_service_process');
	const fixed_mdPick_nav_btn = document.getElementById('fixed_li_mdPick');
	
	// PC sticky header
	pd_nav_btn.addEventListener('click', function(event){
		const product_description = document.getElementById('product_description');
		const pd_absoluteOffset = window.pageYOffset + product_description.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:pd_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	sp_nav_btn.addEventListener('click', function(event){
		const service_process = document.getElementById('service_process');
		const sp_absoluteOffset = window.pageYOffset + service_process.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:sp_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	di_nav_btn.addEventListener('click', function(event){
		const tile_detail_info = document.getElementById('tile_detail_info');
		const di_absoluteOffset = window.pageYOffset + tile_detail_info.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:di_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	
	mdPick_nav_btn.addEventListener('click', function(event){
		const mdPick_slide = document.getElementById('mdPick_slide');
		const mdPick_absoluteOffset = window.pageYOffset + mdPick_slide.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:mdPick_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	// PC Fixed header
	fixed_pd_nav_btn.addEventListener('click', function(event){
		const product_description = document.getElementById('product_description');
		const pd_absoluteOffset = window.pageYOffset + product_description.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:pd_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	fixed_sp_nav_btn.addEventListener('click', function(event){
		const service_process = document.getElementById('service_process');
		const sp_absoluteOffset = window.pageYOffset + service_process.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:sp_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	fixed_di_nav_btn.addEventListener('click', function(event){
		const tile_detail_info = document.getElementById('tile_detail_info');
		const di_absoluteOffset = window.pageYOffset + tile_detail_info.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:di_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	
	fixed_mdPick_nav_btn.addEventListener('click', function(event){
		const mdPick_slide = document.getElementById('mdPick_slide');
		const mdPick_absoluteOffset = window.pageYOffset + mdPick_slide.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:mdPick_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	/*// 모바일 요소
	const mobile_product_description = document.getElementById('mobile_product_description');
	const mobile_service_process = document.getElementById('mobile_service_process');
	const mobile_tile_detail_info = document.getElementById('mobile_tile_detail_info');
	const mobile_mdPick_slide = document.getElementById('mobile_mdPick_slide');
	// 모바일 절대 높이
	const mobile_pd_absoluteOffset = window.pageYOffset + mobile_product_description.getBoundingClientRect().top;
	const mobile_sp_absoluteOffset = window.pageYOffset + mobile_service_process.getBoundingClientRect().top;
	const mobile_di_absoluteOffset = window.pageYOffset + mobile_tile_detail_info.getBoundingClientRect().top;
	const mobile_mdPick_absoluteOffset = window.pageYOffset + mobile_mdPick_slide.getBoundingClientRect().top;*/
	
	// 모바일 환경
	const mobile_pd_nav_btn = document.getElementById('mobile_li_product_description');
	const mobile_sp_nav_btn = document.getElementById('mobile_li_service_process');
	const mobile_di_nav_btn = document.getElementById('mobile_li_tile_detail_info');
	const mobile_mdPick_nav_btn = document.getElementById('mobile_li_mdPick');
	
	//모바일 클릭시 이동
	mobile_pd_nav_btn.addEventListener('click', function(event){
		const mobile_product_description = document.getElementById('mobile_product_description');
		const mobile_pd_absoluteOffset = window.pageYOffset + mobile_product_description.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:mobile_pd_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	mobile_sp_nav_btn.addEventListener('click', function(event){
		const mobile_service_process = document.getElementById('mobile_service_process');
		const mobile_sp_absoluteOffset = window.pageYOffset + mobile_service_process.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:mobile_sp_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	mobile_di_nav_btn.addEventListener('click', function(event){
		const mobile_tile_detail_info = document.getElementById('mobile_tile_detail_info');
		const mobile_di_absoluteOffset = window.pageYOffset + mobile_tile_detail_info.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:mobile_di_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	mobile_mdPick_nav_btn.addEventListener('click', function(event){
		const mobile_mdPick_slide = document.getElementById('mobile_mdPick_slide');
		const mobile_mdPick_absoluteOffset = window.pageYOffset + mobile_mdPick_slide.getBoundingClientRect().top;
		$("html, body").animate({scrollTop:mobile_mdPick_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
		event.stopPropagation();
	});
	
	// 창 크기 변화에서도 적용 되도록	
	window.addEventListener('resize', function(event) {
		// 각 이동 요소 절대좌표	
		/*const product_description = document.getElementById('product_description');
		const tile_detail_info = document.getElementById('tile_detail_info');
		const service_process = document.getElementById('service_process');
		const mdPick_slide = document.getElementById('mdPick_slide');
		const pd_absoluteOffset = window.pageYOffset + product_description.getBoundingClientRect().top;
		const di_absoluteOffset = window.pageYOffset + tile_detail_info.getBoundingClientRect().top;
		const sp_absoluteOffset = window.pageYOffset + service_process.getBoundingClientRect().top;
		const mdPick_absoluteOffset = window.pageYOffset + mdPick_slide.getBoundingClientRect().top;*/
		
		const pd_nav_btn = document.getElementById('li_product_description');
		const di_nav_btn = document.getElementById('li_tile_detail_info');
		const sp_nav_btn = document.getElementById('li_service_process');
		const mdPick_nav_btn = document.getElementById('li_mdPick');
		
		// PC Fixed Header 버튼
		const fixed_pd_nav_btn = document.getElementById('fixed_li_product_description');
		const fixed_di_nav_btn = document.getElementById('fixed_li_tile_detail_info');
		const fixed_sp_nav_btn = document.getElementById('fixed_li_service_process');
		const fixed_mdPick_nav_btn = document.getElementById('fixed_li_mdPick');
		
		
		// PC sticky header
		pd_nav_btn.addEventListener('click', function(event){
			const product_description = document.getElementById('product_description');
			const pd_absoluteOffset = window.pageYOffset + product_description.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:pd_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		sp_nav_btn.addEventListener('click', function(event){
			const service_process = document.getElementById('service_process');
			const sp_absoluteOffset = window.pageYOffset + service_process.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:sp_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		di_nav_btn.addEventListener('click', function(event){
			const tile_detail_info = document.getElementById('tile_detail_info');
			const di_absoluteOffset = window.pageYOffset + tile_detail_info.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:di_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		
		mdPick_nav_btn.addEventListener('click', function(event){
			const mdPick_slide = document.getElementById('mdPick_slide');
			const mdPick_absoluteOffset = window.pageYOffset + mdPick_slide.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:mdPick_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		// PC Fixed header
		fixed_pd_nav_btn.addEventListener('click', function(event){
			const product_description = document.getElementById('product_description');
			const pd_absoluteOffset = window.pageYOffset + product_description.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:pd_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		fixed_sp_nav_btn.addEventListener('click', function(event){
			const service_process = document.getElementById('service_process');
			const sp_absoluteOffset = window.pageYOffset + service_process.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:sp_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		fixed_di_nav_btn.addEventListener('click', function(event){
			const tile_detail_info = document.getElementById('tile_detail_info');
			const di_absoluteOffset = window.pageYOffset + tile_detail_info.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:di_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		
		fixed_mdPick_nav_btn.addEventListener('click', function(event){
			const mdPick_slide = document.getElementById('mdPick_slide');
			const mdPick_absoluteOffset = window.pageYOffset + mdPick_slide.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:mdPick_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		/*// 모바일 요소
		const mobile_product_description = document.getElementById('mobile_product_description');
		const mobile_tile_detail_info = document.getElementById('mobile_tile_detail_info');
		const mobile_service_process = document.getElementById('mobile_service_process');
		const mobile_mdPick_slide = document.getElementById('mobile_mdPick_slide');
		// 모바일 절대 높이
		const mobile_pd_absoluteOffset = window.pageYOffset + mobile_product_description.getBoundingClientRect().top;
		const mobile_di_absoluteOffset = window.pageYOffset + mobile_tile_detail_info.getBoundingClientRect().top;
		const mobile_sp_absoluteOffset = window.pageYOffset + mobile_service_process.getBoundingClientRect().top;
		const mobile_mdPick_absoluteOffset = window.pageYOffset + mobile_mdPick_slide.getBoundingClientRect().top;*/
		
		// 모바일 환경
		const mobile_pd_nav_btn = document.getElementById('mobile_li_product_description');
		const mobile_di_nav_btn = document.getElementById('mobile_li_tile_detail_info');
		const mobile_sp_nav_btn = document.getElementById('mobile_li_service_process');
		const mobile_mdPick_nav_btn = document.getElementById('mobile_li_mdPick');
		
		//모바일 클릭시 이동
		mobile_pd_nav_btn.addEventListener('click', function(event){
			const mobile_product_description = document.getElementById('mobile_product_description');
			const mobile_pd_absoluteOffset = window.pageYOffset + mobile_product_description.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:mobile_pd_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		mobile_sp_nav_btn.addEventListener('click', function(event){
			const mobile_service_process = document.getElementById('mobile_service_process');
			const mobile_sp_absoluteOffset = window.pageYOffset + mobile_service_process.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:mobile_sp_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		mobile_di_nav_btn.addEventListener('click', function(event){
			const mobile_tile_detail_info = document.getElementById('mobile_tile_detail_info');
			const mobile_di_absoluteOffset = window.pageYOffset + mobile_tile_detail_info.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:mobile_di_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
		mobile_mdPick_nav_btn.addEventListener('click', function(event){
			const mobile_mdPick_slide = document.getElementById('mobile_mdPick_slide');
			const mobile_mdPick_absoluteOffset = window.pageYOffset + mobile_mdPick_slide.getBoundingClientRect().top;
			$("html, body").animate({scrollTop:mobile_mdPick_absoluteOffset-190}, 0); // 190 = 네비바height + 해당 요소의 top margin + 가이드북 기준 여백
			event.stopPropagation();
		});
		
	});

	
	//PC
	document.addEventListener('scroll', function(){
		let scltop = window.document.documentElement.scrollTop;
		let contents = document.getElementsByClassName('scroll');
		let nav = document.getElementsByClassName('top_nav_btn');
		
		[].forEach.call(contents, function(item, index, array){
			let target = contents[index];
			let	targetTop = target.getBoundingClientRect().top+window.pageYOffset;
			
			if(targetTop <= scltop + 200) {
				if(index > 0) {
					nav[index-1].classList.remove("current");
				} else {
					nav[index+1].classList.remove("current");
					nav[index+2].classList.remove("current");
					nav[index+3].classList.remove("current");
				}
				nav[index].classList.add("current");
			}
			
			if(!(200 <= scltop))
				nav[index].classList.remove("current");
				
		});
		
	});
	
	// 768 size
	document.addEventListener('scroll', function(){
		let scltop = window.document.documentElement.scrollTop;
		let contents = document.getElementsByClassName('scroll');
		let nav = document.getElementsByClassName('fixed_top_nav_btn');
		
		[].forEach.call(contents, function(item, index, array){
			let target = contents[index];
			let	targetTop = target.getBoundingClientRect().top+window.pageYOffset;
			
			if(targetTop <= scltop + 200) {
				if(index > 0) {
					nav[index-1].classList.remove("current");
				} else {
					nav[index+1].classList.remove("current");
					nav[index+2].classList.remove("current");
					nav[index+3].classList.remove("current");
				}
				nav[index].classList.add("current");
			}
			
			if(!(200 <= scltop))
				nav[index].classList.remove("current");
				
		});
		
	});
	
	
		
	// 모바일
	document.addEventListener('scroll', function(){
		let mobile_scltop = window.document.documentElement.scrollTop;
		let mobile_contents = document.getElementsByClassName('mobile_scroll');
		let mobilenav = document.getElementsByClassName('mobile_top_nav_btn');
		
		[].forEach.call(mobile_contents, function(item, index, array){
			let target = mobile_contents[index];
			let	targetTop = target.getBoundingClientRect().top+window.pageYOffset;
			
			if(targetTop <= mobile_scltop + 200) {
				if(index > 0) {
					mobilenav[index-1].classList.remove("current");
				} else {
					mobilenav[index+1].classList.remove("current");
					mobilenav[index+2].classList.remove("current");
					mobilenav[index+3].classList.remove("current");
				}
				mobilenav[index].classList.add("current");
			}
			
			if(!(200 <= mobile_scltop))
				mobilenav[index].classList.remove("current");
				
		});
	});	
		
});

$(document).ready(function(){
	
	/*let headerDiv = $("._header_bg");
	
	headerDiv.css("height", "50px");*/
	
	$('[data-toggle="tooltip"]').tooltip();
	
	// 공유버튼 이미지 변경
	$(document).on("mouseover", ".share_btn", function(){
		$("#share_btn_img").attr("src", "/assets/resources/icon/contents/detail/ic_btn_share_s.svg");
		$(".mdSize_share_btn_img").attr("src", "/assets/resources/icon/contents/detail/ic_btn_share_s.svg");
	});
	$(document).on("mouseout", ".share_btn", function(){
		$("#share_btn_img").attr("src", "/assets/resources/icon/contents/detail/ic_btn_share_n.svg");
		$(".mdSize_share_btn_img").attr("src", "/assets/resources/icon/contents/detail/ic_btn_share_n.svg");
	});

	
	$(document).on("mouseover", ".mobile_share_btn", function(){
		$(".mobile_share_btn_img").attr("src", "/assets/resources/icon/contents/detail/ic_btn_share_s.svg");
	});
	$(document).on("mouseout", ".mobile_share_btn", function(){
		$(".mobile_share_btn_img").attr("src", "/assets/resources/icon/contents/detail/ic_btn_share_n.svg");
	});
	
	//주소복사
	$(".share_btn").on("click", function(){
		
		getEncodeUrl(url);
	});
	
	$(".mdSize_share_btn").on("click", function(){
		
		getEncodeUrl(url);
	});
	
	$(".mobile_share_btn").on("click", function(){
		
		getEncodeUrl(url);
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
			$("#contactBtn").removeAttr("disabled");
		else
			$('#contactBtn').attr('disabled',true);
			
	});

	// 네비바 클릭시 css 효과 적용
	$('ul.tabs li').click(function(){
		//var tab_id = $(this).attr('data-tab');
		$('ul.tabs li').removeClass('current');
		//$('.tab-content').removeClass('current');
		$(this).addClass('current');
		
	})
	
	// 모바일 네비바
	$('ul.mobile_tabs li').click(function(){
		//var tab_id = $(this).attr('data-tab');
		$('ul.mobile_tabs li').removeClass('current');
		//$('.tab-content').removeClass('current');
		$(this).addClass('current');
		
	})
	
	function nav_controller() {
		
		const imgWapper = $("._tile_img_wapper"); // 최상단 메인 이미지
		const md_imgWapper = $("._tile_img_wapper_md"); // 최상단 메인 이미지
		const mobile_imgWapper = $(".mobile_tile_img_wapper"); // 최상단 메인 이미지
		
		if(imgWapper.css('display') == 'block') {
			if(checkVisible(imgWapper)) {
				$(".detail_nav").css("display", "block");
				$(".detail_nav_bg").css("display", "none");
			} else {
				$(".detail_nav_bg").css("display", "block");
				$(".detail_nav").css("display", "none");
			}
		}
		
		
		if(md_imgWapper.css('display') == 'block') {
			if(checkVisible(md_imgWapper)) {
				$(".detail_nav").css("display", "block");
				$(".detail_nav_bg").css("display", "none");
			} else {
				$(".detail_nav_bg").css("display", "block");
				$(".detail_nav").css("display", "none");
			}
			
		}
		
	}
	nav_controller();

	// 화면의 크기 변경 및 스크롤 이벤트 바인딩
	// viewport 크기 900를 초과할 때만 상단 GNB 메뉴 BG 적용
	$(window).on('resize scroll', function(){
		nav_controller();
	});
	
	// PC환경 시공안내 리스트 오픈 버튼
	$("#exchanges_Refunds_list_open_btn").on('click', function(){
		if($("#exchanges_Refunds_text_box").css('display') == 'none') {
			$("#exchanges_Refunds_text_box").css("display", "block");
			$("#exchanges_Refunds_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_close_n@2x.png")
		} else {
			$("#exchanges_Refunds_text_box").css("display", "none");
			$("#exchanges_Refunds_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_open_n@2x.png")
			
		}
	});
	
	$("#AS_list_open_btn").on('click', function(){
		if($("#AS_text_box").css('display') == 'none') {
			$("#AS_text_box").css("display", "block");
			$("#AS_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_close_n@2x.png")
		} else {
			$("#AS_text_box").css("display", "none");
			$("#AS_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_open_n@2x.png")
			
		}
	});
	
	// 모바일 환경
	$("#mobile_exchanges_Refunds_list_open_btn").on('click', function(){
		if($("#mobile_exchanges_Refunds_text_box").css('display') == 'none') {
			$("#mobile_exchanges_Refunds_text_box").css("display", "block");
			$("#mobile_exchanges_Refunds_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_close_n@2x.png")
		} else {
			$("#mobile_exchanges_Refunds_text_box").css("display", "none");
			$("#mobile_exchanges_Refunds_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_open_n@2x.png")
			
		}
	});
	
	$("#mobile_AS_list_open_btn").on('click', function(){
		if($("#mobile_AS_text_box").css('display') == 'none') {
			$("#mobile_AS_text_box").css("display", "block");
			$("#mobile_AS_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_close_n@2x.png")
		} else {
			$("#mobile_AS_text_box").css("display", "none");
			$("#mobile_AS_list_open_btn").attr("src", "/assets/resources/icon/contents/detail/btn_list_open_n@2x.png")
			
		}
	});
	
});
