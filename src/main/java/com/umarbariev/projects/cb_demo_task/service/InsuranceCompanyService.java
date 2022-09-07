package com.umarbariev.projects.cb_demo_task.service;

import com.umarbariev.projects.cb_demo_task.dao.InsuranceCompanyRepository;
import com.umarbariev.projects.cb_demo_task.model.InsuranceCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceCompanyService {
    private InsuranceCompanyRepository insuranceCompanyRepository;

    @Autowired
    public InsuranceCompanyService(InsuranceCompanyRepository insuranceCompanyRepository) {
        this.insuranceCompanyRepository = insuranceCompanyRepository;
    }

    public List<InsuranceCompany> getAllCompanies() {
        return insuranceCompanyRepository.findAll();
    }

    public List<InsuranceCompany> getCompaniesByTIN(String TIN) {
        return insuranceCompanyRepository.getInsuranceCompaniesByTINStartsWith(TIN);
    }

    public InsuranceCompany saveCompany(InsuranceCompany insuranceCompany) {
        return insuranceCompanyRepository.save(insuranceCompany);
    }
}
