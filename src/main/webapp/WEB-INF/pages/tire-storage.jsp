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
            <div class="row">
                <div class="col-md-12 content-box-large">
                    <div class="panel-heading">
                        <div class="panel-title">Разместить шины на хранение</div>
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal panel-body" id="storage_form" action="tire-storage" method="post">
                            <div class="form-group">
                                <label for="tire_name">Производитель и модель</label>
                                ${tire_name}
                                <input type="text" class="form-control"
                                       value="${param.tire_name}" name="tire_name" id="tire_name" required>
                            </div>
                            <div class="row">
                                <div class="col-md-3 form-group">
                                    <label for="width_tire">Ширина колеса</label>
                                    ${width_tire}
                                    <select name="width_tire" id="width_tire" class="form-control" required>
                                        <option hidden></option>
                                        <option ${param.width_tire == '215' ? 'selected' : ''}>215</option>
                                    </select>
                                </div>
                                <div class="col-md-3 form-group">
                                    <label for="height_tire">Высота профиля</label>
                                    ${height_tire}}
                                    <select name="height_tire" id="height_tire" class="form-control" required>
                                        <option hidden></option>
                                        <option${param.height_tire == '45' ? 'selected' : ''}>45</option>
                                    </select>
                                </div>
                                <div class="col-md-3 form-group">
                                    <label for="diameter_wheel">Диаметр диска</label>
                                    ${diameter_wheel}
                                    <select name="diameter_wheel" id="diameter_wheel" class="form-control" required>
                                        <option hidden></option>
                                        <option ${param.diameter_wheel == 'r13' ? 'selected' : ''}>r13</option>
                                        <option ${param.diameter_wheel == 'r14' ? 'selected' : ''}>r14</option>
                                        <option ${param.diameter_wheel == 'r15' ? 'selected' : ''}>r15</option>
                                        <option ${param.diameter_wheel == 'r16' ? 'selected' : ''}>r16</option>
                                        <option ${param.diameter_wheel == 'r17' ? 'selected' : ''}>r17</option>
                                        <option ${param.diameter_wheel == 'r18' ? 'selected' : ''}>r18</option>
                                        <option ${param.diameter_wheel == 'r19' ? 'selected' : ''}>r19</option>
                                        <option ${param.diameter_wheel == 'r20' ? 'selected' : ''}>r20</option>
                                        <option ${param.diameter_wheel == 'r21' ? 'selected' : ''}>r21</option>
                                        <option ${param.diameter_wheel == 'r22' ? 'selected' : ''}>r22</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="placement_date">Дата размещения</label>
                                ${placement_date}
                                <input type="date" name="placement_date" id="placement_date"
                                       value="${param.placement_date}" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="end_date">Дата окончания хранения</label>
                                ${end_date}
                                <input type="date" name="end_date" id="end_date"
                                       value="${param.end_date}" class="form-control" required>
                            </div>
                            <input type="submit" class="btn btn-success" id="submit_storage" value="Submit">
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

