<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR', 'ROLE_MANAGER')" var="allowEditProduct" />
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
    <div>
        <form id="addproduct">
            <table border="1">
                <thead>
                    <tr><td colspan="2">Add new product</td></tr>
                </thead>
                <tbody>
                    <tr><td>Product title: </td><td><input type="text" name="title" value=""></td></tr>
                    <tr><td>Price: </td><td><input type="text" name="price" value=""></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Create product" /></td></tr>
                </tbody>
            </table>
        </form>
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
function resetProductAddForm() {
    $("#addproduct").get(0).reset();
}
function loadAllProducts() {
    var allowEditProduct = ${allowEditProduct};
    $.ajax({
        url: "/products",
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            tbody = "";
            if (allowEditProduct) {
                $.each(data, function(index, product){
                    tbody += "<tr>"+
                      "<td><a href=\"/productview/"+product.id+"\">"+product.title+"</a></td>"+
                      "<td>"+product.price+"</td>"+
                      "<td>"+
                         "<div style=\"text-decoration: underline; cursor: pointer\" onclick=\"addToCart("+product.id+",'"+product.price+"','new')\">Add to cart</div>"+
                         "<div style=\"text-decoration: underline; cursor: pointer\" onclick=\"deleteProduct("+product.id+")\">Delete product</div>"+
                      "</td>"+
                    "</tr>";
                });
            } else {
                $.each(data, function(index, product){
                    tbody += "<tr>"+
                      "<td>"+product.title+"</td>"+
                      "<td>"+product.price+"</td>"+
                      "<td><div style=\"text-decoration: underline; cursor: pointer\" onclick=\"addToCart("+product.id+",'"+product.price+"','new')\">Add to cart</div></td>"+
                    "</tr>";
                });
            }
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
function deleteProduct(id) {
    //e.preventDefault();
    $.ajax({
        url: "/products/delete/"+id,
        type: "DELETE",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            resetProductAddForm();
            loadAllProducts();
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
            resetProductAddForm();
            loadAllProducts();
        }
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