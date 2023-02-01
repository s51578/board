$(document).ready(function() {
		
		/* v0.1.3.4 */
		$(".modal_consReview--button").click( () => {
			$("#menuModal").modal('toggle');
		});
		/* //v0.1.3.4 */
		
		$('[data-toggle="tooltip"]').tooltip();
		
		$('.magazine_slick_slide').slick({
			lazyLoad: 'ondemand', // default 'ondemand'
			slidesToShow : 3,
			slidesToScroll : 1,
			//autoplay : true,
			//autoplaySpeed : 2000,
			dots : false,
			draggable: false,
			infinite : false,
			responsive : [
				{
					breakpoint : 1200,
					settings : {
						slidesToShow : 3,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1,
						dots : false
					}
				}, 
				{
					breakpoint : 993,
					settings : {
						slidesToShow : 3,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1,
						dots : false
					}
				},
				{
					breakpoint : 992,
					settings : {
						slidesToShow : 2,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1,
						dots : false
					}
				}, 
				{
					breakpoint : 769,
					settings : {
						slidesToShow : 2,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1,
						dots : true,
						draggable: true
					}
				}, 
				{
					breakpoint : 576,
					settings : {
						slidesToShow : 1,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1,
						dots : true,
						draggable: true
					}
				}
			],
			prevArrow:"<div class='mdList-slick-prev-arrow d-none d-md-block'></div>",
			nextArrow:"<div class='mdList-slick-next-arrow d-none d-md-block'></div>"
			
		});

	$(document).on("mouseover", "#magazine_slick_prev_hover", function(){
		$("#magazine_slick_prev_hover").attr("src", "/assets/resources/icon/contents/slide/btn_prev_page_s.svg");
	});
	$(document).on("mouseout", "#magazine_slick_prev_hover", function(){
		$("#magazine_slick_prev_hover").attr("src", "/assets/resources/icon/contents/slide/btn_prev_page_d.svg");
	});
	$(document).on("mouseover", "#magazine_slick_next_hover", function(){
		$("#magazine_slick_next_hover").attr("src", "/assets/resources/icon/contents/slide/btn_next_page_s.svg");
	});
	$(document).on("mouseout", "#magazine_slick_next_hover", function(){
		$("#magazine_slick_next_hover").attr("src", "/assets/resources/icon/contents/slide/btn_next_page_n.svg");
	});
	
	$(document).on("mouseover", ".store_move_btn", function(){
		$(".store_move_img").attr("src", "/assets/resources/icon/contents/slide/ic_arrow_next_s.svg");
	});
	$(document).on("mouseout", ".store_move_btn", function(){
		$(".store_move_img").attr("src", "/assets/resources/icon/contents/slide/ic_arrow_next_n.svg");
	});
	
	$(document).on("mouseover", "#popular_product_more_btn", function(){
		$("#popular_move_img").attr("src", "/assets/resources/icon/contents/slide/icon_select_12_s.svg");
	});
	$(document).on("mouseout", "#popular_product_more_btn", function(){
		$("#popular_move_img").attr("src", "/assets/resources/icon/contents/slide/icon_select_12_n.svg");
	});
	
	$(document).on("mouseover", "#price_product_more_btn", function(){
		$("#price_move_img").attr("src", "/assets/resources/icon/contents/slide/icon_select_12_s.svg");
	});
	$(document).on("mouseout", "#price_product_more_btn", function(){
		$("#price_move_img").attr("src", "/assets/resources/icon/contents/slide/icon_select_12_n.svg");
	});
	
	$(document).on("mouseover", "#newest_product_more_btn", function(){
		$("#newest_move_img").attr("src", "/assets/resources/icon/contents/slide/icon_select_12_s.svg");
	});
	$(document).on("mouseout", "#newest_product_more_btn", function(){
		$("#newest_move_img").attr("src", "/assets/resources/icon/contents/slide/icon_select_12_n.svg");
	});

	
	// 스토어 랭킹 마지막 div border제거
	// 자식 요소의 마지막에서 두번째 -> 제품 더보기 div 이전 마지막으로 생성된 타일 div
	$("#popular").children().eq(-2).css("border-bottom", "none");
	$("#price").children().eq(-2).css("border-bottom", "none");
	$("#newest").children().eq(-2).css("border-bottom", "none");
	
	$("#popular_tabs").children().eq(-2).css("border-bottom", "none");
	$("#price_tabs").children().eq(-2).css("border-bottom", "none");
	$("#newest_tabs").children().eq(-2).css("border-bottom", "none");
		
		
	// 모바일 탭
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})
	
});



