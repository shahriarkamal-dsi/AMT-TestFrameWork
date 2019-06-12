package test.Log;

import test.coreModule.TestPlan;
import test.utility.PropertyConfig;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Properties;

public class EmailSend {

    private static String env = PropertyConfig.getPropertyValue("env");
    private static String version = PropertyConfig.getPropertyValue("version");


    public static void sendLogReport(){
        File file = new File("./Report/" + PropertyConfig.getPropertyValue("passedReprtName"));
        if (file.exists()) {
            sendEmail(PropertyConfig.getPropertyValue("passedReprtName"),PropertyConfig.getPropertyValue("successfullTCrecipeints"),true);
        }
        file = new File("./Report/" + PropertyConfig.getPropertyValue("failedReprtName"));
        if (file.exists()) {
            sendEmail(PropertyConfig.getPropertyValue("failedReprtName"),PropertyConfig.getPropertyValue("failedTCrecipeints"),false);
        }
    }
    private static void sendEmail(String reportName,String receivers, boolean reportType) {

        final String SMTP_HOST = "smtp.gmail.com";
        final String SMTP_PORT = "587";
        final String GMAIL_USERNAME = "testing4010@gmail.com";
        final String GMAIL_PASSWORD = "12345678amt";


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
            /*
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("./Report/" + reportName);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Log Report");
            attachment.setName(reportName);

            EmailAttachment dashboard = new EmailAttachment();
            dashboard.setPath("./Report/PassedImage/Dashboard.png");
            dashboard.setDisposition(EmailAttachment.ATTACHMENT);
            dashboard.setDescription("Dashboard");
            dashboard.setName("Dashboard.png");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part three is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "/home/manisha/file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("This is message body");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "/home/manisha/file.txt";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);
            */

            System.out.println("before sending");

            // This mail has 3 part, the BODY , the embedded image and the attachment
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            //DataSource fds = new FileDataSource("./Report/PassedImage/Dashboard.png");

            DataSource fds = new FileDataSource("/Users/asif/AMT-TestFrameWork/Report");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            // Part 3.1 is attachment 01
           /* messageBodyPart = new MimeBodyPart();
            String filename = "./Report/" + reportName;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Part 3.2 is attachment 02
            messageBodyPart = new MimeBodyPart();
            String dashboard = "./Report/PassedImage/Dashboard.png";
            DataSource dashboardsource = new FileDataSource(dashboard);
            messageBodyPart.setDataHandler(new DataHandler(dashboardsource));
            messageBodyPart.setFileName(dashboard);
            multipart.addBodyPart(messageBodyPart);*/

            // put everything together
            message.setContent(multipart);

            String[] recepients = receivers.split(",");
            for(String recepient : recepients)
                message.addRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recepient));
            message.setFrom(new InternetAddress(GMAIL_USERNAME));

            if (reportType){
                message.setSubject(getPassedEmailSubject());
                message.setText(getPassedEmailBody());

            }else {
                message.setSubject(getFailedEmailSubject());
                message.setText(getFailedEmailBody());
            }

            Transport transport = session.getTransport("smtp");
            System.out.println("Got Transport" + transport);
            transport.connect(SMTP_HOST, GMAIL_USERNAME, GMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("message Object : " + message);
            System.out.println("Email Sent Successfully");

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    private static String getPassedEmailBody(){

        String BODY = String.join(
                System.getProperty("line.separator"),
                "Hi all,",
                " ",
                "Please find the attached results of the Automated Smoke Test performed around FASB/IASB in " + env.toUpperCase()+ " Version: " + version,
                " ",
                "NOTE: To view the report please download the HTML file.",
                " ",
                "Thank you!"
        );
        return BODY;
    }

    private static String getFailedEmailBody(){

        String BODY = String.join(
                System.getProperty("line.separator"),
                "Hi all,",
                " ",
                "Please find the attached summary of Failed test cases as part of Automated Smoke Test around FASB/IASB in " + env.toUpperCase() +" Version: " + version ,
                " ",
                "NOTE: To view the report please download the HTML file.",
                " ",
                "Thank you!"
        );
        return BODY;
    }
    private static String getFailedEmailSubject(){

        LocalDateTime dateTime = TestPlan.getInstance().getCreationTime();
        String Subject = env.toUpperCase() + " - Login Test Failed Scenarios  " + dateTime.getMonthValue()+"-" + dateTime.getDayOfMonth()+ "-" +dateTime.getYear();
        return Subject;
    }

    private static String getPassedEmailSubject(){

        LocalDateTime dateTime = TestPlan.getInstance().getCreationTime();
        String Subject = env.toUpperCase() + " - Login Test  " + dateTime.getMonthValue()+"-" + dateTime.getDayOfMonth()+ "-" +dateTime.getYear();
        return Subject;
    }
}
