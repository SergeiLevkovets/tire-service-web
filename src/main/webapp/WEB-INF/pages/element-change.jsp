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
            <div class="col-md-12 content-box-large">
                <div class="content-box-header row">
                    <div class="panel-title">Редактирование</div>
                </div>
                <div class="content-box-large box-with-header row">

                    <fieldset>
                        <c:url value="/authorized/admin/element-change" var="serviceItemChange_url">
                            <c:param name="elementChange" value="serviceItemChange"/>
                        </c:url>
                        <c:url value="/authorized/admin/element-change" var="heightChange_url">
                            <c:param name="elementChange" value="heightChange"/>
                        </c:url>
                        <c:url value="/authorized/admin/element-change" var="widthChange_url">
                            <c:param name="elementChange" value="widthChange"/>
                        </c:url>
                        <c:url value="/authorized/admin/element-change" var="diameterChange_url">
                            <c:param name="elementChange" value="diameterChange"/>
                        </c:url>
                        <div class="form-group">
                            <div class="btn-group btn-group-toggle" data-toggle="buttons" id="typeParam">
                                <label id="serviceItemChangeBtn"
                                       class="btn btn-info ${param.elementChange == null || param.elementChange == 'serviceItemChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="serviceItemChange"
                                           value="serviceItemChange"
                                           onchange="window.location = '${serviceItemChange_url}'" checked required>
                                    Сервисные операции
                                </label>
                                <label id="heightChangeBtn"
                                       class="btn btn-info ${param.elementChange == 'heightChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="heightChange"
                                           value="heightChange" ${param.elementChange == 'heightChange' ? 'checked' : ''}
                                           onchange="window.location = '${heightChange_url}'" required>
                                    Высота профиля шины
                                </label>
                                <label id="widthChangeBtn"
                                       class="btn btn-info ${param.elementChange == 'widthChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="widthChange"
                                           value="widthChange" ${param.elementChange == 'widthChange' ? 'checked' : ''}
                                           onchange="window.location = '${widthChange_url}'" required>
                                    Ширина шины
                                </label>
                                <label id="diameterChangeBtn"
                                       class="btn btn-info ${param.elementChange == 'diameterChange' ? 'active' : ''}">
                                    <input type="radio" name="elementChange" id="diameterChange"
                                           value="diameterChange" ${param.elementChange == 'diameterChange' ? 'checked' : ''}
                                           onchange="window.location = '${diameterChange_url}'" required>
                                    Диаметр диска колеса
                                </label>
                            </div>
                        </div>
                    </fieldset>

<%--universal--%>

                    <div class="row" ${param.elementChange == 'serviceItemChange' ? 'hidden' : ''}>
                        <div class="panel-title">Диаметр ${elemSimpleName}</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Диаметр ${elemSimpleName}</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${elemList}" var="elem">
                                <tr>
                                    <td>${elem.id}</td>
                                    <td>${elem.[elemArtikle]}</td>
                                    <td>
                                        <c:url value="/authorized/admin/element-change" var="update">
                                            <c:param name="elementChangeUpdate" value="${elem.id}"/>
                                        </c:url>
                                        <a href="${update}" class="btn btn-info" ><i
                                                class="glyphicon glyphicon-refresh"></i> Update</a>
                                        <c:url value="/authorized/admin/element-change" var="delete">
                                            <c:param name="elementChangeDelete" value="${elem.id}"/>
                                        </c:url>
                                        <a href="${delete}" class="btn btn-danger" ><i
                                                class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form class="form-horizontal panel-body" id="elemChangeForm"
                              action="${pageContext.request.contextPath}/authorized/admin/element-change"
                              method="post">
                            <div class="form-group row">
                                <div class="col-md-1" ${elemForUpdate.id == null ? (param.elemId == null ?'hidden' : '') : ''}>
                                    <label for="serviceItemId">ID</label>
                                    <input type="text" class="form-control" name="elemId" id="elemId"
                                           readonly
                                           value="${param.elemId == null ? elemId : param.elemId}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap.value.elemNameError}
                                    <label for="price">Диаметр ${elemSimpleName}</label>
                                    <input type="text" class="form-control" name="elemName" id="elemName"
                                           value="${param.elemName == null ? elemName : param.elemName}">
                                </div>
                            </div>
                            <div class="form-inline">
                                <button type="button" class="btn btn-success"
                                        id="saveElemChange">Сохранить
                                </button>
                            </div>
                        </form>
                    </div>

