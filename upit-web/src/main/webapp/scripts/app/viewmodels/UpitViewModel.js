define([ 'jquery', 'knockout', 'sammy' ], function($, ko, SammyJS) {
	return function UpitViewModel() {
		var self = this;

		self.primaryNavigation = ['Home', 'About', 'Register', 'Login'];
		self.currentPage = ko.observable();
		
		self.goToPage = function(page) {
			self.currentPage(page);
		};

	};
});