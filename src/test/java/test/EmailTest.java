package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import test.utility.PropertyConfig;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmtTestFrameworkApplication.class)
public class EmailTest {
    @Autowired
    private JavaMailSender mailSender ;

      @Test
      public void test() {
        try {
            String[] recipeints =  PropertyConfig.getPropertyValue("successfullTCrecipeints").split(",") ;
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("testing4010@gmail.com");
            helper.setTo(recipeints);
            File file = new File("./Report/" + PropertyConfig.getPropertyValue("passedReprtName"));
            helper.addAttachment("log.html",file);

            helper.setSubject("Email with Inline images Example");
            helper.setText(
                    "<html>"
                            + "<body>"
                            + "<div>Hi All,"
                            + "<div><strong>Add the image to the left :</strong></div>"  + "<div>"
                            + "<img src='cid:leftSideImage' style='float:left;'/>"
                            + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                            + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                            + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                            + "<div>Adding a inline resource/image on to the left of the paragraph.</div>"
                            + "</div>"
                            + "<div>Thanks,</div>"
                            + "kalliphant"
                            + "</div></body>"
                            + "</html>", true);
           // helper.addInline("rightSideImage",
               //     new File("C:/images/SpringSource-logo.jpg"));

            helper.addInline("leftSideImage",
                    new File("C:/images/Dashboard.png"));

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
