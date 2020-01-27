<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<li class="${pageContext.request.servletPath.endsWith("/index.jsp") ? 'current' : ''}"><a href="${pageContext.request.contextPath}/index.html"> <i class="glyphicon glyphicon-home"></i> Главная страница </a> </li>
<li class="${pageContext.request.servletPath.endsWith("/order-create.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/order-create"> <i class="glyphicon glyphicon-shopping-cart"></i> Оформить заказ </a> </li>
<li class="${pageContext.request.servletPath.endsWith("/profile.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/profile"> <i class="glyphicon glyphicon-user"></i> Профиль </a> </li>
<li class="${pageContext.request.servletPath.endsWith("/login.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName != null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/login"> <i class="glyphicon glyphicon-log-in"></i> Войти  </a> </li>
<li class="${pageContext.request.servletPath.endsWith("/registration.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName != null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/registration"> <i class="glyphicon glyphicon-floppy-disk"></i> Регистрация </a> </li>
<li class="submenu ${sessionScope.authorizedUserName == null ? 'hidden' : ''}"><a href="#"><i class="glyphicon glyphicon-list"></i> Редактирование <span class="caret pull-right"></span></a>
    <!-- Sub menu -->
    <ul>
        <li class="${pageContext.request.servletPath.endsWith("/service-item-price.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/admin/service-item-price"> <i class="glyphicon glyphicon-list"></i> Стоимость операций </a> </li>
        <li class="${pageContext.request.servletPath.endsWith("/service-item.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/admin/service-item"> <i class="glyphicon glyphicon-list"></i> Операции </a> </li>
        <li class="${pageContext.request.servletPath.endsWith("/diameter.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/admin/diameter"> <i class="glyphicon glyphicon-list"></i> Диаметр диска </a> </li>
        <li class="${pageContext.request.servletPath.endsWith("/height.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/admin/height"> <i class="glyphicon glyphicon-list"></i> Высота профиля </a> </li>
        <li class="${pageContext.request.servletPath.endsWith("/width.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/authorized/admin/width"> <i class="glyphicon glyphicon-list"></i> Ширина колеса </a> </li>
    </ul>
</li>
<li class="${pageContext.request.servletPath.endsWith("/logout.jsp") ? 'current' : ''} ${sessionScope.authorizedUserName == null ? 'hidden' : ''}" ><a href="${pageContext.request.contextPath}/logout"> <i class="glyphicon glyphicon-log-out"></i> Выход </a> </li>
<li class="${pageContext.request.servletPath.endsWith("/404.jsp") ? 'current' : ''}"><a href="${pageContext.request.contextPath}/404"> <i class="glyphicon glyphicon-warning-sign"></i> 404 </a> </li>