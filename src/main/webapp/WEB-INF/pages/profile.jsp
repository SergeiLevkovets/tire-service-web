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
                        <form class="form-horizontal panel-body" id="profile_form" action="profile" method="post">
                            <div class="form-group">
                                <label for="profile_name">Full Name</label>
                                ${profile_name}
                                <input type="text" class="form-control"
                                       value="${param.profile_name}" name="profile_name" id="profile_name">
                            </div>
                            <div class="form-group">
                                <label for="profile_email">Email</label>
                                ${profile_email}
                                <input type="email" class="form-control"
                                       value="${param.profile_email}" name="profile_email" id="profile_email">
                            </div>
                            <div class="form-group">
                                <label for="profile_password">Password</label>
                                ${profile_password}
                                <input type="password" class="form-control" name="profile_password"
                                       value="${param.profile_password}" id="profile_password">
                            </div>
                            <div class="form-group">
                                <label for="profile_phone">Phone No</label>
                                ${profile_phone}
                                <input type="text" value="${param.profile_phone}" id="profile_phone" name="profile_phone" class="form-control">
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

