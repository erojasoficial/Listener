package com.example.demo.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class SendMailRequest {

    @JsonProperty("source_application")
    private String sourceApplication;

    @JsonProperty("business_line")
    private String businessLine;

    @JsonProperty("customer_unique_id")
    private String customerUniqueId;

    @JsonProperty("customer_unique_id_type")
    private CustomerUniqueIdType customerUniqueIdType;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("delivery_methods")
    @Valid
    private List<DeliveryMethod> deliveryMethods;

    @JsonProperty("template")
    private Template template;

    @JsonProperty("attachments")
    @Valid
    private List<Attachment> attachments;

    @JsonProperty("sender")
    private Sender sender;
    @JsonProperty("priority")
    private PriorityEnum priority;
    @JsonProperty("auditable")
    private AuditableEnum auditable;

    public SendMailRequest sourceApplication(String sourceApplication) {
        this.sourceApplication = sourceApplication;
        return this;
    }

    @NotNull
    @Size(min = 1, max = 64)
    public String getSourceApplication() {
        return this.sourceApplication;
    }

    public void setSourceApplication(String sourceApplication) {
        this.sourceApplication = sourceApplication;
    }

    public SendMailRequest businessLine(String businessLine) {
        this.businessLine = businessLine;
        return this;
    }

    @NotNull
    @Size(min = 1, max = 64)
    public String getBusinessLine() {
        return this.businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public SendMailRequest customerUniqueId(String customerUniqueId) {
        this.customerUniqueId = customerUniqueId;
        return this;
    }

    @NotNull
    @Size(min = 1, max = 64)
    public String getCustomerUniqueId() {
        return this.customerUniqueId;
    }

    public void setCustomerUniqueId(String customerUniqueId) {
        this.customerUniqueId = customerUniqueId;
    }

    public SendMailRequest customerUniqueIdType(CustomerUniqueIdType customerUniqueIdType) {
        this.customerUniqueIdType = customerUniqueIdType;
        return this;
    }

    @NotNull
    @Valid
    public CustomerUniqueIdType getCustomerUniqueIdType() {
        return this.customerUniqueIdType;
    }

    public void setCustomerUniqueIdType(CustomerUniqueIdType customerUniqueIdType) {
        this.customerUniqueIdType = customerUniqueIdType;
    }

    public SendMailRequest requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    @NotNull
    @Size(min = 1, max = 64)
    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public SendMailRequest deliveryMethods(List<DeliveryMethod> deliveryMethods) {
        this.deliveryMethods = Collections.unmodifiableList(deliveryMethods);
        return this;
    }

    public SendMailRequest addDeliveryMethodsItem(DeliveryMethod deliveryMethodsItem) {
        if (this.deliveryMethods == null) {
            this.deliveryMethods = new ArrayList<>();
        }
        this.deliveryMethods.add(deliveryMethodsItem);
        return this;
    }

    @Valid
    @Size(min = 1, max = 5)
    public List<DeliveryMethod> getDeliveryMethods() {
        return Collections.unmodifiableList(this.deliveryMethods);
    }

    public void setDeliveryMethods(List<DeliveryMethod> deliveryMethods) {
        this.deliveryMethods = Collections.unmodifiableList(deliveryMethods);
    }

    public SendMailRequest template(Template template) {
        this.template = new Template(template);
        return this;
    }

    @Valid
    public Template getTemplate() {
        return new Template(this.template);
    }

    public void setTemplate(Template template) {
        this.template = new Template(template);
    }

    public SendMailRequest attachments(List<Attachment> attachments) {
        this.attachments = new ArrayList<>(attachments);
        return this;
    }

    public SendMailRequest addAttachmentsItem(Attachment attachmentsItem) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }
        this.attachments.add(attachmentsItem);
        return this;
    }

    @Valid
    @Size(max = 10)
    public List<Attachment> getAttachments() {
        return Collections.unmodifiableList(this.attachments);
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = Collections.unmodifiableList(attachments);
    }

    public SendMailRequest sender(Sender sender) {
        this.sender = new Sender(sender);
        return this;
    }

    @Valid
    public Sender getSender() {
        return new Sender(this.sender);
    }

    public void setSender(Sender sender) {
        this.sender = new Sender(sender);
    }

    public SendMailRequest priority(PriorityEnum priority) {
        this.priority = priority;
        return this;
    }

    public PriorityEnum getPriority() {
        return this.priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public SendMailRequest auditable(AuditableEnum auditable) {
        this.auditable = auditable;
        return this;
    }

    public AuditableEnum getAuditable() {
        return this.auditable;
    }

    public void setAuditable(AuditableEnum auditable) {
        this.auditable = auditable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SendMailRequest sendMailRequest = (SendMailRequest) o;
        return Objects.equals(this.sourceApplication, sendMailRequest.sourceApplication) && Objects.equals(this.businessLine, sendMailRequest.businessLine) && Objects.equals(this.customerUniqueId, sendMailRequest.customerUniqueId) && Objects.equals(this.customerUniqueIdType, sendMailRequest.customerUniqueIdType) && Objects.equals(this.requestId, sendMailRequest.requestId) && Objects.equals(this.deliveryMethods, sendMailRequest.deliveryMethods) && Objects.equals(this.template, sendMailRequest.template) && Objects.equals(this.attachments, sendMailRequest.attachments) && Objects.equals(this.sender, sendMailRequest.sender) && Objects.equals(this.priority, sendMailRequest.priority) && Objects.equals(this.auditable, sendMailRequest.auditable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sourceApplication, this.businessLine, this.customerUniqueId, this.customerUniqueIdType, this.requestId, this.deliveryMethods, this.template, this.attachments, this.sender, this.priority, this.auditable);
    }

    @Override
    public String toString() {
        String sb = "class SendMailRequest {\n";
        sb = sb + "    sourceApplication: " + toIndentedString(this.sourceApplication) + "\n";
        sb = sb + "    businessLine: " + toIndentedString(this.businessLine) + "\n";
        sb = sb + "    customerUniqueId: " + toIndentedString(this.customerUniqueId) + "\n";
        sb = sb + "    customerUniqueIdType: " + toIndentedString(this.customerUniqueIdType) + "\n";
        sb = sb + "    requestId: " + toIndentedString(this.requestId) + "\n";
        sb = sb + "    deliveryMethods: " + toIndentedString(this.deliveryMethods) + "\n";
        sb = sb + "    template: " + toIndentedString(this.template) + "\n";
        sb = sb + "    attachments: " + toIndentedString(this.attachments) + "\n";
        sb = sb + "    sender: " + toIndentedString(this.sender) + "\n";
        sb = sb + "    priority: " + toIndentedString(this.priority) + "\n";
        sb = sb + "    auditable: " + toIndentedString(this.auditable) + "\n";
        sb = sb + "}";
        return sb;
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public enum PriorityEnum {
        INMEDIATE("1"),

        ASYNCHRONOUS("2");

        private String value;

        PriorityEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static PriorityEnum fromValue(String value) {
            for (PriorityEnum b : PriorityEnum.values()) {
                if (b.value.equals(value.trim())) {
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

    public enum AuditableEnum {
        ACTIVE("1"),

        INACTIVE("0");

        private String value;

        AuditableEnum(String value) {
            this.value = value;
        }

        @JsonCreator
        public static AuditableEnum fromValue(String value) {
            for (AuditableEnum b : AuditableEnum.values()) {
                if (b.value.equals(value.trim())) {
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