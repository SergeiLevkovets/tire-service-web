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
                        ${name_error}
                        <input class="form-control" type="text" id="name" name="name"
                               value="${param.name}" placeholder="Name" required>
                        ${email_error}
                        <input class="form-control" type="text" id="email" name="email"
                               value="${param.email}" placeholder="E-mail address" required>
                        ${phone_error}
                        <input class="form-control" type="text" id="phone" name="phone"
                               value="${param.phone}" placeholder="Phone number" required>
                        ${password_error}
                        <input class="form-control" type="password" id="password" name="password"
                               value="${param.password}" placeholder="Password" required>
                        ${confirm_password_error}
                        <input class="form-control" type="password" id="confirm_password"
                               value="${param.confirm_password}" name="confirm_password" placeholder="Confirm Password" required>
                        <div class="action">
                            <input type="button" class="btn btn-primary signup" id="submit_registration" value="Sign Up">
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