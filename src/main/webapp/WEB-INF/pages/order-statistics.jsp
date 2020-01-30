<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@include file="/WEB-INF/pages/head.jsp" %>
<body>
<%@include file="/WEB-INF/pages/header.jsp" %>
<div class="page-content">
    <div class="row">
        <div class="col-md-2">
            <%@include file="/WEB-INF/pages/sidebar.jsp" %>
        </div>
        <div class="col-md-10">
            <div class="col-md-12 content-box-large">
                <div class="content-box-header row">
                    <div class="panel-title">Статистика по заказам</div>
                </div>
                <div class="content-box-large box-with-header row">
                    <form class="form-horizontal panel-body" id="OrderStatistic_form"
                          action="${pageContext.request.contextPath}/authorized/order-statistics"
                          method="get">
                        <div class="form-group row">
                            <div class="col-md-3">
                                ${errorMap["startDateError"]}
                                <label for="startDate">Дата с</label>
                                <input type="date" class="form-control" name="startDate" id="startDate"
                                       value="${param.startDate}">
                            </div>
                            <div class="col-md-3">
                                ${errorMap["endDateError"]}
                                <label for="endDate">Дата по</label>
                                <input type="date" class="form-control" name="endDate" id="endDate"
                                       value="${param.endDate}">
                            </div>
                        </div>
                        <div class="form-inline">
                            <button type="submit" class="btn btn-success">Показать
                            </button>
                        </div>
                    </form>
                    <div class="panel-body row ${OrderStatisticList == null ?'hidden' : ''}">
                        <div class="panel-title">Заказы</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Пользователь</th>
                                <th>Шина</th>
                                <th>Тип</th>
                                <th>Дата</th>
                                <th>стоимость</th>
                                <th>Подробности заказа</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${OrderStatisticList}" var="OrderStatistic">
                                <tr>
                                    <td>${OrderStatistic.order.id}</td>
                                    <td>${OrderStatistic.order.user.name}</td>
                                    <td>${OrderStatistic.order.tire.width.width}/${OrderStatistic.order.tire.height.height}/${OrderStatistic.order.tire.diameter.diameter}</td>
                                    <td>${OrderStatistic.order.type.type}</td>
                                    <td>${OrderStatistic.order.date}</td>
                                    <td>${OrderStatistic.totalPrice}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#exampleModal${OrderStatistic.order.id}">
                                            Подробнее
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="5"></td>
                                <td>${allTotalPrice}</td>
                            </tr>
                            </tbody>
                        </table>

                        <c:forEach items="${OrderStatisticList}" var="OrderStatistic">
                        <div class="modal fade" id="exampleModal${OrderStatistic.order.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Элементы заказа</h5>
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Имя</th>
                                                <th>Артикул</th>
                                                <th>Цена (бел. руб)</th>
                                                <th>Количество</th>
                                                <th>Общая стоимость (бел. руб)</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${OrderStatistic.orderServiceItemPriceDTOList}"
                                                       var="orderServiceItemPrice">
                                                <tr>
                                                    <td>${orderServiceItemPrice.serviceItemPrice.serviceItem.name}</td>
                                                    <td>${orderServiceItemPrice.serviceItemPrice.serviceItem.article}</td>
                                                    <td>${orderServiceItemPrice.count}</td>
                                                    <td>${orderServiceItemPrice.serviceItemPrice.price}</td>
                                                    <td>${orderServiceItemPrice.totalPrice}</td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td colspan="4"></td>
                                                <td>${OrderStatistic.totalPrice}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/footer.jsp" %>
<%@include file="/WEB-INF/pages/script-import.jsp" %>

</body>
</html>

