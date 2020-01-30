package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.OrderStatisticDTO;
import by.stormnet.levkovets.dto.impl.UserDTO;
import by.stormnet.levkovets.services.OrderService;
import by.stormnet.levkovets.services.OrderStatisticService;
import by.stormnet.levkovets.services.UserService;
import by.stormnet.levkovets.services.factory.ServiceFactory;
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

@WebServlet("/authorized/admin/order-statistics")
public class OrderAdminStatisticsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = ServiceFactory.getFactory().getUserService();

        String start = req.getParameter("startDate");
        String end = req.getParameter("endDate");
        String user = req.getParameter("user");
        boolean startNotBlank = StringUtils.isNotBlank(start);
        boolean endNotBlank = StringUtils.isNotBlank(end);

        if (startNotBlank && endNotBlank) {
            OrderService orderService = ServiceFactory.getFactory().getOrderService();
            OrderStatisticService orderStatisticService = ServiceFactory.getFactory().getOrderStatisticService();

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            try {

                startDate = formatter.parse(start);
                endDate = formatter.parse(end);

            } catch (ParseException e) {
                throw new RuntimeException("Can not parse Date!", e);
            }

            List<OrderDTO> allByDatesAndUser = new ArrayList<>();

            if (user.equals("allUsers")){

                allByDatesAndUser = orderService.getAllByDates(startDate, endDate);

            }else {

                int userId = Integer.parseInt(user);
                UserDTO userDTO = userService.getById(userId);
                allByDatesAndUser = orderService.getAllByDatesAndUser(startDate, endDate, userDTO);

            }

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

        if (req.getAttribute("usersList") == null) {
            List<UserDTO> userDTOList = userService.getAll();
            req.setAttribute("usersList", userDTOList);
        }

        req.getRequestDispatcher("/WEB-INF/pages/order-admin-statistics.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/order-admin-statistics.jsp").forward(req, resp);
    }

}