package pl.polsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;

    void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("trackyourpacks@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }
}