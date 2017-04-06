package com.ericsson.group.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mypc1 on 03/04/2017.
 */
@Entity
@Table(name="failure_class")
public class FailureClass implements Serializable {
    @Id// Assigns type as Primary Key
    @Column(name = "failure_class")// Identifies Columns within Table
    private int failureClass;

    @Column(name = "description")
    private String description;

    public FailureClass(int failureClass, String description) {
        this.failureClass = failureClass;
        this.description = description;
    }

    public FailureClass() {
    }

    public int getFailureClass() {
        return failureClass;
    }

    public void setFailureClass(int failureClass) {
        this.failureClass = failureClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}