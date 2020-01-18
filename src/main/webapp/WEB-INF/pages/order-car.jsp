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

                        <form name="order" id="order" action="${pageContext.request.contextPath}/authorized/order-car"
                              method="get">

                            <fieldset class="content-box-large">
                                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                                    <label class="btn btn-info ${param.type == null || param.type == 'car' ? 'active' : ''}">
                                        <input type="radio" name="type" id="car" value="car" checked required> Легвой
                                    </label>
                                    <label class="btn btn-info ${param.type == 'suv' ? 'active' : ''}">
                                        <input type="radio" name="type" id="suv"
                                               value="suv" ${param.type == 'suv' ? 'checked' : ''} required>
                                        Микроавтобус / джип
                                    </label>
                                    <label class="btn btn-info ${param.type == 'truck' ? 'active' : ''}">
                                        <input type="radio" name="type" id="truck"
                                               value="truck" ${param.type == 'truck' ? 'checked' : ''} required>
                                        Грузовой
                                    </label>
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
                                            <div class="hidden" id="saveTireUrl">${pageContext.request.contextPath}/authorized/tire-save</div>

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
                                                <button type="button" class="btn btn-default" id="tireSaveBtn" value="${pageContext.request.contextPath}/authorized/tire-save">
                                                    Сохранить в базу
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                <div class="row panel-heading">
                                    <div class="panel-title">Операции</div>
                                </div>
                                <div class="row btn">
                                    <button type="button" class="btn btn-default" id="complex"
                                            name="complex">Комплекс
                                    </button>
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
                                                <input type="checkbox" id="wheelRemove"
                                                       name="wheelRemove" ${param.wheelRemove == 'on' ? 'checked' : ''}>Снятие
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
                                                       name="balanceWeightsAdhesive" ${param.balanceWeightsAdhesive == 'on' ? 'checked' : ''}>Клеящиеся
                                                или дополнительные
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
                                                       name="cameraInsert" ${param.cameraInsert == 'on' ? 'checked' : ''}>
                                                Вставка камеры </label>
                                        </div>
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"
                                                       name="pumping" ${param.pumping == 'on' ? 'checked' : ''}>
                                                Подкачка колеса </label>
                                        </div>
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"
                                                       name="explosivePumping" ${param.explosivePumping == 'on' ? 'checked' : ''}>Взрывная
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
                                                       name="usingKeyJack" ${param.usingKeyJack == 'on' ? 'checked' : ''}>
                                                использование ключа, домкрата</label>
                                        </div>


                                    </div>
                                </div>
                                <div class="content-box-large form-group row">
                                    <div class="col-sm-8">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"
                                                       name="valveReplacement" ${param.valveReplacement == 'on' ? 'checked' : ''}
                                                       id="valveReplacement">Замена вентиля</label>
                                        </div>
                                        <div class="form-group">
                                            <label>количество</label>
                                            ${valveCountError}
                                            <div class="btn-toolbar mb-3" role="toolbar"
                                                 aria-label="Toolbar with button groups">
                                                <div class="btn-group mr-2" id="btn_car_valve" role="group"
                                                     aria-label="First group">
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
                                                <div class="btn-group col-sm-3">
                                                    <input type="number" class="form-control" id="valveCount"
                                                           name="valveCount" min="1" value="${param.valveCount}">
                                                </div>
                                            </div>
                                        </div>
                                        <label>Вентиль</label>
                                        ${valveError}
                                        <select class="form-control" id="valve" name="valve">
                                            <option hidden></option>
                                            <c:forEach items="${valveList}" var="valveItem">
                                                <option ${paramValues.valve.stream().anyMatch(v->v == valveItem.serviceItem.name).get() ? 'selected' : ''}>${valveItem.serviceItem.name}</option>
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
                                            ${sealingCountError}
                                            <div class="btn-toolbar mb-3" role="toolbar"
                                                 aria-label="Toolbar with button groups">
                                                <div class="btn-group mr-2" id="btn_car_sealing" role="group"
                                                     aria-label="First group">
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#sealingCount').val(1)">1
                                                    </button>
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#sealingCount').val(2)">2
                                                    </button>
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#sealingCount').val(3)">3
                                                    </button>
                                                    <button type="button" class="btn btn-default"
                                                            onclick="$('#sealingCount').val(4)">4
                                                    </button>
                                                </div>
                                                <div class="btn-group col-sm-3">
                                                    <input type="number" class="form-control" id="sealingCount"
                                                           name="sealingCount" min="1"
                                                           value="${param.sealingCount}">
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
                                        ${repairCountError}
                                        <div class="btn-toolbar mb-3" role="toolbar"
                                             aria-label="Toolbar with button groups">
                                            <div class="btn-group mr-2" id="btn_car_repair" role="group"
                                                 aria-label="First group">
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#repairCount').val(1)">1
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#repairCount').val(2)">2
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#repairCount').val(3)">3
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="$('#repairCount').val(4)">4
                                                </button>
                                            </div>
                                            <div class="btn-group col-sm-3">
                                                <input type="number" class="form-control" id="repairCount"
                                                       name="repairCount" min="1" value="${param.repairCount}">
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
                                                              id="punctureRepair" ${param.punctureRepair == 'on' ? 'checked' : ''}
                                                              name="punctureRepair">Прокол </label>
                                            </div>
                                            <div class="checkbox">
                                                <label><input type="checkbox"
                                                              id="cutRepair" ${param.cutRepair == 'on' ? 'checked' : ''}
                                                              name="cutRepair">Порез
                                                </label>
                                            </div>
                                            <div class="checkbox">
                                                <label><input type="checkbox"
                                                              id="bigCutRepair" ${param.bigCutRepair == 'on' ? 'checked' : ''}
                                                              name="bigCutRepair">Большой порез </label>
                                            </div>
                                            <div class="checkbox">
                                                <label><input type="checkbox"
                                                              id="verticalCutRepair" ${param.verticalCutRepair == 'on' ? 'checked' : ''}
                                                              name="verticalCutRepair">Вертикальный порез
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-md-4">
                                            <div class="col-sm-8">
                                                <label>Латка</label>
                                                ${patch_type_error}
                                                <select class="form-control" id="patch" name="patch">
                                                    <option hidden></option>
                                                    <c:forEach items="${patchList}" var="patchItem">
                                                        <option ${paramValues.patch.stream().anyMatch(v->fn:startsWith(v, patchItem.serviceItem.name)).get() ? 'selected' : ''}>${patchItem.serviceItem.name}</option>
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

<%@include file="/WEB-INF/pages/footer.jsp" %>
<%@include file="/WEB-INF/pages/script-import.jsp" %>

</body>
</html>