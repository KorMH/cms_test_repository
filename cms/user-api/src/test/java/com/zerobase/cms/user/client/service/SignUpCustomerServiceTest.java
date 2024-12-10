package com.zerobase.cms.user.client.service;

import com.zerobase.cms.user.client.domain.SingUpForm;
import com.zerobase.cms.user.client.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SignUpCustomerServiceTest {

    @Autowired
    private SignUpCustomerService service;

    @Test
    void signUp() {
        SingUpForm form = SingUpForm.builder()
                .name("name")
                .birth(LocalDate.now())
                .email("abc@gmail.com")
                .password("1")
                .phone("01000000000")
                .build();
        Customer c = service.signUp(form);
//        Assert.isTrue(service.signUp(form).getId()!=null);
        assertNotNull(c.getId());
        assertNotNull(c.getCreateAt());
    }

}