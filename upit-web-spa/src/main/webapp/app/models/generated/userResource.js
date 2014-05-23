/**
 * Auto Generated by jaxrs-js-gen from io.upit.jaxrs.resources.UserResource
 * Warning: Manual changes to this file may be over-written.
 * 
 * 
 * See https://github.com/GeorgeMH/jaxrs-js-gen for more details
 */
define([ 'jquery', 'exports' ], function($, exports) {
	
	var resourceName = "user";
	var baseURL = $('meta[name="app-data"]').data('context-path');
	
	exports.register = function(resourceEntity) {
		return $.ajax({
			cache : false,
			url : baseURL + '/user/register/',
			type : 'POST',
			data : resourceEntity
		}).promise();
	};

	exports.getUserById = function(id) {
		return $.ajax({
			cache : false,
			url : baseURL + '/user/get/' + encodeURI(id) + '/',
			type : 'GET',
			data : {
			}
		}).promise();
	};

});