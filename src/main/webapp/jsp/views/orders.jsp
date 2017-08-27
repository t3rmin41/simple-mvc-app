<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders page</title>
        <script type="text/javascript" src="ui-resources/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
    <div style="float:right">
        <security:authorize access="isAuthenticated()">
            <security:authentication property="principal.username" /> | <a href="logout">Logout</a>
        </security:authorize>
    </div>
    <div>
        <table id="orders" border="1">
            <thead>
                <tr><th>Title</th><th>Price</th><th>Status</th><th>Created</th><th>Updated</th></tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div>
        <a href="/logged">Go to previous page</a>
    </div>
    </body>
</html>
<script>
$(document).ready(function(){
    console.log("Orders ready!");
    $.ajax({
        url: "/orders",
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            tbody = "";
            $.each(data, function(index, value){
                tbody += "<tr><td>"+value.title+"</td><td>"+value.price+"</td><td>"+value.status+"</td><td>"+value.created+"</td><td>"+value.updated+"</td></tr>";
            });
            $("#orders tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
   });
});
</script>