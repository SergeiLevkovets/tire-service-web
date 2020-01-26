package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.DiameterDTO;
import by.stormnet.levkovets.dto.impl.HeightDTO;
import by.stormnet.levkovets.dto.impl.TireDTO;
import by.stormnet.levkovets.dto.impl.WidthDTO;
import by.stormnet.levkovets.services.TireService;
import by.stormnet.levkovets.services.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorized/tire-save")
public class TireSaveController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String width = req.getParameter("width");
        String height = req.getParameter("height");
        String diameter = req.getParameter("diameter");


        WidthDTO widthDto = ServiceFactory.getFactory().getWidthService().getByName(width);
        HeightDTO heightDto = ServiceFactory.getFactory().getHeightService().getByName(height);
        DiameterDTO diameterDto = ServiceFactory.getFactory().getDiameterService().getByName(diameter);

        TireDTO tireDto = new TireDTO();
        tireDto.setWidth(widthDto);
        tireDto.setHeight(heightDto);
        tireDto.setDiameter(diameterDto);

        TireService tireService = ServiceFactory.getFactory().getTireService();
        tireService.saveOrUpdate(tireDto);
    }


}
