package com.zerobase.cms.user.client.service;

import com.zerobase.cms.user.client.domain.SingUpForm;
import com.zerobase.cms.user.client.domain.model.Customer;
import com.zerobase.cms.user.client.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SingUpForm form){
        return customerRepository.save(Customer.from(form));
    }

}