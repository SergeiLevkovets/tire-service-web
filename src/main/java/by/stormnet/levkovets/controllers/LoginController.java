package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.UserDto;
import by.stormnet.levkovets.services.impl.UserServiceImpl;
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

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final String MESSAGE = "<strong style=\"color: red\">Введите Email или номер телефона</strong>";
    private static final String EMAIL_MESSAGE = "<strong style=\"color: red\">Такого Email не существует</strong>";
    private static final String PHONE_MESSAGE = "<strong style=\"color: red\">Такого номер телефона не существует</strong>";
    private static final String PASSWORD_MESSAGE = "<strong style=\"color: red\">Неверный пароль</strong>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        if (isNoValid(req, email, phone, password)){
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();

        String uri = "/index.html";
        String requestURI = (String) session.getAttribute("requestURI");


        if (StringUtils.isNotBlank(requestURI)){
            uri = requestURI;
            session.removeAttribute("requestURI");
        }
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + uri);
    }

    private boolean isNoValid(HttpServletRequest req, String email, String phone, String password){
        Map<String, String> errorMap = new HashMap<>();

        Boolean isEmailExist = true;
        Boolean isPhoneExist = true;

        UserServiceImpl userService = new UserServiceImpl();
        List<UserDto> allUsers = userService.getAll();
        HttpSession session = req.getSession();

        if (StringUtils.isBlank(email) && StringUtils.isBlank(phone)){
            errorMap.put("email_error", MESSAGE);
        }

        if (StringUtils.isBlank(password)) {
            errorMap.put("password_error", PASSWORD_MESSAGE);
        }

        if (errorMap.isEmpty()) {
            for (UserDto user : allUsers) {
                String userEmail = user.getEmail();
                String userPhone = user.getPhone();
                String userPassword = user.getPassword();
                if (userEmail.equals(email)){
                    if (userPassword.equals(password)) {
                        session.setAttribute("authorizedUserId", user.getId());
                        session.setAttribute("authorizedUserName", user.getName());
                        return false;
                    }
                    isEmailExist = false;
                }
                if (userPhone.equals(phone)){
                    if (userPassword.equals(password)) {
                        session.setAttribute("authorizedUserId", user.getId());
                        session.setAttribute("authorizedUserName", user.getName());
                        return false;
                    }
                    isPhoneExist = false;
                }
            }
            if (StringUtils.isNotBlank(email)) {
                if (isEmailExist){
                    errorMap.put("email_error", EMAIL_MESSAGE);
                }else {
                    errorMap.put("password_error", PASSWORD_MESSAGE);
                }
            }
            if (StringUtils.isNotBlank(phone)) {
                if (isPhoneExist){
                    errorMap.put("phone_error", PHONE_MESSAGE);
                }else {
                    errorMap.put("password_error", PASSWORD_MESSAGE);
                }
            }
        }
        for (String key : errorMap.keySet()) {
            req.setAttribute(key, errorMap.get(key));
        }
        return true;
    }

}
