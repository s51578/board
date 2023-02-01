$(document).ready(function () {

	/*var data = [
	    ['Jazz', 'Honda', '2019-02-12', '', true, '$ 2.000,00', '#777700'],
	    ['Civic', 'Honda', '2018-07-11', '', true, '$ 4.000,01', '#007777'],
	];*/
	var so = source;
	
	jspreadsheet(document.getElementById('spreadsheet'), {
	    data: source,
	    defaultColWidth: 200
	});

});