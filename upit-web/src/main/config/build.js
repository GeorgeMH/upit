({
    appDir: "../webapp",
    baseUrl: "../webapp/app",
    dir: "../../app-build",
    modules: [
        {
            name: "main"
        }
    ],
	paths : {
		'text' : './lib/require/text',
		'durandal' : './lib/durandal/js',
		'plugins' : './lib/durandal/js/plugins',
		'transitions' : './lib/durandal/js/transitions',
		'knockout' : './lib/knockout/knockout-2.3.0',
		'knockoutMapping' : './lib/knockout/knockout.mapping-2.4.1',
		'bootstrap' : '../lib/bootstrap/js/bootstrap',
		'jquery' : './lib/jquery/jquery-1.9.1'
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