package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.WidthDTO;
import by.stormnet.levkovets.services.WidthService;
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

@WebServlet("/authorized/admin/width")
public class WidthController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";

    private static final String WIDTH_NAME = "Ширина";
    private static final String WIDTH_ARTICLE = "width";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WidthService widthService = ServiceFactory.getFactory().getWidthService();

        if (HttpUtils.isParameterExists(req, "elementChangeUpdate")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeUpdate");

            WidthDTO widthDTO = widthService.getById(id);

            req.setAttribute("elemId", widthDTO.getId());
            req.setAttribute("elemName", widthDTO.getWidth());
        }

        if (HttpUtils.isParameterExists(req, "elementChangeDelete")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeDelete");

            widthService.deleteById(id);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/width");
            return;
        }

        List<WidthDTO> all = widthService.getAll();

        req.setAttribute("elemList", all);
        req.setAttribute("tableName", WIDTH_NAME);
        req.setAttribute("firstFieldName", WIDTH_NAME);
        req.setAttribute("firstFieldArticle", WIDTH_ARTICLE);

        req.getRequestDispatcher("/WEB-INF/pages/width.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer elemId = HttpUtils.getIntParam(req, "elemId");
        String elemName = req.getParameter("elemName");

        if (validateData(req, elemName)) {

            WidthService widthService = ServiceFactory.getFactory().getWidthService();

            WidthDTO widthDTO = new WidthDTO();
            widthDTO.setId(elemId);
            widthDTO.setWidth(elemName);

            widthService.saveOrUpdate(widthDTO);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/width");

        } else {

            req.getRequestDispatcher("/WEB-INF/pages/width.jsp").forward(req, resp);
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
