package com.example.demo.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class EmptyDataResponse {

    @JsonProperty("data")
    private Object data;

    @JsonProperty("notifications")
    @Valid
    private List<Notification> notifications = new ArrayList<>();

    public EmptyDataResponse data(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public EmptyDataResponse notifications(List<Notification> notifications) {
        this.notifications = Collections.unmodifiableList(notifications);
        return this;
    }

    public EmptyDataResponse addNotificationsItem(Notification notificationsItem) {
        if (this.notifications == null) {
            this.notifications = new ArrayList<>();
        }
        this.notifications.add(notificationsItem);
        return this;
    }

    @NotNull
    @Valid
    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(this.notifications);
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = Collections.unmodifiableList(notifications);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmptyDataResponse emptyDataResponse = (EmptyDataResponse) o;
        return Objects.equals(this.data, emptyDataResponse.data)
                && Objects.equals(this.notifications, emptyDataResponse.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.data, this.notifications);
    }

    @Override
    public String toString() {
        String sb = "class EmptyDataResponse {\n";
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