package com.example.demo.domain.model;

import com.example.demo.domain.dto.DeliveryMethod;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMethodFactory {
    public DeliveryMethod create(StringPosition[] positions) {
        DeliveryMethod deliveryMethod = new DeliveryMethod();
        String deliveryDestination = positions[5].getValue();
        if (deliveryDestination != null) {
            deliveryMethod.setDeliveryDestination(deliveryDestination.replaceAll("\\s+$", ""));
            deliveryMethod.setDeliveryType(DeliveryMethod.DeliveryTypeEnum.fromValue("EMAIL"));
            deliveryMethod.setDeviceType(DeliveryMethod.DeviceTypeEnum.fromValue("ANDROID"));
            deliveryMethod.setDeviceModel("xiaomi");
            deliveryMethod.setProvider("ENTEL");
            deliveryMethod.setCountryCallingCode("51");
        }
        return deliveryMethod;
    }
}
