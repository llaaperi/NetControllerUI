<!DOCTYPE html>
<html ng-app="myApp">
  <head>
    <meta charset="utf-8">
    <title>NetController</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.0/css/bootstrap.min.css">
  </head>
  <body>
  
	<div>
	  <div ng-controller="SensorController">
	  
	  	<div class="container">
		<h1>NetController</h1>
		<div class="page">
		
		  <div>
		  		
		  		<ul>
		  			
		  			<li ng-repeat="sensor in sensors">{{sensor.id}} = {{sensor.value}}</li>
		  		
		  		</ul>
		  		
		  </div>
		  
		
		</div>
</div>
	  </div>
    </div>
    
    <script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.10/angular.min.js"></script>
    
    <script>
      var myApp = angular.module('myApp', []);
      
      myApp.config(function ($routeProvider){
    	  $routeProvider
    	  .when('/view',
    	  {
    		  controller: 'SensorController',
    		  templateUrl: 'js/view.html'
    	  })
    	  .otherwise({redirectTo: '/'});
      });
      
      /*
      myApp.factory('remoteFactory', function(){
    	  var sensors = [{id:1, value:2}, {id:2, value:3}];
    	  var factory = {};
    	  factory.getSensors = function (){
    		  return sensors;
    	  };
      });
      */
      myApp.controller('SensorController', function ($scope){
    	  $scope.sensors = [{id:1, value:2}, {id:2, value:3}];
      });
    </script>
    
    
  </body>
</html>