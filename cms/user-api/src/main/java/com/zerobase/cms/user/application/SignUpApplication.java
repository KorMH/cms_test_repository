package com.zerobase.cms.user.application;


import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.domain.SingUpForm;
import com.zerobase.cms.user.client.domain.model.Customer;
import com.zerobase.cms.user.client.mailgurn.SendMailForm;
import com.zerobase.cms.user.client.service.SignUpCustomerService;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code){
        signUpCustomerService.verifyEmail(email,code);
    }


    public String customerSignUp(SingUpForm form) {
        if (signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
            // exception
        } else {
            Customer c = signUpCustomerService.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code = getRandomCode();

            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("test@dannymytester.com")
                    .to(form.getEmail())
                    .subject("Verification Email")
                    .text(getVerificationEmailBody(form.getEmail(), form.getName(), getRandomCode()))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.ChangeCustomerValidateEmail(c.getId(),code);

            return "회원가입에 성공하였습니다.";
        }


    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello").append(name).append("! Please Click Link for verification\n\n")
                .append("http://localhost:8081/customer/signup/verify?email=")
                .append(email)
                .append(code).toString();
    }

}
