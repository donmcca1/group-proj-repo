package com.ericsson.group.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="base_data")
public class BaseData implements Serializable{
	
	@Id// Assigns type as Primary Key
	@GeneratedValue(strategy=GenerationType.IDENTITY)// Used for Auto-Increment
	@Column(name="id")// Identifies Columns within Table
	private int id;
	
	@Column(name="imsi") private long imsi;
	@Column(name="event_id") private Integer eventId;
	@Column(name="cause_code") private Integer causeCode;
	@Column(name="failure_class") private Integer failureClass;
	@Column(name="date_time") private Date date;
	
	public BaseData() {}
	
	public BaseData(long imsi, Integer eventId, Integer causeCode, Integer failureClass, Date date){
		this.imsi = imsi;
		this.eventId = eventId;
		this.causeCode = causeCode;
		this.failureClass = failureClass;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getImsi() {
		return imsi;
	}

	public Integer getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(Integer failureClass) {
		this.failureClass = failureClass;
	}

	public void setImsi(long imsi) {
		this.imsi = imsi;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(Integer causeCode) {
		this.causeCode = causeCode;
	}

}
