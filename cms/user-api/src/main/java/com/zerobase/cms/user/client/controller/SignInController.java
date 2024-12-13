package com.zerobase.cms.user.client.controller;

import com.zerobase.cms.user.application.SignInApplication;
import com.zerobase.cms.user.client.domain.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signIn")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> SignInCustomer(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.customerLoginToken(form));
    }

}
