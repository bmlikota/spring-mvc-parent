$(function() {

	var contextRoot = $('meta').data('context-url');
	loadScripts();
	initialize();


	// ******************************** function definitions ********************************************************
	function initialize() {

		$('body').on('click', '.counter', function() {
			var count = parseInt($('p.counter').html(), 10);
			$('p.counter').html(count + 1);

			var callback = function(data) {
				$('#result').html(data);
			};
			bmAjax.getString(contextRoot + '/ajaxtest', callback, null);

			var callback = function(data) {
				var a = "debug";
			};
			var url = 'http://localhost:8080/rest-ws-example/bm-service/get2/';
			var params = {max : 12, count : 12};
			var data = {};
			bmAjax.getJson(url, callback);

		});
	};

	function loadScripts() {
		$.getScript(contextRoot + '/static/js/utils/bmAjax.js', function() {
		});
	};

});
