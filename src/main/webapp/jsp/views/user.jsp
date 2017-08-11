<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User edit</title>
        <script type="text/javascript" src="../ui-resources/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
     <div style="float:right">
        <a href="logout">Logout</a>
    </div>
    <div>
        <form id="edituser">
            <table border="1">
                <thead>
                    <tr><td colspan="2">Edit user</td></tr>
                </thead>
                <tbody>
                    <tr><td>Username: </td><td><input type="text" name="username" value=""></td></tr>
                    <tr><td>Password: </td><td><input type="password" name="password" value=""></td></tr>
                    <tr><td>Roles: </td>
                        <td>
                            <input type="checkbox" name="roles[]" value="ADMIN">ADMIN <br />
                            <input type="checkbox" name="roles[]" value="USER">USER <br />
                            <input type="checkbox" name="roles[]" value="GUEST">GUEST <br />
                        </td>
                    <tr><td colspan="2"><input type="submit" value="Edit user" /></td></tr>
                </tbody>
            </table>
        </form>
    </div>
    <div>
        <a href="/usersPage">Go to previous page</a>
    </div>
    </body>
</html>
<script>
function loadUser() {
    $.ajax({
        url: "/users/"+window.location.href.substring(window.location.href.lastIndexOf('/')+1),
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            $("#edituser input[name=username]").val(data.username);
            $.each(data.roles, function(index, rolename) {
                $("#edituser input[name='roles[]']").filter(function(i, obj) {
                    return obj.value && obj.value == rolename;
                }).attr('checked', true);
            });
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
   });
}
$("#edituser").submit(function(e) {
    //prevent Default functionality
    e.preventDefault();
    var userBean = {};
    userBean.username = $("#adduser input[name=username]").val();
    userBean.password = $("#adduser input[name=password]").val();
    var roles = [];
    $("#adduser input[name='roles[]']:checked").each(function(){
        roles.push($(this).val());
    });
    userBean.roles = roles;
    $.ajax({
            url: "/users/edit/"+window.location.href.substring(window.location.href.lastIndexOf('/')+1),
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(userBean),
            success: function(data, textStatus, jQxhr){
                console.log(data);
                $("#adduser").get(0).reset();
                loadAllUsers();
            },
            error: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
            }
    });
});
$(document).ready(function(){
    loadUser();
    console.log("User ready!");
});
</script>