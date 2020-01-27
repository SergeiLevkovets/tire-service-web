package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.DiameterDTO;
import by.stormnet.levkovets.dto.impl.ServiceItemDTO;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDTO;
import by.stormnet.levkovets.dto.impl.TypeDTO;
import by.stormnet.levkovets.services.DiameterService;
import by.stormnet.levkovets.services.ServiceItemPriceService;
import by.stormnet.levkovets.services.ServiceItemService;
import by.stormnet.levkovets.services.TypeService;
import by.stormnet.levkovets.services.factory.ServiceFactory;
import by.stormnet.levkovets.utils.HttpUtils;
import by.stormnet.levkovets.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/authorized/admin/service-item-price")
public class ServiceItemPriceController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceItemPriceService serviceItemPriceService = ServiceFactory.getFactory().getServiceItemPriceService();
        ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();
        TypeService typeService = ServiceFactory.getFactory().getTypeService();
        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();

        if (HttpUtils.isParameterExists(req, "serviceItemPriceUpdate")){

            Integer id = HttpUtils.getIntParam(req, "serviceItemPriceUpdate");
            ServiceItemPriceDTO serviceItemPriceDTO = serviceItemPriceService.getById(id);

            req.setAttribute("serviceItemForUpdate", serviceItemPriceDTO);
        }

        if (HttpUtils.isParameterExists(req, "serviceItemPriceDelete")){
            Integer id = HttpUtils.getIntParam(req, "serviceItemPriceDelete");

            serviceItemPriceService.deleteById(id);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/service-item-price");
            return;
        }

        List<ServiceItemPriceDTO> serviceItemPriceDTOList = serviceItemPriceService.getAll();
        List<ServiceItemDTO> serviceItemDTOList = serviceItemService.getAll();
        List<TypeDTO> typeDTOList = typeService.getAll();
        List<DiameterDTO> diameterDTOList = diameterService.getAll();

        req.setAttribute("serviceItemPriceList", serviceItemPriceDTOList);
        req.setAttribute("serviceItemList", serviceItemDTOList);
        req.setAttribute("typeList", typeDTOList);
        req.setAttribute("diameterList", diameterDTOList);

        req.getRequestDispatcher("/WEB-INF/pages/service-item-price.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isValid = validateData(req);

        if (isValid){

            ServiceItemPriceService serviceItemPriceService = ServiceFactory.getFactory().getServiceItemPriceService();
            ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();
            TypeService typeService = ServiceFactory.getFactory().getTypeService();

            Integer id = HttpUtils.getIntParam(req, "serviceItemPriceId");
            Integer serviceItemId = HttpUtils.getIntParam(req, "serviceItemId");
            Integer typeId = HttpUtils.getIntParam(req, "typeId");
            Integer diameterId = HttpUtils.getIntParam(req, "diameterId");
            double price = Double.parseDouble(req.getParameter("price"));

            ServiceItemPriceDTO serviceItemPriceDTO = new ServiceItemPriceDTO();

            serviceItemPriceDTO.setId(id);
            serviceItemPriceDTO.setServiceItem(serviceItemService.getById(serviceItemId));
            serviceItemPriceDTO.setType(typeService.getById(typeId));
            if (diameterId != null) {
                DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();
                serviceItemPriceDTO.setDiameter(diameterService.getById(diameterId));
            }
            serviceItemPriceDTO.setPrice(price);

            serviceItemPriceService.saveOrUpdate(serviceItemPriceDTO);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/service-item-price");

        }else {

            String contextPath = req.getContextPath();
            req.getRequestDispatcher(contextPath + "/authorized/admin/service-item-price").forward(req, resp);
        }

    }

    private boolean validateData(HttpServletRequest req) {

        Map<String, String> errorMap = new HashMap<>();

        String serviceItemId = req.getParameter("serviceItemId");
        String typeId = req.getParameter("typeId");
        String price = req.getParameter("price");

        if (StringUtils.isBlank(serviceItemId)) {
            errorMap.put("serviceItemIdError", MESSAGE);
        }

        if (StringUtils.isBlank(typeId)) {
            errorMap.put("typeIdError", MESSAGE);
        }

        if (StringUtils.isBlank(price)) {
            errorMap.put("priceError", MESSAGE);
        }

        if (!errorMap.isEmpty()) {
            req.setAttribute("errorMap", errorMap);
            return false;
        }

        return true;
    }
}
