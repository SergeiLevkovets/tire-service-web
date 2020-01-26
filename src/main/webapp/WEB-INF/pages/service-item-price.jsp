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
                                <c:forEach items="${serviceItemPriceList}" var="serviceItem">
                                    <tr>
                                        <td>${serviceItem.id}</td>
                                        <td>${serviceItem.serviceItem.name}</td>
                                        <td>${serviceItem.serviceItem.article}</td>
                                        <td>${serviceItem.type.type}</td>
                                        <td>${serviceItem.diameter.diameter}</td>
                                        <td>${serviceItem.price}</td>
                                        <td>
                                            <c:url value="/authorized/admin/service-item-price" var="update">
                                                <c:param name="serviceItemPrice_update" value="${serviceItem.id}"/>
                                            </c:url>
                                            <a href="${update}" class="btn btn-info" id="update"><i
                                                    class="glyphicon glyphicon-refresh"></i> Update</a>
                                            <c:url value="/authorized/admin/service-item-price" var="delete">
                                                <c:param name="serviceItemPrice_delete" value="${serviceItem.id}"/>
                                            </c:url>
                                            <a href="${delete}" class="btn btn-danger" id="delete"><i
                                                    class="glyphicon glyphicon-remove"></i> Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <c:url value="/authorized/admin/element-change" var="getServiceItem">
                            <c:param name="elementChange" value="serviceItemChange"/>
                        </c:url>
                        <c:url value="/authorized/admin/element-change" var="getDiameter">
                            <c:param name="elementChange" value="diameterChange"/>
                        </c:url>
                        <form class="form-horizontal panel-body" id="service_price_form"
                              action="${pageContext.request.contextPath}/authorized/admin/service-item-price"
                              method="post">
                            <div class="form-group row">
                                <div class="col-md-1" ${serviceItemForUpdate.id == null ? (param.serviceItemPriceId == null ? 'hidden' : '') : ''}>
                                    <label for="serviceItemId">ID</label>
                                    <input type="text" class="form-control" name="serviceItemPriceId" id="serviceItemId" readonly
                                           value="${param.serviceItemPriceId == null ? serviceItemForUpdate.id : param.serviceItemPriceId}" >
                                </div>
                                <div class="col-md-3">
                                    <label>ServiceItem (<a href="${getServiceItem}">Create New</a>)</label>
                                    ${errorMap.value.serviceItemIdError}
                                    <select class="form-control" id="serviceItem" name="serviceItemId">
                                        <option hidden></option>
                                        <c:forEach items="${serviceItemList}" var="serviceItemElem">
                                            <option ${param.serviceItemId == serviceItemElem.id ? 'selected' : serviceItemForUpdate.serviceItem.id == serviceItemElem.id ? 'selected' : ''} value="${serviceItemElem.id}">${serviceItemElem.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-md-2">
                                    <label>Type</label>
                                    ${errorMap.value.typeIdError}
                                    <select class="form-control" id="type" name="typeId">
                                        <option hidden></option>
                                        <c:forEach items="${typeList}" var="typeElem">
                                            <option ${param.typeId == typeElem.id ? 'selected' : serviceItemForUpdate.type.id == typeElem.id ? 'selected' : ''} value="${typeElem.id}">${typeElem.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-2">

                                    <label>Diameter (<a href="${getDiameter}" id="">Create New</a>) </label>
                                    <select class="form-control" id="diameter" name="diameterId">
                                        <option hidden></option>
                                        <c:forEach items="${diameterList}" var="diameterElem">
                                            <option ${param.diameterid == diameterElem.id ? 'selected' : serviceItemForUpdate.diameter.id == diameterElem.id ? 'selected' : ''} value="${diameterElem.id}">${diameterElem.diameter}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    ${errorMap.value.priceError}
                                    <label for="price">Price</label>
                                    <input type="number" class="form-control" name="price" id="price"
                                           value="${param.price == null ? serviceItemForUpdate.price : param.price}" >
                                </div>
                            </div>
                            <div class="form-inline">
                                <button type="button" class="btn btn-success" name="saveServiceItemPrice"
                                        id="saveServiceItemPrice">Save
                                </button>
                            </div>
                        </form>
                        <div class="row">
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

