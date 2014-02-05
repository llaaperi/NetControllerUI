var UserList = new Backbone.View.extend({
	el:'.container',
	render: function(){
		this.$el.html('Content');
	}
});

var Router = new Backbone.Router.extend({
	
	routes:{
		'':'home'
	}
	
});

var userList = new UserList();
var router = new Router();
router.on('route:home', function(){
	userList.render();
});

Backbone.history.start();