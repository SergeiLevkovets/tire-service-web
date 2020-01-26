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

                    <fieldset>
                        <c:url value="/authorized/admin/element-change" var="serviceItemChange_url">
                            <c:param name="elementChange" value="serviceItemChange"/>
                        </c:url>
                        <c:url value="/authorized/admin/element-change" var="heightChange_url">
                            <c:param name="elementChange" value="heightChange"/>
                        </c:url>
                        <c:url value="/authorized/admin/element-change" var="widthChange_url">
                            <c:param name="elementChange" value="widthChange"/>
                        </c:url>
                        <c:url value="/authorized/admin/element-change" var="diameterChange_url">
                            <c:param name="elementChange" value="diameterChange"/>
                        </c:url>
                        <div class="form-group">
                            <div class="btn-group btn-group-toggle" data-toggle="buttons" id="typeParam">
                                <label id="serviceItemChangeBtn"
                                       class="btn btn-info ${elementChange == null || elementChange == 'serviceItemChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="serviceItemChange"
                                           value="serviceItemChange" form="elemChangeForm"
                                           onchange="window.location = '${serviceItemChange_url}'" checked required>
                                    Сервисные операции
                                </label>
                                <label id="heightChangeBtn"
                                       class="btn btn-info ${elementChange == 'heightChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="heightChange" form="elemChangeForm"
                                           value="heightChange" ${elementChange == 'heightChange' ? 'checked' : ''}
                                           onchange="window.location = '${heightChange_url}'" required>
                                    Высота профиля шины
                                </label>
                                <label id="widthChangeBtn"
                                       class="btn btn-info ${elementChange == 'widthChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="widthChange" form="elemChangeForm"
                                           value="widthChange" ${elementChange == 'widthChange' ? 'checked' : ''}
                                           onchange="window.location = '${widthChange_url}'" required>
                                    Ширина шины
                                </label>
                                <label id="diameterChangeBtn"
                                       class="btn btn-info ${elementChange == 'diameterChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="diameterChange" form="elemChangeForm"
                                           value="diameterChange" ${elementChange == 'diameterChange' ? 'checked' : ''}
                                           onchange="window.location = '${diameterChange_url}'" required>
                                    Диаметр диска колеса
                                </label>
                            </div>
                        </div>
                    </fieldset>

                    <%--universal--%>

                    <div class="row">
                        <div class="panel-title">${tableName}</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Id</th>

                                <th>${firstFieldName}</th>

                                <th ${elementChange == null || elementChange == 'serviceItemChange'  ? '' : 'hidden'}>${secondFieldName}</th>
                                <th>Опереации</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${elemList}" var="elem">
                                <tr>
                                    <td>${elem.id}</td>
                                    <td>${elem[firstFieldArticle]}</td>
                                    <td ${elementChange == null || elementChange == 'serviceItemChange'  ? '' : 'hidden'}>${elem[secondFieldArticle]}</td>
                                    <td>
                                        <c:url value="/authorized/admin/element-change" var="update">
                                            <c:param name="elementChangeUpdate" value="${elem.id}"/>
                                            <c:param name="elementChange" value="${elementChange}"/>
                                        </c:url>
                                        <a href="${update}" class="btn btn-info"><i
                                                class="glyphicon glyphicon-refresh"></i> Update</a>
                                        <c:url value="/authorized/admin/element-change" var="delete">
                                            <c:param name="elementChangeDelete" value="${elem.id}"/>
                                            <c:param name="elementChange" value="${elementChange}"/>
                                        </c:url>
                                        <a href="${delete}" class="btn btn-danger"><i
                                                class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form class="form-horizontal panel-body" id="elemChangeForm"
                              action="${pageContext.request.contextPath}/authorized/admin/element-change"
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
                                <div class="col-md-2" ${elementChange == null || elementChange == 'serviceItemChange'  ? '' : 'hidden disabled'}>
                                    ${errorMap.value.articleError}
                                    <label id="labelElemArticle" for="elemArticle">${secondFieldName}</label>
                                    <input type="text" class="form-control" name="elemArticle" id="elemArticle"
                                     value="${param.elemArticle == null ? elemArticle : param.elemArticle}">
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