<%--diameter--%>
                    <div class="row" ${param.elementChange == 'diameterChange' ? '' : 'hidden'}>
                        <div class="panel-title">Диаметр</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Диаметр</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${diameterList}" var="diameter">
                                <tr>
                                    <td>${diameter.id}</td>
                                    <td>${diameter.diameter}</td>
                                    <td>
                                        <c:url value="/authorized/admin/element-change" var="update">
                                            <c:param name="elementChangeUpdate" value="${diameter.id}"/>
                                        </c:url>
                                        <a href="${update}" class="btn btn-info" ><i
                                                class="glyphicon glyphicon-refresh"></i> Update</a>
                                        <c:url value="/authorized/admin/element-change" var="delete">
                                            <c:param name="elementChangeDelete" value="${diameter.id}"/>
                                        </c:url>
                                        <a href="${delete}" class="btn btn-danger" ><i
                                                class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form class="form-horizontal panel-body" id="diameterChangeForm"
                              action="${pageContext.request.contextPath}/authorized/admin/element-change"
                              method="post">
                            <div class="form-group row">
                                <div class="col-md-1" ${diameterForUpdate.id == null ? (param.diameterId == null ?'hidden' : '') : ''}>
                                    <label for="serviceItemId">ID</label>
                                    <input type="text" class="form-control" name="diameterId" id="diameterId"
                                           readonly
                                           value="${param.diameterId == null ? diameterForUpdate.id : param.diameterId}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap.value.diameterError}
                                    <label for="price">Диаметр</label>
                                    <input type="text" class="form-control" name="diameter" id="diameter"
                                           value="${param.diameter == null ? diameterForUpdate.diameter : param.diameter}">
                                </div>
                            </div>
                            <div class="form-inline">
                                <button type="button" class="btn btn-success"
                                        id="saveDiameterChange">Сохранить
                                </button>
                            </div>
                        </form>
                    </div>
<%--width--%>
                    <div class="row" ${param.elementChange == 'widthChange' ? '' : 'hidden'}>
                        <div class="panel-title">Диаметр</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Диаметр</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${widthList}" var="width">
                                <tr>
                                    <td>${width.id}</td>
                                    <td>${width.width}</td>
                                    <td>
                                        <c:url value="/authorized/admin/element-change" var="update">
                                            <c:param name="elementChangeUpdate" value="${width.id}"/>
                                        </c:url>
                                        <a href="${update}" class="btn btn-info" ><i
                                                class="glyphicon glyphicon-refresh"></i> Update</a>
                                        <c:url value="/authorized/admin/element-change" var="delete">
                                            <c:param name="elementChangeDelete" value="${width.id}"/>
                                        </c:url>
                                        <a href="${delete}" class="btn btn-danger" ><i
                                                class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form class="form-horizontal panel-body" id="widthChangeForm"
                              action="${pageContext.request.contextPath}/authorized/admin/element-change"
                              method="post">
                            <div class="form-group row">
                                <div class="col-md-1" ${widthForUpdate.id == null ? (param.widthId == null ?'hidden' : '') : ''}>
                                    <label for="serviceItemId">ID</label>
                                    <input type="text" class="form-control" name="widthId" id="widthId"
                                           readonly
                                           value="${param.widthId == null ? widthForUpdate.id : param.widthId}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap.value.widthError}
                                    <label for="price">Диаметр</label>
                                    <input type="text" class="form-control" name="width" id="width"
                                           value="${param.width == null ? widthForUpdate.width : param.width}">
                                </div>
                            </div>
                            <div class="form-inline">
                                <button type="button" class="btn btn-success"
                                        id="saveWidthChange">Сохранить
                                </button>
                            </div>
                        </form>
                    </div>
