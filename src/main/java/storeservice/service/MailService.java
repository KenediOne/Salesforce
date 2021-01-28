package storeservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import storeservice.Constants;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Service
public class MailService {

    public void sendMail(String recipientMail, String text, JavaMailSender javaMailService, String mailSubject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(Constants.MY_EMAIL);
        mailMessage.setTo(recipientMail);
        mailMessage.setSubject(mailSubject);
        mailMessage.setText(text);
        javaMailService.send(mailMessage);
        LOGGER.info("Mail Sent");
    }

}
