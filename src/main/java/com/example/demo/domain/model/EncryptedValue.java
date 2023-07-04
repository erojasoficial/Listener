package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.processing.Generated;
import javax.validation.constraints.Size;
import java.util.Objects;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:49:05.877922800-05:00[America/Bogota]")
public class EncryptedValue {

    @JsonProperty("encrypted_value")
    private String encryptedValue;

    public EncryptedValue encryptedValue(String encryptedValue) {
        this.encryptedValue = encryptedValue;
        return this;
    }

    @Size(max = 2_147_483_647)
    public String getEncryptedValue() {
        return this.encryptedValue;
    }

    public void setEncryptedValue(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EncryptedValue encrypted = (EncryptedValue) o;
        return Objects.equals(this.encryptedValue, encrypted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.encryptedValue);
    }

    @Override
    public String toString() {
        String sb = "class EncryptedValue {\n";
        sb = sb + "    encryptedValue: " + toIndentedString(this.encryptedValue) + "\n";
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