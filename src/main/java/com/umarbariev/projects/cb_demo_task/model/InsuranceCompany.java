package com.umarbariev.projects.cb_demo_task.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "insurance_companies")
@Entity
public class InsuranceCompany {
    @Id
    @Column(name = "TIN", nullable = false, unique = true)
    private String TIN;
    @Column(name = "PSRN", nullable = false, unique = true)
    private String PSRN;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "address", nullable = false)
    private String address;
}
