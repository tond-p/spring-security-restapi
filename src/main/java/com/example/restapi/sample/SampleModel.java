package com.example.restapi.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SampleModel {

  @Schema(description = "로그인 아이디", required = true)
  private String username;

  @Schema(description = "비밀번호", required = true)
  private String password;
}
