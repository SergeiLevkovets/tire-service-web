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
                    <div class="container">
                        <ul class="nav nav-pills">
                            <li class="active"><a
                                    href="${pageContext.request.contextPath}/authorized/order-car">Легвой</a></li>
                            <li><a href="${pageContext.request.contextPath}/authorized/order-suv">Микроавтобус /
                                джип</a></li>
                            <li><a href="${pageContext.request.contextPath}/authorized/order-truck">Грузовой</a></li>
                        </ul>
                    </div>

                    <div id="car">
                        <div class="panel-heading">
                            <div class="panel-title">Заказ</div>
                        </div>
                        <div class="panel-body">
                            <form name="order" id="order" action="${pageContext.request.contextPath}/authorized/order-car"
                                  method="get">
                                <fieldset class="content-box-large">
                                    <div class="row panel-heading">
                                        <div class="panel-title"> Размер и количество колес</div>
                                    </div>
                                    <div class="form-group">
                                        <label>Количество колес</label>
                                        ${wheel_count}
                                        <div class="btn-toolbar mb-3" role="toolbar"
                                             aria-label="Toolbar with button groups">
                                            <div class="btn-group mr-2" id="car_btn_count" role="group"
                                                 aria-label="First group">
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#wheel_count').val(1)">1
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#wheel_count').val(2)">2
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#wheel_count').val(3)">3
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#wheel_count').val(4)">4
                                                </button>
                                            </div>
                                            <div class="btn-group col-sm-3">
                                                <input type="number" class="form-control" id="wheel_count"
                                                       value="${param.wheel_count}" name="wheelCount" min="1"
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
                                                        <option ${paramValues.height.stream().anyMatch(v->v == '${height.height}' ).get() ? 'selected' : ''}>${height.height}</option>
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
                                                <button type="button" class="btn btn-default" id="wheel_save"> Сохранить в базу
                                                </button>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row panel-heading">
                                        <div class="panel-title">Операции</div>
                                    </div>
                                    <div class="row btn">
                                        <button type="button" class="btn btn-default" id="complex"
                                                name="complex">Комплекс</button>
                                    </div>

                                    <div class=" row">
                                        <div class="col-md-4">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="mounting"
                                                           name="mounting" ${param.mounting == 'on' ? 'checked' : ''}>Демонтаж-монтаж
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="wheel_remove"
                                                           name="wheel_remove" ${param.wheel_remove == 'on' ? 'checked' : ''}>Снятие
                                                    установка
                                                    с а/м
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" id="balancing"
                                                           name="balancing" ${param.balancing == 'on' ? 'checked' : ''}>Балансировка
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="balance_weights_adhesive" ${param.car_balance_weights_adhesive == 'on' ? 'checked' : ''}>Клеящиеся или дополнительные
                                                    грузы
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="diagnostic" ${param.diagnostic == 'on' ? 'checked' : ''}>
                                                    Диагностика колеса</label>
                                            </div>


                                        </div>

                                        <div class="col-md-4">

                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="camera_insert" ${param.camera_insert == 'on' ? 'checked' : ''}>
                                                    Вставка камеры </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="pumping" ${param.tire_pumping == 'on' ? 'checked' : ''}>
                                                    Подкачка колеса </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="explosive_pumping" ${param.explosive_pumping == 'on' ? 'checked' : ''}>Взрывная
                                                    накачка
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="cleaning" ${param.cleaning == 'on' ? 'checked' : ''}>
                                                    Удаление грязи с диска</label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="using_key_jack" ${param.using_key_jack == 'on' ? 'checked' : ''}>
                                                    использование ключа, домкрата</label>
                                            </div>


                                        </div>
                                    </div>
                                    <div class="content-box-large form-group row">
                                        <div class="col-sm-8">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="valve_replacement" ${param.valve_replacement == 'on' ? 'checked' : ''}
                                                           id="valve_replacement">Замена вентиля</label>
                                            </div>
                                            <div class="form-group">
                                                <label>количество</label>
                                                ${valve_count_error}
                                                <div class="btn-toolbar mb-3" role="toolbar"
                                                     aria-label="Toolbar with button groups">
                                                    <div class="btn-group mr-2" id="btn_car_valve" role="group"
                                                         aria-label="First group">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#valve_count').val(1)">1
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#valve_count').val(2)">2
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#valve_count').val(3)">3
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#valve_count').val(4)">4
                                                        </button>
                                                    </div>
                                                    <div class="btn-group col-sm-3">
                                                        <input type="number" class="form-control" id="valve_count"
                                                               name="valve_count" min="1" value="${param.valve_count}">
                                                    </div>
                                                </div>
                                            </div>
                                            <label>Вентиль</label>
                                            ${valve_type_error}
                                            <select class="form-control" id="valve_type" name="valve">
                                                <option hidden></option>
                                                <c:forEach items="${valveList}" var="valve">
                                                    <option ${paramValues.valve.stream().anyMatch(v->fn:startsWith(v, diameter.diameter)).get() ? 'selected' : ''}>${valve.serviceItem.name}</option>
                                                </c:forEach>
                                            </select></div>
                                    </div>

                                    <div class="content-box-large form-group row">
                                        <div class="col-sm-8">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="sealing"
                                                           id="sealing" ${param.sealing == 'on' ? 'checked' : ''}>Гермитизация</label>
                                            </div>
                                            <div class="form-group">
                                                <label>количество</label>
                                                ${sealing_count_error}
                                                <div class="btn-toolbar mb-3" role="toolbar"
                                                     aria-label="Toolbar with button groups">
                                                    <div class="btn-group mr-2" id="btn_car_sealing" role="group"
                                                         aria-label="First group">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#sealing_count').val(1)">1
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#sealing_count').val(2)">2
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#sealing_count').val(3)">3
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#sealing_count').val(4)">4
                                                        </button>
                                                    </div>
                                                    <div class="btn-group col-sm-3">
                                                        <input type="number" class="form-control" id="sealing_count"
                                                               name="sealing_count" min="1"
                                                               value="${param.sealing_count}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row panel-heading">
                                        <div class="panel-title"> Ремонт</div>
                                    </div>
                                    <div class="content-box-large form-group row">
                                        <div class="form-group">
                                            <label>количество колес</label>
                                            ${repair_count_error}
                                            <div class="btn-toolbar mb-3" role="toolbar"
                                                 aria-label="Toolbar with button groups">
                                                <div class="btn-group mr-2" id="btn_car_repair" role="group"
                                                     aria-label="First group">
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#repair_count').val(1)">1
                                                    </button>
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#repair_count').val(2)">2
                                                    </button>
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#repair_count').val(3)">3
                                                    </button>
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#repair_count').val(4)">4
                                                    </button>
                                                </div>
                                                <div class="btn-group col-sm-3">
                                                    <input type="number" class="form-control" id="repair_count"
                                                           name="repair_count" min="1" value="${param.repair_count}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row panel-heading">
                                            <div class="panel-title">Операции</div>
                                        </div>
                                        <div class=" row">
                                            <div class="col-md-4" id="repair">
                                                <div class="checkbox">
                                                    <label><input type="checkbox"
                                                                  id="diagnostic" ${param.diagnostic == 'on' ? 'checked' : ''}
                                                                  name="diagnostic">Диагностика </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox"
                                                                  id="puncture_repair" ${param.puncture_repair == 'on' ? 'checked' : ''}
                                                                  name="puncture_repair">Прокол </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox"
                                                                  id="cut_repair" ${param.cut_repair == 'on' ? 'checked' : ''}
                                                                  name="cut_repair">Порез
                                                    </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox"
                                                                  id="big_cut_repair" ${param.big_cut_repair == 'on' ? 'checked' : ''}
                                                                  name="big_cut_repair">Большой порез </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox"
                                                                  id="vertical_cut_repair" ${param.vertical_cut_repair == 'on' ? 'checked' : ''}
                                                                  name="vertical_cut_repair">Вертикальный порез
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="col-sm-8">
                                                    <label>Латка</label>
                                                    ${patch_type_error}
                                                    <select class="form-control" id="patch_type" name="patch">
                                                        <option hidden></option>
                                                        <c:forEach items="${patchList}" var="patch">
                                                            <option ${paramValues.patch.stream().anyMatch(v->fn:startsWith(v, patch.serviceItem.name)).get() ? 'selected' : ''}>${patch.serviceItem.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
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

                                </fieldset>

                            </form>
                        </div>
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