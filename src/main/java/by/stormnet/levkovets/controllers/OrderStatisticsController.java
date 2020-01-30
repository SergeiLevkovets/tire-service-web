package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.*;
import by.stormnet.levkovets.services.*;
import by.stormnet.levkovets.services.factory.ServiceFactory;
import by.stormnet.levkovets.utils.HttpUtils;
import by.stormnet.levkovets.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/authorized/order-statistics")
public class OrderStatisticsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String start = req.getParameter("startDate");
        String end = req.getParameter("endDate");

        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            UserService userService = ServiceFactory.getFactory().getUserService();
            OrderService orderService = ServiceFactory.getFactory().getOrderService();
            OrderStatisticService orderStatisticService = ServiceFactory.getFactory().getOrderStatisticService();

            HttpSession session = req.getSession();
            int userId = (Integer) session.getAttribute("authorizedUserId");

            UserDTO userDTO = userService.getById(userId);

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = formatter.parse(start);
                endDate = formatter.parse(end);
            } catch (ParseException e) {
                throw new RuntimeException("Can not parse Date!", e);
            }
            List<OrderDTO> allByDatesAndUser = orderService.getAllByDatesAndUser(startDate, endDate, userDTO);
            List<OrderStatisticDTO> statisticDTOList = new ArrayList<>();
            Double totalPrice = new Double(0);
            for (OrderDTO orderDTO : allByDatesAndUser) {
                OrderStatisticDTO orderStatistic = orderStatisticService.createOrderStatistic(orderDTO);
                totalPrice += orderStatistic.getTotalPrice();
                statisticDTOList.add(orderStatistic);
            }
            req.setAttribute("OrderStatisticList", statisticDTOList);
            req.setAttribute("allTotalPrice", totalPrice);
        }


        req.getRequestDispatcher("/WEB-INF/pages/order-statistics.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/order-statistics.jsp").forward(req, resp);
    }

}