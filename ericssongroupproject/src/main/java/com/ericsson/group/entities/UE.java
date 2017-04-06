package com.ericsson.group.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mypc1 on 04/04/2017.
 */
@Entity
@Table(name="ue")
public class UE {
    @Id// Assigns type as Primary Key
    @Column(name = "tac")// Identifies Columns within Table
    private Integer tac;

    @Column(name = "marketing_name") private String marketing_name;
    @Column(name = "manufacturer") private String manufacturer;
    @Column(name = "model") private String model;
    @Column(name = "vendor_name") private String vendor_name;
    @Column(name = "ue_type") private String ue_type;
    @Column(name = "os") private String os;
    @Column(name = "input_mode") private String input_mode;

    public UE() {
    }

    public Integer getTac() {
        return tac;
    }

    public void setTac(Integer tac) {
        this.tac = tac;
    }

    public String getMarketing_name() {
        return marketing_name;
    }

    public void setMarketing_name(String marketing_name) {
        this.marketing_name = marketing_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getUe_type() {
        return ue_type;
    }

    public void setUe_type(String ue_type) {
        this.ue_type = ue_type;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getInput_mode() {
        return input_mode;
    }

    public void setInput_mode(String input_mode) {
        this.input_mode = input_mode;
    }

}
