package com.example.demo.domain.model;

import com.example.demo.domain.dto.CustomerUniqueIdType;
import com.example.demo.domain.dto.SendMailRequest;
import org.springframework.stereotype.Component;

@Component
public class SendMailRequestFactory {
    public SendMailRequest create(StringPosition[] positions) {
        SendMailRequest request = new SendMailRequest();

        String sourceApplication = positions[0].getValue();
        if (sourceApplication != null) {
            request.setSourceApplication(sourceApplication.replaceAll("\\s+$", ""));
        }
        System.out.println("setSourceApplication:"+positions[0].getValue());

        String businessLine = positions[1].getValue();
        if (businessLine != null) {
            request.setBusinessLine(businessLine.replaceAll("\\s+$", ""));
        }
        System.out.println("setBusinessLine:"+positions[1].getValue());

        String customerUniqueId = positions[2].getValue();
        if (customerUniqueId != null) {
            request.setCustomerUniqueId(customerUniqueId.replaceAll("\\s+$", ""));
        }
        System.out.println("setCustomerUniqueId:"+positions[2].getValue());

        String customerUniqueIdType = positions[3].getValue();
        if (customerUniqueIdType != null) {
            request.setCustomerUniqueIdType(CustomerUniqueIdType.fromValue(customerUniqueIdType.replaceAll("\\s+$", "")));
        }
        System.out.println("setCustomerUniqueIdType:"+positions[3].getValue());

        String requestId = positions[4].getValue();
        if (requestId != null) {
            request.setRequestId(requestId.replaceAll("\\s+$", ""));
        }
        System.out.println("setRequestId:"+positions[4].getValue());

        String priority = positions[11].getValue();
        if (priority != null) {
            request.setPriority(SendMailRequest.PriorityEnum.fromValue(priority.replaceAll("\\s+$", "")));
        }

        String auditable = positions[12].getValue();
        if (auditable != null) {
            request.setAuditable(SendMailRequest.AuditableEnum.fromValue(auditable.replaceAll("\\s+$", "")));
        }

        return request;
    }
}
