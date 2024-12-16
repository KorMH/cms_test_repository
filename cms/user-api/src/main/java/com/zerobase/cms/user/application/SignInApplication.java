package com.zerobase.cms.user.application;


import com.zerobase.cms.user.client.domain.SignInForm;
import com.zerobase.cms.user.client.domain.model.Customer;
import com.zerobase.cms.user.client.domain.model.Seller;
import com.zerobase.cms.user.client.service.customer.CustomerService;
import com.zerobase.cms.user.client.service.seller.SellerService;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.domain.common.UserType;
import com.zerobase.domain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.zerobase.cms.user.exception.ErrorCode.LOGIN_CHECK_FAIL;
import static com.zerobase.domain.common.UserType.CUSTOMER;
import static com.zerobase.domain.common.UserType.SELLER;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;
    private final SellerService sellerService;
    public String customerLoginToken(SignInForm form){
        // 1.로그인 가능 여부
        Customer c  =customerService.findValidCustomer(form.getEmail(),form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));
        // 2. 토큰을 발행하고

        // 3. 토큰을 response 한다.
        return provider.createToken(c.getEmail(),c.getId(), CUSTOMER);
    }


    public String sellerLoginToken(SignInForm form){
        // 1.로그인 가능 여부
        Seller s  =sellerService.findValidSeller(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));
        // 2. 토큰을 발행하고

        // 3. 토큰을 response 한다.
        return provider.createToken(s.getEmail(),s.getId(), SELLER);
    }
}
