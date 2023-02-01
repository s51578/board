$(function () {
  $('[data-toggle="tooltip"]').tooltip()

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
						arrows: false,
						dots : true,
						draggable: true
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

		// 동적 요소 적용
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
})