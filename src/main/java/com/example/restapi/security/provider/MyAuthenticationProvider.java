package com.example.restapi.security.provider;

import com.example.restapi.security.MyUserDetail;
import com.example.restapi.security.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

  private final MyUserDetailService myUserDetailService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    // provider 들어올때(filter에서) principal에 id, credentials에 패스워드 가지고 들어올것임.
    // 물론 다른걸 넣어서 적절히 커스텀 해도 됨.
    String userId = authentication.getPrincipal().toString();
    String password = authentication.getCredentials().toString();

    MyUserDetail myUserDetail = myUserDetailService.loadUserByUsername(userId);

    // 인증 하기 !!!! 예를들어... 패스워드 검증이라던지, 등등
    // 역시 나는 기록용이니 대충 패스워드 검증하는척
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (!passwordEncoder.matches(password, myUserDetail.getPassword())) {
      // throw도 일단 내맘대로..
      throw new BadCredentialsException("PWD_NOT_MATCHED");
    }

    // 문제없다면 토큰 생성해서 넘겨버리기
    // 물론 권한이나 등등 넣을수도 있음. 귀찮으니 패스
    return new UsernamePasswordAuthenticationToken(myUserDetail, null, null);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
