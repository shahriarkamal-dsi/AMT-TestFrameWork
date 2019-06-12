package test.Log;

/**
 *
 * @author Administrator
 *      * testingEmailAddress=testing4010@gmail.com
 *      * testingEmailPassword=12345678amt
 */

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class MimeMessageTest {
    public static void main(String args[]) {
        final String SMTP_HOST = "smtp.gmail.com";
        final String SMTP_PORT = "587";
        final String GMAIL_USERNAME = "testing4010@gmail.com";
        final String GMAIL_PASSWORD = "12345678amt";
        final String Receipient = "asifurrouf.dsi@gmail.com";

        System.out.println("Process Started");

        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.smtp.host", SMTP_HOST);
        prop.setProperty("mail.smtp.user", GMAIL_USERNAME);
        prop.setProperty("mail.smtp.password", GMAIL_PASSWORD);
        prop.setProperty("mail.smtp.port", SMTP_PORT);
        prop.setProperty("mail.smtp.auth", "true");
        System.out.println("Props : " + prop);

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_USERNAME,
                        GMAIL_PASSWORD);
            }
        });

        System.out.println("Got Session : " + session);

        javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(session);
        try {
            System.out.println("before sending");


            message.setSubject("My First Email Attempt from Java");
            message.setText("Hi, This mail came from Java Application.");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(
                    "/Users/asif/AMT-TestFrameWork/src/main/java/test/Log/100.jpeg");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            // put everything together
            message.setContent(multipart);


            message.setFrom(new InternetAddress(GMAIL_USERNAME));
            message.addRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Receipient));

            Transport transport = session.getTransport("smtp");
            System.out.println("Got Transport" + transport);
            transport.connect(SMTP_HOST, GMAIL_USERNAME, GMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("message Object : " + message);
            System.out.println("Email Sent Successfully");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}