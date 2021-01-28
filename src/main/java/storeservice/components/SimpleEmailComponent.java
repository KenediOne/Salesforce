package storeservice.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import storeservice.service.MailService;

@Component
public class SimpleEmailComponent {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    public MailService mailService;


    public void sendSimpleEmail(String email) {

        String text =
                "Hello, you registered to enter follow the link:" +
                        "http://localhost:8080/client/login"
        ;
        mailService.sendMail(email,text,javaMailSender, "Autorization");

    }
}
