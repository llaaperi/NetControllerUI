<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>NetController</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.0/css/bootstrap.min.css">
  </head>
  <body>
  
	<div class="container">
		<h1>NetController</h1>
		<div class="page">
		</div>
    </div>
    
    <script type="text/template" id="sensor-list-template">
    	<h2>Testing </h2>
    </script>
    
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.5.2/underscore-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/backbone.js/1.1.0/backbone-min.js"></script>
    
    <script>
    
    
    $.ajaxPrefilter(function(options, originalOptions, jqXHR){
    	options.url = 'http://localhost:8080/netcontroller' + options.url;
    });
    
    var Sensors = Backbone.Collection.extend({
    	url: '/sensors'
    });
    
    var SensorList = Backbone.View.extend({
    	el:'.page',
    	render: function(){
    		var that = this;
    		var sensors = new Sensors();
    		sensors.fetch({
    			success: function(sensors){
    				var template = _.template($('#sensor-list-template').html(), {sensors: sensors.models});
    				that.$el.html(template);
    			}
    		});
    	}
    });

    var Router = Backbone.Router.extend({
    	
    	routes:{
    		'':'home'
    	}
    	
    });

    var sensorList = new SensorList();
    var router = new Router();
    router.on('route:home', function(){
    	sensorList.render();
    });

    Backbone.history.start();
    
    </script>
    
    
  </body>
</html>