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
                        <div class="panel-title">Пользователи</div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Имя</th>
                                    <th>Почта</th>
                                    <th>Пароль</th>
                                    <th>Телефон</th>
                                    <th>Роль</th>
                                    <th>Операции</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${usersList}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.name}</td>
                                        <td>${user.email}</td>
                                        <td>${user.password}</td>
                                        <td>${user.phone}</td>
                                        <td>${user.role}</td>
                                        <td>
                                            <c:url value="/authorized/admin/users" var="update">
                                                <c:param name="userUpdate" value="${user.id}"/>
                                            </c:url>
                                            <a href="${update}" class="btn btn-info" id="update"><i
                                                    class="glyphicon glyphicon-refresh"></i> Update</a>
                                            <c:url value="/authorized/admin/users" var="delete">
                                                <c:param name="userDelete" value="${user.id}"/>
                                            </c:url>
                                            <a href="${delete}" class="btn btn-danger" id="delete"><i
                                                    class="glyphicon glyphicon-remove"></i> Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <form class="form-horizontal panel-body" id="usersForm"
                              action="${pageContext.request.contextPath}/authorized/admin/users"
                              method="post">
                            <div class="form-group row">
                                <div class="col-md-1" ${userForUpdate.id == null ? (param.userId == null ? 'hidden' : '') : ''}>
                                    <label>ID</label>
                                    <input type="text" class="form-control" name="userId" id="userId" readonly
                                           value="${param.userId == null ? userForUpdate.id : param.userId}">
                                </div>
                                <div class="col-md-3">
                                    <label>Имя</label>
                                    ${errorMap["nameError"]}
                                    <input type="text" class="form-control" name="name" id="name"
                                           value="${param.name == null ? userForUpdate.name : param.name}">
                                </div>

                                <div class="col-md-2">
                                    <label>Почта</label>
                                    ${errorMap['emailError']}
                                    <input type="text" class="form-control" name="email" id="email"
                                           value="${param.email == null ? userForUpdate.email : param.email}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap["passwordError"]}
                                    <label>Пароль</label>
                                    <input type="text" class="form-control" name="password" id="password"
                                           value="${param.password == null ? userForUpdate.password : param.password}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap["phoneError"]}
                                    <label>Номер телефона</label>
                                    <input type="text" class="form-control" name="phone" id="phone"
                                           value="${param.phone == null ? userForUpdate.phone : param.phone}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap["roleError"]}
                                    <label>Роль</label>
                                    <select class="form-control" id="role" name="role">
                                        <option hidden></option>
                                        <option ${param.role == 'user' ? 'selected' : userForUpdate.role == 'user' ? 'selected' : ''}
                                                value="user">user</option>
                                        <option ${param.role == 'admin' ? 'selected' : userForUpdate.role == 'admin' ? 'selected' : ''}
                                                value="admin">admin</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-inline">
                                <button type="button" class="btn btn-success" name="saveUser"
                                        id="saveUser">Save
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

