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
                                    <li class="active"><a href="order-suv">Микроавтобус / джип</a></li>
                                    <li><a href="order-truck">Грузовой</a></li>
                                </ul>
                            </div>
                            <div id="suv">
                                <div class="panel-heading">
                                    <div class="panel-title">Заказ SUV</div>
                                </div>
                                <div class="panel-body">
                                    <form name="suv_order" action="order-suv" method="post">
                                        <fieldset class="content-box-large">
                                            <div class="row panel-heading">
                                                <div class="panel-title"> Размер и количество колес</div>
                                            </div>
                                            <div class="form-group">
                                                <label>количество колес</label>
                                                <div class="btn-toolbar mb-3" role="toolbar"
                                                     aria-label="Toolbar with button groups">
                                                    <div class="btn-group mr-2" id="suv_btn_count" role="group"
                                                         aria-label="First group">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#suv_wheel_count').val(1)">1
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#suv_wheel_count').val(2)">2
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#suv_wheel_count').val(3)">3
                                                        </button>
                                                        <button type="button" class="btn btn-default"
                                                                onclick="$('#suv_wheel_count').val(4)">4
                                                        </button>
                                                    </div>
                                                    <div class="form-group col-sm-3">
                                                        <input type="number" class="form-control"
                                                               id="suv_wheel_count" name="suv_wheel_count" min="1"
                                                               required>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="content-box-large">
                                                <div class="form-group row" id="wheel_size">
                                                    <div class="col-sm-3">
                                                        <label>Ширина колеса</label>
                                                        <select class="form-control" required name="suv_wheel_width"
                                                                id="suv_wheel_width">
                                                            <option hidden></option>
                                                            <option>215</option>
                                                            <option>235</option>
                                                            <option>255</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label>Высота профиля</label>
                                                        <select class="form-control" required
                                                                name="suv_wheel_height" id="suv_wheel_height">
                                                            <option hidden></option>
                                                            <option>55</option>
                                                            <option>60</option>
                                                            <option>65</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <label>Диаметр диска</label>
                                                        <select class="form-control" required
                                                                name="suv_wheel_diameter">
                                                            <option hidden></option>
                                                            <option value="r13">r13</option>
                                                            <option value="r14">r14</option>
                                                            <option value="r15">r15</option>
                                                            <option value="r16">r16</option>
                                                            <option value="r17">r17</option>
                                                            <option value="r18">r18</option>
                                                            <option value="r19">r19</option>
                                                            <option value="r20">r20</option>
                                                            <option value="r21">r21</option>
                                                            <option value="r22">r22</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group row" id="wheel_size_btn">
                                                    <div class="col-sm-6 ">
                                                        <button type="button" class="btn btn-default"
                                                                id="wheel_size_count" name="suv_wheel_type_count"> +
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
                                                                   name="suv_complex">Комплекс</label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="suv_dismantling">Демонтаж
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label><input type="checkbox" name="suv_installation">Монтаж
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label><input type="checkbox" name="suv_balancing">Балансировка
                                                        </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label><input type="checkbox" name="suv_remove_wheel">Снятие
                                                            с а/м </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label><input type="checkbox" name="suv_install_wheel">Установка
                                                            на а/м </label>
                                                    </div>

                                                </div>
                                                <div class="col-md-4">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox"
                                                                   name="suv_balance_weights_adhesive">Клеящиеся
                                                            грузы </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="suv_camera_insert">Вставка
                                                            камеры </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="suv_tire_pumping">Подкачка
                                                            колеса </label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="suv_cleaning">Удаление
                                                            грязи с диска</label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="suv_diagnostic">Диагностика
                                                            колеса</label>
                                                    </div>
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="suv_using_key_jack">использование
                                                            ключа, домкрата</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="content-box-large form-group row">
                                                <div class="col-sm-8">
                                                    <div class="checkbox">
                                                        <label><input type="checkbox" id="valve_replacement"
                                                                      name="suv_valve_replacement">Замена
                                                            вентиля</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>количество</label>
                                                        <div class="btn-toolbar mb-3" role="toolbar"
                                                             aria-label="Toolbar with button groups">
                                                            <div class="btn-group mr-2" id="btn_suv_valve"
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
                                                                       id="valve_count" name="suv_valve_count"
                                                                       min="1">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <label>Вентиль</label>
                                                    <select class="form-control" id="valve_type"
                                                            name="suv_valve_type">
                                                        <option hidden></option>
                                                        <option>215</option>
                                                        <option>235</option>
                                                        <option>255</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="content-box-large form-group row">
                                                <div class="col-sm-8">
                                                    <div class="checkbox">
                                                        <label><input type="checkbox" name="suv_sealing"
                                                                      id="sealing">Гермитизация</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>количество</label>
                                                        <div class="btn-toolbar mb-3" role="toolbar"
                                                             aria-label="Toolbar with button groups">
                                                            <div class="btn-group mr-2" id="btn_suv_sealing"
                                                                 role="group" aria-label="First group">
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
                                                                <input type="number" class="form-control"
                                                                       id="sealing_count" name="suv_sealing_count"
                                                                       min="1">
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
                                                    <div class="btn-toolbar mb-3" role="toolbar"
                                                         aria-label="Toolbar with button groups">
                                                        <div class="btn-group mr-2" id="btn_suv_repair" role="group"
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
                                                            <input type="number" class="form-control"
                                                                   id="repair_count" name="suv_repair_count"
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
                                                            <label><input type="checkbox" id="puncture_repair"
                                                                          name="suv_puncture_repair">Прокол </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label><input type="checkbox" id="cut_repair"
                                                                          name="suv_cut_repair">Порез </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label><input type="checkbox" id="big_cut_repair"
                                                                          name="suv_big_cut_repair">Большой порез
                                                            </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label><input type="checkbox" id="vertical_cut_repair"
                                                                          name="suv_vertical_cut_repair">Вертикальный
                                                                порез </label>
                                                        </div>
                                                        <div class="checkbox">
                                                            <label><input type="checkbox" id="repair_diagnostic"
                                                                          name="suv_repair_diagnostic">Диагностика
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="col-sm-8">
                                                            <label>Латка</label>
                                                            <select class="form-control" id="patch_type"
                                                                    name="suv_patch_type">
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