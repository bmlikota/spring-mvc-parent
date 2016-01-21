$(function() {

	var contextRoot = $('meta').data('context-url');
	loadScripts();
	initialize();


	// ******************************** function definitions ********************************************************
	function initialize() {

		$('body').on('click', '.counter', function() {
			var count = parseInt($('p.counter').html(), 10);
			$('p.counter').html(count + 1);

			bmAjax.getString(contextRoot + '/ajaxtest', function(data) {
				$('#result').html(data);
			});
		});
	};

	function loadScripts() {
		$.getScript(contextRoot + '/static/js/utils/bmAjax.js', function() {
		});
	};

});
