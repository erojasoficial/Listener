package com.example.demo.domain.dto;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class Sender {

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    public Sender() {
    }

    public Sender(String email, String name) {
        super();
        this.email = email;
        this.name = name;
    }

    public Sender(Sender sender) {
        this(sender.getEmail(), sender.getName());
    }

    public Sender email(String email) {
        this.email = email;
        return this;
    }

    @Size(max = 256)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sender name(String name) {
        this.name = name;
        return this;
    }

    @Size(max = 256)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sender sender = (Sender) o;
        return Objects.equals(this.email, sender.email) && Objects.equals(this.name, sender.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email, this.name);
    }

    @Override
    public String toString() {
        String sb = "class Sender {\n";
        sb = sb + "    email: " + toIndentedString(this.email) + "\n";
        sb = sb + "    name: " + toIndentedString(this.name) + "\n";
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
