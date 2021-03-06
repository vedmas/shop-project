package ru.tokarev.shop.service.mailSender;

import ru.tokarev.shop.service.repr.MailInfo;

import javax.mail.MessagingException;

public interface MailSendService {
    void sendMailHtml(MailInfo mailInfo) throws MessagingException;

    void sendMail(String userEmail, String topicMessage, String textMessage);
}
