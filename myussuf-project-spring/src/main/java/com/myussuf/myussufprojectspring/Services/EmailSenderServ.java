package com.myussuf.myussufprojectspring.Services;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class EmailSenderServ {
    private JavaMailSender javaMailSender;

    public void sendEmail(String email,
                          String subject,
                          String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("myussuf1603@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
        System.out.println("Mail sent succesfully....");

    }
}
