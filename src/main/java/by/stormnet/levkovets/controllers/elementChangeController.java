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

@WebServlet("/authorized/admin/element-change")
public class elementChangeController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";

    private static final String DIAMETER_NAME = "Диаметр";
    private static final String DIAMETER_ARTICLE = "diameter";
    private static final String WIDTH_NAME = "Ширина";
    private static final String WIDTH_ARTICLE = "width";
    private static final String HEIGHT_NAME = "Высота";
    private static final String HEIGHT_ARTICLE = "height";
    private static final String SERVICE_ITEM_TABLE_NAME = "Услуги";
    private static final String SERVICE_ITEM_NAME = "Название";
    private static final String SERVICE_ITEM_NAME_ARTICLE = "name";
    private static final String SERVICE_ITEM_ARTICLE = "Артикул";
    private static final String SERVICE_ITEM_ARTICLE_ARTICLE = "article";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String elementChange = null;

        HttpSession session = req.getSession();

        elementChange = (String) session.getAttribute("elementChange");

        session.removeAttribute("elementChange");

        if (HttpUtils.isParameterExists(req, "elementChange")) {

            elementChange = req.getParameter("elementChange");
        }

        req.setAttribute("elementChange", elementChange);


        if (elementChange == null || elementChange.equals("serviceItemChange")) {
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
                resp.sendRedirect(contextPath + "/authorized/admin/element-change");
                return;
            }

            List<ServiceItemDTO> all = serviceItemService.getAll();

            req.setAttribute("elemList", all);
            req.setAttribute("tableName", SERVICE_ITEM_TABLE_NAME);
            req.setAttribute("firstFieldName", SERVICE_ITEM_NAME);
            req.setAttribute("firstFieldArticle", SERVICE_ITEM_NAME_ARTICLE);
            req.setAttribute("secondFieldName", SERVICE_ITEM_ARTICLE);
            req.setAttribute("secondFieldArticle", SERVICE_ITEM_ARTICLE_ARTICLE);

        }else if (elementChange.equals("heightChange")) {

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

                session.setAttribute("elementChange", elementChange);

                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/authorized/admin/element-change");
                return;
            }

            List<HeightDTO> all = heightService.getAll();

            req.setAttribute("elemList", all);
            req.setAttribute("tableName", HEIGHT_NAME);
            req.setAttribute("firstFieldName", HEIGHT_NAME);
            req.setAttribute("firstFieldArticle", HEIGHT_ARTICLE);

        } else if (elementChange.equals("widthChange")) {

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

                session.setAttribute("elementChange", elementChange);

                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/authorized/admin/element-change");
                return;
            }

            List<WidthDTO> all = widthService.getAll();

            req.setAttribute("elemList", all);
            req.setAttribute("tableName", WIDTH_NAME);
            req.setAttribute("firstFieldName", WIDTH_NAME);
            req.setAttribute("firstFieldArticle", WIDTH_ARTICLE);

        }else if (elementChange.equals("diameterChange")) {

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

                session.setAttribute("elementChange", elementChange);

                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/authorized/admin/element-change");
                return;
            }

            List<DiameterDTO> all = diameterService.getAll();

            req.setAttribute("elemList", all);
            req.setAttribute("tableName", DIAMETER_NAME);
            req.setAttribute("firstFieldName", DIAMETER_NAME);
            req.setAttribute("firstFieldArticle", DIAMETER_ARTICLE);
        }


        req.getRequestDispatcher("/WEB-INF/pages/element-change.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String elementChange = req.getParameter("elementChange");

        Integer elemId = HttpUtils.getIntParam(req, "elemId");
        String elemName = req.getParameter("elemName");
        String elemArticle = req.getParameter("elemArticle");


        if (elementChange.equals("heightChange")) {
            if (validateData(req, elemName)) {
                HeightService heightService = ServiceFactory.getFactory().getHeightService();

                HeightDTO heightDTO = new HeightDTO();
                heightDTO.setId(elemId);
                heightDTO.setHeight(elemName);

                heightService.saveOrUpdate(heightDTO);

            } else {

                String contextPath = req.getContextPath();
                req.getRequestDispatcher(contextPath + "/authorized/admin/element-change").forward(req, resp);
                return;
            }

        } else if (elementChange.equals("widthChange")) {

            if (validateData(req, elemName)) {

                WidthService widthService = ServiceFactory.getFactory().getWidthService();

                WidthDTO widthDTO = new WidthDTO();
                widthDTO.setId(elemId);
                widthDTO.setWidth(elemName);

                widthService.saveOrUpdate(widthDTO);

            } else {

                String contextPath = req.getContextPath();
                req.getRequestDispatcher(contextPath + "/authorized/admin/element-change").forward(req, resp);
                return;
            }

        }else if (elementChange.equals("diameterChange")) {

            if (validateData(req, elemName)) {

                DiameterService diameterService = ServiceFactory.getFactory().getDiameterService();

                DiameterDTO diameterDTO = new DiameterDTO();
                diameterDTO.setId(elemId);
                diameterDTO.setDiameter(elemName);

                diameterService.saveOrUpdate(diameterDTO);

            } else {

                String contextPath = req.getContextPath();
                req.getRequestDispatcher(contextPath + "/authorized/admin/element-change").forward(req, resp);
                return;
            }

        }else if (elementChange.equals("serviceItemChange")) {

            if (validateData(req, elemName, elemArticle)) {
                ServiceItemService serviceItemService = ServiceFactory.getFactory().getServiceItemService();

                ServiceItemDTO serviceItemDTO = new ServiceItemDTO();
                serviceItemDTO.setId(elemId);
                serviceItemDTO.setName(elemName);
                serviceItemDTO.setArticle(elemArticle);

                serviceItemService.saveOrUpdate(serviceItemDTO);

            } else {
                String contextPath = req.getContextPath();
                req.getRequestDispatcher(contextPath + "/authorized/admin/element-change").forward(req, resp);
                return;
            }

        }

        HttpSession session = req.getSession();
        session.setAttribute("elementChange", elementChange);

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/authorized/admin/element-change");

    }

    private boolean validateData(HttpServletRequest req, String name, String article) {

        Map<String, String> errorMap = new HashMap<>();

        if (StringUtils.isBlank(name)) {
            errorMap.put("nameError", MESSAGE);
        }

        if (StringUtils.isBlank(article)) {
            errorMap.put("articleError", MESSAGE);
        }

        if (!errorMap.isEmpty()) {
            req.setAttribute("errorMap", errorMap);
            return false;
        }

        return true;
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
