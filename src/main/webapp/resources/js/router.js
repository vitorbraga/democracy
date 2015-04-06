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
		
		var urlInfo = getUrlInfo();

		$.ajax({
			url : basePath + urlInfo.ajaxUrl,
			type : 'get'
		}).done(function(data) {

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

function newQuestion() {
	
	$('#add-answer').on('click', function() {
		var answers = $('.answer-box');
		var num = answers.length;
		num++;
		var newAnswer = '<div class="form-group answer-box"><label class="label_answer">Resposta '+num+'</label>' +
			'<input type="text" num="'+ num +'" name="question.answers['+(num-1)+'].answer" class="input_answer  form-control" '+
			'placeholder="Resposta '+num+'"/></div>';
		$(newAnswer).insertBefore('#question-submit-but');
	});	
	
}

function searchQuestion() {
	
	$('#question-search-but').on('click', function() {
		// chama busca ajax
		var search = getSearchFilters();
		$.ajax({
			url : basePath + 'question/search',
			type : 'get',
			data : search
		}).done(function(data) {
			$('#questions-result').hide().html(data).fadeIn(150);
		});
	});
}