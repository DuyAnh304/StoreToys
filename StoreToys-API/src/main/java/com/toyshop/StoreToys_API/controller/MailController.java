package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

//    @PostMapping
//    public ResponseEntity<?> sendEmail(String to, String subject, String body) throws MessagingException {
//        return ResponseEntity.ok().body(mailService.sendCodeEmail(to, subject, body));
//    }
}
