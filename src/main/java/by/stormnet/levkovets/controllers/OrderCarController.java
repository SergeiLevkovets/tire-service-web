package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.DiameterDto;
import by.stormnet.levkovets.dto.impl.HeightDto;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDto;
import by.stormnet.levkovets.dto.impl.WidthDto;
import by.stormnet.levkovets.services.DiameterService;
import by.stormnet.levkovets.services.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/authorized/order-car")
public class OrderCarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WidthServiceImpl widthService = new WidthServiceImpl();
        HeightServiceImpl heightService = new HeightServiceImpl();
        DiameterService diameterService = new DiameterServiceImpl();
        ServiceItemPriceServiceImpl serviceItemPriceService = new ServiceItemPriceServiceImpl();
        TypeServiceImpl typeService = new TypeServiceImpl();
        List<WidthDto> widthList = widthService.getAll();
        List<HeightDto> heightList = heightService.getAll();
        List<DiameterDto> diameterList = diameterService.getAll();
        List<ServiceItemPriceDto> valveList = serviceItemPriceService.getAllServiceItemPriceByType(typeService.getTypeByName("valve"));
        List<ServiceItemPriceDto> patchList = serviceItemPriceService.getAllServiceItemPriceByType(typeService.getTypeByName("patch"));
        req.setAttribute("widthList", widthList);
        req.setAttribute("heightList", heightList);
        req.setAttribute("diameterList", diameterList);
        req.setAttribute("valveList", valveList);
        req.setAttribute("patchList", patchList);
        req.getRequestDispatcher("/WEB-INF/pages/order-car.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
