package com.ericsson.group.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="base_data")
public class BaseData implements Serializable{
	
	@Id// Assigns type as Primary Key
	@GeneratedValue(strategy=GenerationType.IDENTITY)// Used for Auto-Increment
	@Column(name="id")// Identifies Columns within Table
	private int id;
	
	@Column(name="date_time") private Date date_time;

    @ManyToOne
    @JoinColumn(name="failure_class")
    private FailureClass failure_class;

	@Column(name="cell_id") private Integer cell_id;
	@Column(name="duration") private Integer duration;

	@Column(name="ne_version") private String ne_version;
	@Column(name="imsi") private long imsi;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "cause_code", referencedColumnName = "cause_code"),
            @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    })
    private EventCause event_cause;

	@ManyToOne
	@JoinColumn(name="ue_type")
	private UE ue;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "market", referencedColumnName = "mcc"),
			@JoinColumn(name = "operator", referencedColumnName = "mnc")
	})
	private MccMnc mcc_mnc;
	
	public BaseData() {}
	
	public BaseData(Date date_time, Integer event_id, FailureClass failure_class, Integer cell_id, Integer duration,
			Integer cause_code, String ne_version, Long imsi, EventCause event_cause){
		this.date_time = date_time;
		this.cell_id = cell_id;
		this.duration = duration;
		this.event_cause = event_cause;
		this.ne_version = ne_version;
		this.imsi = imsi;

        this.failure_class = failure_class;
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

	public void setFailure_class(FailureClass failure_class) {
		this.failure_class = failure_class;
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

	public void setUe(UE ue) {
		this.ue = ue;
	}

	public UE getUe() {
		return ue;
	}

	public FailureClass getFailure_class() {
		return failure_class;
	}

	public EventCause getEvent_cause() {
		return event_cause;
	}

	public void setEvent_cause(EventCause event_cause) {
		this.event_cause = event_cause;
	}

	public MccMnc getMcc_mnc() {
		return mcc_mnc;
	}

	public void setMcc_mnc(MccMnc mcc_mnc) {
		this.mcc_mnc = mcc_mnc;
	}
}
