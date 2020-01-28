package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.*;
import by.stormnet.levkovets.services.ServiceItemService;
import by.stormnet.levkovets.services.TireService;
import by.stormnet.levkovets.services.factory.ServiceFactory;
import by.stormnet.levkovets.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/authorized/admin/tire")
public class TireController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";

    private static final String TIRE_TABLE_NAME = "Список наиболее встречающихся шин";
    private static final String TIRE_WIDTH_NAME = "Ширина шины";
    private static final String TIRE_HEIGHT_NAME = "Высота профиля";
    private static final String TIRE_DIAMETER_NAME = "Диаметр";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TireService tireService = ServiceFactory.getFactory().getTireService();

        if (HttpUtils.isParameterExists(req, "getTOPList")) {
            Integer countTOP = HttpUtils.getIntParam(req, "getTOPList");
            List<TireDTO> list = tireService.loadCountTOP(countTOP);
            req.setAttribute("elemList", list);
            req.setAttribute("tableName", TIRE_TABLE_NAME);
            req.setAttribute("widthFieldName", TIRE_WIDTH_NAME);
            req.setAttribute("heightFieldName", TIRE_HEIGHT_NAME);
            req.setAttribute("diameterFieldName", TIRE_DIAMETER_NAME);
        }

        req.getRequestDispatcher("/WEB-INF/pages/tire.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String width = req.getParameter("width");
        String height = req.getParameter("height");
        String diameter = req.getParameter("diameter");


        WidthDTO widthDto = ServiceFactory.getFactory().getWidthService().getByName(width);
        HeightDTO heightDto = ServiceFactory.getFactory().getHeightService().getByName(height);
        DiameterDTO diameterDto = ServiceFactory.getFactory().getDiameterService().getByName(diameter);

        TireService tireService = ServiceFactory.getFactory().getTireService();

        tireService.createTireDTO(widthDto, heightDto, diameterDto);

    }

}
