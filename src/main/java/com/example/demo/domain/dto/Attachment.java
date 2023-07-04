package com.example.demo.domain.dto;

import java.util.Objects;

import javax.annotation.Generated;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-03T22:57:40.870197800-05:00[America/Bogota]")
public class Attachment {

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_content")
    private String fileContent;

    @JsonProperty("file_type")
    private String fileType;

    public Attachment fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Size(max = 60)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Attachment fileContent(String fileContent) {
        this.fileContent = fileContent;
        return this;
    }

    @Size(max = 200_000)
    public String getFileContent() {
        return this.fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public Attachment fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    @Size(max = 4)
    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attachment attachment = (Attachment) o;
        return Objects.equals(this.fileName, attachment.fileName)
                && Objects.equals(this.fileContent, attachment.fileContent)
                && Objects.equals(this.fileType, attachment.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.fileName, this.fileContent, this.fileType);
    }

    @Override
    public String toString() {
        String sb = "class Attachment {\n";
        sb = sb + "    fileName: " + toIndentedString(this.fileName) + "\n";
        sb = sb + "    fileContent: " + toIndentedString(this.fileContent) + "\n";
        sb = sb + "    fileType: " + toIndentedString(this.fileType) + "\n";
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
