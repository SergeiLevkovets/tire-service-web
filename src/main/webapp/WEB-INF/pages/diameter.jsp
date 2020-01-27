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
                    <div class="row">
                        <div class="panel-title">${tableName}</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>${firstFieldName}</th>
                                <th>Опереации</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${elemList}" var="elem">
                                <tr>
                                    <td>${elem.id}</td>
                                    <td>${elem[firstFieldArticle]}</td>
                                    <td>
                                        <c:url value="/authorized/admin/diameter" var="update">
                                            <c:param name="elementChangeUpdate" value="${elem.id}"/>
                                        </c:url>
                                        <a href="${update}" class="btn btn-info"><i
                                                class="glyphicon glyphicon-refresh"></i> Update</a>
                                        <c:url value="/authorized/admin/diameter" var="delete">
                                            <c:param name="elementChangeDelete" value="${elem.id}"/>
                                        </c:url>
                                        <a href="${delete}" class="btn btn-danger"><i
                                                class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form class="form-horizontal panel-body" id="elemChangeForm"
                              action="${pageContext.request.contextPath}/authorized/admin/diameter"
                              method="post">
                            <div class="form-group row">
                                <div class="col-md-1" ${param.elemId == null ? (elemId == null ?'hidden' : '') : ''}>
                                    <label for="elemId">ID</label>
                                    <input type="text" class="form-control" name="elemId" id="elemId"
                                           readonly
                                           value="${param.elemId == null ? elemId : param.elemId}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap.value.nameError}
                                    <label id="labelElemName" for="elemName">${firstFieldName}</label>
                                    <input type="text" class="form-control" name="elemName" id="elemName"
                                           value="${param.elemName == null ? elemName : param.elemName}">
                                </div>
                            </div>
                            <div class="form-inline">
                                <button type="button" class="btn btn-success"
                                        id="saveElemChange">Сохранить
                                </button>
                            </div>
                        </form>
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

