define([ 'jquery', 'knockout', 'durandal/app', 'plugins/router' ], function($, ko, app, router) {
	return function() {
		var self = this;

		self.displayName = 'Sign In';
		self.description = 'Log into your account to access advanced features';

		self.doLogin = function() {
			app.showMessage('Sign in not yet implemented...');
		};
	}
});