package com.umarbariev.projects.cb_demo_task.controller;

import com.umarbariev.projects.cb_demo_task.model.InsuranceCompany;
import com.umarbariev.projects.cb_demo_task.service.InsuranceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsuranceCompanyController {

    private InsuranceCompanyService insuranceCompanyService;

    @Autowired
    public InsuranceCompanyController(InsuranceCompanyService insuranceCompanyService) {
        this.insuranceCompanyService = insuranceCompanyService;
    }

    @GetMapping("/companies")
    public List<InsuranceCompany> getAllCompanies() {
        return insuranceCompanyService.getAllCompanies();
    }

    @GetMapping("/companies/{tin}")
    public List<InsuranceCompany> getCompaniesByTIN(@PathVariable(name = "tin") String TIN) {
        return insuranceCompanyService.getCompaniesByTIN(TIN);
    }

    @PostMapping("/companies")
    public InsuranceCompany addCompany(@RequestBody InsuranceCompany insuranceCompany) {
        return insuranceCompanyService.saveCompany(insuranceCompany);
    }

    @PutMapping("/companies")
    public InsuranceCompany updateCompany(@RequestBody InsuranceCompany insuranceCompany) {
        return insuranceCompanyService.saveCompany(insuranceCompany);
    }

}
