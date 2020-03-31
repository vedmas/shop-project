package ru.tokarev.shop.service.mailSender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import ru.tokarev.shop.service.repr.MailInfo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@Slf4j
public class MailSenderServiceImpl implements MailSendService {

    private JavaMailSender mailSender;

    private SpringTemplateEngine templateEngine;

    public MailSenderServiceImpl(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    //Отправка html-thymeleaf сообщений
    @Override
    public void sendMailHtml(MailInfo mailInfo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        if(mailInfo.getAttachFile() != null) {
            mailInfo.getAttachFile().forEach((key, value) -> {
                try {
                    helper.addAttachment(key, new ClassPathResource(value));
                } catch (MessagingException e) {
                    log.error("", e);
                }
            });
        }
        Context context = new Context();
        context.setVariables(mailInfo.getModel()); //передаем объекты в html страницу
        String html = templateEngine.process(mailInfo.getMailPageName(), context);
        helper.setTo(mailInfo.getTo());
        helper.setSubject(mailInfo.getSubject());
        helper.setText(html, true);
        this.mailSender.send(message);
    }


    //Отправка обычных сообщений
    @Override
    public void sendMail(String userEmail, String topicMessage, String textMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject(topicMessage);
        message.setText(textMessage);
        this.mailSender.send(message);
    }
}
