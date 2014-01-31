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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-switch.css" rel="stylesheet">
    <link href="css/netcontroller.css" rel="stylesheet">
  </head>
  <body>
  
  <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
        <div class="col-md-6">
          <div class="panel panel-primary">
            <div class="panel-heading">
			  <h3 class="panel-title">Sensors</h3>
			</div>
            <div class="list-group">
              
              <!-- ADD SENSORS -->
              <c:if test="${not empty sensors}">
                <c:forEach var="sensor" varStatus="status" items="${sensors}">
                  <a href="sensor?id=${sensor.id}" class="list-group-item">
                    <h4 class="list-group-item-heading">${sensor.name}</h4>
                    <p class="list-group-item-text">
                      Current value: ${sensor.value}
                    </p>
                  </a>
                </c:forEach>
              </c:if>
              
            </div> <!-- end list -->
          </div> <!-- end panel -->
        </div>
        
        <div class="col-md-6">
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
                  	  <div class="col-md-8" align="left">
                  	    <a href="relay" class="list-group-item">
                          <h4 class="list-group-item-heading">${relay.name}</h4>
                          <p align="left" class="list-group-item-text">
                            Current state: ${relay.state}
                          </p>
                        </a>
                      </div>
                      <div class="col-md-4" align="right">
                        <input type="checkbox" onchange="relayToggle(${relay.id})" <c:if test="${relay.state}">checked</c:if>>
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
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-switch.js"></script>
    
    <script type="text/javascript">
      $(function(){
	    // initialize all the inputs
	    $('input[type="checkbox"],[type="radio"]').bootstrapSwitch();
      });
      
      
      function relayToggle(id){
    	console.log("Toggle relay " + id);
      }
      
    </script>
    
    
    
  </body>
</html>