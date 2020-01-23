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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/authorized/admin/service-item-price")
public class ServiceItemPriceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("serviceItemPrice_update") != null){

        }

        ServiceItemPriceService serviceItemPriceService = ServiceFactory.getFactory().getServiceItemPriceService();
        ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();
        TypeService typeService = ServiceFactory.getFactory().getTypeService();
        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();
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

        if (req.getParameter("serviceItemPrice_delete") != null){

        }

        if (req.getParameter("serviceItemPrice_save") != null){

        }

    }
}
