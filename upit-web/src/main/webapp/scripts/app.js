requirejs.config({
	"baseUrl" : "scripts/app",
	shim : {
		'sammy' : {
			deps : [ 'jquery' ],
			exports : 'Sammy'
		},
		"bootstrap" : {
			deps : [ "jquery" ],
			exports: "$.fn.popover"
		}
	},
	"paths" : {
		"jquery" : "../lib/jquery-2.0.3",
		"knockout" : "../lib/knockout-2.3.0",
		"sammy": "../lib/sammy",
		"bootstrap" : "../lib/bootstrap-3.0.0.min"
	}

});

requirejs(["bootstrap", "upit" ]);

