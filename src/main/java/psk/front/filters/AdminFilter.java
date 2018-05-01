package psk.front.filters;

import psk.businessLogic.AuthenticatedAccountHolder;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter, Serializable {

    @Inject
    private Instance<AuthenticatedAccountHolder> authenticatedAccountHolder;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        if (authenticatedAccountHolder.get().isAdmin()) {
            //if user is logged in, complete request
            chain.doFilter(req, res);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) res;
            //not logged in, go to login page
            httpServletResponse.sendRedirect("/index.xhtml");
        }
    }

    @Override
    public void destroy() {

    }
}
