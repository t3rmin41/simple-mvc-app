<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Index page</title>
    <script type="text/javascript" src="ui-resources/js/jquery-3.2.1.min.js"></script>
</head>
<body>
    <div>
        <div>
            <h1>Spring Boot JSP Example</h1>
            <h2>Hello <spring:message code="hello.text" /></h2>
             
            Click <strong><a href="login">here</a></strong> to login
        </div>
    </div>
</body>
</html>
<script>
$(document).ready(function(){
    console.log("Ready!");
});
</script>