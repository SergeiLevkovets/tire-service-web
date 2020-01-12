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
                <form class="box" id="registration_form" action="registration" method="post">
                    <div class="content-wrap">
                        <h6>Sign Up</h6>
                        ${email_registration}
                        <input class="form-control" type="text" id="email_reg" name="email_registration"
                               value="${param.email_registration}" placeholder="E-mail address" required>
                        ${phone_registration}
                        <input class="form-control" type="text" id="phone_reg" name="phone_registration"
                               value="${param.phone_registration}" placeholder="Phone number" required>
                        ${password_registration}
                        <input class="form-control" type="password" id="password_reg" name="password_registration"
                               value="${param.password_registration}" placeholder="Password" required>
                        ${confirm_password_registration}
                        <input class="form-control" type="password" id="confirm_password_reg"
                               value="${param.confirm_password_registration}" name="confirm_password_registration" placeholder="Confirm Password" required>
                        <div class="action">
                            <input type="button" class="btn btn-primary signup" id="submit_reg" value="Sign Up">
                        </div>
                    </div>
                </form>

                <div class="already">
                    <p>Have an account already?</p>
                    <a href="login">Login</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/script-import.jsp" %>
</body>
</html>