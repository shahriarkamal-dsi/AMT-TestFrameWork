package test.Log;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import test.coreModule.TestPlan;
import test.utility.PropertyConfig;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;

@Component
public class SpringEmailModule {

    @Autowired
    private JavaMailSender mailSender ;

    public SpringEmailModule() {
    }

    public void emailSend() {
        try {
            String[] recipeints =  PropertyConfig.getPropertyValue("successfullTCrecipeints").split(",") ;
            MimeMessage message = mailSender.createMimeMessage();
            String env = TestPlan.getInstance().getCurrentTestEnvironment().getEnv() ;
            String version = PropertyConfig.getPropertyValue("version");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("ehsanulaiub15@outlook.com");
            helper.setTo(recipeints);
            File logFile = new File("./Report/" + PropertyConfig.getPropertyValue("passedReprtName"));
            File dashBoard = new File("./Report/PassedImage/Dashboard.png");
            helper.addAttachment("LogReport.html",logFile);

            helper.setSubject(getPassedEmailSubject());
            helper.setText(
                    "<html>"
                            + "<body>"
                            + "<div>Hi All,"
                            + "<div> <br> Following is the summary of Automated Test run in  <b>APP</b> Version: <b>" + version + " </b></div>"  + "<div>"
                            + "<img src='cid:leftSideImage' style='float:center;'  width='800' height='400' />"  + "</div>"
                            + "<div> <p> Additionally, please find the attached detailed report. "
                             +" <br> <strong> NOTE: To view the report please download the HTML file. </strong> </p> </div> "
                            + "<div>Thanks,</div>"
                            + "</div></body>"
                            + "</html>", true);
            // helper.addInline("rightSideImage",
            //     new File("C:/images/SpringSource-logo.jpg"));

            helper.addInline("leftSideImage", dashBoard);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    private  String getFailedEmailSubject(){

        String env = TestPlan.getInstance().getCurrentTestEnvironment().getEnv() ;
        LocalDateTime dateTime = TestPlan.getInstance().getCreationTime();
        String Subject = env.toUpperCase() + " - Login Test Failed Scenarios  " + dateTime.getMonthValue()+"-" + dateTime.getDayOfMonth()+ "-" +dateTime.getYear();
        return Subject;
    }

    private  String getPassedEmailSubject(){

        String env = TestPlan.getInstance().getCurrentTestEnvironment().getEnv() ;
        LocalDateTime dateTime = TestPlan.getInstance().getCreationTime();
        String Subject = env.toUpperCase() + " - Login Test  " + dateTime.getMonthValue()+"-" + dateTime.getDayOfMonth()+ "-" +dateTime.getYear();
        return Subject;
    }

}
