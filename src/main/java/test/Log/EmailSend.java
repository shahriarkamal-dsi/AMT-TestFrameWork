package test.Log;

import org.apache.commons.mail.*;
import test.utility.PropertyConfig;

import java.io.File;

public class EmailSend {

    public static void sendLogReport(){
        File file = new File("./Report/" + PropertyConfig.getPropertyValue("passedReprtName"));
        if (file.exists()) {
            sendEmail(PropertyConfig.getPropertyValue("passedReprtName"),PropertyConfig.getPropertyValue("successfullTCrecipeints"));
        }
        file = new File("./Report/" + PropertyConfig.getPropertyValue("failedReprtName"));
        if (file.exists()) {
            sendEmail(PropertyConfig.getPropertyValue("failedReprtName"),PropertyConfig.getPropertyValue("failedTCrecipeints"));
        }
    }
    private static void sendEmail(String reportName,String receivers) {
        try {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("./Report/" + reportName);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Log Report");
            attachment.setName("LogReport.html");

            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(PropertyConfig.getPropertyValue("testingEmailAddress"), PropertyConfig.getPropertyValue("testingEmailPassword")));
            email.setSSL(true);
           // email.addTo("shahriarcsedu@gmail.com", "Shahriar Kamal");
            String[] recepients = receivers.split(",");
            for(String recepient : recepients)
                email.addTo(recepient);
            email.setFrom(PropertyConfig.getPropertyValue("testingEmailAddress"), "Testing");
            email.setSubject("Log Report");
            email.setMsg("Please find the attachment of the log report and download it");

            // add the attachment
            email.attach(attachment);

            // send the email
            email.send();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void email() {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("shahriarkamal.dsi@gmail.com", "4018qwert"));
            email.setSSL(true);
            email.setFrom("shahriarkamal.dsi@gmail.com");
            email.setSubject("TestMail");
            email.setMsg("This is a test mail ... :-)");
            email.addTo("shahriarcsedu@gmail.com");
            email.send();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}
