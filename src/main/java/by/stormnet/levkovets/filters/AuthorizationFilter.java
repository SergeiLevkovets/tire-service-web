package by.stormnet.levkovets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/authorized/*")
public class AuthorizationFilter implements Filter {
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            /*HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpSession session = httpServletRequest.getSession();
            Integer authorizedUserId = (Integer) session.getAttribute("authorizedUserId");
            if (authorizedUserId == null) {
                String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
                String reqURI = requestURI.replaceAll("/tire_service", "");
                session.setAttribute("requestURI", reqURI);
                servletRequest.getServletContext().getRequestDispatcher("/login").forward(servletRequest, servletResponse);
            }*/
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
    }