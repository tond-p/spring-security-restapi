package com.example.restapi.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

  @Override
  public MyUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {

    // 실제 디비나 유저 정보를 취득 할 수 있는 ... 로직 추가해서 사용해야함.
    // 기록용이니.. 나는 하드코딩..
    MyUserDetail myUserDetail = new MyUserDetail();
    myUserDetail.setUsername(username);
    myUserDetail.setPassword("1234");

    return myUserDetail;
  }
}
