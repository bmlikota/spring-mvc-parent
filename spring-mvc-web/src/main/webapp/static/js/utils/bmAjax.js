var bmAjax = {
	ajaxWithJsonResponse: function (url, params, callback, errorCallback, beforeSendCallback) {
        if (typeof params === 'undefined') {
            params = null;
        }

        var contextRoot = this.getContextRoot();

        $.ajax({
            type: "POST",
            url: contextRoot + url,
            dataType: "json",
            data: params,
            beforeSend: function () {
//                addSpinner(); TODO
                return beforeSendCallback != null ? beforeSendCallback() : null;
            },
            success: function (rs) {
//                removeSpinner(); TODO
                if (rs.status !== 'OK') {
                    this.error(rs);
                    return;
                }
//                UTILS.handleMessages(rs); TODO
                return callback != null ? callback(rs) : null;
            },
            error: function (rs, textStatus, errorThrown) {
//                handleAjaxError(rs, textStatus, errorThrown); TODO
            }
        });
    }
}
