var bmAjax = {
		getString : function(urlPath, callback) {
			$.ajax({
	            url : urlPath,
	            success : function(data) {
	            	callback(data);
	            }
	        });
		}
}
