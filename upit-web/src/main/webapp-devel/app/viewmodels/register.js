define([ 'jquery', 'knockout', 'durandal/app', 'upit/api/gen/userResource', 'models/UserSession' ], function($, ko, app, userResource, userSession) {
	return function() {
		var self = this;

		self.displayName = 'Register';
		self.description = 'Create an account to start using Upit.IO today!';
		
		self.newUser = {
			email: ko.observable(''),
			password: ko.observable(''),
		};

		self.confirmPassword = ko.observable();

		self.submitRegistration = function() {
			
			//TODO: Validate newUser.password == confirmPassword!
			
			console.log('Calling register: "' + self.newUser.email() + '" => "' + self.newUser.password() + '" | ' + self.newUser);
			
			userResource.register(self.newUser).then(function(registeredUser){
				console.log('Registered user: ' + registeredUser.id() + ' => ' + registeredUser.email() + '');
			}, function(jqXHR, textStatus, errorThrown){
				console.log("Error fetching data: " + textStatus)
			});
		};
		
	};

});