package by.stormnet.levkovets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/authorized/*")
public class AuthorizationFilter implements Filter {
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            Integer authorizedUserId = (Integer)((HttpServletRequest) servletRequest).getSession().getAttribute("authorizedUserId");
            if (authorizedUserId == null) {
                servletRequest.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(servletRequest, servletResponse);
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