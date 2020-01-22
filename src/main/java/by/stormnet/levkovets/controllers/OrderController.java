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

        Map<String, String> parameters = getAllNotNullParam(req);

        OrderDTO order = createOrder(parameters);

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/authorized/order-car");

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

        Integer wheelCount = Integer.valueOf(parameters.get("wheelCount"));

        for (String parameterName : parameters.keySet()) {
            Integer defaultCount = wheelCount;
            ServiceItemDTO serviceItemDTO = serviceItemService.getByName(parameterName);
            if (serviceItemDTO == null) {
                serviceItemDTO = serviceItemService.getByName(parameters.get(parameterName));
                if (serviceItemDTO == null) {
                    continue;
                }
                if (parameterName.equals("valve")) {
                    defaultCount = Integer.parseInt(parameters.get("valveCount"));
                }
                if (parameterName.equals("patch")) {
                    defaultCount = Integer.parseInt(parameters.get("repairCount"));
                }

            }
            switch (parameterName) {
                case "mounting": {
                    if (parameters.containsKey("suv")) {
                        TypeDTO type = typeService.getByName("suv");
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    if (parameters.containsKey("heavy")) {
                        TypeDTO type = typeService.getByName("heavy");
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    if (parameters.containsKey("ring")) {
                        TypeDTO type = typeService.getByName("ring");
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, orderTypeDTO, diameterDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;
                }
                case "balancing": {
                    if (parameters.containsKey("suv")) {
                        TypeDTO type = typeService.getByName("suv");
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, orderTypeDTO, diameterDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;

                }
                case "wheelRemove": {
                    if (parameters.containsKey("heavy")) {
                        TypeDTO type = typeService.getByName("heavy");
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    if (parameters.containsKey("dual")) {
                        TypeDTO type = typeService.getByName("dual");
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    if (parameters.containsKey("suv")) {
                        TypeDTO type = typeService.getByName("suv");
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, orderTypeDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;
                }
                case "pumping": {
                    if (typeName.equals("truck")) {
                        if (parameters.containsKey("heavy")) {
                            TypeDTO type = typeService.getByName("heavy");
                            ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, type);
                            serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                            break;
                        }
                        ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, orderTypeDTO);
                        serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                        break;
                    }
                    TypeDTO universalType = typeService.getByName("universal");
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO, universalType);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;
                }
                case "valveReplacement": {
                    defaultCount = Integer.parseInt(parameters.get("valveCount"));
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;
                }
                case "sealing": {
                    defaultCount = Integer.parseInt(parameters.get("sealingCount"));
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;
                }
                case "diagnostic":
                case "punctureRepair":
                case "cutRepair":
                case "bigCutRepair":
                case "verticalCutRepair": {
                    defaultCount = Integer.parseInt(parameters.get("repairCount"));
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;
                }
                default: {
                    ServiceItemPriceDTO serviceItemPrice = findServiceItemPrice(serviceItemPriceDTOAll, serviceItemDTO);
                    serviceItemPriceDtoList.put(serviceItemPrice, defaultCount);
                    break;
                }
            }
        }

        return serviceItemPriceDtoList;
    }

    /**
     * Как лучшу сделать? Методы ниже добавить в DAO или один раз зделать getAll и передовать List в метод?
     * Иногда передаем все четыре параметра в метод, а находит по двум или трем
     */

    public ServiceItemPriceDTO findServiceItemPrice(List<ServiceItemPriceDTO> list, ServiceItemDTO serviceItem) {
        return findServiceItemPrice(list, serviceItem, null, null);
    }

    public ServiceItemPriceDTO findServiceItemPrice(List<ServiceItemPriceDTO> list, ServiceItemDTO serviceItem, TypeDTO type) {
        return findServiceItemPrice(list, serviceItem, type, null);
    }

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