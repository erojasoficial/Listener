package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.processing.Generated;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class Notification {

    @JsonProperty("category")
    private String category;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("description")
    private String description;

    @JsonProperty("action")
    private String action;

    @JsonProperty("metadata")
    @Valid
    private Map<String, Object> metadata;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("timestamp")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime timestamp;

    @JsonProperty("severity")
    private String severity;

    @JsonProperty("field_name")
    private String fieldName;

    public Notification category(String category) {
        this.category = category;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Notification code(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Notification message(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notification description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Notification action(String action) {
        this.action = action;
        return this;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Notification metadata(Map<String, Object> metadata) {
        this.metadata = new HashMap<>(metadata);
        return this;
    }

    public Notification putMetadataItem(String key, Object metadataItem) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, metadataItem);
        return this;
    }

    public Map<String, Object> getMetadata() {
        return Collections.unmodifiableMap(this.metadata);
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = new HashMap<>(metadata);
    }

    public Notification uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Notification timestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Valid
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Notification severity(String severity) {
        this.severity = severity;
        return this;
    }

    public String getSeverity() {
        return this.severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Notification fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification notification = (Notification) o;
        return Objects.equals(this.category, notification.category) && Objects.equals(this.code, notification.code)
                && Objects.equals(this.message, notification.message)
                && Objects.equals(this.description, notification.description)
                && Objects.equals(this.action, notification.action)
                && Objects.equals(this.metadata, notification.metadata) && Objects.equals(this.uuid, notification.uuid)
                && Objects.equals(this.timestamp, notification.timestamp)
                && Objects.equals(this.severity, notification.severity)
                && Objects.equals(this.fieldName, notification.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.category, this.code, this.message, this.description, this.action,
                this.metadata, this.uuid, this.timestamp, this.severity, this.fieldName);
    }

    @Override
    public String toString() {
        String sb = "class Notification {\n";
        sb = sb + "    category: " + toIndentedString(this.category) + "\n";
        sb = sb + "    code: " + toIndentedString(this.code) + "\n";
        sb = sb + "    message: " + toIndentedString(this.message) + "\n";
        sb = sb + "    description: " + toIndentedString(this.description) + "\n";
        sb = sb + "    action: " + toIndentedString(this.action) + "\n";
        sb = sb + "    metadata: " + toIndentedString(this.metadata) + "\n";
        sb = sb + "    uuid: " + toIndentedString(this.uuid) + "\n";
        sb = sb + "    timestamp: " + toIndentedString(this.timestamp) + "\n";
        sb = sb + "    severity: " + toIndentedString(this.severity) + "\n";
        sb = sb + "    fieldName: " + toIndentedString(this.fieldName) + "\n";
        sb = sb + "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}