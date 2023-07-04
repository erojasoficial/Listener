package com.example.demo.domain.dto;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Template_template_variables")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class TemplateTemplateVariables {

    @JsonProperty("content")
    private Object content;

    public TemplateTemplateVariables() {
    }

    public TemplateTemplateVariables(Object content) {
        super();
        this.content = content;
    }

    public TemplateTemplateVariables(TemplateTemplateVariables templateTemplateVariables) {

        this(templateTemplateVariables.getContent());
    }

    public TemplateTemplateVariables content(Object content) {
        this.content = content;
        return this;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemplateTemplateVariables templateTemplateVariables = (TemplateTemplateVariables) o;
        return Objects.equals(this.content, templateTemplateVariables.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.content);
    }

    @Override
    public String toString() {
        String sb = "class TemplateTemplateVariables {\n";
        sb = sb + "    content: " + toIndentedString(this.content) + "\n";
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