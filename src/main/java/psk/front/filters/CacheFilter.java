package psk.front.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CacheFilter implements Filter {
    private static long maxAge = 10; // 30 days in seconds

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (uri.contains(".gif") || uri.contains(".png")) {
            httpResponse.setHeader("Cache-Control", "max-age=" + maxAge);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
    }
}
