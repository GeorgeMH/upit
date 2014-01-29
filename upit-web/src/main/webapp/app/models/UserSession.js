define([ 'jquery' , 'knockout'], function($, ko) {

	return {
		sessionId: ko.observable(''),
		userId: ko.observable(-1),
		authToken: ko.observable(''),
		init: function(){
			
		},
	};

});