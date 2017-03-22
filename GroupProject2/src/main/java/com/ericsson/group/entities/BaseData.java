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
	@Column(name="event_id") private Integer event_id;
	@Column(name="ue_type") private Integer ue_type;
	@Column(name="duration") private Integer duration;
	@Column(name="cause_code") private Integer cause_code;
	@Column(name="failure_class") private Integer failure_class;
	@Column(name="date_time") private Date date;
	
	public BaseData() {}
	
	public BaseData(long imsi, Integer event_id, Integer ue_type, Integer cause_code, Integer failure_class, Date date, Integer duration){
		this.imsi = imsi;
		this.event_id = event_id;
		this.ue_type = ue_type;
		this.cause_code = cause_code;
		this.failure_class = failure_class;
		this.date = date;
		this.duration = duration;
	}

	public Integer getUe_type() {
		return ue_type;
	}

	public void setUe_type(Integer ue_type) {
		this.ue_type = ue_type;
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
		return failure_class;
	}

	public void setFailureClass(Integer failure_class) {
		this.failure_class = failure_class;
	}

	public void setImsi(long imsi) {
		this.imsi = imsi;
	}

	public Integer getEventId() {
		return event_id;
	}

	public void setEventId(Integer event_id) {
		this.event_id = event_id;
	}

	public Integer getCauseCode() {
		return cause_code;
	}

	public void setCauseCode(Integer cause_code) {
		this.cause_code = cause_code;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	

}
