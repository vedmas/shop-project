package ru.tokarev.shop.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;


@RestController
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template;

    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/send_message")
    @SendTo("/chat_out/receive_message")
    public ChatMessage messageReceiver(ChatMessage message) {
        log.info("New chat message {}", message);
        return new ChatMessage("server", "Echo: " + HtmlUtils.htmlEscape(message.getMessage()));
    }

    @GetMapping("/test/message")
    public void sendMessage() {
        template.convertAndSend("/chat_out/receive_message", new ChatMessage("Server", "Test message"));
    }

}
