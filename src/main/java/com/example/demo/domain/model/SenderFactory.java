package com.example.demo.domain.model;

import com.example.demo.domain.dto.Sender;
import org.springframework.stereotype.Component;

@Component
public class SenderFactory {
    public Sender create(StringPosition[] positions) {
        Sender sender = new Sender();
        String emailValue = positions[9].getValue();
        if (emailValue != null) {
            sender.setEmail(emailValue.replaceAll("\\s+$", ""));
        }
        String nameValue = positions[10].getValue();
        if (nameValue != null) {
            sender.setName(nameValue.replaceAll("\\s+$", ""));
        }
        return sender;
    }
}
