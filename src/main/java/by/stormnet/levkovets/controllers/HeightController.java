package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.HeightDTO;
import by.stormnet.levkovets.services.HeightService;
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

@WebServlet("/authorized/admin/height")
public class HeightController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";

    private static final String HEIGHT_NAME = "Высота";
    private static final String HEIGHT_ARTICLE = "height";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HeightService heightService = ServiceFactory.getFactory().getHeightService();

        if (HttpUtils.isParameterExists(req, "elementChangeUpdate")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeUpdate");

            HeightDTO heightDTO = heightService.getById(id);

            req.setAttribute("elemId", heightDTO.getId());
            req.setAttribute("elemName", heightDTO.getHeight());

        }

        if (HttpUtils.isParameterExists(req, "elementChangeDelete")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeDelete");

            heightService.deleteById(id);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/height");
            return;
        }

        List<HeightDTO> all = heightService.getAll();

        req.setAttribute("elemList", all);
        req.setAttribute("tableName", HEIGHT_NAME);
        req.setAttribute("firstFieldName", HEIGHT_NAME);
        req.setAttribute("firstFieldArticle", HEIGHT_ARTICLE);


        req.getRequestDispatcher("/WEB-INF/pages/height.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer elemId = HttpUtils.getIntParam(req, "elemId");
        String elemName = req.getParameter("elemName");


        if (validateData(req, elemName)) {
            HeightService heightService = ServiceFactory.getFactory().getHeightService();

            HeightDTO heightDTO = new HeightDTO();
            heightDTO.setId(elemId);
            heightDTO.setHeight(elemName);

            heightService.saveOrUpdate(heightDTO);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/height");

        } else {

            req.getRequestDispatcher("/WEB-INF/pages/height.jsp").forward(req, resp);

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
