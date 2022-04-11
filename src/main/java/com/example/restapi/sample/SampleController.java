package com.example.restapi.sample;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

  @PostMapping(value = "/my-login")
  public void login(@RequestBody SampleModel sampleModel) {
    System.out.println(sampleModel);
  }
}
