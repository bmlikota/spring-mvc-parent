define(function () {

    return {
    	getString : function(urlPath, callback, params) {
			$.ajax({
				data: params,
	            url : urlPath,
	            success : function(data) {
	            	callback(data);
	            }
	        });
		},

		getJson : function(url, callback) {
			$.ajax({
			  dataType: "application/json",
			  url: url,
			  type: "GET",
			  success : function(data) {
            	callback(data);
			  }
			});
		}
    }
});