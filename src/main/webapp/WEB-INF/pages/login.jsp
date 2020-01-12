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
                    <h1><a href="index.html">Tire Service</a></h1>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="page-content container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-wrapper">
                <form class="box" id="login_form" action="login" method="post">
                    <div class="content-wrap">
                        <h6>Sign In</h6>
                        ${email_login}
                        <input class="form-control" type="text" id="email_login" name="email_login"
                               value="${param.email_login}" placeholder="E-mail address" required>
                        ${password_login}
                        <input class="form-control" type="password" id="password_login" name="password_login"
                               value="${param.password_login}" placeholder="Password" required>
                        <div class="col text-right">
                            <a href="forgot-password">Forgot Password ?</a>
                        </div>
                        <div class="action">
                            <input type="button" class="btn btn-primary signup" id="submit_login" value="Login">
                        </div>
                    </div>
                </form>

                <div class="already">
                    <p>Don't have an account?</p>
                    <a href="registration">Create an account</a>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/pages/script-import.jsp" %>
</body>
</html>