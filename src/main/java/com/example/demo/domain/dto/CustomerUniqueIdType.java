package com.example.demo.domain.dto;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public enum CustomerUniqueIdType {

    CID("CID"),
    SCOTIACARD("SCOTIACARD"),
    CREDITCARD("CREDITCARD"),
    USERID("USERID"),
    TAXID("TAXID"),
    DNI("DNI"),
    FOREIGNCARD("FOREIGNCARD"),
    PASSPORT("PASSPORT");

    private String value;

    CustomerUniqueIdType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CustomerUniqueIdType fromValue(String value) {
        for (CustomerUniqueIdType b : CustomerUniqueIdType.values()) {
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