package com.example.demo.converter;

import com.example.demo.domain.dto.*;
import com.example.demo.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StringPositionToCustomDtoConverter {

    private final SendMailRequestFactory sendMailRequestFactory;
    private final DeliveryMethodFactory deliveryMethodFactory;
    private final TemplateFactory templateFactory;
    private final SenderFactory senderFactory;
    private final ContentMapFactory contentMapFactory;

    public StringPositionToCustomDtoConverter(SendMailRequestFactory sendMailRequestFactory, DeliveryMethodFactory deliveryMethodFactory, TemplateFactory templateFactory, SenderFactory senderFactory, ContentMapFactory contentMapFactory) {
        this.sendMailRequestFactory = sendMailRequestFactory;
        this.deliveryMethodFactory = deliveryMethodFactory;
        this.templateFactory = templateFactory;
        this.senderFactory = senderFactory;
        this.contentMapFactory = contentMapFactory;
    }

    public SendMailRequest convert(StringPosition[] positions) {
        SendMailRequest request = sendMailRequestFactory.create(positions);
        DeliveryMethod deliveryMethod = deliveryMethodFactory.create(positions);
        Map<String, Object> contentMap = contentMapFactory.create(positions);
        Template template = templateFactory.create(positions, contentMap);
        Sender sender = senderFactory.create(positions);

        List<DeliveryMethod> deliveryMethods = new ArrayList<>();
        deliveryMethods.add(deliveryMethod);

        request.setDeliveryMethods(deliveryMethods);
        request.setTemplate(template);
        request.setSender(sender);
        request.setAttachments(new ArrayList<>());

        return request;
    }
}
