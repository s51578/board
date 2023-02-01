$(document).ready(function() {	
		$('.box__slider').slick({
			lazyLoad: 'ondemand', // default 'ondemand'
			infinite:true,
			arrows:false,
			slidesToShow : 3,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 0,
			speed:10000,
			dots : false,
			draggable: false,
			variableWidth:true,
			cssEase: 'linear',
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
						dots : false,
						draggable: true
					}
				}, 
				{
					breakpoint : 576,
					settings : {
						slidesToShow : 1,
						centerMode : false, /* set centerMode to false to show complete slide instead of 3 */
						slidesToScroll : 1,
						dots : false,
						draggable: true
					}
				}
			],		
		});
});



