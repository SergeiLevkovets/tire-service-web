<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@include file="/WEB-INF/pages/head.jsp" %>
<body class="login-bg">
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
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
                <form class="box" id="forgot_form" action="forgot-password" method="post">
                    <div class="content-wrap">
                        <h6>Forgot Password</h6>
                        <p>We will send you a link to reset password.</p>
                        ${email_forgot}
                        <input class="form-control" type="email" id="email_forgot" name="email_forgot"
                               value="${param.email_forgot}" required placeholder="E-mail address">
                        <div class="action">
                            <input type="button" class="btn btn-primary signup"  id="submit_forgot" value="Submit">
                        </div>
                    </div>
                </form>

                <div class="already">
                    <p>Not a member?</p>
                    <a href="registration">Create an account</a>
                </div>
            </div>
        </div>
    </div>
</div>



<%@include file="/WEB-INF/pages/script-import.jsp" %>
</body>
</html>