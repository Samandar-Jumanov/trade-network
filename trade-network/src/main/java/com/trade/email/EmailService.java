package com.trade.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Async
public class EmailService {

    private final JavaMailSender emailSender ;
    private final SpringTemplateEngine templateEngine;


    public void sendEmail(
            String to,
            EmailTemplate emailTemplate ,
            String username  ,
            String confirmationUrl ,
            String activationCode ,
            String subject
    ) throws MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();

        String templateName;

        if(emailTemplate == null) {
              templateName = "confirm-email";
        }

        templateName = emailTemplate.name();


        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage ,
                 MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
                );



        Map<String , Object > properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        mimeMessageHelper.setFrom("contact@trade.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);



        String html = templateEngine.process(templateName, context);
        mimeMessageHelper.setText(html, true);


        emailSender.send(mimeMessage);





    }

}
