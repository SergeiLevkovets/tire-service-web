package by.stormnet.levkovets.controllers;

import by.stormnet.levkovets.dto.impl.UserDTO;
import by.stormnet.levkovets.services.UserService;
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

@WebServlet("/authorized/admin/users")
public class usersController extends HttpServlet {

    private static final String MESSAGE = "<strong style=\"color: red\">Поле не может быть пустым</strong>";
    private static final String EMAIL_MESSAGE = "<strong style=\"color: red\">Такой Email уже существует</strong>";
    private static final String PHONE_MESSAGE = "<strong style=\"color: red\">Такой номер телефона уже существует</strong>";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = ServiceFactory.getFactory().getUserService();

        if (HttpUtils.isParameterExists(req, "userUpdate")) {
            Integer id = HttpUtils.getIntParam(req, "userUpdate");

            UserDTO userDTO = userService.getById(id);

            req.setAttribute("userForUpdate", userDTO);
        }

        if (HttpUtils.isParameterExists(req, "userDelete")) {
            Integer id = HttpUtils.getIntParam(req, "userDelete");

            userService.deleteById(id);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/users");
            return;
        }

        List<UserDTO> all = userService.getAll();

        req.setAttribute("usersList", all);


        req.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer userId = HttpUtils.getIntParam(req, "userId");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");


        if (validateData(req, userId, name, email, password, phone, role, MESSAGE)) {

            UserService userService = ServiceFactory.getFactory().getUserService();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(userId);
            userDTO.setName(name);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            userDTO.setPhone(phone);
            userDTO.setRole(role);

            userService.saveOrUpdate(userDTO);

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/authorized/admin/users");

        } else {

            req.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(req, resp);

        }
    }

    static boolean validateData(HttpServletRequest req, Integer id, String name, String email, String password, String phone, String role, String message) {

        Map<String, String> errorMap = new HashMap<>();

        UserService userService = ServiceFactory.getFactory().getUserService();
        List<UserDTO> allUsers = userService.getAll();

        for (UserDTO user : allUsers) {

            Integer userId = user.getId();

            if (userId.equals(id)) {
                continue;
            }

            String userEmail = user.getEmail();
            String userPhone = user.getPhone();

            if (StringUtils.isNotBlank(email) && userEmail.equals(email)) {
                errorMap.put("emailError", EMAIL_MESSAGE);
            }

            if (StringUtils.isNotBlank(phone)
                    && StringUtils.simplePhoneNumber(userPhone).equals(StringUtils.simplePhoneNumber(phone))) {
                errorMap.put("phoneError", PHONE_MESSAGE);
            }
        }

        if (StringUtils.isBlank(name)) {
            errorMap.put("nameError", message);
        }

        if (StringUtils.isBlank(email)) {
            errorMap.put("emailError", message);
        }

        if (StringUtils.isBlank(password)) {
            errorMap.put("passwordError", message);
        }

        if (StringUtils.isBlank(phone)) {
            errorMap.put("phoneError", message);
        }

        if (StringUtils.isBlank(role)) {
            errorMap.put("roleError", message);
        }

        if (!errorMap.isEmpty()) {
            req.setAttribute("errorMap", errorMap);
            return false;
        }

        return true;

    }

}
