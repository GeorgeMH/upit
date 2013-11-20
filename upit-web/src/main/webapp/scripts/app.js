requirejs.config({
	"baseUrl" : "scripts/app",
	urlArgs: "bust=" + (new Date()).getTime(),
	//urlArgs: "bust=v1",
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
		"jquery" : "../lib/jquery/jquery-2.0.3",
		"knockout" : "../lib/knockout/knockout-2.3.0",
		"bootstrap" : "../lib/bootstrap/bootstrap-2.3.2",

		"durandal" : "../lib/durandal",
		"plugins" : "../lib/durandal/plugins",
		"transitions" : "../lib/durandal/transitions"
	}
});
//requirejs(["bootstrap", "upit" ]);


