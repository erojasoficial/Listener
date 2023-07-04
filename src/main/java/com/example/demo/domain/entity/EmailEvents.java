package com.example.demo.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "EMAIL_LISTENER", name = "EMAIL_EVENTS")
public class EmailEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer EVENT_ID;

    @Column(name = "DATA_QUEUE_NAME")
    private String DATA_QUEUE_NAME;

    @Column(name = "EVENT_STATE", nullable = false)
    private String EVENT_STATE = "QUEUED";

    @Column(name = "PROCESSING_STATE", nullable = false)
    private String PROCESSING_STATE = "UNPROCESSED";

    @Column(name = "EVENT_DATE", nullable = false)
    private Date EVENT_DATE = new Date();

    @Column(name = "PROCESSING_DATE")
    private Date PROCESSING_DATE;

    @Column(name = "INPUT_DATA_QUEUE", length = 100000)
    private String INPUT_DATA_QUEUE;

    @Column(name = "ERROR_DETAILS", length = 100000)
    private String ERROR_DETAILS;

    @Column(name = "DESTINATION_EMAIL", length = 256)
    private String DESTINATION_EMAIL;

    @Column(name = "EMAIL_SUBJECT", length = 256)
    private String EMAIL_SUBJECT;

    @Column(name = "EMAIL_JSON", length = 100000)
    private String EMAIL_JSON;

    @Column(name = "ENCRYPTED_EMAIL", length = 100000)
    private String ENCRYPTED_EMAIL;

    public Integer getEventId() {
        return EVENT_ID;
    }

    public void setEventId(Integer EVENT_ID) {
        this.EVENT_ID = EVENT_ID;
    }

    public String getDataQueueName() {
        return DATA_QUEUE_NAME;
    }

    public void setDataQueueName(String DATA_QUEUE_NAME) {
        this.DATA_QUEUE_NAME = DATA_QUEUE_NAME;
    }

    public String getEventState() {
        return EVENT_STATE;
    }

    public void setEventState(String EVENT_STATE) {
        this.EVENT_STATE = EVENT_STATE;
    }

    public String getProcessingState() {
        return PROCESSING_STATE;
    }

    public void setProcessingState(String PROCESSING_STATE) {
        this.PROCESSING_STATE = PROCESSING_STATE;
    }

    public Date getEventDate() {
        return EVENT_DATE;
    }

    public void setEventDate(Date EVENT_DATE) {
        this.EVENT_DATE = EVENT_DATE;
    }

    public Date getProcessingDate() {
        return PROCESSING_DATE;
    }

    public void setProcessingDate(Date PROCESSING_DATE) {
        this.PROCESSING_DATE = PROCESSING_DATE;
    }

    public String getInputDataQueue() {
        return INPUT_DATA_QUEUE;
    }

    public void setInputDataQueue(String INPUT_DATA_QUEUE) {
        this.INPUT_DATA_QUEUE = INPUT_DATA_QUEUE;
    }

    public String getErrorDetails() {
        return ERROR_DETAILS;
    }

    public void setErrorDetails(String ERROR_DETAILS) {
        this.ERROR_DETAILS = ERROR_DETAILS;
    }

    public String getDestinationEmail() {
        return DESTINATION_EMAIL;
    }

    public void setDestinationEmail(String DESTINATION_EMAIL) {
        this.DESTINATION_EMAIL = DESTINATION_EMAIL;
    }

    public String getEmailSubject() {
        return EMAIL_SUBJECT;
    }

    public void setEmailSubject(String EMAIL_SUBJECT) {
        this.EMAIL_SUBJECT = EMAIL_SUBJECT;
    }

    public String getEmailJson() {
        return EMAIL_JSON;
    }

    public void setEmailJson(String EMAIL_JSON) {
        this.EMAIL_JSON = EMAIL_JSON;
    }

    public String getEncryptedEmail() {
        return ENCRYPTED_EMAIL;
    }

    public void setEncryptedEmail(String ENCRYPTED_EMAIL) {
        this.ENCRYPTED_EMAIL = ENCRYPTED_EMAIL;
    }
}
