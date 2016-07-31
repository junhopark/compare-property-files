package com.jpark;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jpark on 7/30/16.
 */
@WebFilter(filterName = "defaultFilter", urlPatterns = "*")
public class DefaultFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    final HttpServletResponse response = (HttpServletResponse)servletResponse;
    if (servletRequest.getServerName().contains("herokuapp")) {
      response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
      response.setHeader("Location", "https://comparepropertyfiles.com");
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
  }
}
