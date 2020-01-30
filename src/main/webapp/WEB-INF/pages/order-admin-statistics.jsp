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
                    <div class="panel-title">Редактирование</div>
                </div>
                <div class="content-box-large box-with-header row">
                    <div class="panel-body form-inline">
                        <c:url value="/authorized/admin/tire" var="getTop5">
                            <c:param name="getTOPList" value="5"/>
                        </c:url>
                        <a href="${getTop5}" class="btn btn-info">Показать топ 5 шин</a>
                        <c:url value="/authorized/admin/tire" var="getTop10">
                            <c:param name="getTOPList" value="10"/>
                        </c:url>
                        <a href="${getTop10}" class="btn btn-info">Показать топ 10 шин</a>
                        <c:url value="/authorized/admin/tire" var="getTop50">
                            <c:param name="getTOPList" value="50"/>
                        </c:url>
                        <a href="${getTop50}" class="btn btn-info">Показать топ 50 шин</a>
                    </div>
                    <div class="panel-body row ${elemList == null ?'hidden' : ''}">
                        <div class="panel-title">${tableName}</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>${widthFieldName}</th>
                                <th>${heightFieldName}</th>
                                <th>${diameterFieldName}</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${elemList}" var="elem">
                                <tr>
                                    <td>${elem.width.width}</td>
                                    <td>${elem.height.height}</td>
                                    <td>${elem.diameter.diameter}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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

