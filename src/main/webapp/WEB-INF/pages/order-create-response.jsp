<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>


    <div class="content-box-header row">
        <div class="panel-title">Сведения по заказу</div>
    </div>
    <div class="content-box-large box-with-header row">

        <div class="panel-body row">
            <table class="table table-bordered text-center">
                <thead>
                <tr>
                    <th>Имя</th>
                    <th>Артикул</th>
                    <th>Количество</th>
                    <th>Цена (бел. руб)</th>
                    <th>Общая стоимость (бел. руб)</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${serviceItemPricesList}" var="serviceItem">
                    <tr>
                        <td><c:out value="${serviceItem.key.serviceItem.name}"/></td>
                        <td><c:out value="${serviceItem.key.serviceItem.article}"/></td>
                        <td><c:out value="${serviceItem.value}"/></td>
                        <td><c:out value="${serviceItem.key.price}"/></td>
                        <td><c:out value="${serviceItem.key.price * serviceItem.value}"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>${totalPrice}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="row panel-heading">
            <div class="panel-title">Хотите сохранить заказ?</div>
        </div>

        <div class="panel-body row">
            <div class="col-sm-4 ">
                <input type="button" class="btn btn-primary" id="saveFormBtn"
                       value="Сохранить заказ" >
            </div>
            <div class="col-sm-4 ">
                <input type="button" class="btn btn-default" id="cancelFormBtn" value="Отмена">
            </div>
        </div>
    </div>
