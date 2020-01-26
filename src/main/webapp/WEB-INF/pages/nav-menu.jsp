<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<li class="current"><a href="${pageContext.request.contextPath}/index.html"> <i class="glyphicon glyphicon-home"></i> Home </a> </li>
<li class="${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/order-create"> <i class="glyphicon glyphicon-shopping-cart"></i> Order </a> </li>
<li class="${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/profile"> <i class="glyphicon glyphicon-user"></i> Profile </a> </li>
<li class="${sessionScope.authorizedUserName != null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/login"> <i class="glyphicon glyphicon-log-in"></i> Log-in </a> </li>
<li class="${sessionScope.authorizedUserName != null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/registration"> <i class="glyphicon glyphicon-floppy-disk"></i> Registration </a> </li>
<li class="${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/logout"> <i class="glyphicon glyphicon-log-out"></i> Logout </a> </li>
<li class="${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/admin/service-item-price"> <i class="glyphicon glyphicon-list"></i> ServiceItemPrice </a> </li>
<li class="${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/admin/element-change"> <i class="glyphicon glyphicon-list"></i> ServiceElements </a> </li>
<li><a href="${pageContext.request.contextPath}/404"> <i class="glyphicon glyphicon-warning-sign"></i> 404 </a> </li>
