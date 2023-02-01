$(document).ready(function () {
			// validation
			'use strict';
			window.addEventListener('load', function() {
				// Fetch all the forms we want to apply custom Bootstrap validation styles to
				var forms = document.getElementsByClassName('needs-validation');
				// Loop over them and prevent submission
				var validation = Array.prototype.filter.call(forms, function(form) {
					form.addEventListener('submit', function(event) {
						if (form.checkValidity() === false) {
							event.preventDefault();
							event.stopPropagation();
						}
						form.classList.add('was-validated');
					}, false);
				});
			}, false);
			
			$(".list-group-item").on("click", function(e){
				
				var selector = $(e.target).parents(".list-group-item");
				
				var no = $(selector).find("img").attr("mno");
				$("#materialNo").val(no);
				
				//clear
				$(".list-group-item").removeClass("active");
				$("#__select_main_image_div").html("");
				$("#viewImageNo").val(null);
				
				//click -> focus
				selector.toggleClass("active");
				
				//click -> focus -> view main_image
				var html = $(selector).find(".__select_image_list").html();
				$("#__select_main_image_div").html(html);
				
				//[clicker] after material select, main_image click
				$("#__select_main_image_div .__select_image img").on("click", function(e2){
					
					$("#__select_main_image_div .__select_image img").removeClass("_active_image");
					$(e2.target).toggleClass("_active_image");
					
					var no = $(e2.target).attr("ino");
					$("#viewImageNo").val(no);
					
				});
				
			});
			
			$("._sub_item").on("click", function(e){
				
				var selector = $(e.target).parents("._sub_item");
				
				$(selector).find("img").toggleClass("_active_sub_item");

				var subNo = $(selector).attr("item");
				var param = '<input type="hidden" name="subslist" value="'+ subNo + '">';
				var check = $(selector).find("img").hasClass("_active_sub_item");
				
				if(check) {
					$("#_subslist_div").append(param);
				} else {
					$("#_subslist_div").find("input[value="+subNo+"]").remove();
				}
				
			});
			
			 /*$("#priceUnknown").on('click', function(){           
		       if($(this).is(':checked')){
		           $("#consumerPrice").attr('disabled', true);
		       } else {
				   $("#consumerPrice").val("0");
		           $("#consumerPrice").attr('disabled', false);
	
		       }
   			});*/
			
			
			
			
			
});