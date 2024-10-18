package com.toyshop.StoreToys_API.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${spring.mail.from}")
    private String mailFrom;

    private final JavaMailSender mailSender;

    public String sendCodeEmail(String to, String subject, String text) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(mailFrom, "StoreToy");
        if(to.contains(",")){
            mimeMessageHelper.setTo(to.split(","));
        } else{
            mimeMessageHelper.setTo(to);
        }
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        mailSender.send(mimeMessage);
        return "sent";
    }
}
