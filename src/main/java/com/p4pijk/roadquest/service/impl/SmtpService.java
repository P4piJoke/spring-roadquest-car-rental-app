package com.p4pijk.roadquest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmtpService {

    private static final String TO = "danil.papizhuk02@gmail.com";
    private final JavaMailSender mailSender;

    public void sendEmail(String from, String subject, String message){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(TO);
        email.setText(message);
        email.setSubject(subject);
        mailSender.send(email);
    }
}
