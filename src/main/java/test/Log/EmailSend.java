package test.Log;

import org.apache.commons.mail.*;
import test.coreModule.TestPlan;
import test.utility.PropertyConfig;
import java.io.File;
import java.time.LocalDateTime;

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
        try {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath("./Report/" + reportName);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Log Report");
            attachment.setName(reportName);

            EmailAttachment dashboard = new EmailAttachment();
            dashboard.setPath("./Report/PassedImage/Dashboard.png");
            dashboard.setDisposition(EmailAttachment.ATTACHMENT);
            dashboard.setDescription("Dashboard");
            dashboard.setName("Dashboard");

            EmailAttachment category = new EmailAttachment();
            category.setPath("./Report/PassedImage/Category.png");
            category.setDisposition(EmailAttachment.ATTACHMENT);
            category.setDescription("Category");
            category.setName("Category");

            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(PropertyConfig.getPropertyValue("testingEmailAddress"), PropertyConfig.getPropertyValue("testingEmailPassword")));
            email.setSSL(true);
            String[] recepients = receivers.split(",");
            for(String recepient : recepients)
                email.addTo(recepient);
            email.setFrom(PropertyConfig.getPropertyValue("testingEmailAddress"), "Testing");
            if (reportType){
                email.setSubject(getPassedEmailSubject());
                email.setMsg(getPassedEmailBody());

            }else {
                email.setSubject(getFailedEmailSubject());
                email.setMsg(getFailedEmailBody());

            }
            email.attach(dashboard);
            email.attach(category);
            email.attach(attachment);
            email.send();

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
