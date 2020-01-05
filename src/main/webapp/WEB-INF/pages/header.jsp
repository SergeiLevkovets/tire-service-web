<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-5">

                <div class="logo">
                    <h1><a href="index.html">Tire Service</a></h1>
                </div>
            </div>
            <div class="col-md-5">
            </div>
            <div class="col-md-2">
                <div class="navbar navbar-inverse" role="banner">
                    <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-align-justify"></i> Main menu<b class="caret"></b></a>
                                <ul class="dropdown-menu animated fadeInUp">
                                    <%@include file="/WEB-INF/pages/nav-menu.jsp" %>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>