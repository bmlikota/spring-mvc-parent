$(function () {

	$('body').on('click', '.counter', function () {
		var count = parseInt($('p.counter').html(), 10);
		$('p.counter').html(count + 1);
	});

});
