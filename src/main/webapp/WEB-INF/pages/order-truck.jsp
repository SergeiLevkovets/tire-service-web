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
            <div class="row">
                <div class="col-md-12">
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">Оформление заказа</div>
                        </div>
                        <div class="panel-body">
                            <div class="container">
                                <ul class="nav nav-pills">
                                    <li><a href="order-car">Легвой</a></li>
                                    <li><a href="order-suv">Микроавтобус / джип</a></li>
                                    <li class="active"><a href="order-truck">Грузовой</a></li>
                                </ul>
                            </div>

                            <div id="truck">
                                <div class="panel-heading">
                                    <div class="panel-title">Заказ</div>
                                </div>
                                <div class="panel-body">
                                    <form action="order-truck" method="post">
                                        <fieldset class="content-box-large">
                                            <div class="row panel-heading">
                                                <div class="panel-title"> Количество колес</div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="btn-toolbar mb-3" role="toolbar"
                                                     aria-label="Toolbar with button groups">
                                                    <div class="btn-group mr-2" id="btn_truck_count" role="group"
                                                         aria-label="First group">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#truck_wheel_count').val(1)">1
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#truck_wheel_count').val(2)">2
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#truck_wheel_count').val(3)">3
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#truck_wheel_count').val(4)">4
                                                        </button>
                                                    </div>
                                                    <div class="form-group col-sm-3">
                                                        <input type="number" class="form-control"
                                                               name="truck_wheel_count" id="truck_wheel_count"
                                                               min="1" required>
                                                    </div>
                                                </div>
                                                <div class="col-md-4 row">
                                                    <label>Тип колеса</label>
                                                    <select class="form-control" name="truck_rim_type" required>
                                                        <option hidden></option>
                                                        <option>Стандарт</option>
                                                        <option>С кольцом</option>
                                                        <option>Спец. техника</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row panel-heading">
                                                <div class="panel-title">Операции</div>
                                            </div>

                                            <div class=" row">
                                                <div class="col-md-4">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_complex">Комплекс
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_dismantling">Демонтаж
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_installation">Монтаж
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_balancing">Балансировка
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_remove_wheel">Снятие
                                                            с а/м
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_install_wheel">Установка
                                                            на а/м
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_sealing">Гермитизация
                                                        </label>
                                                    </div>

                                                </div>

                                                <div class="col-md-4">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_camera_insert">Вставка
                                                            камеры
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox"
                                                                   name="truck_tire_explosive_pumping">Взрывная
                                                            накачка
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_tire_pumping">Подкачка
                                                            колеса
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_cleaning">Удаление
                                                            грязи с диска
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_diagnostic">Диагностика
                                                            колеса
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="truck_using_key_jack">использование
                                                            ключа, домкрата
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="content-box-large form-group row">
                                                <div class="col-sm-8">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" id="valve_replacement"
                                                                   name="truck_valve_replacement">Замена вентиля
                                                        </label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>количество</label>
                                                        <div class="btn-toolbar mb-3" role="toolbar"
                                                             aria-label="Toolbar with button groups">
                                                            <div class="btn-group mr-2" id="btn_truck_valve"
                                                                 role="group" aria-label="First group">
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
                                                                <input type="number" class="form-control"
                                                                       id="valve_count" name="truck_valve_count"
                                                                       min="1">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <label>Вентиль</label>
                                                    <select class="form-control" id="valve_type"
                                                            name="truck_valve_type">
                                                        <option hidden></option>
                                                        <option>215</option>
                                                        <option>235</option>
                                                        <option>255</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="row panel-heading">
                                                <div class="panel-title"> Ремонт</div>
                                            </div>
                                            <div class="content-box-large form-group row">
                                                <div class="form-group">
                                                    <label>количество колес</label>
                                                    <div class="btn-toolbar mb-3" role="toolbar"
                                                         aria-label="Toolbar with button groups">
                                                        <div class="btn-group mr-2" id="btn_truck_repair"
                                                             role="group" aria-label="First group">
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
                                                            <input type="number" class="form-control"
                                                                   id="repair_count" name="truck_repair_count"
                                                                   min="1">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row panel-heading">
                                                    <div class="panel-title">Операции</div>
                                                </div>
                                                <div class=" row">
                                                    <div class="col-md-4" id="repair">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" id="puncture_repair"
                                                                       name="truck_puncture_repair">Прокол
                                                            </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" id="cut_repair"
                                                                       name="truck_cut_repair">Порез
                                                            </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" id="big_cut_repair"
                                                                       name="truck_big_cut_repair">Большой порез
                                                            </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" id="vertical_cut_repair"
                                                                       name="truck_vertical_cut_repair">Вертикальный
                                                                порез
                                                            </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" id="repair_diagnostic"
                                                                       name="truck_repair_diagnostic">Диагностика
                                                            </label>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <div class="col-sm-8">
                                                            <label>Латка</label>
                                                            <select class="form-control" id="patch_type"
                                                                    name="truck_patch_type">
                                                                <option hidden></option>
                                                                <option>215</option>
                                                                <option>235</option>
                                                                <option>255</option>
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
                                                    <input type="submit" class="btn btn-primary"
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
    </div>
</div>

<%@include file="/WEB-INF/pages/footer.jsp" %>
<%@include file="/WEB-INF/pages/script-import.jsp" %>
</body>
</html>