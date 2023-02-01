
$(function(){
	
	//slick 옵션 설정
	$('.slide-box').slick({
		prevArrow: $('.prev'), // 이전 버튼
		nextArrow: $('.next'), // 다음 버튼
		slide: 'li', // 슬라이드 기준
		adaptiveHeight:true, // 높이 자동 조정
		draggable:false, //마우스 드래그로 페이지전환 옵션
		slidesToShow : 1, //한 화면에 보여질 컨텐츠 개수
		infinite:false, //무한 반복 옵션
		swipe:false, //모바일 스와이프 옵션
		fade: true, //fade효과
		speed: 200, //slide 속도
	}).on("afterChange", function(){
		//$("#destroy1")
		//$("#destroy4")
	})
	
	//타일 상세보기 캐러셀 옵션
	$('.carousel').carousel({
		interval: false, //자동 순환 옵션
		wrap : false //캐러셀이 계속 순환해야 하는지 아니면 정지해야 하는지 여부
	});
	$("#orderForm").show();
	//$(".button_box").hide(); //첫 페이지에서 버튼박스 숨기기
	//$(".progress").hide(); //progress bar 숨기기

	disable_next_button(); //다음버튼 비활성화
	const startButton = document.querySelector('.start_button'); // 시작하기 버튼
	const prevButton = document.querySelector('.prev_button'); // 이전 버튼
	const nextButton = document.querySelector('.next_button'); // 다음 버튼
	startButton.addEventListener('click', estimate_start)
	prevButton.addEventListener('click', prev_check)
	nextButton.addEventListener('click', next_check)
	
	// 타일 선택 페이지
	const viewAllText = document.getElementById('viewAllText'); //전체 상품 더보기 텍스트
	const tileAllList = document.getElementById('tileAllList'); // 전체 타일 텍스트
	const tileCard = document.querySelectorAll('._tile_card'); //타일 카드
	
	// 건물 종류 선택 페이지
	const buildingRadio = document.querySelectorAll('.building_radio_input'); //라디오버튼 input 그룹
	const buildingRadioLabel = document.querySelectorAll('.building_radio'); //라디오버튼 label 그룹
	
	// 주거공간 첫번째 페이지
	const livingFloor = document.getElementById('living'); //거실 바닥 체크박스
	const kitchenFloor = document.getElementById('kitchen'); //주방 바닥 체크박스
	const roomFloor = document.getElementById('room'); //방 바닥 체크박스
	const destroyLiving = document.getElementById('destroyLiving'); //철거할 기존 바닥재(거실바닥) 드롭다운
	const destroyKitchen = document.getElementById('destroyKitchen'); //철거할 기존 바닥재(주방바닥) 드롭다운
	const destroyRoom = document.getElementById('destroyRoom'); //철거할 기존 바닥재(방바닥) 드롭다운
	const expandLiving = document.getElementById('expandLiving'); //거실 베란다 확장됨 체크박스
	const expandKitchen = document.getElementById('expandKitchen'); //주방 베란다 확장됨 체크박스
	const expandRoom = document.getElementById('expandRoom'); //방 베란다 확장됨 체크박스
	const destroyGroup = document.querySelectorAll('.destroy_group'); //기존 바닥재 철거 여부 라디오버튼 그룹(input)
	const destroyGroupLabel = document.querySelectorAll('.destroy_group_label'); //기존 바닥재 철거 여부 라디오버튼 그룹(label)
	const arrangeCheckbox = document.querySelectorAll('.arrange_checkbox'); // 체크박스 필수항목 그룹
	const destroyLivingText = document.getElementById('destroyLivingText'); //철거할 기존 바닥재 거실바닥 텍스트
	const destroyKitchenText = document.getElementById('destroyKitchenText'); //철거할 기존 바닥재 주방바닥 텍스트
	const destroyRoomText = document.getElementById('destroyRoomText'); //철거할 기존 바닥재 방바닥 텍스트
	
	//주거공간 두번째 페이지
	//공급 면적
	const changeSupM = document.getElementById('changeSupM'); //제곱미터 변환버튼
	const changeSupA = document.getElementById('changeSupA'); //평 변환버튼
	const areaSupNum = document.getElementById('areaSupNum'); //input 텍스트
	const areaSupDefault = document.getElementById('areaSupDefault'); //변환될 단위 텍스트
	const areaSup = document.getElementById('areaSup'); //변환될 단위 텍스트
	const acreageSupDefault = document.getElementById('acreageSupDefault'); //변환된 단위 텍스트
	const acreageSup = document.getElementById('acreageSup'); //변환된 단위 텍스트
	const acreageSupNum = document.getElementById('acreageSupNum'); //변환된 숫자 텍스트
	const supTextVal = document.getElementById('supTextVal'); // 공급 면적 (필수) 텍스트
	//전용 면적
	const changeDedM = document.getElementById('changeDedM'); //제곱미터 변환버튼
	const changeDedA = document.getElementById('changeDedA'); //평 변환버튼
	const areaDedNum = document.getElementById('areaDedNum'); //input 텍스트
	const areaDedDefault = document.getElementById('areaDedDefault');	//변환될 단위 텍스트
	const areaDed = document.getElementById('areaDed');	//변환될 단위 텍스트
	const acreageDedDefault = document.getElementById('acreageDedDefault'); //변환된 단위 텍스트
	const acreageDed = document.getElementById('acreageDed'); //변환된 단위 텍스트
	const acreageDedNum = document.getElementById('acreageDedNum'); //변환된 숫자 텍스트
	//시공 면적
	const changeConsM = document.getElementById('changeConsM'); //제곱미터 변환버튼
	const changeConsA = document.getElementById('changeConsA'); //평 변환버튼
	const areaConsNum = document.getElementById('areaConsNum'); //input 텍스트
	const areaCons = document.getElementById('areaCons'); //변환될 단위 텍스트
	const areaConsDefault = document.getElementById('areaConsDefault'); //변환된 단위 텍스트
	const acreageCons = document.getElementById('acreageCons'); //변환된 단위 텍스트
	const acreageConsDefault = document.getElementById('acreageConsDefault'); //변환될 단위 텍스트
	const acreageConsNum = document.getElementById('acreageConsNum'); //변환된 숫자 텍스트
	
	const cntRoom = document.getElementById('cntRoom'); //방 개수 selectbox
	const cntBath = document.getElementById('cntBath'); //욕실 개수 selectbox
	const moldingGroup = document.querySelectorAll('.molding_group'); //몰딩여부 radio그룹(input)
	const moldingGroupLabel = document.querySelectorAll('.molding_group_label'); //몰딩여부 radio그룹(label)
	const stateGroup = document.querySelectorAll('.state_group'); //현재 공간 상황 radio그룹(input)
	const stateGroupLabel = document.querySelectorAll('.state_group_label'); //현재 공간 상황 radio그룹(label)
	const elevatorGroup = document.querySelectorAll('.elevator_group'); //엘리베이터 유무 radio그룹(label)
	const elevatorGroupLabel = document.querySelectorAll('.elevator_group_label'); //엘리베이터 유무 radio그룹(label)
	
	//상업공간 첫번째 페이지
	const spaceGroup = document.querySelectorAll('.space_group'); //공간 선택 radio그룹(input)
	const spaceGroupLabel = document.querySelectorAll('.space_group_label'); //공간 선택 radio그룹(label)
	const destroyGroupCommerce = document.querySelectorAll('.destroy_group_commerce');	//상업 기존 바닥재 철거 여부 radio그룹(input)
	const destroyCommerceLabel = document.querySelectorAll('.destroy_commerce_label');	//상업 기존 바닥재 철거 여부 radio그룹(label)
	const floorGroup = document.querySelectorAll('.floor_group'); //기존 바닥재 종류 선택 radio버튼 그룹(input)
	const floorGroupLabel = document.querySelectorAll('.floor_group_label'); //기존 바닥재 종류 선택 radio버튼 그룹(label)
	
	//상업공간 두번째 페이지
	//전용 면적 (상업)
	const changeDedMCm = document.getElementById('changeDedMCm'); //제곱미터 변환버튼
	const changeDedACm = document.getElementById('changeDedACm'); //평 변환버튼
	const areaDedNumCm = document.getElementById('areaDedNumCm'); //input 텍스트
	const areaDedCmDefault = document.getElementById('areaDedCmDefault'); //변환될 단위 텍스트
	const areaDedCm = document.getElementById('areaDedCm'); //변환될 단위 텍스트
	const acreageDedCmDefault = document.getElementById('acreageDedCmDefault'); //변환된 단위 텍스트
	const acreageDedCm = document.getElementById('acreageDedCm'); //변환된 단위 텍스트
	const acreageDedNumCm = document.getElementById('acreageDedNumCm'); //변환된 숫자 텍스트
	//시공 면적 (상업)
	const changeConsMCm = document.getElementById('changeConsMCm'); //제곱미터 변환버튼
	const changeConsACm = document.getElementById('changeConsACm'); //평 변환버튼
	const areaConsNumCm = document.getElementById('areaConsNumCm'); //input 텍스트
	const areaConsCmDefault = document.getElementById('areaConsCmDefault'); //변환될 단위 텍스트
	const areaConsCm = document.getElementById('areaConsCm'); //변환될 단위 텍스트
	const acreageConsCmDefault = document.getElementById('acreageConsCmDefault'); //변환된 단위 텍스트
	const acreageConsCm = document.getElementById('acreageConsCm'); //변환된 단위 텍스트
	const acreageConsNumCm = document.getElementById('acreageConsNumCm'); //변환된 숫자 텍스트

	const moldingGroupCm = document.querySelectorAll('.molding_group_commerce'); //몰딩여부 radio그룹(input)
	const moldingGroupLabelCm = document.querySelectorAll('.molding_commerce_label'); //몰딩여부 radio그룹(label)
	const stateGroupCm = document.querySelectorAll('.state_group_commerce'); //현재 공간 상황 radio그룹(input)
	const stateGroupLabelCm = document.querySelectorAll('.state_commerce_label'); //현재 공간 상황 radio그룹(label)
	const elevatorGroupCm = document.querySelectorAll('.elevator_group_commerce'); //엘리베이터 유무 radio그룹(label)
	const elevatorGroupLabelCm = document.querySelectorAll('.elevator_commerce_label'); //엘리베이터 유무 radio그룹(label)

	//시공 희망일 페이지
	const desireGroup = document.querySelectorAll('.desire_group'); //시공 희망일 radio그룹(input)
	const desireGroupLabel = document.querySelectorAll('.desire_group_label'); //시공 희망일 radio그룹(label)
	
	//견적서 미리보기 페이지
	const customerName = document.getElementById('customerName'); //고객이름 텍스트
	const customerPhone = document.getElementById('customerPhone'); //연락처 텍스트
	const privacyCheck = document.getElementById('privacyCheck'); //개인정보 수집동의 체크박스(input)
	const privacyCheckLabel = document.getElementById('privacyCheckLabel'); //개인정보 수집동의 체크박스(label)
	const customerFunnel = document.getElementById('customerFunnel'); //유입경로 드롭다운
	const estimateAdd = document.getElementById('estimateAdd'); //견적서 추가
	
	
	
	
	//전체 드롭다운 박스 항목 선택시 검정색 글씨 적용
	const dropdownBox = document.querySelectorAll('.dropdown_box');
	dropdownBox.forEach((box)=>{
		box.addEventListener('change',function(){
			box.style.color = '#000'
		});
	});
	
	//타일선택 페이지
	viewAllText.addEventListener('click',function(){ //타일 전체보기 버튼
		id_display_show(tileAllList); //타일 전체리스트를 show
		id_display_none(viewAllText); //타일 전체보기 텍스트 display none
		auto_height(); //높이 재조정
	});
	
	$(document).on("click", ".view_detail", function(){ // 상세보기 버튼 클릭 시 타일 현재/전체 인덱스
 		var modalNo = $(this).data('id'); //상세보기의 id값을 통해 타일번호를 알아낸다
		var totalItems = $('.modal'+ modalNo).length; //총 이미지 갯수
		var currentIndex = $('.modal'+modalNo+'.active').index()+1; //현재 인덱스
		$('#carouselNum'+modalNo).html(currentIndex + '/' + totalItems); //현재인덱스 / 전체인덱스
		$('#carousel'+modalNo).on('slid.bs.carousel', function() { //캐러셀이 슬라이드 전환을 완료 후 시작
			currentIndex = $('.modal'+modalNo+'.active').index()+1; //현재 인덱스 다시 계산
		  	$('#carouselNum'+modalNo).html(currentIndex + '/' + totalItems);
		});
	});
	
	//건물종류 선택페이지 유효성 체크
	buildingRadio.forEach((building)=>{
		building.addEventListener('change',function(){
			for(var i of buildingRadioLabel){
				i.removeAttribute("style") //라디오버튼 전체 element style(빨간색 버튼) 제거
			}
			valid_hide(0); // 필수 입력 항목입니다 텍스트 숨기기
			able_next_button(); //다음버튼 활성화
			auto_height(); //높이 재조정
		});
	});
	
	// 주거공간 첫번째페이지 선택 유효성체크
	kitchenFloor.addEventListener('change',function(){ //주방바닥 체크박스
		dropdown_disabled(destroyKitchen,this,expandKitchen,destroyKitchenText);  //철거할 기존 바닥재 validation체크 함수
		for(var i of arrangeCheckbox){
			i.removeAttribute("style")
		}
		if(residential_question_check()){ //주거공간 첫번째 인덱스 전체 validation 체크 후 버튼 활성화/비활성화
			able_next_button();
		} else{
			disable_next_button();
		}
		valid_hide(1); // 필수 입력 항목입니다 텍스트 숨기기
		valid_small_hide(1); // 거실바닥(철거할 기존 바닥재 selectbox) 필수입력 텍스트 숨기기
		auto_height(); //높이 재조정
		
	});
	roomFloor.addEventListener('change',function(){ //방바닥 체크박스
		dropdown_disabled(destroyRoom,this,expandRoom,destroyRoomText);
		for(var i of arrangeCheckbox){
			i.removeAttribute("style")
		}
		if(residential_question_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		valid_hide(1);
		valid_small_hide(2);
		auto_height();
	});
	
	destroyGroup.forEach((destroy)=>{
		destroy.addEventListener('change',function(){
			for(var i of destroyGroupLabel){
				i.removeAttribute("style")
			}
			valid_hide(2);
			auto_height();
			
			if(destroyGroup[0].checked){ //와플에서 철거 예정 선택시
				if(livingFloor.checked){ //거실 바닥 선택시
					destroyLiving.disabled=false; //철거할 기존바닥재 거실바닥 활성화
					destroyLivingText.style.opacity="1"; //투명도 활성화로 변경
					if(destroyLiving.value == ""){ //selectbox 항목을 선택하지 않았다면 
						disable_next_button(); //다음버튼 비활성화
					}
				}
				if(kitchenFloor.checked){
					destroyKitchen.disabled=false;
					destroyKitchenText.style.opacity="1";
					if(destroyKitchen.value == ""){
						disable_next_button();
					}
				}
				if(roomFloor.checked){
					destroyRoom.disabled=false;
					destroyRoomText.style.opacity="1";
					if(destroyRoom.value == ""){
						disable_next_button();
					}
				}
			} else{ //와플에서 철거하지 않을 때
				destroyLiving.disabled=true; //철거할 기존 바닥재 전체를 disabled
				destroyKitchen.disabled=true;
				destroyRoom.disabled=true;
				destroyLivingText.style.opacity="0.4"; //투명도 비활성화
				destroyKitchenText.style.opacity="0.4";
				destroyRoomText.style.opacity="0.4";
				border_color_default(destroyLiving); //bordercolor default설정
				border_color_default(destroyKitchen);
				border_color_default(destroyRoom);
				valid_small_hide(0); //필수입력 텍스트 숨김
				valid_small_hide(1);
				valid_small_hide(2);
				auto_height();
			}
			if(residential_question_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
		});
	});
	
	destroyLiving.addEventListener('change', function(){ //거실 바닥 드롭다운
		if(residential_question_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		border_color_default(destroyLiving);
		valid_small_hide(0);
		auto_height();
	});
	
	destroyKitchen.addEventListener('change', function(){ //주방 바닥 드롭다운
		if(residential_question_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		border_color_default(destroyKitchen);
		valid_small_hide(1);
		auto_height();
		
	});
	
	destroyRoom.addEventListener('change', function(){ //방 바닥 드롭다운
		if(residential_question_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		border_color_default(destroyRoom);
		valid_small_hide(2);
		auto_height();
	});
	
	
	//주거공간 두번째 페이지 유효성 체크
	changeSupA.addEventListener('click', function(){ //평수 전환버튼 클릭시 이벤트
		id_display_show(acreageSup); //input박스 밖에 있는 '㎡'텍스트를 보여준다
		id_display_show(areaSup); //input박스 안에 있는 '평' 텍스트를 보여준다
		id_display_none(areaSupDefault); //input박스 밖에 있는 '평'텍스트를 숨긴다
		id_display_none(acreageSupDefault); //input박스 안에 있는 '㎡'텍스트를 숨긴다
		changeSupA.style.backgroundColor='black'; //평 전환버튼 색을 검정색으로
		changeSupM.style.backgroundColor='#A8A8A8'; //㎡ 전환버튼 색을 회색으로
		convertToSmButton(areaSupNum,acreageSupNum); //면적 전환 버튼
	});
	changeSupM.addEventListener('click', function(){
		id_display_show(acreageSupDefault);
		id_display_show(areaSupDefault);
		id_display_none(acreageSup);
		id_display_none(areaSup);
		changeSupA.style.backgroundColor='#A8A8A8';
		changeSupM.style.backgroundColor='black';
		convertToPyButton(areaSupNum,acreageSupNum);
	});
	changeDedA.addEventListener('click', function(){
		id_display_show(acreageDed);
		id_display_show(areaDed);
		id_display_none(areaDedDefault);
		id_display_none(acreageDedDefault);
		changeDedA.style.backgroundColor='black';
		changeDedM.style.backgroundColor='#A8A8A8';
		convertToSmButton(areaDedNum,acreageDedNum);
	});
	changeDedM.addEventListener('click', function(){
		id_display_show(acreageDedDefault);
		id_display_show(areaDedDefault);
		id_display_none(acreageDed);
		id_display_none(areaDed);
		changeDedA.style.backgroundColor='#A8A8A8';
		changeDedM.style.backgroundColor='black';
		convertToPyButton(areaDedNum,acreageDedNum);
	});
	changeConsA.addEventListener('click', function(){
		id_display_show(acreageCons);
		id_display_show(areaCons);
		id_display_none(areaConsDefault);
		id_display_none(acreageConsDefault);
		changeConsA.style.backgroundColor='black';
		changeConsM.style.backgroundColor='#A8A8A8';
		convertToSmButton(areaConsNum,acreageConsNum);
	});
	changeConsM.addEventListener('click', function(){
		id_display_show(areaConsDefault);
		id_display_show(acreageConsDefault);
		id_display_none(acreageCons);
		id_display_none(areaCons);
		changeConsA.style.backgroundColor='#A8A8A8';
		changeConsM.style.backgroundColor='black';
		convertToPyButton(areaConsNum,acreageConsNum);
	});
	
	 //면적 변환 input에 값을 입력할때 이벤트 
	$(document).on("change keyup keypress", "#areaSupNum", function(e){ //공급면적 input
		var val = isNumberKey(e,this); //입력 값 유효성 체크
		if(areaSup.style.display == 'block'){ //input text가 '평' 일때
			convertToSm(val,acreageSupNum); //평 -> ㎡ 전환
		} else{
			convertToPy(val,acreageSupNum); //㎡ -> 평 전환
		}
		if(residential_area_check()){ //주거공간 두번째 전체 유효성체크 
			able_next_button();
		} else{
			disable_next_button();
		}
		if(this.value == ""){
			this.value = 0;
		}
		if(supTextVal.style.display == 'block'){ //공급면적이 필수일때
			if(this.value != 0 || this.value != ""){ //공급면적 input값이 존재할때
				border_color_default(areaSupNum); //validation 경고창을 원래 input창으로 변경
				valid_small_hide(3);//필수항목 텍스트 숨김
			}
		}
		
	});
	
	$(document).on("change keyup keypress", "#areaDedNum", function(e){ //전용면적 input
		var val = isNumberKey(e,this);
		if(areaDed.style.display == 'block'){
			convertToSm(val,acreageDedNum);
		} else{
			convertToPy(val,acreageDedNum);
		}
		if(residential_area_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		if(this.value == ""){
			this.value = 0;
		}
		if(supTextVal.style.display != 'block'){
			if(this.value != 0 || this.value != ""){
				border_color_default(areaDedNum);
				valid_small_hide(4);			
			}
		}

	});
	
	$(document).on("change keyup keypress", "#areaConsNum", function(e){ //시공면적 input
		var val = isNumberKey(e,this);
		if(areaCons.style.display == 'block'){
			convertToSm(val,acreageConsNum);
		} else{
			convertToPy(val,acreageConsNum);
		}
		if(this.value == ""){
			this.value = 0;
		}
	});
	
	cntRoom.addEventListener('change', function(){ //방 개수 드롭다운
		if(residential_area_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		border_color_default(cntRoom);
		valid_small_hide(5);
		auto_height();
	});
	cntBath.addEventListener('change', function(){ //욕실 개수 드롭다운
		if(residential_area_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		border_color_default(cntBath);
		valid_small_hide(6);
		auto_height();
	});

	moldingGroup.forEach((molding)=>{ //몰딩여부 라디오버튼
		molding.addEventListener('change',function(){
			for(var i of moldingGroupLabel){
				i.removeAttribute("style")
			}
			valid_hide(3);
			if(residential_area_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	stateGroup.forEach((state)=>{ //현재 공간 상황 라디오버튼
		state.addEventListener('change',function(){
			for(var i of stateGroupLabel){
				i.removeAttribute("style")
			}
			valid_hide(4);
			if(residential_area_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	elevatorGroup.forEach((elevator)=>{ //엘리베이터 유무 라디오버튼
		elevator.addEventListener('change',function(){
			for(var i of elevatorGroupLabel){
				i.removeAttribute("style")
			}
			valid_hide(5);
			if(residential_area_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	
	//상업공간 첫번째 페이지 유효성 체크
	spaceGroup.forEach((space)=>{ //사무실 공간 선택 라디오버튼
		space.addEventListener('change',function(){
			for(var i of spaceGroupLabel){
				i.removeAttribute("style")
			}
			valid_hide(6);
			if(commerce_question_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	destroyGroupCommerce.forEach((destroyCommerce)=>{ //기존 바닥재 철거여부 라디오버튼
		destroyCommerce.addEventListener('change',function(){
			for(var i of destroyCommerceLabel){
				i.removeAttribute("style")
			}
			valid_hide(7);
			if(commerce_question_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	floorGroup.forEach((floor)=>{ //기존 바닥재의 종류 라디오버튼
		floor.addEventListener('change',function(){
			for(var i of floorGroupLabel){
				i.removeAttribute("style")
			}
			valid_hide(8);
			if(commerce_question_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	
	//상업공간 두번째 페이지 유효성 체크
	
	$(document).on("change keyup keypress", "#areaConsNumCm", function(e){ //시공면적 input
		var val = isNumberKey(e,this);
		if(areaConsCm.style.display == 'block'){
			convertToSm(val,acreageConsNumCm);
		} else{
			convertToPy(val,acreageConsNumCm);
		}
		if(this.value == ""){
			this.value = 0;
		}		
	});
	
	$(document).on("change keyup keypress", "#areaDedNumCm", function(e){//전용면적 input
		var val = isNumberKey(e,this);
		if(areaDedCm.style.display == 'block'){
			convertToSm(val,acreageDedNumCm);
		} else{
			convertToPy(val,acreageDedNumCm);
		}
		if(commerce_area_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		if(this.value == ""){
			this.value = 0;
		}
		if(this.value != 0 || this.value != ""){
			border_color_default(areaDedNumCm);
			valid_small_hide(7);			
		}
	});
	
	changeDedACm.addEventListener('click', function(){ //전용면적 평수 전환 버튼
		id_display_show(acreageDedCm);
		id_display_show(areaDedCm);
		id_display_none(areaDedCmDefault);
		id_display_none(acreageDedCmDefault);
		changeDedACm.style.backgroundColor='black';
		changeDedMCm.style.backgroundColor='#A8A8A8';
		convertToSmButton(areaDedNumCm,acreageDedNumCm);
	});
	changeDedMCm.addEventListener('click', function(){ //전용면적 ㎡ 전환 버튼
		id_display_show(areaDedCmDefault);
		id_display_show(acreageDedCmDefault);
		id_display_none(acreageDedCm);
		id_display_none(areaDedCm);
		changeDedACm.style.backgroundColor='#A8A8A8';
		changeDedMCm.style.backgroundColor='black';
		convertToPyButton(areaDedNumCm,acreageDedNumCm);
	});
	changeConsACm.addEventListener('click', function(){ //시공면적 평수 전환 버튼
		id_display_show(acreageConsCm);
		id_display_show(areaConsCm);
		id_display_none(areaConsCmDefault);
		id_display_none(acreageConsCmDefault);
		changeConsACm.style.backgroundColor='black';
		changeConsMCm.style.backgroundColor='#A8A8A8';
		convertToSmButton(areaConsNumCm,acreageConsNumCm);
	});
	changeConsMCm.addEventListener('click', function(){ //전용면적 ㎡ 전환 버튼
		id_display_none(acreageConsCm);
		id_display_none(areaConsCm);
		id_display_show(areaConsCmDefault);
		id_display_show(acreageConsCmDefault);
		changeConsACm.style.backgroundColor='#A8A8A8';
		changeConsMCm.style.backgroundColor='black';
		convertToPyButton(areaConsNumCm,acreageConsNumCm);
	});
	
	moldingGroupCm.forEach((moldingCm)=>{ //몰딩여부 라디오버튼
		moldingCm.addEventListener('change',function(){
			for(var i of moldingGroupLabelCm){
				i.removeAttribute("style")
			}
			valid_hide(9);
			if(commerce_area_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	stateGroupCm.forEach((stateCm)=>{ //현재 공간 상황 라디오버튼
		stateCm.addEventListener('change',function(){
			for(var i of stateGroupLabelCm){
				i.removeAttribute("style")
			}
			valid_hide(10);
			if(commerce_area_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	elevatorGroupCm.forEach((elevatorCm)=>{ //엘리베이터 유무 라디오버튼
		elevatorCm.addEventListener('change',function(){
			for(var i of elevatorGroupLabelCm){
				i.removeAttribute("style")
			}
			valid_hide(11);
			if(commerce_area_check()){
				able_next_button();
			} else{
				disable_next_button();
			}
			auto_height();
		});
	});
	
	//시공 희망일 페이지
	desireGroup.forEach((desire)=>{
		desire.addEventListener('change',function(){
			for(var i of desireGroupLabel){
				i.removeAttribute("style")
			}
			valid_hide(12);
			able_next_button();
			auto_height();
		});
	});
	
	
	//견적서 미리보기 페이지
	$(document).on("change keyup keydown", ".add_request_textarea", function(){
		let content = $(this).val();
	    // 글자수 세기
	   	if(content.length > 100){
			$('.letter_number').text(100 + '/100');
		} else {
	    	$('.letter_number').text(content.length + '/100');
	    }
	    // 글자수 제한
	    if (content.length > 100) {
	    	// 100자 부터는 타이핑 되지 않도록
	        $(this).val($(this).val().substring(0, 100));
	    };
	});
		
	$(document).on("change keyup keydown", "#customerName", function(){ //고객명 입력 input
		valid_small_hide(9);
		border_color_default(customerName);
		
		if(expect_estimate_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		auto_height();
	});
	
	$(document).on("change keyup keydown", "#customerPhone", function(){ //고객 휴대폰번호 입력 input
		valid_small_hide(10);
		border_color_default(customerPhone);
		autoHyphen(this);
		if(expect_estimate_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		auto_height();
	});
	
	privacyCheck.addEventListener('change',function(){ //개인정보 수집 동의 체크박스
		valid_hide(13);
		privacyCheckLabel.removeAttribute("style")
		if(expect_estimate_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		auto_height();
	});

	customerFunnel.addEventListener('change',function(){ //유입경로 selectbox
		valid_small_hide(11);
		border_color_default(customerFunnel);
		if(expect_estimate_check()){
			able_next_button();
		} else{
			disable_next_button();
		}
		auto_height();
	});
	

	for(var i of tileCard){ //전체 타일카드에 card_select함수 onclick설정
		i.setAttribute("onclick", "card_select(this)");
	}
	
	//견적서 추가 버튼
	estimateAdd.addEventListener('click',function(){
		const tileCard = document.querySelectorAll('._tile_card');
		$(".prev_button").hide(); //이전버튼 비활성화
		$(".next_button").width('100%'); //다음버튼 width 조정
		slickGoindex(i1);
		$("#nextButton").html("다음");
		disable_next_button();
		for(var i of tileCard){
			if(i.style.borderColor == "rgba(255, 93, 79, 0.2)"){
				able_next_button();
			}
		}
		progress_move(progress1);
	});
	
	
	
	
});


const i1 = 0;
const i2 = 1;
const i3 = 2;
const i4 = 3;
const i5 = 4;
const i6 = 5;
const i7 = 6;
const i8 = 7;
const i9 = 8;

var cnt = 1; //cnt = 1일때 현재 등록 견적서 0 견적서 추가기능 구현 시 견적서 하나 추가할때마다 +1

var mtNo1 = 0;
var mtNo2 = 0;
var mtNo3 = 0;

const progress0 = '0'
const progress1 = '14.2857'
const progress2	= '28.5714'
const progress3	= '42.8571'
const progress4	= '57.1428'
const progress5	= '71.4285'
const progress6	= '85.7142'
const progress7 = '100'

const __calSquareMeter = 3.305785; 
//var indexNum = [0,1,2,3,4,5,6,7,8,9]

//면적 input validation
function isNumberKey(evt,val) {
	val.value=val.value.replace(/[^0-9.]|(^0+)/g, "");

	var _pattern = /^(\d{1,3}([.]\d{0,3})?)?$/;
	var _value = event.srcElement.value;    
	if (!_pattern.test(_value)) {     
	event.srcElement.value = event.srcElement.value.substring(0,event.srcElement.value.length - 1);        
	event.srcElement.focus();
	}
	return event.srcElement.value;
}

//휴대폰번호 입력양식
const autoHyphen = (target) => {
 target.value = target.value
	.replace(/[^0-9]/g, '')
	.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3")
	.replace(/(\-{1,2})$/g, "");
}

//평수 변환
function convertToPy(smValue,acreage) {
	var convertToPy = Math.round(smValue / __calSquareMeter); // 소수점자리 반올림
	acreage.textContent=convertToPy;
}

//면적 변환
function convertToSm(value,acreage) {
	var convertToSm = value * __calSquareMeter;
	convertToSm = convertToSm.toFixed(3); //소수점 3자리까지 표현
	acreage.textContent=convertToSm;
}
//평수 변환 버튼
function convertToPyButton(val,acreage) {
	var smValue = val.value;
	var convertToPy = Math.round(smValue / __calSquareMeter); // 소수점자리 반올림
	acreage.textContent=convertToPy;
}

//면적 변환 버튼
function convertToSmButton(val,acreage) {
	var value = val.value;
	var convertToSm = value * __calSquareMeter;
	convertToSm = convertToSm.toFixed(3); //소수점 3자리까지 표현
	acreage.textContent=convertToSm;
}

//카드 선택 함수
function card_select(tile){
	const tileCard = document.querySelectorAll('._tile_card'); //전체 타일카드
	const cardValue = tile.lastChild.previousSibling.value; //파라미터의 value값 (mtNo)
	const card = document.querySelectorAll('.card'+cardValue); //card+mtNo로 이뤄진 class (mtname의 추천타일과 전체타일 모두 가져옴) 
	const tileSelect = document.getElementById('tileSelect'+cardValue); //타일 선택하기 버튼
	if(tile.style.borderColor != "rgba(255, 93, 79, 0.2)"){ //타일이 선택 되어있지 않은 경우
		for(var i of tileCard){
			if(i.style.opacity!="0.2"){
				i.removeAttribute("style") //다른 타일카드 선택을 초기화
			}
		}
		for(var i of card){
			i.style.border = "2px solid rgba(255, 93, 79, 0.2)"; //mtName에 맞는 추천타일과 전체타일 카드를 선택
		}
		tileSelect.removeAttribute("style"); //const card에 타일 선택하기 버튼도 포함되어있으므로 스타일제거
		able_next_button();
	} else{ //타일이 선택되어 있는경우
		for(var i of tileCard){ 
			if(i.style.opacity!="0.2"){
				i.removeAttribute("style")
			}
		}
		disable_next_button()
	}
}

//카드 선택하기 버튼 함수
function tile_select_button(tile){
	const tileCard = document.querySelectorAll('._tile_card');
	const card = document.querySelectorAll('.card'+tile);
	const tileSelect = document.getElementById('tileSelect'+tile);
	for(var i of tileCard){
		if(i.style.opacity!="0.2"){
			i.removeAttribute("style")
		}
	}
	for(var i of card){
		i.style.border = "2px solid rgba(255, 93, 79, 0.2)";
	}
	tileSelect.removeAttribute("style");
	able_next_button();
}

//체크박스 체크시 드롭다운 disabled여부 처리
function dropdown_disabled(destroy,floor,expand,text){ 
	const isDestroy = document.getElementById('destroy1'); //와플에서 철거예정 체크박스
	if(floor.checked) { //바닥 체크박스 체크시
		expand.disabled=false; //확장 체크박스 활성화
		if(isDestroy.checked){ //와플에서 철거예정 체크상태일시
			destroy.disabled=false; //바닥철거 드롭다운 활성화
			text.style.opacity="1"; 
		}
	} else{
			destroy.disabled=true; //체크박스 체크가 안되어 있을시
			expand.disabled=true; //확장 체크박스 비활성화
			text.style.opacity="0.4";
			border_color_default(destroy);
		}
}

//체크박스 유효성 검사
function chkbox_check(chkbox){ 
	var check = 0;
	for(var i of chkbox){
		if(i.checked == true){
			check += 1;
		}
	}
	return check;
}

//라디오버튼 유효성 검사
function radio_check(radioGroup){ 
	var groupLength = $('input[name='+radioGroup+']:checked').length
	return groupLength;
}

//주거공간 첫번째 전체 validation 체크
function residential_question_check(){ 
	var arrangeGroup = document.getElementsByClassName('arrange_group');
	var arrangeCheck = chkbox_check(arrangeGroup);
	const destroyLiving = document.getElementById('destroyLiving');
	const destroyKitchen = document.getElementById('destroyKitchen');
	const destroyRoom = document.getElementById('destroyRoom');
	
	if(arrangeCheck == 0 || radio_check('isDestroy') == 0 
	|| destroyLiving.value == "" && destroyLiving.disabled == false
	|| destroyKitchen.value == "" && destroyKitchen.disabled == false
	|| destroyRoom.value == "" && destroyRoom.disabled == false){
		return false;
	} else{
		return true;
	}
}

//주거공간 두번째 전체 validation 체크
function residential_area_check(){
	const supText = document.getElementById('supText');
	const areaDedNum = document.getElementById('areaDedNum');
	const areaSupNum = document.getElementById('areaSupNum');
	const cntRoom = document.getElementById('cntRoom');
	const cntBath = document.getElementById('cntBath');
	if(supText.style.display=='block'){
		//공급 면적이 선택일경우 전체 validation 체크
		if(areaDedNum.value=="0" || areaDedNum.value=="" || cntRoom.value=="" || cntBath.value==""
		|| radio_check('isMolding')==0 || radio_check('stateSpace')==0 || radio_check('isElevator')==0){
			return false;
		} else{
			return true;
		}
	} else{
		//공급 면적이 필수일경우 전체 validation 체크
		if(areaSupNum.value=="0" || areaSupNum.value=="" || cntRoom.value=="" || cntBath.value==""
		|| radio_check('isMolding')==0 || radio_check('stateSpace')==0 || radio_check('isElevator')==0){
			return false;
		} else{
			return true;
		}
	}
}

//상업공간 첫번쨰 전체 validation 체크
function commerce_question_check(){
	if($('input:radio[class=destroy_group_commerce]:checked').length == 0 || radio_check('spaceUse') == 0 || radio_check('commercialFloorMaterial') == 0){
		return false;
	} else{
		return true;
	}
}

//상업공간 두번쨰 전체 validation 체크
function commerce_area_check(){
	const areaDedNumCm = document.getElementById('areaDedNumCm');
	if(areaDedNumCm.value=="0" || areaDedNumCm.value=="" || $('input:radio[class=molding_group_commerce]:checked').length == 0 || radio_check('stateSpaceCommerce')==0 || $('input:radio[class=elevator_group_commerce]:checked').length == 0){
		return false;
	} else{
		return true;
	}
}

//시공 희망일 페이지 validation 체크
function desire_date_check(){
	if(radio_check('desireDate')==0){
		return false;
	} else{
		return true;
	}
}

//slick slider높이 수동 재조정
function auto_height(){
	$('.slide-box').slick('setPosition');
}

//견적서 미리보기 페이지 전체 validation체크
function expect_estimate_check(){
	const address = document.getElementById('address');
	const customerName = document.getElementById('customerName');
	const customerPhone = document.getElementById('customerPhone');
	const privacyCheck = document.getElementById('privacyCheck');
	const customerFunnel = document.getElementById('customerFunnel');
	
	if(address.value == "" || customerName.value == "" || customerPhone.value == "" ||
	privacyCheck.checked == false || customerFunnel.value == ""){
		return false;
	} else{
		return true;
	}
}

//뒤로 버튼
function prev_check(){
	document.documentElement.scrollTop = 0;
	var currentSlide = $('.slide-box').slick('slickCurrentSlide'); //현재 슬라이드 index 체크
	var buildingRadioValue = $("input[name=typeHouse]:checked").val(); //체크박스 value 체크
	const startPage = document.getElementById('startPage');
	const slickSlide = document.getElementById('slickSlide');
	const tileCard = document.querySelectorAll('._tile_card');
	if(currentSlide == i1){ 
		if(cnt ==1){
			$(".button_box").hide();//시작페이지면 버튼박스 숨김
			$(".progress").hide(); //progress bar 숨기기
			progress_move(progress0);
			startPage.style.display = 'block';
			slickSlide.style.display = 'none';
		} else {
			progress_move(progress6);
			$("#nextButton").html("견적서 제출");
			if(expect_estimate_check()==false){
				disable_next_button();
			}
			slickGoindex('7');
			return;
		}
	}
	if(currentSlide == i2){
		$(".prev_button").hide(); //이전버튼 비활성화
		$(".next_button").width('100%'); //다음버튼 width 조정
		disable_next_button();
		for(var i of tileCard){
			if(i.style.borderColor == "rgba(255, 93, 79, 0.2)"){
				able_next_button();
			}
		}
		progress_move(progress1);
	}
	if(currentSlide == i3){
		progress_move(progress2);
		able_next_button();
	}
	if(currentSlide == i4){
		progress_move(progress3);
		able_next_button();
	}
	if(currentSlide == i5){
		$('.slide-box').slick('slickGoTo', i2);
		progress_move(progress2);
		able_next_button();
		return;
	}
	if(currentSlide == i6){
		progress_move(progress3);
	}
	if(currentSlide == i7){
		if(buildingRadioValue != 6){
			$('.slide-box').slick('slickGoTo', i4);
		} else{
			$('.slide-box').slick('slickPrev');
		}
		progress_move(progress4);
		able_next_button();
		return;
	}
	if(currentSlide == i8){
		progress_move(progress5);
		able_next_button();
		$("#nextButton").html("다음");
	}
	$('.slide-box').slick('slickPrev');
}

//다음 버튼
function next_check(){
	const nextButton = document.querySelector('.next_button');
	var isDisabled = getComputedStyle(nextButton).backgroundColor== 'rgb(' + parseInt('35') + ', ' + parseInt('69') + ', ' + parseInt('64') + ')';
	if(isDisabled){
		document.documentElement.scrollTop = 0; //다음버튼 able상태일때만 스크롤 최상단
	}
	var currentSlide = $('.slide-box').slick('slickCurrentSlide'); //현재 슬라이드 index 체크
	var buildingRadioCheck = $("input[name=typeHouse]:checked").length; //체크박스 몇개가 체크되었는지
	var buildingRadioValue = $("input[name=typeHouse]:checked").val(); //체크박스 value 체크
	
	const arrangeCheckbox = document.querySelectorAll('.arrange_checkbox');
	const arrangeGroup = document.querySelectorAll('.arrange_group');
	const destroyGroupLabel = document.querySelectorAll('.destroy_group_label');
	const moldingGroupLabel = document.querySelectorAll('.molding_group_label');
	const stateGroupLabel = document.querySelectorAll('.state_group_label');
	const elevatorGroupLabel = document.querySelectorAll('.elevator_group_label');
	const destroyLiving = document.getElementById('destroyLiving');
	const destroyKitchen = document.getElementById('destroyKitchen');
	const destroyRoom = document.getElementById('destroyRoom');
	const veranda = document.getElementById('veranda');
	const expandVeranda = document.querySelectorAll('.expand_veranda');
	const supTextVal = document.getElementById('supTextVal');
	const supText = document.getElementById('supText');
	const dedTextVal = document.getElementById('dedTextVal');
	const dedText = document.getElementById('dedText');
	const areaSupNum = document.getElementById('areaSupNum');
	const areaDedNum = document.getElementById('areaDedNum');
	const cntRoom = document.getElementById('cntRoom');
	const cntBath = document.getElementById('cntBath');
	const spaceGroupLabel = document.querySelectorAll('.space_group_label');	
	const destroyCommerceLabel = document.querySelectorAll('.destroy_commerce_label');	
	const floorGroupLabel = document.querySelectorAll('.floor_group_label');
	const areaDedNumCm = document.getElementById('areaDedNumCm');
	const moldingGroupLabelCm = document.querySelectorAll('.molding_commerce_label');
	const steateGroupLabelCm = document.querySelectorAll('.state_commerce_label');
	const elevatorGroupLabelCm = document.querySelectorAll('.elevator_commerce_label');
	const desireGroupLabel = document.querySelectorAll('.desire_group_label');
	const address = document.getElementById('address');
	const customerName = document.getElementById('customerName');
	const customerPhone = document.getElementById('customerPhone');
	const privacyCheck = document.getElementById('privacyCheck');
	const privacyCheckLabel = document.getElementById('privacyCheckLabel');
	const customerFunnel = document.getElementById('customerFunnel');
	
	
	
	
	if(currentSlide==i1){
		if(!isDisabled){ 
			return;	
		}
		if(cnt==1){		
			progress_move(progress2);
			if(buildingRadioCheck ==0){
				disable_next_button();
			}
		} /*else{
			slickGoindex(i8);
			progress_move(progress6);
			$("#nextButton").html("견적서 제출");
			if(expect_estimate_check()==false){
				disable_next_button();
			}
			get_estimate_calc();
			auto_height();
		}*/
		$(".prev_button").removeAttr("style");
		$(".next_button").removeAttr("style");
	}
	
	if(currentSlide==i2){
		if(!isDisabled){ 
			const buildingRadio = document.querySelectorAll('.building_radio');
			for(var i of buildingRadio){
				radio_alert(i);
			}
			valid_show(0);
			valid_focus('typeHouseFocus'); 
			auto_height();
			return;
		} 
		if(buildingRadioValue==6){ //상업공간을 체크했을때
			slickGoindex(i5);
			if(commerce_question_check() == false){
				disable_next_button();				
			}
			progress_move(progress3);
			return;
		} else{
			if(residential_question_check() == false){
				disable_next_button();
			}
		}
		progress_move(progress3);
	}
	if(currentSlide==i3){
		if(!isDisabled){
			if(chkbox_check(arrangeGroup)==0){
				for(var i of arrangeCheckbox){
					checkbox_alert(i);
				}
				valid_show(1);
				valid_focus('arrangeFocus'); 
			}
			if(radio_check('isDestroy') == 0){
				for(var i of destroyGroupLabel){
					radio_alert(i);
				}
				valid_show(2);
				if(chkbox_check(arrangeGroup)!=0){
					valid_focus('destroyFocus');
				}
			}
			if(destroyLiving.value == "" && destroyLiving.disabled == false){
				border_color_red(destroyLiving);
				valid_small_show(0);
				valid_focus('destroyLivingText');
			}
			if(destroyKitchen.value == "" && destroyKitchen.disabled == false){
				border_color_red(destroyKitchen);
				valid_small_show(1);
				valid_focus('destroyKitchenText');
			}
			if(destroyRoom.value == "" && destroyRoom.disabled == false){
				border_color_red(destroyRoom);
				valid_small_show(2);
				valid_focus('destroyRoomText');
			}
			auto_height();
			return;
		}
		
		if(veranda.checked==false && chkbox_check(expandVeranda)==false){ //베란다 체크가 안되어 있을 시
			id_display_show(dedTextVal); //공급면적(필수)를 숨기고 공급면적(선택)을 보이게 한다.
			id_display_show(supText);	 //전용면적(선택)을 숨기고 전용면적(필수)를 보이게 한다.
			id_display_none(supTextVal);
			id_display_none(dedText);
			id_display_none(acreageDed);
			id_display_none(areaDed);
			id_display_none(acreageSup);
			id_display_none(areaSup);
			id_display_none(acreageCons);
			id_display_none(areaCons);
			border_color_default(areaSupNum);
			valid_small_hide(3);
		} else{ //베란다 체크가 되어 있을 시
			id_display_show(supTextVal); //공급면적(선택)를 숨기고 공급면적(필수)을 보이게 한다.
			id_display_show(dedText); //전용면적(필수)을 숨기고 전용면적(선택)를 보이게 한다.
			id_display_none(dedTextVal);
			id_display_none(supText);
			id_display_none(acreageDed);
			id_display_none(areaDed);
			id_display_none(acreageSup);
			id_display_none(areaSup);
			id_display_none(acreageCons);
			id_display_none(areaCons);
			border_color_default(areaDedNum);
			valid_small_hide(4);
		}
		if(residential_area_check()==false){
			disable_next_button();
		}
		progress_move(progress4);
	}
	if(currentSlide == i4){
			if(!isDisabled){
				if(supTextVal.style.display=='block'){
					if(areaSupNum.value=="0" || areaSupNum.value==""){
						border_color_red(areaSupNum);
						valid_small_show(3);
						valid_focus('supTextVal');
					}
				} else{
					if(areaDedNum.value=="0" || areaDedNum.value==""){
						border_color_red(areaDedNum);
						valid_small_show(4);
						valid_focus('dedTextVal');
					}
				}
				if(cntRoom.value == ""){
					border_color_red(cntRoom);
					valid_small_show(5);
					if(valid_small_show_check(3) && valid_small_show_check(4)){
						valid_focus('cntRoomFocus');
					}
				}
				if(cntBath.value == ""){
					border_color_red(cntBath);
					valid_small_show(6);
					if(valid_small_show_check(3) && valid_small_show_check(4)){
						valid_focus('cntRoomFocus');
					}
				}
				if(radio_check('isMolding') == 0){
					for(var i of moldingGroupLabel){
						radio_alert(i);
					}
					valid_show(3);
					if(valid_small_show_check(3) && valid_small_show_check(4) && valid_small_show_check(5) && valid_small_show_check(6)){
						valid_focus('moldingFocus');
					}
				}
				if(radio_check('stateSpace') == 0){
					for(var i of stateGroupLabel){
						radio_alert(i);
					}
					valid_show(4);
					if(valid_small_show_check(3) && valid_small_show_check(4) && valid_small_show_check(5) && valid_small_show_check(6) && valid_show_check(3)){
						valid_focus('stateSpaceFocus');
					}
					
				}
				if(radio_check('isElevator') == 0){
					for(var i of elevatorGroupLabel){
						radio_alert(i);
					}
					valid_show(5);
					if(valid_small_show_check(3) && valid_small_show_check(4) && valid_small_show_check(5) && valid_small_show_check(6) && valid_show_check(3) && valid_show_check(4)){
						valid_focus('elevatorFocus');
					}
					
				}
				auto_height();
				return;
				
			}
		progress_move(progress5);
		slickGoindex(i7);
		if(desire_date_check()==false){
			disable_next_button();
		}
		return;
	}
	if(currentSlide==i5){
		if(!isDisabled){
			if(radio_check('spaceUse') == 0){
				for(var i of spaceGroupLabel){
					radio_alert(i);
				}
				valid_show(6);
				valid_focus('spaceFocus');
	
			}
			if($('input:radio[class=destroy_group_commerce]:checked').length == 0){
				for(var i of destroyCommerceLabel){
					radio_alert(i);
				}
				valid_show(7);
				if(valid_show_check(6)){
					valid_focus('destroyCommerce');
				}
			}
			if(radio_check('commercialFloorMaterial') == 0){
				for(var i of floorGroupLabel){
					radio_alert(i);
				}
				valid_show(8);
				if(valid_show_check(6) && valid_show_check(7)){
					valid_focus('floorFocus');
				}
				
			}
			auto_height();			
			return;
		}
		if(commerce_area_check()==false){
			disable_next_button();
		}
		id_display_none(acreageDedCm);
		id_display_none(areaDedCm);
		id_display_none(acreageConsCm);
		id_display_none(areaConsCm);
		progress_move(progress4);
	}
	if(currentSlide==i6){
		if(!isDisabled){
				if(areaDedNumCm.value=="0" || areaDedNumCm.value==""){
					border_color_red(areaDedNumCm);
					valid_small_show(7);
					valid_focus('dedTextValCm');
				}

				if($('input:radio[class=molding_group_commerce]:checked').length == 0){
					for(var i of moldingGroupLabelCm){
						radio_alert(i);
					}
					valid_show(9);
					if(valid_small_show_check(7)){
						valid_focus('moldingFocusCm');
					}
				}
				if(radio_check('stateSpaceCommerce') == 0){
					for(var i of steateGroupLabelCm){
						radio_alert(i);
					}
					valid_show(10);
					if(valid_small_show_check(7) && valid_show_check(9)){
						valid_focus('stateSpaceFocusCm');
					}
				}
				if($('input:radio[class=elevator_group_commerce]:checked').length == 0){
					for(var i of elevatorGroupLabelCm){
						radio_alert(i);
					}
					valid_show(11);
					if(valid_small_show_check(7) && valid_show_check(9) && valid_show_check(10)){
						valid_focus('elevatorFocusCm');
					}
				}
				auto_height();
				return;
			}
		if(desire_date_check()==false){
			disable_next_button();
		}
		progress_move(progress5);
	}
	if(currentSlide==i7){
		if(!isDisabled){
			for(var i of desireGroupLabel){
				radio_alert(i);
			}
			valid_show(12);
			valid_focus('wishFocus'); 
			auto_height();
			return;
		} 
		auto_height()
		progress_move(progress6);
		$("#nextButton").html("견적서 제출");
		if(expect_estimate_check()==false){
			disable_next_button();
		}
		get_estimate_calc();
		get_area_value();
		
	}
	if(currentSlide==i8){
		if(!isDisabled){
			if(address.value == ""){
				border_color_red(address);
				valid_small_show(8);
				valid_focus('addressFocus'); 
			}
			if(customerName.value == ""){
				border_color_red(customerName);
				valid_small_show(9);
				if(valid_small_show_check(8)){
					valid_focus('nameFocus'); 
				}
			}
			if(customerPhone.value == ""){
				border_color_red(customerPhone);
				valid_small_show(10);
				if(valid_small_show_check(8) && valid_small_show_check(9)){
					valid_focus('phoneFocus'); 
				}
			}
			if(privacyCheck.checked == false){
				checkbox_alert(privacyCheckLabel);
				valid_show(13); 
				if(valid_small_show_check(8) && valid_small_show_check(9) && valid_small_show_check(10)){
					valid_focus('privacyCheckLabel');
				}
			}
			if(customerFunnel.value == ""){
				border_color_red(customerFunnel);
				valid_small_show(11);
				if(valid_small_show_check(8) && valid_small_show_check(9) && valid_small_show_check(10) && valid_show_check(13)){
					valid_focus('funnelFocus');
				}
			}
			auto_height();
			return;
		}
		document.getElementById('orderForm').submit();
		sessionStorage.clear();//세션 삭제
	}
	
	$('.slide-box').slick('slickNext');
}

//슬라이드 인덱스 이동
function slickGoindex(index){
	$('.slide-box').slick('slickGoTo', index);
}

//필수 입력사항 focus
function valid_focus(id){
	$('#'+ id).attr("tabindex", -1).focus(); 
}

function border_color_red(element){
	element.style.borderColor = 'rgb(255, 93, 79)';
}

function border_color_default(element){
	element.style.borderColor = 'rgb(219, 219, 219)';
}

//체크박스 alert
function checkbox_alert(label){
	label.style.backgroundImage = "url('/assets/resources/icon/estimate_form/checkbox/ic_check_box_alert.svg')"
}

//라디오버튼 alert
function radio_alert(label){
	label.style.backgroundImage = "url('/assets/resources/icon/estimate_form/radiobutton/ic_radio_btn_alert.svg')"
}

//필수 입력 항목입니다 텍스트 show 
function valid_show(i){
	const validCheck = document.getElementsByClassName('validation_check');
	validCheck[i].style.display = "block";
}

//필수 입력 항목입니다 텍스트 hide
function valid_hide(i){
	const validCheck = document.getElementsByClassName('validation_check');
	validCheck[i].style.display = "none";
}

//필수 입력 항목입니다 작은 텍스트 show
function valid_small_show(i){
	const validCheckSmall = document.getElementsByClassName('validation_check_small');
	validCheckSmall[i].style.display = "block";
}

//필수 입력 항목입니다 작은 텍스트 hide
function valid_small_hide(i){
	const validCheckSmall = document.getElementsByClassName('validation_check_small');
	validCheckSmall[i].style.display = "none";
}

//필수 입력 항목입니다 작은 텍스트가 show인지
function valid_small_show_check(i){
	const validCheckSmall = document.getElementsByClassName('validation_check_small');
	if(validCheckSmall[i].style.display == "none"){
		return true;
	}
}

//필수 입력 항목입니다 텍스트가 show인지
function valid_show_check(i){
	const validCheck = document.getElementsByClassName('validation_check');
	if(validCheck[i].style.display == "none"){
		return true;
	}
}

function id_display_none(id){
	id.style.display = "none";
}
function id_display_show(id){
	id.style.display = "block";
}

//신청하기 버튼
function estimate_start(){
	document.documentElement.scrollTop = 0; //스크롤 최상단
	$(".button_box").show(); //버튼박스 활성화
	$(".prev_button").hide(); //이전버튼 비활성화
	$(".progress").show(); //progressbar 활성화
	$(".next_button").width('100%'); //progressbar 활성화
	const urlParams = new URL(location.href).searchParams;
	const name = urlParams.get('pno'); //url parameter 값 가져오기
	var buildingRadioCheck = $("input[name=typeHouse]:checked").length;
	const card = document.querySelectorAll('.card'+name);
	const tileCard = document.querySelectorAll('._tile_card');
	const startPage = document.getElementById('startPage');
	const slickSlide = document.getElementById('slickSlide');
	for(var i of tileCard){
		if(i.style.borderColor == "rgba(255, 93, 79, 0.2)"){
			able_next_button();
		}
	}
	startPage.style.display = 'none';
	slickSlide.style.display = 'block';
	auto_height();
	if(name!=null){
		for(var i of card){
			i.style.border = "2px solid rgba(255, 93, 79, 0.2)";
		}
		if(buildingRadioCheck !=0){
			able_next_button();
		}
		slickGoindex(i2);
		progress_move(progress2); //progressbar 움직이기
		$(".prev_button").removeAttr("style");
		$(".next_button").removeAttr("style");
		history.replaceState({}, null, location.pathname); //url parameter 삭제
	} else{
		progress_move(progress1); //progressbar 움직이기
	}
}

//진행상황 퍼센트
function progress_move(percent){
	var progressBar = document.getElementById('progress-bar');
	progressBar.style.width = percent + "%";
}

//다음버튼 disable 시키기
function disable_next_button(){
	document.getElementsByClassName('next_button')[0].classList.remove('bt-primary-default');
	document.getElementsByClassName('next_button')[0].classList.add('bt-primary-default-disabled');
	
}

//다음버튼 able 시키기
function able_next_button(){
	document.getElementsByClassName('next_button')[0].classList.add('bt-primary-default');
	document.getElementsByClassName('next_button')[0].classList.remove('bt-primary-default-disabled');
}

//카카오 지도 api
function foldDaumPostcode() {
 var element_wrap = document.getElementById('wrap');
    // iframe을 넣은 element를 안보이게 한다.
    element_wrap.style.display = 'none';
    $('#kakaoModal').modal('hide');
}

//카카오 지도 api
function execDaumPostcode() {
	var element_wrap = document.getElementById('wrap');
    // 현재 scroll 위치를 저장해놓는다.
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 주소 선택시 도로명+아파트명으로만 가져온다.
            if (data.userSelectedType === 'R' || data.userSelectedType === 'J') {
				if(data.buildingName == ""){
					addr = data.roadAddress
				} else{
					addr = data.roadAddress + ' ('+ data.buildingName + ')';
				}
            }

            if(commerce_area_check()){
				able_next_button();
			}
         
			const address = document.getElementById("address");
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            address.value = addr;
            //
            if(address.style.borderColor == 'rgb(255, 93, 79)'){
				valid_small_hide(8);
				border_color_default(address);
				auto_height();
			}
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addressDetail").focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_wrap.style.display = 'none';
			$('#kakaoModal').modal('hide')
            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize : function(size) {
            element_wrap.style.height = size.height+'px';
            auto_height()
        },
        width : '100%',
        height : '100%'
        
    }).embed(element_wrap);
    // iframe을 넣은 element를 보이게 한다.
    element_wrap.style.display = 'block';
    auto_height()
}

//견적서 추가 기능
function get_estimate_calc(){
	const changeDedA = document.getElementById('changeDedA'); //평 변환버튼
	const changeSupA = document.getElementById('changeSupA'); //평 변환버튼
	const changeConsA = document.getElementById('changeConsA'); //평 변환버튼
	const changeConsACm = document.getElementById('changeConsACm'); //평 변환버튼
	const changeDedACm = document.getElementById('changeDedACm'); //평 변환버튼
	var typeHouse = $("input[name=typeHouse]:checked").val();
	var costSheetNo = 1;
	const tileCard = document.querySelectorAll('._tile_card');
	for(var i of tileCard){
		if(i.style.borderColor == "rgba(255, 93, 79, 0.2)"){
			var mtContentsNo = i.lastChild.previousSibling.previousSibling.previousSibling.value;
			var mtNo = i.lastChild.previousSibling.value;
		}
	}
	var isLivingRoom = $("#living").is(':checked');
	var isKitchen = $("#kitchen").is(':checked');
	var isRoom = $("#room").is(':checked');
	var isEntrance = $("#entrance").is(':checked');
	var isVeranda = $("#veranda").is(':checked');
	var isExpandLiving = $("#expandLiving").is(':checked');
	var isExpandKitchen = $("#expandKitchen").is(':checked');
	var isExpandRoom = $("#expandRoom").is(':checked');
	var roomCount = $("#cntRoom").val();
	var bathCount = $("#cntBath").val();
	
	if(typeHouse != 6){ //주거공간일 경우
		if(changeSupA.style.backgroundColor=='black'){
			var inSupArea = $("#acreageSupNum").html();
		} else{
			var inSupArea = $("#areaSupNum").val();
		}
		if(changeDedA.style.backgroundColor=='black'){
			var inDedArea = $("#acreageDedNum").html();
		} else{
			var inDedArea = $("#areaDedNum").val();
		}
		if(changeConsA.style.backgroundColor=='black'){
			var inConsArea = $("#acreageConsNum").html();
		} else{
			var inConsArea = $("#areaConsNum").val();
		}
	} else{
		var inSupArea = 0.0;
		roomCount = 0;
		bathCount = 0;
		if(changeDedACm.style.backgroundColor=='black'){
			var inDedArea = $("#acreageDedNumCm").html();
		} else{
			var inDedArea = $("#areaDedNumCm").val();
		}
		if(changeConsACm.style.backgroundColor=='black'){
			var inConsArea = $("#acreageConsNumCm").html();
		} else{
			var inConsArea = $("#areaConsNumCm").val();
		}
	}

	var formData = new FormData();
	
	formData.append("costSheetNo", costSheetNo);
	formData.append("mtContentsNo", mtContentsNo);
	formData.append("isLivingRoom", isLivingRoom);
	formData.append("isKitchen", isKitchen);
	formData.append("isRoom", isRoom);
	formData.append("isEntrance", isEntrance);
	formData.append("isVeranda", isVeranda);
	formData.append("isExpandLiving", isExpandLiving);
	formData.append("isExpandKitchen", isExpandKitchen);
	formData.append("isExpandRoom", isExpandRoom);
	formData.append("inSupArea", inSupArea);
	formData.append("inDedArea", inDedArea);
	formData.append("inConsArea", inConsArea);
	formData.append("roomCount", roomCount);
	formData.append("bathCount", bathCount);
	formData.append("typeHouse", typeHouse);
	
	var display2 = document.getElementById('tile_display2');
	var display3 = document.getElementById('tile_display3');

	$.ajax({
		url : "/service/ajax/estimate/form",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			var name = data.estimate.mtName;
			var finalCost = data.estimate.totalFinalCost;
			var classSm = data.material.mtClass.classSm;
			var surface = data.material.mtSpec.surface
			var sizeW = data.material.mtStd.sizeW
			var sizeH = data.material.mtStd.sizeH
			var display2 = document.getElementById('tile_display2');
			var display3 = document.getElementById('tile_display3');
			if(cnt==1){ //첫 견적서 등록시 cnt == 1
				$('#totalCost1').html('총 예상 견적가 '+finalCost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
				$('#tileName1').html(name);
				document.getElementById("tileImage1").src = '/image_storage/' + data.material.mainImage.thumbnailPath +'/'+ data.material.mainImage.thumbnailName;
				$('#classSm1').html(classSm);
				$('#surface1').html(surface);				
				$('#sizeW1').html(sizeW);				
				$('#sizeH1').html(sizeH);
				const estimateNoAdd = $("<input type='hidden' value=" + data.estimate.no + " id='estimateNo1' name='estimateNo1' readonly>");
				$("#orderForm").append(estimateNoAdd);				
				mtNo1 = mtNo;
				//cnt++; 견적서 하나로 고정 시 cnt값은 고정
			} else if(cnt==2){ //견적서 추가시 cnt를 더해 cnt값에 따라 견적서 등록 두번째 견적서 등록시 cnt == 2
				id_display_show(display2)
				$('#totalCost2').html('총 예상 견적가 '+finalCost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
				$('#tileName2').html(name);
				document.getElementById("tileImage2").src = '/image_storage/' + data.material.mainImage.thumbnailPath +'/'+ data.material.mainImage.thumbnailName;
				$('#classSm2').html(classSm);
				$('#surface2').html(surface);				
				$('#sizeW2').html(sizeW);				
				$('#sizeH2').html(sizeH);
				const estimateNoAdd = $("<input type='hidden' value=" + data.estimate.no + " id='estimateNo2' name='estimateNo2' readonly>");
				$("#orderForm").append(estimateNoAdd);			
				mtNo2 = mtNo;
				id_display_show(display2);
				cnt++;
			} else if(cnt==3){ //세번째 견적서 등록시 cnt ==3
				id_display_show(display3)
				$('#totalCost3').html('총 예상 견적가 '+finalCost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
				$('#tileName3').html(name);
				document.getElementById("tileImage3").src = '/image_storage/' + data.material.mainImage.thumbnailPath +'/'+ data.material.mainImage.thumbnailName;
				$('#classSm3').html(classSm);
				$('#surface3').html(surface);				
				$('#sizeW3').html(sizeW);				
				$('#sizeH3').html(sizeH);
				const estimateNoAdd = $("<input type='hidden' value=" + data.estimate.no + " id='estimateNo3' name='estimateNo3' readonly>");
				$("#orderForm").append(estimateNoAdd);				
				mtNo3 = mtNo;
				id_display_show(display3);
				id_display_none(estimateAdd);
				cnt++;
			}
			/*const cardOnclick = document.querySelectorAll('.card'+mtNo);
			for(var i of cardOnclick){
				i.onclick = null;
				i.style.opacity="0.2"
				i.style.border = "2px solid rgba(255, 93, 79, 0.8)"
			}
			const tileSelect = document.getElementById('tileSelect'+mtNo);
			tileSelect.removeAttribute("style");*/
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
//견적서 삭제 기능
function delete_estimate_calc(close){
	var display2 = document.getElementById('tile_display2');
	var display3 = document.getElementById('tile_display3');
	var addOnclick3 = document.querySelectorAll('.card'+mtNo3);
	var addOnclick2 = document.querySelectorAll('.card'+mtNo2);
	var addSelectButton2 = document.getElementById('tileSelect'+mtNo2);
	var addSelectButton3 = document.getElementById('tileSelect'+mtNo3);
	var formData = new FormData();
	if(cnt==4){ //견적서가 3개일 경우
		var id = $(close).attr("id");
		if(id == 'closeThird'){ //삭제 할 견적서가 3번째 견적서 일 경우
			id_display_none(display3);
			estimateAdd.style.display = "flex";
			for(var i of addOnclick3){
				i.setAttribute("onclick", "card_select(this)");
				i.removeAttribute("style")
			}
			addSelectButton3.setAttribute("onclick", "tile_select_button("+mtNo3+")");
			formData.append("estimateNo", $('#estimateNo3').val())
			$('#estimateNo3').remove();
		} else{ //삭제 할 견적서가 2번째 견적서 일 경우
			id_display_none(display3);
			$('#totalCost2').html($('#totalCost3').html());
			$('#tileName2').html($('#tileName3').html());
			document.getElementById("tileImage2").src = document.getElementById("tileImage3").src
			$('#classSm2').html($('#classSm3').html());
			$('#surface2').html($('#surface3').html());				
			$('#sizeW2').html($('#sizeW3').html());				
			$('#sizeH2').html($('#sizeH3').html());
			estimateAdd.style.display = "flex";
			for(var i of addOnclick2){
				i.setAttribute("onclick", "card_select(this)");
				i.removeAttribute("style")
			}
			addSelectButton2.setAttribute("onclick", "tile_select_button("+mtNo2+")");
			mtNo2 = mtNo3;
			formData.append("estimateNo", $('#estimateNo2').val())
			const estimateVal = $('#estimateNo3').val();
			const estimateNo2 = document.getElementById('estimateNo2');
			estimateNo2.value = estimateVal;
			$('#estimateNo3').remove();
		}
		$(".prev_button").removeAttr("style");
		$(".next_button").removeAttr("style");
		cnt--;
	} else if(cnt==3){ //견적서가 2개일 경우
		id_display_none(display2);
		for(var i of addOnclick2){
			i.setAttribute("onclick", "card_select(this)");
			i.removeAttribute("style")
		}
		addSelectButton2.setAttribute("onclick", "tile_select_button("+mtNo2+")");
		formData.append("estimateNo", $('#estimateNo2').val())
		cnt--;
		$('#estimateNo2').remove();
	}
	auto_height();
	
	$.ajax({
		url : "/service/ajax/estimate/form/del",
		type : "POST",
		cache : false,
		async : true,
		data : formData,
		contentType: false,
		processData: false,
		success : function(data, textStatus, xhr) {
			
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
//면적 value 넣는 기능
function get_area_value(){
	const changeDedA = document.getElementById('changeDedA'); //평 변환버튼
	const changeSupA = document.getElementById('changeSupA'); //평 변환버튼
	const changeConsA = document.getElementById('changeConsA'); //평 변환버튼
	const changeConsACm = document.getElementById('changeConsACm'); //평 변환버튼
	const changeDedACm = document.getElementById('changeDedACm'); //평 변환버튼
	var typeHouse = $("input[name=typeHouse]:checked").val();
	if(typeHouse != 6){ //주거공간일 경우
		if(changeSupA.style.backgroundColor=='black'){
			var inSupArea = $("#acreageSupNum").html();
		} else{
			var inSupArea = $("#areaSupNum").val();
		}
		if(changeDedA.style.backgroundColor=='black'){
			var inDedArea = $("#acreageDedNum").html();
		} else{
			var inDedArea = $("#areaDedNum").val();
		}
		if(changeConsA.style.backgroundColor=='black'){
			var inConsArea = $("#acreageConsNum").html();
		} else{
			var inConsArea = $("#areaConsNum").val();
		}
	} else{
		var inSupArea = 0.0;
		if(changeDedACm.style.backgroundColor=='black'){
			var inDedArea = $("#acreageDedNumCm").html();
		} else{
			var inDedArea = $("#areaDedNumCm").val();
		}
		if(changeConsACm.style.backgroundColor=='black'){
			var inConsArea = $("#acreageConsNumCm").html();
		} else{
			var inConsArea = $("#areaConsNumCm").val();
		}
	}
	document.getElementById('supValue').value = inSupArea;
	document.getElementById('dedValue').value = inDedArea;
	document.getElementById('consValue').value = inConsArea;
}