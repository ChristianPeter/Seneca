<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="Viewer">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="lib/angular.min.js">
            </script>
            
        <script src="app/app.js"></script>
    </head>
    <body>
        <h1>The Viewer.....</h1>
        
        <div ng-view></div>
        <!-- load scripts at the end -->
        
        
    </body>
</html>