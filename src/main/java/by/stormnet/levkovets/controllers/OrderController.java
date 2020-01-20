package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.*;
import by.stormnet.levkovets.services.*;
import by.stormnet.levkovets.services.factory.ServiceFactory;
import by.stormnet.levkovets.services.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/authorized/order-car")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getAttribute("widthList") == null) {
            WidthService widthService = ServiceFactory.getFactory().getWidthService();
            List<WidthDTO> widthList = widthService.getAll();
            req.setAttribute("widthList", widthList);

        }
        if (req.getAttribute("heightList") == null) {
            HeightService heightService = ServiceFactory.getFactory().getHeightService();
            List<HeightDTO> heightList = heightService.getAll();
            req.setAttribute("heightList", heightList);

        }
        if (req.getAttribute("diameterList") == null) {
            DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();
            List<DiameterDTO> diameterList = diameterService.getAll();
            req.setAttribute("diameterList", diameterList);

        }

        if ((req.getAttribute("patchList") == null) || (req.getAttribute("valveList") == null)) {
            ServiceItemPriceService serviceItemPriceService = ServiceFactory.getFactory().getServiceItemPriceService();
            TypeService typeService = ServiceFactory.getFactory().getTypeService();
            if (req.getAttribute("patchList") == null) {
                List<ServiceItemPriceDTO> patchList = serviceItemPriceService.getAllByType(typeService.getByName("patch"));
                req.setAttribute("patchList", patchList);
            }
            if (req.getAttribute("valveList") == null) {
                List<ServiceItemPriceDTO> valveList = serviceItemPriceService.getAllByType(typeService.getByName("valve"));
                req.setAttribute("valveList", valveList);
            }
        }

        req.getRequestDispatcher("/WEB-INF/pages/order-car.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer userId = Integer.valueOf((String) session.getAttribute("authorizedUserId"));
// ----- refactor this ------
        if (userId == null){
            userId = 1;
        }
        UserService userService = ServiceFactory.getFactory().getUserService();
        UserDTO user = userService.getById(userId);

        TypeService typeService = ServiceFactory.getFactory().getTypeService();

        String type = req.getParameter("type");
        TypeDTO typeDto = typeService.getByName(type);

        Integer wheelCount = Integer.valueOf(req.getParameter("wheelCount"));

        String width = req.getParameter("width");
        String height = req.getParameter("height");
        String diameter = req.getParameter("diameter");

        WidthService widthService = ServiceFactory.getFactory().getWidthService();
        HeightService heightService = ServiceFactory.getFactory().getHeightService();
        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();

        WidthDTO widthDto = widthService.getByName(width);
        HeightDTO heightDto = heightService.getByName(height);
        DiameterDTO diameterDto = diameterService.getByName(diameter);

        TireService tireService = ServiceFactory.getFactory().getTireService();

        TireDTO tireDto = new TireDTO();
        tireDto.setWidth(widthDto);
        tireDto.setHeight(heightDto);
        tireDto.setDiameter(diameterDto);
        Integer tireId = tireService.saveOrUpdate(tireDto);
        tireDto.setId(tireId);


        OrderDTO orderDto = new OrderDTO();

        orderDto.setUser(user);
        orderDto.setTire(tireDto);
        orderDto.setType(typeDto);
        orderDto.setTotalPrice(null);


        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/authorized/order-car");
    }


    public List<ServiceItemPriceDTO> findAllServiceItemPriceInOrder(HttpServletRequest req){
        List<ServiceItemPriceDTO> serviceItemPriceDtoList = new ArrayList<>();

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String parameterName = parameterNames.nextElement();
            String parameter = req.getParameter(parameterName);

            if (parameter != null) {
                switch (parameterName) {
                    case "mounting": {

                    }
                    case "balancing": {
                        break;
                    }
                    case "wheelRemove": {
                        break;
                    }
                    case "sealing":
                    case "pumping":

                    case "age": {
                        /*String value = parameterMap.get(parameterName)[0];
                        if (!isEmpty(value)) {
                            if (!value.matches("[-+]?\\d+")) {
                                errorsMap.put(parameterName, MESSAGE);
                            }
                        }*/
                    }


                }



            }
        }

        return serviceItemPriceDtoList;
    }

    public ServiceItemPriceDTO findServiceItemPrice(List<ServiceItemPriceDTO> list, ServiceItemDTO serviceItem, TypeDTO type, DiameterDTO diameter) {
        ServiceItemPriceDTO itemPriceDto = null;

        List<ServiceItemPriceDTO> serviceItemPriceByItemList = new ArrayList<>();

        for (ServiceItemPriceDTO serviceItemPrice : list) {

            if (serviceItemPrice.getServiceItem().equals(serviceItem)) {
                serviceItemPriceByItemList.add(serviceItemPrice);
            }

        }

        List<ServiceItemPriceDTO> serviceItemPriceDtoByTypeList = new ArrayList<>();

        for (ServiceItemPriceDTO serviceItemPrice : serviceItemPriceByItemList) {
            if (serviceItemPrice.getType().equals(type)) {
                serviceItemPriceDtoByTypeList.add(serviceItemPrice);
            }
        }

        if (serviceItemPriceDtoByTypeList.size() == 1) {

            itemPriceDto = serviceItemPriceDtoByTypeList.get(1);

        } else {

            for (ServiceItemPriceDTO serviceItemPrice : serviceItemPriceDtoByTypeList) {
                if (serviceItemPrice.getDiameter().equals(diameter)) {
                    itemPriceDto = serviceItemPrice;
                }
            }
        }

        return itemPriceDto;

    }

}