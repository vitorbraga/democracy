Backbone.Router.prototype.before = function() {
};
Backbone.Router.prototype.after = function() {
};

Backbone.Router.prototype.route = function(route, name, callback) {
	if (!_.isRegExp(route))
		route = this._routeToRegExp(route);
	if (_.isFunction(name)) {
		callback = name;
		name = '';
	}
	if (!callback)
		callback = this[name];

	var router = this;

	Backbone.history.route(route, function(fragment) {
		var args = router._extractParameters(route, fragment);

		router.before.apply(router, arguments);
		callback && callback.apply(router, args);
		router.after.apply(router, arguments);

		router.trigger.apply(router, [ 'route:' + name ].concat(args));
		router.trigger('route', name, args);
		Backbone.history.trigger('route', router, name, args);
	});
	return this;
};

var AppRouter = Backbone.Router.extend({
	routes : {
		'' : 'home',
		'*path' : 'redirect',
	},

	home : function() {
	},

	redirect : function() {
		
		$('#loader-wrapper').fadeIn(150);
		var urlInfo = getUrlInfo();

		$.ajax({
			url : basePath + urlInfo.ajaxUrl,
			type : 'get'
		}).done(function(data) {
			$('#loader-wrapper').fadeOut(150);
			$('.container-fluid').html(data);
			if (callback != undefined && callback != '') {
				callback = window[callback](param);
				callback ? callback(param) : null;
			}
		});
	},

	after : function() {
		var callbackObj = getCallbackAndParam();
		if (!jQuery.isEmptyObject(callbackObj)) {
			callback = callbackObj.callback;
			param = callbackObj.param;
		}
	}

});

function getCallbackAndParam() {

	var callbackObj = {};
	var currentUrl = document.URL;
	if (currentUrl.indexOf('#') != -1) {
		var action = currentUrl.substring(currentUrl.indexOf('#'),
				currentUrl.length);
		callbackObj.callback = $('a[href=' + action + ']').attr('callback');
		callbackObj.param = $('a[href=' + action + ']').attr('param');
	}
	return callbackObj;
}

function getUrlInfo() {
	var currentUrl = document.URL;
	var urlInfo = {};
	var aux = currentUrl.substring(currentUrl.indexOf('#'), currentUrl.length);
	aux = aux.substring(1, aux.length);
	urlInfo.ajaxUrl = 'admin/' + aux;

	return urlInfo;
}

function getSearchFilters() {

	var search = {};
	$.each($('.search-field'), function(i) {
		search[$(this).attr('name')] = $(this).val();
	});

	return search;
}

function searchQuestion() {
	
	$('#question-search-but').on('click', function() {
		$('#loader-wrapper').fadeIn(150);
		doSearchQuestion();
	});
}

function doSearchQuestion() {
	
	var search = getSearchFilters();
	
	$.ajax({
		url : basePath + 'question/search',
		type : 'get',
		data : search
	}).done(function(data) {
		if(data.success == 'false') {
			alert(data.message);
		} else {
			$('#loader-wrapper').fadeOut(150);
			$('#questions-result').hide().html(data).fadeIn(150);
		}
	});
}

function doSearchAwaitingUsers() {
	
	$.ajax({
		url : basePath + 'admin/awaitingUsers',
		type : 'get'
	}).done(function(data) {
		$('#loader-wrapper').fadeOut(150);
		$('.container-fluid').html(data);
	});
	
}

function registerAdmin() {
	
}

function awaitingUsers() {
	
}