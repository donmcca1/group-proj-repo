package com.ericsson.group.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mypc1 on 03/04/2017.
 */
@Entity
@Table(name="event_cause")
@IdClass(EventCausePK.class)
public class EventCause implements Serializable{
    @Id
    @Column(name = "cause_code") private Integer cause_code;

    @Id
    @Column(name = "event_id") private Integer event_id;

    @Column(name = "description") private String description;

    public Integer getCause_code() {
        return cause_code;
    }

    public void setCause_code(Integer cause_code) {
        this.cause_code = cause_code;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
