package ru.tokarev.shop.service.repr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
    private String to;
    private String subject;
    private String mailPageName;
    private Map<String, String> attachFile;
    private Map<String, Object> model;

    public MailInfo(String to, String subject, String mailPageName, Map<String, Object> model) {
        this.to = to;
        this.subject = subject;
        this.mailPageName = mailPageName;
        this.model = model;
    }
}


