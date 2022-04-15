package com.example.restapi.sample;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  // headers = {"Content-type=application/x-www-form-urlencoded"}
  @PostMapping(value = "/my-login", consumes = "application/x-www-form-urlencoded")
  public void login(SampleModel sampleModel) {
    System.out.println(sampleModel);
  }

  @GetMapping(value = "/login-test")
  public String loginTest() {
    return "login_user";
  }

  @PostMapping(value = "/my-logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    HttpSession session = request.getSession();
    Cookie cookie = new Cookie("remember-me", null);

    if (auth != null) {
      cookie.setMaxAge(0);
      response.addCookie(cookie); // remember-me 쿠키삭제

      session.invalidate();
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }

    return "logout success!";
  }
}
