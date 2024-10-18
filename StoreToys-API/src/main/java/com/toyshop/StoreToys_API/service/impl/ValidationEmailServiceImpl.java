package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.ValidationCodeRequest;
import com.toyshop.StoreToys_API.DTOs.request.ValidationEmailRequest;
import com.toyshop.StoreToys_API.model.ValidationEmail;
import com.toyshop.StoreToys_API.repository.ValidationEmailRepository;
import com.toyshop.StoreToys_API.service.MailService;
import com.toyshop.StoreToys_API.service.ValidationEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


@Service
@RequiredArgsConstructor
public class ValidationEmailServiceImpl implements ValidationEmailService {

    private final ValidationEmailRepository vlEmailRepo;
    private final MailService mailService;

    @Override
    public void createValidationCode(ValidationEmailRequest email) {
        String ValidationCode = RandomStringUtils.randomAlphanumeric(5);
        System.out.println(ValidationCode);
        ValidationEmail vlemail = ValidationEmail.builder()
                .validationCode(ValidationCode)
                .email(email.getEmail())
                .build();
        vlEmailRepo.save(vlemail);
        try {
            mailService.sendCodeEmail(email.getEmail(), "Validate Email", "Your validation code: "+ValidationCode);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String validateCode(ValidationCodeRequest code) {
        ValidationEmail email=vlEmailRepo.validateCode(code.getCode(), code.getEmail()).orElseThrow();
        vlEmailRepo.delete(email);
        return "True";
    }

}
