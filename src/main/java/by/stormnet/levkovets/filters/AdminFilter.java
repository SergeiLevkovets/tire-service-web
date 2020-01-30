package by.stormnet.levkovets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/authorized/admin/*")
public class AdminFilter implements Filter {
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpSession session = httpServletRequest.getSession();
            String authorizedUserRole = (String) session.getAttribute("authorizedUserRole");

            if (!authorizedUserRole.equals("admin")){
                httpServletRequest.getRequestDispatcher("/WEB-INF/pages/404.jsp").forward(servletRequest, servletResponse);
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