define([ 'jquery', 'knockout', 'sammy' ], function($, ko, SammyJS) {
	return function UpitViewModel() {
		var self = this;

		// Client-side routes
		var sammy = SammyJS('body', function() {

			this.get('#/', function(context) {
				console.log('foo');
			});

		});
		sammy.run('#/');
	};
});