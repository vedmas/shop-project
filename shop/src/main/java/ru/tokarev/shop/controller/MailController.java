package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tokarev.shop.service.mailSender.MailSendService;
import ru.tokarev.shop.service.repr.MailInfo;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Value("${path.email.page}")
    private String pathEmailPage;

    private MailSendService mailSendService;

    @Autowired
    public void setMailSendService(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

    @ResponseBody
    @RequestMapping("/send")
    public String sendMail() throws MessagingException {
        Map<String, Object> model = new HashMap<>();
        Map<String, String> attachFile = new HashMap<>();
        attachFile.put("attach.bmp", "/templates/email-templates/attach/attach.bmp");
        MailInfo mailInfo = new MailInfo(
                "07011984t@mail.ru",
                "Your order for Shop-Electro for map",
                pathEmailPage + "e-msg-order1",
                attachFile,
                model);
        mailSendService.sendMailHtml(mailInfo);
        return "Message send";
    }
}
