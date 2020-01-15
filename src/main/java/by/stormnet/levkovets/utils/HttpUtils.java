package by.stormnet.levkovets.utils;

import javax.servlet.http.HttpServletRequest;

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

}
