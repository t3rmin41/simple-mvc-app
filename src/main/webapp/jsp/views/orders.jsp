<!DOCTYPE html>
<%@ include file="/jsp/includes.jsp"%>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERVISOR')" var="allowEditOrderStatus" />
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
                <c:choose>
                    <c:when test="${allowEditOrderStatus}">
                        <tr><th>Title</th><th>Price</th><th>Status</th><th>Ordered by</th><th>Created</th><th>Updated</th><th>Actions</th></tr>
                    </c:when>
                    <c:otherwise>
                        <tr><th>Title</th><th>Price</th><th>Status</th><th>Ordered by</th><th>Created</th><th>Updated</th></tr>
                    </c:otherwise>
                </c:choose>
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
    var allowChangeOrderStatus = ${allowEditOrderStatus};
    if (allowChangeOrderStatus) {
        loadAllOrders();
    } else {
        loadUserOrders('<security:authorize access="isAuthenticated()"><security:authentication property="principal.username" /></security:authorize>');
    }
});
function loadAllOrders() {
    var statusList = getOrderStatusMap();
    var allowChangeOrderStatus = ${allowEditOrderStatus};
    $.ajax({
        url: "/orders",
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            tbody = "";
            $.each(data, function(index, value){
                var created = new Date(value.created);
                var createdUTC = created.toUTCString();
                var updatedUTC = undefined;
                if (undefined != value.updated && null != value.updated) {
                    var updated = new Date(value.updated);
                    updatedUTC = updated.toUTCString();
                }
                var statusCell = "";
                var actionsCell = "";
                if (allowChangeOrderStatus) {
                	statusCell = "<td>"+value.status+"</td>";
                    actionsCell = "<td><div style=\"text-decoration: underline; cursor: pointer\" onclick=\"updateOrderStatus("+value.id+")\">update order</div></td>";
                } else {
                    statusCell = "<td>"+value.status+"</td>";
                    actionsCell = "";
                }
                tbody += 
                    "<tr>"+
                        "<td>"+value.productName+"</td>"+
                        "<td>"+value.price+"</td>"+
                        statusCell+
                        "<td>"+value.orderedBy+"</td>"+
                        "<td>"+created.toUTCString()+"</td>"+
                        "<td>"+updatedUTC+"</td>"+
                        actionsCell+
                   "</tr>";
            });
            $("#orders tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
   });
}
function loadUserOrders(username) {
    $.ajax({
        url: "/orders/user/"+username,
        type: "GET",
        success: function(data, textStatus, jQxhr){
            console.log(data);
            tbody = "";
            $.each(data, function(index, value){
                var created = new Date(value.created);
                var createdUTC = created.toUTCString();
                var updatedUTC = undefined;
                if (undefined != value.updated && null != value.updated) {
                    var updated = new Date(value.updated);
                    updatedUTC = updated.toUTCString();
                }
                tbody += 
                    "<tr>"+
                        "<td>"+value.productName+"</td>"+
                        "<td>"+value.price+"</td>"+
                        "<td>"+value.status+"</td>"+
                        "<td>"+value.orderedBy+"</td>"+
                        "<td>"+created.toUTCString()+"</td>"+
                        "<td>"+updatedUTC+"</td>"+
                   "</tr>";
            });
            $("#orders tbody").html(tbody);
        },
        error: function(jqXhr, textStatus, errorThrown){
            console.log(errorThrown);
        }
   });
}
function updateOrderStatus(orderId) {
    var orderBean = {};
    orderBean.id = orderId;
    orderBean.status = status;
    $.ajax({
            url: "/orders/update",
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(orderBean),
            success: function(data, textStatus, jQxhr){
                console.log(data);
                loadOrders();
            },
            error: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
            }
    });
}
function getOrderStatusMap() {
    var statusMap = {};
    $.ajax({
            url: "/orders/statusmap",
            type: "GET",
            success: function(data, textStatus, jQxhr){
                console.log(data);
                statusMap = data;
            },
            error: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
            }
    });
    return statusMap;
}
</script>