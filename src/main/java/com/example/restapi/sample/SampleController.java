package com.example.restapi.sample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  // headers = {"Content-type=application/x-www-form-urlencoded"}
  @PostMapping(value = "/my-login", consumes = "application/x-www-form-urlencoded")
  public void login(SampleModel sampleModel) {
    System.out.println(sampleModel);
  }
}
