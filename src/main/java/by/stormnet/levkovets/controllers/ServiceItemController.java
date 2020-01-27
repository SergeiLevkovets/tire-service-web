package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.ServiceItemDTO;
import by.stormnet.levkovets.services.ServiceItemService;
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

@WebServlet("/authorized/admin/service-item")
public class ServiceItemController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";

    private static final String SERVICE_ITEM_TABLE_NAME = "Сервисный элемент";
    private static final String SERVICE_ITEM_NAME = "Название";
    private static final String SERVICE_ITEM_NAME_ARTICLE = "name";
    private static final String SERVICE_ITEM_ARTICLE = "Артикул";
    private static final String SERVICE_ITEM_ARTICLE_ARTICLE = "article";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();

        if (HttpUtils.isParameterExists(req, "elementChangeUpdate")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeUpdate");

            ServiceItemDTO serviceItemDTO = serviceItemService.getById(id);

            req.setAttribute("elemId", serviceItemDTO.getId());
            req.setAttribute("elemName", serviceItemDTO.getName());
            req.setAttribute("elemArticle", serviceItemDTO.getArticle());
        }

        if (HttpUtils.isParameterExists(req, "elementChangeDelete")) {
            Integer id = HttpUtils.getIntParam(req, "elementChangeDelete");

            serviceItemService.deleteById(id);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/service-item");
            return;
        }

        List<ServiceItemDTO> all = serviceItemService.getAll();

        req.setAttribute("elemList", all);
        req.setAttribute("tableName", SERVICE_ITEM_TABLE_NAME);
        req.setAttribute("firstFieldName", SERVICE_ITEM_NAME);
        req.setAttribute("firstFieldArticle", SERVICE_ITEM_NAME_ARTICLE);
        req.setAttribute("secondFieldName", SERVICE_ITEM_ARTICLE);
        req.setAttribute("secondFieldArticle", SERVICE_ITEM_ARTICLE_ARTICLE);

        req.getRequestDispatcher("/WEB-INF/pages/service-item.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer elemId = HttpUtils.getIntParam(req, "elemId");
        String elemName = req.getParameter("elemName");
        String elemArticle = req.getParameter("elemArticle");


        if (validateData(req, elemName, elemArticle, MESSAGE)) {

            ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();

            ServiceItemDTO serviceItemDTO = new ServiceItemDTO();
            serviceItemDTO.setId(elemId);
            serviceItemDTO.setName(elemName);
            serviceItemDTO.setArticle(elemArticle);

            serviceItemService.saveOrUpdate(serviceItemDTO);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/service-item");

        } else {

            req.getRequestDispatcher("/WEB-INF/pages/service-item.jsp").forward(req, resp);

        }
    }

    static boolean validateData(HttpServletRequest req, String name, String article, String message) {
        Map<String, String> errorMap = new HashMap<>();

        if (StringUtils.isBlank(name)) {
            errorMap.put("nameError", message);
        }

        if (StringUtils.isBlank(article)) {
            errorMap.put("articleError", message);
        }

        if (!errorMap.isEmpty()) {
            req.setAttribute("errorMap", errorMap);
            return false;
        }

        return true;
    }

}
