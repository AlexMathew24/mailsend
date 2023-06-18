package com.data.mailssend;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    public static  void sendEmailWithAttachment(String emailTo,String filePath) throws IOException, MessagingException {
        //Email configuration
        String EMAIL_HOST=ApplicationProperties.getProperty("smtp.host");
        int EMAIL_PORT=Integer.parseInt(ApplicationProperties.getProperty("smtp.port"));
        String EMAIL_USERNAME=ApplicationProperties.getProperty("email.username");
        String EMAIL_PASSWORD=ApplicationProperties.getProperty("email.password");
        String EMAIL_SUBJECT=ApplicationProperties.getProperty("email.subject");
        String TEXT_MESSAGE=ApplicationProperties.getProperty("email.test");
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", EMAIL_HOST);
            props.put("mail.smtp.port", EMAIL_PORT);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                    }

            });

            //create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(EMAIL_SUBJECT);
            message.setText(TEXT_MESSAGE);

            //create the email body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(TEXT_MESSAGE);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            //Attach the Excel file
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new java.io.File(filePath));


            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            message.setHeader("Return-Path", EMAIL_SUBJECT);
            Transport.send(message);
        } catch (MessagingException|IOException e){
            e.printStackTrace();

        }

    }
}
