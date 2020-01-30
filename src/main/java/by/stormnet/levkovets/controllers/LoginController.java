package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.UserDTO;
import by.stormnet.levkovets.services.UserService;
import by.stormnet.levkovets.services.factory.ServiceFactory;
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

        if (isNoValid(req)) {

            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            return;

        }

        HttpSession session = req.getSession();

        String uri = req.getContextPath() + "/index.html";
        String requestURI = (String) session.getAttribute("requestURIFromFilter");

        if (StringUtils.isNotBlank(requestURI)) {

            uri = requestURI;
            session.removeAttribute("requestURI");

        }

        resp.sendRedirect(uri);

    }

    private boolean isNoValid(HttpServletRequest req) {

        Map<String, String> errorMap = new HashMap<>();

        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        boolean isEmailExist = true;
        boolean isPhoneExist = true;

        if (StringUtils.isBlank(email) && StringUtils.isBlank(phone)) {

            errorMap.put("email_error", MESSAGE);

        }

        if (StringUtils.isBlank(password)) {

            errorMap.put("password_error", PASSWORD_MESSAGE);

        }

        if (errorMap.isEmpty()) {

            UserService userService = ServiceFactory.getFactory().getUserService();
            List<UserDTO> allUsers = userService.getAll();
            HttpSession session = req.getSession();

            for (UserDTO user : allUsers) {

                String userEmail = user.getEmail();
                String userPhone = user.getPhone();
                String userPassword = user.getPassword();

                if (userEmail.equals(email)) {

                    if (userPassword.equals(password)) {

                        session.setAttribute("authorizedUserId", user.getId());
                        session.setAttribute("authorizedUserName", user.getName());
                        session.setAttribute("authorizedUserRole", user.getRole());
                        return false;

                    }

                    isEmailExist = false;

                }

                if (userPhone.equals(phone)) {

                    if (userPassword.equals(password)) {

                        session.setAttribute("authorizedUserId", user.getId());
                        session.setAttribute("authorizedUserName", user.getName());
                        session.setAttribute("authorizedUserRole", user.getRole());
                        return false;

                    }

                    isPhoneExist = false;

                }
            }

            if (StringUtils.isNotBlank(email)) {

                if (isEmailExist) {

                    errorMap.put("email_error", EMAIL_MESSAGE);

                } else {

                    errorMap.put("password_error", PASSWORD_MESSAGE);

                }
            }

            if (StringUtils.isNotBlank(phone)) {

                if (isPhoneExist) {

                    errorMap.put("phone_error", PHONE_MESSAGE);

                } else {

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
