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
                        <div class="panel-title">Редактирование профиля</div>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal panel-body" id="profile_form" action="${pageContext.request.contextPath}/authorized/profile" method="post">
                            <div class="form-group">
                                <label for="profile_name">Full Name</label>
                                ${name_error}
                                <input type="text" class="form-control"
                                       value="${name == null ? param.name : name}" name="name" id="profile_name">
                            </div>
                            <div class="form-group">
                                <label for="profile_email">Email</label>
                                ${email_error}
                                <input type="email" class="form-control"
                                       value="${email == null ? param.email : email}" name="email" id="profile_email">
                            </div>
                            <div class="form-group">
                                <label for="profile_password">Password</label>
                                ${password_error}
                                <input type="text" class="form-control" name="password"
                                       value="${password == null ? param.password : password}" id="profile_password">
                            </div>
                            <div class="form-group">
                                <label for="profile_phone">Phone No</label>
                                ${phone_error}
                                <input type="text" value="${phone == null ? param.phone : phone}" id="profile_phone" name="phone" class="form-control">
                            </div>
                            <input type="button" class="btn btn-success" id="profile_submit" value="Update Profile">
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

