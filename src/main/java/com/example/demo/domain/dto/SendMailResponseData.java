package com.example.demo.domain.dto;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("SendMailResponse_data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class SendMailResponseData {

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("status")
    private String status;

    public SendMailResponseData() {
    }

    public SendMailResponseData(String referenceId, String status) {
        super();
        this.referenceId = referenceId;
        this.status = status;
    }

    public SendMailResponseData(SendMailResponseData sendMailResponseData) {
        this(sendMailResponseData.getReferenceId(), sendMailResponseData.getStatus());
    }

    public SendMailResponseData referenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    @Size(max = 36)
    public String getReferenceId() {
        return this.referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public SendMailResponseData status(String status) {
        this.status = status;
        return this;
    }

    @Size(max = 512)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SendMailResponseData sendMailResponseData = (SendMailResponseData) o;
        return Objects.equals(this.referenceId, sendMailResponseData.referenceId)
                && Objects.equals(this.status, sendMailResponseData.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.referenceId, this.status);
    }

    @Override
    public String toString() {
        String sb = "class SendMailResponseData {\n";
        sb = sb + "    referenceId: " + toIndentedString(this.referenceId) + "\n";
        sb = sb + "    status: " + toIndentedString(this.status) + "\n";
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