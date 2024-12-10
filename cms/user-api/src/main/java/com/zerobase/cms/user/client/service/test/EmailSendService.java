package com.zerobase.cms.user.client.service.test;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgurn.SendMailForm;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final MailgunClient mailgunClient;

    public String sendEmail() {

        SendMailForm form = SendMailForm.builder()
                .from("dvkk12@gmail.com")
                .to("mhleekitecs@gmail.com")
                .subject("Test email from zero base")
                .text("my text")
                .build();

        return mailgunClient.sendEmail(form).getBody();
    }
}
