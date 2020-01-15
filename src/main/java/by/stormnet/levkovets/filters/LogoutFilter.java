package by.stormnet.levkovets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/logout/*")
public class LogoutFilter implements Filter {
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpSession httpSession = ((HttpServletRequest) servletRequest).getSession();
            Integer authorizedUserId = (Integer) httpSession.getAttribute("authorizedUserId");
            if (authorizedUserId != null) {
                httpSession.removeAttribute("authorizedUserId");
            }
         servletRequest.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(servletRequest, servletResponse);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
    }