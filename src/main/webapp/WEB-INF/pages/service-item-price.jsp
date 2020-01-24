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
            <div class="row">
                <div class="col-md-12 content-box-large">
                    <div class="panel-heading">
                        <div class="panel-title">Редактирование</div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>ServiceItemName</th>
                                    <th>ServiceItemArticle</th>
                                    <th>Type</th>
                                    <th>Diameter</th>
                                    <th>Price</th>
                                    <th>Operation</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${serviceItemPriceList}" var="serviceItemPrice">
                                    <tr>
                                        <td>${serviceItemPrice.id}</td>
                                        <td>${serviceItemPrice.serviceItem.name}</td>
                                        <td>${serviceItemPrice.serviceItem.article}</td>
                                        <td>${serviceItemPrice.type.type}</td>
                                        <td>${serviceItemPrice.diameter.diameter}</td>
                                        <td>${serviceItemPrice.price}</td>
                                        <td>
                                            <c:url value="/authorized/admin/service-item-price" var="update">
                                                <c:param name="serviceItemPrice_update" value="${serviceItemPrice.id}"/>
                                            </c:url>
                                            <a href="${update}" class="btn btn-info" id="update"><i class="glyphicon glyphicon-refresh"></i> Update</a>
                                            <c:url value="/authorized/admin/service-item-price" var="delete">
                                                <c:param name="serviceItemPrice_delete" value="${serviceItemPrice.id}"/>
                                            </c:url>
                                            <a href="${delete}" class="btn btn-danger" id="delete"><i class="glyphicon glyphicon-remove"></i> Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <form class="form-horizontal panel-body" id="service_price_form"
                                  action="" method="">
                                <div class="form-group row">
                                    <div class="col-md-3">
                                        <label>ServiceItem (<a href="" id="">Create New</a>)</label>
                                        ${serviceItemError}
                                        <select class="form-control" id="serviceItem" name="serviceItem">
                                            <option ${serviceItem == null ? 'hidden' : ''}>${serviceItem}</option>
                                            <c:forEach items="${serviceItemList}" var="serviceItemElem">
                                                <option ${paramValues.serviceItem.stream().anyMatch(v->v == serviceItemElem.name).get() ? 'selected' : ''}>${serviceItemElem.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <label>Type (<a href="" id="">Create New</a>)</label>
                                        ${typeError}
                                        <select class="form-control" id="type" name="type">
                                            <option ${type == null ? 'hidden' : ''}>${type}</option>
                                            <c:forEach items="${typeList}" var="typeElem">
                                                <option ${paramValues.type.stream().anyMatch(v->v == typeElem.type).get() ? 'selected' : ''}>${typeElem.type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <label>Diameter (<a href="" id="">Create New</a>) </label>
                                        ${diameterError}
                                        <select class="form-control" id="diameter" name="diameter">
                                            <option ${diameter == null ? 'hidden' : ''}>${diameter}</option>
                                            <c:forEach items="${diameterList}" var="diameterElem">
                                                <option ${paramValues.diameter.stream().anyMatch(v->v == diameterElem.diameter).get() ? 'selected' : ''}>${diameterElem.diameter}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <label for="price">Price</label>
                                        <input type="number" class="form-control"
                                               value="${param.price}" name="price" id="price">
                                    </div>
                                </div>
                                <div class="form-inline">
                                    <button type="button" class="btn btn-success" name="saveServiceItemPrice" id="saveServiceItemPrice">Save</button>
                                </div>
                            </form>
                        </div>
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

