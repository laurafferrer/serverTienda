/*

   Service responsible for sending emails asynchronously using JavaMailSender.
   This service is intended for sending SimpleMailMessage instances.

package net.ausiasmarch.serverTienda.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {
    
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    
       Asynchronously sends the specified SimpleMailMessage 
       using the configured JavaMailSender.
    
    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
}
*/