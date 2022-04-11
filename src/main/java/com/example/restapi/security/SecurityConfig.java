package com.example.restapi.security;

import com.example.restapi.security.filter.MyAuthenticationFilter;
import com.example.restapi.security.provider.MyAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final MyAuthenticationProvider myAuthenticationProvider;

  private static final String REMEMBER_ME_KEY = "rememberKey";

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public MyAuthenticationFilter myAuthenticationFilter() throws Exception {
//    TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
//        REMEMBER_ME_KEY, this.userDetailsService);
//    rememberMeServices.setParameter("remember");
//    rememberMeServices.setTokenValiditySeconds(Integer.MAX_VALUE);  // 젤루 길게 세팅

    MyAuthenticationFilter filter = new MyAuthenticationFilter();
    filter.setFilterProcessesUrl("/my-login");
    filter.setAuthenticationManager(authenticationManagerBean());
//    filter.setAuthenticationSuccessHandler();  커스텀하게 필요하다면... 추가하기
//    filter.setAuthenticationFailureHandler();  커스텀하게 필요하다면... 추가하기

//    filter.setRememberMeServices(rememberMeServices);
    return filter;
  }

  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(myAuthenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .formLogin().disable();
//        .rememberMe()
//        .key(REMEMBER_ME_KEY)
//        .userDetailsService(this.userDetailsService);

    http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

  }
}
