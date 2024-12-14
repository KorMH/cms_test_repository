package com.zerobase.cms.user.application;


import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.domain.SingUpForm;
import com.zerobase.cms.user.client.domain.model.Customer;
import com.zerobase.cms.user.client.domain.model.Seller;
import com.zerobase.cms.user.client.mailgurn.SendMailForm;
import com.zerobase.cms.user.client.service.customer.SignUpCustomerService;
import com.zerobase.cms.user.client.service.seller.SellerService;
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
    private final SellerService sellerService;

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
                    .text(getVerificationEmailBody(c.getEmail(), c.getName(), "customer",code))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.ChangeCustomerValidateEmail(c.getId(),code);

            return "회원가입에 성공하였습니다.";
        }


    }

    public void sellerVerify(String email, String code){
        sellerService.verifyEmail(email,code);
    }


    public String sellerSignUp(SingUpForm form){
        if (sellerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
            // exception
        } else {
            Seller s = sellerService.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code = getRandomCode();

            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("test@dannymytester.com")
                    .to(form.getEmail())
                    .subject("Verification Email")
                    .text(getVerificationEmailBody(s.getEmail(), s.getName(), "seller",code))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.ChangeCustomerValidateEmail(s.getId(),code);

            return "회원가입에 성공하였습니다.";
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type,String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello").append(name).append("! Please Click Link for verification\n\n")
                .append("http://localhost:8081/signup/"+type+"verify/?email=")
                .append(email)
                .append(code).toString();
    }

}
