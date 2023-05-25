package com.funcenter.RecupForm.services;

import com.funcenter.RecupForm.entities.EmailFun;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(EmailFun emailfun) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("theroypercy@gmail.com");
        message.setTo(emailfun.getToemail());
        message.setSubject(emailfun.getSubject());
        message.setText(emailfun.getBody());

        mailSender.send(message);

    }
}
