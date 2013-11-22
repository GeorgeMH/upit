define([ 'jquery', 'knockout', 'durandal/app', 'plugins/router' ], function($, ko, app, router) {
	return function() {
		var self = this;

		self.displayName = 'Register';
		self.description = 'Create an account to start using Upit.IO today!';

		self.email = ko.observable();
		self.password = ko.observable();
		self.confirmPassword = ko.observable();

		self.submitRegistration = function() {
			app.showMessage('Register not yet implemented...');
		};

	};

});