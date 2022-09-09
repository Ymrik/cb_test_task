package com.umarbariev.projects.cb_demo_task.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Table(name = "insurance_companies")
@Entity
public class InsuranceCompany {
    @Id
    @Column(name = "TIN", nullable = false, unique = true)
    @Pattern(regexp = "[0-9]{10}", message = "TIN must consist of 10 digits.")
    private String TIN;
    @Column(name = "PSRN", nullable = false, unique = true)
    @Pattern(regexp = "[0-9]{13}", message = "PSRN must consist of 13 digits.")
    private String PSRN;
    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Full name can't be blank.")
    private String fullName;
    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address can't be blank.")
    private String address;
}
