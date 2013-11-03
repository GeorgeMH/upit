define([ 'jquery', 'sammy', 'knockout', 'viewmodels/UpitViewModel' ], function($, SammyJS, ko, UpitViewModel) {
	return function Router() {
		var self = this;
		
		self.primaryNavigation = ['home', 'about', 'register', 'login'];
		self.currentPage = ko.observable('Home');
		
		// Client-side routes
		var sammy = SammyJS('body', function() {

			//Default
			this.get('', function(){
				this.app.runRoute('get', '#/home/')
			});

			this.get('#/', function(context) {
				console.log('foo1');
			});

		});
		sammy.run('#/');
	};
});