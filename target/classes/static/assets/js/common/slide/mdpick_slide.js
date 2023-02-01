$(function(){
	
	$('.mdList-slick-slide').slick({
		lazyLoad: 'ondemand', // default 'ondemand'
		slidesToShow : 4,
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
		prevArrow:"<div class='mdList-slick-prev-arrow d-none d-md-block'></div>",
		nextArrow:"<div class='mdList-slick-next-arrow d-none d-md-block'></div>"
		
	})
	
	
});