<%--height--%>
                    <div class="row" ${param.elementChange == 'heightChange' ? '' : 'hidden'}>
                        <div class="panel-title">Диаметр</div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Диаметр</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${heightList}" var="height">
                                <tr>
                                    <td>${height.id}</td>
                                    <td>${height.height}</td>
                                    <td>
                                        <c:url value="/authorized/admin/element-change" var="update">
                                            <c:param name="elementChangeUpdate" value="${height.id}"/>
                                        </c:url>
                                        <a href="${update}" class="btn btn-info" ><i
                                                class="glyphicon glyphicon-refresh"></i> Update</a>
                                        <c:url value="/authorized/admin/element-change" var="delete">
                                            <c:param name="elementChangeDelete" value="${height.id}"/>
                                        </c:url>
                                        <a href="${delete}" class="btn btn-danger" ><i
                                                class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form class="form-horizontal panel-body" id="heightChangeForm"
                              action="${pageContext.request.contextPath}/authorized/admin/element-change"
                              method="post">
                            <div class="form-group row">
                                <div class="col-md-1" ${heightForUpdate.id == null ? (param.heightId == null ?'hidden' : '') : ''}>
                                    <label for="serviceItemId">ID</label>
                                    <input type="text" class="form-control" name="heightId" id="heightId"
                                           readonly
                                           value="${param.heightId == null ? heightForUpdate.id : param.heightId}">
                                </div>
                                <div class="col-md-2">
                                    ${errorMap.value.heightError}
                                    <label for="price">Диаметр</label>
                                    <input type="text" class="form-control" name="height" id="height"
                                           value="${param.height == null ? heightForUpdate.height : param.height}">
                                </div>
                            </div>
                            <div class="form-inline">
                                <button type="button" class="btn btn-success"
                                        id="saveHeightChange">Сохранить
                                </button>
                            </div>
                        </form>
                    </div>


                    <div class="row">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>ServiceItemName</th>
                                <th>ServiceItemArticle</th>
                                <th>Type</th>
                                <th>Diameter</th>
                                <th>Price</th>
                                <th>Operation</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${serviceItemPriceList}" var="serviceItem">
                                <tr>
                                    <td>${serviceItem.id}</td>
                                    <td>${serviceItem.serviceItem.name}</td>
                                    <td>${serviceItem.serviceItem.article}</td>
                                    <td>${serviceItem.type.type}</td>
                                    <td>${serviceItem.diameter.diameter}</td>
                                    <td>${serviceItem.price}</td>
                                    <td>
                                        <c:url value="/authorized/admin/service-item-price" var="update">
                                            <c:param name="serviceItemPrice_update" value="${serviceItem.id}"/>
                                        </c:url>
                                        <a href="${update}" class="btn btn-info" id="update"><i
                                                class="glyphicon glyphicon-refresh"></i> Update</a>
                                        <c:url value="/authorized/admin/service-item-price" var="delete">
                                            <c:param name="serviceItemPrice_delete" value="${serviceItem.id}"/>
                                        </c:url>
                                        <a href="${delete}" class="btn btn-danger" id="delete"><i
                                                class="glyphicon glyphicon-remove"></i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <c:url value="" var="getServiceItem">
                        <c:param name="serviceItem_get" value="serviceItem"/>
                    </c:url>
                    <c:url value="" var="getType">
                        <c:param name="type_get" value="type"/>
                    </c:url>
                    <c:url value="" var="getDiameter">
                        <c:param name="diameter_get" value="diameter"/>
                    </c:url>
                    <form class="form-horizontal panel-body" id="service_price_form"
                          action="${pageContext.request.contextPath}/authorized/admin/service-item-price"
                          method="post">
                        <div class="form-group row">
                            <div class="col-md-1" ${serviceItemForUpdate.id == null ? 'hidden' : ''}>
                                <label for="serviceItemId">ID</label>
                                <input type="text" class="form-control" name="serviceItemPriceId" id="serviceItemId"
                                       readonly
                                       value="${param.serviceItemPriceId == null ? serviceItemForUpdate.id : param.serviceItemPriceId}">
                            </div>
                            <div class="col-md-3">
                                <label>ServiceItem (<a href="${getServiceItem}">Create New</a>)</label>
                                ${errorMap.value.serviceItemIdError}
                                <select class="form-control" id="serviceItem" name="serviceItemId">
                                    <option hidden></option>
                                    <c:forEach items="${serviceItemList}" var="serviceItemElem">
                                        <option ${param.serviceItemId == serviceItemElem.id ? 'selected' : serviceItemForUpdate.serviceItem.id == serviceItemElem.id ? 'selected' : ''}
                                                value="${serviceItemElem.id}">${serviceItemElem.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-2">
                                <label>Type (<a href="${getType}">Create New</a>)</label>
                                ${errorMap.value.typeIdError}
                                <select class="form-control" id="type" name="typeId">
                                    <option hidden></option>
                                    <c:forEach items="${typeList}" var="typeElem">
                                        <option ${param.typeId == typeElem.id ? 'selected' : serviceItemForUpdate.type.id == typeElem.id ? 'selected' : ''}
                                                value="${typeElem.id}">${typeElem.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2">

                                <label>Diameter (<a href="${getDiameter}" id="">Create New</a>) </label>
                                <select class="form-control" id="diameter" name="diameterId">
                                    <option hidden></option>
                                    <c:forEach items="${diameterList}" var="diameterElem">
                                        <option ${param.diameterid == diameterElem.id ? 'selected' : serviceItemForUpdate.diameter.id == diameterElem.id ? 'selected' : ''}
                                                value="${diameterElem.id}">${diameterElem.diameter}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-2">
                                ${errorMap.value.priceError}
                                <label for="price">Price</label>
                                <input type="number" class="form-control" name="price" id="price"
                                       value="${param.price == null ? serviceItemForUpdate.price : param.price}">
                            </div>
                        </div>
                        <div class="form-inline">
                            <button type="button" class="btn btn-success" name="saveServiceItemPrice"
                                    id="saveServiceItemPrice">Save
                            </button>
                        </div>
                    </form>
                    <div class="row">
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

