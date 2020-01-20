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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/authorized/order-car")
public class OrderController extends HttpServlet {
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
            if (req.getAttribute("patchList") == null) {
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

        Integer wheelCount = Integer.valueOf(req.getParameter("wheelCount"));

        String width = req.getParameter("width");
        String height = req.getParameter("height");
        String diameter = req.getParameter("diameter");


        WidthDto widthDto = new WidthServiceImpl().getByName(width);
        HeightDto heightDto = new HeightServiceImpl().getByName(height);
        DiameterDto diameterDto = new DiameterServiceImpl().getByName(diameter);

        TireServiceImpl tireService = new TireServiceImpl();
        TireDto tireDto = new TireDto();
        tireDto.setWidth(widthDto);
        tireDto.setHeight(heightDto);
        tireDto.setDiameter(diameterDto);
        Integer tireId = tireService.saveOrUpdate(tireDto);
        tireDto.setId(tireId);


        OrderDto orderDto = new OrderDto();

// refactor this
        orderDto.setUser(new UserServiceImpl().getById(1));
        orderDto.setTire(tireDto);
        orderDto.setType(typeDto);
//        orderDto.setTotalPrice();

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameter = req.getParameter(parameterName);

            if (parameter != null) {
                switch (parameterName) {
                    case "mounting": {
                        break;
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
                        break;
                    }


                }


                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/authorized/order-car");
            }
        }
    }


    public ServiceItemPriceDto findServiceItemPrice(List<ServiceItemPriceDto> list, ServiceItemDto serviceItem, TypeDto type, DiameterDto diameter) {
        ServiceItemPriceDto itemPriceDto = null;
        List<ServiceItemPriceDto> serviceItemPriceByItemList = new ArrayList<>();
        for (ServiceItemPriceDto serviceItemPrice : list) {
            if (serviceItemPrice.getServiceItem().equals(serviceItem)) {
                serviceItemPriceByItemList.add(serviceItemPrice);
            }
        }
        List<ServiceItemPriceDto> serviceItemPriceDtoByTypeList = new ArrayList<>();
        for (ServiceItemPriceDto serviceItemPrice : serviceItemPriceByItemList) {
            if (serviceItemPrice.getType().equals(type)) {
                serviceItemPriceDtoByTypeList.add(serviceItemPrice);
            }
        }
        if (serviceItemPriceDtoByTypeList.size() == 1) {
            itemPriceDto = serviceItemPriceDtoByTypeList.get(1);
        } else {
            for (ServiceItemPriceDto serviceItemPrice : serviceItemPriceDtoByTypeList) {
                if (serviceItemPrice.getDiameter().equals(diameter)) {
                    itemPriceDto = serviceItemPrice;
                }
            }
        }
        return itemPriceDto;
    }

}