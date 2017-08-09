<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users page</title>
        <script type="text/javascript" src="ui-resources/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
     <div style="float:right">
        <a href="logout">Logout</a>
    </div>
    <div>
        <table id="users" border="1">
            <thead>
                <tr><th>Username</th><th>Enabled</th><th>Roles</th></tr>
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
    console.log("Users ready!");
    $.ajax({
        url: "/users",
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            tbody = "";
            $.each(data, function(index, value){
                tbody += "<tr><td>"+value.username+"</td><td>"+value.enabled+"</td><td>"+value.roles.join()+"</td></tr>";
            });
            $("#users tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
   });
});
</script>