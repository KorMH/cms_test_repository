package com.zerobase.cms.user.client.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingUpForm {
    private String email;
    private String name;
    private String password;
    private LocalDate birth;
    private String phone;
}
