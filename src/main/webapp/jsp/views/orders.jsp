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
                var created = new Date(value.created);
                var updated = undefined;
                if (undefined != value.updated) {
                    var updated = new Date(value.updated);
                }
                tbody += "<tr><td>"+value.productName+"</td><td>"+value.price+"</td><td>"+value.status+"</td><td>"+created.toUTCString()+"</td><td>"+updated.toUTCString()+"</td></tr>";
            });
            $("#orders tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
   });
});
</script>