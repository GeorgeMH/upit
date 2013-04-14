
$(document).ready(function(){
	
	var basePage = new BasePageViewModel();
	
    //ko.applyBindings(basePage);

    // knockback.js / backbone.js GO 
});
	

function BasePageViewModel(){
	this.firstName = ko.observable("Foo");
	this.lastName = ko.observable("Bar");
	
	this.fullName = ko.computed(function() {
        return this.firstName() + " " + this.lastName();
    }, this);
}