package by.stormnet.levkovets.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private HttpUtils() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static boolean isParameterExists(HttpServletRequest request, String paramName) {
        if (StringUtils.isBlank(paramName)) {
            throw new IllegalArgumentException("Parameter name can not be blank!");
        }

        String valueStr = request.getParameter(paramName);
        return StringUtils.isNotBlank(valueStr);
    }

    public static Integer getIntParam(HttpServletRequest request, String paramName) {
        if (StringUtils.isBlank(paramName)) {
            throw new IllegalArgumentException("Parameter name can not be blank!");
        }

        String valueStr = request.getParameter(paramName);
        try {
            return new Integer(valueStr);
        } catch(Exception e) {
            return null;
        }
    }

    public static Map<String, String> getAllNotNullParam(HttpServletRequest req) {

        Map<String, String> notNullParam = new HashMap<>();

        HttpSession session = req.getSession();
        Integer authorizedUserId = (Integer) session.getAttribute("authorizedUserId");
        notNullParam.put("authorizedUserId", String.valueOf(authorizedUserId));

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            String parameter = req.getParameter(paramName);

            if (StringUtils.isNotBlank(parameter)) {
                notNullParam.put(paramName, parameter);
            }
        }

        return notNullParam;
    }

}
