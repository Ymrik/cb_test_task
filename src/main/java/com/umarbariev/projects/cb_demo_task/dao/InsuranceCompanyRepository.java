package com.umarbariev.projects.cb_demo_task.dao;

import com.umarbariev.projects.cb_demo_task.model.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, String> {
    List<InsuranceCompany> getInsuranceCompaniesByTINStartsWith(String TIN);

    List<InsuranceCompany> getInsuranceCompaniesByPSRNStartsWith(String PSRN);

    List<InsuranceCompany> getInsuranceCompaniesByFullNameIgnoreCaseStartsWith(String fullName);

    InsuranceCompany getInsuranceCompanyByTIN(String TIN);

    InsuranceCompany getInsuranceCompanyByPSRN(String PSRN);

    List<InsuranceCompany> findAllByOrderByTINAsc();

    List<InsuranceCompany> findAllByOrderByTINDesc();

    List<InsuranceCompany> findAllByOrderByPSRNAsc();

    List<InsuranceCompany> findAllByOrderByPSRNDesc();
}
