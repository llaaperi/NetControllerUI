<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
  	<base href="/netcontroller/"/>
    <title>NetController</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-switch/2.0.1/css/bootstrap3/bootstrap-switch.min.css">
    <link href="css/netcontroller.css" rel="stylesheet">
  </head>
  <body>
  
  <div class="navbar navbar-inverse navbar-static-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">NetController</a>
          <a class="navbar-brand" href="settings">Settings</a>
        </div>
        <div class="navbar-collapse collapse">
          <form class="navbar-form navbar-right">
          
            <!--
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
             -->
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </div>
  
  
	<div class="container">
	
	  <div class="page-header">
	  	<h1>System status</h1>
	  </div>
      
      <div class="row">
        <div class="col-sm-6">
          <div class="panel panel-primary">
            <div class="panel-heading">
			  <h3 class="panel-title">Sensors</h3>
			</div>
            <div class="list-group">
              
              <!-- ADD SENSORS -->
              <c:if test="${not empty sensors}">
                <c:forEach var="sensor" varStatus="status" items="${sensors}">
                  
                  <div class="list-group-item relay-list-group-item">
                    <div class="row">
                      <div class="col-sm-8" align="left">
                        <a href="sensor?id=${sensor.id}" class="list-group-item">
                          <h4 class="list-group-item-heading">${sensor.name}</h4>
                          <p class="list-group-item-text">
                            Min: ${sensor.min} Max: ${sensor.max}
                          </p>
                        </a>
                      </div>
                      <div class="col-sm-4" align="center">
                        <p id="sensor${sensor.id}" style="font-size: 24px;">
                          ${sensor.value}
                        </p>
                      </div>
                    </div>
                  </div> <!-- relay-list-group-item -->
                  
                </c:forEach>
              </c:if>
              
            </div> <!-- end list -->
          </div> <!-- end panel -->
        </div>
        
        <div class="col-sm-6">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title">Relays</h3>
			</div>
            
            <div class="list-group">
              
              <!-- ADD RELAYS -->
              <c:if test="${not empty relays}">
                <c:forEach var="relay" varStatus="status" items="${relays}">
                
                
                  <div class="list-group-item relay-list-group-item">
                    <div class="row">
                  	  <div class="col-sm-8" align="left">
                  	    <a href="relay" class="list-group-item">
                          <h4 class="list-group-item-heading">${relay.name}</h4>
                          <p id="state${relay.id}" align="left" class="list-group-item-text">
                            <c:if test="${relay.state}">
                              <p id="state${relay.id}text" style="color:green">Current state: ${relay.state}</p>
                            </c:if>
                            <c:if test="${not relay.state}">
                              <p id="state${relay.id}text">Current state: ${relay.state}</p>
                            </c:if>
                            
                          </p>
                        </a>
                      </div>
                      <div class="col-sm-4" align="center">
                        <input id="${relay.id}" type="checkbox" onchange="relayToggle(${relay.id})" <c:if test="${relay.state}">checked</c:if>>
                      </div>
                    </div>
                  </div> <!-- relay-list-group-item -->
                  
                </c:forEach>
              </c:if>
              
            </div> <!-- end list -->
          </div> <!-- end panel -->
        </div>
        
      </div>
      
      
    </div>
    
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-switch/2.0.1/js/bootstrap-switch.min.js"></script>
    <script src="js/rainbowvis.js"></script>
    
    <script type="text/javascript">
      $(function(){
	    // initialize all the inputs
	    $('input[type="checkbox"],[type="radio"]').bootstrapSwitch();
	    
	    var rainbow = new Rainbow(); 
	    rainbow.setNumberRange(-20, 30);
	    rainbow.setSpectrum('blue', 'red');
	    $("#sensor0").css("color", "#"+rainbow.colourAt(-20));
	    $("#sensor1").css("color", "#"+rainbow.colourAt(30));
	    $("#sensor2").css("color", "#"+rainbow.colourAt(30));
      });
      
      var last_call = 0;
      function relayToggle(id){
    	  //Remove possible duplicate events
    	  var time = new Date().getTime();
    	  if((time - last_call) > 100){
    		  last_call = time;
    		  
    		  console.log("Toggle relay " + id);
    		  $.ajax({
        		  type: "POST",
        		  url: "relay/toggle",
        		  data: {id:id},
        		  success: function(){
        			  console.log("OK");
        			  if($("#"+id).is(":checked")){
        				  $("#state"+id+"text").text("Current state: true").css("color","green");
        			  }else{
        				  $("#state"+id+"text").text("Current state: false").css("color","");
        			  }
        			  
        		  }
			  });
    	  }
      }
      
    </script>
    
    
    
  </body>
</html>