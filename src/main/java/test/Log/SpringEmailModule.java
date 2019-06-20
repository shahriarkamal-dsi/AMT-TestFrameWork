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
            String env = TestPlan.getInstance().getCurrentTestEnvironment().getEnv().toUpperCase() ;
            String version = PropertyConfig.getPropertyValue("version");
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("ehsanulaiub15@outlook.com");
            helper.setTo(recipeints);
            File logFile = new File("./Report/" + PropertyConfig.getPropertyValue("passedReprtName"));
            File extentReportUserManual = new File("./Report/User Manual.pdf");
            helper.addAttachment("LogReport.html",logFile);
            helper.addAttachment("ReadMe.pdf",extentReportUserManual);
            helper.setSubject(getPassedEmailSubject());
            File dashBoard = new File("./Report/PassedImage/Dashboard.png");
            if(dashBoard.exists() && !dashBoard.isDirectory()) { 
                helper.setText(
                    "<html>"
                            + "<body>"
                            + "<div>Hi All,"
                            + "<div> <br> Please find below the Snapshot of the Automated Login Smoke Test executed against <b> " + env + "</b> Version: <b>" + version + " </b></div>"  + "<div>"
                            + "<img src='cid:leftSideImage' style='float:center;'  width='800' height='400' />"  + "</div>"
                            + "<div> <p> Attached a detailed script execution report in html format."
                            + "<br> Please use the attached ReadMe file for help with the dashboard elements."
                            +"<br> NOTE : The attached html file might NOT render properly if viewed from any mobile device."
                            +"<br> We are currently in the process of fixing this. However, it looks good when opened from outlook on desktop. </p> </div> "
                            + "<div>Thanks,</div>"
                            + "<div>QA Automation Team</div>"
                            + "</div></body>"
                            + "</html>", true);
            // helper.addInline("rightSideImage",
            // new File("C:/images/SpringSource-logo.jpg"));

            helper.addInline("leftSideImage", dashBoard);
            } else {
                helper.setText(
                        "<html>"
                                + "<body>"
                                + "<div>Hi All,"
                                + "<div> <p> Attached a detailed script execution report in html format."
                                + "<br> Please use the attached ReadMe file for help with the dashboard elements."
                                +"<br> NOTE : The attached html file might NOT render properly if viewed from any mobile device."
                                +"<br> We are currently in the process of fixing this. However, it looks good when opened from outlook on desktop. </p> </div> "
                                + "<div>Thanks,</div>"
                                + "<div>QA Automation Team</div>"
                                + "</div></body>"
                                + "</html>", true);
                
            }
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
        String Subject = "Automated Login Smoke Test in Production " + dateTime.getMonthValue()+"." + dateTime.getDayOfMonth()+ "." +dateTime.getYear()+ "_" +dateTime.getHour()+ ":" +dateTime.getMinute()+ ":" +dateTime.getSecond();
        return Subject;
    }

}