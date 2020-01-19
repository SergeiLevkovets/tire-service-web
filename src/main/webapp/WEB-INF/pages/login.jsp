<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>

<%@include file="/WEB-INF/pages/head.jsp" %>
<body class="login-bg">
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <!-- Logo -->
                <div class="logo">
                    <h1><a href="${pageContext.request.contextPath}/index.html">Tire Service</a></h1>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="page-content container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-wrapper">
                <form class="box" id="login_form" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="content-wrap">
                        <h6>Sign In</h6>
                        ${email_error}
                        <input class="form-control" type="email" id="email" name="email"
                               value="${param.email}" placeholder="E-mail address" required>
                        <h5>or</h5>
                        ${phone_error}
                        <input class="form-control" type="text" id="phone" name="phone"
                               value="${param.phone}" placeholder="Phone number" required>

                        ${password_error}
                        <input class="form-control" type="password" id="password" name="password"
                               value="${param.password}" placeholder="Password" required>
                        <div class="col text-right">
                            <a href="${pageContext.request.contextPath}/forgot-password">Forgot Password ?</a>
                        </div>
                        <div class="action">
                            <input type="button" class="btn btn-primary signup" id="submit_login" value="Login">
                        </div>
                    </div>
                </form>

                <div class="already">
                    <p>Don't have an account?</p>
                    <a href="${pageContext.request.contextPath}/registration">Create an account</a>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/pages/script-import.jsp" %>
</body>
</html>