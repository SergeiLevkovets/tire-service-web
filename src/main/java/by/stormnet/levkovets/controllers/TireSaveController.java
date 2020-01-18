package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.DiameterDto;
import by.stormnet.levkovets.dto.impl.HeightDto;
import by.stormnet.levkovets.dto.impl.TireDto;
import by.stormnet.levkovets.dto.impl.WidthDto;
import by.stormnet.levkovets.services.impl.DiameterServiceImpl;
import by.stormnet.levkovets.services.impl.HeightServiceImpl;
import by.stormnet.levkovets.services.impl.TireServiceImpl;
import by.stormnet.levkovets.services.impl.WidthServiceImpl;

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


        WidthDto widthDto = new WidthServiceImpl().getByName(width);
        HeightDto heightDto = new HeightServiceImpl().getByName(height);
        DiameterDto diameterDto = new DiameterServiceImpl().getByName(diameter);

        TireDto tireDto = new TireDto();
        tireDto.setWidth(widthDto);
        tireDto.setHeight(heightDto);
        tireDto.setDiameter(diameterDto);

        TireServiceImpl tireService = new TireServiceImpl();
        tireService.saveOrUpdate(tireDto);
    }
}
