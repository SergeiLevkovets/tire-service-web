package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.UserDto;
import by.stormnet.levkovets.services.UserService;
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
    private static final String EMAIL_MESSAGE = "<strong style=\"color: red\">Введите Email или номер телефона</strong>";
    private static final String PASSWORD_MESSAGE = "<strong style=\"color: red\">Неверный пароль</strong>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        if (isValid(req, email, phone, password)){
            resp.sendRedirect("index.html");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);

    }

    private boolean isValid(HttpServletRequest req, String email, String phone, String password){
        Map<String, String> errorMap = new HashMap<>();

        UserService userService = new UserServiceImpl();
        List<UserDto> allUsers = userService.getAllUsers();
        HttpSession session = req.getSession();

        if (StringUtils.isBlank(email) && StringUtils.isBlank(phone)){
            errorMap.put("email_error", EMAIL_MESSAGE);
        }

        if (StringUtils.isBlank(password)) {
            errorMap.put("password_error", PASSWORD_MESSAGE);
        }

        if (errorMap.isEmpty()) {
            for (UserDto user : allUsers) {
                String userEmail = user.getEmail();
                String userPhone = user.getPhone();
                String userPassword = user.getPassword();
                if (userEmail.equals(email) && userPassword.equals(password)){
                    session.setAttribute("authorizedUserId", user.getId());
                    session.setAttribute("authorizedUserName", user.getName());
                    return true;
                }
                if (userPhone.equals(phone) && userPassword.equals(password)){
                    session.setAttribute("authorizedUserId", user.getId());
                    session.setAttribute("authorizedUserName", user.getName());
                    return true;
                }
            }
            errorMap.put("password_error", PASSWORD_MESSAGE);
        }
        return false;
    }

}
