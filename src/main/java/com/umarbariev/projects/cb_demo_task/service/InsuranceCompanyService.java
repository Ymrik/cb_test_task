package com.umarbariev.projects.cb_demo_task.service;

import com.umarbariev.projects.cb_demo_task.dao.InsuranceCompanyRepository;
import com.umarbariev.projects.cb_demo_task.exception_handling.CompanyAlreadyInDatabaseException;
import com.umarbariev.projects.cb_demo_task.exception_handling.NoSuchCompanyInDBException;
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

    public List<InsuranceCompany> getAllCompanies(){
        return insuranceCompanyRepository.findAll();
    }

    public List<InsuranceCompany> getAllCompanies(String field, String order) {
        if (field.equals("tin")) {
            if (order.equals("asc")) {
                return insuranceCompanyRepository.findAllByOrderByTINAsc();
            } else return insuranceCompanyRepository.findAllByOrderByTINDesc();
        }
        if (field.equals("psrn")) {
            if (order.equals("asc")) {
                return insuranceCompanyRepository.findAllByOrderByPSRNAsc();
            } else return insuranceCompanyRepository.findAllByOrderByPSRNDesc();
        }
        return insuranceCompanyRepository.findAll();
    }


    public List<InsuranceCompany> getCompaniesByTIN(String TIN) {
        return insuranceCompanyRepository.getInsuranceCompaniesByTINStartsWith(TIN);
    }

    public List<InsuranceCompany> getCompaniesByPSRN(String PSRN) {
        return insuranceCompanyRepository.getInsuranceCompaniesByPSRNStartsWith(PSRN);
    }

    public List<InsuranceCompany> getCompaniesByFullName(String fullName) {
        return insuranceCompanyRepository.getInsuranceCompaniesByFullNameIgnoreCaseStartsWith(fullName);
    }

    public InsuranceCompany getCompanyByTIN(String TIN) {
        return insuranceCompanyRepository.getInsuranceCompanyByTIN(TIN);
    }

    public InsuranceCompany getCompanyByPSRN(String PSRN) {
        return insuranceCompanyRepository.getInsuranceCompanyByPSRN(PSRN);
    }

    public InsuranceCompany deleteCompany(String TIN) {
        var company = getCompanyByTIN(TIN);
        if (company == null) {
            throw new NoSuchCompanyInDBException("No company with TIN = " + TIN + " found in the database");
        }
        insuranceCompanyRepository.delete(company);
        return company;
    }

    public InsuranceCompany updateCompany(InsuranceCompany insuranceCompany) {
        var TIN = insuranceCompany.getTIN();
        var companyFromDBByTIN = getCompaniesByTIN(TIN);
        if (companyFromDBByTIN == null) {
            throw new NoSuchCompanyInDBException("No company with TIN = " + TIN + " found in the database");
        }

        var PSRN = insuranceCompany.getPSRN();
        var companyFromDBByPSRN = getCompanyByPSRN(PSRN);
        if (companyFromDBByPSRN != null) {
            if (companyFromDBByPSRN.getTIN() != TIN) {
                var message = "Other company with PSRN = " + PSRN + " already exists in the database";
                throw new CompanyAlreadyInDatabaseException(message);
            }
        }

        return insuranceCompanyRepository.save(insuranceCompany);
    }


    public InsuranceCompany addCompany(InsuranceCompany insuranceCompany) {
        var TIN = insuranceCompany.getTIN();
        var PSRN = insuranceCompany.getPSRN();

        var insuranceCompanyByTIN = getCompanyByTIN(TIN);
        if (insuranceCompanyByTIN != null) {
            throw new CompanyAlreadyInDatabaseException("Company with TIN = " + TIN + " already exists in database.");
        }

        var insuranceCompanyByPSRN = getCompanyByPSRN(PSRN);
        if (insuranceCompanyByPSRN != null) {
            throw new CompanyAlreadyInDatabaseException("Company with PSRN = " + PSRN + " already exists in database.");
        }

        return insuranceCompanyRepository.save(insuranceCompany);
    }
}
