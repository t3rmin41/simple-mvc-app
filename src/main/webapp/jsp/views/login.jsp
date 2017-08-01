<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
        <script type="text/javascript" src="ui-resources/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
        <form id="loginform">
            <center>
            <table border="1" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Login" /></td>
                    <tr/>
                    <tr>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>
<script>
$(document).ready(function(){
    console.log("Ready!");
    $("#loginform").submit(function(e) {
        //prevent Default functionality
        e.preventDefault();

        //do your own request an handle the results
        $.ajax({
                url: "/loginuser",
                type: 'post',
                data: $("#loginform").serialize(),
                success: function(data, textStatus, jQxhr){
                    console.log(data);
                    if (data.username == $("#loginform input[name=username]").val() && data.password == $("#loginform input[name=password]").val()) {
                       window.location.href = "/logged";
                    }
                },
                error: function(jqXhr, textStatus, errorThrown){
                    console.log(errorThrown);
                }
        });
    });
});
</script>