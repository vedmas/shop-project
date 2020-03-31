package ru.tokarev.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ChatAdminController {

    @GetMapping("/admin_chat")
    public String adminChat() {
        return "admin/admin_chat";
    }
}
