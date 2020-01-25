package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.*;
import by.stormnet.levkovets.services.*;
import by.stormnet.levkovets.services.factory.ServiceFactory;
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

        if ((req.getAttribute("patchList") == null)
                || (req.getAttribute("valveList") == null)
                || (req.getAttribute("serviceItemListByType") == null)
                || (req.getAttribute("serviceItemListUniversal") == null)
        ) {
            ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();
            TypeService typeService = ServiceFactory.getFactory().getTypeService();

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> parameters = getAllNotNullParam(req);

        OrderDTO order = createOrder(parameters);

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/authorized/order-create");

    }

    public Map<String, String> getAllNotNullParam(HttpServletRequest req) {

        Map<String, String> notNullParam = new HashMap<>();

        HttpSession session = req.getSession();
        Integer authorizedUserId = (Integer) session.getAttribute("authorizedUserId");
        notNullParam.put("authorizedUserId", String.valueOf(authorizedUserId));

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            String parameter = req.getParameter(paramName);

            if (StringUtils.isNotBlank(parameter)) {
                notNullParam.put(paramName, parameter);
            }
        }

        return notNullParam;
    }

    /**
     * Классы, ниже, оставить здесь или перенести в OrderService
     */

    public OrderDTO createOrder(Map<String, String> parameters) {

        String authorizedUserId = parameters.get("authorizedUserId");
        int userId = Integer.parseInt(authorizedUserId);

        UserService userService = ServiceFactory.getFactory().getUserService();
        UserDTO user = userService.getById(userId);

        TypeService typeService = ServiceFactory.getFactory().getTypeService();
        String type = parameters.get("type");
        TypeDTO typeDto = typeService.getByName(type);

        WidthService widthService = ServiceFactory.getFactory().getWidthService();
        HeightService heightService = ServiceFactory.getFactory().getHeightService();
        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();

        String width = parameters.get("width");
        String height = parameters.get("height");
        String diameter = parameters.get("diameter");

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

        Map<ServiceItemPriceDTO, Integer> serviceItemPricesAndCountForOrder = findAllServiceItemPricesAndCountByParameters(parameters);

        Double totalPrice = Double.valueOf(0);
        for (ServiceItemPriceDTO serviceItemPriceDTO : serviceItemPricesAndCountForOrder.keySet()) {
            totalPrice += serviceItemPriceDTO.getPrice() * serviceItemPricesAndCountForOrder.get(serviceItemPriceDTO);
        }

        OrderDTO orderDto = new OrderDTO();

        orderDto.setUser(user);
        orderDto.setTire(tireDto);
        orderDto.setType(typeDto);
        orderDto.setTotalPrice(totalPrice);

        OrderService orderService = ServiceFactory.getFactory().getOrderService();
        Integer orderId = orderService.saveOrUpdate(orderDto);
        orderDto.setId(orderId);

        List<OrderServiceItemPriceDTO> orderToServiceItemPrices = createOrderToServiceItemPrices(orderDto, serviceItemPricesAndCountForOrder);
        OrderServiceItemPriceService orderServiceItemPriceService = ServiceFactory.getFactory().getOrderServiceItemPriceService();

        orderServiceItemPriceService.saveOrUpdateAll(orderToServiceItemPrices);

        return orderDto;

    }

    private List<OrderServiceItemPriceDTO> createOrderToServiceItemPrices(OrderDTO orderDto, Map<ServiceItemPriceDTO, Integer> serviceItemPricesAndCountForOrder) {
        List<OrderServiceItemPriceDTO> list = new ArrayList<>();
        for (ServiceItemPriceDTO serviceItemPriceDTO : serviceItemPricesAndCountForOrder.keySet()) {

            OrderServiceItemPriceDTO orderServiceItemPriceDTO = new OrderServiceItemPriceDTO();
            orderServiceItemPriceDTO.setOrder(orderDto);
            orderServiceItemPriceDTO.setServiceItemPrice(serviceItemPriceDTO);
            orderServiceItemPriceDTO.setCount(serviceItemPricesAndCountForOrder.get(serviceItemPriceDTO));

            list.add(orderServiceItemPriceDTO);

        }

        return list;
    }

    public Map<ServiceItemPriceDTO, Integer> findAllServiceItemPricesAndCountByParameters(Map<String, String> parameters) {
        Map<ServiceItemPriceDTO, Integer> serviceItemPriceDtoList = new HashMap<>();

        ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();
        ServiceItemPriceService serviceItemPriceService = ServiceFactory.getFactory().getServiceItemPriceService();
        TypeService typeService = ServiceFactory.getFactory().getTypeService();
        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();

        String typeName = parameters.get("type");
        TypeDTO orderTypeDTO = typeService.getByName(typeName);

        String diameterSize = parameters.get("diameter");
        String simpleDiameterSize = StringUtils.simpleDiameterSize(diameterSize);
        DiameterDTO diameterDTO = diameterService.getByName(simpleDiameterSize);

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
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, orderTypeDTO, diameterDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, valveCount);
                    continue;
                }
                if (parameterName.equals("patch")) {
                    int repairCount = Integer.parseInt(parameters.get("repairCount"));
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, orderTypeDTO, diameterDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, repairCount);
                    continue;
                }
            }
            int count = Integer.parseInt(parameters.get(parameterName));
            ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, orderTypeDTO, diameterDTO);
            serviceItemPriceDtoList.put(serviceItemPrice, count);
        }

        return serviceItemPriceDtoList;
    }

    /**
     * Как лучшу сделать? Методы ниже добавить в DAO или один раз зделать getAll и передовать List в метод?
     * передаем четыре параметра в метод, а находит по одному или двум, или трем, не считая list
     */

    public ServiceItemPriceDTO findServiceItemPrice(List<ServiceItemPriceDTO> list, ServiceItemDTO serviceItem, TypeDTO type, DiameterDTO diameter) {
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