package by.stormnet.levkovets.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorized/service-price")
public class ServicePriceController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*
        Dao<ServicePrice> dao = new ServicePriceDao();

        if (req.getParameter("name") != null && req.getParameter("price") != null){
            ServicePrice servicePrice = new ServicePrice();
            servicePrice.setName(req.getParameter("name"));
            servicePrice.setPrice(Double.valueOf(req.getParameter("price")));
            dao.save(servicePrice);
        }



        if (req.getParameter("servicePrice_delete") != null){
            ServicePrice servicePrice = dao.loadById(Integer.valueOf(req.getParameter("servicePrice_delete")));
            dao.delete(servicePrice);
        }

        List<ServicePrice> list = dao.loadAll();
        req.setAttribute("servicePriceList", list);*/
        req.getRequestDispatcher("/WEB-INF/pages/service-price.jsp").forward(req, resp);
    }
}
