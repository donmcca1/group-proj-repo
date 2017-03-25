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
	
	@Column(name="date_time") private Date date_time;
	@Column(name="event_id") private Integer event_id;
	@Column(name="failure_class") private Integer failure_class;
	@Column(name="ue_type") private Integer ue_type;
	@Column(name="market") private Integer market;
	@Column(name="operator") private Integer operator;
	@Column(name="cell_id") private Integer cell_id;
	@Column(name="duration") private Integer duration;
	@Column(name="cause_code") private Integer cause_code;
	@Column(name="ne_version") private String ne_version;
	@Column(name="imsi") private long imsi;
	
	
	public BaseData() {}
	
	public BaseData(Date date_time, Integer event_id, Integer failure_class, Integer ue_type,
			Integer market, Integer operator, Integer cell_id, Integer duration,
			Integer cause_code, String ne_version, Long imsi){
		this.date_time = date_time;
		this.event_id = event_id;
		this.failure_class = failure_class;
		this.ue_type = ue_type;
		this.market = market;
		this.operator = operator;
		this.cell_id = cell_id;
		this.duration = duration;
		this.cause_code = cause_code;
		this.ne_version = ne_version;
		this.imsi = imsi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	public Integer getFailure_class() {
		return failure_class;
	}

	public void setFailure_class(Integer failure_class) {
		this.failure_class = failure_class;
	}

	public Integer getUe_type() {
		return ue_type;
	}

	public void setUe_type(Integer ue_type) {
		this.ue_type = ue_type;
	}

	public Integer getMarket() {
		return market;
	}

	public void setMarket(Integer market) {
		this.market = market;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Integer getCell_id() {
		return cell_id;
	}

	public void setCell_id(Integer cell_id) {
		this.cell_id = cell_id;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getCause_code() {
		return cause_code;
	}

	public void setCause_code(Integer cause_code) {
		this.cause_code = cause_code;
	}

	public String getNe_version() {
		return ne_version;
	}

	public void setNe_version(String ne_version) {
		this.ne_version = ne_version;
	}

	public long getImsi() {
		return imsi;
	}

	public void setImsi(long imsi) {
		this.imsi = imsi;
	}

}
