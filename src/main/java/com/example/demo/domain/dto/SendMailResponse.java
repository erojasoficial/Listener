package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.processing.Generated;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class SendMailResponse {

    @JsonProperty("data")
    private SendMailResponseData data;

    @JsonProperty("notifications")
    @Valid
    private List<Notification> notifications;

    public SendMailResponse data(SendMailResponseData data) {
        this.data = new SendMailResponseData(data);
        return this;
    }

    @Valid
    public SendMailResponseData getData() {
        return new SendMailResponseData(this.data);
    }

    public void setData(SendMailResponseData data) {
        this.data = new SendMailResponseData(data);
    }

    public SendMailResponse notifications(List<Notification> notifications) {
        this.notifications = new ArrayList<>(notifications);
        return this;
    }

    public SendMailResponse addNotificationsItem(Notification notificationsItem) {
        if (this.notifications == null) {
            this.notifications = new ArrayList<>();
        }
        this.notifications.add(notificationsItem);
        return this;
    }

    @Valid
    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(this.notifications);
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = new ArrayList<>(notifications);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SendMailResponse sendMailResponse = (SendMailResponse) o;
        return Objects.equals(this.data, sendMailResponse.data)
                && Objects.equals(this.notifications, sendMailResponse.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.data, this.notifications);
    }

    @Override
    public String toString() {
        String sb = "class SendMailResponse {\n";
        sb = sb + "    data: " + toIndentedString(this.data) + "\n";
        sb = sb + "    notifications: " + toIndentedString(this.notifications) + "\n";
        sb = sb + "}";
        return sb;
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

