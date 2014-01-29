define([ 'plugins/router', 'durandal/app', 'models/UserSession'], function(router, app, userSession) {
	return {
		router : router,
		
		activate : function() {
			router.map([ {
				route : '',
				title : 'Main',
				moduleId : 'viewmodels/main',
				nav : true
			}, {
				route : 'about',
				title : 'About',
				moduleId : 'viewmodels/about',
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
			
			userSession.init();

			return router.activate();
		},
	};
});