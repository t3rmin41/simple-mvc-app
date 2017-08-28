<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products page</title>
        <script type="text/javascript" src="ui-resources/js/jquery-3.2.1.min.js"></script>
    </head>
    <body>
    <div style="float:right">
        <security:authorize access="isAuthenticated()">
            <security:authentication property="principal.username" /> | <a href="logout">Logout</a>
        </security:authorize>
    </div>
    <div>
        <table id="products" border="1">
            <thead>
                <tr><th>Title</th><th>Price</th><th>Actions</th></tr>
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
function loadAllProducts() {
    $.ajax({
        url: "/products",
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            tbody = "";
            $.each(data, function(index, value){
                tbody += "<tr>"+
                  "<td>"+value.title+"</td>"+
                  "<td>"+value.price+"</td>"+
                  "<td><div style=\"text-decoration: underline; cursor: pointer\" onclick=\"addToCart("+value.id+",'"+value.title+"','"+value.price+"','new')\">Add to cart</div></td>"+
                "</tr>";
            });
            $("#products tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
    });
}
function addToCart(productId, title, price, status) {
    //prevent Default functionality
    var orderBean = {};
    orderBean.productId = productId;
    orderBean.productName = title;
    orderBean.price = price;
    orderBean.status = status;
    $.ajax({
            url: "/cart/addOrder",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(orderBean),
            success: function(data, textStatus, jQxhr){
                console.log(data);
                loadAllProducts();
            },
            error: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
            }
    });
}
$(document).ready(function(){
    console.log("Products ready!");
    loadAllProducts();
});
</script>