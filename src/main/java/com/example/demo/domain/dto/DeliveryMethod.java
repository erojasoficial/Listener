package com.example.demo.domain.dto;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class DeliveryMethod {

    @JsonProperty("delivery_type")
    private DeliveryTypeEnum deliveryType;
    @JsonProperty("delivery_destination")
    private String deliveryDestination;
    @JsonProperty("device_type")
    private DeviceTypeEnum deviceType;
    @JsonProperty("device_model")
    private String deviceModel;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("country_calling_code")
    private String countryCallingCode;

    public DeliveryMethod deliveryType(DeliveryTypeEnum deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    @NotNull
    public DeliveryTypeEnum getDeliveryType() {
        return this.deliveryType;
    }

    public void setDeliveryType(DeliveryTypeEnum deliveryType) {
        this.deliveryType = deliveryType;
    }

    public DeliveryMethod deliveryDestination(String deliveryDestination) {
        this.deliveryDestination = deliveryDestination;
        return this;
    }

    @NotNull
    @Size(min = 1, max = 256)
    public String getDeliveryDestination() {
        return this.deliveryDestination;
    }

    public void setDeliveryDestination(String deliveryDestination) {
        this.deliveryDestination = deliveryDestination;
    }

    public DeliveryMethod deviceType(DeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public DeviceTypeEnum getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(DeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }

    public DeliveryMethod deviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    @Size(max = 64)
    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public DeliveryMethod provider(String provider) {
        this.provider = provider;
        return this;
    }

    @Size(max = 10)
    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public DeliveryMethod countryCallingCode(String countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
        return this;
    }

    @Size(max = 10)
    public String getCountryCallingCode() {
        return this.countryCallingCode;
    }

    public void setCountryCallingCode(String countryCallingCode) {
        this.countryCallingCode = countryCallingCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeliveryMethod deliveryMethod = (DeliveryMethod) o;
        return Objects.equals(this.deliveryType, deliveryMethod.deliveryType)
                && Objects.equals(this.deliveryDestination, deliveryMethod.deliveryDestination)
                && Objects.equals(this.deviceType, deliveryMethod.deviceType)
                && Objects.equals(this.deviceModel, deliveryMethod.deviceModel)
                && Objects.equals(this.provider, deliveryMethod.provider)
                && Objects.equals(this.countryCallingCode, deliveryMethod.countryCallingCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deliveryType, this.deliveryDestination, this.deviceType, this.deviceModel, this.provider, this.countryCallingCode);
    }

    @Override
    public String toString() {
        String sb = "class DeliveryMethod {\n";
        sb = sb + "    deliveryType: " + toIndentedString(this.deliveryType) + "\n";
        sb = sb + "    deliveryDestination: " + toIndentedString(this.deliveryDestination) + "\n";
        sb = sb + "    deviceType: " + toIndentedString(this.deviceType) + "\n";
        sb = sb + "    deviceModel: " + toIndentedString(this.deviceModel) + "\n";
        sb = sb + "    provider: " + toIndentedString(this.provider) + "\n";
        sb = sb + "    countryCallingCode: " + toIndentedString(this.countryCallingCode) + "\n";
        sb = sb + "}";
        return sb;
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public enum DeliveryTypeEnum {
        EMAIL("EMAIL"),
        PUSH("PUSH"),
        SMS("SMS"),
        VOICE("VOICE");

        private String value;

        DeliveryTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static DeliveryTypeEnum fromValue(String value) {
            for (DeliveryTypeEnum b : DeliveryTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    public enum DeviceTypeEnum {
        ANDROID("ANDROID"),
        IOS("IOS");

        private String value;

        DeviceTypeEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static DeviceTypeEnum fromValue(String value) {
            for (DeviceTypeEnum b : DeviceTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }
}
