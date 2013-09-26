define(['jquery-2.0.3', 'knockout-2.3.0','sammy'], function($, ko, SammyJS) {
		return function UpitViewModel() {
			this.navigation = ko.observable(['About', 'Terms', 'Paste']);
			
			// Client-side routes
			SammyJS('body', function() {
				
				this.get('#/', function(context) {
					console.log('foo');
				});
				
			}).run('#/');
		};
	}
);