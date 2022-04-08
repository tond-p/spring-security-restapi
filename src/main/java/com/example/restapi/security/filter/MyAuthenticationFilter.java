package com.example.restapi.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) {

    UsernamePasswordAuthenticationToken authenticationToken = null;
    LoginDto loginDto = null;

    if (!ObjectUtils.isEmpty(request.getContentType()) && request.getContentType()
        .equals(MimeTypeUtils.APPLICATION_JSON_VALUE)) {
      // json request 혹시모르니까....
      try {
        loginDto = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(),
            StandardCharsets.UTF_8), LoginDto.class);
        authenticationToken = new UsernamePasswordAuthenticationToken(
            loginDto.getUsername(),
            loginDto.getPassword());
      } catch (Exception e) {
        throw new AuthenticationServiceException(
            "Request Content-Type(application/json) Parsing Error!"
        );
      }

    } else {
      // form-request
      String username = obtainUsername(request);
      String password = obtainPassword(request);
      authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    }

    this.setDetails(request, authenticationToken);
    return this.getAuthenticationManager().authenticate(authenticationToken);
  }

  @Getter
  @Setter
  private static class LoginDto {

    String username;
    String password;
    boolean remember;
  }
}
