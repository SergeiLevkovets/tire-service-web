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

            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpSession session = httpServletRequest.getSession();
            Integer authorizedUserId = (Integer) session.getAttribute("authorizedUserId");
            String authorizedUserName = (String) session.getAttribute("authorizedUserName");

            if (authorizedUserId == null || authorizedUserName == null) {
                String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
                session.setAttribute("requestURI", requestURI);
                servletRequest.getServletContext().getRequestDispatcher("/login").forward(servletRequest, servletResponse);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
    }