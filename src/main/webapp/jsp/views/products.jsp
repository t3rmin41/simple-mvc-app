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
    <div style="display:inline">
        <table id="products" border="1">
            <thead>
                <tr><th>Title</th><th>Price</th><th>Actions</th></tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <div id="submitCartStatus" style="display:none">
    </div>
    <div style="display:inline">
        <h3><span>Cart</span></h3>
        <form id="submitcart">
            <table id="cart" border="1">
                <thead>
                    <tr><th>Title</th><th>Price</th></tr>
                </thead>
                <tbody>
                </tbody>
                <tfoot>
                    <tr><td colspan="2"><input type="submit" value="Submit cart" /></td></tr>
                </tfoot>
            </table>
        </form>
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
                  "<td><div style=\"text-decoration: underline; cursor: pointer\" onclick=\"addToCart("+value.id+",'"+value.price+"','new')\">Add to cart</div></td>"+
                "</tr>";
            });
            $("#products tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
    });
}
function loadCart() {
    $.ajax({
        url: "/cart",
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            tbody = "";
            $.each(data.items, function(index, item){
                tbody += "<tr>"+
                  "<td>"+item.productName+"</td>"+
                  "<td>"+item.price+"</td>"+
                "</tr>";
            });
            $("#cart tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
    });
}
$("#submitcart").submit(function(e) {
    //prevent Default functionality
    e.preventDefault();
    $.ajax({
            url: "/cart/submit",
            type: "POST",
            success: function(data, textStatus, jQxhr){
                console.log(data);
                loadCart();
                $("#submitCartStatus").html("Cart submitted successfully");
                $("#submitCartStatus").css("background-color", "#58FF33");
                $("#submitCartStatus").css("display", "block");
            },
            error: function(jqXhr, textStatus, errorThrown){
                $("#submitCartStatus").html("Failed to submit cart : "+jqXhr.statusText);
                $("#submitCartStatus").css("background-color", "#F35A53");
                $("#submitCartStatus").css("display", "block");
                console.log(errorThrown);
            }
    });
});
function addToCart(productId, price) {
    getOrderStatusMap().done(function(orderStatusMap){
        var orderBean = {};
        orderBean.productId = productId;
        //orderBean.productName = title;
        orderBean.price = price;
        orderBean.status = "PENDING";
        orderBean.orderedBy = '<security:authorize access="isAuthenticated()"><security:authentication property="principal.username" /></security:authorize>';
        $.ajax({
                url: "/cart/addOrder",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(orderBean),
                success: function(data, textStatus, jQxhr){
                    console.log(data);
                    $("#submitCartStatus").html("");
                    $("#submitCartStatus").css("display", "none");
                    loadCart();
                },
                error: function(jqXhr, textStatus, errorThrown){
                    $("#submitCartStatus").html("Failed to add product to cart : "+jqXhr.statusText);
                    $("#submitCartStatus").css("background-color", "#F35A53");
                    $("#submitCartStatus").css("display", "block");
                    console.log(errorThrown);
                }
        });
    });
}
function getOrderStatusMap() {
    return $.ajax({
            url: "/orders/statusmap",
            type: "GET",
            success: function(data, textStatus, jQxhr){
                return data;
            },
            error: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
            }
    });
}
$(document).ready(function(){
    loadAllProducts();
    loadCart();
    console.log("Products ready!");
});
</script>