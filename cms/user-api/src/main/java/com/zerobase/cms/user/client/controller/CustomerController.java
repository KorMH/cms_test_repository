package com.zerobase.cms.user.client.controller;


import com.zerobase.cms.user.client.domain.customer.CustomerDto;
import com.zerobase.cms.user.client.domain.model.Customer;
import com.zerobase.cms.user.client.domain.repository.CustomerRepository;
import com.zerobase.cms.user.client.service.CustomerService;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import com.zerobase.domain.common.UserVo;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zerobase.cms.user.exception.ErrorCode.NOT_FOUND_USER;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider provider;
    private final CustomerService customerService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo vo = provider.getUserVo(token);
        Customer c = customerService.findByIdAndEmail(vo.getId(),vo.getEmail()).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER));
        return ResponseEntity.ok(CustomerDto.from(c));
    }



}
