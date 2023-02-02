$(function() {
    $('#crTable').DataTable({
		order: [[0, "DESC"]],
		info:false,
		 language: {
    search: "찾기 : "
   },
	});
} );