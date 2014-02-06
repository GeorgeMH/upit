requirejs.config({
	urlArgs : "bust=" + (new Date()).getTime(),
	paths : {
		'text' : 'lib/require/text',
		'durandal' : 'lib/durandal/js',
		'plugins' : 'lib/durandal/js/plugins',
		'transitions' : 'lib/durandal/js/transitions',
		'knockout' : 'lib/knockout/knockout-2.3.0',
		'knockoutMapping' : 'lib/knockout/knockout.mapping-2.4.1',
		'bootstrap' : 'lib/bootstrap/js/bootstrap',
		'jquery' : 'lib/jquery/jquery-1.9.1'
	},
	shim : {
		'bootstrap' : {
			deps : [ 'jquery' ],
			exports : 'jQuery'
		},
		'knockoutMapping': {
			deps : [ 'knockout' ],
			exports : 'knockoutMapping'
		}
	}
});

define(['durandal/system', 'durandal/app', 'durandal/viewLocator', 'knockout', 'knockoutMapping'], function(system, app, viewLocator, ko, koMapping) {
	// >>excludeStart("build", true);.
	system.debug(true);
	// >>excludeEnd("build");

	ko.mapping = koMapping;

	app.title = 'Upit.IO';

	app.configurePlugins({
		router : true,
		dialog : true,
		widget : true
	});

	app.start().then(function() {
		
		// Replace 'viewmodels' in the moduleId with 'views' to locate the view.
		// Look for partial views in a 'views' folder in the root.
		viewLocator.useConvention();

		// Show the app by setting the root view model for our application with
		// a transition.
		app.setRoot('viewmodels/shell', 'entrance');
	});
});