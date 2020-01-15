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
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${servicePriceList}" var="servicePrice">
                                    <tr>
                                        <td>${servicePrice.id}</td>
                                        <td>${servicePrice.name}</td>
                                        <td>${servicePrice.price}</td>
                                        <td>
                                            <c:url value="/service-price" var="update_url">
                                                <c:param name="servicePrice_update" value="${servicePrice.id}"/>
                                            </c:url>
                                            <a href="${update_url}" id="update_url">update</a>

                                            <c:url value="/service-price" var="delete_url">
                                                <c:param name="servicePrice_delete" value="${servicePrice.id}"/>
                                            </c:url>
                                            <a href="${delete_url}" id="delete_url">delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row">
                            <form class="form-horizontal panel-body" id="service_price_form"
                                  action="${pageContext.request.contextPath}/authorized/service-price" method="post">
                                <div class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" class="form-control"
                                           value="${param.name}" name="name" id="name">
                                </div>
                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <input type="number" class="form-control"
                                           value="${param.price}" name="price" id="price">
                                </div>
                                <input type="submit" class="btn btn-success" name="save" value="Save Service Price">
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

