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
import java.util.*;

@WebServlet("/authorized/order-create")
public class OrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TypeService typeService = ServiceFactory.getFactory().getTypeService();
        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();



        if (req.getParameter("getInformationByOrder") != null) {
//            ajax
            Map<String, String> parameters = HttpUtils.getAllNotNullParam(req);

            String type = parameters.get("type");
            TypeDTO typeDto = typeService.getByName(type);

            String diameter = parameters.get("diameter");
            DiameterDTO diameterDto = diameterService.getByName(diameter);

            Map<ServiceItemPriceDTO, Integer> serviceItemPricesAndCountForOrder = findAllServiceItemPricesAndCountByParameters(parameters, typeDto, diameterDto);

            Double totalPrice = Double.valueOf(0);
            for (ServiceItemPriceDTO serviceItemPriceDTO : serviceItemPricesAndCountForOrder.keySet()) {
                totalPrice += serviceItemPriceDTO.getPrice() * serviceItemPricesAndCountForOrder.get(serviceItemPriceDTO);
            }

            req.setAttribute("serviceItemPricesList", serviceItemPricesAndCountForOrder);
            req.setAttribute("totalPrice", totalPrice);

            req.getRequestDispatcher("/WEB-INF/pages/order-create-response.jsp").forward(req, resp);

        }else {

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
                List<DiameterDTO> diameterList = diameterService.getAll();
                req.setAttribute("diameterList", diameterList);

            }

            if ((req.getAttribute("patchList") == null)
                    || (req.getAttribute("valveList") == null)
                    || (req.getAttribute("serviceItemListByType") == null)
                    || (req.getAttribute("serviceItemListUniversal") == null)
            ) {
                ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();

                if (req.getAttribute("serviceItemListByType") == null) {
                    if (req.getParameter("type") == null) {
                        List<ServiceItemDTO> list = serviceItemService.getAllByTypeInServiceItemPrice(typeService.getByName("car"));
                        req.setAttribute("serviceItemListByType", list);
                    } else {
                        String type = req.getParameter("type");
                        List<ServiceItemDTO> list = serviceItemService.getAllByTypeInServiceItemPrice(typeService.getByName(type));
                        req.setAttribute("serviceItemListByType", list);
                    }
                }
                if (req.getAttribute("serviceItemListUniversal") == null) {
                    List<ServiceItemDTO> list = serviceItemService.getAllByTypeInServiceItemPrice(typeService.getByName("universal"));
                    req.setAttribute("serviceItemListUniversal", list);
                }
                if (req.getAttribute("patchList") == null) {
                    List<ServiceItemDTO> patchList = serviceItemService.getAllByTypeInServiceItemPrice(typeService.getByName("patch"));
                    req.setAttribute("patchList", patchList);
                }
                if (req.getAttribute("valveList") == null) {
                    List<ServiceItemDTO> valveList = serviceItemService.getAllByTypeInServiceItemPrice(typeService.getByName("valve"));
                    req.setAttribute("valveList", valveList);
                }
            }

            req.getRequestDispatcher("/WEB-INF/pages/order-create.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = ServiceFactory.getFactory().getUserService();
        TypeService typeService = ServiceFactory.getFactory().getTypeService();
        WidthService widthService = ServiceFactory.getFactory().getWidthService();
        HeightService heightService = ServiceFactory.getFactory().getHeightService();
        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();
        TireService tireService = ServiceFactory.getFactory().getTireService();
        OrderService orderService = ServiceFactory.getFactory().getOrderService();
        OrderServiceItemPriceService orderServiceItemPriceService = ServiceFactory.getFactory().getOrderServiceItemPriceService();

        Map<String, String> parameters = HttpUtils.getAllNotNullParam(req);

        String authorizedUserId = parameters.get("authorizedUserId");
        int userId = Integer.parseInt(authorizedUserId);
        UserDTO userDTO = userService.getById(userId);

        String type = parameters.get("type");
        TypeDTO typeDto = typeService.getByName(type);

        String width = parameters.get("width");
        WidthDTO widthDto = widthService.getByName(width);

        String height = parameters.get("height");
        HeightDTO heightDto = heightService.getByName(height);

        String diameter = parameters.get("diameter");
        DiameterDTO diameterDto = diameterService.getByName(diameter);

        TireDTO tireDto = tireService.createTireDTO(widthDto, heightDto, diameterDto);

        OrderDTO orderDto = orderService.createOrder(userDTO, tireDto, typeDto);

        Map<ServiceItemPriceDTO, Integer> serviceItemPricesAndCountForOrder = findAllServiceItemPricesAndCountByParameters(parameters, typeDto, diameterDto);

        orderServiceItemPriceService.createOrderToServiceItemPrices(orderDto, serviceItemPricesAndCountForOrder);

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/authorized/order-create");

    }

    /**
     * Классы, ниже, оставить здесь или перенести в Service?
     */

    private Map<ServiceItemPriceDTO, Integer> findAllServiceItemPricesAndCountByParameters(Map<String, String> parameters,  TypeDTO typeDTO, DiameterDTO diameterDTO ) {

        Map<ServiceItemPriceDTO, Integer> serviceItemPriceDtoList = new HashMap<>();

        ServiceItemPriceService serviceItemPriceService = ServiceFactory.getFactory().getServiceItemPriceService();
        ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();

        List<ServiceItemPriceDTO> serviceItemPriceDTOAll = serviceItemPriceService.getAll();

        for (String parameterName : parameters.keySet()) {
            ServiceItemDTO serviceItemDTO = serviceItemService.getByName(parameterName);
            if (serviceItemDTO == null) {
                serviceItemDTO = serviceItemService.getByName(parameters.get(parameterName));
                if (serviceItemDTO == null) {
                    continue;
                }
                if (parameterName.equals("valve")) {
                    int valveCount = Integer.parseInt(parameters.get("valveCount"));
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, typeDTO, diameterDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, valveCount);
                    continue;
                }
                if (parameterName.equals("patch")) {
                    int repairCount = Integer.parseInt(parameters.get("repairCount"));
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, typeDTO, diameterDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, repairCount);
                    continue;
                }
            }
            int count = Integer.parseInt(parameters.get(parameterName));
            ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, typeDTO, diameterDTO);
            serviceItemPriceDtoList.put(serviceItemPrice, count);
        }

        return serviceItemPriceDtoList;
    }

    private ServiceItemPriceDTO findServiceItemPrice(List<ServiceItemPriceDTO> list, ServiceItemDTO serviceItem, TypeDTO type, DiameterDTO diameter) {
        ServiceItemPriceDTO itemPriceDto = null;

        List<ServiceItemPriceDTO> serviceItemPriceByItemList = new ArrayList<>();

        for (ServiceItemPriceDTO serviceItemPrice : list) {

            if (serviceItemPrice.getServiceItem().equals(serviceItem)) {
                serviceItemPriceByItemList.add(serviceItemPrice);
            }

        }

        if (serviceItemPriceByItemList.size() == 1) {

            return serviceItemPriceByItemList.get(0);

        }

        List<ServiceItemPriceDTO> serviceItemPriceDtoByTypeList = new ArrayList<>();

        for (ServiceItemPriceDTO serviceItemPrice : serviceItemPriceByItemList) {
            if (serviceItemPrice.getType().equals(type)) {
                serviceItemPriceDtoByTypeList.add(serviceItemPrice);
            }
        }

        if (serviceItemPriceDtoByTypeList.size() == 1) {

            return serviceItemPriceDtoByTypeList.get(0);

        }

        for (ServiceItemPriceDTO serviceItemPrice : serviceItemPriceDtoByTypeList) {
            if (serviceItemPrice.getDiameter().equals(diameter)) {
                itemPriceDto = serviceItemPrice;
            }
        }

        return itemPriceDto;

    }

}