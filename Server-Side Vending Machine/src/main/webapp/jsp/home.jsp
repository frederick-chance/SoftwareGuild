<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/vend.css" rel="stylesheet">
    </head>

    <body>
        <div class="container text-center">

            <!-- Title Container -->
            <div class="col-sm-12 header">
                <strong><h1>Vending Machine</h1></strong>
            </div>

            <hr>

            <div class="col-sm-12">

                <!-- Item Inventory Container -->
                <div class="col-sm-8" id="inventory">
                    <c:forEach var="item" items="${inventory}">
                        <a href="select?barcode=${item.barcode}">
                            <div class="col-sm-4 item"> 
                                <p class="item-barcode">${item.barcode}</p>
                                <p class="item-name">${item.name}</p>
                                <p class="item-price">$${item.value}</p>
                                <p class="item-quantity">Quantity Left: ${item.quantity}</p>
                            </div>
                        </a>
                    </c:forEach>
                </div>

                <!-- Panel Container -->
                <div class="col-sm-4">

                    <div class="col-sm-12 panel" id="total-in-panel">
                        <div class="col-sm-12 form-group">
                            <label for="total-display">Total $ In</label>
                            <input class="form-control" id="total-display" value="${total}"readonly>
                        </div>

                        <form role="form" method="POST" action="deposit">
                            <div class="col-sm-12 form-group">
                                <button type="submit" name="deposit" class="deposit-btn col-sm-6 btn-secondary" value="1.00">Add Dollar</button>
                                <button type="submit" name="deposit" class="deposit-btn col-sm-6 btn-secondary" value="0.25">Add Quarter</button>
                                <button type="submit" name="deposit" class="deposit-btn col-sm-6 btn-secondary" value="0.10">Add Dime</button>
                                <button type="submit" name="deposit" class="deposit-btn col-sm-6 btn-secondary" value="0.05">Add Nickel</button>
                            </div>
                        </form>
                    </div>

                    <div class="col-sm-12 panel" id="message-panel">
                        <div class="col-sm-12 form-group">
                            <label for="message-display">Message</label>
                            <input class="form-control" id="message-display" value="${message}" readonly>
                        </div>

                        <div class="col-sm-12 form-group form-inline">
                            <label for="selection-display">Item:</label>
                            <input class="form-control" id="selection-display" value="${selection}" readonly>
                        </div>

                        <form role="form" method="POST" action="vend">
                            <div class="col-sm-12 form-group">
                                <button type="submit" id="purchase-btn" class="btn-secondary">Make Purchase</button>
                            </div>
                        </form>
                    </div>

                    <div class="col-sm-12 panel" id="change-panel">
                        <div class="col-sm-12 form-group">
                            <label for="change-display">Change</label>
                            <input class="form-control" id="change-display" value="${change}" readonly>
                        </div>

                        <form role ="form" methods="GET" action="change">
                            <div class="col-sm-12 form-group">
                                <button type="submit" id="return-btn" class="btn-secondary">Change Return</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>

</html>