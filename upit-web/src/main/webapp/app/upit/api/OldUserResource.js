define([ 'jquery' , 'knockout'], function($, ko) {

	return {
		register: function(user){
			
			var deferred = $.Deferred();
			
			var data = ko.mapping.toJSON(user);
			
			$.ajax({
				type: 'POST',
				contentType: 'application/json',
				url: 'api/user/register',
				dataType: 'json',
				data: data,
				success: function(data, textStatus, jqXHR){
					//console.log('Got json response:\n' + data);
					data = ko.mapping.fromJS(data);
					deferred.resolve(data, textStatus, jqXHR);
				},
				error: function(jqXHR, textStatus, errorThrown){
					deferred.reject(jqXHR, textStatus, errorThrown);
				}
			});
			
			return deferred.promise();
		},
	};

});