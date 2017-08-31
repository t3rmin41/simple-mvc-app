<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MANAGER')" var="allowEditOrderStatus" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged in</title>
        <script type="text/javascript" src="ui-resources/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
    <div style="float:right">
        <security:authorize access="isAuthenticated()">
            <security:authentication property="principal.username" /> | <a href="logout">Logout</a>
        </security:authorize>
    </div>
    <div>
      <h3>Successfully logged in</h3>
    </div>
    <div>
        <a href="/productsPage">Go to products page</a>
    </div>
    <security:authorize access="hasAnyRole('ROLE_ADMIN')">
    <div>
        <a href="/usersPage">Go to users page</a>
    </div>
    </security:authorize>
    <div>
        <a href="/ordersPage">Go to orders page</a>
    </div>
    </body>
</html>
<script>
$(document).ready(function(){
    console.log("Ready!");
});
</script>