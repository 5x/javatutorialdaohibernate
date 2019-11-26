package utils;

import javax.servlet.http.HttpServletRequest;

public class RequestHelpers {
    public static String getVirtualMethod(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        if (method.equals(HttpMethods.POST.name())) {
            String methodOverride = request.getParameter("_method");

            if (methodOverride != null && !methodOverride.equals("")) {
                method = methodOverride.toUpperCase();
            }
        }

        return method;
    }
}
