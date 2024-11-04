package com.lsuncar.notepad.core.messaging.email;

import com.lsuncar.notepad.core.exception.EmailException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static java.util.Objects.isNull;

@Service
public class SMTPUtil {

    private static JavaMailSenderImpl mailSender;

    @Value( "${spring.mail.username}" )
    private String uname;

    @Value( "${spring.mail.password}" )
    private String pass;

    @Value( "${spring.mail.host}" )
    private String host;

    @Value( "${spring.mail.port}" )
    private String port;

    @Value( "${spring.mail.protocol}" )
    private String proto;

    @Value( "${spring.mail.properties.mail.smtp.auth}" )
    private String auth;

    @Value( "${spring.mail.properties.mail.smtp.starttls.enable}" )
    private String ssl;


    private void configure() {
        mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(Integer.valueOf(port));
        mailSender.setUsername(uname);
        mailSender.setPassword(pass);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", proto);
        props.put("mail.smtp.auth", Boolean.valueOf(auth));
        props.put("mail.smtp.starttls.enable", Boolean.valueOf(ssl));
        mailSender.setJavaMailProperties(props);

    }

    public Boolean sendMail(String to, String subject, String body) throws EmailException {
        try {
            if (isNull(mailSender)) configure();
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(uname);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(mimeMessage);

            return true;
        } catch (Exception e) {
            throw new EmailException(e);
        }

    }
}
