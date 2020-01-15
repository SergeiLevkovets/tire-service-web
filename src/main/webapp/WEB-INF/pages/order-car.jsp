<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <li class="active"><a href="${pageContext.request.contextPath}/authorized/order-car">Легвой</a></li>
                            <li><a href="${pageContext.request.contextPath}/authorized/order-suv">Микроавтобус / джип</a></li>
                            <li><a href="${pageContext.request.contextPath}/authorized/order-truck">Грузовой</a></li>
                        </ul>
                    </div>

                    <div id="car">
                        <div class="panel-heading">
                            <div class="panel-title">Заказ</div>
                        </div>
                        <div class="panel-body">
                            <form name="car_order" action="${pageContext.request.contextPath}/authorized/order-car" method="post">
                                <fieldset class="content-box-large">
                                    <div class="row panel-heading">
                                        <div class="panel-title"> Размер и количество колес</div>
                                    </div>
                                    <div class="form-group">
                                        <label>Количество колес</label>
                                        ${car_wheel_count}
                                        <div class="btn-toolbar mb-3" role="toolbar"
                                             aria-label="Toolbar with button groups">
                                            <div class="btn-group mr-2" id="car_btn_count" role="group"
                                                 aria-label="First group">
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#car_wheel_count').val(1)">1
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#car_wheel_count').val(2)">2
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#car_wheel_count').val(3)">3
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#car_wheel_count').val(4)">4
                                                </button>
                                            </div>
                                            <div class="form-group col-sm-3">
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
                                                    <option ${paramValues.width.stream().anyMatch(v->v == '235').get() ? 'selected' : ''}>235</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Высота профиля</label>
                                                ${height}
                                                <select class="form-control" name="height"
                                                        id="height" required>
                                                    <option hidden></option>
                                                    <option ${paramValues.height.stream().anyMatch(v->v == '55').get() ? 'selected' : ''}>55</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Диаметр диска</label>
                                                ${diameter}
                                                <select class="form-control" name="diameter" required>
                                                    <option hidden></option>
                                                    <option ${paramValues.diameter.stream().anyMatch(v->v == 'r13').get() ? 'selected' : ''}>r13</option>
                                                </select>
                                            </div>
                                        </div>


                                        <div class="form-group row" id="wheel_size_btn">
                                            <div class="col-sm-6 ">
                                                <button type="button" class="btn btn-default" id="wheel_size_count"> +
                                                    добавить элемент
                                                </button>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row panel-heading">
                                        <div class="panel-title">Операции</div>
                                    </div>

                                    <div class=" row">
                                        <div class="col-md-4">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="complex" ${param.complex == 'on' ? 'checked' : ''}>Комплекс
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="mounting" ${param.mounting == 'on' ? 'checked' : ''}>Демонтаж
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="installation" ${param.installation == 'on' ? 'checked' : ''}>Монтаж
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="balancing" ${param.balancing == 'on' ? 'checked' : ''}>Балансировка
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="remove_wheel" ${param.remove_wheel == 'on' ? 'checked' : ''}>Снятие
                                                    с а/м
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="install_wheel" ${param.install_wheel == 'on' ? 'checked' : ''}>Установка
                                                    на а/м
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-md-4">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="balance_weights_adhesive" ${param.car_balance_weights_adhesive == 'on' ? 'checked' : ''}>Клеящиеся
                                                    грузы
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="camera_insert" ${param.camera_insert == 'on' ? 'checked' : ''}>
                                                    Вставка камеры </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="tire_pumping" ${param.tire_pumping == 'on' ? 'checked' : ''}>
                                                    Подкачка колеса </label>
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
                                                           name="diagnostic" ${param.diagnostic == 'on' ? 'checked' : ''}>
                                                    Диагностика колеса</label>
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
                                                    <div class="form-group col-sm-3">
                                                        <input type="number" class="form-control" id="valve_count"
                                                               name="valve_count" min="1" value="${param.valve_count}">
                                                    </div>
                                                </div>
                                            </div>
                                            <label>Вентиль</label>
                                            ${valve_type_error}
                                            <select class="form-control" id="valve_type" name="valve">
                                                <option hidden></option>
                                                <option ${paramValues.valve.stream().anyMatch(v->v == 'name').get() ? 'selected' : ''}>name</option>
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
                                                    <div class="form-group col-sm-3">
                                                        <input type="number" class="form-control" id="sealing_count"
                                                               name="sealing_count" min="1" value="${param.sealing_count}">
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
                                                <div class="form-group col-sm-3">
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
                                                    <label><input type="checkbox" id="big_cut_repair" ${param.big_cut_repair == 'on' ? 'checked' : ''}
                                                                  name="big_cut_repair">Большой порез </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox" id="vertical_cut_repair" ${param.vertical_cut_repair == 'on' ? 'checked' : ''}
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
                                                        <option ${paramValues.patch.stream().anyMatch(v->v == 'name').get() ? 'selected' : ''}>name</option>
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