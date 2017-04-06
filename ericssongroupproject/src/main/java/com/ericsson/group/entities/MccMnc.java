package com.ericsson.group.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mypc1 on 04/04/2017.
 */
@Entity
@Table(name="mcc_mnc")
@IdClass(MccMncPK.class)
public class MccMnc {
    @Id
    @Column(name = "mcc")
    private Integer mcc;

    @Id
    @Column(name = "mnc")
    private Integer mnc;

    @Column(name = "country") private String country;
    @Column(name = "operator") private String operator;

    public MccMnc() {
    }

    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    public Integer getMnc() {
        return mnc;
    }

    public void setMnc(Integer mnc) {
        this.mnc = mnc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
