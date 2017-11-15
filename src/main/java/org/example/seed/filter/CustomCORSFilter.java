package org.example.seed.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomCORSFilter implements Filter {

  @Override
  public void destroy() { }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

    final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpServletResponse.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
    httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
    httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept");

    chain.doFilter(request, response);
  }

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException { }
}	
