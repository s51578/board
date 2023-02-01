
$(document).ready(function(){
	
	$(".bottom_nav_contact__ded_area--select").on('change', function(){
		getEstimateMinCost(this.value);
	})
	// 가격 표시
	
	$('.dedModalCenter').on('show.bs.modal', function (event) {
		$(this).appendTo('body');
		$(this).remove();
	});
	
	/* $('.dedModalCenter').on('shown.bs.modal', function (event) {
		$(".modal-backdrop").eq(0).css("z-index", "1050");
	}); */
	
	$('.dedModalCenter').on('hidden.bs.modal', function (event) {
		$(this).appendTo('.bottom_nav_contact');
		$(".modal-backdrop").eq(0).css("z-index", "1040");
		$(".modal-backdrop").eq(1).css("z-index", "1040");
		$(this).remove();
	});
	
	/* $(".bottom_nav_contact__ded_area--select").find("option[value='75']").prop("selected", true);
	$(".bottom_nav_contact__ded_area--select").trigger('change'); */
	
	function getEstimateMinCost(dedarea) {

		if(dedarea=="")
			return;
		
		//load
		var price = contentsTileCost;
		//var dedarea = 84; // 25평 제곱 변환시 수치
		var meter2Box = m2Box;
		
		var formData = new FormData();
		
		formData.append("price", price);
		formData.append("area", dedarea);
		formData.append("meter2Box", meter2Box);
		
		$.ajax({
			url : "/service/ajax/estimate/min",
			type : "POST",
			cache : false,
			async : false,
			data : formData,
			contentType: false,
			processData: false,
			success : function(data, textStatus, xhr) {
				
				var final = data.totalFinalCost;
				var final_fmt = thousands_separators(final);
				$(".bottom_nav_contact__ded_area_result--result").text(final_fmt);
				
				//document.getElementsByClassName("bottom_nav_contact__ded_area_result--result").innerHTML = 

			},
			error : function(data, textStatus, xhr) {

				if (data.status == 200)
					alert("로그인이 필요합니다.");
				else if (data.status == 400)
					alert("잘못된 요청입니다." + textStatus);
				else
					alert("관리자에게 문의해주십시오." + textStatus);
			}
		});
		
	}

});
