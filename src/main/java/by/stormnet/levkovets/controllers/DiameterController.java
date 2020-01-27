package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.DiameterDTO;
import by.stormnet.levkovets.dto.impl.HeightDTO;
import by.stormnet.levkovets.dto.impl.ServiceItemDTO;
import by.stormnet.levkovets.dto.impl.WidthDTO;
import by.stormnet.levkovets.services.DiameterService;
import by.stormnet.levkovets.services.HeightService;
import by.stormnet.levkovets.services.ServiceItemService;
import by.stormnet.levkovets.services.WidthService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/authorized/admin/diameter")
public class DiameterController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";

    private static final String DIAMETER_NAME = "Диаметр";
    private static final String DIAMETER_ARTICLE = "diameter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();

        if (HttpUtils.isParameterExists(req, "elementChangeUpdate")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeUpdate");

            DiameterDTO diameterDTO = diameterService.getById(id);

            req.setAttribute("elemId", diameterDTO.getId());
            req.setAttribute("elemName", diameterDTO.getDiameter());
        }

        if (HttpUtils.isParameterExists(req, "elementChangeDelete")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeDelete");

            diameterService.deleteById(id);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/diameter");
            return;
        }

        List<DiameterDTO> all = diameterService.getAll();

        req.setAttribute("elemList", all);
        req.setAttribute("tableName", DIAMETER_NAME);
        req.setAttribute("firstFieldName", DIAMETER_NAME);
        req.setAttribute("firstFieldArticle", DIAMETER_ARTICLE);

        req.getRequestDispatcher("/WEB-INF/pages/diameter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer elemId = HttpUtils.getIntParam(req, "elemId");
        String elemName = req.getParameter("elemName");

            if (validateData(req, elemName)) {

                DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();

                DiameterDTO diameterDTO = new DiameterDTO();
                diameterDTO.setId(elemId);
                diameterDTO.setDiameter(elemName);

                diameterService.saveOrUpdate(diameterDTO);

                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/authorized/admin/diameter");

            } else {

                req.getRequestDispatcher("/WEB-INF/pages/diameter.jsp").forward(req, resp);

            }
    }

    private boolean validateData(HttpServletRequest req, String name) {

        Map<String, String> errorMap = new HashMap<>();

        if (StringUtils.isBlank(name)) {
            errorMap.put("nameError", MESSAGE);
        }

        if (!errorMap.isEmpty()) {
            req.setAttribute("errorMap", errorMap);
            return false;
        }

        return true;
    }


}
