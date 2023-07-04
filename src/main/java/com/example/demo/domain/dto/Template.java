package com.example.demo.domain.dto;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class Template {

    @JsonProperty("title")
    private String title;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("language")
    private String language;

    @JsonProperty("template_variables")
    private TemplateTemplateVariables templateVariables;

    public Template() {
    }

    public Template(String title, String templateId, String language, TemplateTemplateVariables templateVariables) {
        super();
        this.title = title;
        this.templateId = templateId;
        this.language = language;
        this.templateVariables = new TemplateTemplateVariables(templateVariables);
    }

    public Template(Template template) {
        this(template.getTitle(), template.getTemplateId(), template.getLanguage(), template.getTemplateVariables());
    }

    public Template title(String title) {
        this.title = title;
        return this;
    }

    @Size(max = 256)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Template templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    @NotNull
    @Size(min = 5, max = 20)
    public String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Template language(String language) {
        this.language = language;
        return this;
    }

    @NotNull
    @Size(max = 2)
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Template templateVariables(TemplateTemplateVariables templateVariables) {
        this.templateVariables = new TemplateTemplateVariables(templateVariables);
        return this;
    }

    @Valid
    public TemplateTemplateVariables getTemplateVariables() {
        return new TemplateTemplateVariables(this.templateVariables);
    }

    public void setTemplateVariables(TemplateTemplateVariables templateVariables) {
        this.templateVariables = new TemplateTemplateVariables(templateVariables);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Template template = (Template) o;
        return Objects.equals(this.title, template.title) && Objects.equals(this.templateId, template.templateId)
                && Objects.equals(this.language, template.language)
                && Objects.equals(this.templateVariables, template.templateVariables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.templateId, this.language, this.templateVariables);
    }

    @Override
    public String toString() {
        String sb = "class Template {\n";
        sb = sb + "    title: " + toIndentedString(this.title) + "\n";
        sb = sb + "    templateId: " + toIndentedString(this.templateId) + "\n";
        sb = sb + "    language: " + toIndentedString(this.language) + "\n";
        sb = sb + "    templateVariables: " + toIndentedString(this.templateVariables) + "\n";
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