<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <script type="text/javascript" src="ui-resources/js/jquery-3.2.1.min.js"></script>
</head>
<body>
    <div>
        <div>
            <h1>Spring Boot JSP Example</h1>
            <h2>Hello <spring:message code="hello.text" /></h2>
             
            Click on this <strong><a href="next">link</a></strong> to visit another page.
        </div>
    </div>
</body>
</html>
<script>
$(document).ready(function(){
    console.log("Ready!");
});
</script>