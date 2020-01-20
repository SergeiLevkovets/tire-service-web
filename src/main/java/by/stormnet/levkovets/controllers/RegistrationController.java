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

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Введены неверные данные</strong>";
    private static final String EMAIL_MESSAGE = "<strong style=\"color: red\">Такой Email уже существует</strong>";
    private static final String PHONE_MESSAGE = "<strong style=\"color: red\">Такой номер уже существует</strong>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        if (isNoValid(req, name, email, phone, password, confirmPassword)) {
            req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
            return;
        }

        UserDTO userDto = new UserDTO();
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setPhone(phone);
        userDto.setPassword(password);

        UserService userService = ServiceFactory.getFactory().getUserService();
        userService.saveOrUpdate(userDto);

        HttpSession session = req.getSession();

        List<UserDTO> allUsers = userService.getAll();
        for (UserDTO user : allUsers) {
            if (user.getEmail().equals(email)) {
                session.setAttribute("authorizedUserId", user.getId());
                session.setAttribute("authorizedUserName", user.getName());
                break;
            }
        }

        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/index.html");

    }

    private boolean isNoValid(HttpServletRequest req, String name, String email, String phone, String password, String confirmPassword) {

        Map<String, String> errorMap = new HashMap<>();

        UserService userService = ServiceFactory.getFactory().getUserService();
        List<UserDTO> allUsers = userService.getAll();

        if (StringUtils.isBlank(name)) {
            errorMap.put("name_error", MESSAGE);
        }

        if (StringUtils.isBlank(email)) {
            errorMap.put("email_error", MESSAGE);
        }

        if (StringUtils.isBlank(phone)) {
            errorMap.put("phone_error", MESSAGE);
        }

        if (StringUtils.isBlank(password)) {
            errorMap.put("password_error", MESSAGE);
        }

        if (StringUtils.isBlank(confirmPassword) || !password.equals(confirmPassword)) {
            errorMap.put("confirm_password_error", MESSAGE);
        }

        if (StringUtils.isNotBlank(email)) {
            for (UserDTO user : allUsers) {
                if (user.getEmail().equals(email)) {
                    errorMap.put("email_error", EMAIL_MESSAGE);
                    break;
                }
            }
        }
        if (StringUtils.isNotBlank(email)) {
            for (UserDTO user : allUsers) {
                String userPhone = StringUtils.simplePhoneNumber(user.getPhone());
                String phoneNumber = StringUtils.simplePhoneNumber(phone);
                if (userPhone.equals(phoneNumber)) {
                    errorMap.put("phone_error", PHONE_MESSAGE);
                    break;
                }
            }
        }


        if (errorMap.isEmpty()) {
            return false;
        }

        for (String key : errorMap.keySet()) {
            req.setAttribute(key, errorMap.get(key));
        }
        return true;
    }


}
