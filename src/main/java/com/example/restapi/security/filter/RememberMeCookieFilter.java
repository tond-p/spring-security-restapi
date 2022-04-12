package com.example.restapi.security.filter;

import com.example.restapi.security.RememberMeCookieResponseWrapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class RememberMeCookieFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse newResponse = new RememberMeCookieResponseWrapper(
        (HttpServletResponse) response);
    filterChain.doFilter(request, newResponse);
  }
}
