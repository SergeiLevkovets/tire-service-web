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
                                    <th style="text-align: center"><img src="${pageContext.request.contextPath}/images/car.png" height="100px"></th>
                                    <th style="text-align: center"><img src="${pageContext.request.contextPath}/images/jeep.png" height="100px"></th>
                                    <th style="text-align: center"><img src="${pageContext.request.contextPath}/images/truck.png" height="100px">
                                    </th>
                                    <th style="text-align: center"><img src="${pageContext.request.contextPath}/images/traсtor.png" height="100px">
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
                        </div>
                    </div>
                </div>


            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title"><h2>Tire service</h2>
                                cars, trucks and special vehicles
                            </div>
                        </div>
                        <div class="panel-body">
                            <p>Tire fitting of passenger cars, truck wheels and special equipment, balancing of rims is
                                an obligatory procedure that accompanies the purchase and change of rubber. Our
                                equipment and experience allows us to perform tire fitting work on radial, diagonal
                                tires, low-profile tires, as well as special equipment.</p>
                            <p>We provide tire repair services for heavy-duty career wheels.</p>
                            <p>Tire repair of any complexity, the method of hot and cold vulcanization. All types of
                                tire repair are feasible for us.</p>
                            <p>Truck tire tread cutting is a procedure that directly saves the car owner's money.
                                Savings are achieved by increasing tire life as well as fuel consumption. If the concept
                                of tread cutting is included in the concept of your tires, we will help you increase the
                                service life of tires, i.e. save money.</p>
                            <p>When performing work, a full package of supporting documents is provided: an order-order,
                                an act on the work performed, an invoice, an electronic invoice, in accordance with the
                                requirements of the legislation of the Republic of Belarus. Cash and bank transfer.
                            </p></div>
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