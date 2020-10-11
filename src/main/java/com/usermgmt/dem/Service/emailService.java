package com.usermgmt.dem.Service;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

@Service
public class emailService {



 public void sendEmail(String email,String passWord) throws MessagingException {


     Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");

     Session session = Session.getInstance(props, new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication("no.reply.techblogs@gmail.com", "hello@123");
         }
     });
     Message msg = new MimeMessage(session);
     msg.setFrom(new InternetAddress("no.reply.techblogs@gmail.com", false));

     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
     msg.setSubject("Login Password");
     msg.setContent("Password  email", "text/html");
     msg.setSentDate(new Date());

     MimeBodyPart messageBodyPart = new MimeBodyPart();
     messageBodyPart.setContent("Your password to login is "+passWord, "text/html");

     Multipart multipart = new MimeMultipart();
     multipart.addBodyPart(messageBodyPart);
   //  MimeBodyPart attachPart = new MimeBodyPart();

//     attachPart.attachFile("/var/tmp/image19.png");
   //  multipart.addBodyPart(attachPart);
     msg.setContent(multipart);
     Transport.send(msg);
 }

}


