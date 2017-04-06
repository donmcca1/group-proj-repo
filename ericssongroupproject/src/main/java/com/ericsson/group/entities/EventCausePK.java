package com.ericsson.group.entities;

import java.io.Serializable;

/**
 * Created by mypc1 on 03/04/2017.
 */
public class EventCausePK implements Serializable{
    protected Integer cause_code;
    protected Integer event_id;

    public EventCausePK() {
    }

    public EventCausePK(Integer cause_code, Integer event_id) {
        this.cause_code = cause_code;
        this.event_id = event_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventCausePK that = (EventCausePK) o;

        if (!cause_code.equals(that.cause_code)) return false;
        return event_id.equals(that.event_id);
    }

    @Override
    public int hashCode() {
        int result = cause_code.hashCode();
        result = 31 * result + event_id.hashCode();
        return result;
    }
}
