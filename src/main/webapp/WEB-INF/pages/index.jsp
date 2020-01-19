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
                <div class="col-md-12">
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title"><h2>Шиномонтаж</h2>
                                легковых, грузовых колес и спецтехники
                            </div>
                        </div>
                        <div class="panel-body">
                            <table class="table table-bordered text-center">
                                <tr>
                                    <th style="text-align: center"><img
                                            src="${pageContext.request.contextPath}/resources/images/car.png"
                                            height="100px"></th>
                                    <th style="text-align: center"><img
                                            src="${pageContext.request.contextPath}/resources/images/jeep.png"
                                            height="100px"></th>
                                    <th style="text-align: center"><img
                                            src="${pageContext.request.contextPath}/resources/images/truck.png"
                                            height="100px">
                                    </th>
                                    <th style="text-align: center"><img
                                            src="${pageContext.request.contextPath}/resources/images/traсtor.png"
                                            height="100px">
                                    </th>
                                </tr>
                                <tr>
                                    <td>Легковые авто<br>
                                        от 27,00 руб.
                                    </td>
                                    <td>Микроавтобусы и<br>
                                        внедорожные авто<br>
                                        от 30,00 руб.
                                    </td>
                                    <td>Грузовые авто<br>
                                        от 18,00 руб.<br>
                                        за колесо
                                    </td>
                                    <td>Спецтехника<br>
                                        от 36,00 руб.<br>
                                        за колесо
                                    </td>
                                </tr>
                            </table>
                            <p>Шиномонтаж легковых, грузовых колес и спецтехники,
                                балансировка колесных дисков - обязательная процедура, сопутствующая
                                покупке и смене резины. Наше оборудование и опыт позволяет нам
                                выполнять работы по шиномонтажу радиальных, диагональных шин,
                                низкопрофильной резины, а также спецтехники.</p>
                            <p>Оказываем услуги шиноремонта большегрузных карьерных колес.</p>
                            <p>Шиноремонт любой сложности, метод горячей и холодной
                                вулканизации. Нам посильны все виды шиноремонта.</p>
                            <p>Нарезка протектора грузовых шин – процедура, напрямую
                                экономящая деньги автовладельца. Экономия добивается благодаря
                                увеличению срока службы шины, а также расходу топлива. Если в
                                концепцию ваших автошин включена возможность нарезки протектора – мы
                                поможем Вам увеличить срок службы автошин, т.е. сэкономить средства.</p>
                            <p>При выполнении работ предоставляется полный пакет подтверждающих документов: заказ-наряд,
                                акт на выполненные работы, счет, ЭСЧФ – в соответствии с требованиями законодательства
                                РБ.
                                Наличный и безналичный расчет.</p>
                            <p>Мы находимся по адресу г. Горид, ул. Улица, д. 1</p>
                            <p>Телефон: + 375 (29) 000 00 00</p>

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