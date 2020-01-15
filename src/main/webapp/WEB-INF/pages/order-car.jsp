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
                            <li class="active"><a href="authorized/order-car">Легвой</a></li>
                            <li><a href="authorized/order-suv">Микроавтобус / джип</a></li>
                            <li><a href="authorized/order-truck">Грузовой</a></li>
                        </ul>
                    </div>

                    <div id="car">
                        <div class="panel-heading">
                            <div class="panel-title">Заказ</div>
                        </div>
                        <div class="panel-body">
                            <form name="car_order" action="order-car" method="post">
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
                                                       value="${param.car_wheel_count}" name="car_wheel_count" min="1"
                                                       required>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="content-box-large">
                                        <div class="form-group row" id="wheel_size">
                                            <div class="col-sm-3">
                                                <label>Ширина колеса</label>
                                                ${car_wheel_width}
                                                <select class="form-control" name="car_wheel_width" id="car_wheel_width"
                                                        required>
                                                    <option hidden></option>
                                                    <option ${paramValues.car_wheel_width.stream().anyMatch(v->v == '235').get() ? 'selected' : ''}>235</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Высота профиля</label>
                                                ${car_wheel_height}
                                                <select class="form-control" name="car_wheel_height"
                                                        id="car_wheel_height" required>
                                                    <option hidden></option>
                                                    <option ${paramValues.car_wheel_height.stream().anyMatch(v->v == '55').get() ? 'selected' : ''}>55</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Диаметр диска</label>
                                                ${car_wheel_diameter}
                                                <select class="form-control" name="car_wheel_diameter" required>
                                                    <option hidden></option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r13').get() ? 'selected' : ''}>r13</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r14').get() ? 'selected' : ''}>r14</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r15').get() ? 'selected' : ''}>r15</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r16').get() ? 'selected' : ''}>r16</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r17').get() ? 'selected' : ''}>r17</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r18').get() ? 'selected' : ''}>r18</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r19').get() ? 'selected' : ''}>r19</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r20').get() ? 'selected' : ''}>r20</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r21').get() ? 'selected' : ''}>r21</option>
                                                    <option ${paramValues.car_wheel_diameter.stream().anyMatch(v->v == 'r22').get() ? 'selected' : ''}>r22</option>
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
                                                           name="car_complex" ${param.car_complex == 'on' ? 'checked' : ''}>Комплекс
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_dismantling" ${param.car_dismantling == 'on' ? 'checked' : ''}>Демонтаж
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_installation" ${param.car_installation == 'on' ? 'checked' : ''}>Монтаж
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_balancing" ${param.car_balancing == 'on' ? 'checked' : ''}>Балансировка
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_remove_wheel" ${param.car_remove_wheel == 'on' ? 'checked' : ''}>Снятие
                                                    с а/м
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_install_wheel" ${param.car_install_wheel == 'on' ? 'checked' : ''}>Установка
                                                    на а/м
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-md-4">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_balance_weights_adhesive" ${param.car_balance_weights_adhesive == 'on' ? 'checked' : ''}>Клеящиеся
                                                    грузы
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_camera_insert" ${param.car_camera_insert == 'on' ? 'checked' : ''}>
                                                    Вставка камеры </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_tire_pumping" ${param.car_tire_pumping == 'on' ? 'checked' : ''}>
                                                    Подкачка колеса </label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_cleaning" ${param.car_cleaning == 'on' ? 'checked' : ''}>
                                                    Удаление грязи с диска</label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_diagnostic" ${param.car_diagnostic == 'on' ? 'checked' : ''}>
                                                    Диагностика колеса</label>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_using_key_jack" ${param.car_using_key_jack == 'on' ? 'checked' : ''}>
                                                    использование ключа, домкрата</label>
                                            </div>


                                        </div>
                                    </div>
                                    <div class="content-box-large form-group row">
                                        <div class="col-sm-8">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox"
                                                           name="car_valve_replacement" ${param.car_valve_replacement == 'on' ? 'checked' : ''}
                                                           id="valve_replacement">Замена вентиля</label>
                                            </div>
                                            <div class="form-group">
                                                <label>количество</label>
                                                ${car_valve_count}
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
                                                               name="car_valve_count" min="1" value="${param.car_valve_count}">
                                                    </div>
                                                </div>
                                            </div>
                                            <label>Вентиль</label>
                                            ${car_valve_type}
                                            <select class="form-control" id="valve_type" name="car_valve_type">
                                                <option hidden></option>
                                                <option ${paramValues.car_valve_type.stream().anyMatch(v->v == 'name').get() ? 'selected' : ''}>name</option>
                                            </select></div>
                                    </div>

                                    <div class="content-box-large form-group row">
                                        <div class="col-sm-8">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="car_sealing"
                                                           id="sealing" ${param.car_sealing == 'on' ? 'checked' : ''}>Гермитизация</label>
                                            </div>
                                            <div class="form-group">
                                                <label>количество</label>
                                                ${car_sealing_count}
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
                                                               name="car_sealing_count" min="1" value="${param.car_sealing_count}">
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
                                            ${car_repair_count}
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
                                                           name="car_repair_count" min="1" value="${param.car_repair_count}">
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
                                                                  id="puncture_repair" ${param.car_puncture_repair == 'on' ? 'checked' : ''}
                                                                  name="car_puncture_repair">Прокол </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox"
                                                                  id="cut_repair" ${param.car_cut_repair == 'on' ? 'checked' : ''}
                                                                  name="car_cut_repair">Порез
                                                    </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox" id="big_cut_repair" ${param.car_big_cut_repair == 'on' ? 'checked' : ''}
                                                                  name="car_big_cut_repair">Большой порез </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox" id="vertical_cut_repair" ${param.car_vertical_cut_repair == 'on' ? 'checked' : ''}
                                                                  name="car_vertical_cut_repair">Вертикальный порез
                                                    </label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input type="checkbox" id="repair_diagnostic" ${param.car_repair_diagnostic == 'on' ? 'checked' : ''}
                                                                  name="car_repair_diagnostic">Диагностика </label>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="col-sm-8">
                                                    <label>Латка</label>
                                                    ${car_patch_type}
                                                    <select class="form-control" id="patch_type" name="car_patch_type">
                                                        <option hidden></option>
                                                        <option ${paramValues.car_patch_type.stream().anyMatch(v->v == 'name').get() ? 'selected' : ''}>name</option>
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