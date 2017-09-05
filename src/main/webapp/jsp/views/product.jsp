<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product edit</title>
        <script type="text/javascript" src="../ui-resources/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
    <div style="float:right">
        <security:authorize access="isAuthenticated()">
            <security:authentication property="principal.username" /> | <a href="logout">Logout</a>
        </security:authorize>
    </div>
    <div id="updateSuccess"></div>
    <div>
        <form id="editproduct">
            <table border="1">
                <thead>
                    <tr>
                        <td>Edit product</td>
                        <td id="productTitle"></td>
                    </tr>
                </thead>
                <tbody>
                    <input type="hidden" name="productId" value="" />
                    <tr><td>Price: </td><td><input type="text" name="price" value=""></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Edit product" /></td></tr>
                </tbody>
            </table>
        </form>
    </div>
    <div>
        <a href="/productsPage">Go to previous page</a>
    </div>
    </body>
</html>
<script>
function resetProductEditForm() {
    $("#editproduct").get(0).reset();
}
function loadProduct() {
    $.ajax({
        url: "/products/"+window.location.href.substring(window.location.href.lastIndexOf('/')+1),
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            $("#editproduct input[name=productId]").val(data.id);
            $("#editproduct #productTitle").html(data.title);
            $("#editproduct input[name=price]").val(data.price);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
   });
}
$("#editproduct").submit(function(e) {
    //prevent Default functionality
    e.preventDefault();
    var productBean = {};
    productBean.id = $("#editproduct input[name=productId]").val();
    productBean.price = $("#editproduct input[name=price]").val();
    $.ajax({
            url: "/products/update",
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(productBean),
            success: function(data, textStatus, jQxhr){
                console.log(data);
                resetProductEditForm();
                loadProduct();
                $("#updateSuccess").html("Product updated successfully");
                $("#updateSuccess").css("background-color", "#58FF33");
                $("#updateSuccess").css("display", "block");
            },
            error: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
                $("#updateSuccess").html("Failed to update product : "+jqXhr.statusText);
                $("#updateSuccess").css("background-color", "#F35A53");
                $("#updateSuccess").css("display", "block");
                resetProductEditForm();
                loadProduct();
            }
    });
});
$(document).ready(function(){
    loadProduct();
    $("#updateSuccess").css("display", "none");
    console.log("Product ready!");
});
</script>