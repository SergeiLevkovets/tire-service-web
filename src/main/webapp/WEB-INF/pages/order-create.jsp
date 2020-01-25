<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <!-- content -->
            <div class="row col-md-12 content-box-large">
                <div class="panel-heading">
                    <div class="panel-title">Оформление заказа</div>
                </div>
                <div class="panel-body">
                    <div class="panel-body">

                        <form name="order" id="order_form"
                              action="${pageContext.request.contextPath}/authorized/order-create"
                              method="get">

                            <fieldset class="content-box-large">
                                <c:url value="/authorized/order-create" var="car_url">
                                    <c:param name="type" value="car"/>
                                </c:url>
                                <c:url value="/authorized/order-create" var="bus_url">
                                    <c:param name="type" value="bus"/>
                                </c:url>
                                <c:url value="/authorized/order-create" var="truck_url">
                                    <c:param name="type" value="truck"/>
                                </c:url>
                                <div class="form-group">
                                    <div class="btn-group btn-group-toggle" data-toggle="buttons" id="typeParam">
                                        <label id="carBtn"
                                               class="btn btn-info ${param.type == null || param.type == 'car' ? 'active' : ''}">
                                            <input type="radio" name="type" id="car" value="car" onchange="window.location = '${car_url}'" checked required>
                                            Легвой
                                        </label>
                                        <label id="busBtn" class="btn btn-info ${param.type == 'bus' ? 'active' : ''}">
                                            <input type="radio" name="type" id="bus"
                                                   value="bus" ${param.type == 'bus' ? 'checked' : ''} onchange="window.location = '${bus_url}'" required >
                                            Микроавтобус / джип
                                        </label>
                                        <label id="truckBtn"
                                               class="btn btn-info ${param.type == 'truck' ? 'active' : ''}">
                                            <input type="radio" name="type" id="truck"
                                                   value="truck" ${param.type == 'truck' ? 'checked' : ''} onchange="window.location = '${truck_url}'" required>
                                            Грузовой
                                        </label>
                                    </div>
                                </div>
                            </fieldset>

                            <fieldset class="content-box-large">
                                <div class="row panel-heading">
                                    <div class="panel-title"> Размер и количество колес</div>
                                </div>
                                <div class="form-group">
                                    <label>Количество колес</label>
                                    ${wheelCount}
                                    <div class="btn-toolbar mb-3" role="toolbar"
                                         aria-label="Toolbar with button groups">
                                        <div class="btn-group mr-2" id="car_btn_count" role="group"
                                             aria-label="First group">
                                            <button type="button" class="btn btn-default"
                                                    onclick="$('#wheelCount').val(1)">1
                                            </button>
                                            <button type="button" class="btn btn-default"
                                                    onclick="$('#wheelCount').val(2)">2
                                            </button>
                                            <button type="button" class="btn btn-default"
                                                    onclick="$('#wheelCount').val(3)">3
                                            </button>
                                            <button type="button" class="btn btn-default"
                                                    onclick="$('#wheelCount').val(4)">4
                                            </button>
                                        </div>
                                        <div class="btn-group col-sm-3">
                                            <input type="number" class="form-control" id="wheelCount"
                                                   value="${param.wheelCount}" name="wheelCount" min="1"
                                                   required>
                                        </div>
                                    </div>
                                </div>

                                <div class="content-box-large">
                                    <div class="form-group row" id="wheel_size">
                                        <div class="col-sm-3">
                                            <label>Ширина колеса</label>
                                            ${width}
                                            <select class="form-control" name="width" id="width"
                                                    required>
                                                <option hidden></option>
                                                <c:forEach items="${widthList}" var="width">
                                                    <option ${paramValues.width.stream().anyMatch(v->fn:startsWith(v, width.width)).get() ? 'selected' : ''}>${width.width}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-3">
                                            <label>Высота профиля</label>
                                            ${height}
                                            <select class="form-control" name="height"
                                                    id="height" required>
                                                <option hidden></option>
                                                <c:forEach items="${heightList}" var="height">
                                                    <option ${paramValues.height.stream().anyMatch(v->fn:containsIgnoreCase(v, height.height)).get() ? 'selected' : ''}>${height.height}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-3">
                                            <label>Диаметр диска</label>
                                            ${diameter}
                                            <select class="form-control" name="diameter" id="diameter" required>
                                                <option hidden></option>
                                                <c:forEach items="${diameterList}" var="diameter">
                                                    <option ${paramValues.diameter.stream().anyMatch(v->fn:startsWith(v, diameter.diameter)).get() ? 'selected' : ''}>${diameter.diameter}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row" id="wheel_save_btn">
                                        <div class="col-sm-6 ">
                                            <button type="button" class="btn btn-default" id="tireSaveBtn"
                                                    value="${pageContext.request.contextPath}/authorized/tire-save">
                                                Сохранить в базу
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div id="operation_div">
                                    <div class="content-box">
                                        <div class="row panel-heading">
                                            <div class="panel-title">Основные операции</div>
                                        </div>
                                        <div class="row btn">
                                            <button type="button" class="btn btn-default" id="complex"
                                                    name="complex">Комплекс
                                            </button>
                                        </div>
                                        <c:forEach items="${serviceItemListByType}" var="serviceItem">
                                            <div class="observable content-box-header row">
                                                <div class="btn-toolbar col-md-4" role="toolbar"
                                                     aria-label="Toolbar with button groups">
                                                    <div class="btn-group mr-2" role="group"
                                                         aria-label="First group">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val('')">
                                                            0
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(1)">
                                                            1
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(2)">
                                                            2
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(3)">
                                                            3
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(4)">
                                                            4
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-dollar"></i></span>
                                                        <input type="number" class="form-control" id="${serviceItem.article}"
                                                               name="${serviceItem.article}" min="1" value="${param.get(serviceItem.article)}"
                                                        >
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-check"></i></span>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="panel-title">${serviceItem.name}</div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="content-box">
                                        <div class="row panel-heading">
                                            <div class="panel-title">Дополнительные операции</div>
                                        </div>
                                        <c:forEach items="${serviceItemListUniversal}" var="serviceItem">
                                            <div class="observable content-box-header row">
                                                <div class="btn-toolbar col-md-4" role="toolbar"
                                                     aria-label="Toolbar with button groups">
                                                    <div class="btn-group mr-2" role="group"
                                                         aria-label="First group">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val('')">
                                                            0
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(1)">
                                                            1
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(2)">
                                                            2
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(3)">
                                                            3
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#${serviceItem.article}').val(4)">
                                                            4
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-dollar"></i></span>
                                                        <input type="number" class="form-control" id="${serviceItem.article}"
                                                               name="${serviceItem.article}" min="1" value="${param.get(serviceItem.article)}">
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-check"></i></span>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="panel-title">${serviceItem.name}</div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="observable content-box form-group row">
                                        <div class="row panel-heading">
                                            <div class="panel-title">Вентили</div>
                                        </div>
                                        <div class="btn-toolbar col-md-3" role="toolbar"
                                             aria-label="Toolbar with button groups">
                                            <div class="btn-group mr-2" id="btn_" role="group"
                                                 aria-label="First group">
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#valveCount').val('')">0
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#valveCount').val(1)">1
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#valveCount').val(2)">2
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#valveCount').val(3)">3
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#valveCount').val(4)">4
                                                </button>
                                            </div>
                                        </div>
                                        <div class="has-success col-md-3">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-dollar"></i></span>
                                                <input type="number" class="form-control" id="valveCount"
                                                       name="valveCount" min="1" value="${param.valveCount}">
                                                <span class="input-group-addon"><i class="fa fa-check"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            ${valveError}
                                            <select class="form-control" id="valve" name="valve">
                                                <option hidden></option>
                                                <c:forEach items="${valveList}" var="valveItem">
                                                    <option ${paramValues.valve.stream().anyMatch(v->v == valveItem.article).get() ? 'selected' : ''}
                                                            value="${valveItem.article}">${valveItem.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="observable content-box form-group row">
                                        <div class="row panel-heading">
                                            <div class="panel-title">Латки</div>
                                        </div>
                                        <div class="btn-toolbar col-md-3" role="toolbar"
                                             aria-label="Toolbar with button groups">
                                            <div class="btn-group mr-2" id="btn" role="group"
                                                 aria-label="First group">
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#patchCount').val('')">0
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#patchCount').val(1)">1
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#patchCount').val(2)">2
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#patchCount').val(3)">3
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#patchCount').val(4)">4
                                                </button>
                                            </div>
                                        </div>
                                        <div class="has-success col-md-3">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-dollar"></i></span>
                                                <input type="number" class="form-control" id="patchCount"
                                                       name="patchCount" min="0" value="${param.patchCount}">
                                                <span class="input-group-addon"><i class="fa fa-check"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            ${patch_type_error}
                                            <select class="form-control" id="patch" name="patch">
                                                <option hidden></option>
                                                <c:forEach items="${patchList}" var="patchItem">
                                                    <option ${paramValues.patch.stream().anyMatch(v->fn:startsWith(v, patchItem.article)).get() ? 'selected' : ''}
                                                            value="${patchItem.article}">${patchItem.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <div class="col-sm-4 ">
                                            <input type="button" class="btn btn-default" value="Очистить форму">
                                        </div>
                                        <div class="col-sm-4 ">
                                            <input type="button" class="btn btn-primary" id="submit_order"
                                                   value="Оформить заказ">
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
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