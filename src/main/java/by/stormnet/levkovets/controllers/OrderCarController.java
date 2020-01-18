package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.*;
import by.stormnet.levkovets.services.DiameterService;
import by.stormnet.levkovets.services.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/authorized/order-car")
public class OrderCarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getAttribute("widthList") == null) {
            WidthServiceImpl widthService = new WidthServiceImpl();
            List<WidthDto> widthList = widthService.getAll();
            req.setAttribute("widthList", widthList);

        }
        if (req.getAttribute("heightList") == null) {
            HeightServiceImpl heightService = new HeightServiceImpl();
            List<HeightDto> heightList = heightService.getAll();
            req.setAttribute("heightList", heightList);

        }
        if (req.getAttribute("diameterList") == null) {
            DiameterService diameterService = new DiameterServiceImpl();
            List<DiameterDto> diameterList = diameterService.getAll();
            req.setAttribute("diameterList", diameterList);

        }

        if ((req.getAttribute("patchList") == null) || (req.getAttribute("valveList") == null)) {
            ServiceItemPriceServiceImpl serviceItemPriceService = new ServiceItemPriceServiceImpl();
            TypeServiceImpl typeService = new TypeServiceImpl();
            if (req.getAttribute("patchList") == null){
                List<ServiceItemPriceDto> patchList = serviceItemPriceService.getAllByType(typeService.getByName("patch"));
                req.setAttribute("patchList", patchList);
            }
            if (req.getAttribute("valveList") == null) {
                List<ServiceItemPriceDto> valveList = serviceItemPriceService.getAllByType(typeService.getByName("valve"));
                req.setAttribute("valveList", valveList);
            }
        }

        req.getRequestDispatcher("/WEB-INF/pages/order-car.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

//        Integer UserId = Integer.valueOf((String) session.getAttribute("authorizedUserId"));

        String type = req.getParameter("type");
        TypeDto typeDto = new TypeServiceImpl().getByName(type);

//        Integer wheelCount = req.getAttribute("");

        String width = req.getParameter("width");
        String height = req.getParameter("height");
        String diameter = req.getParameter("diameter");


        WidthDto widthDto = new WidthServiceImpl().getByName(width);
        HeightDto heightDto = new HeightServiceImpl().getByName(height);
        DiameterDto diameterDto = new DiameterServiceImpl().getByName(diameter);

        TireDto tireDto = new TireDto();
        tireDto.setWidth(widthDto);
        tireDto.setHeight(heightDto);
        tireDto.setDiameter(diameterDto);

        OrderDto orderDto = new OrderDto();
//        orderDto.setUser(new UserServiceImpl().getById(UserId));
        orderDto.setTire(tireDto);
//        orderDto.setType();
//        orderDto.setTotalPrice();

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameter = req.getParameter(parameterName);
            if (parameter != null) {

            }

        }

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/authorized/order-car");
    }
}
