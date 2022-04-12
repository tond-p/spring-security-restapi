package com.example.restapi.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SampleModel {

  @Schema(title = "로그인 아이디", required = true)
  private String username;

  @Schema(title = "비밀번호", required = true)
  private String password;

  @Schema(title = "자동로그인여부", example = "true", required = true)
  private String remember;
}
