define([ 'plugins/router', 'durandal/app' ], function(router, app) {
	return {
		router : router,
		search : function() {
			// It's really easy to show a message box.
			// You can add custom options too. Also, it returns a promise for
			// the user's response.
			app.showMessage('Search not yet implemented...');
		},
		activate : function() {
			router.map([ {
				route : '',
				title : 'About',
				moduleId : 'viewmodels/about',
				nav : true
			}, {
				route : 'pastebin',
				moduleId : 'viewmodels/pastebin',
				nav : true
			}, {
				route : 'signin',
				title : "Sign In",
				moduleId : 'viewmodels/signin',
				nav : true
			}, {
				route : 'register',
				moduleId : 'viewmodels/register',
				nav : true
			} ]).buildNavigationModel();

			return router.activate();
		}
	};
});