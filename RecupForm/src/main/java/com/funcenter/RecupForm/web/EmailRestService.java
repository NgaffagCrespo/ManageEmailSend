package com.funcenter.RecupForm.web;

import com.funcenter.RecupForm.entities.EmailFun;
import com.funcenter.RecupForm.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/recupform")
@AllArgsConstructor
public class EmailRestService {

    private EmailService emailService;

    @PostMapping(path="/email")
    public void triggerMail(@RequestBody  EmailFun emailFun){

        emailService.sendEmail(emailFun);

    }

}
